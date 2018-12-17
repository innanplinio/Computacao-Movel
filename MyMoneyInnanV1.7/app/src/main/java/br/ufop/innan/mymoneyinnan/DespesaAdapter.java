package br.ufop.innan.mymoneyinnan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DespesaAdapter extends BaseAdapter {

    private Context contextD;

    private ArrayList<Despesa> despesas;

    public DespesaAdapter(Context contextD, ArrayList<Despesa> despesas) {
        this.contextD = contextD;
        this.despesas = despesas;
    }

    @Override
    public int getCount() {
        return despesas.size();
    }

    @Override
    public Object getItem(int position) {
        return despesas.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Despesa despesa = despesas.get(position);
        LayoutInflater inflater = (LayoutInflater)
                contextD.getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(
                R.layout.despesa_receita, null);

        //Update layout elements according to the receita's info
        TextView tvValor = v.findViewById(R.id.valor);
        tvValor.setText("Valor: " + SharedResources.getInstance().convert(despesa.getValor()));

        TextView tvName = v.findViewById(R.id.name);
        tvName.setText("Nome: " + despesa.getName());

        TextView tvOrigem = v.findViewById(R.id.origem);
        tvOrigem.setText("Origem: " + despesa.getOrigem());

        TextView tvData = v.findViewById(R.id.data);
        tvData.setText("Data :" + despesa.getData());

        return v;
    }
}






