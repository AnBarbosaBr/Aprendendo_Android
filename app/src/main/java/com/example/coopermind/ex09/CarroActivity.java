package com.example.coopermind.ex09;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ex10.com.example.coopermind.ex10.Carro;
import ex10.com.example.coopermind.ex10.CarrosAdapter;

public class CarroActivity extends Activity {
    List<Carro> carros;
    CarrosAdapter adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        listView = new ListView(this);
        setContentView(listView);
        Intent _intentRecebido = getIntent();
        if(_intentRecebido==null){
            carros = listaDeTeste();
        } else {
            carros = (ArrayList<Carro>) _intentRecebido.getSerializableExtra("carros");

            if(carros.size()==0) {
                carros = listaDeTeste();
            }
        }

        adapter = new CarrosAdapter(this, carros);
        listView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent _resultado = new Intent();
        setResult(Activity.RESULT_OK, _resultado);
        _resultado.putExtra("carros", new ArrayList<Carro> (carros));
        finish();
    }

    private List<Carro> listaDeTeste() {
        List<Carro> _carros= new ArrayList<>();
        _carros.add(new Carro("Celta", 2010, Carro.Fabricante.GM.getId(), true, false));
        _carros.add(new Carro("Uno", 2012, Carro.Fabricante.FIAT.getId(), true, true));
        _carros.add(new Carro("Fiesta", 2009, Carro.Fabricante.FORD.getId(), false, true));
        _carros.add(new Carro("Gol", 2014, Carro.Fabricante.VW.getId(), true, true));
        return _carros;
    }
}
