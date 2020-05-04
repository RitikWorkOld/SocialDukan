package com.example.socialdukan.Employe.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.socialdukan.Employe.Login_Register_Employe.Model.Employe;
import com.example.socialdukan.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class  edit_prof_emp extends AppCompatActivity implements View.OnClickListener {
    TextView cmp_name,cmp_email,cmp_no;
    Button next;
    ImageView pencil_desc,cross_desc;
    EditText desc;
    TextView desc_view;
    ImageView user_img;
    RelativeLayout layout_gone;
    RelativeLayout layout_visible;
    Button adddesc;

    private FirebaseAuth mFirebaseAuth,mAuth;

    private static final int ImageBack1 = 1;
    StorageReference storageReference1;
    ProgressBar pb_userimg;

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
        setContentView( R.layout.activity_edit_prof_emp );


        cmp_name=findViewById( R.id.cmp_name );
        cmp_email=findViewById( R.id.cmp_email );
        cmp_no=findViewById( R.id.cmp_no );
        next=findViewById( R.id.buttonNext );
        user_img=findViewById( R.id.user_img );
        pb_userimg=findViewById( R.id.pb_userimg );
        pb_userimg.setVisibility( View.GONE );
        okay=findViewById( R.id.okay_btn );
        desc=findViewById( R.id.desc );
        adddesc=findViewById( R.id.add_desc_btn );

desc_view=findViewById( R.id.desc_view );
        pencil_desc=findViewById( R.id.pencil_desc );
        cross_desc=findViewById( R.id.cross_desc );




        mFirebaseAuth = FirebaseAuth.getInstance();




        pencil_desc.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                desc_view.setVisibility( View.GONE );
                desc.setVisibility( View.VISIBLE );
                cross_desc.setVisibility( View.VISIBLE );
                adddesc.setVisibility( View.VISIBLE );
                pencil_desc.setVisibility( View.GONE );

            }
        } );




        adddesc.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!desc.getText().toString().isEmpty()){
                    DatabaseReference databaseReferencecmpexp = FirebaseDatabase.getInstance().getReference().child("Employe").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                    databaseReferencecmpexp.keepSynced(true);
                    databaseReferencecmpexp.child("descrip").setValue(desc.getText().toString());
                    adddesc.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_check_green_24dp, 0, 0, 0 );
                    adddesc.setCompoundDrawablePadding( 5 );
                    adddesc.setText("Added");


                }
                else {
                    desc.setError("Empty");
                }
            }
        } );



        cross_desc.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                desc.setVisibility( View.GONE );
                cross_desc.setVisibility( View.GONE );
                adddesc.setVisibility( View.GONE );
                desc_view.setVisibility( View.VISIBLE );
                pencil_desc.setVisibility( View.VISIBLE );
            }
        } );



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
        databaseReference.orderByChild("eid").equalTo( FirebaseAuth.getInstance()
                .getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    Employe bnd_helper = dataSnapshot1.getValue(Employe.class);

                    String n = bnd_helper.name;
                    String n_one=bnd_helper.email;
                    String n_two=bnd_helper.contactn;
                    String n_three=bnd_helper.descrip;
                    cmp_name.setText(n);
                    cmp_email.setText( n_one );

                    cmp_no.setText("+91 "+ n_two );
                 desc.setText( n_three );
                    if(bnd_helper.descrip==null) {
                        desc_view.setHint( "Describe Your Company" );

                    }
                    else{
                        desc_view.setText( n_three );
                    }


                    if (bnd_helper.profileimg!=null){
                        Picasso.get().load(bnd_helper.profileimg).into(user_img);
                    }
                    else {
                        user_img.setImageResource(R.drawable.empty2);
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
            finish();
        }





        }

    }




