package com.codigoartesanal.lupa.services.impl;

import com.codigoartesanal.lupa.services.OriginPhoto;
import com.codigoartesanal.lupa.services.PathWebService;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by betuzo on 16/01/16.
 */
@Service
@Profile("googlestorage")
public class PathWebGoogleCloudImpl implements PathWebService {

    static final String PROPERTY_STATIC_GOOGLE_BUCKET_NAME = "lupa.service.google.pathWeb";

    @Resource
    Environment env;

    @Override
    public String getValidPathWebFoto(String path, OriginPhoto originPhoto) {
        String pathBase = originPhoto.getPathBase();
        String pathDefault = originPhoto.getPathDefault();

        if (path == null || path.isEmpty() || path.equals("novalid")) {
            return pathDefault;
        }

        String pathFull = env.getRequiredProperty(PROPERTY_STATIC_GOOGLE_BUCKET_NAME)
                + originPhoto.getPathPhotoBase() + pathBase + path;
        return pathFull;
    }
}
