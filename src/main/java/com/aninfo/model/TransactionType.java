package com.aninfo.model;

import javax.persistence.*;

@Entity
public class TransactionType {

    @Id
    private Long idm;

    private String description;

    public TransactionType(String description) {
        this.description = description;
    }

    public TransactionType() {

    }

    public Long getIdm() {
        return idm;
    }

    public void setIdm(Long idm) {
        this.idm = idm;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "TransactionType{" +
                "idm=" + idm +
                ", description='" + description + '\'' +
                '}';
    }

    public static final int DEPOSIT_IDM = 1;
    public static final int WITHDRAW_IDM = 2;

}
