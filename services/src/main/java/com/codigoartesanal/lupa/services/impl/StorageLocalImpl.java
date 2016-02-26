package com.codigoartesanal.lupa.services.impl;

import com.codigoartesanal.lupa.services.*;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;

/**
 * Created by betuzo on 15/01/16.
 */
@Service
@Profile({"localbmvstorage", "localhomestorage", "test"})
public class StorageLocalImpl implements StorageImageService {

    @Resource
    Environment env;

    @Override
    public boolean writeImage(byte[] file, String path, OriginPhoto originPhoto) {
        return writeFile(file, getValidPathAbsolute(getPathBaseByOriginPhoto(originPhoto)) + path);
    }

    @Override
    public void deleteImage(String logo, OriginPhoto originPhoto) {
        String pathFull = env.getRequiredProperty(PathWebService.PROPERTY_STATIC_FILE_PHOTO)
                + getPathBaseByOriginPhoto(originPhoto) + logo;
        File dir = new File(pathFull);
        if (dir.exists())
            dir.delete();
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

    private boolean writeFile(byte[] file, String path)  {
        if (file == null || path == null || path.isEmpty()) {
            return false;
        }

        File serverFile = new File(path);
        BufferedOutputStream stream = null;
        try {
            stream = new BufferedOutputStream(
                    new FileOutputStream(serverFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        try {
            stream.write(file);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        try {
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private String getValidPathAbsolute(String base) {
        String pathFull = env.getRequiredProperty(PathWebService.PROPERTY_STATIC_FILE_PHOTO)
                + base;
        File dir = new File(pathFull);
        if (!dir.exists())
            dir.mkdirs();

        return dir.getAbsolutePath()
                + File.separator;
    }
}
