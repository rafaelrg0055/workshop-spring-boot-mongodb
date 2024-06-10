package com.rggames.workshopmongo.resources;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	
	
}
