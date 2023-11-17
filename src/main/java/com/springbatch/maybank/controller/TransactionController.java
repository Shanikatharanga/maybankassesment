package com.springbatch.maybank.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springbatch.maybank.entity.TransactionData;
import com.springbatch.maybank.exception.ConcurrentUpdateException;
import com.springbatch.maybank.service.TransactionService;

@RestController
@RequestMapping("/api/v1")
public class TransactionController {

    @Autowired
    private TransactionService service;

    @GetMapping("/transactions")
    public ResponseEntity<Page<TransactionData>> searchTransaction(
            @RequestParam(required = false) String customerId,
            @RequestParam(required = false) String accountNumber,
            @RequestParam(required = false) String description,
            Pageable pageable) {
        Page<TransactionData> records = service.searchTransactions(customerId, accountNumber, description, pageable);
        return ResponseEntity.ok(records);
    }

    @PutMapping("/transactions/{id}")
    public ResponseEntity<TransactionData> updateDescription(
            @PathVariable Long id,
            @RequestParam("description") String newDescription) {
    	try {
    		TransactionData updatedTransaction = service.updateDescription(id, newDescription);
            return ResponseEntity.ok(updatedTransaction);
        } catch (ConcurrentUpdateException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }
}
