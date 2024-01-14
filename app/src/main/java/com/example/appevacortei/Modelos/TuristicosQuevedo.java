package com.example.appevacortei.Modelos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TuristicosQuevedo {
    String Nombre, Direccion, Telfono, URlLogo;

    public TuristicosQuevedo(JSONObject a) throws JSONException {
        Nombre = a.getString("nombre_lugar").toString() ;
        Direccion = a.getString("direccion").toString() ;
        Telfono = a.getString("telefono").toString() ;
        URlLogo = "https://uealecpeterson.net/turismo/assets/imgs/logos_gifs/" + a.getString("logo").toString() ;
    }
    public static ArrayList<TuristicosQuevedo> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<TuristicosQuevedo> Listalugares = new ArrayList<>();
        for (int i = 0; i < datos.length(); i++) {
            Listalugares.add(new TuristicosQuevedo(datos.getJSONObject(i)));
        }
        return Listalugares;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getTelfono() {
        return Telfono;
    }

    public void setTelfono(String telfono) {
        Telfono = telfono;
    }

    public String getURLlogo() {
        return URlLogo;
    }

    public void setURLlogo(String URLlogo) {
        this.URlLogo = URlLogo;
    }
}
