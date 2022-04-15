package br.com.aeviles.api.services.impl;

import br.com.aeviles.api.domain.User;
import br.com.aeviles.api.domain.dto.UserDto;
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

    @Override
    public User update(UserDto obj) {

        findByEmail(obj);
        return repository.save(mapper.map(obj, User.class));
    }

    @Override
    public void delete(Integer id) {

        findById(id);
        repository.deleteById(id);

    }


    //TRATAR O EMAIL CASO O MESMO REPITA
    private void findByEmail(UserDto obj){
        Optional<User> user=repository.findByEmail(obj.getEmail());

        if(user.isPresent() && !user.get().getId().equals(obj.getId())){// se o ig desse cara for diferente do id que
                                                  // veio como parametro quer dizer que é o id de outro usuario, então lança a exceção para ele , senão segue fluxo
            throw new DataIntegratyViolationException("Email já cadastrado no sistema");
        }
    }


}
