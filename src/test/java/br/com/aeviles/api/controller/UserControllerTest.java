package br.com.aeviles.api.controller;

import br.com.aeviles.api.domain.User;
import br.com.aeviles.api.domain.dto.UserDto;
import br.com.aeviles.api.services.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserControllerTest {


    public static final Integer ID = 1;
    public static final String NAME = "Aeviles Aguiar";
    public static final String EMAIL = "aev@aev.com";
    public static final String PASSWORD = "123";
    public static final String OBJETO_NAO_ENCONTRADO = "objeto não encontrado";
    public static final int INDEX = 0;

    @InjectMocks
    private UserController userController;

    private User user;
    private UserDto userDto;


    @Mock
    private UserServiceImpl service;

    @Mock
    private ModelMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        //chamar o método privado
        startUser();
    }

    @Test
    void whenFindByIdThenReturnSucess() {
        Mockito.when(service.findById(Mockito.anyInt())).thenReturn(user);
        Mockito.when(mapper.map(Mockito.any(),Mockito.any())).thenReturn(userDto);

        ResponseEntity<UserDto> response=userController.findById(ID);

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());//o corpo não pode ser vazio tem que vir um objeto
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(UserDto.class,response.getBody().getClass() );

        //assegurar os atributos
        //EU QUERO ASSEGURAR QUE O ID QUE EU PASSEI NO MEU METODO RESPONSO SEJA OS MESMO ID QUE EU PASSEI NO PARAMETRO
        Assertions.assertEquals(ID, response.getBody().getId());
        Assertions.assertEquals(NAME, response.getBody().getName());
        Assertions.assertEquals(EMAIL, response.getBody().getEmail());
        Assertions.assertEquals(PASSWORD, response.getBody().getPassword());

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

    }
}