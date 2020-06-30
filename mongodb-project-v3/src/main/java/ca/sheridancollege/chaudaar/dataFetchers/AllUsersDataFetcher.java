package ca.sheridancollege.chaudaar.dataFetchers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ca.sheridancollege.chaudaar.beans.User;
import ca.sheridancollege.chaudaar.repositories.UserRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;


import java.util.List;

@Component
public class AllUsersDataFetcher implements DataFetcher<List<User>>{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> get(DataFetchingEnvironment dataFetchingEnvironment) {
        return userRepository.findAll();
    }
}