package model;

import org.junit.Assert;
import org.junit.Test;

public class OrderTest {

    Order order = new Order(1,1,1,10);

    @Test
    public void testId() {
        Assert.assertEquals(1, order.getId());
    }

    @Test
    public void testUserId() {
        Assert.assertEquals(1, order.getUserId());
    }

    @Test
    public void testProductId() {
        Assert.assertEquals(1, order.getProductId());
    }

    @Test
    public void testQuantityProduct() {
        Assert.assertEquals(10, order.getQuantityProducts());
    }
}
