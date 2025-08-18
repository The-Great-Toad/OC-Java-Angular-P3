package oc.rental.rental_oc.service.impl;

import io.micrometer.common.util.StringUtils;
import jakarta.annotation.PostConstruct;
import oc.rental.rental_oc.exception.StorageException;
import oc.rental.rental_oc.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

/**
 * <pre>
 * Ressources:
 * <a href="https://spring.io/guides/gs/uploading-files">Spring uploading files guide</a>
 * <a href="https://github.com/spring-guides/gs-uploading-files/tree/main/complete">GitHub</a>
 * </pre>
 */
@Service
public class FileSystemStorageService implements StorageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileSystemStorageService.class);
    private static final String LOG_PREFIX = "[FileSystemStorageService]";

    @Value("${app.base.url:http://localhost:8080}")
    private String baseUrl;

    private final Path rootLocation;

    public FileSystemStorageService(@Value("${app.upload.dir:uploads}") String location) {
        if(location.trim().isEmpty()){
            throw new StorageException("File upload location can not be Empty.");
        }
        this.rootLocation = Paths.get(location);
    }

    @PostConstruct
    public void initService() {
        deleteAll();
        init();
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
        LOGGER.info("Upload directory initialized: {}", rootLocation);
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
        LOGGER.info("Upload directory deleted: {}", rootLocation);
    }

    @Override
    public String store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file.");
            }

            String filename = generateUniqueFilename(file.getOriginalFilename());
            Path destinationFile = this.rootLocation
                    .resolve(Paths.get(filename))
                    .normalize()
                    .toAbsolutePath();

            /* Security check */
            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                throw new StorageException("Cannot store file outside current directory.");
            }

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }

            LOGGER.info("{} - File stored successfully: {}", LOG_PREFIX, filename);
            return baseUrl + filename;

        } catch (IOException e) {
            throw new StorageException("Failed to store file.", e);
        }
    }

    /**
     * Generates a unique filename for the uploaded file.
     *
     * @param originalFilename the original filename of the uploaded file
     * @return a unique filename with the same extension as the original file
     */
    private static String generateUniqueFilename(String originalFilename) {
        String extension = StringUtils.isNotBlank(originalFilename)
                ? originalFilename.substring(originalFilename.lastIndexOf("."))
                : "";
        return UUID.randomUUID() + extension;
    }
}