package examen.justo.diego.dao;
import examen.justo.diego.beans.MuestraForense;
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

public class MuestraForenseDAOImpl extends AbstractDAO<MuestraForense> {
    private static final String SQL_INSERT = "INSERT INTO MUESTRAS_FORENSES (codigoCaso, tipoMuestra, fechaRecogida, estadoCustodia) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE MUESTRAS_FORENSES SET codigoCaso = ?, tipoMuestra = ?, fechaRecogida = ?, estadoCustodia = ? WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM MUESTRAS_FORENSES WHERE id = ?";
    private static final String SQL_FIND = "SELECT * FROM MUESTRAS_FORENSES WHERE id = ?";
    private static final String SQL_FIND_ALL = "SELECT * FROM MUESTRAS_FORENSES ORDER BY id";
    private static final String SQL_FIND_BY_CODIGO_CASO = "SELECT * FROM MUESTRAS_FORENSES WHERE codigoCaso = ?";
    private static final String SQL_FIND_BY_TIPO_MUESTRA = "SELECT * FROM MUESTRAS_FORENSES WHERE tipoMuestra = ?";

    public MuestraForenseDAOImpl(MotorSQL motorSQL) {
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
    public void add(MuestraForense muestraForense) {
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_INSERT);
            motorSQL.getPs().setString(1, muestraForense.getCodigoCaso());
            motorSQL.getPs().setString(2, muestraForense.getTipoMuestra());
            motorSQL.getPs().setString(3, muestraForense.getFechaRecogida());
            motorSQL.getPs().setString(4, muestraForense.getEstadoCustodia());

            int rows = motorSQL.executeUpdate();
            System.out.println("INSERTADOS: " + rows);
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
    }

    @Override
    public void update(int id, MuestraForense muestraForense) {
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_UPDATE);
            motorSQL.getPs().setString(1, muestraForense.getCodigoCaso());
            motorSQL.getPs().setString(2, muestraForense.getTipoMuestra());
            motorSQL.getPs().setString(3, muestraForense.getFechaRecogida());
            motorSQL.getPs().setString(4, muestraForense.getEstadoCustodia());
            motorSQL.getPs().setInt(5, id);
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
    public MuestraForense find(int id) {
        MuestraForense muestraForense = null;
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_FIND);
            motorSQL.getPs().setInt(1, id);
            ResultSet rs = motorSQL.executeQuery();
            if (rs.next()) {
                muestraForense = mapMuestraForense(rs);
            }
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
        return muestraForense;
    }

    @Override
    public ArrayList<MuestraForense> findAll() {
        ArrayList<MuestraForense> lstMuestrasForenses = new ArrayList<>();
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_FIND_ALL);
            ResultSet rs = motorSQL.executeQuery();
            while (rs.next()) {
                lstMuestrasForenses.add(mapMuestraForense(rs));
            }
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
        return lstMuestrasForenses;
    }

    /*
     * =========================
     * CONSULTAS AVANZADAS
     * =========================
     */


    public ArrayList<MuestraForense> findByCodigoCaso(String codigoCaso) {
        ArrayList<MuestraForense> lstMuestrasForenses = new ArrayList<>();
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_FIND_BY_CODIGO_CASO);
            motorSQL.getPs().setString(1, codigoCaso);
            ResultSet rs = motorSQL.executeQuery();
            while (rs.next()) {
                lstMuestrasForenses.add(mapMuestraForense(rs));
            }
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
        return lstMuestrasForenses;
    }

    public ArrayList<MuestraForense> findByTipoMuestra(String tipoMuestra) {
        ArrayList<MuestraForense> lstMuestrasForenses = new ArrayList<>();
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_FIND_BY_TIPO_MUESTRA);
            motorSQL.getPs().setString(1, tipoMuestra);
            ResultSet rs = motorSQL.executeQuery();
            while (rs.next()) {
                lstMuestrasForenses.add(mapMuestraForense(rs));
            }
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
        return lstMuestrasForenses;
    }

    private MuestraForense mapMuestraForense(ResultSet rs) throws Exception {
        MuestraForense muestraForense = new MuestraForense();
        muestraForense.setId(rs.getInt("id"));
        muestraForense.setCodigoCaso(rs.getString("codigoCaso"));
        muestraForense.setTipoMuestra(rs.getString("tipoMuestra"));
        muestraForense.setFechaRecogida(rs.getString("fechaRecogida"));
        muestraForense.setEstadoCustodia(rs.getString("estadoCustodia"));
        return muestraForense;
    }
}
