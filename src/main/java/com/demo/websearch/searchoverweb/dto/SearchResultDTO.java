package com.demo.websearch.searchoverweb.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import java.net.URI;

/**
 * result dto Object for storing below fields
 *  title, url and authorDisplayName
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResultDTO
{
    @JsonProperty("title")
    private String title;

    @JsonProperty("link")
    private URI url;

    @JsonProperty("owner")
    private JsonNode owner;

    public JsonNode getOwner() {
        return owner;
    }

    public void setOwner(JsonNode owner) {
        this.owner = owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public URI getUrl() {
        return url;
    }

    public void setUrl(URI url) {
        this.url = url;
    }
}
