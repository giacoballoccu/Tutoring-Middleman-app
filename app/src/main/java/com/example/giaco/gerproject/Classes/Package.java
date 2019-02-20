package com.example.giaco.gerproject.Classes;

import android.graphics.drawable.Drawable;

public class Package {
    private Drawable image;
    private int nOre;
    private float prezzo;

    public Package(){

    }

    public Package(Drawable image, int nOre, float prezzo){
        this.setImage(image);
        this.setnOre(nOre);
        this.setPrezzo(prezzo);
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public int getnOre() {
        return nOre;
    }

    public void setnOre(int nOre) {
        this.nOre = nOre;
    }
}
