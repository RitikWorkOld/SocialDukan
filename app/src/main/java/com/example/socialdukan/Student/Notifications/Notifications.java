package com.example.socialdukan.Student.Notifications;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialdukan.R;
import com.example.socialdukan.Student.Notifications.Customised.BucketRecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class Notifications extends AppCompatActivity {

    private BucketRecyclerView recyclerView;
    private ArrayList<Noti_Helper> arrayList;
    private FirebaseRecyclerOptions<Noti_Helper> options;
    private FirebaseRecyclerAdapter<Noti_Helper,Notification_ViewHolder> adapter;
    private DatabaseReference databaseReference;
    private View no_new_notifications;


    protected void onStart(){
        super.onStart();
        adapter.startListening();
    }

    protected void onStop(){
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature( Window.FEATURE_NO_TITLE);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView( R.layout.activity_notifications);

        no_new_notifications = findViewById(R.id.no_new_notifications);


        recyclerView = findViewById(R.id.noti_rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.showIfEmpty(no_new_notifications);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("notification")
        .child( FirebaseAuth.getInstance().getCurrentUser().getUid());
        databaseReference.keepSynced(true);

        arrayList = new ArrayList<Noti_Helper>();

        options = new FirebaseRecyclerOptions.Builder<Noti_Helper>().setQuery(databaseReference,Noti_Helper.class).build();

        adapter = new FirebaseRecyclerAdapter<Noti_Helper, Notification_ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final Notification_ViewHolder notification_viewHolder, int i, @NonNull final Noti_Helper noti_helper) {

                notification_viewHolder.Title.setText(noti_helper.getTitle());
                notification_viewHolder.Description.setText(noti_helper.getDesc());
                notification_viewHolder.Date.setText(noti_helper.getDate());     //added



                final String notiid = noti_helper.getNotiid();

                /*if (type != null){
                    notification_viewHolder.notification_trigger.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            switch (type)
                            {
                                case "TECH" :
                                    Intent intent = new Intent(Notifications.this, EventsMain.class);
                                    intent.putExtra("type",type);
                                    startActivity(intent);
                                    break;
                                case "FUN" :
                                    Intent intent1 = new Intent(Notifications.this, EventsMain.class);
                                    intent1.putExtra("type",type);
                                    startActivity(intent1);
                                    break;
                                case "CORP" :
                                    Intent intent2 = new Intent(Notifications.this, EventsMain.class);
                                    intent2.putExtra("type",type);
                                    startActivity(intent2);
                                    break;
                                case "FEB" :
                                    Intent intent3 = new Intent(Notifications.this, Shedule.class);
                                    intent3.putExtra("type",type);
                                    startActivity(intent3);
                                    break;
                                case "MAR" :
                                    Intent intent4 = new Intent(Notifications.this, Shedule.class);
                                    intent4.putExtra("type",type);
                                    startActivity(intent4);
                                    break;
                            }
                        }
                    });
                }*/


            }
            @NonNull
            @Override
            public Notification_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new Notification_ViewHolder( LayoutInflater.from(Notifications.this).inflate(R.layout.notification_card,parent,false));
            }
        };
        recyclerView.setAdapter(adapter);


        ItemTouchHelper.SimpleCallback simpleCallback =
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                          RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                        //Toast.makeText(Notifications.this,"swiped",Toast.LENGTH_SHORT).show();
                        int id = viewHolder.getAdapterPosition();
                        String notiid = adapter.getItem(id).getNotiid();

                        FirebaseDatabase.getInstance().getReference().child("notification")
                                .child( FirebaseAuth.getInstance().getCurrentUser().getUid()).child(notiid).removeValue();
                        //Toast.makeText(Notifications.this,"Sucess "+notiid,Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                        new RecyclerViewSwipeDecorator.Builder(Notifications.this,c,recyclerView,viewHolder,dX,dY,actionState,isCurrentlyActive)
                                .addSwipeRightActionIcon(R.drawable.ic_delete_black_24dp)
                                .addSwipeRightBackgroundColor(getResources().getColor(R.color.purple))
                                .create()
                                .decorate();

                        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                    }
                };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

  /*  @Override
    public void onBackPressed() {
        Intent intent = new Intent(Notifications.this, Dashboard.class);
        startActivity(intent);
        finish();
    }*/
}
