package OrderTemplates;

import Catalog.MethodsForCatalog;
import MakingOrders.MethodsForMakingOrders;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class MethodsForOrderTemplates extends MethodsForCatalog {
    MethodsForMakingOrders makeOrder = new MethodsForMakingOrders();
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
    public void uploadingExcelCatalogForOrderTemplates(String nameCatalog) {
        driver.findElement(actionsButtonLocator).click();
        driver.findElement(By.cssSelector("#add-ordertemplate")).click();
        choiceExcelFromResources(nameCatalog);
    }
    String randomNameTemplate;
    public void setNameForOrderTemplate(){
        driver.findElement(By.xpath("//*[@name='TEMPLATE_NAME']")).clear();
        randomNameTemplate = randomString(20);
        driver.findElement(By.xpath("//*[@name='TEMPLATE_NAME']")).sendKeys(randomNameTemplate);
    }
    public void createOrderTemplate(){
        driver.findElement(By.cssSelector(".save-btn")).click();
    }
    public void checkingThatCreatedOrderTemplateDisplayed(){
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='" + randomNameTemplate + "']")).isDisplayed());

    }
    public void makingOrderWithItemsFromOrderTemplate(){
        makeOrder.navigationToMakingOrderFromCart();
        makeOrder.trySelectCompany();
        makeOrder.makingOrder();
    }
    public void expendHamburgerMenuInFirstOrderTemplate(){
        if (driver.findElements(By.cssSelector(".main-grid-row-body")).size()<2){
            uploadingExcelCatalogForOrderTemplates("blank.xlsx");
            setNameForOrderTemplate();
            createOrderTemplate();
            navigationToOrderTemplates();
            checkingThatCreatedOrderTemplateDisplayed();
        }
        driver.findElement(By.xpath("//*[contains(@title, 'Изменить')] //*[@class='main-grid-row-action-button']")).click();
    }
    public void addingItemsToTheCartFromOrderTemplate(){
        expendHamburgerMenuInFirstOrderTemplate();
        driver.findElement(By.xpath("//*[@class='menu-popup-item-text'][text()='Создать заказ']")).click();
        confirmTheConfidenceThatTheBasketWillBeReplacedWithProductsFromTheOrderTemplate();
    }
    public void confirmTheConfidenceThatTheBasketWillBeReplacedWithProductsFromTheOrderTemplate(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@onclick='addToBasket()']")));
        driver.findElement(By.xpath("//*[@onclick='addToBasket()']")).click();
    }
    public void clickingLookOrderTemplate(){
        driver.findElement(By.xpath("//*[@class='menu-popup-item-text'][text()='Просмотреть']")).click();
    }
    public void clickingActionsInOpenedOrderTemplate(){
        driver.findElement(By.xpath("//*[@data-toggle='dropdown'][contains(@class, 'btn_b2b')]")).click();
    }
    public void clickingButtonForCreatingOrderInTheActions(){
        driver.findElement(By.xpath("//*[contains(@data-target, 'add-basket')]")).click();
    }
    public void checkingThatItemIsDeletedFromOrder(){
        int numberOfItems = Integer.parseInt(replacingSomeSymbols(driver.findElement(By.xpath("//h4[text()='Заказ']/ following::td[" + makeOrder.tempInt + "]"))
                .getText()));
        Assert.assertTrue(numberOfItems == 1);
    }


}
