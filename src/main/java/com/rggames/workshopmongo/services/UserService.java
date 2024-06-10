package com.rggames.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rggames.workshopmongo.domain.User;
import com.rggames.workshopmongo.repository.UserRepository;
import com.rggames.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired   //instancia automaticamente um objeto no serviço
	private UserRepository repo;  //UserService acessa o Repository - o spring vai procurar esse objeto e vai instanciar 
	
	public List<User> findAll() {
		return repo.findAll();
	}
	
	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

}
