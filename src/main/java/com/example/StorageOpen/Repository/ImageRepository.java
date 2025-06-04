package com.example.StorageOpen.Repository;

import com.example.StorageOpen.Entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<ImageEntity, Integer> {
    List<ImageEntity> findByOrderByIdIMG();
}
