package com.example.hotel_reservation_system;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

public class ConfirmationNumberFragment extends Fragment {
    TextView confirmation_text_view;
    View view;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.confirmation_number_layout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        confirmation_text_view = view.findViewById(R.id.confirmation_text_view);
        String confirmationNumber = getArguments().getString("confirmationId");
        confirmation_text_view.setText("Dear customer, your reservation confirmation number is " + confirmationNumber + ", We are looking forward to seeing you!");
    }
}
