package kg.attractor.demo.service;

import kg.attractor.demo.dto.ImageDto;
import lombok.SneakyThrows;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public interface ImageService {

    @SneakyThrows
    default String saveUploadedFile(MultipartFile file, String subDir){
        String uuidFile = UUID.randomUUID().toString();
        String resultFileName = uuidFile + "_" + file.getOriginalFilename();

        Path pathDir = Paths.get("data/" + subDir);
        Files.createDirectories(pathDir);

        Path filePath = Paths.get(pathDir + "/" + resultFileName);
        if (!Files.exists(filePath)) Files.createFile(filePath);

        try (OutputStream os = Files.newOutputStream(filePath)) {
            os.write(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultFileName;
    }

    default ResponseEntity<?> downloadFile(String filename, String subDir, MediaType mediaType){
        try {
            byte[] image = Files.readAllBytes(Paths.get("data/" + subDir + "/" + filename));
            Resource resource = new ByteArrayResource(image);
            return  ResponseEntity.ok().
                    header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                    .contentLength(resource.contentLength())
                    .contentType(mediaType)
                    .body(resource);
        } catch (IOException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Image not found");
        }
    }

    ResponseEntity<?> getById(String filename);

    void create(ImageDto imageDto);
}
