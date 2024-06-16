package com.rggames.workshopmongo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.rggames.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{   //só com isso será capaz de fazer operações como salvar, recuperar, deletar...

	//List<Post> findByTitleContainingIgnoreCase(String text);  //Com Query Methods
	
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")  //Com @Query
	List<Post> searchTitle(String text);
	
	@Query("{ $and: [ { date: {$gte: ?1} }, { date: { $lte: ?2} } , { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
	List<Post> fullSearch(String text, Date minDate, Date maxDate);
}
