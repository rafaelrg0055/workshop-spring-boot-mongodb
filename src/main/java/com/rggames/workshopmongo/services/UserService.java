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
	
	//INSERT
	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	//DELETE
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	//PUT 
	public User update(User obj) {
		User newObj = findById(obj.getId());   //obj original do banco de dados
		updateData(newObj, obj);   //método para copiar os dados do obj para o newObj
		return repo.save(newObj);
	}
	
	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}

	
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}

}
