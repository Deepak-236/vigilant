package com.demo.websearch.searchoverweb.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResults {

    @JsonProperty("items")
    public List<SearchResultDTO> searchResultDTOList;

    public List<SearchResultDTO> getSearchResultDTOList() {
        return searchResultDTOList;
    }

    public void setSearchResultDTOList(List<SearchResultDTO> searchResultDTOList) {
        this.searchResultDTOList = searchResultDTOList;
    }
}
