package org.lilyhe.admin.user;

import org.lilyhe.common.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Integer> {
}
