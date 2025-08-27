package com.course.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.course.entity.Account;
import com.course.repository.AccountRepository;

@Service
public class AccountTransaction {

	@Autowired
	private AccountRepository accountRepository;
	
	@Transactional
	public Account innerTransferMethod(Account payerAccount, Account payeeAccount, BigDecimal amount) throws Exception {

		payerAccount.setBalance(payerAccount.getBalance().subtract(amount));
		accountRepository.save(payerAccount);

		// 觸發 RuntimeException
		// Integer.parseInt("abc");
		payeeAccount.setBalance(payeeAccount.getBalance().add(amount));
		accountRepository.save(payeeAccount);

		return payerAccount;
	}
}
