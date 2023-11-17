package com.springbatch.maybank.service;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springbatch.maybank.entity.TransactionData;
import com.springbatch.maybank.exception.TransactionNotFoundException;
import com.springbatch.maybank.repository.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository repository;

	public Page<TransactionData> searchTransactions(String customerId, String accountNumber, String description,
			Pageable pageable) {
		if (customerId != null || accountNumber != null || description != null) {
			return repository.findByCustomerIdOrAccountNumberOrDescription(customerId, accountNumber, description,
					pageable);
		}else {
			return repository.findAll(pageable);
		}
	}

	@Transactional
	public TransactionData updateDescription(Long id, String newDescription){
		
			TransactionData transactionData = repository.findById(id).orElseThrow(TransactionNotFoundException::new);

			transactionData.setDescription(newDescription);
			transactionData.setTrxDate(LocalDate.now().toString());
			transactionData.setTrxTime(LocalTime.now().toString());
			
			return repository.save(transactionData);
		
	}
}
