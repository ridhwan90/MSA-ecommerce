package com.msa_ecommerce.CustomerService;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.javafaker.Faker;
import com.msa_ecommerce.CustomerService.entity.Address;
import com.msa_ecommerce.CustomerService.entity.Customer;
import com.msa_ecommerce.CustomerService.entity.PaymentCard;
import com.msa_ecommerce.CustomerService.repository.CustomerRepository;

@Configuration
public class DataSeeder {
    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
        return args -> {
            if (customerRepository.count() == 0) {
                Faker faker = new Faker();
                for (int i = 0; i < 10; i++) {
                    Address address = Address.builder().addressLine1(faker.address().streetAddress()).addressLine2(faker.address().streetAddress()).city(faker.address().city()).state(faker.address().state()).country(faker.address().country()).postalCode(faker.address().zipCode()).build();
                    PaymentCard paymentCard = PaymentCard.builder().cardNumber(faker.number().digits(16)).cardHolderName(faker.name().fullName()).expiryDate(faker.date().future(365, TimeUnit.DAYS).toString()).cvv(faker.number().digits(3)).address(address).build();
                    Customer customer = Customer.builder()
                            .name(faker.name().fullName())
                            .email(faker.internet().emailAddress())
                            .phoneNumber(faker.phoneNumber().phoneNumber())
                            .isActive(true)
                            .isAdmin(false)
                            .password(faker.internet().password())
                            .profilePicture("https://avatars.dicebear.com/api/avataaars/" + i + ".svg?background=%23fff&radius=50")
                            .address(address)
                            .paymentCard(List.of(paymentCard))
                            .build();

                    customerRepository.save(customer);
                }
            }
        };
    }
}
