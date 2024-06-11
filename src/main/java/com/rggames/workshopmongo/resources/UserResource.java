package com.rggames.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rggames.workshopmongo.domain.User;
import com.rggames.workshopmongo.dto.UserDTO;
import com.rggames.workshopmongo.services.UserService;


@RestController
@RequestMapping(value="/users")
public class UserResource {

	@Autowired
	private UserService service;   //O controlador Rest UserResource acessa o Service
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> findAll(){   //Usar RespondeEntity para respostas HTTP com cabeçalho, erros e etc...
	
		List<User> list = service.findAll();  //busca no BD os usuários e guarda da lista
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());   //converte os obj da lista original para DTO
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<UserDTO> findById(@PathVariable String id){   
		User obj = service.findById(id); 
		return ResponseEntity.ok().body(new UserDTO(obj));
	}
	
	@RequestMapping(method=RequestMethod.POST)   //ou pode usar PostMapping
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDto){   
		User obj = service.fromDTO(objDto);   //converte DTO para User
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();   //retorna na página o código 201 contendo resposta vazia com cabeçalho com a localização do recurso criado
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable String id){   
		service.delete(id); 
		return ResponseEntity.noContent().build();   //código 204, quando não retorna nada
	}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)   
	public ResponseEntity<Void> update(@RequestBody UserDTO objDto, @PathVariable String id){   
		User obj = service.fromDTO(objDto);   //converte DTO para User
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();   //código 204, quando não retorna nada
	}
	
	
}
