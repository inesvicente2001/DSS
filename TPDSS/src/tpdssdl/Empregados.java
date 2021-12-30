package tpdssdl;

import tpdssln.ssempregados.Empregado;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Empregados {

    public static void escreveFile(Map<String, Empregado> empregados){

        try {
            File fileOne = new File("db/empregados");
            FileOutputStream fos = new FileOutputStream(fileOne);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(empregados);
            oos.flush();
            oos.close();
            fos.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static HashMap<String, Empregado> leFile() {
        try {
            File toRead = new File("db/empregados");
            FileInputStream fis = new FileInputStream(toRead);
            ObjectInputStream ois = new ObjectInputStream(fis);

            HashMap<String, Empregado> empregados = (HashMap<String, Empregado>) ois.readObject();

            ois.close();
            fis.close();
            return empregados;
        } catch (Exception e){
            e.printStackTrace();
        }
        return new HashMap<>();
    }
}
