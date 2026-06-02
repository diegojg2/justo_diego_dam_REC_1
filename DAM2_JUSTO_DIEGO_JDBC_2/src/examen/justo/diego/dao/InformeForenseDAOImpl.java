package examen.justo.diego.dao;
import examen.justo.diego.beans.InformeForense;
import examen.justo.diego.motores.MotorSQL;

import java.sql.ResultSet;
import java.util.ArrayList;

public class InformeForenseDAOImpl extends AbstractDAO<InformeForense> {
    private static final String SQL_INSERT = "INSERT INTO INFORMES_FORENSES (nombre, pais, nivelSeguridad) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE INFORMES_FORENSES SET nombre = ?, pais = ?, nivelSeguridad = ? WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM INFORMES_FORENSES WHERE id = ?";
    private static final String SQL_FIND = "SELECT * FROM INFORMES_FORENSES WHERE id = ?";
    private static final String SQL_FIND_ALL = "SELECT * FROM INFORMES_FORENSES ORDER BY id";
    private static final String SQL_FIND_BY_NOMBRE = "SELECT * FROM INFORMES_FORENSES WHERE nombre = ?";
    private static final String SQL_FIND_BY_PAIS = "SELECT * FROM INFORMES_FORENSES WHERE pais = ?";

    public InformeForenseDAOImpl(MotorSQL motorSQL) {
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
    public void add(InformeForense informeForense) {
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
    public void update(int id, InformeForense informeForense) {
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
    public InformeForense find(int id) {
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
    public ArrayList<InformeForense> findAll() {
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


    public ArrayList<InformeForense> findByNombre(String nombre) {
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

    public ArrayList<InformeForense> findBypais(String pais) {
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

    private InformeForense mapInformeForense(ResultSet rs) throws Exception {
        InformeForense informeForense = new InformeForense();
        informeForense.setId(rs.getInt("id"));
        informeForense.setAdnPositivo(rs.getString("adnPositivo"));
        informeForense.setNivelRiesgo(rs.getString("nivelRiesgo"));
        informeForense.setConclusion(rs.getString("conclusion"));
        return informeForense;
    }
}
