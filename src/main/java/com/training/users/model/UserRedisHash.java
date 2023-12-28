package com.training.users.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash("user")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserRedisHash {
    @Id private String userId;
    @Indexed private String userName;
    @Indexed private String status;
    @Indexed private String email;
    private String password;
}
