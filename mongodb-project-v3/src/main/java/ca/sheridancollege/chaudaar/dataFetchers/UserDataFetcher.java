package ca.sheridancollege.chaudaar.dataFetchers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ca.sheridancollege.chaudaar.beans.User;
import ca.sheridancollege.chaudaar.repositories.UserRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
	public class UserDataFetcher implements DataFetcher<User>{

	    @Autowired
	    UserRepository userRepository;

	    @Override
	    public User get(DataFetchingEnvironment dataFetchingEnvironment) {

	        String name = dataFetchingEnvironment.getArgument("name");

	        return userRepository.findByName(name);
	    }
	}
	