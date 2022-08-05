package com.example.alled.calendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;

import com.example.alled.MainActivity;
import com.example.alled.Profile_Page;
import com.example.alled.R;
import com.example.alled.educationalwebsites.EducationalWebsites;
import com.example.alled.notes.activities.NotesActivity;
import com.example.alled.reminders.ReminderActivity;
import com.example.alled.reminders.ReminderActivity;
import com.example.alled.signupandlogin.Login;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class CalendarActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    CustomCalendarView customCalendarView;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        customCalendarView = (CustomCalendarView) findViewById(R.id.custom_calendar_view);

        drawerLayout = findViewById(R.id.calendarDrawerLayout);
        navigationView = findViewById(R.id.nav_view_calendar);
        toolbar = findViewById(R.id.calendartoolbar);

        mAuth = FirebaseAuth.getInstance();



        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_calendar);

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_calendar:
                break;
            case R.id.nav_home:
                Intent intent = new Intent(CalendarActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_profile:
                startActivity(new Intent(CalendarActivity.this,Profile_Page.class));
                break;

            case R.id.nav_notes:
                startActivity(new Intent(CalendarActivity.this, NotesActivity.class));
                break;

            case R.id.nav_notification:
                startActivity(new Intent(CalendarActivity.this, ReminderActivity.class));
                break;


            case R.id.nav_websites:
                startActivity(new Intent(CalendarActivity.this, EducationalWebsites.class));

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}