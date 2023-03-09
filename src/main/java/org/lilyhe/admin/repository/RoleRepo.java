package org.lilyhe.admin.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import org.lilyhe.admin.model.Role;

/**
 * @author Lily H.
 * Repo layer for entity classes and queries to db
 * Using built it in methods of save, findAll, deleteByID etc.
 */

@Repository
public interface RoleRepo extends CrudRepository<Role, Integer> {
}
