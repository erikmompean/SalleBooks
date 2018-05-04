package com.salle.erikdavid.sallebooks.Models;

/**
 * Clase modelo del usuario.
 * @author Erik & David.
 */
public class User {
    /**
     * Email del usuario
     */
    private String email = "";

    /**
     * Nombre del usuario
     */
    private String name;

    /**
     * Password del usuario.
     */
    private String password;

    public User(String email, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    /**
     * Obtener el email
     * @return email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece email
     * @param email email a establecer
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtener el nombre.
     * @return nombre
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre
     * @param name nombre a establecer
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtener la contraseña
     * @return la contraseña
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña
     * @param password la contraseña a establecer.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
