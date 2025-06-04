package com.example.StorageOpen.Entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Strorage_TBL")
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idIMG;
    private String imgName;
    private String imgUri;
    private String imgCode;

    public ImageEntity(String imgName, String imgUri, String imgCode) {
        this.imgName = imgName;
        this.imgUri = imgUri;
        this.imgCode = imgCode;
    }
}
