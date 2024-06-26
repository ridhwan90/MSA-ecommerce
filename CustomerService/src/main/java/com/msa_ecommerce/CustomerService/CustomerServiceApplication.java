package com.msa_ecommerce.CustomerService;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.msa_ecommerce.CustomerService.entity.Address;
import com.msa_ecommerce.CustomerService.entity.Customer;
import com.msa_ecommerce.CustomerService.entity.PaymentCard;
import com.msa_ecommerce.CustomerService.repository.CustomerRepository;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// 				.isAdmin(false)h
	// 				.phoneNumber("0123456789")
	// 				.profilePicture("profilePicture")
	// 				.address(List.of(Address.builder().addressLine1("address1").addressLine2(null).city("city").state("state").country("country").postalCode("postalCode").type("customer").build()))
	// 				.paymentCard(List.of(PaymentCard.builder().cardNumber("cardNumber").cardHolderName("cardHolderName").expiryDate("expiryDate").cvv("cvv").type("type").build()))
	// 				.build();
	// 		customerRepository.save(customer);

	// 		// sleep fo 5 seconds
	// 		try {
	// 			Thread.sleep(5000);
	// 		} catch (InterruptedException e) {
	// 			e.printStackTrace();
	// 		}

	// 		var updateCustomer = customerRepository.findById(customer.getUuid());
	// 		updateCustomer.get().setProfilePicture("newProfilePicture");
	// 		customerRepository.save(updateCustomer.get());
	// 		System.out.println(updateCustomer.get().getProfilePicture());
	// 	};
	// }
}