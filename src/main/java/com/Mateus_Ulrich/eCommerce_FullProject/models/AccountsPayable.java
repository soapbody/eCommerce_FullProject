package com.Mateus_Ulrich.eCommerce_FullProject.models;

import com.Mateus_Ulrich.eCommerce_FullProject.enums.AccountReceivableStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "accounts_receivable")
@SequenceGenerator(name = "seq_accounts_receivable", sequenceName = "seq_accounts_receivable", allocationSize = 1, initialValue = 1)

public class AccountsReceivable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_accounts_receivable")
    private Long id;
    private String description;
    @Enumerated(EnumType.STRING)
    private AccountReceivableStatus status;
    @Temporal(TemporalType.DATE)
    private Date dueDate;
    @Temporal(TemporalType.DATE)
    private Date paymentDate;
    private BigDecimal totalValue;
    private BigDecimal discountValue;
    @ManyToOne(targetEntity = Person.class)
    @JoinColumn(name = "person_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "person_fk"))
    private Person person;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AccountReceivableStatus getStatus() {
        return status;
    }

    public void setStatus(AccountReceivableStatus status) {
        this.status = status;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public BigDecimal getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(BigDecimal discountValue) {
        this.discountValue = discountValue;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountsReceivable that = (AccountsReceivable) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
