package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CartDto;
import com.example.demo.dto.OrderDto;
import com.example.demo.dto.PaymentDto;
import com.example.demo.dto.ShipmentDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.Cart;
import com.example.demo.entity.Item;
import com.example.demo.entity.ItemCart;
import com.example.demo.entity.Order;
import com.example.demo.entity.Payment;
import com.example.demo.entity.Shipment;
import com.example.demo.entity.User;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ShipmentRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository ordRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ItemRepository itemRepo;
	@Autowired
	private ShipmentRepository shipRepo;

	@Override
	public List<OrderDto> findAll(Pageable pageable) {
		return null;
	}

	@Override
	public OrderDto getById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDto getByUserId(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(value = TxType.REQUIRES_NEW, rollbackOn = Exception.class)
	public OrderDto insert(OrderDto orderDto) {
		UserDto uDto = orderDto.getUser();

		CartDto cartDto = orderDto.getCart();
		PaymentDto payDto = orderDto.getPayment();
		ShipmentDto shipDto = payDto.getShipment();
		User user = userRepo.findById(uDto.getId()).orElse(null);
		if (user == null)
			return null;
		Cart cart = new Cart();
		cart.setItems(cartDto.getItems().stream().map(dto -> {
			ItemCart itemCart = new ItemCart();
			itemCart.setAmount(dto.getAmount());
			Item item = itemRepo.findById(dto.getItem().getId()).orElse(null);
			itemCart.setItem(item);
			return itemCart;
		}).collect(Collectors.toList()));
		cart.setUser(user);

		Payment pay = new Payment();
		pay.setMethod(payDto.getMethod());
		pay.setTotal(payDto.getTotal());

		if (payDto.getShipment().getId() != null) {
			pay.setShipment(shipRepo.findById(payDto.getShipment().getId()).orElse(new Shipment()));
		} else {
			Shipment ship = new Shipment();
			ship.setPayment(pay);
			ship.setShipCompany(shipDto.getShipCompany());
			ship.setShipFee(shipDto.getShipFee());
			ship.setShipType(shipDto.getShipType());
			ship.setShipAddress(shipDto.getShipAddress());
			pay.setShipment(ship);
		}

		Order order = new Order(cart, pay, user);
		cart.setOrder(order);
		cart.getItems().stream().forEach(i -> {
			i.setCart(cart);
		});
		pay.setOrder(order);
		Order newOrder = ordRepo.save(order);
		if (newOrder != null)
			return new OrderDto(newOrder);
		return null;
	}

	@Override
	public OrderDto update(long id) {
		return null;
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub

	}

}
