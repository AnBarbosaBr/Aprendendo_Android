package com.example.coopermind.ex09;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
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
        carros= new ArrayList<>();
        carros.add(new Carro("Celta", 2010, Carro.Fabricante.GM.getId(), true, false));
        carros.add(new Carro("Uno", 2012, Carro.Fabricante.FIAT.getId(), true, true));
        carros.add(new Carro("Fiesta", 2009, Carro.Fabricante.FORD.getId(), false, true));
        carros.add(new Carro("Gol", 2014, Carro.Fabricante.VW.getId(), true, true));

        adapter = new CarrosAdapter(this, carros);
        listView.setAdapter(adapter);
    }
}
