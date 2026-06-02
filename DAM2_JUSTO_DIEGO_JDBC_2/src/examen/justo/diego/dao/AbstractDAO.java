package examen.justo.diego.dao;
import examen.justo.diego.motores.MotorSQL;

/*
=========================================
AUTOR: DIEGO JUSTO GARCIA
GRUPO: DAM2
EXAMEN JDBC AWS RDS
FECHA: 02/06/2026
=========================================
*/

public abstract class AbstractDAO<T> implements DAO<T> {
    protected MotorSQL motorSQL;
    public AbstractDAO(MotorSQL motorSQL) {
        this.motorSQL = motorSQL;
    }
    protected void printError(Exception e) {
        System.out.println("[ERROR] " + e.getMessage());
    }
}
