package com.example.agenziaviaggiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class PopUpActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;
    Spinner spinner;
    String settingToChange, whatHasChanged;
    ArrayList<String> tables;
    ArrayList<Integer> marks;
    Intent intentBack;
    Cliente utenteProvvisorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen 
        setContentView(R.layout.activity_pop_up);

        tables = new ArrayList<>();
        marks = new ArrayList<>();

        tables.add("Image");
        tables.add("Status");
        tables.add("ProfileImage");

        for(int i = 1; i <= 10; i++){
            marks.add(i);
        }

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout(width, height);

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        //params.x = 0;
        //params.y = -20;

        getWindow().setAttributes(params);

        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
        spinner = findViewById(R.id.spinner);

        ArrayAdapter marksadapter = new ArrayAdapter<Integer>(this, R.layout.support_simple_spinner_dropdown_item, marks);
        spinner.setAdapter(marksadapter);

        Intent intent = getIntent();
        intentBack = intent.getParcelableExtra("Intent");
        //settingToChange = intent.getStringExtra("SettingToChange");
        editText.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);


    }

    public void onClick(View view){
        if(editText.getText().toString().matches("")){
            //Nessun inserimento
            //startActivity(intentBack);
        }else {
            //Inserimento valutazione
            int mark = (int) spinner.getSelectedItem();
            Valutazione newMark = new Valutazione(utenteProvvisorio, editText.getText().toString(), mark);
            //for(int i = 0; i < 10; i++){
            //    utenteProvvisorio.getPrenotazione(i).setValutazione(newMark);
            //}
        }
        startActivity(intentBack);
    }
}
