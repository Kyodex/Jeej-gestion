package com.example.gestiondeportsfx.Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bateau {
    private int id;


    private Port portDepart;
    private Port portArrivee;
    private boolean enMer;
    private Image imageNavire;
    private ImageView imageNavireView;


    public ImageView getImageNavireView() {
        return imageNavireView;
    }

    public void setImageNavireView(ImageView imageNavireView) {
        this.imageNavireView = imageNavireView;
    }

    public Bateau(Port portArrivee) {
        if(portArrivee.getQuais().estPlein()){
            this.enMer=true;
        }else{
            this.portArrivee = portArrivee;
            this.portArrivee.ajouterBateau();
        };
    }

    public Bateau() {
        this.enMer=true;
    }

    public Image getImageNavire() {
        return imageNavire;
    }

    public void setImageNavire(Image imageNavire) {
        this.imageNavire = imageNavire;
    }

    public boolean isEnMer() {
        return enMer;
    }

    public void setEnMer(boolean enMer) {
        this.enMer = enMer;
    }

    public void accoster(Port a){
        if(a.getQuais().estPlein()){
            System.out.println("impossible d'accostercquai complet");
        }else{
            a.getQuais().ajouterBateau();
            this.portArrivee = a ;
            this.enMer = false;
        }
    }

    public void quitter(){
        if(this.portArrivee!=null || !this.enMer){
            this.portArrivee.retraitBateau();
            this.portDepart = this.portArrivee;
            this.portArrivee = null;
            this.enMer=true;
        }else{
            System.out.println("Le bateau ne se trouve pas sur un quai");
        }
    }

    public double distance() {
        if (this.portArrivee != null || this.portDepart != null) {
            double distanceAparc = this.portArrivee.getX() + this.portArrivee.getY() + this.portDepart.getX() + this.portDepart.getY();
            return (double) Math.sqrt(distanceAparc);
        }
        return -1;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
