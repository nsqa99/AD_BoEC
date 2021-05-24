package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.Address;
import com.example.demo.entity.FullName;
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
	private List<Integer> roleCodes = new ArrayList<>();

	public UserDto() {
		super();
	}

	public UserDto(User user) {
		super();
		Address address = user.getAddress();
		FullName fullname = user.getFullname();
		this.setId(user.getId());
		this.username = user.getUsername();
		this.email = user.getEmail();
		if (fullname != null) {
			this.firstName = fullname.getFirstName();
			this.lastName = fullname.getLastName();
		}
		
		this.phone = user.getPhone();
		if (address != null) {
			this.city = address.getCity();
			this.district = address.getDistrict();
			this.ward = address.getWard();
		}
		if (user.getRoles() != null) {
			user.getRoles().stream().forEach(role -> {
				roleCodes.add(role.getId().intValue());
			});
		}
		
		
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

	public List<Integer> getRoleCodes() {
		return roleCodes;
	}

	public void setRoleCodes(List<Integer> roleCodes) {
		this.roleCodes = roleCodes;
	}

	
	

	
	
	
}
