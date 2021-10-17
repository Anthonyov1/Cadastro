package com.example.cadastro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class FormularioActivity extends AppCompatActivity {

    private EditText etNome;
    private EditText etSobrenome;
    private EditText etIdade;
    private Spinner spEstados;
    private Button btnRegistrar;
    private String acao;
    private Cadastro cadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        etNome = findViewById(R.id.etNome);
        etSobrenome = findViewById(R.id.etSobrenome);
        etIdade = findViewById(R.id.etIdade);
        spEstados = findViewById(R.id.spEstado);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        acao = getIntent().getStringExtra("acao");
        if(acao.equals("editar") ){
            carregarFormulario();
        }

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });
    }

    private void carregarFormulario(){
        int id = getIntent().getIntExtra("idCadastro", 0);
        cadastro = CadastroP.getCadastrosPessoasById(this, id);
        etNome.setText(cadastro.getNome() );
        etSobrenome.setText(cadastro.getSobrenome());
        etIdade.setText(cadastro.getIdade());

        String[] estados = getResources().getStringArray(R.array.estados);
        for(int i = 1; i  < estados.length; i++){
            if (cadastro.getEstados().equals( estados[i] )){
                spEstados.setSelection(i);
                break;

            }
        }
    }

    private void salvar(){
        String nome = etNome.getText().toString();
        String sobrenome = etSobrenome.getText().toString();
        String idade = etIdade.getText().toString();

        if(nome.isEmpty() || spEstados.getSelectedItemPosition() == 0 || sobrenome.isEmpty() || idade.isEmpty()){
            Toast.makeText(this, "Você deve preencher todos os campos!", Toast.LENGTH_LONG).show();
        }else{
            if(acao.equals("inserir")){
                cadastro = new Cadastro();
            }

            cadastro.setNome( nome );
            cadastro.setSobrenome( sobrenome );
            cadastro.setIdade( idade );
            cadastro.setEstados(spEstados.getSelectedItem().toString() );

            if(acao.equals("inserir")) {
                CadastroP.inserir(this, cadastro);
                etNome.setText("");
                etSobrenome.setText("");
                etIdade.setText("");
                spEstados.setSelection(0, true);
            }else{
                CadastroP.editar(this, cadastro);
                finish();
            }

        }

    }

}
