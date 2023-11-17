package com.springbatch.maybank.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springbatch.maybank.entity.TransactionData;
import com.springbatch.maybank.repository.TransactionRepository;

@Component
public class DBWriter implements ItemWriter<TransactionData> {

	@Autowired
	private TransactionRepository transactionRepository;
	
	@Override
	public void write(List<? extends TransactionData> transactions) throws Exception {

		System.out.println("Data saved in DB : "+ transactions);
		transactionRepository.saveAll(transactions);
		
	}

}
