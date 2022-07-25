/**
 * Interface will allow a class to be displayed in the inventory
 */
public interface InventoryItem {
     /**
      * Method will be called when the item in the inventory is clicked
      * 
      */
     public void use(MainCharacter m);

     /**
      * Returns the name that will be displayed in the inventory above the item
      * 
      * @return name of the item
      */
     public String getName();

}
