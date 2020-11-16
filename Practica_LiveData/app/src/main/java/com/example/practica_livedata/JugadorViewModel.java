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

    LiveData<Integer> jugadaLiveData;
    LiveData<String> repeticionLiveData;

    public JugadorViewModel (@NonNull Application application) {
        super(application);

        jugador = new Jugador();

        jugadaLiveData = Transformations.switchMap(jugador.ordenLiveData, new Function<String, LiveData<Integer>>() {
            String ejercicioAnterior;

            @Override
            public LiveData<Integer> apply(String orden) {
                String jugada = orden.split(";")[0];

                if (!jugada.equals(ejercicioAnterior)) {
                    ejercicioAnterior = jugada;
                    int imagen;
                    switch (jugada) {
                        case "Jugada":
                        default:
                            imagen = R.drawable.chute;
                            break;
                        case "GOL":
                            imagen = R.drawable.marcando;
                            break;
                    }
                    return new MutableLiveData<>(imagen);
                }
                return null;
            }
        });

        repeticionLiveData = Transformations.switchMap(jugador.ordenLiveData, new Function<String, LiveData<String>>() {
            @Override
            public LiveData<String> apply(String orden) {
                return new MutableLiveData<>(orden.split(":")[1]);
            }
        });
    }

    LiveData<Integer> obtenerJugada(){
        return jugadaLiveData;
    }

    LiveData<String> obtenerRepeticion(){
        return repeticionLiveData;
    }

}
