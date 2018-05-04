package com.salle.erikdavid.sallebooks;

import com.salle.erikdavid.sallebooks.Models.User;

/**
 * Clase que se encarga de crear el usuario que usara la aplicacion.
 * @author Erik & David
 */
public class UserSingleton {
    /**
     * Instancia del usuario
     */
    private static User instance;

    /**
     * Crear una instancia nueva
     * @param email email del usuario a crear.
     * @param password password del usuario a crear.
     */
    public static void createInstance(String email, String password){
        instance = new User(email, password);
    }

    /**
     * Obtener la intancia del usuario.
     * @return
     */
    public static User getInstance() {
        return instance;
    }

    /**
     * Elimina la instancia del usuario.
     */
    public static void closeInstance() {
        instance = null;
    }
}
