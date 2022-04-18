package br.com.aeviles.api.services.impl;

import br.com.aeviles.api.domain.User;
import br.com.aeviles.api.domain.dto.UserDto;
import br.com.aeviles.api.repository.UserRepository;
import br.com.aeviles.api.services.exception.DataIntegratyViolationException;
import br.com.aeviles.api.services.exception.ObjectNotFoundException;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceImplTest {

    public static final Integer ID = 1;
    public static final String NAME = "Aeviles Aguiar";
    public static final String EMAIL = "aev@aev.com";
    public static final String PASSWORD = "123";
    public static final String OBJETO_NAO_ENCONTRADO = "objeto não encontrado";
    public static final int INDEX = 0;
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
    @DisplayName(" When find by iD then Return An Object Not Found Exception - quando procurar por um id retornar um not Found Exception" )
    void findByIdObjectNotFoundException(){

        //quando chamar um método findById , estoure uma exceção do tipo ObjectNotFound exception  com essa mensagem("objeto não encontrado"));
        //ou seja estamos mockando a mensagem
        when(repository.findById(Mockito.anyInt())).thenThrow( new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO));//thenThrow -> lança uma exceção
                                                                                                                    //ObjectNotFoundException é do Hibbernate
    try {
        service.findById(ID);
    }catch (Exception ex){
        assertEquals(ObjectNotFoundException.class, ex.getClass()); //assegure que essa exceção que foi lançada é um object do tipo Object not found
        assertEquals(OBJETO_NAO_ENCONTRADO, ex.getMessage());//assegure para mim que essas mensagens são iguais


    }

    }

    @Test
    @DisplayName("when find all then return An List of Users - quando buscar todos retorne uma lista de todos os usuarios")
    void findAll() {

        when(repository.findAll()).thenReturn(List.of(user));
        List<User> response=service.findAll();

        assertNotNull(response);//não pode ser vazio
        assertEquals(1, response.size());//eu quero assegurar que o tamanho da lista seja de apenas 1, pois eu adicionei apenas um usuario
        assertEquals(User.class,response.get(INDEX).getClass() );// eu quero assegurar que o meu User.class é igual ao response.get no index (0) .getClass() e que tenha a classe do tipo user
        //asseguro que o objeto que está vindo no index 0 da minha lista tem o id igual ao ID que temos constantes
        assertEquals(ID,response.get(INDEX).getId());
        assertEquals(NAME,response.get(INDEX).getName());
        assertEquals(EMAIL,response.get(INDEX).getEmail());
        assertEquals(PASSWORD,response.get(INDEX).getPassword());




    }

    @Test
    @DisplayName("when create then return sucess")
    void create() {

        when(repository.save(any())).thenReturn(user);

        User response = service.create(userDto);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(NAME, response.getName());

    }

    @Test
    @DisplayName("when update then return sucess")
    void updateReturnSucess() {

        when(repository.save(any())).thenReturn(user);

        User response = service.update(userDto);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(NAME, response.getName());

    }


    @Test
    @DisplayName("when update then return sucess An Data Integrity Violation Exception")
    void createReturnViolationException() {

        when(repository.findByEmail(anyString())).thenReturn(optionalUser);
        try {
            optionalUser.get().setId(2);
            service.create(userDto);
        }catch (Exception e){
            assertEquals(DataIntegratyViolationException.class, e.getClass());
            assertEquals("Email já cadastrado no sistema", e.getMessage());
        }

    }


    @Test
    @DisplayName("when create then return An Data Integrity Violation Exception")
    void whenUpdateThenReturnAnDataIntegrityViolationException() {
        when(repository.findByEmail(anyString())).thenReturn(optionalUser);

        try{
            optionalUser.get().setId(2);
            service.create(userDto);
        } catch (Exception ex) {
            assertEquals(DataIntegratyViolationException.class, ex.getClass());
            assertEquals("Email já cadastrado no sistema", ex.getMessage());
        }
    }


    @Test
    @DisplayName(" delete With Sucess")
    void deleteWithSuccess() {
        when(repository.findById(anyInt())).thenReturn(optionalUser);
        doNothing().when(repository).deleteById(anyInt());
        service.delete(ID);
    //verificar quantas vezes o meu repository foi chamado no metodo deleteById , eu espero que seja 1 vez, senão está errado
        verify(repository, times(1)).deleteById(anyInt());
    }


    @Test
    void deleteWithObjectNoutFoundException(){

        when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundException("Objeto não encontrado"));
        try {
            service.delete(ID);
        }catch (Exception ex){
            assertEquals("Objeto não encontrado", ex.getMessage());

        }

    }




    //Criar um método para iniciar todos os objetos

    private void startUser(){
        user=new User(ID, NAME, EMAIL, PASSWORD);
        userDto= new UserDto(ID, NAME, EMAIL, PASSWORD);
        optionalUser= Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
    }
}