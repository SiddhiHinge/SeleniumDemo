package everShop;

public class Product {
    String name;
    String colour;
    String size;
    String price;
    int qty;
    String value;
    public Product(String name1,String colour1, String size1, String price1, int qty1, String value1){
        name = name1;
        colour = colour1;
        size = size1;
        price = price1;
        qty = qty1;
        value = value1;
    }

    public void printProduct(){
        System.out.println(name+" "+"["+colour+","+size+"] "+"Price:"+price+", Qty:"+qty+" Value:"+value);
    }

}
