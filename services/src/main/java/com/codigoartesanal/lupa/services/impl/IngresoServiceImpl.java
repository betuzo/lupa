package com.codigoartesanal.lupa.services.impl;

import com.codigoartesanal.lupa.exception.DeleteException;
import com.codigoartesanal.lupa.model.*;
import com.codigoartesanal.lupa.repositories.IngresoRepository;
import com.codigoartesanal.lupa.repositories.PersonaRepository;
import com.codigoartesanal.lupa.repositories.ValidarIngresoTokenRepository;
import com.codigoartesanal.lupa.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.codigoartesanal.lupa.model.TipoTokenIngreso.*;

/**
 * Created by betuzo on 26/02/16.
 */
@Service
public class IngresoServiceImpl implements IngresoService {

    @Autowired
    IngresoRepository ingresoRepository;

    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    ValidarIngresoTokenRepository validarIngresoTokenRepository;

    @Autowired
    PathWebService pathWebService;

    @Autowired
    MailService mailService;

    @Override
    public Map<String, Object> createIngreso(Map<String, String> ingresoMap, User user) {
        ingresoMap.put(PROPERTY_ENABLED, StatusIngreso.REGISTRADA.name());
        ingresoMap.put(PROPERTY_FECHA_REGISTRO, String.valueOf((new Date()).getTime()));
        Ingreso ingreso = convertMapToIngreso(ingresoMap);
        ingreso.setRecaudador(personaRepository.findByUsername(user.getUsername()));
        ingreso.setDonador(personaRepository.findOne(ingreso.getDonador().getId()));
        ingreso = ingresoRepository.save(ingreso);
        sendTokenForValidation(ingreso, ingresoMap.get(GeneralService.PROPERTY_CONTEXT));
        return convertIngresoToMap(ingreso);
    }

    @Override
    public List<Map<String, Object>> listIngresos() {
        Iterator<Ingreso> itIngreso = ingresoRepository.findAll().iterator();
        List<Map<String, Object>> copy = new ArrayList<>();
        while (itIngreso.hasNext()) {
            Ingreso ingreso = itIngreso.next();
            Map<String, Object> dto = convertIngresoToMap(ingreso);
            copy.add(dto);
        }
        return copy;
    }

    @Override
    public Map<String, Object> findIngresoById(Long idIngreso) {
        return convertIngresoToMap(ingresoRepository.findOne(idIngreso));
    }

    @Override
    public void deleteIngreso(Long idIngreso) {
        Ingreso ingreso = ingresoRepository.findOne(idIngreso);
        if (ingreso.getStatus() == StatusIngreso.REGISTRADA) {
            validarIngresoTokenRepository.delete(validarIngresoTokenRepository.findAllByIngreso(ingreso));
            ingresoRepository.delete(idIngreso);
        } else {
            throw new DeleteException("El ingreso ya esta validado, contacte al supervisor");
        }
    }

    @Override
    public void updateFichaPagoByIngreso(String fichaPago, Long idIngreso) {
        ingresoRepository.updateFichaPagoByIdIngreso(fichaPago, idIngreso);
    }

    @Override
    public void updateStatusByIngreso(StatusIngreso statusIngreso, Long idIngreso) {
        ingresoRepository.updateStatusByIngreso(statusIngreso, idIngreso);
    }

    private void sendTokenForValidation(Ingreso ingreso, String context){
        validarIngresoTokenRepository.delete(validarIngresoTokenRepository.findAllByIngreso(ingreso));
        ValidarIngresoToken validarIngresoToken = validarIngresoTokenRepository.save(generateValidarIngresoToken(ingreso));
        sendMailsToken(validarIngresoToken, context, VALID_INGRESO_DONADOR);
        validarIngresoToken = validarIngresoTokenRepository.save(generateValidarIngresoToken(ingreso));
        sendMailsToken(validarIngresoToken, context, VALID_INGRESO_VALIDADOR);
    }

