package com.example.giaco.gerproject;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.giaco.gerproject.Classes.Package;
import com.example.giaco.gerproject.Classes.PackageFactory;
import com.example.giaco.gerproject.Classes.UserStudente;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class BuyPackagesFragment extends Fragment {

    TextView aux;
    LinearLayout mparent;
    String emailLoggedUser;
    UserStudente loggedUser;
    LayoutInflater layoutInflater;
    View myView, staticView;
    PackageFactory factory = PackageFactory.getInstance();
    ArrayList<Package> packList = factory.getPackages();
    int ore_rimaste;
    Button b;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.buy_packages, container, false);
    }


    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Acquista pacchetti");
        if (getArguments() != null) {
            emailLoggedUser = getArguments().getString("actualUserMail");
        }
        mparent = view.findViewById(R.id.parentview);
        layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        staticView = layoutInflater.inflate(R.layout.ore_rimanenti_static, null, false);
        mparent.addView(staticView);
        aux = staticView.findViewById(R.id.n_ore_rimanenti);
        ore_rimaste = Integer.parseInt(aux.getText().toString());

        for (int i = 0; i < packList.size(); i++) {
            myView = layoutInflater.inflate(R.layout.single_pack, null, false);
            mparent.addView(myView);
            b = myView.findViewById(R.id.pulsante_acquista);
            updatePackage(packList.get(i), myView);
        }
    }

    public void updatePackage (Package packet, View myView){
        ImageView img;
        TextView prezzo, ore;
        float prezzoF;
        int oreF;

        prezzo = myView.findViewById(R.id.prezzo);
        prezzoF = packet.getPrezzo();
        prezzo.setText(String.valueOf(prezzoF) + " €");

        ore = myView.findViewById(R.id.n_ore);
        oreF = packet.getnOre();
        ore.setText(String.valueOf(oreF) + " Ore di ripetizione");

        if(packet.getPrezzo()> 4.99f){
            Drawable myDrawable = packet.getImage();
            img = myView.findViewById(R.id.imgpack);
            img.setImageDrawable(myDrawable);
        }

        setOnClick(b, oreF);

    }

    private void setOnClick(final Button btn, final int ore){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView t;
                t = staticView.findViewById(R.id.n_ore_rimanenti);
                ore_rimaste = ore_rimaste + ore;
                t.setText(String.valueOf(ore_rimaste) + " Ore di ripetizione");
            }
        });
    }

}
