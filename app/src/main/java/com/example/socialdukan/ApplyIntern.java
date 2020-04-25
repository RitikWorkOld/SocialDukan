package com.example.socialdukan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.socialdukan.fragment.internall_md;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ApplyIntern extends AppCompatActivity {

    TextView ques1,ques2,ques3;
    TextInputLayout answer1,answer2,answer3;
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

        answer1 = (TextInputLayout) findViewById(R.id.answer_1);
        answer2 = findViewById(R.id.answer_2);
        answer3 = findViewById(R.id.answer_3);
        TextView title = (TextView) findViewById(R.id.title);
        SpannableString content = new SpannableString("SocialDukan Application");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        title.setText(content);

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
                if (!answer1.toString().isEmpty()){
                    if (!answer2.toString().isEmpty()){
                        if (!answer3.toString().isEmpty()){

                            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Forms").child(companyid).child(key);
                            databaseReference.keepSynced(true);
                            databaseReference.child("answer1").setValue(answer1.toString());
                            databaseReference.child("answer2").setValue(answer2.toString());
                            databaseReference.child("answer3").setValue(answer3.toString());
                            databaseReference.child("key").setValue(key);
                            databaseReference.child("userid").setValue(FirebaseAuth.getInstance().getCurrentUser().getUid());
                            databaseReference.child("companyid").setValue(companyid);

                            DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference().child("Formsself").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(key);
                            databaseReference1.keepSynced(true);
                            databaseReference1.child("answer1").setValue(answer1.toString());
                            databaseReference1.child("answer2").setValue(answer2.toString());
                            databaseReference1.child("answer3").setValue(answer3.toString());
                            databaseReference1.child("key").setValue(key);
                            databaseReference1.child("userid").setValue(FirebaseAuth.getInstance().getCurrentUser().getUid());
                            databaseReference1.child("companyid").setValue(companyid);

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
