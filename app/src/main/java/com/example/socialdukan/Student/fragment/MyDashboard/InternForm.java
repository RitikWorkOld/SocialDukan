package com.example.socialdukan.Student.fragment.MyDashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.socialdukan.R;
import com.example.socialdukan.Student.fragment.Internship.InternDetail;
import com.example.socialdukan.Student.fragment.MyDashboard.applied_intern_md;
import com.example.socialdukan.Student.fragment.Internship.model.internall_md;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class InternForm extends AppCompatActivity {

    TextView intern_name,company_name,ques_1,ques_2,ques_3,answer_1,answer_2,answer_3;
    CircleImageView intern_img;
    String internid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intern_form);

        internid = getIntent().getStringExtra("internid");
        intern_name = findViewById(R.id.intern_name);
        intern_img = findViewById(R.id.intern_img);
        company_name = findViewById(R.id.company_name);
        ques_1 = findViewById(R.id.ques_1);
        answer_1 = findViewById(R.id.answer_1);
        ques_2 = findViewById(R.id.ques_2);
        answer_2 = findViewById(R.id.answer_2);
        ques_3 = findViewById(R.id.ques_3);
        answer_3 = findViewById(R.id.answer_3);
        ques_2.setVisibility( View.GONE );
        ques_3.setVisibility( View.GONE );

        intern_name.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), InternDetail.class);
                intent.putExtra("key",internid);
                startActivity( intent );
            }
        } );

        DatabaseReference db_form = FirebaseDatabase.getInstance().getReference().child("Formsself").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        db_form.keepSynced(true);
        db_form.orderByChild("internid").equalTo(internid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    applied_intern_md value1 = dataSnapshot1.getValue(applied_intern_md.class);

                    if (value1.getAnswer2().equals("QNP")){
                        ques_2.setVisibility(View.GONE);
                        answer_2.setVisibility(View.GONE);
                    }
                    if (value1.getAnswer3().equals("QNP")){
                        ques_3.setVisibility(View.GONE);
                        answer_3.setVisibility(View.GONE);
                    }

                    answer_1.setText(value1.getAnswer1());
                    answer_2.setText(value1.getAnswer2());
                    answer_3.setText(value1.getAnswer3());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        DatabaseReference db_frm = FirebaseDatabase.getInstance().getReference().child("Internships");
        db_frm.keepSynced(true);
        db_frm.orderByChild("id").equalTo(internid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    internall_md value2 = dataSnapshot1.getValue(internall_md.class);


                    if(!value2.getQues1().equals( "Q2. " )){
                        ques_2.setVisibility( View.VISIBLE );
                        ques_2.setText(value2.getQues1());
                    }
                    if(!value2.getQues2().equals( "Q3. " )){
                        ques_3.setVisibility( View.VISIBLE );
                        ques_3.setText(value2.getQues2());
                    }

                    intern_name.setText(value2.getIntname());
                    company_name.setText(value2.getCmpname());
                    Picasso.get().load(value2.getIntimguri()).resize(400,400).into(intern_img);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
