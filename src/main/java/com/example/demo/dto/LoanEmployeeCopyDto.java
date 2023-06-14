package com.example.demo.dto;

import java.time.LocalDate;

import com.example.demo.entity.Book;
import com.example.demo.entity.Copy;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Loan;

/**
 * A Data Transfer Object that that is used to request all of the relevant data this
 * is connected to a loan. Specifically: Loan, Employee, Book and copy and all it's relevant
 * fields.
 * 
 */
public class LoanEmployeeCopyDto {

	// Loan
	long loanId;
	LocalDate loanDate;
	LocalDate returnDate;
	
	// Employee
	long employeeId;
	String employeeFirstName;
	String employeeLastName;
	
	// Book
	long bookId;
	String bookTitle;
	String isbn;
	
	// Copy
	long copyId;

	public LoanEmployeeCopyDto(Loan loan) {

		// Loan
		this.loanId = loan.getId();
		this.loanDate = loan.getLoanDate();
		this.returnDate = loan.getReturnDate();
		
		// Employee
		this.employeeId = loan.getEmployee().getEmployeeId();
		this.employeeFirstName = loan.getEmployee().getFirstName();
		this.employeeLastName = loan.getEmployee().getLastName();
		
		// Book
		this.bookId = loan.getCopy().getBook().getId();
		this.bookTitle = loan.getCopy().getBook().getTitle();
		this.isbn = loan.getCopy().getBook().getIsbn();
		
		// Copy
		this.copyId = loan.getCopy().getId();
	}
	
	public Loan getLoan(Loan loan) {
		loan.setId(this.loanId);
		loan.setLoanDate(this.loanDate);
		loan.setLoanDate(this.loanDate);
		return loan;
	}
	
	public Employee getEmployee(Employee employee) {
		employee.setEmployeeId(this.employeeId);
		employee.setFirstName(this.employeeFirstName);
		employee.setLastName(this.employeeLastName);
		return employee;
	}
	
	public Book getBook(Book book) {
		book.setId(this.bookId);
		book.setTitle(this.bookTitle);
		book.setIsbn(this.isbn);
		return book;
	}
	
	public Copy getCopy(Copy copy) {
		copy.setId(this.copyId);
		return copy;
	}

	public long getLoanId() {
		return loanId;
	}

	public void setLoanId(long loanId) {
		this.loanId = loanId;
	}

	public LocalDate getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(LocalDate loanDate) {
		this.loanDate = loanDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeFirstName() {
		return employeeFirstName;
	}

	public void setEmployeeFirstName(String employeeFirstName) {
		this.employeeFirstName = employeeFirstName;
	}

	public String getEmployeeLastName() {
		return employeeLastName;
	}

	public void setEmployeeLastName(String employeeLastName) {
		this.employeeLastName = employeeLastName;
	}

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public long getCopyId() {
		return copyId;
	}

	public void setCopyId(long copyId) {
		this.copyId = copyId;
	}

}
