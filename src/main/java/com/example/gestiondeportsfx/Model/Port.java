package com.example.gestiondeportsfx.Model;

public class Port {
    private  float x;
    private float y;
    private Quais quais;

    public Port(){}

    public Port(float x,float y){
        this.x=x;
        this.y=y;
        this.quais = new Quais();
    }

    public Port(float x,float y , int nbQuai){
        this.x=x;
        this.y=y;
        this.quais = new Quais(nbQuai);
    }

    public boolean ajouterBateau(){
        if(this.quais.getQuaisOcc()>=this.quais.getNbQuais()){
            return false;
        }else{
            this.quais.setNbQuais(this.quais.getQuaisOcc()+1);
            return true;
        }
    }

    public void retraitBateau(){
        if(this.quais.getQuaisOcc()>0){
            this.quais.retraitBateau();
        }else{
            System.out.println("Echec pas de bateau sur le quai");
        }
    }


    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Quais getQuais() {
        return quais;
    }

    public void setQuais(Quais quais) {
        this.quais = quais;
    }
}