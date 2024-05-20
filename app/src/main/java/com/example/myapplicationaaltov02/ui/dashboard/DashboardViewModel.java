package com.example.myapplicationaaltov02.ui.dashboard;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class DashboardViewModel extends ViewModel {
    private final ArrayList<String> incomingTaskList;
    private final ArrayList<String> previousTaskList;
    private final MutableLiveData<String> currentTaskText;

    public DashboardViewModel() {
        incomingTaskList = new ArrayList<>();
        previousTaskList = new ArrayList<>();

        currentTaskText = new MutableLiveData<>();


        // Read from the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        Log.d(TAG, "Elliot2");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String currentTaskName = dataSnapshot.child("Current Task").child("Task").getValue(String.class);
                currentTaskText.setValue(currentTaskName);

                incomingTaskList.clear();
                previousTaskList.clear();

                for (DataSnapshot snapshot : dataSnapshot.child("Incoming Task").getChildren()) {
                    String task = snapshot.child("Task").getValue(String.class);
                    incomingTaskList.add(task);
                }
                Log.d(TAG, "Elliot3");

                for (DataSnapshot snapshot : dataSnapshot.child("Previous Task").getChildren()) {
                    String task = snapshot.child("Task").getValue(String.class);
                    previousTaskList.add(task);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

        public ArrayList<String> getIncomingTaskList () {
            return incomingTaskList;
        }
        public ArrayList<String> getPreviousTaskList () {
            return previousTaskList;
        }
        public LiveData<String> getCurrentTaskText () {
                return currentTaskText;
            }

    }