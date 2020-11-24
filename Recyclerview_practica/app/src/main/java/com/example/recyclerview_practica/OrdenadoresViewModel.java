package com.example.recyclerview_practica;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class OrdenadoresViewModel extends AndroidViewModel {
    ElementosRepositorio elementosRepositorio;

    MutableLiveData<List<Ordenador>> listElementosMutableLiveData = new MutableLiveData<>();
    MutableLiveData<Ordenador> patineteSeleccionado = new MutableLiveData<>();

    public OrdenadoresViewModel(@NonNull Application application) {
        super(application);

        elementosRepositorio = new ElementosRepositorio();

        listElementosMutableLiveData.setValue(elementosRepositorio.obtener());
    }

    MutableLiveData<List<Ordenador>> obtener(){
        return listElementosMutableLiveData;
    }

    void insertar(Ordenador elemento){
        elementosRepositorio.insertar(elemento, new ElementosRepositorio.Callback() {
            @Override
            public void cuandoFinalice(List<Ordenador> elementos) {
                listElementosMutableLiveData.setValue(elementos);
            }
        });
    }

    void eliminar(Ordenador elemento){
        elementosRepositorio.eliminar(elemento, new ElementosRepositorio.Callback() {
            @Override
            public void cuandoFinalice(List<Ordenador> elementos) {
                listElementosMutableLiveData.setValue(elementos);
            }
        });
    }

    void actualizar(Ordenador elemento, float valoracion){
        elementosRepositorio.actualizar(elemento, valoracion, new ElementosRepositorio.Callback() {
            @Override
            public void cuandoFinalice(List<Ordenador> elementos) {
                listElementosMutableLiveData.setValue(elementos);
            }
        });
    }

    void seleccionar(Ordenador ordenador) {
        patineteSeleccionado.setValue(ordenador);
    }

    MutableLiveData<Ordenador> seleccionado() {
        return patineteSeleccionado;
    }

}
