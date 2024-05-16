package com.example.myapplicationaaltov02.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplicationaaltov02.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

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

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}