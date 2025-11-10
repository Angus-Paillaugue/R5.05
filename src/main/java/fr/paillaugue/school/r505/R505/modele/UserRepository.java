package fr.paillaugue.school.r505.R505.modele;

import org.springframework.data.jpa.repository.JpaRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends JpaRepository<UserData, Integer> {

  UserData findByName(String name);
}
