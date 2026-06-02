package examen.justo.diego.beans;

/*
=========================================
AUTOR: DIEGO JUSTO GARCIA
GRUPO: DAM2
EXAMEN JDBC AWS RDS
FECHA: 02/06/2026
=========================================
*/

public class InformeForense {
    private int id;
    private String adnPositivo;
    private String nivelRiesgo;
    private String conclusion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdnPositivo() {
        return adnPositivo;
    }

    public void setAdnPositivo(String adnPositivo) {
        this.adnPositivo = adnPositivo;
    }

    public String getNivelRiesgo() {
        return nivelRiesgo;
    }

    public void setNivelRiesgo(String nivelRiesgo) {
        this.nivelRiesgo = nivelRiesgo;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    @Override
    public String toString() {
        return "InformeForense{" +
                "id=" + id +
                ", adnPositivo='" + adnPositivo + '\'' +
                ", nivelRiesgo='" + nivelRiesgo + '\'' +
                ", conclusion='" + conclusion + '\'' +
                '}';
    }
}
