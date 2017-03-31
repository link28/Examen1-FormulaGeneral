package com.example.franc.examen1_formulageneral;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.GregorianCalendar;

public class Principal extends AppCompatActivity {

    private EditText etA,etB,etC,etX1,etX2;
    private Button btnResolver,btnCargar,btnEliminar;
    private double a,b,c,x1,x2;
     private CalendarView calendario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Principal.this, Secundaria.class );
                startActivity(i);
            }
        });

        etA = (EditText)findViewById(R.id.etA);
        etB = (EditText)findViewById(R.id.etB);
        etC = (EditText)findViewById(R.id.etC);
        etX1 = (EditText)findViewById(R.id.etX1);
        etX2 = (EditText)findViewById(R.id.etX2);


        btnResolver= (Button)findViewById(R.id.btnResolver);
        btnCargar= (Button)findViewById(R.id.btnCargar);
        btnEliminar= (Button)findViewById(R.id.btnEliminar);

        calendario = (CalendarView)findViewById(R.id.calendario);

        btnResolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(etA.getText().toString().equals("")|| etB.getText().toString().equals("")||etC.getText().toString().equals("")){
                    Toast.makeText(Principal.this, "No ha llenado los campos", Toast.LENGTH_SHORT).show();
                }
                else
                {
                a = Double.parseDouble(etA.getText().toString());
                b = Double.parseDouble(etB.getText().toString());
                c = Double.parseDouble(etC.getText().toString());

                    if(a==0) {
                        Toast.makeText(Principal.this, "No se puede dividir entre cero", Toast.LENGTH_SHORT).show();
                        return;
                    }
                        if (((b * b) - 4 * a * c) < 0) {
                            Toast.makeText(Principal.this, "No hay solución real para esta ecuación.", Toast.LENGTH_SHORT).show();
                        }

                    else {
                    try{
                        BufferedWriter esc  = new BufferedWriter(new OutputStreamWriter(openFileOutput("textfile.txt",MODE_PRIVATE)));
                        esc.write(etA.getText().toString());
                        esc.newLine();
                        esc.write(etB.getText().toString());
                        esc.newLine();
                        esc.write(etC.getText().toString());
                        esc.flush();
                        esc.close();
                        Toast.makeText(getBaseContext(), "Guardado", Toast.LENGTH_SHORT).show();
                        etA.setText("");
                        etB.setText("");
                        etC.setText("");
                    }catch (IOException e){
                        Toast.makeText(getBaseContext(), "Hubo un problema al guardar el archivo", Toast.LENGTH_SHORT).show();
                    }

                    x1 = ((-1 * b) + Math.sqrt(((b * b) - (4 * a * c)))) / (2 * a);
                    x2 = ((-1 * b) - Math.sqrt(((b * b) - (4 * a * c)))) / (2 * a);

                    etX1.setText(" " + x1);
                    etX2.setText(" " + x2);
                }
                }


            }
        });

        btnCargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    BufferedReader leer = new BufferedReader(new InputStreamReader(getApplicationContext().openFileInput("textfile.txt")));
                    etA.setText(leer.readLine());
                    etB.setText(leer.readLine());
                    etC.setText(leer.readLine());
                    leer.close();
                    Toast.makeText(getBaseContext(), "Cargado", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e)
                {
                    Toast.makeText(getBaseContext(), "Hubo un problema al leer el archivo", Toast.LENGTH_SHORT).show();
                    etA.setText("");
                    etB.setText("");
                    etC.setText("");

                }

            }

        });
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(getApplicationContext().deleteFile("textfile.txt"))
                {
                    Toast.makeText(getApplicationContext(),"Eliminando",Toast.LENGTH_SHORT).show();
                    etA.setText("");
                    etB.setText("");
                    etC.setText("");

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"No se pudo Eliminar",Toast.LENGTH_SHORT).show();
                }


            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
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
