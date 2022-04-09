package br.com.aeviles.api.services.impl;

import br.com.aeviles.api.model.User;
import br.com.aeviles.api.repository.UserRepository;
import br.com.aeviles.api.services.UserService;
import br.com.aeviles.api.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;


    @Override
    public User findById(Integer id) {

        //é utilizado o Optional por que ele pode retornar ou não um ID
        Optional<User> obj = repository.findById(id);
       // return obj.orElse(null); //senão encontrar o Id o valor é nulo
        //após a criação da classe ObjectNotFoundException
        return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado"));
    }



}
