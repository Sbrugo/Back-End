package com.backend.parcial.test;

import com.backend.parcial.dao.impl.OdontologoDaoH2;
import com.backend.parcial.dao.impl.OdontologoDaoMemoria;
import com.backend.parcial.model.Odontologo;
import com.backend.parcial.service.OdontologoService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;

import java.sql.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class OdontologoServiceTest {
    private OdontologoService odontologoService;

    @BeforeAll
    static void doBefore() {
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/parcial;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    @Test
    public void deberiaAgregarUnPaciente_conH2(){
        odontologoService = new OdontologoService(new OdontologoDaoH2());
        Odontologo odontologoAGuardar = new Odontologo(12345, "nombre2", "apellido2");
        Odontologo odontologoGuardado = odontologoService.guardar(odontologoAGuardar);
        assertNotNull(odontologoGuardado.getId());
    }



    @Test
    @Disabled
    public void alTenerUnOdontologo_laListaDeberiaSerDistintaDeCero() {
        odontologoService = new OdontologoService(new OdontologoDaoMemoria(new ArrayList<>()));
        Odontologo odontologoAGuardar = new Odontologo(4564654, "nombre", "apellido");

        Odontologo odontologoGuardado = odontologoService.guardar(odontologoAGuardar);

        assertTrue(odontologoGuardado.getId() != 0);
    }

    @Test
    @Disabled
    public void deberiaRetornarUnaListaNoVacia() {
    odontologoService = new OdontologoService(new OdontologoDaoMemoria(new ArrayList<>()));
    Odontologo odontologo1 = new Odontologo(1234567, "Sofia", "Apellido");
    Odontologo odontologo2 = new Odontologo(9187654, "nombre", "apellido");
    Odontologo odontologoGuardado1 = odontologoService.guardar(odontologo1);
    Odontologo odontologoGuardado2 = odontologoService.guardar(odontologo2);

     assertFalse(odontologoService.ListarTodos().isEmpty());
    }
}
