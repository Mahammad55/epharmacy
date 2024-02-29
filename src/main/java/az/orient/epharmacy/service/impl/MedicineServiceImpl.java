package az.orient.epharmacy.service.impl;

import az.orient.epharmacy.dto.request.MedicineRequest;
import az.orient.epharmacy.dto.response.MedicineResponse;
import az.orient.epharmacy.dto.response.Response;
import az.orient.epharmacy.dto.response.StatusResponse;
import az.orient.epharmacy.entity.Category;
import az.orient.epharmacy.entity.Kind;
import az.orient.epharmacy.entity.Manufacturer;
import az.orient.epharmacy.entity.Medicine;
import az.orient.epharmacy.enums.ActiveType;
import az.orient.epharmacy.exception.Message;
import az.orient.epharmacy.exception.PharmacyException;
import az.orient.epharmacy.mapper.MedicineMapper;
import az.orient.epharmacy.repository.CategoryRepository;
import az.orient.epharmacy.repository.KindRepository;
import az.orient.epharmacy.repository.ManufacturerRepository;
import az.orient.epharmacy.repository.MedicineRepository;
import az.orient.epharmacy.service.MedicineService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MedicineServiceImpl implements MedicineService {
    MedicineRepository medicineRepository;

    ManufacturerRepository manufacturerRepository;

    CategoryRepository categoryRepository;

    KindRepository kindRepository;

    MedicineMapper mapper;

    @Override
    public Response<List<MedicineResponse>> getMedicineList() {
        Response<List<MedicineResponse>> response = new Response<>();
        List<Medicine> medicineList = medicineRepository.findAllByActive(ActiveType.ACTIVE.value);
        if (medicineList.isEmpty()) {
            throw new PharmacyException(HttpStatus.NOT_FOUND, Message.MEDICINE_NOT_FOUND);
        }

        List<MedicineResponse> medicineResponseList = medicineList.stream().map(mapper::entityToResponse).toList();
        response.setT(medicineResponseList);
        response.setStatus(StatusResponse.successMessage());

        return response;
    }

    @Override
    public Response<MedicineResponse> getMedicineByName(String medicineName) {
        Response<MedicineResponse> response = new Response<>();
        if (medicineName == null) {
            throw new PharmacyException(HttpStatus.BAD_REQUEST, Message.INVALID_REQUEST_DATA);
        }

        Medicine medicine = medicineRepository.findMedicineByNameAndActive(medicineName, ActiveType.ACTIVE.value)
                .orElseThrow(() -> new PharmacyException(HttpStatus.NOT_FOUND, Message.MEDICINE_NOT_FOUND));

        MedicineResponse medicineResponse = mapper.entityToResponse(medicine);
        response.setT(medicineResponse);
        response.setStatus(StatusResponse.successMessage());

        return response;
    }

    @Override
    public StatusResponse saveMedicine(MedicineRequest medicineRequest) {
        Long manufacturerId = medicineRequest.getManufacturerId();
        Long categoryId = medicineRequest.getCategoryId();
        Long kindId = medicineRequest.getKindId();
        if (manufacturerId == null || categoryId == null || kindId == null) {
            throw new PharmacyException(HttpStatus.BAD_REQUEST, Message.INVALID_REQUEST_DATA);
        }

        manufacturerRepository.findManufacturerByIdAndActive(manufacturerId, ActiveType.ACTIVE.value)
                .orElseThrow(() -> new PharmacyException(HttpStatus.NOT_FOUND, Message.MANUFACTURER_NOT_FOUND));

        categoryRepository.findCategoryByIdAndActive(categoryId, ActiveType.ACTIVE.value)
                .orElseThrow(() -> new PharmacyException(HttpStatus.NOT_FOUND, Message.CATEGORY_NOT_FOUND));

        kindRepository.findKindByIdAndActive(kindId, ActiveType.ACTIVE.value)
                .orElseThrow(() -> new PharmacyException(HttpStatus.NOT_FOUND, Message.KIND_NOT_FOUND));

        Medicine medicine = mapper.requestToEntity(medicineRequest);
        medicineRepository.save(medicine);

        return StatusResponse.createdMessage();
    }

    @Override
    public StatusResponse updateMedicine(Long medicineId, MedicineRequest medicineRequest) {
        Long manufacturerId = medicineRequest.getManufacturerId();
        Long categoryId = medicineRequest.getCategoryId();
        Long kindId = medicineRequest.getKindId();
        if (medicineId == null || manufacturerId == null || categoryId == null || kindId == null) {
            throw new PharmacyException(HttpStatus.BAD_REQUEST, Message.INVALID_REQUEST_DATA);
        }

        Medicine medicine = medicineRepository.findMedicineByIdAndActive(medicineId, ActiveType.ACTIVE.value)
                .orElseThrow(() -> new PharmacyException(HttpStatus.NOT_FOUND, Message.MEDICINE_NOT_FOUND));

        Manufacturer manufacturer = manufacturerRepository.findManufacturerByIdAndActive(manufacturerId, ActiveType.ACTIVE.value)
                .orElseThrow(() -> new PharmacyException(HttpStatus.NOT_FOUND, Message.MANUFACTURER_NOT_FOUND));

        Category category = categoryRepository.findCategoryByIdAndActive(categoryId, ActiveType.ACTIVE.value)
                .orElseThrow(() -> new PharmacyException(HttpStatus.NOT_FOUND, Message.CATEGORY_NOT_FOUND));

        Kind kind = kindRepository.findKindByIdAndActive(kindId, ActiveType.ACTIVE.value)
                .orElseThrow(() -> new PharmacyException(HttpStatus.NOT_FOUND, Message.KIND_NOT_FOUND));

        medicine.setName(medicineRequest.getName());
        medicine.setCount(medicineRequest.getCount());
        medicine.setDescription(medicineRequest.getDescription());
        medicine.setStrength(medicineRequest.getStrength());
        medicine.setFabDate(medicineRequest.getFabDate());
        medicine.setExpDate(medicineRequest.getExpDate());
        medicine.setPrice(medicineRequest.getPrice());
        medicine.setManufacturer(manufacturer);
        medicine.setCategory(category);
        medicine.setKind(kind);
        medicineRepository.save(medicine);

        return StatusResponse.successMessage();
    }

    @Override
    public StatusResponse deleteMedicine(Long medicineId) {
        if (medicineId == null) {
            throw new PharmacyException(HttpStatus.BAD_REQUEST, Message.INVALID_REQUEST_DATA);
        }

        Medicine medicine = medicineRepository.findMedicineByIdAndActive(medicineId, ActiveType.ACTIVE.value)
                .orElseThrow(() -> new PharmacyException(HttpStatus.NOT_FOUND, Message.MEDICINE_NOT_FOUND));

        medicine.setActive(ActiveType.DEACTIVE.value);
        medicineRepository.save(medicine);

        return StatusResponse.successMessage();
    }

    @Override
    public Response<List<MedicineResponse>> getMedicineListByCategoryId(Long categoryId) {
        Response<List<MedicineResponse>> response=new Response<>();
        if (categoryId == null) {
            throw new PharmacyException(HttpStatus.BAD_REQUEST, Message.INVALID_REQUEST_DATA);
        }

        Category category = categoryRepository.findCategoryByIdAndActive(categoryId, ActiveType.ACTIVE.value)
                .orElseThrow(() -> new PharmacyException(HttpStatus.NOT_FOUND, Message.CATEGORY_NOT_FOUND));

        List<Medicine> medicineList = medicineRepository.findAllByCategoryAndActive(category, ActiveType.ACTIVE.value);
        if(medicineList.isEmpty()){
            throw new PharmacyException(HttpStatus.NOT_FOUND, Message.MEDICINE_NOT_FOUND);
        }

        List<MedicineResponse> medicineResponseList = medicineList.stream().map(mapper::entityToResponse).toList();
        response.setT(medicineResponseList);
        response.setStatus(StatusResponse.successMessage());

        return response;
    }

    @Override
    public Response<List<MedicineResponse>> getMedicineListByKindId(Long kindId) {
        Response<List<MedicineResponse>> response=new Response<>();
        if(kindId==null){
            throw new PharmacyException(HttpStatus.BAD_REQUEST, Message.INVALID_REQUEST_DATA);
        }

        Kind kind = kindRepository.findKindByIdAndActive(kindId, ActiveType.ACTIVE.value)
                .orElseThrow(() -> new PharmacyException(HttpStatus.NOT_FOUND, Message.KIND_NOT_FOUND));

        List<Medicine> medicineList = medicineRepository.findAllByKindAndActive(kind, ActiveType.ACTIVE.value);
        if(medicineList.isEmpty()){
            throw new PharmacyException(HttpStatus.NOT_FOUND, Message.MEDICINE_NOT_FOUND);
        }

        List<MedicineResponse> medicineResponseList = medicineList.stream().map(mapper::entityToResponse).toList();
        response.setT(medicineResponseList);
        response.setStatus(StatusResponse.successMessage());

        return response;
    }
}
