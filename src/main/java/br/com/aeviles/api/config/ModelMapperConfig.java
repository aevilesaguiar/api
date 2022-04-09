package br.com.aeviles.api.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    //dessa forma eu consigo injetar ao invés de criar uma instancia e deixar que o Spring faça o gerenciamento para nó
    @Bean
    public ModelMapper mapper(){
        return new ModelMapper();
    }

}
