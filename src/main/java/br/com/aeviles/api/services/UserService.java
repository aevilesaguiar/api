package br.com.aeviles.api.services;

import br.com.aeviles.api.domain.User;
import br.com.aeviles.api.domain.dto.UserDto;

import java.util.List;

public interface UserService {

    User findById (Integer id);

    List<User> findAll();

    User create(UserDto obj);

    User update(UserDto obj);

    void delete (Integer id);
}
