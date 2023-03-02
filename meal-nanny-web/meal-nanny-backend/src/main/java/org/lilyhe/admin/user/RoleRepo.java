package org.lilyhe.admin.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import org.lilyhe.common.entity.Role;

// Repo layer for entity classes and queries to db
// Using built it in methods of save, findAll, deleteByID etc.
@Repository
public interface RoleRepo extends CrudRepository<Role, Integer> {
}
