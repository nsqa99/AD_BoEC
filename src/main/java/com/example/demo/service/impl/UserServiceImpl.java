package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.common.Erole;
import com.example.demo.dto.OrderDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.Address;
import com.example.demo.entity.FullName;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.OrderService;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private RoleRepository roleRepo;

	@Override
	public UserDto getCurrentUser(Long id) {
		User user = userRepository.getOne(id);
		UserDto dto = new UserDto(user);
		return dto;
	}

	@Override
	public List<OrderDto> getOrdersByUserId(long id, Pageable pageable) {
		List<OrderDto> result = orderService.findAllByUserId(id, pageable);
		return result;
	}

	@Override
	public UserDto updateOne(UserDto dto) {
		User user = userRepository.getOne(dto.getId());
		Address address = user.getAddress();
		address.setCity(dto.getCity());
		address.setDistrict(dto.getDistrict());
		address.setWard(dto.getWard());

		FullName fullname = user.getFullname();
		fullname.setFirstName(dto.getFirstName());
		fullname.setLastName(dto.getLastName());
		
		List<Erole> roles = new ArrayList<Erole>();
		List<Integer> roleCodes = dto.getRoleCodes();

		for (int i = 0; i < roleCodes.size(); i++) {
			switch (roleCodes.get(i)) {
			case 1:
				roles.add(Erole.ROLE_ADMIN);
				break;
			case 2:
				roles.add(Erole.ROLE_STAFF_BUSINESS);
				break;
			case 3:
				roles.add(Erole.ROLE_STAFF_SALE);
				break;
			case 4:
				roles.add(Erole.ROLE_STAFF_STOCK);
				break;
			case 5:
				roles.add(Erole.ROLE_USER);
				break;
			default:
				roles.add(Erole.ROLE_USER);
				break;
			}

		}
		Set<Role> uRoles = user.getRoles();
		uRoles.clear();
		roles.stream().forEach(r -> {
			Role role = roleRepo.findByName(r).orElse(null);
			if (role == null) return;
			uRoles.add(role);
		});
		user.setRoles(uRoles);
		user.setAddress(address);
		user.setFullname(fullname);
		user.setEmail(dto.getEmail());
		user.setPhone(dto.getPhone());
		user.setUsername(dto.getUsername());
		User newUser = userRepository.save(user);
		return new UserDto(newUser);
	}

	@Override
	public List<UserDto> findAll(Pageable pageable) {
		return userRepository
				.findAll(pageable)
				.getContent()
				.stream()
				.map(u -> new UserDto(u))
				.collect(Collectors.toList());
	}

	@Override
	public UserDto findById(long id) {
		User user = userRepository.findById(id).orElse(null);
		if (user == null) return null;
		return new UserDto(user);
	}

	@Override
	public long getTotal() {
		return userRepository.count();
	}

}
