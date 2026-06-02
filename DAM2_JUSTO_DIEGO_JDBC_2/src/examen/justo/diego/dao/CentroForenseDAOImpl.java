package examen.justo.diego.dao;
import examen.justo.diego.beans.CentroForense;
import examen.justo.diego.motores.MotorSQL;

import java.sql.ResultSet;
import java.util.ArrayList;

/*
=========================================
AUTOR: DIEGO JUSTO GARCIA
GRUPO: DAM2
EXAMEN JDBC AWS RDS
FECHA: 02/06/2026
=========================================
*/

public class CentroForenseDAOImpl extends AbstractDAO<CentroForense> {
    private static final String SQL_INSERT = "INSERT INTO CENTROS_FORENSES (nombre, pais, nivelSeguridad) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE CENTROS_FORENSES SET nombre = ?, pais = ?, nivelSeguridad = ? WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM CENTROS_FORENSES WHERE id = ?";
    private static final String SQL_FIND = "SELECT * FROM CENTROS_FORENSES WHERE id = ?";
    private static final String SQL_FIND_ALL = "SELECT * FROM CENTROS_FORENSES ORDER BY id";
    private static final String SQL_FIND_BY_NOMBRE = "SELECT * FROM CENTROS_FORENSES WHERE nombre = ?";
    private static final String SQL_FIND_BY_PAIS = "SELECT * FROM CENTROS_FORENSES WHERE pais = ?";

    public CentroForenseDAOImpl(MotorSQL motorSQL) {
        super(motorSQL);
    }

    public void check() {
        try {
            motorSQL.connect();
            if (motorSQL.conn != null && !motorSQL.conn.isClosed()) {
                System.out.println("CONEXION OK");
            }
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
    }

    @Override
    public void add(CentroForense centroForense) {
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_INSERT);
            motorSQL.getPs().setString(1, centroForense.getNombre());
            motorSQL.getPs().setString(2, centroForense.getPais());
            motorSQL.getPs().setString(3, centroForense.getNivelSeguridad());

            int rows = motorSQL.executeUpdate();
            System.out.println("INSERTADOS: " + rows);
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
    }

    @Override
    public void update(int id, CentroForense centroForense) {
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_UPDATE);
            motorSQL.getPs().setString(1, centroForense.getNombre());
            motorSQL.getPs().setString(2, centroForense.getPais());
            motorSQL.getPs().setString(3, centroForense.getNivelSeguridad());
            motorSQL.getPs().setInt(4, id);
            int rows = motorSQL.executeUpdate();
            System.out.println("ACTUALIZADOS: " + rows);
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
    }

    @Override
    public void delete(int id) {
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_DELETE);
            motorSQL.getPs().setInt(1, id);
            int rows = motorSQL.executeUpdate();
            System.out.println("BORRADOS: " + rows);
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
    }

    @Override
    public CentroForense find(int id) {
        CentroForense centroForense = null;
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_FIND);
            motorSQL.getPs().setInt(1, id);
            ResultSet rs = motorSQL.executeQuery();
            if (rs.next()) {
                centroForense = mapCentroForense(rs);
            }
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
        return centroForense;
    }

    @Override
    public ArrayList<CentroForense> findAll() {
        ArrayList<CentroForense> lstCentrosForenses = new ArrayList<>();
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_FIND_ALL);
            ResultSet rs = motorSQL.executeQuery();
            while (rs.next()) {
                lstCentrosForenses.add(mapCentroForense(rs));
            }
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
        return lstCentrosForenses;
    }

    /*
     * =========================
     * CONSULTAS AVANZADAS
     * =========================
     */


    public ArrayList<CentroForense> findByNombre(String nombre) {
        ArrayList<CentroForense> lstCentrosForenses = new ArrayList<>();
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_FIND_BY_NOMBRE);
            motorSQL.getPs().setString(1, nombre);
            ResultSet rs = motorSQL.executeQuery();
            while (rs.next()) {
                lstCentrosForenses.add(mapCentroForense(rs));
            }
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
        return lstCentrosForenses;
    }

    public ArrayList<CentroForense> findByPais(String pais) {
        ArrayList<CentroForense> lstCentrosForenses = new ArrayList<>();
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_FIND_BY_PAIS);
            motorSQL.getPs().setString(1, pais);
            ResultSet rs = motorSQL.executeQuery();
            while (rs.next()) {
                lstCentrosForenses.add(mapCentroForense(rs));
            }
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
        return lstCentrosForenses;
    }

    private CentroForense mapCentroForense(ResultSet rs) throws Exception {
        CentroForense centroForense = new CentroForense();
        centroForense.setId(rs.getInt("id"));
        centroForense.setNombre(rs.getString("nombre"));
        centroForense.setPais(rs.getString("pais"));
        centroForense.setNivelSeguridad(rs.getString("nivelSeguridad"));
        return centroForense;
    }
}
