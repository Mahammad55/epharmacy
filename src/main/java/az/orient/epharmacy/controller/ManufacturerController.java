package az.orient.epharmacy.controller;

import az.orient.epharmacy.dto.request.ManufacturerRequest;
import az.orient.epharmacy.dto.response.ManufacturerResponse;
import az.orient.epharmacy.dto.response.StatusResponse;
import az.orient.epharmacy.dto.response.Response;
import az.orient.epharmacy.service.ManufacturerService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/manufactures")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ManufacturerController {
    ManufacturerService manufacturerService;

    @GetMapping
    public Response<List<ManufacturerResponse>> getManufacturerList() {
        return manufacturerService.getManufacturerList();
    }

    @GetMapping("/{id}")
    public Response<ManufacturerResponse> getManufacturerById(@PathVariable(value = "id", required = false) Long manufacturerId) {
        return manufacturerService.getManufacturerById(manufacturerId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StatusResponse saveManufacturer(@RequestBody @Valid ManufacturerRequest manufacturerRequest) {
        return manufacturerService.saveManufacturer(manufacturerRequest);
    }

    @PutMapping("/{id}")
    public StatusResponse updateManufacturer(
            @PathVariable(value = "id", required = false) Long manufacturerId,
            @RequestBody @Valid ManufacturerRequest manufacturerRequest) {
        return manufacturerService.updateManufacturer(manufacturerId, manufacturerRequest);
    }

    @DeleteMapping("/{id}")
    public StatusResponse deleteManufacturer(@PathVariable(value = "id", required = false) Long manufacturerId) {
        return manufacturerService.deleteManufacturer(manufacturerId);
    }
}
