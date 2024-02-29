package az.orient.epharmacy.service.impl;

import az.orient.epharmacy.dto.request.KindRequest;
import az.orient.epharmacy.dto.response.KindResponse;
import az.orient.epharmacy.dto.response.Response;
import az.orient.epharmacy.dto.response.StatusResponse;
import az.orient.epharmacy.entity.Kind;
import az.orient.epharmacy.enums.ActiveType;
import az.orient.epharmacy.exception.Message;
import az.orient.epharmacy.exception.PharmacyException;
import az.orient.epharmacy.mapper.KindMapper;
import az.orient.epharmacy.repository.KindRepository;
import az.orient.epharmacy.service.KindService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class KindServiceImpl implements KindService {
    KindRepository kindRepository;

    KindMapper mapper;

    @Override
    public Response<List<KindResponse>> getKindList() {
        Response<List<KindResponse>> response = new Response<>();

        List<Kind> kindList = kindRepository.findAllByActive(ActiveType.ACTIVE.value);
        if (kindList.isEmpty()) {
            throw new PharmacyException(HttpStatus.NOT_FOUND, Message.KIND_NOT_FOUND);
        }

        List<KindResponse> kindResponse = kindList.stream().map(mapper::entityToResponse).toList();
        response.setT(kindResponse);
        response.setStatus(StatusResponse.successMessage());

        return response;
    }

    @Override
    public Response<KindResponse> getKindById(Long kindId) {
        Response<KindResponse> response = new Response<>();
        if (kindId == null) {
            throw new PharmacyException(HttpStatus.BAD_REQUEST, Message.INVALID_REQUEST_DATA);
        }

        Kind kind = kindRepository.findKindByIdAndActive(kindId, ActiveType.ACTIVE.value)
                .orElseThrow(() -> new PharmacyException(HttpStatus.NOT_FOUND, Message.KIND_NOT_FOUND));

        KindResponse kindResponse = mapper.entityToResponse(kind);
        response.setT(kindResponse);
        response.setStatus(StatusResponse.successMessage());

        return response;
    }

    @Override
    public StatusResponse saveKind(KindRequest kindRequest) {
        Kind kind = mapper.requestToEntity(kindRequest);
        kindRepository.save(kind);

        return StatusResponse.createdMessage();
    }

    @Override
    public StatusResponse updateKind(Long kindId, KindRequest kindRequest) {
        if (kindId == null) {
            throw new PharmacyException(HttpStatus.BAD_REQUEST, Message.INVALID_REQUEST_DATA);
        }

        Kind kind = kindRepository.findKindByIdAndActive(kindId, ActiveType.ACTIVE.value)
                .orElseThrow(() -> new PharmacyException(HttpStatus.NOT_FOUND, Message.KIND_NOT_FOUND));

        kind.setName(kindRequest.getName());
        kindRepository.save(kind);

        return StatusResponse.successMessage();
    }

    @Override
    public StatusResponse deleteKind(Long kindId) {
        if (kindId == null) {
            throw new PharmacyException(HttpStatus.BAD_REQUEST, Message.INVALID_REQUEST_DATA);
        }

        Kind kind = kindRepository.findKindByIdAndActive(kindId, ActiveType.ACTIVE.value)
                .orElseThrow(() -> new PharmacyException(HttpStatus.NOT_FOUND, Message.KIND_NOT_FOUND));

        kind.setActive(ActiveType.DEACTIVE.value);
        kindRepository.save(kind);

        return StatusResponse.successMessage();
    }
}
