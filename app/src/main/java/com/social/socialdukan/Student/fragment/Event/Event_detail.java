package com.social.socialdukan.Student.fragment.Event;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.socialdukan.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.social.socialdukan.Student.Miscellaneous.User;
import com.social.socialdukan.Student.fragment.Event.FAQ.FAQ_Managr;
import com.social.socialdukan.Student.fragment.Internship.model.EventRegistered;
import com.social.socialdukan.Student.fragment.profile.models.College_md;
import com.squareup.picasso.Picasso;

import java.util.Objects;
public class Event_detail extends Fragment {

    private TextView Title_dt, insta_handle, link, event_location, date, event_type, fb_handle, fb_title, link_title, insta_title, participants, range, amount, indi_team;
    private TextView Descp_dt, Desc1_dt, Desc2_dt;
    private Button Register_dt, registered, showpass;
    private ImageView Mimg_dt;
    private TextView readless;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private DatabaseReference databaseReferencedetail;
    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference databaseReferencecmpexp;
    private TextView faq;
    private String useremail, usernumber, username;
    private String colg;
    private String teamid;

    public Event_detail() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_event_detail, container, false);
        final Bundle bundle = getArguments();

        // Initialize UI elements
        initializeUI(view);

        // Load data from the bundle
        if (bundle != null) {
            loadDataFromBundle(bundle);
        }

        // Fetch user profile data and check if already registered
        if (bundle != null) {
            String eventId = bundle.getString("key");  // Get event ID from the bundle
            fetchUserProfileData(() -> {
                fetchEventDataFromFirebase(bundle);
            }, eventId);
        }

        // Set up button click listeners
        setupButtonListeners(bundle);

        mSwipeRefreshLayout.setOnRefreshListener(() -> refreshFragment(bundle));

        return view;
    }


    // Initialize UI components
    private void initializeUI(View view) {
        Title_dt = view.findViewById(R.id.event_title);
        participants = view.findViewById(R.id.participants);
        fb_handle = view.findViewById(R.id.fb_handle);
        showpass = view.findViewById(R.id.show_pass);
        range = view.findViewById(R.id.range);
        fb_title = view.findViewById(R.id.fb_title);
        insta_title = view.findViewById(R.id.insta_title);
        amount = view.findViewById(R.id.amount);
        indi_team = view.findViewById(R.id.per_team_indiv);
        link_title = view.findViewById(R.id.link_title);
        event_location = view.findViewById(R.id.location_desc);
        date = view.findViewById(R.id.event_date);
        event_type = view.findViewById(R.id.event_type);
        Descp_dt = view.findViewById(R.id.event_descp);
        Desc1_dt = view.findViewById(R.id.event_descp1);
        insta_handle = view.findViewById(R.id.insta_handle);
        link = view.findViewById(R.id.link);
        Desc2_dt = view.findViewById(R.id.event_descp2);
        Mimg_dt = view.findViewById(R.id.event_main_img1);
        Register_dt = view.findViewById(R.id.register_events);
        faq = view.findViewById(R.id.faq_btn);
        registered = view.findViewById(R.id.registered);
        readless = view.findViewById(R.id.read_less_events);
        mSwipeRefreshLayout = view.findViewById(R.id.swipeToRefresh);

        showpass.setVisibility(View.GONE);
    }

    // Load data from Bundle into respective fields
    private void loadDataFromBundle(Bundle bundle) {
        String eventName = bundle.getString("Name");
        String description = bundle.getString("Descp");
        String desc1 = bundle.getString("Desc1");
        String desc2 = bundle.getString("Desc2");
        String imageUrl = bundle.getString("Mimguri");
        String maxParticipants = bundle.getString("number_of_member");
        String eventDate = bundle.getString("event_date");
        String fbLink = bundle.getString("fb");
        String instaHandle = bundle.getString("insta");
        String websiteLink = bundle.getString("website");
        String amountStr = bundle.getString("amt");
        String locationStr = bundle.getString("location");

        // Setting text fields
        Title_dt.setText(eventName);
        Descp_dt.setText(description);
        Desc1_dt.setVisibility(View.GONE);
        Desc2_dt.setVisibility(View.GONE);
        Desc1_dt.setText(desc1);
        Desc2_dt.setText(desc2);
        event_type.setVisibility(View.GONE);
        date.setText(eventDate);
        event_location.setText(locationStr);
        participants.setText(maxParticipants);
        amount.setText(amountStr.equals("0") ? "Free" : "Rs " + amountStr);

        // Conditionally handle Instagram, Facebook, and Website Links
        handleOptionalFields(instaHandle, insta_handle, insta_title);
        handleOptionalFields(fbLink, fb_handle, fb_title);
        handleOptionalFields(websiteLink, link, link_title);

        // Load event image using Picasso
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Picasso.get().load(imageUrl).resize(400, 400).into(Mimg_dt);
        }
    }

    // Set up click listeners for buttons
    private void setupButtonListeners(Bundle bundle) {

        // "Read Less" button click listener
        readless.setOnClickListener(v -> {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager.popBackStack();
        });

        // "Register" button click listener
        Register_dt.setOnClickListener(v -> {
            handleEventRegistration(bundle);
        });

        // "FAQ" button click listener
        faq.setOnClickListener(v -> {
            openFAQFragment(bundle.getString("key"));
        });
    }

    private void handleEventRegistration(Bundle bundle) {
        String amt = bundle.getString("amt");
        String eventId = bundle.getString("key");

        if (amt.equals("0")) {
            // Free event: Show confirmation dialog and save data to Firebase
            AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
            builder.setTitle(R.string.app_name)
                    .setIcon(R.drawable.social_dukan)
                    .setMessage("Please confirm your registration")
                    .setCancelable(false)
                    .setPositiveButton("Yes", (dialog, id) -> {
                        // Save data to Firebase and show toast
                        saveRegistrationData(eventId);
                    })
                    .setNegativeButton("No", (dialog, id) -> dialog.cancel());

            AlertDialog alert = builder.create();
            alert.show();
        } else {
            Toast.makeText(getActivity(), "Not Working Right now, ", Toast.LENGTH_SHORT).show();
            // Paid event: Navigate to payment or team joining screen
//            Intent intent = new Intent(getActivity(), JoinTeamIndi.class);
//            intent.putExtra("amt", amt);
//            intent.putExtra("eventid", eventId);
//            startActivity(intent);
        }
    }

    private void saveRegistrationData(String eventId) {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // Get reference to Firebase database
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("eventRegister").child(userId).child(eventId);

        // Create a map of the registration data
        DatabaseReference eventRef = FirebaseDatabase.getInstance().getReference("Events").child(eventId);
        eventRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot eventSnapshot) {
                if (eventSnapshot.exists()) {
                    // Save event details to the event registration path
                    databaseRef.child("username").setValue(username);
                    databaseRef.child("email").setValue(useremail);
                    databaseRef.child("uid").setValue(userId);
                    databaseRef.child("event").setValue(eventSnapshot.getValue());
                    databaseRef.child("status").setValue("Registered");

                    // Show toast and update button
                    Toast.makeText(getActivity(), "Registration Confirmed", Toast.LENGTH_SHORT).show();
                    Register_dt.setText("Enrolled");
                    Register_dt.setEnabled(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), "Error during registration", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openFAQFragment(String eventKey) {
        // Navigate to FAQ fragment
        Bundle faqBundle = new Bundle();
        faqBundle.putString("key", eventKey);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        FAQ_Managr faqFragment = new FAQ_Managr();
        faqFragment.setArguments(faqBundle);

        fragmentTransaction.replace(R.id.frame, faqFragment);
        fragmentTransaction.addToBackStack("faq");
        fragmentTransaction.commit();
    }

    private void handleOptionalFields(String data, TextView field, TextView title) {
        if (data == null || data.isEmpty()) {
            field.setVisibility(View.GONE);
            title.setVisibility(View.GONE);
        } else {
            field.setVisibility(View.VISIBLE);
            title.setVisibility(View.VISIBLE);
            field.setText(data);
        }
    }

    private void fetchUserProfileData(final Runnable onComplete, String eventId) {
        // Fetch user data
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        databaseReference.orderByChild("uid").equalTo(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    User user = dataSnapshot1.getValue(User.class);
                    useremail = user.getEmail();
                    username = user.getName();
                    usernumber = user.getContactn();
                }
                // After fetching user data, check if the user is already registered for the event
                checkIfAlreadyRegistered(userId, eventId);
                onComplete.run(); // Continue to event data fetching after user data is loaded
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });
    }
    private void checkIfAlreadyRegistered(String userId, String eventId) {
        DatabaseReference registrationRef = FirebaseDatabase.getInstance().getReference("eventRegister").child(userId).child(eventId);

        registrationRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // User is already registered for the event
                    Register_dt.setText("Enrolled");
                    Register_dt.setEnabled(false);  // Disable the button
                } else {
                    // User is not registered for the event, enable the button
                    Register_dt.setText("Enroll Yourself");
                    Register_dt.setEnabled(true);   // Enable the button
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), "Error checking registration status", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void fetchEventDataFromFirebase(Bundle bundle) {
        // Extract event details from bundle
        final String key = bundle.getString("key");

        databaseReferencedetail = FirebaseDatabase.getInstance().getReference().child("Events");
        databaseReferencedetail.orderByChild("eventid").equalTo(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                handleEventDetails(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });
    }

    private void handleEventDetails(DataSnapshot dataSnapshot) {
        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
            final events_md event = dataSnapshot1.getValue(events_md.class);

            // Populate event details
            if (event != null) {
                populateEventDetails(event);
            }
        }
    }

    private void populateEventDetails(events_md event) {
        Title_dt.setText(event.getEventname());
        Descp_dt.setText(event.getEventdesc());
        date.setText(event.getEventdate());
        event_location.setText(event.getLocation());
        amount.setText(event.getAmount().equals("0") ? "Free" : "Rs " + event.getAmount());
        participants.setText(event.getMax_number());

        // Handle Instagram, Facebook, and Website Links
        handleOptionalFields(event.getEvent_insta_handle(), insta_handle, insta_title);
        handleOptionalFields(event.getEvent_fb_link(), fb_handle, fb_title);
        handleOptionalFields(event.getWebsite_link(), link, link_title);
    }

    private void refreshFragment(Bundle bundle) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Event_detail eventsDetails = new Event_detail();
        eventsDetails.setArguments(bundle);

        fragmentTransaction.replace(R.id.frame, eventsDetails);
        fragmentTransaction.addToBackStack("tech1");
        fragmentTransaction.commit();
    }
}
