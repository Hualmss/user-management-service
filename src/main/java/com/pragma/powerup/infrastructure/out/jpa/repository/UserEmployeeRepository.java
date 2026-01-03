package com.pragma.powerup.infrastructure.out.jpa.repository;

import com.pragma.powerup.infrastructure.out.jpa.Entity.OwnerEmployee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserEmployeeRepository extends CrudRepository<OwnerEmployee, Long> {

    //List<OwnerEmployee> getAllEmployesByOwnerId();
}
