package com.project.laundryappui;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {


    Button BTregister;
    EditText ETEmail, ETUsername, ETPassword;
    String username,email, password;
    ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // deklarasikan variabel
        BTregister = findViewById(R.id.btnRegister);
        ETEmail = findViewById(R.id.inputEmail);
        ETUsername = findViewById(R.id.inputUsername);
        ETPassword = findViewById(R.id.inputPassword);


        // Memfungsikan Widget




        BTregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = ETEmail.getText().toString();
                username = ETUsername.getText().toString();
                password = ETPassword.getText().toString();

                validasiData();

                // Toast.makeText(getApplicationContext(),"Berhasil Melakukan Register",Toast.LENGTH_LONG).show();
                // startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });

    }

    void validasiData(){
        if(email.equals("") ||username.equals("")|| password.equals("")){
            Toast.makeText(RegisterActivity.this, "Data Tidak Lengkap", Toast.LENGTH_SHORT).show();
        } else {
            kirimData();
        }
    }

    void kirimData(){
        AndroidNetworking.post("https://tkjbpnup.com/kelompok_10/register.php")
                .addBodyParameter("email",""+email)
                .addBodyParameter("username",""+username)
                .addBodyParameter("password",""+password)
                .setPriority(Priority.MEDIUM)
                .setTag("Tambah Data")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("cekRegister",""+response);

                        try {
                            Boolean status = response.getBoolean("status");
                            String pesan   = response.getString("result");
                            Toast.makeText(RegisterActivity.this, ""+pesan, Toast.LENGTH_SHORT).show();
                            Log.d("status",""+status);
                            if(status){
                                new AlertDialog.Builder(RegisterActivity.this)
                                        .setMessage("Berhasil Register")
                                        .setCancelable(false)
                                        .setPositiveButton("Login", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                                startActivity(intent);
                                            }
                                        })
                                        .show();
                            }
                            else {
                                new AlertDialog.Builder(RegisterActivity.this)
                                        .setMessage("Gagal Melakukan Register !")
                                        .setPositiveButton("Kembali", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                Intent intent = new Intent(RegisterActivity.this, RegisterActivity.class);
                                                startActivity(intent);
                                            }
                                        })
                                        .setCancelable(false)
                                        .show();
                            }
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }


                    @Override
                    public void onError(ANError anError) {
                        Log.d("ErrorTambahData",""+anError.getErrorBody());
                    }
                });


    }
}
