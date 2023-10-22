package com.aninfo.model;

//import javax.persistence.*;
//
//@Entity
//public class TransactionType {
//
//    @Id
//    private Long idm;
//
//    private String description;
//
//    public TransactionType() {
//
//    }
//
//    public Long getIdm() {
//        return idm;
//    }
//
//    public void setIdm(Long idm) {
//        this.idm = idm;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    @Override
//    public String toString() {
//        return "TransactionType{" +
//                "idm=" + idm +
//                ", description='" + description + '\'' +
//                '}';
//    }
//
//    public static final long DEPOSIT_IDM = 1;
//    public static final long WITHDRAW_IDM = 2;
//
//}

public enum TransactionType {
    DEPOSIT("Deposit"),
    WITHDRAW("Withdraw");

    private final String value;

    TransactionType(String value) {
        this.value = value;
    }

    public String get() {
        return value;
    }

}
