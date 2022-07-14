package com.soft_kali.mfoodly.repository;

import com.soft_kali.mfoodly.entity.OutletEntity;
import com.soft_kali.mfoodly.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutletRepository extends JpaRepository<OutletEntity, Long> {

    Page<OutletEntity> findAllByUserEntity(UserEntity userEntity, Pageable pageable);

}
