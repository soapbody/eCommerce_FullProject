package com.Mateus_Ulrich.eCommerce_FullProject.models;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "individual")
@PrimaryKeyJoinColumn(name = "id")
public class Individual extends Person{
    @Column(nullable = false)
    private String cpf;
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

}
