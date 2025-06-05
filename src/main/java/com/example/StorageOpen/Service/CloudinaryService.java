package com.example.StorageOpen.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class CloudinaryService {
    Cloudinary cloudinary;

    public CloudinaryService() {
        Map<String, String> valoresMap= new HashMap<>();
        valoresMap.put("cloud_name", "IMGStorage");
        valoresMap.put("cloud_secret", "61725");
        valoresMap.put("cloud_url", "");
        cloudinary = new Cloudinary(valoresMap);
    }

    private File convertImg(MultipartFile multipartFile) throws IOException {
        File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream fot = new FileOutputStream(file);

        fot.write(multipartFile.getBytes());
        fot.close();
        return file;
    }

    public Map subirIMG(MultipartFile multiFileImg) throws IOException {
        File file = convertImg(multiFileImg);
        Map result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        if(!Files.deleteIfExists(file.toPath())) {
            throw new IOException("Error deleting file"+ file.getAbsolutePath());
        }
        return result;
    }

    public Map deleteIMG(String id)throws IOException{
        return cloudinary.uploader().destroy(id, ObjectUtils.emptyMap());
    }
}
