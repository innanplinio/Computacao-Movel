package br.ufop.innan.mymoneyinnan;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView etSoma;
    public static double exibeSoma;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedResources.getInstance()
                .loadDespesas(this);
        SharedResources.getInstance()
                .loadReceitas(this);
        etSoma = findViewById(R.id.textSoma);
        etSoma.setVisibility(View.INVISIBLE);
        completeText();
    }


    @Override
    protected void onResume(){
        super.onResume();
        completeText();
    }

    public void completeText(){
        exibeSoma=0;
        exibeSoma = SharedResources.getInstance().calculaReceita() + SharedResources.getInstance().calculaDespesa();
        if(exibeSoma<0){
            etSoma.setTextColor(Color.rgb(255,0,0));
        }else if(exibeSoma>0){
            etSoma.setTextColor(Color.rgb(0,255,0));
        }else{
            etSoma.setTextColor(Color.rgb(0,0,255));
        }
        etSoma.setVisibility(View.VISIBLE);
        etSoma.setText(SharedResources.getInstance().convert((exibeSoma)));
    }

    public void NewDespesa(View view) {
        Intent it = new Intent(this,
                DespesaActivity.class);
        startActivity(it);
    }

    public void NewReceita(View view) {
        Intent it = new Intent(this,
                ReceitaActivity.class);
        startActivity(it);
    }

}
