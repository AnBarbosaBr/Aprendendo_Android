package com.example.coopermind.ex09;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
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

        carros =  (ArrayList<Carro>) savedInstanceState.getSerializable("carros");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putSerializable("carros", new ArrayList(carros));
    }

    public void goToCarrosActivity(View view) {
        Log.d(TAG, "goToCarrosActivity: Chamando CarrosActivity.");

        Intent _intent = new Intent(this, CarroActivity.class);
        _intent.putExtra("carros", new ArrayList(carros));
        startActivityForResult(_intent, 2);

    }

    public void goToPessoaActivity(View view) {
        Log.d(TAG, "goToPessoaActivity: Chamando IncluirActivity.");
        Intent _intent = new Intent(this, IncluirActivity.class);

        startActivityForResult(_intent, 1);
    }

    @Override
    protected void onActivityResult(int aRequestCode, int aResultCode, Intent aData){
        switch(aRequestCode) {
            case 1:
                Log.d(TAG, "Incluindo novo carro.");
                Carro carro = (Carro) aData.getSerializableExtra("carro");
                carros.add(carro);
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Toast.makeText(this, "Adicionando veiculo " + carro, Toast.LENGTH_SHORT);
                break;
            case 2:
                Log.d(TAG, "Retornando da lista de carros.");
                carros = (ArrayList<Carro>)aData.getSerializableExtra("carros");
                break;
        }
    }
}
