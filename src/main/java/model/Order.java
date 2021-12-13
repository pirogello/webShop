package model;

public class Order {
    private int id;
    private int userId;
    private int productId;
    private int quantityProducts;

   /* public Order(int userId, int productId, int quantityProducts) {
        this.userId = userId;
        this.productId = productId;
        this.quantityProducts = quantityProducts;
    }*/

    public Order(int id, int userId, int productId, int quantityProducts) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.quantityProducts = quantityProducts;
    }

    public Order() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantityProducts() {
        return quantityProducts;
    }

    public void setQuantityProducts(int quantityProducts) {
        this.quantityProducts = quantityProducts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
