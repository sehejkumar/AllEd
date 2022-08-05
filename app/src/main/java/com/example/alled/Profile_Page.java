package com.example.alled;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alled.calendar.CalendarActivity;
import com.example.alled.educationalwebsites.EducationalWebsites;
import com.example.alled.notes.activities.NotesActivity;
import com.example.alled.reminders.ReminderActivity;
import com.example.alled.signupandlogin.Login;
import com.example.alled.signupandlogin.User;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile_Page extends AppCompatActivity implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener {

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    private Button logout;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        logout = (Button) findViewById(R.id.logout) ;
        logout.setOnClickListener(this);

        drawerLayout = findViewById(R.id.profileDrawer);
        navigationView = findViewById(R.id.nav_view_profile);
        toolbar = findViewById(R.id.toolbarProfile);

        mAuth = FirebaseAuth.getInstance();

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_profile);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        final TextView nameTextView  = (TextView) findViewById(R.id.name);
        final TextView ageTextView  = (TextView) findViewById(R.id.age);
        final TextView emailTextView  = (TextView) findViewById(R.id.email);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if(userProfile != null){
                    String name = userProfile.fullName;
                    String age = userProfile.age;
                    String email = userProfile.email;


                    nameTextView.setText(name);
                    ageTextView.setText(age);
                    emailTextView.setText(email);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Profile_Page.this, "An Error Occurred", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onClick(View v) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(Profile_Page.this,Login.class));
    }

    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen((GravityCompat.START))) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }




    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_profile:
                break;
            case R.id.nav_calendar:
                Intent intent = new Intent(Profile_Page.this, CalendarActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_home:
                startActivity(new Intent(Profile_Page.this,MainActivity.class));
                break;

            case R.id.nav_notes:
                startActivity(new Intent(Profile_Page.this, NotesActivity.class));
                break;

            case R.id.nav_notification:
                startActivity(new Intent(Profile_Page.this, ReminderActivity.class));
                break;


            case R.id.nav_websites:
                startActivity(new Intent(Profile_Page.this, EducationalWebsites.class));
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}