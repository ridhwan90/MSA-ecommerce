package com.msa_ecommerce.CustomerService.service.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.msa_ecommerce.CustomerService.entity.Address;
import com.msa_ecommerce.CustomerService.entity.Customer;
import com.msa_ecommerce.CustomerService.entity.PaymentCard;
import com.msa_ecommerce.CustomerService.exceptions.UnknownUserException;
import com.msa_ecommerce.CustomerService.proto.CustomerInformation;
import com.msa_ecommerce.CustomerService.proto.CustomerInformationRequest;
import com.msa_ecommerce.CustomerService.repository.CustomerRepository;

public class CustomerInformationRequestHandlerTest {
    @Test
void testGetCustomerInformation() {
    // Create a mock instance of CustomerRepository
    CustomerRepository customerRepository = Mockito.mock(CustomerRepository.class);
    Address address = Address.builder()
            .addressLine1("123 Main St")
            .addressLine2("Apt 1")
            .city("New York")
            .state("NY")
            .country("USA")
            .postalCode("10001")
            .build();
    Mockito.when(customerRepository.findById("123"))
            .thenReturn(Optional.of(Customer.builder()
                    .uuid("123")
                    .email("John.Doe@example.com")
                    .name("John Doe")
                    .phoneNumber("1234567890")
                    .isActive(true)
                    .isAdmin(false)
                    .password("password")
                    .profilePicture("https://example.com/profile.jpg")
                    .address(address)
                    .paymentCard(List.of(PaymentCard.builder()
                            .cardNumber("1234567890123456")
                            .cardHolderName("John Doe")
                            .expiryDate("12/2023")
                            .cvv("123")
                            .address(address)
                            .build()))
                    .build()));

    CustomerInformationRequestHandler handler = new CustomerInformationRequestHandler(customerRepository);
    CustomerInformationRequest request = CustomerInformationRequest.newBuilder().setCustomerId("123").build();
    CustomerInformation result = handler.getCustomerInformation(request);
    assertEquals("123", result.getCustomerId());
}

    @Test
    void testGetCustomerInformationWithUnknownUser() {
        CustomerRepository customerRepository = Mockito.mock(CustomerRepository.class);

        Mockito.when(customerRepository.findById("123")).thenThrow(new UnknownUserException("123"));
                

        CustomerInformationRequestHandler handler = new CustomerInformationRequestHandler(customerRepository);
        CustomerInformationRequest request = CustomerInformationRequest.newBuilder().setCustomerId("123").build();

        try {
            handler.getCustomerInformation(request);
            fail("Expected UnknownUserException to be thrown");
        } catch (UnknownUserException e) {
            assertEquals("User [id: 123] is not found", e.getMessage());
        }
    }
}
