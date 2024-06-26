package com.msa_ecommerce.CustomerService.util;

import java.util.List;

import com.msa_ecommerce.CustomerService.entity.Address;
import com.msa_ecommerce.CustomerService.entity.PaymentCard;
import com.msa_ecommerce.CustomerService.proto.CustomerAddress;
import com.msa_ecommerce.CustomerService.proto.CustomerCard;

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

}
