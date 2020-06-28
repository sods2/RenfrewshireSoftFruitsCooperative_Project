package com.RenfrewshireSoftFruitsCooperative_Project.java.Components;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Entities.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alessandro Spano (Student N. rmb19196)
 */
public class FruitManager {

    List<Fruit> fruitList = new ArrayList<>();

    /**
     *  Getting Fruit's CODE By ID
     * @param id
     * @return Fruit's CODE
     */
    public String getFruitCodesByID(String id){

        fruitList = getFruitList();

        for(Fruit f : fruitList) {
            if (f.getID().equalsIgnoreCase(id)){
                return f.getCode();
            }
        }
        return "";
    }

    /**
     * Getting List of Fruit
     * @return List of Fruit
     */
    public List<Fruit> getFruitList(){

        fruitList.add(new Strawberries());
        fruitList.add(new Raspberries());
        fruitList.add(new Blackberries());
        fruitList.add(new Gooseberries());

        return fruitList;
    }

    /**
     * Getting Fruit's NAME By CODE
     * @param code
     * @return Fruit's NAME
     */
    public String getFruitNameByCode(String code){
        fruitList = getFruitList();

        for(Fruit f : fruitList) {
            if (f.getCode().equalsIgnoreCase(code)) {
                return f.getName();
            }
        }
        return "null";
    }

}
