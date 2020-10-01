package com.example.simple_project_in_spring.repository;

import com.example.simple_project_in_spring.model.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Integer> {

    Privilege findByName(String name);
}
