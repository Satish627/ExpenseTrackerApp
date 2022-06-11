package com.example.expensetrackerapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.expensetrackerapp.R;
import com.example.expensetrackerapp.ui.AddTransaction.AddTransactionsFragment;
import com.example.expensetrackerapp.ui.ViewTransaction.ViewTransactionFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    BottomNavigationView bottomNavView;

    ViewTransactionFragment viewTransactionFragment = new ViewTransactionFragment();
    AddTransactionsFragment addTransactionsFragment =  new AddTransactionsFragment();
    LogoutFragment logoutFragment = new LogoutFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavView = findViewById(R.id.bottomNavigationView);
        navigationView = findViewById(R.id.navigation_view);
        drawerLayout= findViewById(R.id.drawer_layout);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, viewTransactionFragment).commit();


        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.menu_open,R.string.menu_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, viewTransactionFragment).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        return true;
                    case R.id.logout:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,logoutFragment).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        return true;
                }
                return false;
            }
        });
        bottomNavView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
                                                    @Override
                                                    public boolean onNavigationItemSelected( MenuItem item) {
                                                        switch (item.getItemId()){
                                                            case R.id.home:
                                                                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, viewTransactionFragment).commit();
                                                                return true;
                                                            case R.id.add:
                                                                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,addTransactionsFragment).commit();
                                                                return true;
                                                        }
                                                        return false;
                                                    }
                                                }
        );

    }
}