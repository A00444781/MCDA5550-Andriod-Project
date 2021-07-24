package com.example.hotel_reservation_system;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GuestListAdapter extends RecyclerView.Adapter<GuestListAdapter.ViewHolder> {

    //private List<GuestListData> guestListData;
    View rootView;
    private LayoutInflater layoutInflater;
    private int numberOfGuests;
    ArrayList<String> guestsNameArray = new ArrayList<>();
    ArrayList<String> guestGenderArray = new ArrayList<>();
    Button submitButton, nextPageButton;
    Context context;
    TextView check_in_text_view, check_out_text_view, hotel_name_text_view, temp_confirmation_number_text_view;

    boolean isOnTextChanged = false;

    GuestListAdapter(Context context, int numberOfGuests){
        this.layoutInflater = LayoutInflater.from(context);
        this.numberOfGuests = numberOfGuests;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.hotel_guest_details_layout, parent, false);
        context = parent.getContext();
        rootView = ((Activity) context).getWindow().getDecorView().findViewById(android.R.id.content);
        submitButton = rootView.findViewById(R.id.submit_button);
        nextPageButton = rootView.findViewById(R.id.next_page_button);
        check_in_text_view = rootView.findViewById(R.id.guest_check_in_text_view);
        check_out_text_view = rootView.findViewById(R.id.guest_check_out_text_view);
        hotel_name_text_view = rootView.findViewById(R.id.guest_hotel_name_text_view);
        temp_confirmation_number_text_view = rootView.findViewById(R.id.temp_confirmation_number_text_view);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        //set guest n
        int id = position + 1;
        holder.guestNumber.setText("Guest " + id);
        EditText guestName = holder.guestName;
        RadioGroup guestGender = holder.radioGroup;

        guestName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isOnTextChanged = true;
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (isOnTextChanged) {
                    isOnTextChanged = false;

                    try {
                        for (int i = 0; i <= position; i++) {
                            if (i != position) {
                                guestsNameArray.add("");
                            }
                            else{
                                guestsNameArray.add("");
                                guestsNameArray.set(position, s.toString());
                            }
                        }

                    } catch (NumberFormatException e) {

                    }
                }
            }
        });

        guestGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for(int i=0; i<=position; i++){
                    if (i != position) {
                        guestGenderArray.add("");
                    }
                    else{
                        guestGenderArray.add("");
                        if(checkedId == 2131230952){
                            guestGenderArray.set(position, "2");
                        }else{
                            guestGenderArray.set(position, "1");
                        }
                    }
                }
                Log.d("guest gender", guestGenderArray.toString());
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ReservationDetails reservationDetails = new ReservationDetails(hotel_name_text_view.getText().toString(), check_in_text_view.getText().toString(), check_out_text_view.getText().toString());
                //Guest guest = new Guest(name, gender);
                for (int i = 0; i < numberOfGuests; i++){
                    Guest guest = new Guest(guestsNameArray.get(i), Integer.valueOf(guestGenderArray.get(i)));
                    reservationDetails.addGuest(guest);
                }

                Api.getClient().getConfirmationId(reservationDetails).enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        int confirmationId = response.body();
                        //Log.d("confirmation number", String.valueOf(confirmationId));
                        temp_confirmation_number_text_view.setText(String.valueOf(confirmationId));
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {

                    }
                });

                submitButton.setVisibility(View.GONE);
                nextPageButton.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public int getItemCount() {
            return numberOfGuests;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView guestNumber;
        EditText guestName;
        RadioGroup radioGroup;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            guestName = itemView.findViewById(R.id.guest_name_edit_text);
            guestNumber = itemView.findViewById(R.id.guest_number_text_view);
            radioGroup = itemView.findViewById(R.id.radioSex);

        }
    }
}
