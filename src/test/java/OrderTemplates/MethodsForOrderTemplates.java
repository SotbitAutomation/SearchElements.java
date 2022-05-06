package OrderTemplates;

import Catalog.MethodsForCatalog;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MethodsForOrderTemplates extends MethodsForCatalog {
    By orderTemplatesLocator = By.xpath("//a[contains(@href, 'templates')]");

    public void navigationToOrderTemplates(){
        determineThemeColor();
        if (!themeColorBlack) {
            expandMenuWithUnderMenuInWhiteHat("Заказы");
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(orderTemplatesLocator));
        driver.findElement(orderTemplatesLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='breadcrumb']/*[text()='Шаблоны заказов']")));
        checkingBreadcrumbs("Шаблоны заказов");
        checkingPageTitle("Шаблоны заказов");
    }
}
