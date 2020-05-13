package com.example.socialdukan.Student.fragment.other_services;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.socialdukan.R;

public class Thanks_Activity extends AppCompatActivity {
ImageView cross_btn;
Button okay_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_thanks_ );
        cross_btn=findViewById( R.id.cross_btn_rf );
        okay_btn=findViewById( R.id.okay );

        cross_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );
        okay_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );
    }
}
