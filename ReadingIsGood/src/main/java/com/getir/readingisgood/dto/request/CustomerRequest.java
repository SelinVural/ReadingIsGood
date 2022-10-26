package com.getir.readingisgood.dto.request;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.getir.readingisgood.entity.Address;

public class CustomerRequest {
	
	private Long id;
	@NotBlank
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private List<Address> addresses;
	public CustomerRequest() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public List<Address> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	@Override
	public String toString() {
		return "CustomerRequest [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", phone=" + phone + ", addresses=" + addresses + "]";
	}

}
