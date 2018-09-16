package com.example.coopermind.ex09;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import ex10.com.example.coopermind.ex10.Carro;

public class IncluirActivity extends Activity {

    private final String TAG = "INCLUIR_CARRO";

    private CompoundButton ckbGasolina;
    private CompoundButton ckbEtanol;

    private Spinner spnMarca;
    private RadioGroup rgOpcoes;
    private EditText edtModelo;
    private EditText edtAno;

    private final String CARRO_OBJ = "carro";
    private final String MODELO = "modelo";
    private final String FABRICANTE = "fabricante";
    private final String ANO = "ano";
    private final String SELECTED_RADIO = "radio";
    private final String CHB_GASOLINA = "checkbox_gasolina";
    private final String CHB_ALCOOL = "checkbox_alcool";

    private Carro carro;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_incluir);
        obterReferencias();
        configurarSpinner();
        Log.d(TAG, "Preparando valores");
        if(savedInstanceState==null){
            configurarDefaults();
            Log.d(TAG, "Carregados valores padrões");
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(MODELO, edtModelo.getText().toString());
        outState.putBoolean(CHB_GASOLINA, ckbGasolina.isChecked());
        outState.putBoolean(CHB_ALCOOL, ckbEtanol.isChecked());
        outState.putString(ANO, edtAno.getText().toString());
        outState.putInt(FABRICANTE, spnMarca.getSelectedItemPosition());
        outState.putInt(SELECTED_RADIO, rgOpcoes.getCheckedRadioButtonId());
        if(carro!= null){
            outState.putSerializable(CARRO_OBJ, carro);
        }
    }



    @Override
    protected void onRestoreInstanceState(Bundle inState){
        super.onRestoreInstanceState(inState);
        edtModelo.setText(inState.getString(MODELO, ""));
        edtAno.setText(inState.getString(ANO, ""));
        spnMarca.setSelection(inState.getInt(FABRICANTE, 0));
        ckbGasolina.setSelected(inState.getBoolean(CHB_GASOLINA));
        ckbEtanol.setSelected(inState.getBoolean(CHB_ALCOOL));
        RadioButton radio = findViewById(inState.getInt(SELECTED_RADIO));
        if(radio!= null)
            radio.setSelected(true);

        carro = (Carro) inState.getSerializable(CARRO_OBJ);

        Log.d(TAG, "Carregados valores de savedInstanceState");
    }


    private void configurarDefaults(){
        spnMarca.setSelection(0);
        ckbGasolina.setChecked(false);
        ckbEtanol.setChecked(false);

    }
    private void configurarSpinner() {
        String nomes[] = Carro.Fabricante.getMarcas();
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, nomes);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnMarca.setAdapter(adaptador);
    }



    private void obterReferencias() {
        edtModelo = findViewById(R.id.edtModelo);
        edtAno = findViewById(R.id.edtAno);
        spnMarca = findViewById(R.id.spinner);
        ckbGasolina = findViewById(R.id.ckbGasolina);
        ckbEtanol = findViewById(R.id.ckbEtanol);
        rgOpcoes = findViewById(R.id.rdGroup);

    }

    public void btnEnviarPressionado(View v){

        boolean isGas = ckbGasolina.isChecked();
        boolean isEta = ckbEtanol.isChecked();
        String gasolina = isGas? "G" : "";
        String etanol = isEta? "E" : "";
        String combustivel = gasolina+etanol;

        String modelo = "Modelo: " + edtModelo.getText().toString();
        String ano_fab = "Ano: " + edtAno.getText().toString();

        String marca = spnMarca.getSelectedItem().toString();

        int idRadioSelecionado = rgOpcoes.getCheckedRadioButtonId();
        RadioButton _radio = findViewById(idRadioSelecionado);
        String opcao_novo_usado = "Condição: "+((_radio!=null)? _radio.getText() : "Não Informado");
        String formato = "%s\n%s\n%s\n%s\n%s";
        String mensagem = String.format(formato,
                                            modelo, marca,
                                            ano_fab, combustivel,
                                            opcao_novo_usado);

        carro = new Carro(
                edtModelo.getText().toString(),
                Integer.valueOf(edtAno.getText().toString()),
                spnMarca.getSelectedItemPosition(),
                isGas,
                isEta);

        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
        Log.d(TAG, "Incluindo carro:"+mensagem);
        Intent _resultado = new Intent();
        setResult(Activity.RESULT_OK, _resultado);
        _resultado.putExtra(CARRO_OBJ, carro);
        finish();
    }

}
