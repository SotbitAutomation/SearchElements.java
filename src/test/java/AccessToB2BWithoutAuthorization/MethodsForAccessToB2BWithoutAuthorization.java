package AccessToB2BWithoutAuthorization;

import BaseActions.BaseActions;
import Catalog.MethodsForCatalog;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class MethodsForAccessToB2BWithoutAuthorization extends BaseActions {
    MethodsForCatalog catalog = new MethodsForCatalog();

    public void expendMenuWithDropDownSectionsInWhiteHat(String nameDropDownSection) {
        int quantityButtonsMenuWithDropDown = driver.findElements(By.cssSelector(".nav-item.dropdown")).size();
        for (int i = 1; i <= quantityButtonsMenuWithDropDown; i++) {
            if (driver.findElement(By.xpath("(//*[contains(@class, 'nav-item dropdown')])[" + i + "]")).getText().contains(nameDropDownSection)) {
                driver.findElement(By.xpath("(//*[contains(@class, 'nav-item dropdown')])[" + i + "]")).click();
            }
        }
    }
    public void navigationToSubsectionFromTheAboutUsSection(String nameSubsectionFromTheAboutUsSection) {
        determineThemeColor();
        if (!themeColorBlack) {
            expendMenuWithDropDownSectionsInWhiteHat("О нас");
        }
        driver.findElement(By.xpath("//*[contains(@class, 'nav-item')]//*[contains(@href, '" + nameSubsectionFromTheAboutUsSection + "')]")).click();
    }

    public void checkingThatPageWithContactsIsOpened() {
        Assert.assertTrue(driver.findElement(By.cssSelector(".b2b-about_contacts")).isDisplayed());
    }

    public void checkingThatPageWithPaymentWaysIsOpened() {
        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(@class, 'page-title')]")).getText().contains("Оплата"));
    }

    public void checkingThatPageWithDeliveryWaysIsOpened() {
        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(@class, 'page-title')]")).getText().contains("Доставка"));
    }

    public void checkingThatPageWithNewsIsOpened() {
        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(@class, 'page-title')]")).getText().contains("Новости"));
    }
    public void checkingThatBreadcrumbContainsNavigationChain(String lastNavigationHyperlink) {
        Assert.assertTrue(driver.findElement(By.cssSelector(".breadcrumb")).getText().contains("Главная"));
        Assert.assertTrue(driver.findElement(By.cssSelector(".breadcrumb")).getText().contains(lastNavigationHyperlink));
    }
    public void tryToAddProductToTheCartWithoutAuthorization(){
        driver.findElement(By.xpath("(//*[@class='quantity-selector__increment'])[" + catalog.randomProductNumberOnThePage + "]")).click();
        implicitWaiting();
        Assert.assertEquals(driver.findElement(By.xpath("(//*[@class='quantity-selector__value'])[" + catalog.randomProductNumberOnThePage + "]")).getAttribute("value"), "0");
        Assert.assertEquals(driver.findElement(By.cssSelector("#catalog__basket-quantity-value")).getAttribute("value"), null);
        Assert.assertEquals(driver.findElement(By.cssSelector("#catalog__basket-price-value")).getAttribute("value"), null);
        driver.findElement(By.xpath("(//*[@class='quantity-selector__value'])[" + catalog.randomProductNumberOnThePage + "]")).sendKeys("1");
        implicitWaiting();
        Assert.assertEquals(driver.findElement(By.xpath("(//*[@class='quantity-selector__value'])[" + catalog.randomProductNumberOnThePage + "]")).getAttribute("value"), "0");
        Assert.assertEquals(driver.findElement(By.cssSelector("#catalog__basket-quantity-value")).getAttribute("value"), null);
        Assert.assertEquals(driver.findElement(By.cssSelector("#catalog__basket-price-value")).getAttribute("value"), null);
    }
    public void checkingThatThereAreNoOptPrices(){
        Assert.assertTrue(driver.findElements(By.xpath("//*[contains(@id, 'price_OPT')]")).size() == 0);
        Assert.assertTrue(driver.findElements(By.xpath("//*[contains(@id, 'SMALL_OPT')]")).size() == 0);
        Assert.assertTrue(driver.findElements(By.xpath("//*[contains(@id, 'price_TEST')]")).size() == 0);
        Assert.assertTrue(driver.findElements(By.xpath("//*[contains(@id, 'PRIVATE_PRICE')]")).size() == 0);
    }
    public void checkingThatThereAreOnlyRetailPrice(){
        Assert.assertEquals(driver.findElements(By.cssSelector(".bzd-prices__item")).size(), 1);
        Assert.assertEquals(driver.findElement(By.xpath("//*[contains(@class, 'prices__item-name')]")).getText(), "Розничная цена");
    }
    public void enterMessageForUnauthorizedUsers(){
        clearExistingMessage();
        randomData = "test сообщения для невторизованых юзеров " + randomString(5);
        driver.findElement(By.xpath("//*[@name='ALERT_FOR_NOT_AUTHORIZED_USER']")).sendKeys(randomData);
    }
    public void clearExistingMessage(){
        driver.findElement(By.xpath("//*[@name='ALERT_FOR_NOT_AUTHORIZED_USER']")).clear();
    }
    public void checkingWarningOutputForUnauthorizedUsers(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ui-pnotify-title")));
        Assert.assertEquals(driver.findElement(By.cssSelector(".ui-pnotify-title")).getText(), randomData);
    }
    public void navigationToCartByUrl() {
        driver.navigate().to(b2bUrl + "orders/make/");
    }
    public void checkingThatThereIsAStub(){
        Assert.assertTrue(driver.findElement(By.xpath("//img[contains(@src, 'error')]")).isDisplayed());
        Assert.assertTrue(driver.findElements(By.xpath("//*[contains(@href, 'auth')]")).size() ==2);
        driver.findElement(By.cssSelector(".icon-enter")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector(".login-form")).isDisplayed());
    }
}
