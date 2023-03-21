package com.abernathy.mediscreen.mauthorization.repository;

import com.abernathy.mediscreen.mauthorization.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public Optional<User> findByUsername(String userName);

}
