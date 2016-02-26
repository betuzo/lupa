package com.codigoartesanal.lupa.services.impl;

import com.codigoartesanal.lupa.services.*;
import com.codigoartesanal.lupa.services.google.GoogleCloudStorage;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * Created by betuzo on 15/01/16.
 */
@Service
@Profile("googlestorage")
public class StorageGoogleCloudImpl implements StorageImageService {

    static final String PROPERTY_STATIC_GOOGLE_BUCKET_NAME = "lupa.service.google.bucketName";
    static final String PROPERTY_STATIC_GOOGLE_IMAGE_CONTENT_TYPE = "image/*";

    @Resource
    Environment env;

    @Override
    public boolean writeImage(byte[] file, String logo, OriginPhoto originPhoto) {
        String pathFull = env.getRequiredProperty(PathWebService.PROPERTY_STATIC_FILE_PHOTO) +
                getPathBaseByOriginPhoto(originPhoto) + logo;
        return writeFile(file, pathFull);
    }

    @Override
    public void deleteImage(String logo, OriginPhoto originPhoto) {
        String pathFull = env.getRequiredProperty(PathWebService.PROPERTY_STATIC_FILE_PHOTO) +
                getPathBaseByOriginPhoto(originPhoto) + logo;
        deleteFile(pathFull);
    }

    private String getPathBaseByOriginPhoto(OriginPhoto originPhoto){
        switch (originPhoto){
            case ARBITRO:
                return PathPhoto.ARBITRO_BASE.getPath();
            case PERSONA:
                return PathPhoto.JUGADOR_BASE.getPath();
            case EQUIPO:
                return PathPhoto.EQUIPO_BASE.getPath();
        }

        return "";
    }

    private void deleteFile(String path){
        try {
            GoogleCloudStorage.deleteObject(path, env.getRequiredProperty(PROPERTY_STATIC_GOOGLE_BUCKET_NAME));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }

    private boolean writeFile(byte[] file, String path)  {
        try {
            GoogleCloudStorage.uploadStream(
                    path, PROPERTY_STATIC_GOOGLE_IMAGE_CONTENT_TYPE,
                    new ByteArrayInputStream(file),
                    env.getRequiredProperty(PROPERTY_STATIC_GOOGLE_BUCKET_NAME));
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
