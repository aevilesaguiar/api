package br.com.aeviles.api.services.impl;

import br.com.aeviles.api.config.ModelMapperConfig;
import br.com.aeviles.api.model.User;
import br.com.aeviles.api.model.dto.UserDto;
import br.com.aeviles.api.repository.UserRepository;
import br.com.aeviles.api.services.UserService;
import br.com.aeviles.api.services.exception.DataIntegratyViolationException;
import br.com.aeviles.api.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper mapper;

    //buscar por id
    @Override
    public User findById(Integer id) {

        //é utilizado o Optional por que ele pode retornar ou não um ID
        Optional<User> obj = repository.findById(id);
       // return obj.orElse(null); //senão encontrar o Id o valor é nulo
        //após a criação da classe ObjectNotFoundException
        return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado")); //senão encontrar o id ele retorna uma exception
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User create(UserDto obj) {
        findByEmail(obj);//se existir um email igual o que foi passado lança um a exceção, senão ele segue a criação
        return repository.save(mapper.map(obj, User.class));
    }

    private void findByEmail(UserDto obj){
        Optional<User> user=repository.findByEmail(obj.getEmail());

        if(user.isPresent()){
            throw new DataIntegratyViolationException("Email já cadastrado no sistema");
        }
    }


}
