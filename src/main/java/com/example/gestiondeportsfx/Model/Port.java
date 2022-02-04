package com.example.gestiondeportsfx.Model;

public class Port {
    private  double x;
    private double y;
    private Quais quais;


    public Port(){}

    public Port(double x,double y){
        this.x=x;
        this.y=y;
        this.quais = new Quais();
    }

    public Port(double x,double y , int nbQuai){
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


    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Quais getQuais() {
        return quais;
    }

    public void setQuais(Quais quais) {
        this.quais = quais;
    }
}