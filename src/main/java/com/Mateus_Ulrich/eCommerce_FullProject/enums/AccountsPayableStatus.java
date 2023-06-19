package com.Mateus_Ulrich.eCommerce_FullProject.enums;

public enum AccountReceivableStatus {
    CHARGE("Cobrança"),
    OVERDUE("Vencida"),
    OPEN("Aberta"),
    PAID("Quitada");
    private String description;

    AccountReceivableStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
