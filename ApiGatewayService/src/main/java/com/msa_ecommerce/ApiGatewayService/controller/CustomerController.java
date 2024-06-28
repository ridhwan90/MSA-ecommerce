package com.msa_ecommerce.ApiGatewayService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msa_ecommerce.ApiGatewayService.service.CustomerService;
import com.msa_ecommerce.ApiGatewayService.util.UpdateCustomerCardRequestDTO;
import com.msa_ecommerce.ApiGatewayService.proto.*;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("users")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerInformation getCustomerInformation(@PathVariable String userId) {
        return this.customerService.getCustomerInformation(userId);
    }

    @DeleteMapping(value = "{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public DeleteCustomerResponse deleteCustomer(@PathVariable String userId) {
        return this.customerService.deleteCustomer(userId);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CreateCustomerResponse createCustomer(@RequestBody CreateCustomerRequest request) {
        return this.customerService.createCustomer(request);
    }

    @PostMapping(value = "{userId}/cards", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UpdateCustomerCardResponse updateCustomerCard(@PathVariable String userId, @RequestBody UpdateCustomerCardRequestDTO request) {
        UpdateCustomerCardRequest.Builder builder = UpdateCustomerCardRequest.newBuilder();
        builder.setCustomerId(userId);
        builder.setCardNumber(request.getCardNumber());
        builder.setCardHolderName(request.getCardHolderName());
        builder.setExpiryDate(request.getExpiryDate());
        builder.setAddress((CustomerAddress.newBuilder()
                .setAddressLine1(request.getAddress().getAddressLine1())
                .setAddressLine2(request.getAddress().getAddressLine2())
                .setCity(request.getAddress().getCity())
                .setCountry(request.getAddress().getCountry())
                .setState(request.getAddress().getState())
                .setPostalCode(request.getAddress().getPostalCode())
                .build()));
        UpdateCustomerCardRequest grpcRequest = builder.build();
        return this.customerService.updateCustomerCard(grpcRequest);
    }
    
    
}
