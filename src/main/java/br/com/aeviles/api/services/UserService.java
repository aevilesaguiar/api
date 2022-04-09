package br.com.aeviles.api.services;

import br.com.aeviles.api.model.User;

import java.util.List;

public interface UserService {

    User findById (Integer id);


    List<User> findAll();

}
