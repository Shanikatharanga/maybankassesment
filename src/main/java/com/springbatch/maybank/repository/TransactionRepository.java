package com.springbatch.maybank.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springbatch.maybank.entity.TransactionData;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionData, Long>{
	
	Page<TransactionData> findByCustomerIdOrAccountNumberOrDescription(
            String customerId, String accountNumber, String description, Pageable pageable);

}
