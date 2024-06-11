package com.rggames.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rggames.workshopmongo.domain.User;
import com.rggames.workshopmongo.dto.UserDTO;
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
	
	
	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}

}
