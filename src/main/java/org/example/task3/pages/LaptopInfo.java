package org.example.task3.pages;

public class LaptopInfo {
    private String name;
    private String price;
    private boolean isResale;

    public LaptopInfo(String name, String price, boolean isResale) {
        this.name = name;
        this.price = price;
        this.isResale = isResale;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public boolean getIsResale() {
      return isResale;
    }

    public String toString() {
        return name + " " + price + " " + isResale;

    }
}
