package com.example.franc.examen1_formulageneral;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Secundaria extends AppCompatActivity {
    EditText etCorreo;
    EditText etAsunto;
    EditText etContenido;
    Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secundaria);
        etCorreo = (EditText) findViewById(R.id.etCorreo);
        etAsunto = (EditText) findViewById(R.id.etAsunto);
        etContenido = (EditText) findViewById(R.id.etContenido);
        btnEnviar = (Button) findViewById(R.id.btnEnviar);

    }
    public void EnviarMensaje (View view){
        String email = etCorreo.getText().toString();
        String asunto = etAsunto.getText().toString();
        String mensaje = etContenido.getText().toString();

        Intent emailenvio = new Intent(Intent.ACTION_SEND);
        emailenvio.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        emailenvio.putExtra(Intent.EXTRA_SUBJECT, asunto);
        emailenvio.putExtra(Intent.EXTRA_TEXT, mensaje);
        emailenvio.setType("message/rfc822");
        startActivity(Intent.createChooser(emailenvio, "Enviar correo por:"));
    }
}
