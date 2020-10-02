package com.example.contadodeclicks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.contadodeclicks.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    // Contadores
    int totalContadores = 5;
    int[] contadores = new int[totalContadores];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // Pongo los contadores a 0
        resetAll();

        // Principal
        binding.buttonPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 resetAll();
                 actualizarContadores();
            }
        });

        // PRIMERO
        binding.buttonMasUnoFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickButtonMasUno(1);
                actualizarContadores();
            }
        });
        binding.buttonResetFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset(1);
                actualizarContadores();
            }
        });

        // SEGUNDO
        binding.buttonMasUnoSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickButtonMasUno(2);
                actualizarContadores();
            }
        });
        binding.buttonResetSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset(2);
                actualizarContadores();
            }
        });

        // TERCERO
        binding.buttonMasUnoThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickButtonMasUno(3);
                actualizarContadores();
            }
        });
        binding.buttonResetThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset(3);
                actualizarContadores();
            }
        });

        // CUARTO
        binding.buttonMasUnoFourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickButtonMasUno(4);
                actualizarContadores();
            }
        });
        binding.buttonResetFourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset(4);
                actualizarContadores();
            }
        });


    }

    // Reseta todos los contadores
    void resetAll() {
        for (int i = 0; i < totalContadores; i++) {
            contadores[i] = 0;
        }
    }

    // Funcion para cuando se da click en un boton +1
    void clickButtonMasUno(int posicion) {
        contadores[0]++;
        contadores[posicion]++;
    }

    // Resetea el contador que se indique
    void reset(int posicion) {
        // Obtengo los clicks que tiene esa posicion y se la resto al principal
        contadores[0] = contadores[0] - contadores[posicion];
        contadores[posicion] = 0;
    }

    // Actualiza todos los contadores
    void actualizarContadores() {
        binding.textViewPrincipal.setText(String.valueOf(contadores[0]));
        binding.textViewFirst.setText(String.valueOf(contadores[1]));
        binding.textViewSecond.setText(String.valueOf(contadores[2]));
        binding.textViewThird.setText(String.valueOf(contadores[3]));
        binding.textViewFourth.setText(String.valueOf(contadores[4]));
    }

}