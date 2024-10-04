package com.ar.gfstabile.orders.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ar.gfstabile.orders.dto.common.DocumentDto;
import com.ar.gfstabile.orders.dto.common.PersonDto;
import com.ar.gfstabile.orders.util.ResponseHandler;

@Service
public class PeopleService {

    private static final String DOCUMENT_TYPE_KEY = "document_type";
    private static final String DOCUMENT_NUMBER_KEY = "document_number";
    private static final String FIND_BY_FILTER = "/v1/people/filter";

    @Value("${external.service.people.host}")
    private String serviceHost;

    @Autowired
    @Qualifier("peopleHttpClient")
    private HttpClient httpClient;

    @Autowired
    private ResponseHandler responseHandler;

    public Optional<PersonDto> getByDocument(DocumentDto document) {
        if (Objects.isNull(document) || !StringUtils.hasText(document.number())) {
            throw new Error("document_number is required");
        }
        try {
            StringBuilder stringBuilder = new StringBuilder(serviceHost).append(FIND_BY_FILTER).append("?")
                    .append(DOCUMENT_NUMBER_KEY).append("=").append(document.number());
            if (Objects.nonNull(document.type())) {
                stringBuilder.append("&").append(DOCUMENT_TYPE_KEY).append("=").append(document.type().getValue());
            }
            HttpRequest request = HttpRequest
                    .newBuilder(new URI(stringBuilder.toString())).GET().build();
            String result = httpClient.send(request, BodyHandlers.ofString()).body();
            return Optional.ofNullable(responseHandler.map(result, PersonDto.class));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}
