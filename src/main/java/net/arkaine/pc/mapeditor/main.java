package net.arkaine.pc.mapeditor;

/**
 * Created by olivier on 17/06/16.
 */
public class main {

    public static void main(String[] args){
        Editeur editeur;
        if(args.length>2 && Integer.parseInt(args[0]) > 0 && Integer.parseInt(args[1]) > 0 && Integer.parseInt(args[2]) > 0) {
            editeur = new Editeur(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));
        }else{
            editeur = new Editeur(100, 100, 10);
        }
        Editeur.showOnScreen(0,editeur);


    }

}
