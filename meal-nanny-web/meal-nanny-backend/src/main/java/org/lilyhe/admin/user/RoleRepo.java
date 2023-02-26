package org.lilyhe.admin.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import org.lilyhe.common.entity.Role;

@Repository
public interface RoleRepo extends CrudRepository<Role, Integer> {
}
