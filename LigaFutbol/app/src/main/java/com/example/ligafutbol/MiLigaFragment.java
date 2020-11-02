package com.example.ligafutbol;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.ligafutbol.databinding.FragmentMiLigaBinding;

public class MiLigaFragment extends Fragment {
    private FragmentMiLigaBinding binding;
    private int ganados;
    private int empatados;
    private int perdidos;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentMiLigaBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final MiLigaViewModel miLigaViewModel = new ViewModelProvider(this).get(MiLigaViewModel.class);


        binding.calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int ganados = Integer.parseInt(binding.ganados.getText().toString());
                int empatados = Integer.parseInt(binding.empatados.getText().toString());
                int perdidos = Integer.parseInt(binding.perdidos.getText().toString());

                miLigaViewModel.calcular(ganados, empatados, perdidos);

                miLigaViewModel.puntos.observe(getViewLifecycleOwner(), new Observer<Integer>() {
                    @Override
                    public void onChanged(Integer puntos) {
                        binding.puntos.setText(Integer.toString(puntos));
                    }

                });

            }
        });


        miLigaViewModel.calculando.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean calculando) {
                if (calculando) {
                    binding.calculando.setVisibility(View.VISIBLE);
                    binding.puntos.setVisibility(View.GONE);
                } else {
                    binding.calculando.setVisibility(View.GONE);
                    binding.puntos.setVisibility(View.VISIBLE);
                }
            }
        });
    }

}
