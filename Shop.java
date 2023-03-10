import java.util.ArrayList;
import java.util.Random;

public class Shop {
    protected ArrayList<Toy> toysInShop = new ArrayList<>();
    private int totalPlayWeight = 0; //общий вес игрушек в магазине(для розыгрыша)

    public void add(Toy anToy) { //добавление игрушки в магазин 
        if (this.addToy(anToy)) {System.out.println(anToy.name + " - игрушка добавлен(а)");}
        else {System.out.println(anToy.name + " игрушка не добавлен(а)");}
    }

    private boolean addToy(Toy anToy) { //добавление игрушки в магазин с провереками
        int temp = this.haveID(anToy.articul);
        if (temp == -2){
            return false;
        } else if (temp == -1) {//если нет то добавить в базу новую игрушку
            this.toysInShop.add(anToy);
        } else {//если уже есть артикул, то прибавить количество
            Toy tempToy = this.toysInShop.get(temp);
            tempToy.count += anToy.count;
            this.toysInShop.set(temp, tempToy);
        }
        this.calcTotalPlayWeight(); //пересчитать общий вес игрушек в магазине(для розыгрыша)
        return true;
    }

    public void changeWeight (int toyArticul, int newWeight) { //изменение веса игрушки(для розыгрыша)
        int temp = this.haveID(toyArticul);
        if ((temp != -2) && (temp != -1)) {
            Toy tempToy = this.toysInShop.get(temp);
            tempToy.weight = newWeight;
            this.toysInShop.set(temp, tempToy);
        }
    }

    public void delToy (int toyArticul, int count){ //удаление игрушки из магазина по артикулу с количеством
        int temp = this.haveID(toyArticul);
        if ((temp != -2) && (temp != -1)) {
            Toy tempToy = this.toysInShop.get(temp);
            tempToy.count -= count;
            if (tempToy.count<=0) {
                this.toysInShop.remove(temp);
            } else {
                this.toysInShop.set(temp, tempToy);
            }
        }
    }

    private int haveID(int toyArticul) { //проверка есть ли уже игрушка с таким артикулом
        if (toyArticul == 0){return -2;} 
        int temp = 0;
        for (Toy anToy : this.toysInShop) {
            if (anToy.articul == toyArticul) {return temp;} //если есть, то возвращаем индекс в списке
            temp += 1;
        }
        return -1; //если нет, то -1
    }

    private void calcTotalPlayWeight() { //пересчет общего веса (для розыгрыша) игрушек в магазине
        this.totalPlayWeight = 0;
        for (Toy anToy : this.toysInShop) {
            this.totalPlayWeight += anToy.weight * anToy.count;
        }
    }

    public void printShop() {
        for (Toy anToy : this.toysInShop) {
            System.out.println(anToy.toString());
        }
    }

    public void saveToFile() {
        new DataManager().saveToFile(this.toysInShop);
    }

    public void loadFromFile() {
        new DataManager().loadFromFile(this);
    }

    public void saveToFile(String filename) {
        new DataManager().saveToFile(this.toysInShop, filename);
    }

    public void loadFromFile(String filename) {
        new DataManager().loadFromFile(this, filename);
    }

    public Toy letsPlay() {
        Toy tempToy = this.toysInShop.get(letsRandom());
        return tempToy;
    }

    private int letsRandom() { //метод выбора игрушки при розыгрыше с учетом веса. выдаёт номер в списке игрушек
        Random rand = new Random();
        int randomNum = rand.nextInt((this.totalPlayWeight - 1) + 1) + 1;
        int temp = 0;
        int i = 0;
        for (Toy anToy : this.toysInShop) {
            temp += anToy.weight * anToy.count;
            if (temp >= randomNum) {return i;}
            i += 1;
        }
        return -1;
    }

}
