package pages;

import dto.Address;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class AddressBookPage extends BasePage {

    @FindBy(xpath = "//span[text()='Add New Address']")
    private WebElement addNewAddressButton;

    @FindBy(xpath = "//input[@id='telephone']")
    private WebElement phoneNumberField;

    @FindBy(xpath = "//input[@id='street_1']")
    private WebElement streetAddressField;

    @FindBy(xpath = "//input[@id='city']")
    private WebElement cityField;

    @FindBy(xpath = "//select[@id='region_id']")
    private WebElement stateProvinceList;

    @FindBy(xpath = "//select[@id='country']")
    private WebElement countryList;

    @FindBy(xpath = "//input[@id='zip']")
    private WebElement zipPostalCodeField;

    @FindBy(xpath = "//button [@class='action save primary']")
    private WebElement saveAddressButton;

    @FindBy(css = ".data tbody tr")
    private List<WebElement> savedAddressRows;

    @FindBy(css = "[data-th='Street Address']")
    private List<WebElement> savedStreetAddresses;

    private static final By DELETE_LINK = By.cssSelector("td .delete");


    public void clickAddNewAddressButton() {
        addNewAddressButton.click();
    }

    public void enterPhoneNumber(String phoneNumber) {
        phoneNumberField.sendKeys(phoneNumber);
    }

    public void enterStreetAddress(String streetAddress) {
        streetAddressField.sendKeys(streetAddress);
    }

    public void enterCity(String city) {
        cityField.sendKeys(city);
    }

    public void enterZipPostalCode(String zipPostalCode) {
        zipPostalCodeField.sendKeys(zipPostalCode);
    }

    public void clickSaveAddressButton() {
        saveAddressButton.click();
    }

    public void selectCountry() {
        Select dropdown = new Select(countryList);
        List<WebElement> countries = dropdown.getOptions();
        dropdown.selectByValue(countries.get(9).getAttribute("value"));
    }

    public boolean isAddressRecordDisplayed(Address address) {
        return getAddressRowBy(address) != null;
    }

    public void addAddress(Address address) {
        enterPhoneNumber(address.getPhone());
        enterStreetAddress(address.getStreetAddress());
        enterCity(address.getCity());
        enterZipPostalCode(address.getZipCode());
        selectCountry();
        clickSaveAddressButton();
    }

    public void deleteAddress(Address address) {
        WebElement addressRow = getAddressRowBy(address);
        addressRow.findElement(DELETE_LINK).click();
        deleteConfirmButton.click();
    }

    private WebElement getAddressRowBy(Address address) {
       return savedAddressRows.stream().filter(row -> row.getText().contains(address.getStreetAddress()))
               .findFirst().orElseThrow();
    }
}