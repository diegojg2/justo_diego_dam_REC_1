package examen.justo.diego.beans;

public class MuestraForense {
    private int id;
    private String codigoCaso;
    private String tipoMuestra;
    private String fechaRecogida;
    private String estadoCustodia;
    private CentroForense centroForense;
    private InformeForense informeForense;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigoCaso() {
        return codigoCaso;
    }

    public void setCodigoCaso(String codigoCaso) {
        this.codigoCaso = codigoCaso;
    }

    public String getTipoMuestra() {
        return tipoMuestra;
    }

    public void setTipoMuestra(String tipoMuestra) {
        this.tipoMuestra = tipoMuestra;
    }

    public String getFechaRecogida() {
        return fechaRecogida;
    }

    public void setFechaRecogida(String fechaRecogida) {
        this.fechaRecogida = fechaRecogida;
    }

    public String getEstadoCustodia() {
        return estadoCustodia;
    }

    public void setEstadoCustodia(String estadoCustodia) {
        this.estadoCustodia = estadoCustodia;
    }

    public CentroForense getCentroForense() {
        return centroForense;
    }

    public void setCentroForense(CentroForense centroForense) {
        this.centroForense = centroForense;
    }

    public InformeForense getInformeForense() {
        return informeForense;
    }

    public void setInformeForense(InformeForense informeForense) {
        this.informeForense = informeForense;
    }

    @Override
    public String toString() {
        return "InformeForense{" +
                "id=" + id +
                ", codigoCaso='" + codigoCaso + '\'' +
                ", tipoMuestra='" + tipoMuestra + '\'' +
                ", fechaRecogida='" + fechaRecogida + '\'' +
                ", estadoCustodia='" + estadoCustodia + '\'' +
                ", centroForense=" + centroForense +
                ", informeForense=" + informeForense +
                '}';
    }
}
