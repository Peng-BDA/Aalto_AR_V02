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

import java.util.Iterator;

public class DashboardViewModel extends ViewModel {

    private final MutableLiveData<String> currentTaskText;
    private final MutableLiveData<String> incomingTaskText1;
    private final MutableLiveData<String> incomingTaskText2;
    private final MutableLiveData<String> incomingTaskText3;
    private final MutableLiveData<String> incomingTaskText4;
    private final MutableLiveData<String> incomingTaskText5;
    private final MutableLiveData<String> previousTaskText1;
    private final MutableLiveData<String> previousTaskText2;

    public DashboardViewModel() {
        currentTaskText = new MutableLiveData<>();
        incomingTaskText1 = new MutableLiveData<>();
        incomingTaskText2 = new MutableLiveData<>();
        incomingTaskText3 = new MutableLiveData<>();
        incomingTaskText4 = new MutableLiveData<>();
        incomingTaskText5 = new MutableLiveData<>();
        previousTaskText1 = new MutableLiveData<>();
        previousTaskText2 = new MutableLiveData<>();

        // Read from the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String currentTaskName = dataSnapshot.child("Current Task").child("Task").getValue(String.class);
                currentTaskText.setValue(currentTaskName);

                Iterator<DataSnapshot> incomingTaskIterator = dataSnapshot.child("Incoming Task").getChildren().iterator();
                incomingTaskText1.setValue(incomingTaskIterator.next().child("Task").getValue(String.class));
                incomingTaskText2.setValue(incomingTaskIterator.next().child("Task").getValue(String.class));
                incomingTaskText3.setValue(incomingTaskIterator.next().child("Task").getValue(String.class));
                incomingTaskText4.setValue(incomingTaskIterator.next().child("Task").getValue(String.class));
                incomingTaskText5.setValue(incomingTaskIterator.next().child("Task").getValue(String.class));

                Iterator<DataSnapshot> PreviousTaskIterator = dataSnapshot.child("Previous Task").getChildren().iterator();
                previousTaskText1.setValue(PreviousTaskIterator.next().child("Task").getValue(String.class));
                previousTaskText2.setValue(PreviousTaskIterator.next().child("Task").getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

        public LiveData<String> getCurrentTaskText () {
            return currentTaskText;
        }
        public LiveData<String> getIncomingTaskText1 () {
            return incomingTaskText1;
        }
        public LiveData<String> getIncomingTaskText2 () {
            return incomingTaskText2;
        }
        public LiveData<String> getIncomingTaskText3 () {
            return incomingTaskText3;
        }
        public LiveData<String> getIncomingTaskText4 () {
            return incomingTaskText4;
        }
        public LiveData<String> getIncomingTaskText5 () {
            return incomingTaskText5;
        }
        public LiveData<String> getPreviousTaskText1 () {
            return previousTaskText1;
        }
        public LiveData<String> getPreviousTaskText2 () {
            return previousTaskText2;
        }
    }