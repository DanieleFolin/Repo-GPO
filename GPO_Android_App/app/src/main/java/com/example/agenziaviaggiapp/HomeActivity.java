package com.example.agenziaviaggiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class HomeActivity extends AppCompatActivity {

    Intent oldIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("Home");
        oldIntent = getIntent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.share_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent();
        switch(item.getItemId()){
            case R.id.travels_completed:
                intent = new Intent(getApplicationContext(), TravelsCompletedActivity.class);
                break;
            case R.id.travels_booked:
                intent = new Intent(getApplicationContext(), TravelsBookedActivity.class);
                break;
            case R.id.logout:
                break;
        }
        intent.putExtra("Intent", oldIntent);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}
