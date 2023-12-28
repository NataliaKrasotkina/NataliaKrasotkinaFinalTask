package tests;

import cofiguration.MyTestWatcher;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.*;

import static pages.ClothesPage.*;

@ExtendWith(MyTestWatcher.class)
public class AddProductToWishlistTest extends BaseTest {
    private final static String PASSWORD = "NK852456!NK";
    private final static String LOGIN = "nataliakrasotkina7@gmail.com";
    private String productTitle;

    private LoginPage loginPage;
    private ClothesPage clothesPage;
    private ProductDetailsPage productDetailsPage;
    private MyWishListPage myWishListPage;


    @BeforeEach
    public void setUp() {
        loginPage = new LoginPage();
        clothesPage = new ClothesPage();
        productDetailsPage = new ProductDetailsPage();
        myWishListPage = new MyWishListPage();
    }

    @AfterEach
    public void cleanUp() {
        myWishListPage.removeProductFromWishList();
    }

    @Test
    @Description("The test checks add product to wishlist")
    public void addProductToWishlistTest() {
        homePage = loginPage.login(LOGIN, PASSWORD);
        clothesPage = homePage.getHeader().selectCategory(WOMEN, TOPS, JACKETS);
        navigateToWomenPage();
        navigateToProductDetailsPage();
        addProductToWishList();
        validateProductAddedToWishList();
    }
    @Step("Navigate To Women Page")
    private void navigateToWomenPage() {
        homePage.clickWomanLink();
    }

    @Step("Navigate To Product Details Page")
    private void navigateToProductDetailsPage() {
        clothesPage.clickProductByIndexIfExist(0);
        productTitle = productDetailsPage.getProductTitle();
    }

    @Step("Add Product To Wish List")
    private void addProductToWishList() {
        productDetailsPage.clickAddToWishListLink();
    }

    @Step("Validate Product Added To WishList")
    private void validateProductAddedToWishList() {
        Assertions.assertEquals(productTitle, myWishListPage.getProductName(), "Product is not added to wishlist");
    }
}