package es.source.code.model;

import java.io.Serializable;

/**
 * Created by zhoutao on 2017/10/13.
 */

public class Food implements Serializable{
    String foodName;
    int foodPrice;
    int foodQuantity;
    int imageID;
    String foodInfo;

    public Food(){

    }

    public Food(String foodName, int foodPrice, int foodQuantity, int imageID, String foodInfo) {
        this.foodName = foodName;
        this.imageID = imageID;
        this.foodPrice = foodPrice;
        this.foodQuantity = foodQuantity;
        this.foodInfo = foodInfo;
    }

    public int getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(int foodPrice) {
        this.foodPrice = foodPrice;
    }

    public int getFoodQuantity() {
        return foodQuantity;
    }

    public void setFoodQuantity(int foodQuantity) {
        this.foodQuantity = foodQuantity;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getFoodInfo() {
        return foodInfo;
    }

    public void setFoodInfo(String foodInfo) {
        this.foodInfo = foodInfo;
    }
}
