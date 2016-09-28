package com.store;

import com.store.exchangeservvice.ExchangeThreatManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class SpringBootStart {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(SpringBootStart.class, args);

        String[] beanNames = context.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
        context.getBean(ExchangeThreatManager.class).startTread();
    }

}
