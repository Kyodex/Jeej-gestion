package com.example.gestiondeportsfx.Model;

public class Quais {
    private int nbQuais;
    private int quaisOcc;
    //constructeur vide
    public Quais(){
        this.nbQuais = 3;
    }

    public Quais(int nbQuai){
        if(nbQuai>0){
            this.nbQuais = nbQuai;
        }else{
            this.nbQuais = 3;
        }
    }

    public boolean ajouterBateau(){
        if (this.quaisOcc>= this.nbQuais){
            return false;
        }else {
            this.quaisOcc++;
            return true;
        }
    }

    public boolean estPlein(){
        if(this.quaisOcc>=nbQuais){
            return true;
        }else{
            System.out.println("place libre");
            return false;
        }
    }

    public void retraitBateau(){
        if(this.quaisOcc>0){
            this.quaisOcc--;
        }else{
            System.out.println("Pas de bateau à quai");
        }
    }

    public int getNbQuais() {
        return nbQuais;
    }

    public void setNbQuais(int nbQuais) {
        this.nbQuais = nbQuais;
    }

    public int getQuaisOcc() {
        return quaisOcc;
    }

    public void setQuaisOcc(int quaisOcc) {
        this.quaisOcc = quaisOcc;
    }
}