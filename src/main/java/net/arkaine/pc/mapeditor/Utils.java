package net.arkaine.pc.mapeditor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.CharBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.nio.file.StandardOpenOption.READ;

/**
 * Created by olivier on 17/06/16.
 */
public class Utils {

    /**
     *
     * @param objet
     * @param destinationFile
     */
    public static void serialiser(Object objet, File destinationFile){
        try {
            FileOutputStream fichier = new FileOutputStream(destinationFile);

            try {
                ObjectOutputStream oos = new ObjectOutputStream(fichier);

                try {
                    oos.writeObject(objet);
                    oos.flush();
                }
                finally {
                    oos.close();
                }
            }
            finally {
                fichier.close();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @param sourceFile
     * @return
     */
    public static Object deserialiser(File sourceFile){
        Object objet = null;
        try {
            FileInputStream fichier = new FileInputStream(sourceFile);

            try {
                ObjectInputStream ois = new ObjectInputStream(fichier);

                try	{
                    objet = ois.readObject();
                }
                finally {
                    ois.close();
                }
            }
            finally {
                fichier.close();
            }
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }
        catch (ClassNotFoundException cnfe){
            cnfe.printStackTrace();
        }
        return objet;
    }

    public static String openFile(Path path){
        // Convert the string to a
        // byte array.
        String contenu = "";
        try (InputStreamReader in = new InputStreamReader(
                Files.newInputStream(path, READ))) {
            in.read(CharBuffer.wrap(contenu));
        } catch (IOException x) {
            System.err.println(x);
        }
        return contenu;
    }
    /**
     *
     * @param contenu
     * @param destination
     * @param isRewrite
     */
    public static void openIsoFileandWrite(String contenu, String destination,boolean isRewrite){
        File deleteFile = new File(destination);
        if (isRewrite)
            deleteFile.delete();
        OutputStreamWriter ost=null;
        try {
            ost = new OutputStreamWriter(new FileOutputStream(destination,!isRewrite) , "ISO-8859-1");
            ost.write(contenu);
            ost.flush();
            ost.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                ost.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
public static String openFile(String source) {
        StringBuffer contenu = new StringBuffer();
		BufferedReader br = null;

		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader(source));

			while ((sCurrentLine = br.readLine()) != null) {
				contenu.append(sCurrentLine);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
        return contenu.toString();
	}

    static public Image loadImage(String absolutPath){
        File imgFile = new  File(absolutPath);
        Image image = null;
        if(imgFile.exists()){
            try {
                BufferedImage myPicture = ImageIO.read(new File(absolutPath));
                image = myPicture.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return image;
    }

    static public Map<Integer, Image> loadImages(String folder){
        Map<Integer, Image> bitmaps = new HashMap<>();
        File dossier = new File( folder);
        int i = 0;
        File[] files = dossier.listFiles();
        Arrays.sort(files);
        for(File file: files)
        {
            try {
                String[] indexes = file.getName().split("_");
                bitmaps.put(Integer.valueOf(indexes[0]), ImageIO.read(file));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bitmaps;
    }
//
//    static public Map<Integer, Image> loadImages(String path){
//        File directory = new File(path);
//        Map<Integer, Image> bitmaps = new HashMap<Integer, Image>();
//
//        for(int i= 1;i<=directory.list().length; i++)
//            {
//                try
//                {
//                    bitmaps.put(i, loadImage(path + File.separatorChar + directory.list()[i-1]));
//                }
//                catch (Exception e)
//                {
//                    e.printStackTrace();
//                }
//              //  }
//            }
//        return bitmaps;
//    }

    static String imageArbo(String arbo, File currentFolder, String filterFile, String filterFolder){
        if(currentFolder.exists() && currentFolder.listFiles() != null ) {
            for(File file:currentFolder.listFiles()){
                if(file.isDirectory()){
                    if(filterFolder.equals("") || !file.getName().contains(filterFolder)){
                        arbo +="\n"+file.getAbsoluteFile();
                        arbo = imageArbo(arbo, file, filterFile, filterFolder);
                    }
                }else{
                    if (filterFile.equals("") || file.getName().contains(filterFile))
                        arbo +="\n"+file.getAbsoluteFile() + file.length();
                }
            }
        }
        return arbo;
    }

    static private FileFilter customFilter(final String namePart, final String extension){
        return new FileFilter() {
            @Override
            public boolean accept(File file) {
                if(file.getName().contains(namePart) && file.getName().contains(extension))
                    return true;
                return false;
            }
        };

    }
}
