package net.arkaine.pc.mapeditor;

/**
 * Created by olivier on 17/06/16.
 */
public class main {

    public static void main(String[] args){
        Editeur editeur = new Editeur();

    }


    /**
     * format fichier carte:
     * x.y.z;
     * 15_45_15_2_10_2_8|453_545_33_2_5_69_14|...|...;
     *
     * @param sauvegarde
     * @return
     */
//    public boolean load(String sauvegarde){
//        String data = Utils.openAssetTextFile(context, sauvegarde);
//        data = data.replaceAll("[\r\n ]+", "");
////        data = data.replace(" ","");
//        if(data == null || data == "")
//            return false;
//        String[] resultat = data.split(";");
//        if (resultat.length != 2) //deux secteurs dans le fichier : la taille des carte et leur données
//            return false;
//
//        String[] tailles = resultat[0].split("_"); //recuperation des dimensions
//        if (tailles.length != 3)
//            return false;
//        tailleX = Integer.valueOf(tailles[0]);
//        tailleY = Integer.valueOf(tailles[1]);
//        tailleZ = Integer.valueOf(tailles[2]);
//
//        carte = new Case[tailleX][tailleY][tailleZ];
//        //on boucle sur les trois axes
//        String[] carteBrut = resultat[1].split("\\|");
//        int index = 0;
//        for (int z=0;z<tailleZ;z++){
//            for (int y=0;y<tailleY;y++){
//                for (int x=0;x<tailleX;x++){
//                    try{
//                        carte[x][y][z] = new Case(carteBrut[index]);
//                    }catch (ArrayIndexOutOfBoundsException e){
//                        return false;
//                    }
//                    index++;
//                }
//            }
//        }
//        return true;
//    }
}
