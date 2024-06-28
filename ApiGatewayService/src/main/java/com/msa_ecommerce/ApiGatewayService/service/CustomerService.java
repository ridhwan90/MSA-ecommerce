package com.msa_ecommerce.ApiGatewayService.service;

import org.springframework.stereotype.Service;
import com.msa_ecommerce.ApiGatewayService.proto.*;

import net.devh.boot.grpc.client.inject.GrpcClient;

@Service
public class CustomerService {

    @GrpcClient("customer-service")
    private CustomerServiceGrpc.CustomerServiceBlockingStub customerClient;

    public CustomerInformation getCustomerInformation(String customerId) {
        CustomerInformationRequest request = CustomerInformationRequest.newBuilder().setCustomerId(customerId).build();
        return this.customerClient.getCustomerInformation(request);
    }

    public DeleteCustomerResponse deleteCustomer(String customerId) {
        DeleteCustomerRequest request = DeleteCustomerRequest.newBuilder().setCustomerId(customerId).build();
        return this.customerClient.deleteCustomer(request);
    }

    public CreateCustomerResponse createCustomer(CreateCustomerRequest request) {
        return this.customerClient.createCustomer(request);
    }

    public UpdateCustomerCardResponse updateCustomerCard(UpdateCustomerCardRequest request) {
        return this.customerClient.updateCustomerCard(request);
    }

}
