package com.example.socialdukan.Employe;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.socialdukan.R;
import com.example.socialdukan.Student.Login_Register_Student.Dashboard;
import com.example.socialdukan.Student.Login_Register_Student.Studentdetail;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class  Employe_detail extends AppCompatActivity implements View.OnClickListener {
    TextView cmp_name,cmp_email,cmp_no;
    Button next;
    ImageView user_img;
    RelativeLayout layout_gone;
    LinearLayout layout_visible;

    private static final int ImageBack1 = 1;
    StorageReference storageReference1;
    ProgressBar pb_userimg;
private int count1=0;
Button okay;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ImageBack1){

            if (resultCode == RESULT_OK){

                pb_userimg.setVisibility(View.VISIBLE);
                //  user_img.setVisibility(View.GONE);

                Uri user_img_uri = data.getData();

                final StorageReference ImageName = storageReference1.child(user_img_uri.getLastPathSegment());

                ImageName.putFile(user_img_uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        ImageName.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Employe");
                                databaseReference.keepSynced(true);
                                databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("profileimg").setValue(String.valueOf(uri)).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                       // Toast.makeText(Employe_detail.this,"Completed",Toast.LENGTH_SHORT).show();

                                        count1=2;
                                        pb_userimg.setVisibility(View.GONE);


                                        //  user_img.setVisibility(View.VISIBLE);
                                    }
                                });
                            }
                        });

                    }
                });

            }

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_employe_detail );
        cmp_name=findViewById( R.id.cmp_name );
        cmp_email=findViewById( R.id.cmp_email );
        cmp_no=findViewById( R.id.cmp_no );
        next=findViewById( R.id.buttonNext );
        user_img=findViewById( R.id.user_img );
        pb_userimg=findViewById( R.id.pb_userimg );
        pb_userimg.setVisibility( View.GONE );
        okay=findViewById( R.id.okay_btn );

        next.setOnClickListener( this );

     layout_gone = findViewById( R.id.main_layout );
        layout_visible = findViewById( R.id.aft_next_ly );

        storageReference1 = FirebaseStorage.getInstance().getReference().child("ProfileImages");


        user_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,ImageBack1);
            }
        });



    }





    @Override
    protected void onStart() {
        super.onStart();
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Employe");
        databaseReference.keepSynced(true);
        databaseReference.orderByChild("uid").equalTo( FirebaseAuth.getInstance()
                .getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    Employe bnd_helper = dataSnapshot1.getValue(Employe.class);

                    String n = bnd_helper.name;
                    String n_one=bnd_helper.email;
                    String n_two=bnd_helper.contactn;
                    cmp_name.setText(n);
                    cmp_email.setText( n_one );
                    cmp_no.setText("+91 "+ n_two );
                    if (bnd_helper.profileimg!=null){
                        Picasso.get().load(bnd_helper.profileimg).into(user_img);
                    }
                    else {
                        user_img.setImageResource(R.drawable.user);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v==next){
int count=count1;



                layout_gone.setVisibility( View.GONE );
                layout_visible.setVisibility( View.VISIBLE );



                okay.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatabaseReference databaseReferencepstatusone = FirebaseDatabase.getInstance().getReference().child("Employe").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                        databaseReferencepstatusone.keepSynced(true);
                        databaseReferencepstatusone.child("profilestatus").setValue("yes");

                        Intent intent = new Intent( Employe_detail.this, Login_Employe.class );
                        startActivity( intent );
                        finish();
                    }
                } );



        }

        }

    }

