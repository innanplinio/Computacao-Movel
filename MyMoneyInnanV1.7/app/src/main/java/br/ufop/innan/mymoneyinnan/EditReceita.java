package br.ufop.innan.mymoneyinnan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;

public class EditReceita extends AppCompatActivity {

    private Receita receita;
    private int position;
    private EditText etValor;
    private EditText etName;
    private EditText etOrigem;
    private EditText etData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_receita);
        Intent it = getIntent();
        position = it.getIntExtra("position",
                0);
        receita = SharedResources.getInstance()
                .getReceitas().get(position);
        etValor = findViewById(R.id.editValor);
        etName = findViewById(R.id.editName);
        etOrigem = findViewById(R.id.editOrigem);
        etData = findViewById(R.id.editData);
        if(receita != null) {
            //Fill text fields
            etValor.setText(String.valueOf(receita.getValor()));
            etName.setText(receita.getName());
            etOrigem.setText(receita.getOrigem());
            etData.setText(receita.getData());
            etData.addTextChangedListener(SharedResources.getInstance().insert("##/##/####", etData));
        }
    }


    public void confirmEdit(View view) {
        //Update receita's info
        if (!etValor.getText().toString().equals("")) {
            if (Double.parseDouble(etValor.getText().toString()) > 0) {
                if (SharedResources.getInstance().verificaData(etData.getText().toString())) {
                    receita.setValor(Double.parseDouble(etValor.getText().toString()));
                    receita.setName(etName.getText().toString());
                    receita.setOrigem(etOrigem.getText().toString());
                    receita.setData(etData.getText().toString());
                    SharedResources.getInstance().getReceitas()
                            .set(position, receita);
                    SharedResources.getInstance()
                            .saveReceitas(this);
                    Toast.makeText(this, "Receita editada com sucesso!", Toast.LENGTH_SHORT).show();
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
        SharedResources.getInstance().getReceitas()
                .remove(position);
        SharedResources.getInstance()
                .saveReceitas(this);
        Toast.makeText(this, "Receita removida com sucesso", Toast.LENGTH_SHORT).show();
        finish();
    }
}

