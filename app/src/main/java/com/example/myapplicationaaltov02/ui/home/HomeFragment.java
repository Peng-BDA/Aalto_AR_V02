package com.example.myapplicationaaltov02.ui.home;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplicationaaltov02.MainActivity;
import com.example.myapplicationaaltov02.databinding.FragmentHomeBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private boolean isTimerRunning;
    private long startTime;
    private Timer timer;
    private int executionTime;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final Button startButton = binding.startButton;
        final Button finishButton = binding.finishButton;
        final Button emergencyButton = binding.emergencyButton;
        final TextView timerView = binding.textTimer;

        final TextView textTaskNumberView = binding.textTaskNumber;
        homeViewModel.getTaskNumberText().observe(getViewLifecycleOwner(), textTaskNumberView::setText);
        final TextView textUserView = binding.textUser;
        homeViewModel.getUserText().observe(getViewLifecycleOwner(), textUserView::setText);
        final TextView textOriginView = binding.textOrigin;
        homeViewModel.getOriginText().observe(getViewLifecycleOwner(), textOriginView::setText);
        final TextView textTargetView = binding.textTarget;
        homeViewModel.getTargetText().observe(getViewLifecycleOwner(), textTargetView::setText);
        final TextView textShipmentView = binding.textShipment;
        homeViewModel.getShipmentText().observe(getViewLifecycleOwner(), textShipmentView::setText);
        final TextView textEquipmentView = binding.textEquipment;
        homeViewModel.getEquipmentText().observe(getViewLifecycleOwner(), textEquipmentView::setText);
        final TextView textEstimatedTimeView = binding.textEstimatedTime;
        homeViewModel.getEstimatedTimeText().observe(getViewLifecycleOwner(), textEstimatedTimeView::setText);

        // timerTextView = findViewById(R.id.timerTextView);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer(timerView);
            }
        });

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTimer();
            }
        });

        emergencyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTimer();
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void startTimer(TextView timerView) {
        if (!isTimerRunning) {
            startTime = SystemClock.elapsedRealtime();
            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    long currentTime = SystemClock.elapsedRealtime();
                    long elapsedTime = currentTime - startTime;
                    int seconds = (int) (elapsedTime / 1000);
                    int minutes = seconds / 60;
                    seconds = seconds % 60;
                    executionTime = seconds;
                    int milliseconds = (int) (elapsedTime % 1000);
                    final String time = String.format("%02d:%02d:%03d", minutes, seconds, milliseconds);
                    timerView.setText(time);
                }
            }, 0, 10);
            isTimerRunning = true;
        } else {
            // Toast.makeText(MainActivity.this, "Timer is already running", Toast.LENGTH_SHORT).show();
        }
    }

    private void stopTimer() {
        if (isTimerRunning) {
            timer.cancel();
            isTimerRunning = false;
            // Write to the database
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("Current Task");

            int minutes = executionTime / 60;
            int seconds = executionTime % 60;
            final String time = String.valueOf(minutes) + " min " + String.valueOf(seconds) + " s";
            myRef.child("Actual Finishing Time").setValue(time);
        } else {
            // Toast.makeText(MainActivity.this, "Timer is not running", Toast.LENGTH_SHORT).show();
        }
    }

}