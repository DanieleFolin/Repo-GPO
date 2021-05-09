package com.example.agenziaviaggiapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TravelsCompletedActivity extends AppCompatActivity {

    ListView listiv;
    ArrayList<View> views;
    ArrayList<Viaggio> viaggi;
    CustomAdapter listadapter;
    Intent oldIntent, intentBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travels_completed);
        setTitle("Viaggi Completati");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Date dataInizio = new Date();
        Date dataFine = new Date();
        listiv = findViewById(R.id.listViewId);
        oldIntent = getIntent();
        views = new ArrayList<>();
        viaggi = new ArrayList<>();

        Viaggio viaggioA = new Viaggio(0, "Kansas", 100, dataInizio, dataFine);
        Viaggio viaggioB = new Viaggio(1, "Alaska", 100, dataInizio, dataFine);
        Viaggio viaggioC = new Viaggio(2, "Illinois", 100, dataInizio, dataFine);
        viaggioA.setDescrizione("Viaggio in Kansas");
        viaggioB.setDescrizione("Viaggio in Alaska");
        viaggioC.setDescrizione("Viaggio in Illinois");
        viaggi.add(viaggioA);
        viaggi.add(viaggioB);
        viaggi.add(viaggioC);

        for(int i = 0; i < viaggi.size(); i++){
            ImageView newImage = new ImageView(this);
            //TextView newTitle = new TextView(this);
            //TextView newText = new TextView(this);
            //Button newButton = new Button(this);
            //Set image and text and add them to the list
            newImage.setImageResource(R.drawable.ic_launcher_background);
            //newTitle.setText(viaggi.get(i).getNome());
            //newText.setText(viaggi.get(i).getDescrizione());
            views.add(newImage);
            //views.add(newTitle);
            //views.add(newText);
            //views.add(newButton);
        }

        listadapter = new CustomAdapter(this, R.layout.listitem, views);
        listiv.setAdapter(listadapter);
        listiv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = getIntent();
        intentBack = intent.getParcelableExtra("Intent");
        startActivity(intentBack);
        return super.onOptionsItemSelected(item);
    }

    public class CustomAdapter extends ArrayAdapter<View> {

        public CustomAdapter(Context context, int listItemELementView, List<View> objects) {
            super(context, listItemELementView, objects);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listitem, null);
            final ImageView imgv = convertView.findViewById(R.id.imageView2);
            final TextView txtv = convertView.findViewById(R.id.textView);
            final TextView txtv1 = convertView.findViewById(R.id.textView2);
            final ImageButton btn = convertView.findViewById(R.id.button);

            imgv.setImageResource(R.drawable.ic_launcher_background);
            txtv.setText(viaggi.get(position).getNome());
            txtv1.setText(viaggi.get(position).getDescrizione());

            btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), PopUpActivity.class);
                        intent.putExtra("Intent", oldIntent);
                        startActivity(intent);
                    }
                });
            return convertView;
        }
    }
}
