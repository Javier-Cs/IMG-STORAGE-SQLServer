package com.example.StorageOpen.Controller;

import com.example.StorageOpen.Entity.ImageEntity;
import com.example.StorageOpen.Service.CloudinaryService;
import com.example.StorageOpen.Service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/APIrest")
@CrossOrigin(origins = "http://localhost:4200")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping("/allImg")
    public ResponseEntity<List<ImageEntity>> listaImagenes() {
        List<ImageEntity> listaIMG = imageService.list();
        if (listaIMG == null || listaIMG.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.status(HttpStatus.OK).body(listaIMG);
    }


    @PostMapping("/subirIMG")
    @ResponseBody
    public ResponseEntity<String> upload(@RequestParam MultipartFile multipartFile) throws IOException {
        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if (bi == null) {
            return new ResponseEntity<>("image", HttpStatus.BAD_REQUEST);
        }
        Map result = cloudinaryService.subirIMG(multipartFile);
        ImageEntity image = new ImageEntity(
                (String) result.get("original_filename"),
                (String) result.get("url"),
                (String) result.get("public_id"));
        imageService.save(image);
        return new ResponseEntity<>("image", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
        Optional<ImageEntity> imagenOpcional = imageService.getImageById(id);
        if(imagenOpcional.isEmpty()){
            return new ResponseEntity<>("image", HttpStatus.BAD_REQUEST);
        }
        ImageEntity image = imagenOpcional.get();
        String cloudinaryImageId = image.getImgCode();

        try{
            cloudinaryService.deleteIMG(cloudinaryImageId);
        } catch (IOException e) {
            return new ResponseEntity<>("image", HttpStatus.BAD_REQUEST);
        }
        imageService.delete(image);
        return new ResponseEntity<>("image", HttpStatus.OK);
    }


}
