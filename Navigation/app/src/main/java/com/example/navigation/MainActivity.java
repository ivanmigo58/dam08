package com.example.navigation;


import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.navigation.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((binding = ActivityMainBinding.inflate(getLayoutInflater())).getRoot());
        setSupportActionBar(binding.toolbar);

        // Los que se añaden aqui son los que cuando se esten viendo, saldra el menu en la barra izquierda
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.fragment1, R.id.fragment2, R.id.bottom1Fragment, R.id.bottom2Fragment, R.id.bottom3Fragment,
                R.id.options1Fragment, R.id.options2Fragment, R.id.options3Fragment
        ).setOpenableLayout(binding.drawerLayout).build();


        navController = ((NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment)).getNavController();
        NavigationUI.setupWithNavController(binding.navView, navController);
        NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration);
        // Para el BottomNavigationView
        NavigationUI.setupWithNavController(binding.bottomNavView, navController);
    }

    // Para OptionsMenu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return NavigationUI.onNavDestinationSelected(item, navController)
                || super.onOptionsItemSelected(item);
    }

}

