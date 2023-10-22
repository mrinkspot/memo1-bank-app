package com.aninfo.model;

public enum BankAccountDepositPromo {
    MIN_REQUIRED_SUM(2000.0),
    EXTRA_PERCENTAGE(0.1),
    EXTRA_MAX_AMOUNT(500.0);

    private Double value;

    BankAccountDepositPromo(Double value) {
        this.value = value;
    }

    public Double get() {
        return value;
    }

}
