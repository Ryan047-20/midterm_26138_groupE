package com.busstopfinder.busstopfinder.repositories;

import com.busstopfinder.busstopfinder.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.village.parent.parent.parent.parent.id = :provinceId")
    List<User> findByProvinceId(@Param("provinceId") Long provinceId);

    @Query("SELECT u FROM User u WHERE u.village.parent.parent.parent.parent.name = :provinceName")
    List<User> findByProvinceName(@Param("provinceName") String provinceName);
}

