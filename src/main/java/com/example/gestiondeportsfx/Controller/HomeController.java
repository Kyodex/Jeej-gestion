package com.example.gestiondeportsfx.Controller;

import com.example.gestiondeportsfx.Model.Bateau;
import com.example.gestiondeportsfx.Model.Port;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

import java.lang.reflect.Array;
import java.net.URL;
import java.nio.file.FileSystemException;
import java.util.ArrayList;
import java.util.Optional;
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
    public ImageView imgViewNavire;
    @FXML
    public ImageView imgViewNavireA;
    @FXML
    public ImageView imgViewNavireB;

    @FXML
    public Button deplacerButton;
    @FXML
    public TextArea dernierSelectionerTextArea;
    @FXML
    public TextFlow testAreaDernier;

    public int bateauId;

    String path = "C:\\Users\\dineh\\Documents\\Cours\\GestionDePortFx-main\\Jeej-gestion\\src\\main\\resources\\images";

    public ArrayList<Bateau> list_bateau = new ArrayList<Bateau>();

    public ArrayList<Port> list_Port = new ArrayList<Port>();

    @FXML
    public Object lastSelectedBateauImg;

    public Bateau lastSelectedBateau;
    public Port lastSelectedPort;

    public HomeController() throws IOException{}


    @FXML
    public void afficheCharacteristique(MouseEvent mouseEvent){
        String selected_class = mouseEvent.getPickResult().getIntersectedNode().getAccessibleText();
        String selected_id=mouseEvent.getPickResult().getIntersectedNode().getId();
        System.out.println(selected_id +" "+ selected_class);
        if(!list_bateau.isEmpty() && selected_class.equals("bateau") ){
            Bateau bateauActuel = list_bateau.get(parseInt(selected_id));
            //Alert alert = new Alert(Alert.AlertType.INFORMATION);  //crée une alert de confirmation
            //alert.setContentText("Test "+selected_id+"\n"+bateauActuel.isEnMer());
            //alert.show();
            lastSelectedBateau = bateauActuel ;
           // lastSelectedBateauImg=mouseEvent.getSource();
        }else if(!list_Port.isEmpty() && selected_class.equals("port")){
            Port portActuel= list_Port.get(parseInt(selected_id));
            //Alert alert = new Alert(Alert.AlertType.INFORMATION);
            //alert.setContentText("X = "+String.valueOf(portActuel.getX())+"Y = "+String.valueOf(portActuel.getY())+"\n quai"+portActuel.getQuais().toString());   //set le texte de l'alerte
            //alert.show();
            lastSelectedPort = portActuel;
        }
        afficherDernierSelected();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Image imgNavire = null;
        Bateau navire=new Bateau();
        bateauId = 0;
        navire.setId(bateauId);
        navire.setImageNavireView(premierNavire);
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



    }

    public void deplacement() throws InterruptedException {
        double distanceXmaison = list_Port.get(0).getX();
        double distanceYmaison = list_Port.get(0).getY();
        double posBateauX = premierNavire.getParent().getLayoutX();
        double posBateauY = premierNavire.getParent().getLayoutY();
        if(distanceXmaison>posBateauX){
            while(distanceXmaison!=posBateauX){
                premierNavire.setLayoutX(posBateauX++);
                premierNavire.getParent().setLayoutX(posBateauX++);
                posBateauX = premierNavire.getParent().getLayoutX();
                System.out.println(posBateauX + "  "+distanceXmaison);
            }
        }else{
            while(distanceXmaison!=posBateauX){
                premierNavire.setLayoutX(posBateauX--);
                premierNavire.getParent().setLayoutX(posBateauX--);
                posBateauX = premierNavire.getParent().getLayoutX();
                System.out.println(posBateauX + "  "+distanceXmaison);
            }
        }
        if(distanceYmaison>posBateauY){
            while(distanceYmaison!=posBateauY){
                premierNavire.setLayoutY(posBateauY++);
                premierNavire.getParent().setLayoutY(posBateauY++);
                posBateauY = premierNavire.getParent().getLayoutY();
                System.out.println(posBateauY + " Y "+distanceYmaison);
            }
        }else{
            while(distanceYmaison!=posBateauY){
                premierNavire.setLayoutY(posBateauY--);
                premierNavire.getParent().setLayoutY(posBateauY--);
                posBateauY = premierNavire.getParent().getLayoutY();
                System.out.println(posBateauY + " Y "+distanceYmaison);
            }
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Bien arrivé à la maison");   //set le texte de l'alerte
        alert.show();
    }


    public void afficherDernierSelected(){
        String reponse;
        if(lastSelectedPort==null){
            dernierSelectionerTextArea.clear();
            reponse = "Vous avez pas selectionné de port encore";
            System.out.println("Vous avez pas selectionné de port encore");
        }else{
            dernierSelectionerTextArea.clear();
            reponse = "Dernier port selectionné"+lastSelectedPort.getX()+"  Y = "+lastSelectedPort.getY()+"\n Nombre de quai occupé :"+lastSelectedPort.getQuais().getQuaisOcc()+"/"+lastSelectedPort.getQuais().getNbQuais();
            System.out.println(lastSelectedPort.getX()+"  Y = "+lastSelectedPort.getY()+"\n Nombre de quai occupé :"+lastSelectedPort.getQuais().getQuaisOcc()+"/"+lastSelectedPort.getQuais().getNbQuais());
        }
        if(lastSelectedBateau==null){
            dernierSelectionerTextArea.clear();
            reponse += "\nVous avez pas selectionné de Bateau encore";
            System.out.println("Vous avez pas selectionné de Bateau encore");
        }else{
            dernierSelectionerTextArea.clear();
            reponse += "\n\n Dernier bateau selectionner : "+lastSelectedBateau.getId()+"\nEn mer = "+lastSelectedBateau.isEnMer();
            System.out.println(lastSelectedBateau.getId());
        }
        if(isDernierSelected()){
            dernierSelectionerTextArea.clear();
            double distanceAparc = lastSelectedPort.getX() + lastSelectedPort.getY() + lastSelectedBateau.getImageNavireView().getLayoutX() + lastSelectedBateau.getImageNavireView().getLayoutX();
            distanceAparc = Math.sqrt(distanceAparc);
            reponse += "\n\n Distance Port et Bateau : "+distanceAparc;
        }
        dernierSelectionerTextArea.setText(reponse);
    }

    public boolean isDernierSelected(){
        if(lastSelectedPort==null || lastSelectedBateau==null){
            return false;
        }
        return true;
    }


    @FXML
    public void voyager(){
        if(isDernierSelected()){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Voulez vous voyager au dernier port choisi ?");
                Optional<ButtonType> result = alert.showAndWait();    //affiche l'alert et récupère la réponse
                if (result.get() == ButtonType.OK){ //si l'utilisateur valide l'alerte
                    lastSelectedBateau.accoster(lastSelectedPort);
                    deplacementPort(lastSelectedPort,lastSelectedBateau);
                }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Pas possible verifier vous selection");
        }
        afficherDernierSelected();
    }

    public void deplacementPort(Port p,Bateau b)  {
        double distanceXport = p.getX();
        double distanceYport = p.getY();
        double posBateauX = b.getImageNavireView().getLayoutX();
        double posBateauY = b.getImageNavireView().getLayoutY();
        if(distanceXport>posBateauX){
            while(distanceXport!=posBateauX){
                b.getImageNavireView().setLayoutX(posBateauX++);
                b.getImageNavireView().getParent().setLayoutX(posBateauX++);
                posBateauX = b.getImageNavireView().getParent().getLayoutX();
                System.out.println(posBateauX + "  "+distanceXport);
            }
        }else{
            while(distanceXport!=posBateauX){
                b.getImageNavireView().setLayoutX(posBateauX--);
                b.getImageNavireView().getParent().setLayoutX(posBateauX--);
                posBateauX = b.getImageNavireView().getParent().getLayoutX();
                System.out.println(posBateauX + "  "+distanceXport);
            }
        }
        if(distanceYport>posBateauY){
            while(distanceYport!=posBateauY){
                b.getImageNavireView().setLayoutY(posBateauY++);
                b.getImageNavireView().getParent().setLayoutY(posBateauY++);
                posBateauY = b.getImageNavireView().getParent().getLayoutY();
                System.out.println(posBateauY + " Y "+distanceYport);
            }
        }else{
            while(distanceYport!=posBateauY){
                b.getImageNavireView().setLayoutY(posBateauY--);
                b.getImageNavireView().getParent().setLayoutY(posBateauY--);
                posBateauY = b.getImageNavireView().getParent().getLayoutY();
                System.out.println(posBateauY + " Y "+distanceYport);
            }
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Bien arrivé au port");   //set le texte de l'alerte
        alert.show();
        afficherDernierSelected();
    }

    public void creerBateau(){
        Bateau newBateau = new Bateau();
        int random = (int) (Math.random()*10);

        //lastSelectedPort.ajouterBateau();
        if(imgViewNavire.getImage()==null) {
            try {
                FileInputStream input = new FileInputStream(path + "\\vaisseau" + random + ".png");
                Image imgNavire = new Image(input, 200, 500, false, true);
                imgViewNavire.setImage(imgNavire);
                newBateau.setImageNavireView(imgViewNavire);
                bateauId++;
                newBateau.setId(bateauId);
                imgViewNavire.setId(bateauId + "");
                imgViewNavire.setAccessibleText("bateau");
                imgViewNavire.setOnMouseClicked(event -> afficheCharacteristique(event));
                list_bateau.add(newBateau);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Bateau creer");   //set le texte de l'alerte
                alert.show();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }else if(imgViewNavireA.getImage()==null) {
            try {
                FileInputStream input = new FileInputStream(path + "\\vaisseau" + random + ".png");
                Image imgNavire = new Image(input, 200, 500, false, true);
                imgViewNavireA.setImage(imgNavire);
                newBateau.setImageNavireView(imgViewNavireA);
                bateauId++;
                newBateau.setId(bateauId);
                imgViewNavireA.setId(bateauId + "");
                imgViewNavireA.setAccessibleText("bateau");
                imgViewNavireA.setOnMouseClicked(event -> afficheCharacteristique(event));
                list_bateau.add(newBateau);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Bateau creer");   //set le texte de l'alerte
                alert.show();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        } else if(imgViewNavireB.getImage()==null){
            try {
                FileInputStream input = new FileInputStream(path + "\\vaisseau" + random + ".png");
                Image imgNavire = new Image(input, 200, 500, false, true);
                imgViewNavireB.setImage(imgNavire);
                newBateau.setImageNavireView(imgViewNavireB);
                bateauId++;
                newBateau.setId(bateauId);
                imgViewNavireB.setId(bateauId + "");
                imgViewNavireB.setAccessibleText("bateau");
                imgViewNavireB.setOnMouseClicked(event -> afficheCharacteristique(event));
                list_bateau.add(newBateau);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Bateau creer");   //set le texte de l'alerte
                alert.show();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    public void quitterPort(Bateau b){
        int randomX = (int) (Math.random()*739);
        int randomY = (int)(Math.random()*745);
        if(isDernierSelected()){
            double posBateauX = b.getImageNavireView().getLayoutX();
            double posBateauY = b.getImageNavireView().getLayoutY();
            if(randomX>posBateauX){
                while(randomX!=posBateauX){
                    b.getImageNavireView().setLayoutX(posBateauX++);
                    b.getImageNavireView().getParent().setLayoutX(posBateauX++);
                    posBateauX = b.getImageNavireView().getParent().getLayoutX();
                    System.out.println(posBateauX + "  "+randomX);
                }
            }else{
                while(randomX!=posBateauX){
                    b.getImageNavireView().setLayoutX(posBateauX--);
                    b.getImageNavireView().getParent().setLayoutX(posBateauX--);
                    posBateauX = b.getImageNavireView().getParent().getLayoutX();
                    System.out.println(posBateauX + "  "+randomX);
                }
            }
            if(randomY>posBateauY){
                while(randomY!=posBateauY){
                    b.getImageNavireView().setLayoutY(posBateauY++);
                    b.getImageNavireView().getParent().setLayoutY(posBateauY++);
                    posBateauY = b.getImageNavireView().getParent().getLayoutY();
                    System.out.println(posBateauY + " Y "+randomY);
                }
            }else{
                while(randomY!=posBateauY){
                    b.getImageNavireView().setLayoutY(posBateauY--);
                    b.getImageNavireView().getParent().setLayoutY(posBateauY--);
                    posBateauY = b.getImageNavireView().getParent().getLayoutY();
                    System.out.println(posBateauY + " Y "+randomY);
                }
            }
            b.quitter();
            afficherDernierSelected();
        }

    }

    public void quitter(){
        if(isDernierSelected()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Voulez vous repartir en mer ?");
            Optional<ButtonType> result = alert.showAndWait();    //affiche l'alert et récupère la réponse
            if (result.get() == ButtonType.OK){ //si l'utilisateur valide l'alerte
                quitterPort(lastSelectedBateau);
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Pas possible verifier vous selection");
        }
    }

    public void createPort(){
        Port newPort = new Port();

    }




}












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

        list_Port.get(1).getQuais().getBateauAQuai().add(navire);
        navire.accoster(list_Port.get(1));
*/