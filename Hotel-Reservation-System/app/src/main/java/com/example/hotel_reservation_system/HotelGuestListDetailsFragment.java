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

public class HotelGuestListDetailsFragment extends Fragment {

    View view;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.hotel_guest_details_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        TextView hotelRecapTextView = view.findViewById(R.id.hotel_recap_text_view);

        String hotelName = getArguments().getString("hotel name");
        int hotelPrice = getArguments().getInt("hotel price");
        Boolean hotelAvailability = getArguments().getBoolean("hotel availability");

        hotelRecapTextView.setText("You have selected" + hotelName + "at the price of " + hotelPrice + ", and the availability is " + hotelAvailability);

    }
}
