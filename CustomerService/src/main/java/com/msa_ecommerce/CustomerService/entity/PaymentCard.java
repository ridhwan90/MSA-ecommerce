package com.msa_ecommerce.CustomerService.entity;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class PaymentCard {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;
    private String cardNumber;
    private String cardHolderName;
    private String expiryDate;
    private String cvv;
    @Column(nullable = false)
    private final Date createdAt = new Date();
    private Date updatedAt;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_uuid")
    private Address address;

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = new Date();
    }
}
