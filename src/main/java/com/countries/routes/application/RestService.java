package com.countries.routes.application;

import com.countries.routes.models.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpEntity;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * RestService
 */
@Service
public class RestService {

    RestTemplate rest;
    HttpHeaders headers;
    int status;

    public RestService() {
        this.rest = new RestTemplate();
        this.headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");
    }

    public String get(String uri) {
        HttpEntity<String> requestEntity = new HttpEntity<>("", headers);
        ResponseEntity<String> responseEntity = rest.exchange(uri, HttpMethod.GET, requestEntity, String.class);
        this.status = responseEntity.getStatusCodeValue();
        return responseEntity.getBody();
    }

    public List<Country> getCountries() {
        String jsonBody = get(Constants.API_PATH);
        Gson json = new Gson();
        Type countryListType = new TypeToken<ArrayList<Country>>() {
        }.getType();

        List<Country> countries = json.fromJson(jsonBody, countryListType);
        return countries;
    }
}
