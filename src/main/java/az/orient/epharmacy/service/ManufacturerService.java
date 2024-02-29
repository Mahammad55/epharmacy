package az.orient.epharmacy.service;

import az.orient.epharmacy.dto.request.ManufacturerRequest;
import az.orient.epharmacy.dto.response.ManufacturerResponse;
import az.orient.epharmacy.dto.response.StatusResponse;
import az.orient.epharmacy.dto.response.Response;

import java.util.List;

public interface ManufacturerService {
    Response<List<ManufacturerResponse>> getManufacturerList();

    Response<ManufacturerResponse> getManufacturerById(Long manufacturerId);

    StatusResponse saveManufacturer(ManufacturerRequest manufacturerRequest);

    StatusResponse updateManufacturer(Long manufacturerId,ManufacturerRequest manufacturerRequest);

    StatusResponse deleteManufacturer(Long manufacturerId);
}
