package com.de.bookCar;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.de.R;
import com.de.bookCar.model.CarList;
import com.de.bookCar.model.DriverList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bhadresh on 14/6/17.
 */

public class bookCarListAdapter extends RecyclerView.Adapter<bookCarListAdapter.viewHolder> {


    List<CarList> carLists;
    List<DriverList> driverLists;
    FragmentManager fm;
    Context context;
    String fromDate, toDate;

    public bookCarListAdapter(List<CarList> carLists, List<DriverList> driverLists, FragmentManager fm, Context context, String fromDate, String toDate) {
        this.carLists = carLists;
        this.driverLists = driverLists;
        this.fm = fm;
        this.context = context;
        this.fromDate = fromDate;
        this.toDate = toDate;

    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_search_car_booking, parent, false);

        return new bookCarListAdapter.viewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final viewHolder holder, final int position) {
        holder.textCarno.setText(carLists.get(position).getCarNumber());
        holder.textCarType.setText(carLists.get(position).getCarName() + " , " + carLists.get(position).getCarType());
        holder.carAvilableTime.setText(carLists.get(position).getAvailableTime());
        holder.carBufferTime.setText(carLists.get(position).getBufferedTime());
        holder.carEndTime.setText(carLists.get(position).getBookedTime());


        driverSpinnerAdapter adapter = new driverSpinnerAdapter(context, R.layout.layout_spinner_backgroung, driverLists);
        holder.spinnerDriver.setAdapter(adapter);

        holder.buttonBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("carList", (ArrayList<? extends Parcelable>) carLists);
                bundle.putParcelableArrayList("driverList", (ArrayList<? extends Parcelable>) driverLists);
                bundle.putInt("carPosition", position);
                bundle.putInt("driverPosition", holder.spinnerDriver.getSelectedItemPosition());
                bundle.putString("fromDate", fromDate);
                bundle.putString("toDate", toDate);

                bookCarInformation fragment = new bookCarInformation();
                fragment.setArguments(bundle);
                fm.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return carLists.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView textCarno, textCarType, carAvilableTime, carBufferTime, carEndTime;
        Spinner spinnerDriver;
        Button buttonBooking;

        public viewHolder(View itemView) {
            super(itemView);

            textCarno = (TextView) itemView.findViewById(R.id.txt_searchcar_carno);
            textCarType = (TextView) itemView.findViewById(R.id.txt_searchcar_cartype);
            carAvilableTime = (TextView) itemView.findViewById(R.id.txt_searchcar_caravilaable_time);
            carBufferTime = (TextView) itemView.findViewById(R.id.txt_searchcar_carbuffer_time);
            carEndTime = (TextView) itemView.findViewById(R.id.txt_searchcar_car_end_time);
            spinnerDriver = (Spinner) itemView.findViewById(R.id.spinner_driver);
            buttonBooking = (Button) itemView.findViewById(R.id.btn_bookcar_booking);

        }
    }
}
