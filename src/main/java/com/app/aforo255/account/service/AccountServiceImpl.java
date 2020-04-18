package com.app.aforo255.account.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.aforo255.account.model.entity.Account;
import com.app.aforo255.account.model.repository.AccountRepository;

@Service
public class AccountServiceImpl implements IAccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public List<Account> findAll() {
		return (List<Account>) accountRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Account findById(Integer id) {
		return accountRepository.findById(id).orElse(null);
	}

	@Override
	public Account save(Account account) {
		return accountRepository.save(account);
	}
}
