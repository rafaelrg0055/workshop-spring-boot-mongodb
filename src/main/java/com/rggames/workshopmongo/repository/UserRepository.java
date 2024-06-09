package com.rggames.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.rggames.workshopmongo.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{   //só com isso será capaz de fazer operações como salvar, recuperar, deletar...

}
