public class Thunderbolt {
    private int codigo;
    private String nombre;
    private String habilidadPrincipal;
    private int nivelRedencion;
    private String misionAsignada;

    public Thunderbolt(int codigo, String nombre, String habilidadPrincipal, int nivelRedencion, String misionAsignada) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.habilidadPrincipal = habilidadPrincipal;
        this.nivelRedencion = nivelRedencion;
        this.misionAsignada = misionAsignada;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getHabilidadPrincipal() {
        return habilidadPrincipal;
    }

    public int getNivelRedencion() {
        return nivelRedencion;
    }

    public String getMisionAsignada() {
        return misionAsignada;
    }
}

