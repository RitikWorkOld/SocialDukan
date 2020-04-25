package com.example.socialdukan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.socialdukan.fragment.internall_md;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ApplyIntern extends AppCompatActivity {

    TextView ques1,ques2,ques3;
    EditText answer1,answer2,answer3;
    Button fillbtn;
    String key;
    String companyid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_intern);

        key = getIntent().getStringExtra("key");

        ques1 = findViewById(R.id.ques_1);
        ques2 = findViewById(R.id.ques_2);
        ques3 = findViewById(R.id.ques_3);

        answer1 = findViewById(R.id.answer_1);
        answer2 = findViewById(R.id.answer_2);
        answer3 = findViewById(R.id.answer_3);

        fillbtn = findViewById(R.id.fill_btn);

        DatabaseReference dbques = FirebaseDatabase.getInstance().getReference().child("Internships");
        dbques.keepSynced(true);
        dbques.orderByChild("key").equalTo(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){

                    internall_md valueintern = dataSnapshot1.getValue(internall_md.class);

                    ques2.setText(valueintern.getQues1());
                    ques3.setText(valueintern.getQues2());
                    companyid = valueintern.getCompanyid();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        fillbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answer1.getText().toString().isEmpty()){
                    if (!answer2.getText().toString().isEmpty()){
                        if (!answer3.getText().toString().isEmpty()){

                            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Forms").child(companyid).child(key);
                            databaseReference.keepSynced(true);
                            databaseReference.child("answer1").setValue(answer1.getText().toString());
                            databaseReference.child("answer2").setValue(answer2.getText().toString());
                            databaseReference.child("answer3").setValue(answer3.getText().toString());
                            databaseReference.child("key").setValue(key);
                            databaseReference.child("userid").setValue(FirebaseAuth.getInstance().getCurrentUser().getUid());

                            Toast.makeText(ApplyIntern.this,"Done",Toast.LENGTH_SHORT).show();

                        }
                        else {
                            answer3.setError("Empty");
                        }
                    }
                    else {
                        answer2.setError("Empty");
                    }
                }
                else {
                    answer1.setError("Empty");
                }
            }
        });

    }
}
