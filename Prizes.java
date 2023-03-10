

public class Prizes extends Shop {
    
    private int index = 0;

    @Override
    public void add(Toy anToy) {
        Toy tempToy = anToy;
        tempToy.articul = index;
        this.toysInShop.add(anToy);
        index += 1;
    }
}
