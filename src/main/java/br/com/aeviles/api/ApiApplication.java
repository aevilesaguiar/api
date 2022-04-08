package br.com.aeviles.api;

import br.com.aeviles.api.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);


		//Verificar se as anotações realmente estão sendo contruidas, a anotação no @argsContructor está funcionando corretamente

		User user = new User(1, "Aeviles", "aev@aev.com","12345678");
	}




}
