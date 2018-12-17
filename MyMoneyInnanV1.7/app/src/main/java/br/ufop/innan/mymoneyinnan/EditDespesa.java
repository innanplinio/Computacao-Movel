package br.ufop.innan.mymoneyinnan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;

public class EditDespesa extends AppCompatActivity {

    private Despesa despesa;
    private int position;
    private EditText etValor;
    private EditText etName;
    private EditText etOrigem;
    private EditText etData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_despesa);
        Intent it = getIntent();
        position = it.getIntExtra("position",
                0);
        despesa = SharedResources.getInstance()
                .getDespesas().get(position);
        etValor = findViewById(R.id.editValor);
        etName = findViewById(R.id.editName);
        etOrigem = findViewById(R.id.editOrigem);
        etData = findViewById(R.id.editData);
        if(despesa != null) {
            //Fill text fields
            etValor.setText((String.valueOf(-1*despesa.getValor())));
            etName.setText(despesa.getName());
            etOrigem.setText(despesa.getOrigem());
            etData.setText(despesa.getData());
            etData.addTextChangedListener(SharedResources.getInstance().insert("##/##/####", etData));
        }
    }


    public void confirmEdit(View view) {
        //Update despesa's info
        if (!etValor.getText().toString().equals("")) {
            if (Double.parseDouble(etValor.getText().toString()) > 0) {
                if (SharedResources.getInstance().verificaData(etData.getText().toString())) {
                    despesa.setValor(-1*Double.parseDouble(etValor.getText().toString()));
                    despesa.setName(etName.getText().toString());
                    despesa.setOrigem(etOrigem.getText().toString());
                    despesa.setData(etData.getText().toString());
                    SharedResources.getInstance().getDespesas()
                            .set(position, despesa);
                    SharedResources.getInstance()
                            .saveDespesas(this);
                    Toast.makeText(this, "Despesa editada com sucesso!", Toast.LENGTH_SHORT).show();
                    finish();
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
        }else {
            Toast.makeText(this, "Valor incorreto!", Toast.LENGTH_SHORT).show();
            etValor.setText("");
            etValor.requestFocus();
        }
    }


    public void deleteEdit(View view) {
        SharedResources.getInstance().getDespesas()
                .remove(position);
        SharedResources.getInstance()
                .saveDespesas(this);
        Toast.makeText(this, "Despesa removida com sucesso!", Toast.LENGTH_SHORT).show();
        finish();
    }
}

