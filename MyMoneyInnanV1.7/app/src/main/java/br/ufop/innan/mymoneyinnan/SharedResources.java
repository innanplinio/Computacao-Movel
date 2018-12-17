package br.ufop.innan.mymoneyinnan;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class SharedResources {

    private static SharedResources shared = null;

    //Singleton elements
    private static ArrayList<Receita> receitas;
    private static ArrayList<Despesa> despesas;

    NumberFormat currency = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

    public static SharedResources getInstance() {
        if(shared == null) {
            shared = new SharedResources();
        }
        return shared;
    }

    private SharedResources() {
        receitas = new ArrayList<Receita>();
        despesas = new ArrayList<Despesa>();
    }

    //Converter Double para mascara de string para
    public String convert(double v){
        String valor = currency.format(v);
        return valor;
    }

    //Verificar se data está dentro do padrão
    public boolean verificaData(String data) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            sdf.parse(data);
            return true;
        } catch (ParseException ex) {
            return false;
        }
    }

    // Load/Save Receitas/Despesas
    public void saveReceitas(Context context) {
        try {
            FileOutputStream fos = context
                    .openFileOutput("Receitas.dat",
                            Context.MODE_PRIVATE);
            ObjectOutputStream oos = new
                    ObjectOutputStream(fos);
            oos.writeObject(receitas);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void loadReceitas(Context context) {
        try {
            FileInputStream fis = context
                    .openFileInput("Receitas.dat");
            ObjectInputStream ois =
                    new ObjectInputStream(fis);
            receitas = (ArrayList<Receita>)
                    ois.readObject();
            ois.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void saveDespesas(Context context) {
        try {
            FileOutputStream fos = context
                    .openFileOutput("Despesas.dat",
                            Context.MODE_PRIVATE);
            ObjectOutputStream oos = new
                    ObjectOutputStream(fos);
            oos.writeObject(despesas);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void loadDespesas(Context context) {
        try {
            FileInputStream fis = context
                    .openFileInput("Despesas.dat");
            ObjectInputStream ois =
                    new ObjectInputStream(fis);
            despesas = (ArrayList<Despesa>)
                    ois.readObject();
            ois.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    //Somatório dos dos valores de Despesa/Receita
    public double calculaDespesa(){
        double somaDespesas = 0;
        despesas = SharedResources.getInstance().getDespesas();
        for(int i = 0; i<despesas.size();i++){
            somaDespesas = somaDespesas +  despesas.get(i).getValor();
        }
        return somaDespesas;
    }
    public double calculaReceita(){
        double somaReceitas = 0;
        receitas = SharedResources.getInstance().getReceitas();
        for(int i = 0; i<receitas.size();i++) {
            somaReceitas = somaReceitas + receitas.get(i).getValor();
        }
        return somaReceitas;
    }



    public ArrayList<Receita> getReceitas() {
        return receitas;
    }
    public ArrayList<Despesa> getDespesas() {
        return despesas;
    }

    //Cria mascara para entrada de DATA
    public static TextWatcher insert(final String mask, final EditText et) {
        return new TextWatcher() {
            boolean isUpdating;
            String oldTxt = "";
            public void onTextChanged(
                    CharSequence s, int start, int before,int count) {
                String str = unmask(s.toString());
                String maskCurrent = "";
                if (isUpdating) {
                    oldTxt = str;
                    isUpdating = false;
                    return;
                }
                int i = 0;
                for (char m : mask.toCharArray()) {
                    if (m != '#' && str.length() > oldTxt.length()) {
                        maskCurrent += m;
                        continue;
                    }
                    try {
                        maskCurrent += str.charAt(i);
                    } catch (Exception e) {
                        break;
                    }
                    i++;
                }
                isUpdating = true;
                et.setText(maskCurrent);
                et.setSelection(maskCurrent.length());
            }

            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {}
            public void afterTextChanged(Editable s) {}
        };
    }
    private static String unmask(String s) {
        return s.replaceAll("[.]", "").replaceAll("[-]", "")
                .replaceAll("[/]", "").replaceAll("[(]", "")
                .replaceAll("[)]", "");
    }
}
