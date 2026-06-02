package examen.justo.diego.beans;

public class CentroForense {
    private int id;
    private String nombre;
    private String pais;
    private String nivelSeguridad;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getNivelSeguridad() {
        return nivelSeguridad;
    }

    public void setNivelSeguridad(String nivelSeguridad) {
        this.nivelSeguridad = nivelSeguridad;
    }

    @Override
    public String toString() {
        return "CentroForense{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", pais='" + pais + '\'' +
                ", nivelSeguridad='" + nivelSeguridad + '\'' +
                '}';
    }
}
