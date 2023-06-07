package com.example.demo.dto;

public class LoanReservationDto {

	private SaveLoanDto loanDto;
	
    private long reservationId;

	public SaveLoanDto getLoanDto() {
		return loanDto;
	}

	public void setLoanDto(SaveLoanDto loanDto) {
		this.loanDto = loanDto;
	}

	public long getReservationId() {
		return reservationId;
	}

	public void setReservationId(long reservationId) {
		this.reservationId = reservationId;
	}
    
}
