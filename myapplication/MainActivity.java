package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;
    Toolbar toolbar; //used when xml do not show the toolbar created

    //drawer layout id:
    DrawerLayout drawerLayout;

    //navigation view id :
    NavigationView navigationView;

    ActionBarDrawerToggle actionBarDrawerToggle;

    //for bottom bar :
    private int selectedTab = 1; //no of selected tabs. we have 3 tabs so no must lie between 1-3. default value is 1 because first tab is selected by default


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);

        navigationView = (NavigationView) findViewById(R.id.navigationview);

        //drawerlayout ke upper navigationview ko set karenge

        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this,drawerLayout,toolbar,R.string.open,R.string.close);

        //is toggle ko kon sukega : that is drawerlayout he ise slide-on and slide-out karega
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        auth = FirebaseAuth.getInstance();

        // Retrieve the currently signed-in user
        user = auth.getCurrentUser();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.home);
        }

        // Access the nav_header layout and find the TextView
        View headerView = navigationView.getHeaderView(0); // 0 is the index of the header layout
        TextView textView = headerView.findViewById(R.id.setemail);

        // Check if a user is signed in and set the email in the TextView
        if (user != null) {
            textView.setText(user.getEmail());
        }

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.home);
        }

        //inorder  to activate menu items in navigation bar :

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                if(id == R.id.home){
                    //Toast.makeText(MainActivity.this, "Home Clicked", Toast.LENGTH_SHORT).show();
                    getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new HomeFragment()).commit();
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (id == R.id.share) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new ShareFragment()).commit();
                    //Toast.makeText(MainActivity.this, "Share Clicked", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (id == R.id.about) {
                   // Toast.makeText(MainActivity.this, "About Us Clicked", Toast.LENGTH_SHORT).show();
                    getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new AboutFragment()).commit();

                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (id == R.id.logout) {
                    if(user==null){
                        Intent intent=new Intent(getApplicationContext(),Login_Activity.class);
                        startActivity(intent);
                        finish();
                    }
                    FirebaseAuth.getInstance().signOut();
                    Intent intent=new Intent(getApplicationContext(), Login_Activity.class);
                    startActivity(intent);
                    finish();
                }
                return true;
            }
        });

        //Bottom navigation bar :

        final LinearLayout homeLayout = (LinearLayout) findViewById(R.id.homelayout);
        final LinearLayout serviceLayout = (LinearLayout) findViewById(R.id.servicelayout);
        final LinearLayout helpLayout = (LinearLayout) findViewById(R.id.helplayout);

        final ImageView homeImage = (ImageView) findViewById(R.id.homeImage);
        final ImageView serviceImage = (ImageView) findViewById(R.id.serviceImage);
        final ImageView helpImage = (ImageView) findViewById(R.id.helpImage);

        final TextView homeText = (TextView) findViewById(R.id.homeText);
        final TextView serviceText = (TextView) findViewById(R.id.serviceText);
        final TextView helpText = (TextView) findViewById(R.id.helpText);

        //set home as default fragment :
        getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                                .replace(R.id.framelayout,HomeFragment.class,null)
                                        .commit();

        homeLayout.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                //check if home is already selected or not
                if(selectedTab != 1){

                    //set home fragment
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.framelayout,HomeFragment.class,null)
                            .commit();

                    //unselect other tabs expect home tab
                    serviceText.setVisibility(View.GONE);
                    helpText.setVisibility(View.GONE);

                    serviceImage.setImageResource(R.drawable.services0_icon);
                    helpImage.setImageResource(R.drawable.help0_icon);

                    serviceLayout.setBackgroundResource(getResources().getColor(android.R.color.transparent));
                    helpLayout.setBackgroundResource(getResources().getColor(android.R.color.transparent));


                    //select home tab
                    homeText.setVisibility(View.VISIBLE);
                    homeImage.setImageResource(R.drawable.home_icon);
                    homeLayout.setBackgroundResource(R.drawable.round_back_home_100);

                    //create animation
                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f,1.0f,1f,1f, Animation.RELATIVE_TO_SELF,0.0f,Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    homeLayout.startAnimation(scaleAnimation);

                    selectedTab = 1;
                }
            }
        });

        serviceLayout.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                //check if home is already selected or not
                if(selectedTab != 2){

                    //set service fragment
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.framelayout,ServiceFragment.class,null)
                            .commit();

                    //unselect other tabs expect home tab
                    homeText.setVisibility(View.GONE);
                    helpText.setVisibility(View.GONE);

                    homeImage.setImageResource(R.drawable.home0_icon);
                    helpImage.setImageResource(R.drawable.help0_icon);

                    homeLayout.setBackgroundResource(getResources().getColor(android.R.color.transparent));
                    helpLayout.setBackgroundResource(getResources().getColor(android.R.color.transparent));


                    //select home tab
                    serviceText.setVisibility(View.VISIBLE);
                    serviceImage.setImageResource(R.drawable.services_icon);
                    serviceLayout.setBackgroundResource(R.drawable.round_back_home_100);

                    //create animation
                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f,1.0f,1f,1f, Animation.RELATIVE_TO_SELF,1.0f,Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    serviceLayout.startAnimation(scaleAnimation);

                    selectedTab = 2;
                }
            }
        });

        helpLayout.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                //check if home is already selected or not
                if(selectedTab != 3){

                    //set user fragment
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.framelayout, HelpFragment.class,null)
                            .commit();

                    //unselect other tabs expect home tab
                    serviceText.setVisibility(View.GONE);
                    homeText.setVisibility(View.GONE);

                    serviceImage.setImageResource(R.drawable.services0_icon);
                    homeImage.setImageResource(R.drawable.home0_icon);

                    serviceLayout.setBackgroundResource(getResources().getColor(android.R.color.transparent));
                    homeLayout.setBackgroundResource(getResources().getColor(android.R.color.transparent));


                    //select home tab
                    helpText.setVisibility(View.VISIBLE);
                    helpImage.setImageResource(R.drawable.help_icon);
                    helpLayout.setBackgroundResource(R.drawable.round_back_home_100);

                    //create animation
                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f,1.0f,1f,1f, Animation.RELATIVE_TO_SELF,1.0f,Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    helpLayout.startAnimation(scaleAnimation);
                    selectedTab = 3;
                }
            }
        });
    }
}