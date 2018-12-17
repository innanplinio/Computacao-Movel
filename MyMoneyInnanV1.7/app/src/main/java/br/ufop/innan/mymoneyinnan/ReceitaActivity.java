package br.ufop.innan.mymoneyinnan;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ReceitaActivity extends AppCompatActivity {

    private ListView lvStudents;
    private TextView tvEmptyList;
    private TextView receitaText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receita);
        lvStudents = findViewById(R.id.lvStudents);
        tvEmptyList = findViewById(R.id.tvEmptyList);
        receitaText = findViewById(R.id.receitaText);
        receitaText.setTextColor(Color.rgb(0,255,0));
    }

    @Override
    protected void onResume() {
        super.onResume();
        receitaText.setText(SharedResources.getInstance().convert((SharedResources.getInstance().calculaReceita())));
        lvStudents.setAdapter(new ReceitaAdapter(this,
                SharedResources.getInstance().getReceitas()));
        lvStudents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(
                        getApplicationContext(),
                        EditReceita.class);
                it.putExtra("position", position);
                startActivity(it);
            }
        });
        if(SharedResources.getInstance().getReceitas().isEmpty()) {
            tvEmptyList.setVisibility(View.VISIBLE);
        } else
            tvEmptyList.setVisibility(View.INVISIBLE);
    }

    public void addReceita(View view) {
        Intent it = new Intent(this,
                CadastroReceita.class);
        startActivity(it);
    }
}
