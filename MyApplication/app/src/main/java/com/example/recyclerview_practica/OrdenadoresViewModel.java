package com.example.recyclerview_practica;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class PatinetesViewModel extends AndroidViewModel {
    ElementosRepositorio elementosRepositorio;

    MutableLiveData<List<Patinete>> listElementosMutableLiveData = new MutableLiveData<>();
    MutableLiveData<Patinete> patineteSeleccionado = new MutableLiveData<>();

    public PatinetesViewModel(@NonNull Application application) {
        super(application);

        elementosRepositorio = new ElementosRepositorio();

        listElementosMutableLiveData.setValue(elementosRepositorio.obtener());
    }

    MutableLiveData<List<Patinete>> obtener(){
        return listElementosMutableLiveData;
    }

    void insertar(Patinete elemento){
        elementosRepositorio.insertar(elemento, new ElementosRepositorio.Callback() {
            @Override
            public void cuandoFinalice(List<Patinete> elementos) {
                listElementosMutableLiveData.setValue(elementos);
            }
        });
    }

    void eliminar(Patinete elemento){
        elementosRepositorio.eliminar(elemento, new ElementosRepositorio.Callback() {
            @Override
            public void cuandoFinalice(List<Patinete> elementos) {
                listElementosMutableLiveData.setValue(elementos);
            }
        });
    }

    void actualizar(Patinete elemento, float valoracion){
        elementosRepositorio.actualizar(elemento, valoracion, new ElementosRepositorio.Callback() {
            @Override
            public void cuandoFinalice(List<Patinete> elementos) {
                listElementosMutableLiveData.setValue(elementos);
            }
        });
    }

    void seleccionar(Patinete patinete) {
        patineteSeleccionado.setValue(patinete);
    }

    MutableLiveData<Patinete> seleccionado() {
        return patineteSeleccionado;
    }

}
