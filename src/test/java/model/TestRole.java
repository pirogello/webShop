package model;

import org.junit.Assert;
import org.junit.Test;

public class TestRole {
    Role role = new Role(1, "user");

    @Test
    public void testId() {
        Assert.assertEquals(1, role.getId());
    }

    @Test
    public void testTitle() {
        Assert.assertEquals("user", role.getTitle());
    }
}
