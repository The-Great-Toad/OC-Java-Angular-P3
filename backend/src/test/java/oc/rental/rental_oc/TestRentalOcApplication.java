package oc.rental.rental_oc;

import org.springframework.boot.SpringApplication;

public class TestRentalOcApplication {

	public static void main(String[] args) {
		SpringApplication.from(RentalOcApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
