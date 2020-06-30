package ca.sheridancollege.chaudaar.services;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import ca.sheridancollege.chaudaar.dataFetchers.AllUsersDataFetcher;
import ca.sheridancollege.chaudaar.dataFetchers.UserDataFetcher;
import ca.sheridancollege.chaudaar.repositories.UserRepository;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Service
public class GraphQLService {


    @Autowired
    private UserRepository userRepository;

    
    @Value("classpath:users.graphqls")
    Resource resource;

    private GraphQL graphQL;
    @Autowired
    private AllUsersDataFetcher allUsersDataFetcher;
    @Autowired
    private UserDataFetcher userDataFetcher;

    // load schema at application start up
    @PostConstruct
    private void loadSchema() throws IOException {
    	
        // get the schema
        File schemaFile = resource.getFile();
        // parse schema
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

	

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("allUsers", allUsersDataFetcher)
                        .dataFetcher("user", userDataFetcher))
                .build();
    }


    public GraphQL getGraphQL() {
        return graphQL;
    }
	
}
