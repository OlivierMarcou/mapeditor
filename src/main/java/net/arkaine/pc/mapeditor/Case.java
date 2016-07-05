package net.arkaine.pc.mapeditor;
import java.awt.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: omarcou
 * Date: 22/10/13
 * Time: 14:37
 * To change this template use File | Settings | File Templates.
 */
public class Case implements Serializable {

    int type;//0-1024
    int vie;//0-1024  max = indestructible

    int atmosphere;//0-255
    float lumiere;//0-1
    int traversable;//255 = non, <255 oui mais freinne

    float transparence;//0-1
    int couleurAtmos;//0-255

    int indexAnim;//0-255
    List<Image> animation;

    public Case(String values){
        String[] valeurs = values.split("_");
        this.type = Integer.parseInt(valeurs[0]);
        this.vie = Integer.parseInt(valeurs[1]);
        this.indexAnim = Integer.parseInt(valeurs[2]);
        this.atmosphere = Integer.parseInt(valeurs[3]);
        this.lumiere = Float.parseFloat(valeurs[4]);
        this.traversable = Integer.parseInt(valeurs[5]);
        this.transparence = Float.parseFloat(valeurs[6]);
        this.couleurAtmos = Integer.parseInt(valeurs[7]);
    }

    public void loadPictures(){

    }

    @Override
    public String toString(){
        String caseString="0_0_0_0_0_0_0_0|";
        try {
            caseString = type + "_" + vie + "_" + indexAnim + "_" + atmosphere + "_" + lumiere + "_" + traversable + "_" + transparence + "_" + couleurAtmos + "|";
        }catch (NullPointerException e){
            return caseString;
        }
        return caseString;
    }
}
