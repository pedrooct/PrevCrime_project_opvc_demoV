package com.example.pedro.projecto_opvc;

import android.app.Activity;
import android.app.ListActivity;
import android.content.ClipData;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.widget.Toast.LENGTH_LONG;


public class InserirRua extends Activity implements View.OnClickListener {

    private SimpleAdapter simpleAdpt;
    private SimpleAdapter simpleAdpt2;
    private ListView Mylistview;
    private ListView Mylistview2;
    List<Map<String, String>> categoria = new ArrayList<Map<String, String>>();
    List<Map<String, String>> Subcategoria = new ArrayList<Map<String, String>>();
    Button guardar;
    EditText txtrua;
    String cat = "";
    String subcat = "";
    int i=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_rua);
        guardar = (Button) findViewById(R.id.button5);
        guardar.setOnClickListener(this);
        txtrua = (EditText) findViewById(R.id.editText9);
        Mylistview = (ListView) findViewById(R.id.listView);
        Mylistview2 = (ListView) findViewById(R.id.listView2);
        simpleAdpt = new SimpleAdapter(this, categoria, android.R.layout.simple_list_item_1, new String[]{"categoria"}, new int[]{android.R.id.text1});
        Mylistview.setAdapter(simpleAdpt);
        simpleAdpt2 = new SimpleAdapter(this, Subcategoria, android.R.layout.simple_list_item_1, new String[]{"Subcategoria"}, new int[]{android.R.id.text1});
        Mylistview2.setAdapter(simpleAdpt2);
        Mylistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long Id) {
                cat = ((TextView) view).getText().toString();
                Toast.makeText(getApplicationContext(), "" + "Escolheu a opcao: " + ((TextView) view).getText(), Toast.LENGTH_LONG).show();
            }
        });
        Mylistview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long Id) {
                subcat = ((TextView) view).getText().toString();
                Toast.makeText(getApplicationContext(), "" + "Escolheu a opcao: "+ ((TextView) view).getText(), Toast.LENGTH_LONG).show();
            }
        });
        initList();

    }

    private void initList() {

        categoria.add(createCategoria("categoria", "1-visibilidade"));
        categoria.add(createCategoria("categoria", "2-acessibilidade"));
        categoria.add(createCategoria("categoria", "3-Estruturas do espaco"));
        categoria.add(createCategoria("categoria", "4-Condicoes ambientais"));
        categoria.add(createCategoria("categoria", "5-Controlo de vigilancia"));
        Subcategoria.add(createSubCategoria("Subcategoria", "1-Iluminacao"));
        Subcategoria.add(createSubCategoria("Subcategoria", "1-Barreiras Fisicas"));
        Subcategoria.add(createSubCategoria("Subcategoria", "1-Esquinas cegas"));
        Subcategoria.add(createSubCategoria("Subcategoria", "1-Locais cegos"));
        Subcategoria.add(createSubCategoria("Subcategoria", "1-Distribuicao de luminacao"));
        Subcategoria.add(createSubCategoria("Subcategoria", "1-Iluminacao"));
        Subcategoria.add(createSubCategoria("Subcategoria", "1-Barreiras fisicas(Arbustos,etc...)"));
        Subcategoria.add(createSubCategoria("Subcategoria", "2-Esquinas cegas"));
        Subcategoria.add(createSubCategoria("Subcategoria", "2-Locais cegos"));
        Subcategoria.add(createSubCategoria("Subcategoria", "2-Distribuicao de luminacao"));
        Subcategoria.add(createSubCategoria("Subcategoria", "2-Estado dos arruamentos"));
        Subcategoria.add(createSubCategoria("Subcategoria", "2-Estado dos passeios"));
        Subcategoria.add(createSubCategoria("Subcategoria", "3-Delimitacao de passagens pedonais"));
        Subcategoria.add(createSubCategoria("Subcategoria", "3-Becos sem saida"));
        Subcategoria.add(createSubCategoria("Subcategoria", "4-Lixo dispersado"));
        Subcategoria.add(createSubCategoria("Subcategoria", "4-Degradamento habitacional"));
        Subcategoria.add(createSubCategoria("Subcategoria", "4-Degradamento das areas publicas"));
        Subcategoria.add(createSubCategoria("Subcategoria", "4-Sinais de vandalismo"));
        Subcategoria.add(createSubCategoria("Subcategoria", "5-Camaras de vigilancia"));
        Subcategoria.add(createSubCategoria("Subcategoria", "5-Patrulhamento"));
        Subcategoria.add(createSubCategoria("Subcategoria", "5-Residentes moradores"));
    }
    public void onClick(View v) {
        File map =Environment.getExternalStorageDirectory();

        switch (v.getId()) {

            case R.id.button5:
                if(map.canWrite()) {
                    try {
                        Bundle extras = getIntent().getExtras();
                        if (extras != null) {
                            String latitude = extras.getString("latitude");
                            String longitude = extras.getString("longitude");

                            File rua = new File(map, "rua.txt");
                            FileWriter mapa = new FileWriter(rua);
                            BufferedWriter out = new BufferedWriter(mapa);
                            out.write("latitude:" + latitude.toString());
                            out.write("\n");
                            out.write("longitude:" + longitude.toString());
                            out.write("\n");
                            out.write("Rua:" + txtrua.getText().toString());
                            out.write("\n");
                            out.write("Categoria:" + cat.toString());
                            out.write("\n");
                            out.write("Subcategoria:" + subcat.toString());
                            out.close();
                            Toast.makeText(this, "Registo efectuado com sucesso e conteudo guardado localmente num ficheiro.", LENGTH_LONG).show();
                            Intent i = new Intent(this, menu.class);
                            startActivity(i);
                        }}catch(Throwable t){
                            Toast.makeText(this, "Excepcao: " + t.toString(), LENGTH_LONG).show();
                        }
                    }
                else{
                    Toast.makeText(getApplicationContext(), "" + "Não consegue escrever no ficheiro", LENGTH_LONG).show();
                }
        }
    }

    private HashMap<String, String> createCategoria(String key, String name) {
        HashMap<String, String> categoria = new HashMap<String, String>();
        categoria.put(key, name);
        return categoria;

    }

    private HashMap<String, String> createSubCategoria(String key, String name) {
        HashMap<String, String> Subcategoria = new HashMap<String, String>();
        Subcategoria.put(key, name);
        return Subcategoria;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_inserir_rua, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

