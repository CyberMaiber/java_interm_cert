/**
 * index
 */
public class index {
    public static void main(String[] args) {
        
        Shop shop = new Shop(); //наш склад магазина
        Shop prizes = new Prizes(); //призы на выдачу
        shop.loadFromFile(); //загружаем перечень игрушек в магазине из файла
        prizes.loadFromFile("prizes"); //загружаем перечень игрушек из файла на выдачу

        System.out.println("----Магазин игрушек-----");
        shop.printShop();
        
        Toy tempToy = shop.letsPlay(); //Разыгрываем игрушку для Покупателя 1
        //принцип работы розыгрыша - у каждой игрушки есть вес (weight).
        //есть параметр который содержит суммарный вес всех игрушек в магазине.
        //выбирается случайное число от 1 до суммарного веса, и выбранной игрушкой считается та, чей вес,
        // с учетом суммы весов предыдущих по списку игрушек, будет больше выбранного случайного числа.


        //shop.changeWeight(tempToy.articul, 25); //если нужно поменять вес игрушки - делаем это так

        shop.delToy(tempToy.articul, 1); //Изымаем игрушку из магазина
        tempToy.count = 1;
        tempToy.description = "Выдать Покупателю 1";

        prizes.add(tempToy); //Кладём изъятую игрушку в список призов
        prizes.saveToFile("prizes"); //сохраняем файл со списком призов
        //shop.saveToFile(); //сохраняем файл магазина

    }
    
}
