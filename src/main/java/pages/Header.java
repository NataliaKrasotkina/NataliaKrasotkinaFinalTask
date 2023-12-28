package pages;

import org.apache.commons.lang3.ArrayUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class Header extends BasePage {

    @FindBy(css = ".action.showcart")
    private WebElement shopCartIcon;

    @FindBy(css = ".counter-number")
    private WebElement counterNumberIcon;

    @FindBy(css = "a.level-top")
    protected List<WebElement> menuCategories;

    @FindBy(css = ".submenu .category-item a")
    protected List<WebElement> subMenuCategories;

    public void clickShopCartIcon() {
        shopCartIcon.click();
    }

    public void waitForProductsNumberIcon() {
        waitForElementVisible(counterNumberIcon);
    }

    public ClothesPage selectCategory(String categoryName, String... subCategoryNames) {
        WebElement menuCategory = menuCategories.stream()
                .filter(element -> element.getText().equals(categoryName)).findFirst().orElseThrow();
        hover(menuCategory);
        WebElement targetMenu = menuCategory;
        if (!ArrayUtils.isEmpty(subCategoryNames)) {
            for (String subCategoryName : subCategoryNames) {
                targetMenu = subMenuCategories.stream().filter(element -> element.getText().equals(subCategoryName))
                        .findFirst().orElseThrow();
                hover(targetMenu);
            }
        }
        targetMenu.click();
        return new ClothesPage();
    }
}