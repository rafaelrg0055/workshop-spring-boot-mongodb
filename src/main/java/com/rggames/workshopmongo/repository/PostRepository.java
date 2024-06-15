package com.rggames.workshopmongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.rggames.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{   //só com isso será capaz de fazer operações como salvar, recuperar, deletar...

	List<Post> findByTitleContainingIgnoreCase(String text);
}
