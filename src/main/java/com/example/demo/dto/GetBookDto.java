package com.example.demo.dto;

/**
 * Dto used to send search data to the backend, currently used in: searchBooksAndReservation in the BookController
 */
public class GetBookDto {
    private String email;

    private String searchTerm;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

}
