package tpdssdl;

import tpdssln.ssreparacoes.Registo;

import java.io.*;
import java.util.Set;

public class PedidosOrcamento {
    public static void escreveFile(Set<Registo> po) {
        try {
            File fileOne = new File("db/pedidosOrcamento");
            FileOutputStream fos = new FileOutputStream(fileOne);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(po);
            oos.flush();
            oos.close();
            fos.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Set<Registo> leFile(){
        try {
            File toRead = new File("db/pedidosOrcamento");
            FileInputStream fis = new FileInputStream(toRead);
            ObjectInputStream ois = new ObjectInputStream(fis);

            Set<Registo> po = (Set<Registo>) ois.readObject();

            ois.close();
            fis.close();
            return po;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
