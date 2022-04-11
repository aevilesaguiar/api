package br.com.aeviles.api.controller;

import br.com.aeviles.api.model.User;
import br.com.aeviles.api.model.dto.UserDto;
import br.com.aeviles.api.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    //boa prática para não ter que repetir muito código
    public static final String ID = "/{id}";
    //instancia do model Mapper que foi defiidio na classe ModelMapperConfig
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserService service;

    @GetMapping(value = ID)
    public ResponseEntity<UserDto> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(mapper.map(service.findById(id), UserDto.class));

    }
    //endpoint que vai listar todos os usuarios do nosso banco

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok().body(service.findAll()
                .stream().map(x -> mapper.map(x, UserDto.class)).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserDto obj){
        User newObj=service.create(obj);

        URI uri= ServletUriComponentsBuilder.fromCurrentRequest().path(ID).buildAndExpand(newObj.getId()).toUri();

        return ResponseEntity.created(uri).build();

    }

    @PutMapping(value = ID)
    public ResponseEntity<UserDto> update(@PathVariable Integer id, @RequestBody UserDto obj){
        obj.setId(id);
        User newObj = service.update(obj);
        return ResponseEntity.ok().body(mapper.map(newObj, UserDto.class));


    }

@DeleteMapping(value = ID)
    public  ResponseEntity<UserDto> delete(@PathVariable Integer id){
        service.delete(id);

        return ResponseEntity.noContent().build();

}

}
