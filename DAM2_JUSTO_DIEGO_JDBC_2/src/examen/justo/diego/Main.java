package examen.justo.diego;

import examen.justo.diego.beans.MuestraForense;
import examen.justo.diego.dao.MuestraForenseDAOImpl;
import examen.justo.diego.motores.MotorFactory;

import java.util.ArrayList;

/*
=========================================
AUTOR: DIEGO JUSTO GARCIA
GRUPO: DAM2
EXAMEN JDBC AWS RDS
FECHA: 02/06/2026
=========================================
*/

public class Main {
    public static void main(String[] args) {

        MuestraForenseDAOImpl muestraForenseDAO = new MuestraForenseDAOImpl(MotorFactory.create(MotorFactory.POSTGRE));

        //1. ADD muestra forense
        MuestraForense muestraForense1 = new MuestraForense();
        muestraForense1.setCodigoCaso("A922");
        muestraForense1.setTipoMuestra("Pelo");
        muestraForense1.setFechaRecogida("2026-04-11");
        muestraForense1.setEstadoCustodia("Controlado");
        muestraForenseDAO.add(muestraForense1);

        //2. UPDATE muestra forense
        MuestraForense muestraForense2 = new MuestraForense();
        muestraForense2.setCodigoCaso("A117");
        muestraForense2.setTipoMuestra("Sangre");
        muestraForense2.setFechaRecogida("2026-01-11");
        muestraForense2.setEstadoCustodia("Controlado");
        muestraForenseDAO.update(5, muestraForense2);

        //3. FIND muestra forense
        System.out.println(muestraForenseDAO.find(2));

        //4. FIND ALL muestra forense
        ArrayList<MuestraForense> lstMuestrasForenses = muestraForenseDAO.findAll();
        for (MuestraForense muestraForense:lstMuestrasForenses){
            System.out.println(muestraForense.toString());
        }

        //5. FIND MUESTRAS FORENSES BY CENTRO


        //6. FIND MUESTRA WITH INFORME

    }
}
