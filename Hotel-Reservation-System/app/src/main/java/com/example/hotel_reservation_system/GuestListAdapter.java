package com.example.hotel_reservation_system;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.Layout;
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
import java.util.List;

public class GuestListAdapter extends RecyclerView.Adapter<GuestListAdapter.ViewHolder> {

    //private List<GuestListData> guestListData;
    View rootView;
    private LayoutInflater layoutInflater;
    private int numberOfGuests;
    ArrayList<String> guestsNameArray = new ArrayList<>();
    ArrayList<String> guestGenderArray = new ArrayList<>();
    Button submitButton;
    Context context;

    boolean isOnTextChanged = false;

    GuestListAdapter(Context context, int numberOfGuests){
        this.layoutInflater = LayoutInflater.from(context);
        this.numberOfGuests = numberOfGuests;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.hotel_guest_details_layout, parent, false);

        rootView = ((Activity) context).getWindow().getDecorView().findViewById(android.R.id.content);
        submitButton = rootView.findViewById(R.id.submit_button);

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
