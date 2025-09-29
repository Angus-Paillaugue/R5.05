package fr.paillaugue.school.r505.R505;

import org.springframework.data.repository.CrudRepository;

import fr.paillaugue.school.r505.R505.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Integer> {

}
