package com.backend.parcial.dao.impl;

import com.backend.parcial.dao.IDao;
import com.backend.parcial.model.Odontologo;
import org.apache.log4j.Logger;

import java.util.List;

public class OdontologoDaoMemoria implements IDao<Odontologo> {
    private final Logger LOGGER = Logger.getLogger(OdontologoDaoMemoria.class);
    private List<Odontologo> odontologosLista;

    public OdontologoDaoMemoria(List<Odontologo> odontologosLista) {
        this.odontologosLista = odontologosLista;
    }

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        int id = odontologosLista.size() + 1;
        Odontologo odontologoGuardado = new Odontologo(id, odontologo.getNumeroDeMatricula(), odontologo.getNombre(), odontologo.getApellido());
        odontologosLista.add(odontologoGuardado);
        LOGGER.info("Odontólogo guardado: " + odontologoGuardado);
        return odontologoGuardado;
    }

    @Override
    public List<Odontologo> listarTodos() {
        LOGGER.info("Lista de Odontólogos : " + odontologosLista);
        return odontologosLista;
    }
}