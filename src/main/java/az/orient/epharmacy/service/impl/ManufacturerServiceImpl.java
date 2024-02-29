package az.orient.epharmacy.service.impl;

import az.orient.epharmacy.dto.request.ManufacturerRequest;
import az.orient.epharmacy.dto.response.ManufacturerResponse;
import az.orient.epharmacy.dto.response.StatusResponse;
import az.orient.epharmacy.dto.response.Response;
import az.orient.epharmacy.entity.Manufacturer;
import az.orient.epharmacy.enums.ActiveType;
import az.orient.epharmacy.exception.Message;
import az.orient.epharmacy.exception.PharmacyException;
import az.orient.epharmacy.mapper.ManufacturerMapper;
import az.orient.epharmacy.repository.ManufacturerRepository;
import az.orient.epharmacy.service.ManufacturerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ManufacturerServiceImpl implements ManufacturerService {
    ManufacturerRepository manufacturerRepository;

    ManufacturerMapper mapper;

    @Override
    public Response<List<ManufacturerResponse>> getManufacturerList() {
        Response<List<ManufacturerResponse>> response = new Response<>();

        List<Manufacturer> manufacturerList = manufacturerRepository.findAllByActive(ActiveType.ACTIVE.value);
        if (manufacturerList.isEmpty()) {
            throw new PharmacyException(HttpStatus.NOT_FOUND, Message.MANUFACTURER_NOT_FOUND);
        }

        List<ManufacturerResponse> manufacturerResponseList = manufacturerList.stream().map(mapper::entityToResponse).toList();
        response.setT(manufacturerResponseList);
        response.setStatus(StatusResponse.successMessage());

        return response;
    }

    @Override
    public Response<ManufacturerResponse> getManufacturerById(Long manufacturerId) {
        Response<ManufacturerResponse> response = new Response<>();
        if (manufacturerId == null) {
            throw new PharmacyException(HttpStatus.BAD_REQUEST, Message.INVALID_REQUEST_DATA);
        }

        Manufacturer manufacturer = manufacturerRepository.findManufacturerByIdAndActive(manufacturerId, ActiveType.ACTIVE.value)
                .orElseThrow(() -> new PharmacyException(HttpStatus.NOT_FOUND, Message.MANUFACTURER_NOT_FOUND));

        ManufacturerResponse manufacturerResponse = mapper.entityToResponse(manufacturer);
        response.setT(manufacturerResponse);
        response.setStatus(StatusResponse.successMessage());

        return response;
    }

    @Override
    public StatusResponse saveManufacturer(ManufacturerRequest manufacturerRequest) {
        Manufacturer manufacturer = mapper.requestToEntity(manufacturerRequest);
        manufacturerRepository.save(manufacturer);

        return StatusResponse.createdMessage();
    }

    @Override
    public StatusResponse updateManufacturer(Long manufacturerId, ManufacturerRequest manufacturerRequest) {
        if (manufacturerId == null) {
            throw new PharmacyException(HttpStatus.BAD_REQUEST, Message.INVALID_REQUEST_DATA);
        }

        Manufacturer manufacturer = manufacturerRepository.findManufacturerByIdAndActive(manufacturerId, ActiveType.ACTIVE.value)
                .orElseThrow(() -> new PharmacyException(HttpStatus.NOT_FOUND, Message.MANUFACTURER_NOT_FOUND));

        manufacturer.setName(manufacturerRequest.getName());
        manufacturerRepository.save(manufacturer);

        return StatusResponse.successMessage();
    }

    @Override
    public StatusResponse deleteManufacturer(Long manufacturerId) {
        if (manufacturerId == null) {
            throw new PharmacyException(HttpStatus.BAD_REQUEST, Message.INVALID_REQUEST_DATA);
        }

        Manufacturer manufacturer = manufacturerRepository.findManufacturerByIdAndActive(manufacturerId, ActiveType.ACTIVE.value)
                .orElseThrow(() -> new PharmacyException(HttpStatus.NOT_FOUND, Message.MANUFACTURER_NOT_FOUND));

        manufacturer.setActive(ActiveType.DEACTIVE.value);
        manufacturerRepository.save(manufacturer);

        return StatusResponse.successMessage();
    }
}
