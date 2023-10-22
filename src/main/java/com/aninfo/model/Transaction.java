package com.aninfo.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Account account;

    private Double amount;

    private LocalDateTime date;

//    @OneToOne
//    private TransactionType transactionType;

    private String type;

    public Transaction() {
    }

//    public Transaction(Account account, Double amount, TransactionType transactionType) {
//        this.account = account;
//        this.amount = amount;
//        this.transactionType = transactionType;
//        this.date = LocalDateTime.now();
//    }

    public Transaction(Account account, Double amount, String type) {
        this.account = account;
        this.amount = amount;
        this.type = type;
        this.date = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

//    public TransactionType getTransactionType() {
//        return transactionType;
//    }
    public String getType() {
        return type;
    }

//    public void setTransactionType(TransactionType transactionType) {
//        this.transactionType = transactionType;
//    }
    public void setType(String transactionType) {
        this.type = transactionType;
    }

}
