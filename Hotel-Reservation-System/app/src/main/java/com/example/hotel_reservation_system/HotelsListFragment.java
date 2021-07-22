package com.example.hotel_reservation_system;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class HotelsListFragment extends Fragment implements ItemClickListener{

    View view;
    TextView headingTextView;
    List<HotelListData> userListResponseData;
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.hotel_list_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //heading text view
        headingTextView = view.findViewById(R.id.heading_text_view);
        progressBar = view.findViewById(R.id.progress_bar);

        String checkInDate = getArguments().getString("check in date");
        String checkOutDate = getArguments().getString("check out date");
        String numberOfGuests = getArguments().getString("number of guests");

        //Set up the header
        headingTextView.setText("Welcome customer, displaying hotel for " + numberOfGuests + " guests staying from " + checkInDate +
                " to " + checkOutDate);

        getHotelListData();
    }

    public void getHotelListData(){

        progressBar.setVisibility(View.VISIBLE);
        Api.getClient().getHotelLists(new Callback<List<HotelListData>>() {
            @Override
            public void success(List<HotelListData> userListResponse, Response response) {
                //map variables from backend to local list
                userListResponseData = userListResponse;

                setupRecyclerView();
            }

            @Override
            public void failure(RetrofitError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(),error.toString(),Toast.LENGTH_LONG).show();

            }
        });

    }

    private void setupRecyclerView(){
        progressBar.setVisibility(View.GONE);
        RecyclerView recyclerView = view.findViewById(R.id.hotel_list_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        HotelListAdapter hotelListAdapter = new HotelListAdapter(getActivity(),userListResponseData);
        recyclerView.setAdapter(hotelListAdapter);

        //Bind the click listener
        hotelListAdapter.setClickListener(this);
    }

    @Override
    public void onClick(View view, int position) {
        HotelListData hotelListData = userListResponseData.get(position);

        String hotelName = hotelListData.getHotel_name();
        int price = hotelListData.getPrice();
        boolean availability = hotelListData.isAvailability();

        String checkInDate = getArguments().getString("check in date");
        String checkOutDate = getArguments().getString("check out date");
        String numberOfGuests = getArguments().getString("number of guests");

        Bundle bundle = new Bundle();
        bundle.putString("hotel name", hotelName);
        bundle.putInt("hotel price", price);
        bundle.putBoolean("hotel availability", availability);
        bundle.putString("check in date", checkInDate);
        bundle.putString("check out date", checkOutDate);
        bundle.putString("number of guests", numberOfGuests);

        HotelGuestListDetailsFragment hotelGuestListDetailsFragment = new HotelGuestListDetailsFragment();
        hotelGuestListDetailsFragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.remove(HotelsListFragment.this);
        fragmentTransaction.replace(R.id.main_layout, hotelGuestListDetailsFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();


    }
}
