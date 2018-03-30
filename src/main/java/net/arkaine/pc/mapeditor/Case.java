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
    int lumiere;//-255 255
    int traversable;//255 = non, <255 oui mais freinne

    float transparence;//0-1
    String couleurAtmos;//r.g.b

    int indexAnim;//0-255
    List<Image> animation;

    public Case(String values){
        String[] valeurs = values.split("_");
        if(valeurs[0] != null){this.type = Integer.parseInt(valeurs[0]);}else{this.type =0;}
        if(valeurs[1] != null){this.vie = Integer.parseInt(valeurs[1]);}else{this.vie =0;}
        if(valeurs[2] != null){this.indexAnim = Integer.parseInt(valeurs[2]);}else{this.indexAnim =0;}
        if(valeurs[3] != null){this.atmosphere = Integer.parseInt(valeurs[3]);}else{this.atmosphere =0;}
        if(valeurs[4] != null){this.lumiere = Integer.parseInt(valeurs[4]);}else{this.lumiere =255;}
        if(valeurs[5] != null){this.traversable = Integer.parseInt(valeurs[5]);}else{this.traversable =0;}
        if(valeurs[6] != null){this.transparence = Float.parseFloat(valeurs[6]);}else{this.transparence =1;}
        this.couleurAtmos = valeurs[7];
    }

    public void loadPictures(){

    }

    @Override
    public String toString(){
        String caseString="|";
        try {
            caseString = type + "_" + vie + "_" + indexAnim + "_" + atmosphere + "_" + lumiere + "_" + traversable + "_" + transparence + "_" + couleurAtmos + "|";
        }catch (NullPointerException e){
            return caseString;
        }
        return caseString;
    }
}
