package com.msa_ecommerce.CustomerService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msa_ecommerce.CustomerService.entity.PaymentCard;

public interface PaymentCardRepository extends JpaRepository<PaymentCard, String> {

}
