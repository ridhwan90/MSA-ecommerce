package com.msa_ecommerce.CustomerService.service.handler;

import org.springframework.stereotype.Service;

import com.msa_ecommerce.CustomerService.exceptions.UnknownUserException;
import com.msa_ecommerce.CustomerService.proto.CustomerInformation;
import com.msa_ecommerce.CustomerService.proto.CustomerInformationRequest;
import com.msa_ecommerce.CustomerService.repository.CustomerRepository;
import com.msa_ecommerce.CustomerService.util.EntityMessageMapper;

import jakarta.transaction.Transactional;

@Service
public class CustomerInformationRequestHandler {

    private final CustomerRepository customerRepository;

    public CustomerInformationRequestHandler(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public CustomerInformation getCustomerInformation(CustomerInformationRequest request) {
        var user = this.customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new UnknownUserException(request.getCustomerId()));

        return CustomerInformation
                .newBuilder()
                .setCustomerId(user.getUuid())
                .setName(user.getName())
                .setEmail(user.getEmail())
                .setPhoneNumber(user.getPhoneNumber())
                .setIsActive(user.getIsActive())
                .setIsAdmin(user.getIsAdmin())
                .setProfilePicture(user.getProfilePicture())
                .setAddress(EntityMessageMapper.toCustomerAddress(user.getAddress()))
                .addAllPaymentCard(EntityMessageMapper.toCustomerCard(user.getPaymentCard()))
                .build();
    }
}
