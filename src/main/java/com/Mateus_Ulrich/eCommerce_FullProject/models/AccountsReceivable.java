package com.Mateus_Ulrich.eCommerce_FullProject.models;

import com.Mateus_Ulrich.eCommerce_FullProject.enums.AddressType;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "residential_address")
@SequenceGenerator(name = "seq_residential_address", sequenceName = "seq_residential_address", allocationSize = 1, initialValue = 1)

public class ResidentialAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_product_brand")
    private Long id;
    @Column(name = "description_name", nullable = false)
    private String street; //rua
    private String city; //cidade
    private String state; //estado ou uf.
    private String neighborhood; //bairro
    private String postalCode; //cep
    private String complement; //complemento
    @ManyToOne(targetEntity = Person.class)
    @JoinColumn(name = "person_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "person_fk"))
    private Person person;
    @Enumerated(EnumType.STRING)
    private AddressType addressType;

    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
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
        ResidentialAddress that = (ResidentialAddress) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
