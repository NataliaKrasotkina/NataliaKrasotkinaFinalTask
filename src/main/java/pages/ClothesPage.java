package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ClothesPage extends BasePage {

    @FindBy(css = ".product-item-photo")
    private List<WebElement> productsList;

    @FindBy(css = ".grid .product-image-photo")
    private List<WebElement> topsProductsList;

    public static final String WOMEN = "Women";
    public static final String TOPS = "Tops";
    public static final String JACKETS = "Jackets";


    public void clickProductByIndexIfExist(int number) {
        if (productsList.size() >= number) {
            productsList.get(number).click();
        }
    }

    public void clickProductByIndex(int number) {
        topsProductsList.get(number).click();
    }
}