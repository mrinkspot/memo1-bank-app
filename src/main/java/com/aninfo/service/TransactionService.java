package com.aninfo.service;

import com.aninfo.exceptions.DepositNegativeSumException;
import com.aninfo.exceptions.DepositZeroSumException;
import com.aninfo.exceptions.InsufficientFundsException;
import com.aninfo.model.Account;
import com.aninfo.model.BankAccountDepositPromo;
import com.aninfo.model.Transaction;
import com.aninfo.model.TransactionType;
import com.aninfo.repository.TransactionRepository;
//import com.aninfo.repository.TransactionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
//    @Autowired
//    private TransactionTypeRepository transactionTypeRepository;
    @Autowired
    private AccountService accountService;

    public Collection<Transaction> findTransactionsByAccountCbu(Long cbu) { return transactionRepository.findAllByAccount_Cbu(cbu); }

    public Transaction createDeposit(Account account, Double sum) {

        if (sum == 0) {
            throw new DepositZeroSumException("Sum cannot be zero");
        }

        if (sum < 0) {
            throw new DepositNegativeSumException("Cannot deposit negative sums");
        }

        if (sum >= BankAccountDepositPromo.MIN_REQUIRED_SUM.get()) {
            double extra = sum * BankAccountDepositPromo.EXTRA_PERCENTAGE.get();
            if (extra >= BankAccountDepositPromo.EXTRA_MAX_AMOUNT.get()) sum += BankAccountDepositPromo.EXTRA_MAX_AMOUNT.get();
            else sum += extra;
        }

//        TransactionType deposit = transactionTypeRepository.findById(TransactionType.DEPOSIT_IDM).get();

//        Transaction transaction = new Transaction(account, sum, deposit);

        Transaction transaction = new Transaction(account, sum, TransactionType.DEPOSIT.get());

        return transactionRepository.save(transaction);
    }

    public Optional<Transaction> getById(Long id) { return transactionRepository.findById(id); }

    public Transaction createWithdraw(Account account, Double sum) {

        if (account.getBalance() < sum) {
            throw new InsufficientFundsException("Insufficient funds");
        }

//        TransactionType withdraw = transactionTypeRepository.findById(TransactionType.WITHDRAW_IDM).get();
//        Transaction transaction = new Transaction(account, sum, withdraw);

        Transaction transaction = new Transaction(account, sum, TransactionType.WITHDRAW.get());

        return transactionRepository.save(transaction);
    }

    public void deleteById(Long id) {
        transactionRepository.deleteById(id);
    }
}
