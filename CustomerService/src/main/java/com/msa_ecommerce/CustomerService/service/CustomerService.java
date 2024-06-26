package com.msa_ecommerce.CustomerService.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.msa_ecommerce.CustomerService.proto.CustomerInformation;
import com.msa_ecommerce.CustomerService.proto.CustomerInformationRequest;
import com.msa_ecommerce.CustomerService.proto.CustomerServiceGrpc;
import com.msa_ecommerce.CustomerService.service.handler.CustomerInformationRequestHandler;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class CustomerService extends CustomerServiceGrpc.CustomerServiceImplBase {

    private final CustomerInformationRequestHandler customerInformationRequestHandler;
    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);
    

    public CustomerService(CustomerInformationRequestHandler customerInformationRequestHandler) {
        this.customerInformationRequestHandler = customerInformationRequestHandler;
    }

    @Override
    public void getCustomerInformation(CustomerInformationRequest request,
            StreamObserver<CustomerInformation> responseObserver) {
            CustomerInformation customerInformation = customerInformationRequestHandler.getCustomerInformation(request);

            log.info("Get Customer Information: {}", customerInformation);
            responseObserver.onNext(customerInformation);
            responseObserver.onCompleted();
    }

}
