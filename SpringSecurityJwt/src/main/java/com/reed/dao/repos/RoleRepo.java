package com.reed.dao.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.reed.dao.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {

	@Query("from Role R where R.user.userId=?1")
	public List<Role> findByUserId(long userId);
	
}