    private ValidarIngresoToken generateValidarIngresoToken(Ingreso ingreso){
        ValidarIngresoToken validarIngresoToken = new ValidarIngresoToken();
        validarIngresoToken.setIngreso(ingreso);
        Calendar fechaVigencia = Calendar.getInstance();
        fechaVigencia.add(Calendar.DAY_OF_MONTH, PROPERTY_TOKEN_VIGENCIA_DAYS);
        validarIngresoToken.setFechaVigencia(fechaVigencia.getTime());
        validarIngresoToken.setToken(UUID.randomUUID().toString());

        return validarIngresoToken;
    }

    private Map<String, Object> convertIngresoToMap(Ingreso ingreso) {
        Map<String, Object> map = new HashMap<>();
        map.put(PROPERTY_ID, ingreso.getId());
        map.put(PROPERTY_DONADOR_ID, ingreso.getDonador().getId());
        if (TipoVisibilidad.PUBLICA == ingreso.getVisibilidad()) {
            map.put(PROPERTY_DONADOR_NOMBRE, ingreso.getDonador().getNombreCompleto());
        } else {
            map.put(PROPERTY_DONADOR_NOMBRE, TipoVisibilidad.ANONIMA.getDescription());
        }
        map.put(PROPERTY_RECAUDADOR_ID, ingreso.getRecaudador().getId());
        map.put(PROPERTY_RECAUDADOR_NOMBRE, ingreso.getRecaudador().getNombreCompleto());
        map.put(PROPERTY_MONTO, ingreso.getMonto());
        map.put(PROPERTY_COMENTARIO, ingreso.getComentario());
        map.put(PROPERTY_FICHA_PAGO, ingreso.getFichaPago());
        String pathWebFull = pathWebService.getValidPathWebFoto(ingreso.getFichaPago(), OriginPhoto.INGRESO);
        map.put(PROPERTY_RUTA_FICHA_PAGO, pathWebFull);
        map.put(PROPERTY_HAS_FICHA_PAGO, !pathWebFull.contains(OriginPhoto.INGRESO.getPathDefault()));
        map.put(PROPERTY_FECHA_REGISTRO, ingreso.getFechaRegistro());
        map.put(PROPERTY_VISIBILIDAD, ingreso.getVisibilidad());
        map.put(PROPERTY_VISIBILIDAD_DES, ingreso.getVisibilidad().getDescription());
        map.put(PROPERTY_ENABLED, ingreso.getStatus());
        map.put(PROPERTY_ENABLED_DES, ingreso.getStatus().getDescription());

        return map;
    }

    private Ingreso convertMapToIngreso(Map<String, String> ingresoMap) {
        Ingreso ingreso = new Ingreso();
        if (ingresoMap.containsKey(PROPERTY_ID)) {
            ingreso = this.get(Long.valueOf(ingresoMap.get(PROPERTY_ID)));
        }
        Persona donador = new Persona();
        donador.setId(Long.valueOf(ingresoMap.get(PROPERTY_DONADOR_ID)));
        ingreso.setDonador(donador);
        ingreso.setMonto(Double.parseDouble(ingresoMap.get(PROPERTY_MONTO)));
        ingreso.setComentario(ingresoMap.get(PROPERTY_COMENTARIO));
        ingreso.setVisibilidad(TipoVisibilidad.valueOf(ingresoMap.get(PROPERTY_VISIBILIDAD)));
        ingreso.setStatus(StatusIngreso.valueOf(ingresoMap.get(PROPERTY_ENABLED)));
        ingreso.setFechaRegistro(new Date(Long.valueOf(ingresoMap.get(PROPERTY_FECHA_REGISTRO))));

        return ingreso;
    }

    private Ingreso get(Long idIngreso){
        return this.ingresoRepository.findOne(idIngreso);
    }

    private void sendMailsToken(ValidarIngresoToken validarIngresoToken, String context, TipoTokenIngreso tipo) {
        Map<String, Object> props = new HashMap<>();
        props.put("folio", validarIngresoToken.getIngreso().getId());
        props.put("link", context + "/#token/ingreso/" + validarIngresoToken.getToken());

        if (VALID_INGRESO_DONADOR == tipo) {
            mailService.sendValidTokenIngresoToDonador(validarIngresoToken.getIngreso().getDonador().getUser(), props);
        } else {
            mailService.sendValidTokenIngresoByRole("VALIDADOR", props);
        }
    }
}
