package com.example.demo.dto;

public class SaveKeywordDto {

    private String keyword;

    private Long bookId;

    public SaveKeywordDto() {
    }

    public SaveKeywordDto(String keyword, Long bookId) {
        this.keyword = keyword;
        this.bookId = bookId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}
