package az.orient.epharmacy.service;

import az.orient.epharmacy.dto.request.MedicineRequest;
import az.orient.epharmacy.dto.response.MedicineResponse;
import az.orient.epharmacy.dto.response.Response;
import az.orient.epharmacy.dto.response.StatusResponse;

import java.util.List;

public interface MedicineService {
    Response<List<MedicineResponse>> getMedicineList();

    Response<MedicineResponse> getMedicineByName(String medicineName);

    StatusResponse saveMedicine(MedicineRequest medicineRequest);

    StatusResponse updateMedicine(Long medicineId, MedicineRequest medicineRequest);

    StatusResponse deleteMedicine(Long medicineId);

    Response<List<MedicineResponse>> getMedicineListByCategoryId(Long categoryId);

    Response<List<MedicineResponse>> getMedicineListByKindId(Long kindId);
}
