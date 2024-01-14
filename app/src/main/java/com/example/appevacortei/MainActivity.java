package com.example.appevacortei;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.appevacortei.Adaptador.Turistico_adaptador;
import com.example.appevacortei.Listeners.LlenarCB;
import com.example.appevacortei.Modelos.CB_seleccionar;
import com.example.appevacortei.Modelos.TuristicosQuevedo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import WebServices.Asynchtask;
import WebServices.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask, AdapterView.OnItemSelectedListener {
    Spinner cbCate, cbSubcate;
    RecyclerView rvListaLugares;
    Map<String, String> datos = new HashMap<String, String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cbCate=findViewById(R.id.spnCat);
        cbSubcate=findViewById(R.id.spnSubcat);
        rvListaLugares = findViewById(R.id.rcvLista);
        cbCate.setOnItemSelectedListener(this);
        cbSubcate.setOnItemSelectedListener(this);

        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService(
                "https://uealecpeterson.net/turismo/categoria/getlistadoCB", datos,
                MainActivity.this, new LlenarCB(cbCate, "id", "descripcion", true));
        ws.execute("GET");


      /*  WebService ws2= new WebService(
                "https://uealecpeterson.net/turismo/subcategoria/getlistadoCB/2", datos,
                MainActivity.this, new LlenarCB(cbSubcate, "id", "descripcion"));
        ws2.execute("GET");*/


        rvListaLugares.setHasFixedSize(true);
        rvListaLugares.setLayoutManager(new LinearLayoutManager(this));


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        int IDCat=0, IDSubCat=0;
        int IDItemSeleccionado = ((CB_seleccionar)parent.getItemAtPosition(position)).getId();
        if(parent == cbCate) {
            if (IDItemSeleccionado>0){
                IDCat = IDItemSeleccionado;
                WebService ws2= new WebService(
                        "https://uealecpeterson.net/turismo/subcategoria/getlistadoCB/" + id,
                        datos, MainActivity.this,
                        new LlenarCB(cbSubcate, "id", "descripcion", true) );
                ws2.execute("GET");
            }

        }else if (parent == cbSubcate){
            if (cbCate.getSelectedItemPosition()!=AdapterView.INVALID_POSITION){
                IDCat = ((CB_seleccionar)cbCate.getSelectedItem()).getId();
                IDSubCat = IDItemSeleccionado;
            }
        }

        WebService ws3 = new WebService(
                "https://uealecpeterson.net/turismo/lugar_turistico/json_getlistadoGridLT/" +
                        IDCat + "/" + IDSubCat,
                datos, MainActivity.this, MainActivity.this);
        ws3.execute("GET");

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void processFinish(String result) throws JSONException {

        ArrayList<TuristicosQuevedo> lstLugares = new ArrayList<TuristicosQuevedo> ();
        try {
            JSONObject JSONlista = new JSONObject(result);
            JSONArray JSONlistaUsuarios= JSONlista.getJSONArray("data");
            lstLugares = TuristicosQuevedo.JsonObjectsBuild(JSONlistaUsuarios);

            Turistico_adaptador adapatorLugaresT = new Turistico_adaptador(this, lstLugares);
            rvListaLugares.setAdapter(adapatorLugaresT);
        }
        catch (JSONException e)
        {
            Toast.makeText(this.getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG);
        }

    }
}