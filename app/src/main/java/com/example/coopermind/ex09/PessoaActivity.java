package com.example.coopermind.ex09;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class PessoaActivity extends Activity {

    private CheckBox ckbHabilitar;
    private SeekBar skbIdade;
    private TextView txtIdade;
    private Spinner spinner;
    private RadioGroup rgOpcoes;
    private EditText edtNome;

    private final String NOME = "nome";
    private final String SPINNER = "spinner";
    private final String SELECTED_RADIO = "radio";
    private final String CHECK_BOX = "checkbox";
    private final String SEEK_BAR = "seekbar";
    private final String TAG = "TESTE";
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_pessoa);
        obterReferencias();
        configurarSeekBar();
        configurarSpinner();
        Log.d(TAG, "Preparando valores");
        if(savedInstanceState==null){
            configurarDefaults();
            Log.d(TAG, "Carregados valores padrões");
        } else {
            spinner.setSelection(savedInstanceState.getInt(SPINNER, 0));
            RadioButton radio = (RadioButton) findViewById(savedInstanceState.getInt(SELECTED_RADIO));
            if(radio!= null) radio.setSelected(true);
            edtNome.setText(savedInstanceState.getString(NOME, ""));
            ckbHabilitar.setSelected(savedInstanceState.getBoolean(CHECK_BOX));
            skbIdade.setProgress(savedInstanceState.getInt(SEEK_BAR, 18));
            Log.d(TAG, "Carregados valores de savedInstanceState");
        }


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(NOME, edtNome.getText().toString());
        outState.putBoolean(CHECK_BOX, ckbHabilitar.isChecked());
        outState.putInt(SEEK_BAR, skbIdade.getProgress());
        outState.putInt(SPINNER, spinner.getSelectedItemPosition());
        outState.putInt(SELECTED_RADIO, rgOpcoes.getCheckedRadioButtonId());
    }


    private void configurarDefaults(){
        spinner.setSelection(0);
        skbIdade.setProgress(20);
        ckbHabilitar.setChecked(false);
    }
    private void configurarSpinner() {
        String nomes[] = {"Spin 0", "Spin 1","Spin 2","Spin 3"};
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, nomes);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adaptador);
    }

    private void configurarSeekBar() {
        skbIdade.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean fromUser) {
                txtIdade.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void obterReferencias() {
        skbIdade = (SeekBar) findViewById(R.id.skbIdade);
        txtIdade = (TextView) findViewById(R.id.txtIdade);
        spinner = (Spinner) findViewById(R.id.spinner);
        ckbHabilitar = (CheckBox) findViewById(R.id.ckbHabilitar);
        rgOpcoes = (RadioGroup) findViewById(R.id.rdGroup);
        edtNome = (EditText) findViewById(R.id.edtNome);
    }

    public void btnEnviarPressionado(View v){

        String habilitado = ckbHabilitar.isChecked()? "Habilitado" : "Desabilitado";
        String idade = "Idade: " + skbIdade.getProgress();
        String nome = "Nome: " + edtNome.getText().toString();

        String spin = spinner.getSelectedItem().toString();

        int idRadioSelecionado = rgOpcoes.getCheckedRadioButtonId();
        RadioButton radio = (RadioButton) findViewById(idRadioSelecionado);
        String opcao = "Opção Selecionada: "+((radio!=null)? radio.getText() : "Nenhuma");
        String mensagem = String.format("%s\n%s\n%s\n%s\n%s", habilitado, nome, idade, spin, opcao);
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();

    }

}
