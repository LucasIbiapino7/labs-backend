package com.lab.backend.services.client;

import com.lab.backend.dtos.lattes.LattesProfileDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient(
        name = "lattes-client",
        url = "${LATTES_BASE_URL:http://localhost:8000/api}"
)
public interface LattesClient {

    @PostMapping(path = "/pesquisas/batch",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    Map<String, LattesProfileDto> getPesquisas(@RequestBody List<String> ids);

}
