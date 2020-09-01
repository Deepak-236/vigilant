package com.demo.websearch.searchoverweb.service;

import com.demo.websearch.searchoverweb.dto.SearchResults;
import com.demo.websearch.searchoverweb.shared.constants.ApiConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Component
public class StackoverflowRequestService {

    @Autowired
    private HttpClientService httpClientService;

    public SearchResults getStackoverflowSearchResults(String searchString) throws IOException {
        String searchURI = buildSearchURI(searchString);
        String response = httpClientService.callGetApi(searchURI);

        SearchResults searchResults = new ObjectMapper().readValue(response, SearchResults.class);

        return searchResults;
    }

    private static String buildSearchURI(String searchString) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ApiConstants.STACK_OVERFLOW_BASE_URL+"/search")
                .queryParam("pagesize", ApiConstants.RESULTS_MAX_SIZE)
                .queryParam("order", ApiConstants.ORDER_BY)
                .queryParam("sort", ApiConstants.SORT_BY)
                .queryParam("intitle", searchString)
                .queryParam("site", ApiConstants.STACK_OVERFLOW_SITE);

        return builder.toUriString();
    }
}
