package com.training.users.repository;

import com.training.users.model.UserRedisHash;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<UserRedisHash, String> {
    UserRedisHash findByUserName(String userName);
    List<UserRedisHash> findAllByUserName(String userName);
    UserRedisHash findByEmail(String email);
}
