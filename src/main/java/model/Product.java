package model;

import java.util.Objects;

public class Product {
    private int id;
    private String title;
    private int quantity;
    private int price;
    private String imageHref;
    private String description;
    private User user;
    private boolean highlighting;
    private int priorityInList;


    public Product() {
    }

   /* public Product(int id, String title, int quantity, int price, String imageHref, String description, User user) {
        this.id = id;
        this.title = title;
        this.quantity = quantity;
        this.price = price;
        this.imageHref = imageHref;
        this.description = description;
        this.user = user;
    }
*/
    public Product(int id, String title, int quantity, int price, String imageHref, String description, User user, boolean highlighting, int priorityInList) {
        this.id = id;
        this.title = title;
        this.quantity = quantity;
        this.price = price;
        this.imageHref = imageHref;
        this.description = description;
        this.user = user;
        this.highlighting = highlighting;
        this.priorityInList = priorityInList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImageHref() {
        return imageHref;
    }

    public void setImageHref(String imageHref) {
        this.imageHref = imageHref;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isHighlighting() {
        return highlighting;
    }

    public void setHighlighting(boolean highlighting) {
        this.highlighting = highlighting;
    }

    public int getPriorityInList() {
        return priorityInList;
    }

    public void setPriorityInList(int priorityInList) {
        this.priorityInList = priorityInList;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", imageHref='" + imageHref + '\'' +
                ", description='" + description + '\'' +
                ", user=" + user +
                ", highlighting=" + highlighting +
                ", priorityInList=" + priorityInList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && quantity == product.quantity && price == product.price && Objects.equals(title, product.title) && Objects.equals(imageHref, product.imageHref) && Objects.equals(description, product.description) && Objects.equals(user, product.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, quantity, price, imageHref, description, user);
    }
}
