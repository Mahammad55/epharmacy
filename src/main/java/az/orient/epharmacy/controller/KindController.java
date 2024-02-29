package az.orient.epharmacy.controller;

import az.orient.epharmacy.dto.request.KindRequest;
import az.orient.epharmacy.dto.response.KindResponse;
import az.orient.epharmacy.dto.response.Response;
import az.orient.epharmacy.dto.response.StatusResponse;
import az.orient.epharmacy.service.KindService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/kinds")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class KindController {
    KindService kindService;

    @GetMapping
    public Response<List<KindResponse>> getKindList() {
        return kindService.getKindList();
    }

    @GetMapping("/{id}")
    public Response<KindResponse> getKindById(@PathVariable(value = "id", required = false) Long kindId) {
        return kindService.getKindById(kindId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StatusResponse saveKind(@RequestBody @Valid KindRequest kindRequest) {
        return kindService.saveKind(kindRequest);
    }

    @PutMapping("/{id}")
    public StatusResponse updateKind(
            @PathVariable(value = "id", required = false) Long kindId,
            @RequestBody @Valid KindRequest kindRequest) {
        return kindService.updateKind(kindId, kindRequest);
    }

    @DeleteMapping("/{id}")
    public StatusResponse deleteKind(@PathVariable(value = "id", required = false) Long kindId) {
        return kindService.deleteKind(kindId);
    }
}
