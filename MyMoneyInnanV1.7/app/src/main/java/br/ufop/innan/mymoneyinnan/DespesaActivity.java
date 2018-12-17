package br.ufop.innan.mymoneyinnan;

import android.content.Intent;
import android.graphics.Color;
import android.icu.util.IslamicCalendar;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class DespesaActivity extends AppCompatActivity {


    private ListView lvStudents;
    private TextView tvEmptyList;
    private TextView despesaText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesa);
        lvStudents = findViewById(R.id.lvStudents);
        tvEmptyList = findViewById(R.id.tvEmptyList);
        despesaText = findViewById(R.id.despesaText);
        despesaText.setTextColor(Color.rgb(255,0,0));
    }

    @Override
    protected void onResume() {
        super.onResume();
        despesaText.setText(SharedResources.getInstance().convert(SharedResources.getInstance().calculaDespesa()));
        lvStudents.setAdapter(new DespesaAdapter(this,
                SharedResources.getInstance().getDespesas()));
        lvStudents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(
                        getApplicationContext(),
                        EditDespesa.class);
                it.putExtra("position", position);
                startActivity(it);
            }
        });
        if(SharedResources.getInstance().getDespesas().isEmpty()) {
            tvEmptyList.setVisibility(View.VISIBLE);
        } else
            tvEmptyList.setVisibility(View.INVISIBLE);
    }

    public void addDespesa(View view) {
        Intent it = new Intent(this,
                CadastroDespesa.class);
        startActivity(it);
    }
}
