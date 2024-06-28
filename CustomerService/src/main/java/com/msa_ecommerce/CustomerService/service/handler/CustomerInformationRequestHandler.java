package com.msa_ecommerce.CustomerService.service.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.msa_ecommerce.CustomerService.entity.PaymentCard;
import com.msa_ecommerce.CustomerService.exceptions.UnknownUserException;
import com.msa_ecommerce.CustomerService.proto.CreateCustomerRequest;
import com.msa_ecommerce.CustomerService.proto.CreateCustomerResponse;
import com.msa_ecommerce.CustomerService.proto.CustomerInformation;
import com.msa_ecommerce.CustomerService.proto.CustomerInformationRequest;
import com.msa_ecommerce.CustomerService.proto.DeleteCustomerRequest;
import com.msa_ecommerce.CustomerService.proto.DeleteCustomerResponse;
import com.msa_ecommerce.CustomerService.proto.UpdateCustomerCardRequest;
import com.msa_ecommerce.CustomerService.proto.UpdateCustomerCardResponse;
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
                .setProfilePicture(user.getProfilePicture() != null ? user.getProfilePicture() : "")
                .setAddress(EntityMessageMapper.toCustomerAddress(user.getAddress()))
                .addAllPaymentCard(user.getPaymentCard() == null ? null : EntityMessageMapper.toCustomerCard(user.getPaymentCard()))
                .build();
    }

    public DeleteCustomerResponse deleteCustomer(DeleteCustomerRequest request) {
        var user = this.customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new UnknownUserException(request.getCustomerId()));

        this.customerRepository.delete(user);
        return DeleteCustomerResponse.newBuilder().setIsSuccess(true).setMessage("User id: " + request.getCustomerId() + " is deleted").build();
    }

    public CreateCustomerResponse createCustomer(CreateCustomerRequest request) {
        var user = this.customerRepository.save(EntityMessageMapper.toCustomerEntity(request));
        return CreateCustomerResponse.newBuilder().setIsSuccess(true).setCustomerId(user.getUuid()).build();
    }

    public UpdateCustomerCardResponse updateCustomerCard(UpdateCustomerCardRequest request) {
        var user = this.customerRepository.findById(request.getCustomerId());
        if (user.isEmpty()) {
            throw new UnknownUserException(request.getCustomerId());
        }
        List<PaymentCard> paymentCards = new ArrayList<>();
        paymentCards.add(EntityMessageMapper.toPaymentCard(request));
        user.get().setPaymentCard(paymentCards);
        this.customerRepository.save(user.get());
        return UpdateCustomerCardResponse.newBuilder().setIsSuccess(true).setMessage("Payment card for user id: " + request.getCustomerId() + " is updated").build();
    }
}
