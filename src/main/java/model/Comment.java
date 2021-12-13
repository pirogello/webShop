package model;

public class Comment {
    private Integer id;
    private User user;
    private Product product;
    private String text;

    public Comment() {
    }

    public Comment(Integer id, User user, Product product, String text) {
        this.id = id;
        this.user = user;
        this.product = product;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", user=" + user +
                ", product=" + product +
                ", text='" + text + '\'' +
                '}';
    }
}
