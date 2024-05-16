package com.example.myapplicationaaltov02.ui.home;

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

public class HomeViewModel extends ViewModel {
    private final MutableLiveData<String> taskNumberText;
    private final MutableLiveData<String> userText;
    private final MutableLiveData<String> originText;
    private final MutableLiveData<String> targetText;
    private final MutableLiveData<String> shipmentText;
    private final MutableLiveData<String> equipmentText;
    private final MutableLiveData<String> estimatedTimeText;

    public HomeViewModel() {
        taskNumberText = new MutableLiveData<>();
        userText = new MutableLiveData<>();
        originText = new MutableLiveData<>();
        targetText = new MutableLiveData<>();
        shipmentText = new MutableLiveData<>();
        equipmentText = new MutableLiveData<>();
        estimatedTimeText = new MutableLiveData<>();

        // Read from the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Current Task");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    taskNumberText.setValue("Task:" + snapshot.child("Task").getValue(String.class));
                    userText.setValue("Users: " + snapshot.child("Users").getValue(String.class));
                    originText.setValue("Origin: " + snapshot.child("Origin").getValue(String.class));
                    targetText.setValue("Target: " + snapshot.child("Target").getValue(String.class));
                    shipmentText.setValue("Shipment: " + snapshot.child("Shipment").getValue(String.class));
                    equipmentText.setValue("Equipment: " + snapshot.child("Equipment").getValue(String.class));
                    estimatedTimeText.setValue("Estimated Time: " + snapshot.child("Estimated Time").getValue(String.class));

                    break; // Assuming there's only one current task
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    public LiveData<String> getTaskNumberText() {
        return taskNumberText;
    }
    public LiveData<String> getUserText() {
        return userText;
    }
    public LiveData<String> getOriginText() {
        return originText;
    }
    public LiveData<String> getTargetText() {
        return targetText;
    }
    public LiveData<String> getShipmentText() {
        return shipmentText;
    }
    public LiveData<String> getEquipmentText() {
        return equipmentText;
    }
    public LiveData<String> getEstimatedTimeText() {
        return estimatedTimeText;
    }

}