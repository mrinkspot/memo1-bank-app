package com.aninfo.service;

import com.aninfo.exceptions.DepositNegativeSumException;
import com.aninfo.exceptions.DepositZeroSumException;
import com.aninfo.exceptions.InsufficientFundsException;
import com.aninfo.model.Account;
import com.aninfo.model.Transaction;
import com.aninfo.model.TransactionType;
import com.aninfo.repository.TransactionRepository;
import com.aninfo.repository.TransactionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private TransactionTypeRepository transactionTypeRepository;
    @Autowired
    private AccountService accountService;

    public Collection<Transaction> getTransactionsByAccountCbu(Long cbu) { return transactionRepository.findAllByAccount_Cbu(cbu); }

    public Transaction createDeposit(Account account, Double sum) {

        if (sum == 0) {
            throw new DepositZeroSumException("Sum cannot be zero");
        }

        if (sum < 0) {
            throw new DepositNegativeSumException("Cannot deposit negative sums");
        }

        TransactionType deposit = transactionTypeRepository.findById(TransactionType.DEPOSIT_IDM).get();

        Transaction transaction = new Transaction(account, sum, deposit);

        return transactionRepository.save(transaction);
    }

    public Transaction createWithdraw(Account account, Double sum) {

        if (account.getBalance() < sum) {
            throw new InsufficientFundsException("Insufficient funds");
        }

        TransactionType withdraw = transactionTypeRepository.findById(TransactionType.WITHDRAW_IDM).get();
        Transaction transaction = new Transaction(account, sum, withdraw);

        return transactionRepository.save(transaction);
    }
}
