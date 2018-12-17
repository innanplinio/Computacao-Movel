package br.ufop.innan.mymoneyinnan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;

public class CadastroReceita extends AppCompatActivity {

    private EditText etValor;
    private EditText etName;
    private EditText etOrigem;
    private EditText etData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_receita);
        etValor = findViewById(R.id.addValor);
        etName = findViewById(R.id.addName);
        etOrigem = findViewById(R.id.addOrigem);
        etData = findViewById(R.id.addData);
        etData.addTextChangedListener(SharedResources.getInstance().insert("##/##/####", etData));
    }

    public void confirmAdd(View view) {
        if (!etValor.getText().toString().equals("")) {
            if (Double.parseDouble(etValor.getText().toString()) > 0) {
                if (SharedResources.getInstance().verificaData(etData.getText().toString())) {
                    double valor = Double.parseDouble(etValor.getText().toString());
                    String name = etName.getText().toString();
                    String origem = etOrigem.getText().toString();
                    String data = etData.getText().toString();
                    //Create student object
                    Receita s = new Receita(valor, name, origem, data);
                    //Add on singleton list
                    SharedResources.getInstance()
                            .getReceitas().add(s);
                    SharedResources.getInstance()
                            .saveReceitas(this);
                    Toast.makeText(this, "Receita adicionada com sucesso!", Toast.LENGTH_SHORT).show();
                    //Reset text fields
                    etValor.setText("");
                    etName.setText("");
                    etOrigem.setText("");
                    etData.setText("");
                    etValor.requestFocus();
                } else {
                    Toast.makeText(this, "Data incorreta!", Toast.LENGTH_SHORT).show();
                    etData.setText("");
                    etData.requestFocus();
                }
            } else {
                Toast.makeText(this, "Valor incorreto!", Toast.LENGTH_SHORT).show();
                etValor.setText("");
                etValor.requestFocus();
            }
        }else{
            Toast.makeText(this, "Valor incorreto!", Toast.LENGTH_SHORT).show();
            etValor.setText("");
            etValor.requestFocus();
        }

    }
}


