package com.training.users.repository;

import com.training.users.model.UserRedisHash;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserRedisHash, String> {
    UserRedisHash findByUserName(String userName);
    UserRedisHash findByEmail(String email);
}
