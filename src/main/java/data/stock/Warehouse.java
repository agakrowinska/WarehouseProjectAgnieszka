package data.stock;

import java.util.List;

public class Warehouse {

    private int id;

    //should it be Integer?
    private List<Item> stock;

    public int getId() {
        id = 1;
        return id;
   }

   // public List<Item> getStock() {
        //return stock;
    //}

    public Warehouse(int id) {
        this.id = id;

    }

    //public Warehouse (int i_warehouseId){

    //}
    public int occupancy(){

        return 0;
        //here has to be something else
    }
    public void addItem (Item i_item){

    }
    public List<Item> search (String i_searchTerm){

        return null;
    }

}
