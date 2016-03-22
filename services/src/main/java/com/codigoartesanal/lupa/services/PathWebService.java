package com.codigoartesanal.lupa.services;

/**
 * Created by betuzo on 16/01/16.
 */
public interface PathWebService {

    String PROPERTY_STATIC_FILE_PHOTO = "lupa.web.pathPhoto";

    String getValidPathWebFoto(String path, OriginPhoto originPhoto);
}
