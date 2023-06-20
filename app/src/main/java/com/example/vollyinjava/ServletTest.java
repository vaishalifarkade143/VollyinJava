package com.example.vollyinjava;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class ServletTest extends AppCompatActivity {

    EditText reg_name, reg_email, reg_pass, reg_gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servlet_test);

        reg_name = (EditText) findViewById(R.id.reg_name);
        reg_email = (EditText) findViewById(R.id.reg_email);
        reg_email = (EditText) findViewById(R.id.reg_pass);
        reg_gender = (EditText) findViewById(R.id.reg_gender);

    }

    public void registerUser(View view)
    {
        String name1 = reg_name.getText().toString();
        String email1 = reg_email.getText().toString();
        String pass1 = reg_pass.getText().toString();
        String gender1 = reg_gender.getText().toString();



        //1. RequestQueue ka referance get karva diya
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //set AlertDialog
        final AlertDialog.Builder  builder = new AlertDialog.Builder(this);
        builder.setTitle("Loding..");
        builder.setMessage("Please Wait..");
        builder.show();
        AlertDialog alertDialog = builder.create();

        StringRequest stringRequest= new StringRequest(
                Request.Method.POST,
                "https://192.168.0.137:9494/Testing/ServletTest",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)
                    {
                        alertDialog.hide();
                        Toast.makeText(ServletTest.this, ""+response, Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        alertDialog.hide();
                        Toast.makeText(ServletTest.this, ""+error, Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
               HashMap<String, String> hm = new HashMap<>();
                hm.put("key_name",name1);
                hm.put("key_email",email1);
                hm.put("key_pass",pass1);
                hm.put("key_gender",gender1);
                return hm;
            }
        };
        requestQueue.add(stringRequest);

    }
}