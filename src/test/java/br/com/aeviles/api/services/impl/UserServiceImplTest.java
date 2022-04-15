package br.com.aeviles.api.services.impl;

import br.com.aeviles.api.domain.User;
import br.com.aeviles.api.domain.dto.UserDto;
import br.com.aeviles.api.repository.UserRepository;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class UserServiceImplTest {

    public static final Integer ID = 1;
    public static final String NAME = "Aeviles Aguiar";
    public static final String EMAIL = "aev@aev.com";
    public static final String PASSWORD = "123";
    @InjectMocks //ele vaic riar uma instancia real de UserServiceImpl, os demais ele vai mockar de mentira, eu preciso dessa instancia real por que vou testar os métodos
    private UserServiceImpl service;

    @Mock //por que eu posso mocjkar as respostas que esse método vai retornar
    private UserRepository repository;

    @Mock
    private ModelMapper mapper;

    private User user;
    private UserDto userDto;
    private Optional<User> optionalUser;

    @BeforeEach //antes de tudo faça o seguinte
    void setUp() {

        MockitoAnnotations.openMocks(this); //esse this faz referencia ao objeto que eu quero mockar fazendo referencia a UserServiceImpl
        //agora sempre que startarmos essa classe ele vai criar primiramente ele vai criar os mocks e depois ele chama os métodos users e introduzir valores
        startUser();
    }

    @Test
    @DisplayName(" when Find By Id then return An User Instance - quando fizer uma busca de um id então retorne uma instancia de um usuario")
    void findById() {

        //aqui é também posso importar o método do mockito
        Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(optionalUser);

        User response = service.findById(ID);


        //assegure para mim que ambos são iguais, ou seja o primeiro e o segundo argumetno que vou passar,
        // o primeiro argumento é o que eu estou esperando receber
        //o segundo argumento é o atual é o que o método retornou

        assertNotNull(response);//assegure para mim que o meu response não pode ser nulo
        assertEquals(User.class, response.getClass());//faz a comparação entre as duas classes
        /*
        forma não estatica de fazer assertion abaixo temos a forma estática onde ele importa o método e não precisamos ficar chamando toda hora
        Assertions.assertNotNull(response);//assegure para mim que o meu response não pode ser nulo
        Assertions.assertEquals(User.class, response.getClass());//faz a comparação entre as duas classes
        Assertions.assertEquals(ID, response.getId());//ele está comparando o ID que eu passei com o id da classe
        Assertions.assertEquals(NAME, response.getName());
        Assertions.assertEquals(EMAIL, response.getEmail());*/


        assertEquals(ID, response.getId());//ele está comparando o ID que eu passei com o id da classe
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());

    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }



    //Criar um método para iniciar todos os objetos

    private void startUser(){
        user=new User(ID, NAME, EMAIL, PASSWORD);
        userDto= new UserDto(ID, NAME, EMAIL, PASSWORD);
        optionalUser= Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
    }
}