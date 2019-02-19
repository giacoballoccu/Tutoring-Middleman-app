package com.example.giaco.gerproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PersonalPageFragment extends Fragment implements View.OnClickListener {
    private UserStudente loggedUser;
    ImageView userImg;
    String userImgStr;
    TextView userName, hours;
    Button recharge;
    Button editProfile;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (getArguments() != null) {
            this.loggedUser = getArguments().getParcelable("actualUser");
        }
        return inflater.inflate(R.layout.fragment_personal_page, container, false);
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.loggedUser = getArguments().getParcelable("actualUser");
        }
        // do your variables initialisations here except Views!!!
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("I Miei Dati");
        if (getArguments() != null) {
            this.loggedUser = getArguments().getParcelable("actualUser");
        }

        userName = (TextView) view.findViewById(R.id.username);
        hours = (TextView) view.findViewById(R.id.hours);
        userImg = (ImageView) view.findViewById(R.id.profileImg);
        recharge = (Button) view.findViewById(R.id.rechargeButton);
        editProfile = (Button) view.findViewById(R.id.editButton);

        /*Dinamic data*/
        userName.setText("" + loggedUser.getName() + " " + loggedUser.getSurname() + "");
        hours.setText("" + loggedUser.getHours() + "");

        if (TextUtils.isEmpty(loggedUser.getImgSrc())) {
            userImg.setBackgroundResource(R.drawable.emptyimg);
            userImg.setMaxWidth(50);
            userImg.setMaxHeight(50); //forse dobbiamo usare i bitmap perchè dalla galleria si prendono quelli
        }/*else{
            userImg.setBackground();
        }*/

        recharge.setOnClickListener(this);
        editProfile.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rechargeButton: {
                BuyPackagesFragment buyPackagesFragment = new BuyPackagesFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, buyPackagesFragment, "Buy Packages").commit();
                break;
            }
            case R.id.editButton: {
                EditProfileFragment editProfileFragment = new EditProfileFragment(); //Manca passaggio dati
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, editProfileFragment, "Edit Profile").commit();
                break;
            }
        }
    }
}