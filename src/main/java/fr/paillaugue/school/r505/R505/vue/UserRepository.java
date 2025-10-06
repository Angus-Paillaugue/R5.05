package fr.paillaugue.school.r505.R505.vue;

import org.springframework.data.repository.CrudRepository;

import fr.paillaugue.school.r505.R505.modele.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Integer> {

}
