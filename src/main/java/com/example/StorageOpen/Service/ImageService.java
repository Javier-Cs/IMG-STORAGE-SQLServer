package com.example.StorageOpen.Service;

import com.example.StorageOpen.Entity.ImageEntity;
import com.example.StorageOpen.Repository.ImageRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    public List<ImageEntity> list(){
        return imageRepository.findByOrderByIdIMG();
    }

    public Optional<ImageEntity> getImageById(int id){
        return imageRepository.findById(id);
    }

    public void save(ImageEntity imageEntity){
        imageRepository.save(imageEntity);
    }

    public void delete(ImageEntity imageEntity){
        imageRepository.delete(imageEntity);
    }

    public boolean existsById(int id){
        return imageRepository.existsById(id);
    }

}
