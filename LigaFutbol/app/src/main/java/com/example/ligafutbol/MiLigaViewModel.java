package com.example.ligafutbol;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MiLigaViewModel extends AndroidViewModel {

    Executor executor;

    SimuladorLiga simulador;

    MutableLiveData<Integer> puntos = new MutableLiveData<>();
    MutableLiveData<Boolean> calculando = new MutableLiveData<>();

    public MiLigaViewModel(@NonNull Application application) {
        super(application);

        executor = Executors.newSingleThreadExecutor();
        simulador = new SimuladorLiga();
    }

    public void calcular(int ganados, int empatados, int perdidos) {

        final SimuladorLiga.Solicitud solicitud = new SimuladorLiga.Solicitud(ganados, empatados, perdidos);

        executor.execute(new Runnable() {
            @Override
            public void run() {

                simulador.calcular(solicitud, new SimuladorLiga.Callback() {

                    @Override
                    public void cuandoEsteCalculadoLosPuntos(int puntosResultantes) {
                        puntos.postValue(puntosResultantes);
                    }

                    @Override
                    public void cuandoEmpieceElCalculo() {
                        calculando.postValue(true);
                    }

                    @Override
                    public void cuandoFinaliceElCalculo() {
                        calculando.postValue(false);
                    }
                });
            }

        });

    }
}
