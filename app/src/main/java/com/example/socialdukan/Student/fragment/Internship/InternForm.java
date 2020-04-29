package com.example.socialdukan.Student.fragment.Internship;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.socialdukan.R;
import com.example.socialdukan.Student.fragment.Internship.model.applied_intern_md;
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
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intern_form);

        key = getIntent().getStringExtra("key");
        intern_name = findViewById(R.id.intern_name);
        intern_img = findViewById(R.id.intern_img);
        company_name = findViewById(R.id.company_name);
        ques_1 = findViewById(R.id.ques_1);
        answer_1 = findViewById(R.id.answer_1);
        ques_2 = findViewById(R.id.ques_2);
        answer_2 = findViewById(R.id.answer_2);
        ques_3 = findViewById(R.id.ques_3);
        answer_3 = findViewById(R.id.answer_3);

        DatabaseReference db_form = FirebaseDatabase.getInstance().getReference().child("Formsself").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        db_form.keepSynced(true);
        db_form.orderByChild("key").equalTo(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    applied_intern_md value1 = dataSnapshot1.getValue(applied_intern_md.class);

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
        db_frm.orderByChild("key").equalTo(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    internall_md value2 = dataSnapshot1.getValue(internall_md.class);

                    ques_2.setText(value2.getQues1());
                    ques_3.setText(value2.getQues2());
                    intern_name.setText(value2.getIntname());
                    company_name.setText(value2.getCmpname());
                    Picasso.get().load(value2.getIntimguri()).into(intern_img);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
