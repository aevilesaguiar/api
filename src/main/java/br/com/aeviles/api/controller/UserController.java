package br.com.aeviles.api.controller;

import br.com.aeviles.api.model.User;
import br.com.aeviles.api.model.dto.UserDto;
import br.com.aeviles.api.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    //instancia do model Mapper que foi defiidio na classe ModelMapperConfig
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(mapper.map(service.findById(id), UserDto.class));

    }
    //endpoint que vai listar do tipo User do nosso banco

    @GetMapping//buscar todos
    public ResponseEntity<List<UserDto>> findAll(){
        List<UserDto> dtoList = service.findAll().stream().map(x-> mapper
                .map(x,UserDto.class)).collect(Collectors.toList());
        return ResponseEntity.ok().body(dtoList);

    }


}
