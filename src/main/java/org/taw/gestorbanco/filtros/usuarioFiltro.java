package org.taw.gestorbanco.filtros;

public class usuarioFiltro {
    private String nombre;
    private String apellido;


    public usuarioFiltro() {
        this.nombre = null;
        this.apellido = null;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
