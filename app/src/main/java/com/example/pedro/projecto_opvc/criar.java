package com.example.pedro.projecto_opvc;

import android.app.Activity;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;


public class criar extends Activity implements View.OnClickListener {


    private EditText txtpolicia;
    private EditText txtnome;
    private EditText txtmorada;
    private EditText txtpostal;
    private EditText txtnumero;
    private EditText txtemail;
    private EditText txtpasse;
    private EditText txtpasse2;
    Button registo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar);
        txtpolicia = (EditText) findViewById(R.id.editText3);
        txtnome = (EditText) findViewById(R.id.editText4);
        txtmorada = (EditText) findViewById(R.id.editText5);
        txtpostal = (EditText) findViewById(R.id.editText7);
        txtnumero = (EditText) findViewById(R.id.editText6);
        txtemail = (EditText) findViewById(R.id.editText8);
        txtpasse = (EditText) findViewById(R.id.editText11);
        txtpasse2 = (EditText) findViewById(R.id.editText12);
        registo = (Button) findViewById(R.id.button3);
        registo.setOnClickListener(this);
    }
    public void onClick(View v) {
        File user =Environment.getExternalStorageDirectory();
        switch (v.getId()) {
            case R.id.button3:
                if (txtpasse.getText().toString().equals(txtpasse2.getText().toString())&&(user.canWrite())){
                    try {
                        File record = new File(user, "record.txt");
                        FileWriter recordr = new FileWriter(record);
                        BufferedWriter out = new BufferedWriter(recordr);
                        out.write(txtpolicia.getText().toString());
                        out.write("\n");
                        out.write(txtnome.getText().toString());
                        out.write("\n");
                        out.write(txtmorada.getText().toString());
                        out.write("\n");
                        out.write(txtpostal.getText().toString());
                        out.write("\n");
                        out.write(txtnumero.getText().toString());
                        out.write("\n");
                        out.write(txtemail.getText().toString());
                        out.write("\n");
                        out.write(txtpasse.getText().toString());
                        out.write("\n");
                        out.close();
                        Intent i = new Intent(criar.this, login.class);
                        startActivity(i);
                        Toast.makeText(this, "Registo efectuado com sucesso e conteudo guardado localmente num ficheiro.", Toast.LENGTH_LONG).show();
                    } catch (Throwable t) {
                        Toast.makeText(this, "Excepcao: " + t.toString(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "" + "Palavra-passe tem de ser iguais", Toast.LENGTH_LONG).show();
                }
            default:
                break;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_criar, menu);
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
