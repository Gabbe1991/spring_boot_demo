package com.example.spring_boot_demo.repositories;

import com.example.spring_boot_demo.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
}
