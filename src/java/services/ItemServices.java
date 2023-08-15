/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.CategoryDB;
import dataaccess.ItemDB;
import dataaccess.UserDB;
import java.util.List;
import models.Category;
import models.Item;
import models.User;

/**
 *
 * @author gurwi
 */
public class ItemServices {
    
      public List<Item> getAll(String email) throws Exception {
        ItemDB itemdb = new ItemDB();
        List<Item> items = itemdb.getAll(email);
        return items;
    }
    
       public void delete(int ItemId) throws Exception {
           ItemDB itemdb = new ItemDB();
           Item item = itemdb.get(ItemId);
           itemdb.delete(item);
    }
       
        public void update(int itemId, String name, double price) throws Exception{
//        Item item = new Item(6, name, price);
      ItemDB itemdb = new ItemDB();
      Item item = itemdb.get(itemId);
       item.setItemName(name);
       item.setPrice(price);
      
        itemdb.update(item);
    }
        
          
    public void insert(String cat, String name, double price, String email) throws Exception{
        Item item = new Item(6, name, price);
        UserDB userdb = new UserDB();
        User user = userdb.get(email);
        item.setOwner(user);
        CategoryDB categorydb = new CategoryDB();
        Category category = categorydb.get(cat);
        item.setCategory(category);
        
        ItemDB itemdb = new ItemDB();
        itemdb.insert(item);
    }
}
