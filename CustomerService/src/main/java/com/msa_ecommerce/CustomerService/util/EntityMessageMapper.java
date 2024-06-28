package com.msa_ecommerce.CustomerService.util;

import java.util.List;

import com.msa_ecommerce.CustomerService.entity.Address;
import com.msa_ecommerce.CustomerService.entity.Customer;
import com.msa_ecommerce.CustomerService.entity.PaymentCard;
import com.msa_ecommerce.CustomerService.proto.CreateCustomerRequest;
import com.msa_ecommerce.CustomerService.proto.CustomerAddress;
import com.msa_ecommerce.CustomerService.proto.CustomerCard;
import com.msa_ecommerce.CustomerService.proto.UpdateCustomerCardRequest;

public class EntityMessageMapper {

    public static CustomerAddress toCustomerAddress(Address address) {
        return CustomerAddress
                .newBuilder()
                .setAddressLine1(address.getAddressLine1())
                .setAddressLine2(address.getAddressLine2())
                .setCity(address.getCity())
                .setState(address.getState())
                .setCountry(address.getCountry())
                .setPostalCode(address.getPostalCode())
                .build();
    }

    public static List<CustomerCard> toCustomerCard(List<PaymentCard> paymentCards) {
        return paymentCards.stream().map(i -> CustomerCard.newBuilder()
                .setCardNumber(i.getCardNumber())
                .setCardHolderName(i.getCardHolderName())
                .setExpiryDate(i.getExpiryDate())
                .setAddress(toCustomerAddress(i.getAddress()))
                .build()).toList();
    }

    public static Customer toCustomerEntity(CreateCustomerRequest request) {
        Customer newCustomer = new Customer();
        newCustomer.setName(request.getName());
        newCustomer.setEmail(request.getEmail());
        newCustomer.setPhoneNumber(request.getPhoneNumber());
        newCustomer.setIsActive(true);
        newCustomer.setIsAdmin(false);
        newCustomer.setAddress(Address
                .builder()
                .addressLine1(request.getAddress().getAddressLine1())
                .addressLine2(request.getAddress().getAddressLine2())
                .city(request.getAddress().getCity())
                .country(request.getAddress().getCountry())
                .state(request.getAddress().getState())
                .postalCode(request.getAddress().getPostalCode())
                .build());
        return newCustomer;
    }

    public static PaymentCard toPaymentCard(UpdateCustomerCardRequest request) {
        PaymentCard paymentCard = new PaymentCard();
        paymentCard.setCardNumber(request.getCardNumber());
        paymentCard.setCardHolderName(request.getCardHolderName());
        paymentCard.setExpiryDate(request.getExpiryDate());
        paymentCard.setCvv("TBD");
        paymentCard.setAddress(Address
                .builder()
                .addressLine1(request.getAddress().getAddressLine1())
                .addressLine2(request.getAddress().getAddressLine2())
                .city(request.getAddress().getCity())
                .country(request.getAddress().getCountry())
                .state(request.getAddress().getState())
                .postalCode(request.getAddress().getPostalCode())
                .build());
        return paymentCard;
    }

}
