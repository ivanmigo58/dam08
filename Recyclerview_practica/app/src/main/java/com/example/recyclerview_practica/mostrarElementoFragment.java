package com.example.recyclerview_practica;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.recyclerview_practica.databinding.FragmentMostrarElementoBinding;


public class mostrarElementoFragment extends Fragment {


    private OrdenadoresViewModel ordenadoresViewModel;
    private FragmentMostrarElementoBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return (binding = FragmentMostrarElementoBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ordenadoresViewModel = new ViewModelProvider(requireActivity()).get(OrdenadoresViewModel.class);

        ordenadoresViewModel.seleccionado().observe(getViewLifecycleOwner(), new Observer<Ordenador>() {
            @Override
            public void onChanged(Ordenador ordenador) {
                binding.nombre.setText(ordenador.nombre);
                binding.descripcion.setText(ordenador.descripcion);
            }
        });
    }
}