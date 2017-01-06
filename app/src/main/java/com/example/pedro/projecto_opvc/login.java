package com.example.pedro.projecto_opvc;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class login extends ActionBarActivity implements View.OnClickListener {

    EditText nome;
    EditText password;
    Button confirmar;
    Button inscrever;

    private ProgressDialog pDialog;

    //JSONParser jsonParser = new JSONParser();
    //private static final String LOGIN_URL = "http://10.100.161.62/android/login.php";
    //private static final String TAG_SUCCESS = "success";
    //private static final String TAG_MESSAGE = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        nome = (EditText) findViewById(R.id.editText2);
        password = (EditText) findViewById(R.id.editText);
        confirmar = (Button) findViewById(R.id.button);
        inscrever = (Button) findViewById(R.id.button2);
        confirmar.setOnClickListener(this);
        inscrever.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                File user = Environment.getExternalStorageDirectory();
                File record = new File(user, "record.txt");
                String line;
                String utili="";
                String passe="";
                int lina=1;
                if(user.exists()) {
                    StringBuilder text = new StringBuilder();
                    try {

                        FileReader recordr = new FileReader(record);
                        BufferedReader out = new BufferedReader(new FileReader(record));
                        while((line = out.readLine())!= null) {
                            if(lina==1){
                                utili= line.toString();
                            }
                            if(lina ==7) {
                                passe = line.toString();
                            }
                            lina++;
                        }
                                if (nome.getText().toString().equals(utili.toString())){

                                        if(password.getText().toString().equals(passe.toString())){
                                            Intent i = new Intent(login.this, menu.class);
                                            startActivity(i);
                                            Toast.makeText(getApplicationContext(), "" + "Login feito com sucesso", Toast.LENGTH_LONG).show();
                                        }
                                        else {
                                            Toast.makeText(getApplicationContext(), "" + "Palavra Passe ou  utilizador errados", Toast.LENGTH_LONG).show();
                                        }
                                    } else {
                                     Toast.makeText(getApplicationContext(), "" + "Palavra Passe ou  utilizador errados", Toast.LENGTH_LONG).show();


                                }
                    } catch (Throwable t) {
                        Toast.makeText(this, "Excepcao: " + t.toString(), Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "" + "Ficheiro nao existe", Toast.LENGTH_LONG).show();
                }
            default:
                break;
            case R.id.button2:
                Intent j = new Intent(login.this, criar.class);
                startActivity(j);
        }
    }
}

