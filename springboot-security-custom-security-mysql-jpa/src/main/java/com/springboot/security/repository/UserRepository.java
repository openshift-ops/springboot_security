package com.springboot.security.repository;

import com.springboot.security.entity.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author Bishesh
 */
public interface UserRepository extends CrudRepository<User,Integer> {


    public Optional<User> findUserByUserName(String userName);
}
