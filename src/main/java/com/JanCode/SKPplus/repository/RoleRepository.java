package com.JanCode.SKPplus.repository;

import com.JanCode.SKPplus.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

}