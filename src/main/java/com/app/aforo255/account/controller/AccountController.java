package com.app.aforo255.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.app.aforo255.account.model.entity.Account;
import com.app.aforo255.account.service.IAccountService;

@RestController
public class AccountController {

	@Autowired
	private IAccountService accountService;

	@GetMapping("/v1/list")
	public List<Account> list() {
		return (List<Account>) accountService.findAll();
	}
	
	@GetMapping("/v1/detail/{id}")
	public Account detail(@PathVariable Integer id) {
		return accountService.findById(id);
	}
}
