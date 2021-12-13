package model;

import org.junit.Assert;
import org.junit.Test;

public class TestProduct {

    User user = new User(1,"fName", "sName", "password", new Role(1, "user"));
    Product product = new Product(1, "title", 1000, 10, "image", "description", user, true, 3);

    @Test
    public void testId() {
        Assert.assertEquals(1, product.getId());
    }

    @Test
    public void testText() {
        Assert.assertEquals("title", product.getTitle());
    }

    @Test
    public void testQuantity() {
        Assert.assertEquals(1000, product.getQuantity());
    }

    @Test
    public void testPrice() {
        Assert.assertEquals(10, product.getPrice());
    }

    @Test
    public void testImageHref() {
        Assert.assertEquals("image", product.getImageHref());
    }

    @Test
    public void testDescription() {
        Assert.assertEquals("description", product.getDescription());
    }

    @Test
    public void testUser() {
        Assert.assertEquals(user, product.getUser());
    }

    @Test
    public void testHighlighting() {
        Assert.assertEquals(true, product.isHighlighting());
    }

    @Test
    public void testPriorityInList() {
        Assert.assertEquals(3, product.getPriorityInList());
    }
}
