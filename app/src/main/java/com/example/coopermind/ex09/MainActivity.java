package com.example.coopermind.ex09;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import ex10.com.example.coopermind.ex10.Carro;

public class MainActivity extends Activity {
    private final String TAG = "MAIN ACTIVITY: ";
    private List<Carro> carros;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
            loadSavedInstanceState(savedInstanceState);
        } else {
            loadDefaultValues();
        }
        setContentView(R.layout.activity_main);
    }

    private void loadDefaultValues() {
        Log.d(TAG, "Carregando activity com dados novos. Nenhum estado anterior localizado.");
        carros = new ArrayList<>();
    }

    private void loadSavedInstanceState(@NonNull Bundle savedInstanceState) {
        Log.d(TAG, "Carregando estado anterior da activity.");
        //TODO: Ler carros ao recuperar a tela.
    }

    public void goToCarrosActivity(View view) {
        Log.d(TAG, "goToCarrosActivity: Chamando CarrosActivity.");
        startActivity(new Intent(this, CarroActivity.class));
    }

    public void goToPessoaActivity(View view) {
        Log.d(TAG, "goToPessoaActivity: Chamando PessoaActivity.");
        startActivity(new Intent(this, PessoaActivity.class));
    }
}
