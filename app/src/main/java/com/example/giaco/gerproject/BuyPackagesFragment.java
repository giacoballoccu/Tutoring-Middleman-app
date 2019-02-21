package com.example.giaco.gerproject;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class BuyPackagesFragment extends Fragment {
    LinearLayout mparent;
    LayoutInflater layoutInflater;
    View myView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.buy_packages, container, false);
    }


    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Acquista pacchetti");
        mparent = view.findViewById(R.id.parentview);
        layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        myView = layoutInflater.inflate(R.layout.ore_rimanenti_static, null, false);
        mparent.addView(myView);

        myView = layoutInflater.inflate(R.layout.single_pack, null, false);
        mparent.addView(myView);

        myView = layoutInflater.inflate(R.layout.single_pack, null, false);
        mparent.addView(myView);

    }
}
