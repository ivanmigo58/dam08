package com.example.practica_livedata;

import androidx.lifecycle.LiveData;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Jugador {

    interface JugadorListener {
        void cuandoCambie(String orden);
    }

    Random random = new Random();
    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    ScheduledFuture<?> jugando;
    String ju;

    void iniciarJugada(JugadorListener jugadorListener) {
        if (jugando == null || jugando.isCancelled()) {
            jugando = scheduler.scheduleAtFixedRate(new Runnable() {
                int ejercicio;

                @Override
                public void run() {
                   switch (ejercicio) {
                       case 0:
                           jugadorListener.cuandoCambie("CHUTE");
                           break;

                       case 1:
                           jugadorListener.cuandoCambie("MARCANDO");
                   }
                   ejercicio++;
                   if (ejercicio > 1) {
                       ejercicio = 0;
                   }
                }
            }, 0, 2, SECONDS);
        }
    }

    void pararJugada() {
        if (jugando != null) {
            jugando.cancel(true);
        }
    }

    LiveData<String> ordenLiveData = new LiveData<String>() {
        @Override
        protected void onActive() {
            super.onActive();

            iniciarJugada(new JugadorListener() {
                @Override
                public void cuandoCambie(String ejercicio) {
                    postValue(ejercicio);
                }
            });
        }

        @Override
        protected void onInactive() {
            super.onInactive();
            pararJugada();
        }
    };
}
