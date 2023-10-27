package com.backend.parcial.service;

import com.backend.parcial.dao.IDao;
import com.backend.parcial.model.Odontologo;

import java.util.List;

public class OdontologoService {
    private IDao<Odontologo> odontologoIDao;

    public OdontologoService(IDao<Odontologo> odontologoIDao) {
        this.odontologoIDao = odontologoIDao;
    }

    public Odontologo guardar(Odontologo odontologo) {
        return odontologoIDao.guardar(odontologo);
    }

    public List<Odontologo> ListarTodos() {
        return odontologoIDao.listarTodos();
    }

}
