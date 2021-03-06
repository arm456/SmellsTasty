package com.arm07.android.smellstastyy.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.arm07.android.smellstastyy.R;
import com.arm07.android.smellstastyy.model.Recipes;

public class DirectionsFragment extends Fragment {

    private static final String KEY_CHECK_BOXES ="key_check_boxes" ;
    private CheckBox[] mCheckBoxes;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int index=getArguments().getInt(ViewPagerFragment.KEY_RECIPE_INDEX);
        View view=inflater.inflate(R.layout.fragment_directions,container,false);

        LinearLayout linearLayout=(LinearLayout)view.findViewById(R.id.directionLayout);
        String[] directions= Recipes.directions[index].split("`");

        mCheckBoxes=new CheckBox[directions.length];
        boolean[] checkedBoxes=new boolean[mCheckBoxes.length];
        if(savedInstanceState!=null && savedInstanceState.getBooleanArray(KEY_CHECK_BOXES)!=null){
            checkedBoxes=savedInstanceState.getBooleanArray(KEY_CHECK_BOXES);
        }
        setUpCheckBoxes(directions,linearLayout,checkedBoxes);
        return view;
    }
    private void setUpCheckBoxes(String[] directions, ViewGroup container, boolean[] checkedBoxes){
        int i=0;

        for (String direction:directions){
            mCheckBoxes[i]=new CheckBox(getActivity());
            mCheckBoxes[i].setPadding(8,16,8,16);
            mCheckBoxes[i].setText(direction);
            mCheckBoxes[i].setTextSize(20f);
            container.addView(mCheckBoxes[i]);
            //to save and restoring the checkboxes on rotating the phone
            if (checkedBoxes[i])
                mCheckBoxes[i].toggle();
            i+=1;
        }
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        boolean[] stateOfCheckBoxes=new boolean[mCheckBoxes.length];
        int i=0;
        for (CheckBox mCheckBox:mCheckBoxes) {
            stateOfCheckBoxes[i] = mCheckBox.isChecked();
            i++;
        }
        outState.putBooleanArray(KEY_CHECK_BOXES,stateOfCheckBoxes);
        super.onSaveInstanceState(outState);
    }
}

