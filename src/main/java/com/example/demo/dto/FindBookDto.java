package com.example.demo.dto;

/**
 * Dto used to send all books and respective reservations of a specific user to the frontend.
 */
public class FindBookDto {
    private long id;

    private String title;

    private String writer;

    private String isbn;

    private long reservationId;
    
    private String photo;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public long getReservationId() {
        return reservationId;
    }

    public void setReservationId(long reservationId) {
        this.reservationId = reservationId;
    }

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public FindBookDto(long id, String title, String writer, String isbn, long reservationId, String photo) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.isbn = isbn;
        this.reservationId = reservationId;
        this.photo = photo;
    }

    public FindBookDto(){}
}
