package examen.justo.diego.dao;
import examen.justo.diego.beans.InformeForense;
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

public class InformeForenseDAOImpl extends AbstractDAO<InformeForense> {
    private static final String SQL_INSERT = "INSERT INTO INFORMES_FORENSES (adnPositivo, nivelRiesgo, conclusion) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE INFORMES_FORENSES SET adnPositivo = ?, nivelRiesgo = ?, conclusion = ? WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM INFORMES_FORENSES WHERE id = ?";
    private static final String SQL_FIND = "SELECT * FROM INFORMES_FORENSES WHERE id = ?";
    private static final String SQL_FIND_ALL = "SELECT * FROM INFORMES_FORENSES ORDER BY id";
    private static final String SQL_FIND_BY_ADN_POSITIVO = "SELECT * FROM INFORMES_FORENSES WHERE adnPositivo = ?";
    private static final String SQL_FIND_BY_NIVEL_RIESGO = "SELECT * FROM INFORMES_FORENSES WHERE nivelRiesgo = ?";

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
            motorSQL.getPs().setString(1, informeForense.getAdnPositivo());
            motorSQL.getPs().setString(2, informeForense.getNivelRiesgo());
            motorSQL.getPs().setString(3, informeForense.getConclusion());

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
            motorSQL.getPs().setString(1, informeForense.getAdnPositivo());
            motorSQL.getPs().setString(2, informeForense.getNivelRiesgo());
            motorSQL.getPs().setString(3, informeForense.getConclusion());
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
        InformeForense informeForense = null;
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_FIND);
            motorSQL.getPs().setInt(1, id);
            ResultSet rs = motorSQL.executeQuery();
            if (rs.next()) {
                informeForense = mapInformeForense(rs);
            }
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
        return informeForense;
    }

    @Override
    public ArrayList<InformeForense> findAll() {
        ArrayList<InformeForense> lstInformesForenses = new ArrayList<>();
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_FIND_ALL);
            ResultSet rs = motorSQL.executeQuery();
            while (rs.next()) {
                lstInformesForenses.add(mapInformeForense(rs));
            }
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
        return lstInformesForenses;
    }

    /*
     * =========================
     * CONSULTAS AVANZADAS
     * =========================
     */


    public ArrayList<InformeForense> findByAdnPositivo(String adnPositivo) {
        ArrayList<InformeForense> lstInformesForenses = new ArrayList<>();
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_FIND_BY_ADN_POSITIVO);
            motorSQL.getPs().setString(1, adnPositivo);
            ResultSet rs = motorSQL.executeQuery();
            while (rs.next()) {
                lstInformesForenses.add(mapInformeForense(rs));
            }
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
        return lstInformesForenses;
    }

    public ArrayList<InformeForense> findByNivelRiesgo(String nivelRiesgo) {
        ArrayList<InformeForense> lstInformesForenses = new ArrayList<>();
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_FIND_BY_NIVEL_RIESGO);
            motorSQL.getPs().setString(1, nivelRiesgo);
            ResultSet rs = motorSQL.executeQuery();
            while (rs.next()) {
                lstInformesForenses.add(mapInformeForense(rs));
            }
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
        return lstInformesForenses;
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
