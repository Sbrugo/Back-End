package com.backend.parcial.dao.impl;

import com.backend.parcial.dao.H2Connection;
import com.backend.parcial.dao.IDao;
import com.backend.parcial.model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class OdontologoDaoH2 implements IDao<Odontologo> {

    private final Logger LOGGER = Logger.getLogger(OdontologoDaoH2.class);
    private Connection connection;
    private PreparedStatement preparedStatement;

    private static final String INSERT = "INSERT INTO ODONTOLOGOS (NUMERODEMATRICULA, NOMBRE, APELLIDO) VALUES(?, ?, ?);";
    private static final String SELECT = "SELECT * FROM ODONTOLOGOS;";

    public static final String CREATE = "CREATE TABLE ODONTOLOGOS (ID INT AUTO_INCREMENT PRIMARY KEY, NUMERODEMATRICULA INT NOT NULL, NOMBRE INT NOT NULL, APELLIDO VARCHAR(50) NOT NULL);";

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        connection = null;
        preparedStatement = null;
        Odontologo odontologoGuardado = null;
        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(CREATE);
            preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, odontologo.getNumeroDeMatricula());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3, odontologo.getApellido());
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            odontologoGuardado = new Odontologo(odontologo.getNumeroDeMatricula(), odontologo.getNombre(), odontologo.getApellido());
            while (resultSet.next()){
                odontologoGuardado.setId(resultSet.getInt("id"));
            }
            connection.commit();
            LOGGER.info("Se ha guardado el domicilio: " + odontologoGuardado);
        }  catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                    LOGGER.info("Tuvimos un problema");
                    LOGGER.error(e.getMessage());
                    e.printStackTrace();
                } catch (SQLException exception) {
                    LOGGER.error(exception.getMessage());
                    exception.printStackTrace();
                }
            }
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("No se pudo cerrar la conexion: " + ex.getMessage());
            }
        }

        return odontologoGuardado;
    }

    @Override
    public List<Odontologo> listarTodos() {
        connection = null;
        List<Odontologo> odontologos = new ArrayList<>();

        try{
            connection = H2Connection.getConnection();

            preparedStatement = connection.prepareStatement(SELECT);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Odontologo odontologo = crearObjetoOdontologo(resultSet);
                odontologos.add(odontologo);
            }
            LOGGER.info("Listado de odontologos obtenido: " + odontologos);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("Ha ocurrido un error al intentar cerrar la bdd. " + ex.getMessage());
                ex.printStackTrace();
            }
        }

        return odontologos;
    }

    private Odontologo crearObjetoOdontologo(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int numeroDeMatricula = rs.getInt("numeroDeMatricula");
        String nombre = rs.getString("nombre");
        String apellido = rs.getString("apellido");

        return new Odontologo(id, numeroDeMatricula, nombre, apellido);
    }
}
