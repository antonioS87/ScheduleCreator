package com.example.schedulecreator.fragments;

import android.content.res.Resources;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schedulecreator.DateUtils.CharacterUtil;
import com.example.schedulecreator.Models.Worker;
import com.example.schedulecreator.R;

public class PersonnelRecyclerViewHolder extends RecyclerView.ViewHolder {

    private TextView mWorkerFirstNameTV;
    private TextView mWorkerLastNameTV;
    private TextView mColorCodingView;
    private CardView mCardViewContainer;
    private MutableLiveData<Boolean> mSelected;
    private int mColorPositive;
    private int mColorNegative;


    public PersonnelRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mSelected != null){
                    mSelected.setValue( !mSelected.getValue() );
                    changeBackground( mSelected.getValue() ? mColorPositive : mColorNegative );
                }

            }
        });

        mWorkerLastNameTV = itemView.findViewById( R.id.worker_last_name_tv);
        mWorkerFirstNameTV = itemView.findViewById( R.id.worker_first_name_tv);
        mCardViewContainer = itemView.findViewById( R.id.personnel_list_item_card_view);
        mColorCodingView = itemView.findViewById( R.id.color_coding_view );


    }



    public void changeBackground( int color ){
        mCardViewContainer.setCardBackgroundColor( color );
    }


    public void initializeHolder(Worker worker, Resources resources) {
        Log.d("test_tag_antonio", " itemView is null: " + Boolean.toString( itemView == null));
        mWorkerFirstNameTV.setText( worker.getFirstName() );
        mWorkerLastNameTV.setText( worker.getLastName() );
        mSelected = worker.getSelected();
        mColorPositive = resources.getColor(R.color.colorPrimary, null);
        mColorNegative = resources.getColor(R.color.negative_color, null);
        mColorCodingView.setBackgroundColor( worker.getColor() );

        mColorCodingView.setText(CharacterUtil.getFirstTwoCharactersCRO( worker.getLastName() ));

        changeBackground( mSelected.getValue() ? mColorPositive : mColorNegative );

    }
}
