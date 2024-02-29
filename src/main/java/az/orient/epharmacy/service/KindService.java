package az.orient.epharmacy.service;


import az.orient.epharmacy.dto.request.KindRequest;
import az.orient.epharmacy.dto.request.ManufacturerRequest;
import az.orient.epharmacy.dto.response.KindResponse;
import az.orient.epharmacy.dto.response.ManufacturerResponse;
import az.orient.epharmacy.dto.response.Response;
import az.orient.epharmacy.dto.response.StatusResponse;

import java.util.List;

public interface KindService {
    Response<List<KindResponse>> getKindList();

    Response<KindResponse> getKindById(Long kindId);

    StatusResponse saveKind(KindRequest kindRequest);

    StatusResponse updateKind(Long kindId,KindRequest kindRequest);

    StatusResponse deleteKind(Long kindId);
}
