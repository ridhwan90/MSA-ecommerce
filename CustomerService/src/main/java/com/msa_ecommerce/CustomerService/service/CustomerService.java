package com.msa_ecommerce.CustomerService.service;

import com.msa_ecommerce.CustomerService.proto.CreateCustomerRequest;
import com.msa_ecommerce.CustomerService.proto.CreateCustomerResponse;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

import com.msa_ecommerce.CustomerService.proto.CustomerInformation;
import com.msa_ecommerce.CustomerService.proto.CustomerInformationRequest;
import com.msa_ecommerce.CustomerService.proto.CustomerServiceGrpc;
import com.msa_ecommerce.CustomerService.proto.DeleteCustomerRequest;
import com.msa_ecommerce.CustomerService.proto.DeleteCustomerResponse;
import com.msa_ecommerce.CustomerService.proto.UpdateCustomerCardRequest;
import com.msa_ecommerce.CustomerService.proto.UpdateCustomerCardResponse;
import com.msa_ecommerce.CustomerService.service.handler.CustomerInformationRequestHandler;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class CustomerService extends CustomerServiceGrpc.CustomerServiceImplBase {

    private final CustomerInformationRequestHandler customerInformationRequestHandler;
    // private static final Logger log = LoggerFactory.getLogger(CustomerService.class);
    

    public CustomerService(CustomerInformationRequestHandler customerInformationRequestHandler) {
        this.customerInformationRequestHandler = customerInformationRequestHandler;
    }

    @Override
    public void getCustomerInformation(CustomerInformationRequest request,
            StreamObserver<CustomerInformation> responseObserver) {
            CustomerInformation customerInformation = customerInformationRequestHandler.getCustomerInformation(request);
            responseObserver.onNext(customerInformation);
            responseObserver.onCompleted();
    }

    @Override
    public void deleteCustomer(DeleteCustomerRequest request, StreamObserver<DeleteCustomerResponse> responseObserver) {
        DeleteCustomerResponse deleteCustomerResponse = customerInformationRequestHandler.deleteCustomer(request);
        responseObserver.onNext(deleteCustomerResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void createCustomer(CreateCustomerRequest request, StreamObserver<CreateCustomerResponse> responseObserver) {
        CreateCustomerResponse createCustomerResponse = customerInformationRequestHandler.createCustomer(request);
        responseObserver.onNext(createCustomerResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void updateCustomerCard(UpdateCustomerCardRequest request, StreamObserver<UpdateCustomerCardResponse> responseObserver) {
        UpdateCustomerCardResponse updateCustomerCardResponse = customerInformationRequestHandler.updateCustomerCard(request);
        responseObserver.onNext(updateCustomerCardResponse);
        responseObserver.onCompleted();
    }

}
