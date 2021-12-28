package tpdssdl;

import tpdssln.ssreparacoes.Registo;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class RegistosConcluidos {
    public static void escreveFile(Map<String, Registo> map){

        try {
            File fileOne = new File("db/RegistosConcluidos");
            FileOutputStream fos = new FileOutputStream(fileOne);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(map);
            oos.flush();
            oos.close();
            fos.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static HashMap<String, Registo> leFile(){
        try {
            File toRead = new File("db/RegistosConcluidos");
            FileInputStream fis = new FileInputStream(toRead);
            ObjectInputStream ois = new ObjectInputStream(fis);

            HashMap<String, Registo> map = (HashMap<String, Registo>) ois.readObject();

            ois.close();
            fis.close();
            return map;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
