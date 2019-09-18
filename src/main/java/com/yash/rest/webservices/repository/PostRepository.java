package com.yash.rest.webservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yash.rest.webservices.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

}
