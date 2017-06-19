package com.de.bookedCar;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.de.R;
import com.de.bookedCar.model.Trip;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bhadresh on 12/6/17.
 */

public class bookedCarListAdapter extends RecyclerView.Adapter<bookedCarListAdapter.ViewHolder> {

    FragmentManager fm;
    List<Trip> tripList;

    public bookedCarListAdapter(List<Trip> tripList, FragmentManager fm) {

        this.tripList = tripList;
        this.fm = fm;
    }


    @Override
    public bookedCarListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_booked_car_list, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.txtTripID.setText(tripList.get(position).getTripId());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();

                bundle.putParcelableArrayList("TripData", (ArrayList<? extends Parcelable>) tripList);
                bundle.putInt("Position", position);
                bookedCarDetailFragment frag = new bookedCarDetailFragment();
                frag.setArguments(bundle);
                fm.beginTransaction().replace(R.id.content_frame, frag).addToBackStack(null).commit();
            }
        });



    }


    @Override
    public int getItemCount() {
        return tripList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTripID;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTripID = (TextView) itemView.findViewById(R.id.tv_booked_car_list_trip_id);
        }

    }
}
