package com.sellerapp.demo.controller;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sellerapp.demo.model.Todo;
import com.sellerapp.demo.repository.TodoRepository;

@RestController
@RequestMapping("todo")
@CrossOrigin(origins = "http://localhost:4200")
public class TodoController {

	@Autowired
	private TodoRepository todoRepo;
	
	public TodoController(TodoRepository repository) {
        this.todoRepo = repository;
    }

    @GetMapping("")
    public Collection<Todo> getAll() {

        return ((Collection<Todo>) todoRepo.findAll()).stream()
                .collect(Collectors.toList());
    }

    @PostMapping(value = "")
    public Todo add(@RequestBody Todo todo) {

        todoRepo.save(todo);
        return todo;
    }

    @PutMapping(value = "/{id}")
    public Todo update(@PathVariable("id") String id, @RequestBody Todo todo) {

        todoRepo.save(todo);
        return todo;
    }


    @GetMapping(value = "/{id}")
    public Optional<Todo> findToDoById(@PathVariable("id") Long id) {

        Optional<Todo> todo = todoRepo.findById(id);
        return todo;
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {

    	todoRepo.deleteById(id);
    }
    
    @GetMapping("/complete")
    public Collection<Todo> getComplete() {

        return ((Collection<Todo>) todoRepo.findAll()).stream()
                .filter(todo -> todo.getCompleted())
                .collect(Collectors.toList());
    }
    
    @GetMapping("/pending")
    public Collection<Todo> getPending() {

        return ((Collection<Todo>)todoRepo.findAll()).stream()
                .filter(todo -> !todo.getCompleted())
                .collect(Collectors.toList());
    }
	
}
