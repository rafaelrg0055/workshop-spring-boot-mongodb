package com.rggames.workshopmongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rggames.workshopmongo.domain.User;
import com.rggames.workshopmongo.services.UserService;


@RestController
@RequestMapping(value="/users")
public class UserResource {

	@Autowired
	private UserService service;   //O controlador Rest UserResource acessa o Service
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<User>> findAll(){   //Usar RespondeEntity para respostas HTTP com cabeçalho, erros e etc...
	
		List<User> list = service.findAll();  //busca no BD os usuários e guarda da lista
		return ResponseEntity.ok().body(list);
	}
	
	
}
