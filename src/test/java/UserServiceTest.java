package com.revature.project0.services;

import com.revature.project0.daos.UserDAO;
import com.revature.project0.models.User;
import com.revature.project0.daos.ProductDAO;
import com.revature.project0.models.Product;
import com.revature.project0.util.custom_exceptions.InvalidUserException;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserServiceTest {
    UserService userService = new UserService(new UserDAO());
    ProductService productService = new ProductService(new ProductDAO());

    @Test
    public void isDuplicateUsername_willThrowExceptionIfThereIsADupUsername() {
        /* Act */
        String username = "megan1234";

        /* Arrange */
        boolean isTrue = userService.isNotDuplicateUsername(username);

        /* Assert */
        assertTrue(isTrue);
    }

    @Test
    public void login_willThrowExceptionIfUsernameIsEmpty() {
        /* AAA = Act, Arrange, Assert */

        /* Act */
        String username = "";
        String password = "P@ssw0rd";

        /* Arrange */

        /* Assert */
        Assert.assertThrows(InvalidUserException.class, () -> userService.login(username, password));
    }

    @Test
    public void login_willThrowExceptionIfPasswordIsEmpty() {
        /* AAA = Act, Arrange, Assert */

        /* Act */
        String username = "megan1234";
        String password = "";

        /* Arrange */

        /* Assert */
        Assert.assertThrows(InvalidUserException.class, () -> userService.login(username, password));
    }

    @Test
    public void willGetProductById() {
        String goodID = "83c9fe0f-a639-4485-917b-0cfe760a99b8";
        String badId = "1234";

        Product goodAssertPass = productService.getById(goodID);
        Product badAssertFail = productService.getById(badId);

        assertEquals(goodAssertPass.getName(), "Silver Necklace");
        assertEquals(goodAssertPass.getDescription(), "Very simple design");
        assertNull(badAssertFail.getName());
        assertNull(badAssertFail.getDescription());
    }

    public void willGetAllProducts() {
        List<Product> prods = productService.getAllProd();

        assertEquals(productService.getAllProd().get(1).getId(), "5f08b2ba-f008-4998-aff5-0803e847e8bb");
//        will return false because there are products
        assertFalse(productService.getAllProd().isEmpty());
    }
}
