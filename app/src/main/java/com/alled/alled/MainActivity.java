package com.alled.alled;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.alled.alled.educationalwebsites.EducationalWebsites;
import com.alled.alled.reminders.ReminderActivity;
import com.alled.alled.R;
import com.alled.alled.calendar.CalendarActivity;
import com.alled.alled.notes.activities.NotesActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        mAuth = FirebaseAuth.getInstance();



        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

    }

    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen((GravityCompat.START))){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        
        switch (item.getItemId()){
            case R.id.nav_home:
                break;
            case R.id.nav_calendar:
                Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_profile:
                startActivity(new Intent(MainActivity.this,Profile_Page.class));
                break;

            case R.id.nav_notes:
                startActivity(new Intent(MainActivity.this, NotesActivity.class));
                break;

            case R.id.nav_notification:
                startActivity(new Intent(MainActivity.this, ReminderActivity.class));
                break;

            case R.id.nav_websites:
                startActivity(new Intent(MainActivity.this, EducationalWebsites.class));

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}

