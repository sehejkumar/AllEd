package com.alled.alled.educationalwebsites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.alled.alled.reminders.ReminderActivity;
import com.alled.alled.MainActivity;
import com.alled.alled.Profile_Page;
import com.example.alled.R;
import com.alled.alled.calendar.CalendarActivity;
import com.alled.alled.notes.activities.NotesActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class EducationalWebsites extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Button khanAcademyButton, duolingoButton, udemyButton, historyButton,googleClassroomButton,schoologyButton;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_educational_websites);

        khanAcademyButton = (Button) findViewById(R.id.khanAcademyButton);
        duolingoButton = (Button) findViewById(R.id.duolingoButton);
        udemyButton = (Button) findViewById(R.id.udemyButton);
        historyButton = (Button) findViewById(R.id.historyButton);
        googleClassroomButton = (Button) findViewById(R.id.googleClassroomButton);
        schoologyButton = (Button) findViewById(R.id.schoologyButton);
        drawerLayout = findViewById(R.id.websiteDrawer);
        navigationView = findViewById(R.id.nav_view_website);
        toolbar = findViewById(R.id.websitetoolbar);

        mAuth = FirebaseAuth.getInstance();



        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_websites);

        khanAcademyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToUrl("https://www.khanacademy.org/");
            }
        });

        duolingoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToUrl("https://www.duolingo.com/");
            }
        });

        udemyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToUrl("https://www.udemy.com/");
            }
        });

        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToUrl("https://www.history.com/");
            }
        });

        googleClassroomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToUrl("https://classroom.google.com/");
            }
        });

        schoologyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToUrl("https://www.powerschool.com/solutions/unified-classroom/schoology-learning/");
            }
        });



    }

    private void goToUrl(String s) {

        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_websites:
                break;
            case R.id.nav_calendar:
                Intent intent = new Intent(EducationalWebsites.this, CalendarActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_profile:
                startActivity(new Intent(EducationalWebsites.this, Profile_Page.class));
                break;

            case R.id.nav_notes:
                startActivity(new Intent(EducationalWebsites.this, NotesActivity.class));
                break;

            case R.id.nav_notification:
                startActivity(new Intent(EducationalWebsites.this, ReminderActivity.class));
                break;

            case R.id.nav_home:
                startActivity(new Intent(EducationalWebsites.this, MainActivity.class));

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}