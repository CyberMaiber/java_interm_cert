import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import java.nio.charset.StandardCharsets;

public class DataManager {
    public void loadFromFile(Shop shopList, String baseName) {
        String inpString;
        int ti1; //articul
        String ti2; //name
        Float ti3; //cost
        int ti4; //count
        int ti5; //weight
        String ti6; //description

        try {
            String[] tempStr;
            BufferedReader br = new BufferedReader(new FileReader(baseName+".txt",StandardCharsets.UTF_8));
            inpString = br.readLine();
            //System.out.println(inpString);
            while (inpString != null) {
                //System.out.println(inpString);
                tempStr = inpString.split("\t");
                //System.out.println(tempStr[0]);
                ti1 = Integer.parseInt(tempStr[0]);
                ti2 = tempStr[1];
                ti3 = Float.parseFloat(tempStr[2].replace(",", "."));
                ti4 = Integer.parseInt(tempStr[3]);
                ti5 = Integer.parseInt(tempStr[4]);
                ti6 = tempStr[5];
                shopList.add(new Toy(ti1, ti4, ti5, ti3, ti2, ti6));
                inpString = br.readLine();
            }
            br.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } 
        // return tree;
    }

    public void loadFromFile(Shop shopList) {
        this.loadFromFile(shopList, "shopFile");
    }

    public boolean saveToFile(ArrayList<Toy> toysInShop, String baseName) {
        //String outpt;
        try (FileWriter fw = new FileWriter(baseName+".txt",StandardCharsets.UTF_8,false)) {
            for (Toy member : toysInShop) {
                fw.write(member.toString()+"\n");
            }
            fw.flush();
            return true;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean saveToFile(ArrayList<Toy> toysInShop) {
        return this.saveToFile(toysInShop, "shopFile");
    }

}