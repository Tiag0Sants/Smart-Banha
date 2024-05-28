package com.example.prototipo2;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class suporte extends Fragment implements View.OnClickListener {
    ImageView btnvoltar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_suporte, container, false);

        // Initialize the button and set the click listener
        btnvoltar = view.findViewById(R.id.btnvoltar);
        btnvoltar.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnvoltar) {
            //se clicou no botão de voltar
            Intent tela = new Intent(getActivity(), Home.class);
            startActivity(tela);
        }
    }
}