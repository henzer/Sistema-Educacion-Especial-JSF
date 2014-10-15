/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;

/**
 *
 * @author DiegoRenato
 */
public class Contenido implements Serializable{
    private String nombre;
    private String dirSonido;
    private String dirImagen;

    public Contenido(String nombre, String dirSonido, String dirImagen) {
        this.nombre = nombre;
        this.dirSonido = dirSonido;
        this.dirImagen = dirImagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDirSonido() {
        return dirSonido;
    }

    public void setDirSonido(String dirSonido) {
        this.dirSonido = dirSonido;
    }

    public String getDirImagen() {
        return dirImagen;
    }

    public void setDirImagen(String dirImagen) {
        this.dirImagen = dirImagen;
    }
    
}
