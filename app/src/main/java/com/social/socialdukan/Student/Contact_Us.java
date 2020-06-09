package com.social.socialdukan.Student;
import static android.Manifest.permission.CALL_PHONE;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.socialdukan.R;

public class Contact_Us extends AppCompatActivity {
    TextView number, email1, email2, email3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_contact__us );
        number = findViewById( R.id.number );
        email1 = findViewById( R.id.email1 );
        email2 = findViewById( R.id.email2 );
        email3 = findViewById( R.id.email3 );
email1.setOnClickListener( new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData( Uri.parse("mailto:")); // only email apps should handle this

        intent.putExtra( Intent.EXTRA_EMAIL, new String[]{"ceo@socialdukan.com"  });

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
} );
        email2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData( Uri.parse("mailto:")); // only email apps should handle this
                intent.putExtra( Intent.EXTRA_EMAIL, new String[]{"socialdukan@gmail.com" });


                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        } );
        email3.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData( Uri.parse("mailto:")); // only email apps should handle this
                intent.putExtra( Intent.EXTRA_EMAIL, new String[]{"info@socialdukan.com"} );


                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        } );
        number.setOnClickListener( new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:+91 82727084770"));
/*
Intent i = new Intent(Intent.ACTION_DIAL);
i.setData(Uri.parse("tel:0612312312"));
if (i.resolveActivity(getPackageManager()) != null) {
      startActivity(i);
}*/
                if (ContextCompat.checkSelfPermission(getApplicationContext(), CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    startActivity(i);
                } else {
                    requestPermissions(new String[]{CALL_PHONE}, 1);
                }

         }
     } );

    }
}