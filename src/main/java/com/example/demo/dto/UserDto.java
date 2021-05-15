package com.example.demo.dto;

import com.example.demo.entity.User;

public class UserDto extends AbstractDTO<UserDto> {
	private String username;
	private String email;
	private String firstName;
	private String lastName;
	private String phone;
	private String city;
	private String district;
	private String ward;

	public UserDto() {
		super();
	}

	public UserDto(User user) {
		super();
		this.setId(user.getId());
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.firstName = user.getFullname().getFirstName();
		this.lastName = user.getFullname().getLastName();
		this.phone = user.getPhone();
		this.city = user.getAddress().getCity();
		this.district = user.getAddress().getDistrict();
		this.ward = user.getAddress().getWard();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}
}
