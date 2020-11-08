package com.dexsys.telegrammbot.repository;

import com.dexsys.telegrammbot.domain.User;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface IUserCrudRepository extends CrudRepository<User, Long> {

    Optional<User> findByPhone (String phone);
}
