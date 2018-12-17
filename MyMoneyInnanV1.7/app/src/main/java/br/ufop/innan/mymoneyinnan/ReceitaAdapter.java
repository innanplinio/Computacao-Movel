package br.ufop.innan.mymoneyinnan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ReceitaAdapter extends BaseAdapter {

    private Context contextR;

    private ArrayList<Receita> receitas;

    public ReceitaAdapter(Context contextR, ArrayList<Receita> receitas) {
        this.contextR = contextR;
        this.receitas = receitas;
    }

    @Override
    public int getCount() {
        return receitas.size();
    }

    @Override
    public Object getItem(int position) {
        return receitas.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Receita receita = receitas.get(position);
        LayoutInflater inflater = (LayoutInflater)
                contextR.getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(
                R.layout.despesa_receita, null);

        //Update layout elements according to the receita's info
        TextView tvValor = v.findViewById(R.id.valor);
        tvValor.setText("Valor: " + SharedResources.getInstance().convert(receita.getValor()));

        TextView tvName = v.findViewById(R.id.name);
        tvName.setText("Nome: " + receita.getName());

        TextView tvOrigem = v.findViewById(R.id.origem);
        tvOrigem.setText("Origem: " + receita.getOrigem());

        TextView tvData = v.findViewById(R.id.data);
        tvData.setText("Data :" + String.valueOf(receita.getData()));

        return v;
    }
}






