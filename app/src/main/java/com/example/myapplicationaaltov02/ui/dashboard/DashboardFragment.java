package com.example.myapplicationaaltov02.ui.dashboard;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Need to refactor
        final TextView currentTasktextView = binding.textCurrentTaskContent;
        dashboardViewModel.getCurrentTaskText().observe(getViewLifecycleOwner(), currentTasktextView::setText);

        // Update incoming task list
        ListView incomingTaskListView = binding.listIncomingTask;
        ArrayList<String> incomingTaskList = dashboardViewModel.getIncomingTaskList();
        ArrayAdapter<String> incomingTaskAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, incomingTaskList);
        incomingTaskListView.setAdapter(incomingTaskAdapter);

        // Update previous task list
        ListView previousTaskListView = binding.listPreviousTask;
        ArrayList<String> previousTaskList = dashboardViewModel.getPreviousTaskList();
        ArrayAdapter<String> previousTaskAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, previousTaskList);
        previousTaskListView.setAdapter(previousTaskAdapter);
        Log.d(TAG, "Elliot1");

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}