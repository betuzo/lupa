package com.codigoartesanal.lupa.services;

/**
 * Created by betuzo on 15/01/16.
 */
public interface StorageImageService {
    public boolean writeImage(byte[] file, String logo, OriginPhoto originPhoto);

    void deleteImage(String path, OriginPhoto originPhoto);
}
