package ca.sheridancollege.chaudaar.repositories;



import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import ca.sheridancollege.chaudaar.beans.User;

public interface UserRepository extends MongoRepository<User,Long>{

	User findById(String id);

	User findByName(String name);


}
