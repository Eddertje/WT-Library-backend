package com.example.demo.dto;

public class GetBookDto {
    private long id;

    private String searchTerm;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public GetBookDto(long id, String searchTerm) {
        this.id = id;
        this.searchTerm = searchTerm;
    }

    public GetBookDto(){}
}
