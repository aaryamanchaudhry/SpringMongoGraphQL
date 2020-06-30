package ca.sheridancollege.chaudaar.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.sheridancollege.chaudaar.beans.User;
import ca.sheridancollege.chaudaar.repositories.UserRepository;
import ca.sheridancollege.chaudaar.services.GraphQLService;
import graphql.ExecutionResult;

@RestController
public class HomeController {

	@Autowired
	private UserRepository userRepository;

	@PutMapping(consumes="application/json")
	public String putStudentCollection(@RequestBody List<User> userList)
	{userRepository.deleteAll();
	userRepository.saveAll(userList);
	return "Total Records: " + userRepository.count();
	}
	
	
	 @Autowired
	    GraphQLService graphQLService;

	    @PostMapping("/graph/users")
	    public ResponseEntity<Object> getAllBooks(@RequestBody String query) {
	        ExecutionResult execute = graphQLService.getGraphQL().execute(query);

	        return new ResponseEntity<>(execute, HttpStatus.OK);
	    }
}


