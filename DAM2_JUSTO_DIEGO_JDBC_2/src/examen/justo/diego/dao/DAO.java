package examen.justo.diego.dao;
import java.util.ArrayList;

/*
=========================================
AUTOR: DIEGO JUSTO GARCIA
GRUPO: DAM2
EXAMEN JDBC AWS RDS
FECHA: 02/06/2026
=========================================
*/

public interface DAO<T> {
    void add(T object);
    void update(int id, T object);
    void delete(int id);
    T find(int id);
    ArrayList<T> findAll();
}