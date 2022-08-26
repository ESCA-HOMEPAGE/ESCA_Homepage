package com.esca.escahp.keeper.repository;

import com.esca.escahp.keeper.entity.Keeper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeeperRepository extends JpaRepository<Keeper, Long> {

}