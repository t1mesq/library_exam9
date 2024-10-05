package com.attractor.library.repository;

import com.attractor.library.entity.UserAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthoritiesRepository extends JpaRepository<UserAuthority, Long> {
}
