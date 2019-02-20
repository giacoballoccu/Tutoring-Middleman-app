package com.example.giaco.gerproject.Classes;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;

import com.example.giaco.gerproject.ApplicationContextProvider;
import com.example.giaco.gerproject.R;

import java.util.ArrayList;

public class PackageFactory {
    private static PackageFactory singleton;

    private ArrayList<Package> packages = new ArrayList<>();

    public static PackageFactory getInstance() {
        if (singleton == null) {
            singleton = new PackageFactory();
        }
        return singleton;
    }

    public PackageFactory(){
        Context context = ApplicationContextProvider.getContext();

        Package pack1 = new Package();
        Drawable img1 = ResourcesCompat.getDrawable(context.getResources(), R.drawable.unlibro , null);
        pack1.setImage(img1);

        getPackages().add(pack1);

        Package pack2 = new Package();

        Drawable img2 = ResourcesCompat.getDrawable(context.getResources(), R.drawable.trelibri, null);
        pack1.setImage(img2);

        getPackages().add(pack2);
    }

    public ArrayList<Package> getPackages() {
        return packages;
    }

    public void setPackages(ArrayList<Package> packages) {
        this.packages = packages;
    }
}
