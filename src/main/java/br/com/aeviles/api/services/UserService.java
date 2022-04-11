package br.com.aeviles.api.services;

import br.com.aeviles.api.model.User;
import br.com.aeviles.api.model.dto.UserDto;

import java.util.List;

public interface UserService {

    User findById (Integer id);


    List<User> findAll();

    User create(UserDto obj);

    User update(UserDto obj);

    void delete (Integer id);
}
