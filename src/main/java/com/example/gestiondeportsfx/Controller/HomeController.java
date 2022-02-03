package com.example.gestiondeportsfx.Controller;

import com.example.gestiondeportsfx.Model.Bateau;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystemException;

public class HomeController {
    @FXML
    public AnchorPane anchorPane;

    @FXML
    public ImageView navire;

    @FXML
    public ImageView ileMontagne;
    @FXML
    public ImageView ileDesert;
    @FXML
    public ImageView ileColline;

    public HomeController() throws IOException{}

    public void afficheCharacteristique(){



    }

    public void initialize(){
        Image imgNavire = null;
        Bateau navire=new Bateau();
        int intRandom = (int) (Math.random()*10);
        Integer random = intRandom;
        try{
            imgNavire = new Image(new FileInputStream("../resources/images/vaisseau"+random.toString()+".png"));
            navire.setImageNavire(imgNavire);
            ImageView imgViewNavire = new ImageView(navire.getImageNavire());
            imgViewNavire.setX(0);
            imgViewNavire.setY(0);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
