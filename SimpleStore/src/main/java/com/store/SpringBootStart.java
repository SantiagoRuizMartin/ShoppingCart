package com.store;


import com.google.common.collect.Lists;
import com.store.model.ClientCart;
import com.store.model.Customer;
import com.store.model.CartDetail;
import com.store.model.Product;
import com.store.repository.CartRepository;
import com.store.repository.CustomerRepository;
import com.store.repository.ProductRepository;
import java.util.Arrays;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootStart {

    private static final Logger log = LoggerFactory.getLogger(SpringBootStart.class);

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SpringBootStart.class, args);
    }

    @Bean
    public CommandLineRunner demo(CustomerRepository repository, ProductRepository repositoryP, CartRepository repositoryC) {
        return (args) -> {
            // solo ejecutar una vez 
            /*
            repository.save(new Customer("Santiago Ruiz", "santiago@hotmail.com", "12345", "admin"));
            repository.save(new Customer("Wilson Guevara", "santiago@hotmail.com", "12345", "user"));
            repository.save(new Customer("Jonnatan Garcia", "jhonnatan@yahoo.com", "12345", "user"));
            repository.save(new Customer("Karen Guzman", "karen@hotmail.com", "12345", "user"));
            repository.save(new Customer("Daniel Sosa", "dani@gmail.com", "12345", "user"));

            repositoryP.save(new Product("Xbox 360", 500.00, "Xbox console"));
            repositoryP.save(new Product("TV Samsung 45", 700.00, "45 Led Tv"));
            repositoryP.save(new Product("iMac all included", 2300.00, "all in one imac for home use"));
            repositoryP.save(new Product("Wireless optical mouse", 10.00, "excelent mouse"));
            repositoryP.save(new Product("ipad", 320.00, "ipad with accesories"));
            repositoryP.save(new Product("Guitar Pro 6 Software for all musicians", 100.00, "New great guitar pro"));
            repositoryP.save(new Product("Sword Handle Umbrellas", 30.00, "When you need to stay safe from the rain but also want to keep your man ego intact, a sword handle umbrella is the only solution."));
            repositoryP.save(new Product("Unbreakable Smart Phone Screen Cover", 20.00, "Upgrade the armor on your expensive new smart phone with the unbreakable smart phone cover."));
            repositoryP.save(new Product("TRON Motorcycle", 30000.00, "This TRON style motorcycle is a fully functional and street legal bike that is powered by a Suzuki 996cc engine."));
            repositoryP.save(new Product("Diesel Powered MechWarrior", 1500000.00, "Now you can own your very own diesel powered MechWarrior that stands 13 feet tall and weighs 4.4 tons."));
            repositoryP.save(new Product("Spider Mouse", 15.00, "Encase a real spider in a clear acrylic mouse and watch as your computer or laptop quickly becomes the least used PC at your home or office."));
            repositoryP.save(new Product("Light Up Shoes", 45.00, "These aren’t just any regular light up shoes, these custom made kicks are hand made to order from the designer responsible for the lighting in the TRON movie and for Daft Punk."));
            */
            
            // ejemplo guardando un carrito de compra
            /*
            Customer customer = repository.findByEmail("karen@hotmail.com");
            ClientCart cart = new ClientCart(customer);
            CartDetail cartdetail1 = new CartDetail(cart, repositoryP.findByName("Xbox 360") , 1);
            cart.setCartDetail(Lists.newArrayList(cartdetail1));
            repositoryC.save(cart);
            */         
        };
    }

}
