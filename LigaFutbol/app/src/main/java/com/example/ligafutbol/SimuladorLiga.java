package com.example.ligafutbol;


import android.util.Log;

public class SimuladorLiga {

    public static class Solicitud {
        public int ganados;
        public int emapatados;
        public int perdidos;

        public Solicitud(int ganados, int emapatados, int perdidos) {
            this.ganados = ganados;
            this.emapatados = emapatados;
            this.perdidos = perdidos;
        }
    }

    interface Callback {
        void cuandoEsteCalculadoLosPuntos(int puntos);

        void cuandoEmpieceElCalculo();

        void cuandoFinaliceElCalculo();
    }

    public void calcular(Solicitud solicitud, Callback callback) {
        callback.cuandoEmpieceElCalculo();

        int puntosVictorias = 0;

        int ganados = solicitud.ganados;
        int empate = solicitud.emapatados;
        int perdidos = solicitud.perdidos;

        int resultado = (ganados * 3) + empate;
        callback.cuandoEsteCalculadoLosPuntos(resultado);

        callback.cuandoFinaliceElCalculo();

    }
}
