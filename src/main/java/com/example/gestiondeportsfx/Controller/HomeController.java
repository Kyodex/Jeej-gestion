package com.example.gestiondeportsfx.Controller;

import com.example.gestiondeportsfx.Model.Bateau;
import com.example.gestiondeportsfx.Model.Port;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

import java.lang.reflect.Array;
import java.net.URL;
import java.nio.file.FileSystemException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class HomeController implements Initializable {
    @FXML
    public AnchorPane anchorPane;

    @FXML
    public HBox maison;

    @FXML
    public ImageView premierNavire;

    @FXML
    public ImageView ileMontagne;
    @FXML
    public ImageView ileDesert;
    @FXML
    public ImageView ileColline;

    @FXML
    public Button deplacerButton;

    public ArrayList<Bateau> list_bateau = new ArrayList<Bateau>();

    public ArrayList<Port> list_Port = new ArrayList<Port>();


    public HomeController() throws IOException{}


    @FXML
    public void afficheCharacteristique(MouseEvent mouseEvent){
        String selected_class = mouseEvent.getPickResult().getIntersectedNode().getAccessibleText();
        String selected_id=mouseEvent.getPickResult().getIntersectedNode().getId();
        System.out.println(selected_id +" "+ selected_class);
        if(!list_bateau.isEmpty() && selected_class.equals("bateau") ){
            Bateau bateauActuel = list_bateau.get(parseInt(selected_id)-1);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);  //crée une alert de confirmation
            alert.setContentText("Test "+selected_id+"\n"+bateauActuel.isEnMer());
            alert.show();
        }else if(selected_class.equals("port")){
            Port portActuel= list_Port.get(parseInt(selected_id));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("X = "+String.valueOf(portActuel.getX())+"Y = "+String.valueOf(portActuel.getY()));   //set le texte de l'alerte
            alert.show();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Image imgNavire = null;
        Bateau navire=new Bateau();
        Port maisonS = new Port(maison.getLayoutX(),maison.getLayoutY(),1);
        Port ile_montagne = new Port(ileMontagne.getParent().getLayoutX(),ileMontagne.getParent().getLayoutY());
        Port ile_desert = new Port(ileDesert.getParent().getLayoutX(),ileDesert.getParent().getLayoutY());
        Port ile_colline = new Port(ileColline.getParent().getLayoutX(),ileColline.getParent().getLayoutY());

        System.out.println(ileMontagne.getParent().getLayoutX()+" "+ile_montagne.getY());

        list_Port.add(maisonS);
        list_Port.add(ile_montagne);
        list_Port.add(ile_desert);
        list_Port.add(ile_colline);

        list_bateau.add(navire);

        int intRandom = (int) (Math.random()*10);
        Integer random = intRandom;
        imgNavire = new Image(getClass().getResourceAsStream("/images/vaisseau1.png"));
        ImageView imgViewNavire = new ImageView(imgNavire);

       /* try{
            Class<?> clazz = HomeController.class;

            InputStream input = clazz.getResourceAsStream("/images/vaisseau1.png");

            imgNavire = new Image(input,100,100,false,true);
            navire.setImageNavire(imgNavire);
            ImageView imgViewNavire = new ImageView(imgNavire);
            FlowPane root = new FlowPane();
            root.setPadding(new Insets(20));

            root.getChildren().addAll(imgViewNavire);

            Scene scene = new Scene(root, 400, 200);



        } catch (Exception e) {
            e.printStackTrace();
        }
*/
    }

    public void deplacement() throws InterruptedException {
        double distanceXmaison = list_Port.get(0).getX();
        double distanceYmaison = list_Port.get(0).getY();
        double posBateauX = premierNavire.getParent().getLayoutX();
        double posBateauY = premierNavire.getParent().getLayoutY();
        if(distanceXmaison>posBateauX){
            while(distanceXmaison!=posBateauX){

                premierNavire.setLayoutX(posBateauX++);
                premierNavire.getParent().setLayoutX(posBateauX+=0.5);
                posBateauX = premierNavire.getParent().getLayoutX();
                System.out.println(posBateauX + "  "+distanceXmaison);

            }
        }else{
            while(distanceXmaison!=posBateauX){
                premierNavire.setLayoutX(posBateauX--);
                premierNavire.getParent().setLayoutX(posBateauX-=0.5);
                posBateauX = premierNavire.getParent().getLayoutX();
                System.out.println(posBateauX + "  "+distanceXmaison);


            }
        }


    }
}
