package com.codigoartesanal.lupa.controller;

import com.codigoartesanal.lupa.services.*;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by betuzo on 30/10/15.
 */
@Controller
@RequestMapping("/file")
public class FileUploadController {

    @Autowired
    PathWebService pathWebService;

    @Autowired
    StorageImageService storageImageServices;

    @Autowired
    PersonaService personaService;

    @RequestMapping(value = "/upload/foto", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> addFileFoto(HttpServletRequest request, HttpServletResponse response,
                                       @RequestParam("id") Long id, @RequestParam("origin") String origin) throws IOException {
        if (!ServletFileUpload.isMultipartContent(request)) {

            if (!(request instanceof StandardMultipartHttpServletRequest)){
                throw new IllegalArgumentException("Request is not multipart, please 'multipart/form-data' enctype for your form.");
            }
        }

        StandardMultipartHttpServletRequest dmhsRequest = (StandardMultipartHttpServletRequest) request ;
        MultipartFile file = (MultipartFile) dmhsRequest.getFile("filelogo");

        Map<String, String> result = new HashMap<>();
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                String nameLogo = getValidNameLogo(file.getOriginalFilename(), id);

                OriginPhoto originPhoto = OriginPhoto.valueOf(origin);
                storageImageServices.writeImage(bytes, nameLogo, originPhoto);
                personaService.updateFotoByJugador(nameLogo, id);


                result.put("result", "success");
                result.put("pathfilename", pathWebService.getValidPathWebFoto(nameLogo, OriginPhoto.valueOf(origin)));
                result.put("filename", nameLogo);
            } catch (Exception e) {
                result.put("result", "fail");
            }
        } else {
            result.put("result", "empty");
        }

        return result;
    }

    @RequestMapping(value = "/delete/foto", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> removeFileFoto(@RequestParam("key") Long idJugador,
                                              @RequestParam("logo") String foto, @RequestParam("origin") String origin)
            throws IOException {
        Map<String, String> result = new HashMap<>();

        OriginPhoto originPhoto = OriginPhoto.valueOf(origin);
        storageImageServices.deleteImage(foto, originPhoto);
        personaService.updateFotoByJugador("", idJugador);

        result.put("result", "success");
        result.put("defaultname", PathPhoto.JUGADOR_DEFAULT.getPath());
        return result;
    }

    private String getValidNameLogo(String path, Long idEquipo) {
        String extension=path.substring(path.lastIndexOf("."));
        return idEquipo + extension;
    }
}
