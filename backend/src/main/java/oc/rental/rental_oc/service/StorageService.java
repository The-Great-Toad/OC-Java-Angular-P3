package oc.rental.rental_oc.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    /**
     * Initializes the file upload service.
     * This method is called at application startup to set up the upload directory.
     */
    void init();

    /**
     * Deletes all files in the upload directory.
     * This method is called at application startup to ensure a clean state.
     */
    void deleteAll();

    /**
     * Stores the uploaded file in the designated upload directory.
     *
     * @param file the file to be stored
     * @return the URL of the stored file
     */
    String store(MultipartFile file);

}
