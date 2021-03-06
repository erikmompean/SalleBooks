package com.salle.erikdavid.sallebooks.Utils;

import android.util.Patterns;

/**
 * Clase que agrupa un conjunto de metodos para suplementar a {@link com.salle.erikdavid.sallebooks.LoginActivity}
 * esta clase se comento originalmente en ingles.
 * @author Erik & David
 */
public class LoginUtils {

    /**
     * Validates the email
     * @param email to validate
     * @return if validation successful true, if not, false
     */
    public static boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    /**
     * Validates the password
     * @param password to validate
     * @return if validation successful true, if not, false
     */
    public static boolean isPasswordValid(String password) {
        return password.length() > 4;
    }
}
