package com.msa_ecommerce.CustomerService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msa_ecommerce.CustomerService.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {

}
