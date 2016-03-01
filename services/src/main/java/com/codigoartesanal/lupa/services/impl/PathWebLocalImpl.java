package com.codigoartesanal.lupa.services.impl;

import com.codigoartesanal.lupa.services.OriginPhoto;
import com.codigoartesanal.lupa.services.PathWebService;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;

/**
 * Created by betuzo on 16/01/16.
 */
@Service
@Profile({"localbmvstorage", "localhomestorage", "test"})
public class PathWebLocalImpl implements PathWebService {

    @Resource
    Environment env;

    @Override
    public String getValidPathWebFoto(String path, OriginPhoto originPhoto) {
        String pathBase = originPhoto.getPathBase();
        String pathDefault = originPhoto.getPathDefault();

        if (path == null || path.isEmpty()) {
            return pathDefault;
        }

        String pathFull = env.getRequiredProperty(PathWebService.PROPERTY_STATIC_FILE_PHOTO);
        pathFull = pathFull + pathBase + path;
        File file = new File(pathFull);
        if (file == null || !file.exists()) {
            return pathDefault;
        }
        return originPhoto.getPathPhotoBase() + pathBase + path;
    }
}
