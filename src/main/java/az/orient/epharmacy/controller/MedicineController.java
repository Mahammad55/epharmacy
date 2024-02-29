package az.orient.epharmacy.controller;

import az.orient.epharmacy.dto.request.MedicineRequest;
import az.orient.epharmacy.dto.response.MedicineResponse;
import az.orient.epharmacy.dto.response.Response;
import az.orient.epharmacy.dto.response.StatusResponse;
import az.orient.epharmacy.service.MedicineService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/medicines")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MedicineController {
    MedicineService medicineService;

    @GetMapping
    public Response<List<MedicineResponse>> getMedicineList() {
        return medicineService.getMedicineList();
    }

    @GetMapping("/{name}")
    public Response<MedicineResponse> getMedicineByName(@PathVariable(value = "name", required = false) String medicineName) {
        return medicineService.getMedicineByName(medicineName);
    }

    @GetMapping("/categoryId/{id}")
    public Response<List<MedicineResponse>> getMedicineListByCategoryId(@PathVariable(value = "id", required = false) Long categoryId) {
        return medicineService.getMedicineListByCategoryId(categoryId);
    }

    @GetMapping("/kindId/{id}")
    public Response<List<MedicineResponse>> getMedicineListByKindId(@PathVariable(value = "id", required = false) Long kindId) {
        return medicineService.getMedicineListByKindId(kindId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StatusResponse saveMedicine(@RequestBody @Valid MedicineRequest medicineRequest) {
        return medicineService.saveMedicine(medicineRequest);
    }

    @PutMapping("/{id}")
    public StatusResponse updateMedicine(
            @PathVariable(value = "id", required = false) Long medicineId,
            @RequestBody MedicineRequest medicineRequest) {
        return medicineService.updateMedicine(medicineId, medicineRequest);
    }

    @DeleteMapping("/{id}")
    public StatusResponse deleteMedicine(@PathVariable(value = "id", required = false) Long medicineId) {
        return medicineService.deleteMedicine(medicineId);
    }
}
