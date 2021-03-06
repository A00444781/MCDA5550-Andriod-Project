package com.example.hotel_reservation_system;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.fragment.app.FragmentTransaction;

import org.jetbrains.annotations.NotNull;

public class HotelGuestListDetailsFragment extends Fragment {

    View view;
    TextView hotel_name_text_view, check_in_text_view, check_out_text_view, hotel_price_text_view, temp_confirmation_number_text_view;
    Button submit_button, next_page_button;
    Integer guests_number;

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
        hotel_name_text_view = view.findViewById(R.id.guest_hotel_name_text_view);
        check_in_text_view = view.findViewById(R.id.guest_check_in_text_view);
        check_out_text_view = view.findViewById(R.id.guest_check_out_text_view);
        hotel_price_text_view = view.findViewById(R.id.hotel_price_text_view);
        temp_confirmation_number_text_view = view.findViewById(R.id.temp_confirmation_number_text_view);
        next_page_button = view.findViewById(R.id.next_page_button);
        submit_button = view.findViewById(R.id.submit_button);

        String hotelName = getArguments().getString("hotel name");
        String hotelPrice = String.valueOf(getArguments().getInt("hotel price")) + "$";
        String checkInDate = getArguments().getString("check in date");
        String checkOutDate = getArguments().getString("check out date");
        //save guests number into model
        guests_number = Integer.valueOf(getArguments().getString("number of guests"));


        hotel_name_text_view.setText(hotelName);
        check_in_text_view.setText(checkInDate);
        check_out_text_view.setText(checkOutDate);
        hotel_price_text_view.setText(hotelPrice);

        RecyclerView recyclerView = view.findViewById(R.id.guests_list_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        GuestListAdapter guestListAdapter = new GuestListAdapter(getActivity(),guests_number);
        recyclerView.setAdapter(guestListAdapter);

        next_page_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String confirmationId = temp_confirmation_number_text_view.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("confirmationId", confirmationId);
                HotelGuestListDetailsFragment hotelGuestListDetailsFragment = new HotelGuestListDetailsFragment();
                hotelGuestListDetailsFragment.setArguments(bundle);

                ConfirmationNumberFragment confirmationNumberFragment = new ConfirmationNumberFragment();
                confirmationNumberFragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_layout,confirmationNumberFragment);
                fragmentTransaction.remove(HotelGuestListDetailsFragment.this);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

    }
}
