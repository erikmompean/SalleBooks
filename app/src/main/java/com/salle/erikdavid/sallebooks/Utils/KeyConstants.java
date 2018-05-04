package com.salle.erikdavid.sallebooks.Utils;

/**
 * Clase llena de constantes.
 */
public class KeyConstants {
    /**
     * Constante para recuperar el email
     */
    public static final String EMAIL = "saved_email";
    public static final int USERNAME = 0;
    public static final int PASSWORD = 1;

    /**
     * String que se usa para diversos "codigos" relacionados con la base de datos
     * y los libros.
     */
    public static final String BOOK_BUNDLE = "BOOK";

    /**
     * Constante que se usa para pasar el id entre intents
     */
    public static final String BOOK_BUNDLE_ID = "BOOK_ID";

    /**
     * Constante con el nombre de la base de datos.
     */
    public static final String MINIDATABASE_NAME = "books_db";

    /**
     * Constante con la url para llamar a la API de Google Books
     */
    public static final String GOOGLE_BOOKS_URL = "https://www.googleapis.com/books/v1/volumes?q=";

}
