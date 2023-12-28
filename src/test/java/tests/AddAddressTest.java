package tests;

import cofiguration.MyTestWatcher;
import dto.Address;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.AddressBookPage;
import pages.HomePage;
import pages.LoginPage;
import pages.MyAccountPage;
import service.DataService;

@ExtendWith(MyTestWatcher.class)
public class AddAddressTest extends BaseTest {
    private Address address;
    private final static String PASSWORD = "NK852456!NK";
    private final static String LOGIN = "nataliakrasotkina7@gmail.com";

    private LoginPage loginPage;
    private MyAccountPage myAccountPage;
    private AddressBookPage addressBookPage;

    @BeforeEach
    public void setUp() {
        loginPage = new LoginPage();
        myAccountPage = new MyAccountPage();
        addressBookPage = new AddressBookPage();
        address = DataService.getAddress();
    }

    @AfterEach
    public void cleanUp() {
        addressBookPage.deleteAddress(address);
    }

    @Test
    @Description("The test checks add new address")
    public void addAddressTest() {
        homePage = loginPage.login(LOGIN, PASSWORD);
        navigateToMyAccount();
        addNewAddress();
        validateAddressAdded();
    }

    @Step("Navigate To My Account")
    private void navigateToMyAccount() {
        homePage.clickSwitchArrow();
        homePage.clickMyAccountLink();
    }

    @Step("Add New Address")
    private void addNewAddress() {
        myAccountPage.clickAddressBookLink();
        addressBookPage.clickAddNewAddressButton();
        addressBookPage.addAddress(address);
    }

    @Step("Validate Address Added")
    private void validateAddressAdded() {
        Assertions.assertTrue(addressBookPage.isAddressRecordDisplayed(address), "Address is not added");
    }
}
