package com.sra.emp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.sra.emp.model.User;

@Service
public class UserSarvice {
	private List<User> store = new ArrayList<>();
	
	public UserSarvice()
	{
		store.add(new User(UUID.randomUUID().toString(),"Akshay","akshay2001@gmail.com"));
		store.add(new User(UUID.randomUUID().toString(),"Aman","aman2001@gmail.com"));
		store.add(new User(UUID.randomUUID().toString(),"Tanish","tanish2001@gmail.com"));
	}
	
	public List<User> getUsers()
	{
		return this.store;
	}
}
