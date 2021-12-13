package model;

import org.junit.Assert;
import org.junit.Test;

public class TestUsers {

    Role role = new Role(1, "user");
    User user = new User(1,"fName", "sName", "password", role);

    @Test
    public void testId() {
        Assert.assertEquals(1, user.getId());
    }

    @Test
    public void testFirstName() {
        Assert.assertEquals("fName", user.getFirstName());
    }

    @Test
    public void testSecondName() {
        Assert.assertEquals("sName", user.getSecondName());
    }

    @Test
    public void testPassword() {
        Assert.assertEquals("password", user.getPassword());
    }

    @Test
    public void testRole() {
        Assert.assertEquals(role, user.getRole());
    }
}
