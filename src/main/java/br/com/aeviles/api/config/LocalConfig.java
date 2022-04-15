package br.com.aeviles.api.config;
//configuração do perfil de teste

import br.com.aeviles.api.domain.User;
import br.com.aeviles.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration//visto que é uma classe de configuração
@Profile("local")//é um perfil local
public class LocalConfig {
    @Autowired
    private UserRepository repository;

    @Bean //para que ele possa subir sempre quando este perfil estiver ativo
    public void startDB(){

        User u1 = new User(null, "Aeviles", "aev@email.com","123456");//utilizei o null por que a seuqnecia será gerada elo o banco de dados
        User u2 = new User(null, "Flavio", "fla@email.com","123456");//utilizei o null por que a seuqnecia será gerada elo o banco de dados
        User u3 = new User(null, "Inez", "inez@email.com","123456");//utilizei o null por que a seuqnecia será gerada elo o banco de dados

        repository.saveAll(List.of(u1,u2, u3));

    }
}
