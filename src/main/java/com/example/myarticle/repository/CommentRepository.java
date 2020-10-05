package com.example.myarticle.repository;

import com.example.myarticle.entity.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository  extends CrudRepository<Comment, Long> {
}
