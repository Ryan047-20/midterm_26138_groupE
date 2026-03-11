package com.busstopfinder.busstopfinder.repositories;

import com.busstopfinder.busstopfinder.model.Location;
import com.busstopfinder.busstopfinder.model.LocationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    boolean existsByNameAndType(String name, LocationType type);

    List<Location> findByType(LocationType type);

    List<Location> findByParentId(Long parentId);

    @Query("SELECT u FROM User u WHERE u.village.parent.parent.parent.parent.id = :provinceId")
    List<Location> findUsersByProvinceId(@Param("provinceId") Long provinceId);
}
