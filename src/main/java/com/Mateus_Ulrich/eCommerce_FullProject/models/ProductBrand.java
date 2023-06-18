package com.Mateus_Ulrich.eCommerce_FullProject.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "product_brand")
@SequenceGenerator(name = "seq_product_brand", sequenceName = "seq_product_brand", allocationSize = 1, initialValue = 1)

public class ProductBrand{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_product_brand")
    private Long id;
    @Column(name = "description_name", nullable = false)
    private String descriptionName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameDescription() {
        return descriptionName;
    }

    public void setNameDescription(String nameDescription) {
        this.descriptionName = nameDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductBrand that = (ProductBrand) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
