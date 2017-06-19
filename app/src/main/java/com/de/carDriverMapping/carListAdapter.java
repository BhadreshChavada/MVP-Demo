package com.de.carDriverMapping;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.de.R;
import com.de.carDriverMapping.model.CarList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bhadresh on 10/6/17.
 */

public class carListAdapter extends RecyclerView.Adapter<carListAdapter.ViewHolder> {

    List<CarList> carList;
    FragmentManager fm;


    carListAdapter(List<CarList> carList, FragmentManager fm) {

        this.carList = carList;
        this.fm = fm;

    }


    @Override
    public carListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_cardriver_mapping, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {


        holder.txtCarno.setText(carList.get(position).getCarNumber());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("CarData", (ArrayList<? extends Parcelable>) carList);
                bundle.putInt("Position", position);
                assignDriverFragment frag = new assignDriverFragment();
                frag.setArguments(bundle);
                fm.beginTransaction().replace(R.id.content_frame, frag).addToBackStack(null).commit();


            }
        });
    }


    @Override
    public int getItemCount() {
        return carList.size();

    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtCarno;

        public ViewHolder(View itemView) {
            super(itemView);
            txtCarno = (TextView) itemView.findViewById(R.id.tv_car_number);
        }


    }


}
