package model;

import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestComments {

    User user = new User(1,"fName", "sName", "password", new Role(1, "user"));
    Product product = new Product(1, "title", 1000, 10, "image", "description", user, true, 3);
    Comment comment = new Comment(1, user, product,"comment");

    @Test
    public void testId() {
        Assert.assertEquals(1, comment.getId());
    }

    @Test
    public void testText() {
        Assert.assertEquals("comment", comment.getText());
    }

    @Test
    public void testUser() {
        Assert.assertEquals(user, comment.getUser());
    }

    @Test
    public void testProduct() {
        Assert.assertEquals(product, comment.getProduct());
    }

}
