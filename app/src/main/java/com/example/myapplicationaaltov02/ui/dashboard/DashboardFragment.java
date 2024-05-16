package com.example.myapplicationaaltov02.ui.dashboard;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplicationaaltov02.databinding.FragmentDashboardBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView currentTasktextView = binding.textCurrentTaskContent;
        dashboardViewModel.getCurrentTaskText().observe(getViewLifecycleOwner(), currentTasktextView::setText);

        final TextView incomingTaskTextView1 = binding.textIncomingTaskContent1;
        dashboardViewModel.getIncomingTaskText1().observe(getViewLifecycleOwner(), incomingTaskTextView1::setText);
        final TextView incomingTaskTextView2 = binding.textIncomingTaskContent2;
        dashboardViewModel.getIncomingTaskText2().observe(getViewLifecycleOwner(), incomingTaskTextView2::setText);
        final TextView incomingTaskTextView3 = binding.textIncomingTaskContent3;
        dashboardViewModel.getIncomingTaskText3().observe(getViewLifecycleOwner(), incomingTaskTextView3::setText);
        final TextView incomingTaskTextView4 = binding.textIncomingTaskContent4;
        dashboardViewModel.getIncomingTaskText4().observe(getViewLifecycleOwner(), incomingTaskTextView4::setText);
        final TextView incomingTaskTextView5 = binding.textIncomingTaskContent5;
        dashboardViewModel.getIncomingTaskText5().observe(getViewLifecycleOwner(), incomingTaskTextView5::setText);

        final TextView previousTaskTextView1 = binding.textPreviousTaskContent1;
        dashboardViewModel.getPreviousTaskText1().observe(getViewLifecycleOwner(), previousTaskTextView1::setText);
        final TextView previousTaskTextView2 = binding.textPreviousTaskContent2;
        dashboardViewModel.getPreviousTaskText2().observe(getViewLifecycleOwner(), previousTaskTextView2::setText);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}