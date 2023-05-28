package com.fowox.restfruitshop.bootstrap;

import com.fowox.restfruitshop.domain.Category;
import com.fowox.restfruitshop.domain.Customer;
import com.fowox.restfruitshop.domain.Vendor;
import com.fowox.restfruitshop.repository.CategoryRepository;
import com.fowox.restfruitshop.repository.CustomerRepository;
import com.fowox.restfruitshop.repository.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        categoryBootstrap();
        customerBootstrap();
        vendorBootstrap();
    }

    private void customerBootstrap() {
        Customer joe = new Customer();
        joe.setFirstName("Joe");
        joe.setLastName("Newman");

        Customer michael = new Customer();
        michael.setFirstName("Michael");
        michael.setLastName("Lachappele");

        Customer david = new Customer();
        david.setFirstName("David");
        david.setLastName("Winter");

        Customer anne = new Customer();
        anne.setFirstName("Anne");
        anne.setLastName("Hine");

        Customer alice = new Customer();
        alice.setFirstName("Alice");
        alice.setLastName("Eastman");

        customerRepository.save(joe);
        customerRepository.save(michael);
        customerRepository.save(david);
        customerRepository.save(anne);
        customerRepository.save(alice);

    }

    private void categoryBootstrap() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);
    }

    private void vendorBootstrap() {
        Vendor vendor1 = new Vendor();
        vendor1.setName("Vendor 1");
        vendorRepository.save(vendor1);

        Vendor vendor2 = new Vendor();
        vendor2.setName("Vendor 2");
        vendorRepository.save(vendor2);

    }
}
