package com.example.prototipo2;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


public class suporte extends Fragment implements View.OnClickListener {
    ImageView btnvoltar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_suporte, container, false);

        btnvoltar = (ImageView) findViewById(R.id.btnvoltar);

        btnvoltar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnvoltar) {
            //se clicou no botão de voltar
            Intent tela = new Intent(this, Home.class);
            startActivity(tela);
        }
    }
}