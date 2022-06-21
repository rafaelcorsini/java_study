package io.github.rafaelcorsini.study;

import java.util.function.BiFunction;
import java.util.function.BiPredicate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VendasApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}
	
	@Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            Integer n1 = 10;
            Integer n2 = 25;

            BiPredicate<Integer, Integer> testaNumeros = (numero1, numero2) -> numero1 > 0 && numero2 > 0;
            BiFunction<Integer, Integer, Integer> soma = (numero1, numero2) -> numero1 + numero2;

            if (testaNumeros.test(n1, n2)) {
                System.out.println(soma.apply(n1, n2));
            }
        };
    }

}