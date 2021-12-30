package tpdssdl;

import tpdssln.ssreparacoes.Registo;

import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class PedidosOrcamento {
    public static void escreveFile(Map<String, Registo> po) {
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

    public static Map<String, Registo> leFile() {
        File toRead = new File("db/pedidosOrcamento");
        try {
            FileInputStream fis = new FileInputStream(toRead);
            ObjectInputStream ois = new ObjectInputStream(fis);

            Map<String, Registo> po = (Map<String, Registo>) ois.readObject();

            ois.close();
            fis.close();
            return po;
        } catch (Exception e){
            try {
                Files.createDirectories(toRead.getParentFile().toPath());
                toRead.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return new HashMap<>();
    }
}
