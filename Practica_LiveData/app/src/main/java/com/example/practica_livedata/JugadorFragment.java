package com.example.practica_livedata;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.practica_livedata.databinding.FragmentJugadorBinding;


public class JugadorFragment extends Fragment {

    private FragmentJugadorBinding binding;

      @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

          return (binding = FragmentJugadorBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        JugadorViewModel jugadorViewModel = new ViewModelProvider(this).get(JugadorViewModel.class);

        jugadorViewModel.obtenerJugada().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer ejercicio) {
                Glide.with(JugadorFragment.this).load(ejercicio).into(binding.jugada);
            }
        });

    }
}