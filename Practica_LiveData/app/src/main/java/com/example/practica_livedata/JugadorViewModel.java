package com.example.practica_livedata;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

public class JugadorViewModel extends AndroidViewModel {
    Jugador jugador;

    LiveData<Integer> jugadaImagenLiveData;

    public JugadorViewModel (@NonNull Application application) {
        super(application);

        jugador = new Jugador();

        jugadaImagenLiveData = Transformations.switchMap(jugador.ordenLiveData, new Function<String, LiveData<Integer>>() {
            String ejercicioAnterior;

            @Override
            public LiveData<Integer> apply(String orden) {
                int imagen;

                switch (orden) {
                    case "CHUTE":
                    default:
                        imagen = R.drawable.chute;
                        break;
                    case "MARCANDO":
                        imagen = R.drawable.marcando;
                        break;
                }
                return new MutableLiveData<>(imagen);
            }
        });

    }

    LiveData<Integer> obtenerJugada(){
        return jugadaImagenLiveData;
    }


}
