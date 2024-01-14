package com.example.appevacortei.Listeners;

import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.appevacortei.Modelos.CB_seleccionar;
import com.example.appevacortei.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import WebServices.Asynchtask;

public class LlenarCB implements Asynchtask {

    Spinner cmb;
    String cmpId, cmpDesc;

    Boolean addAllItem;

    public LlenarCB(Spinner cmb, String cmpId, String cmpDesc, Boolean addAllItem) {
        this.cmb = cmb;
        this.cmpId = cmpId;
        this.cmpDesc = cmpDesc;
        this.addAllItem = addAllItem;
    }

    @Override
    public void processFinish(String result) throws JSONException {
        ArrayList<CB_seleccionar> datos = new ArrayList<CB_seleccionar>();
        if(addAllItem) datos.add( new CB_seleccionar(0, "Seleccione una opción"));
        JSONArray JSONlista =  new JSONArray(result);
        for(int i=0; i< JSONlista.length();i++)
        {
            JSONObject lugares= JSONlista.getJSONObject(i);
            datos.add( new CB_seleccionar(
                    lugares.getInt(cmpId),
                    lugares.getString(cmpDesc)));
        }


        ArrayAdapter<CB_seleccionar> adaptador = new ArrayAdapter<CB_seleccionar>
                (cmb.getContext(),android.R.layout.simple_spinner_item, datos);

        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cmb.setAdapter(adaptador);
    }
}
