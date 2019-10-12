package com.sellerapp.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RestController;

import com.sellerapp.demo.model.Todo;

@RestController
public interface TodoRepository extends CrudRepository<Todo,Long>{

}
