public class Toy {
    int articul; //типа ID
    int count; //Количество на складе
    int weight; //вес при розыгрышах, чем больше, тем чаще будет попадаться
    Float cost; // стоимость
    String name; //Название
    String description; //Описание

    public Toy (int articul, int count, int weight, Float cost, String name, String description){
        this.articul = articul;
        this.count = count;
        this.weight = weight;
        this.cost = cost;
        this.name = name;
        this.description = description;
    }
    
    @Override
    public String toString() {
        String temp;
        temp = String.format("%d\t%s\t%f\t%d\t%d\t%s",
        this.articul,
        this.name,
        this.cost,
        this.count,
        this.weight,
        this.description);
        //System.out.println(temp);
        return temp;
    }
}
