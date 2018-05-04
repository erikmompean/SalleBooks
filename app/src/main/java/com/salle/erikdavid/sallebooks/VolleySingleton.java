package com.salle.erikdavid.sallebooks;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Esta clase se encarga de manejar las llamadas a las APIs
 * @author Erik & David
 */
public class VolleySingleton {

    /**
     * La variable con la que interactuaremos para hacer llamadas a la API
     */
    private static VolleySingleton singleton;
    private RequestQueue requestQueue;
    private static Context mContext;

    private VolleySingleton(Context context) {
        mContext = context;
        requestQueue = getRequestQueue();

    }

    /**
     * Nos da la instancia con la que interactuaremos.
     * @param context para crear el singleton.
     * @return instancia de volley
     */
    public static synchronized VolleySingleton getInstance(Context context) {
        if (singleton == null) {
            singleton = new VolleySingleton(context);
        }
        return singleton;
    }

    /**
     * Crea una request si no existe y te la devuelve.
     * @return request
     */
    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return requestQueue;
    }

    /**
     * Añade una reqyest a la cola.
     * @param req la llamada que quieres añadir.
     */
    public void addToRequestQueue(Request req) {
        req.setRetryPolicy(new DefaultRetryPolicy(0,
                0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        getRequestQueue().add(req);
    }
}
