package com.mfn.onlineshop.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.mfn.onlineshop.dao.UserDAO;
import com.mfn.onlineshop.entity.Address;
import com.mfn.onlineshop.entity.Cart;
import com.mfn.onlineshop.entity.User;
import com.mfn.onlineshop.model.RegisterModel;

@Component
public class RegisterHandler {

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public RegisterModel init() {
		return new RegisterModel();
	}

	public void addUser(RegisterModel registerModel, User user) {
		registerModel.setUser(user);
	}

	public void addAddress(RegisterModel registerModel, Address address) {
		registerModel.setAddress(address);
	}

	public String saveAll(RegisterModel model) {

		User user = model.getUser();

		if (user.getRole().equals("USER")) {
			Cart cart = new Cart();
			cart.setUser(user);
			user.setCart(cart);
		}
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		userDAO.addUser(user);

		Address address = model.getAddress();
		address.setUser(user);
		address.setBilling(true);
		userDAO.addAddress(address);

		return "success";
	}

	public String validateUser(User user, MessageContext error) {
		String result = "success";

		if (!(user.getPassword().equals(user.getConfirmPassword()))) {
			error.addMessage(new MessageBuilder().error().source("confirmPassword")
					.defaultText("Password does not match!!").build());

			result = "failure";
		}

		if (userDAO.getByEmail(user.getEmail()) != null) {
			error.addMessage(
					new MessageBuilder().error().source("email").defaultText("Email is already exists!!").build());

			result = "failure";
		}

		return result;
	}
}
