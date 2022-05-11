package OrderTemplates;

import Catalog.MethodsForCatalog;
import MakingOrders.MethodsForMakingOrders;
import OrganizationsWithExtendedVersion.MethodsForAddingOrganizationsWithExtendedVersion;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class MethodsForOrderTemplates extends MethodsForCatalog {
    MethodsForMakingOrders makeOrder = new MethodsForMakingOrders();
    MethodsForAddingOrganizationsWithExtendedVersion org = new MethodsForAddingOrganizationsWithExtendedVersion();
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
        System.out.println("Имя создаваемого шаблона -  " + randomNameTemplate);
    }
    public void setOrganizationForOrderTemplate(){
        driver.findElement(By.xpath("//*[contains(@class, 'selection__rendered')] //input")).sendKeys(nameCompany);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class, 'results__option--highlighted')]")));
        driver.findElement(By.xpath("//*[contains(@class, 'results__option--highlighted')]")).click();
    }
    public void createOrderTemplate(){
        driver.findElement(By.cssSelector(".save-btn")).click();
    }
    public void checkingThatCreatedOrderTemplateDisplayed(){
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='" + randomNameTemplate + "']")).isDisplayed());
    }
    public void checkingThatCreatedOrderTemplateIsNotDisplayed(){
        Assert.assertTrue(driver.findElements(By.xpath("//*[text()='" + randomNameTemplate + "']")).size() == 0);
    }
    public void makingOrderWithItemsFromOrderTemplate(){
        makeOrder.navigationToMakingOrderFromCart();
        makeOrder.trySelectCompany();
        makeOrder.makingOrder();
    }
    public void expendHamburgerMenuInFirstOrderTemplate(){
//        if (driver.findElements(By.cssSelector(".main-grid-row-body")).size()<2){
//            uploadingExcelCatalogForOrderTemplates("blank.xlsx");
//            setNameForOrderTemplate();
//            createOrderTemplate();
//            navigationToOrderTemplates();
//            checkingThatCreatedOrderTemplateDisplayed();
//        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@title, 'Изменить')] //*[@class='main-grid-row-action-button']")));
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
        int numberOfItems = driver.findElements(By.xpath("//*[@class= 'card-body index_checkout-table'] //tbody/tr")).size();
        Assert.assertTrue(numberOfItems == 1);
    }
    public void clickingIconForChangingQuantityItemsInTheCart(int itemNumber, String typeOfChange){
        driver.findElement(By.xpath("(//*[@data-entity='basket-item-quantity-" + typeOfChange + "'])[" + itemNumber + "]")).click();
    }
    double sumPricesInTheCart;
    public void memorizingTheTotalPriceInTheCart(){
        sumPricesInTheCart = Double.valueOf(replacingSomeSymbols(driver.findElement(By.cssSelector(".basket-page__total-price-value")).getText()));
    }
    public void waitingUntilTotalSumIsChanged(){
        double sumPricesInTheCartAfterChangeItems;
        flag = false;
        count = 0;
        while (!flag){
            count++;
            sumPricesInTheCartAfterChangeItems = Double.valueOf(replacingSomeSymbols(driver.findElement(By.cssSelector(".basket-page__total-price-value")).getText()));
            if (sumPricesInTheCart != sumPricesInTheCartAfterChangeItems){
                flag = true;
            }else {
                if (count>1000){
                    System.out.println(5/0);
                }
            }
        }
    }
    public void checkingThatPriceForOrderOnTheMakingOrderPageAndInTheCartIsTheSame(){
        double sumForOrderOnTheMakingOrderPage = Double.valueOf(replacingSomeSymbols(driver.findElement(By.xpath("//h4[text()='Заказ']/ following::td[" + makeOrder.tempInt + "]"))
                .getText()));
        Assert.assertTrue(sumForOrderOnTheMakingOrderPage == sumPricesInTheCart);
    }
    public void determineCurrentOrganizationName(){
        if (themeColorBlack){
            if (driver.findElements(By.cssSelector(".auth-company-change__current")).size()>0){
                nameCompany = driver.findElement(By.cssSelector(".auth-company-change__current")).getText();
            }else {
                org.findingColumnWithOrganizationName("Название");
                nameCompany = driver.findElement(By.xpath("(//tbody /*[@class='main-grid-row main-grid-row-body'] //*[@class='main-grid-cell-content'])[" + (org.count+1) + "]"))
                        .getText();
                confirmRegistrationOfOrganizationInB2bFromTheUser();
                navigationToAuthorizationTab();
                fillingFieldsOnTheLogInTabLikeUser();
                logInToB2B();
            }
        }else {
            if (driver.findElements(By.xpath("//*[@title='Сменить компанию']")).size()>0){
                nameCompany = driver.findElement(By.xpath("//*[@title='Сменить компанию']")).getText();
            }else {
                org.findingColumnWithOrganizationName("Название");
                nameCompany = driver.findElement(By.xpath("(//tbody /*[@class='main-grid-row main-grid-row-body'] //*[@class='main-grid-cell-content'])[" + (org.count+1) + "]"))
                        .getText();
                confirmRegistrationOfOrganizationInB2bFromTheUser();
                navigationToAuthorizationTab();
                fillingFieldsOnTheLogInTabLikeUser();
                logInToB2B();
            }
        }
        org.nameCompany = nameCompany;
    }
    public void selectingOrganizationForWhichAnotherUserHasCreatedAnOrderTemplate(){
        try {
            org.selectionFromTheHeaderOrganization(nameCompany);
        }catch (Exception e){
            System.out.println("Присоединяю сотрудника к этой компании -  " + nameCompany);
            org.requestToJoinTheCompany();
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeUser();
            logInToB2B();
            org.navigationToPersonsTab();
            org.confirmEmployeeRequest();
            navigationToAuthorizationTab();
            fillingFieldsOnTheLogInTabLikeEmployee();
            logInToB2B();
            navigationToOrganizationTab();
            org.selectionFromTheHeaderOrganization(nameCompany);
        }
    }
    public void deletingFirstOrderTemplate(){
        expendHamburgerMenuInFirstOrderTemplate();
        driver.findElement(By.xpath("//*[@class='menu-popup-item-text'][text()='Удалить']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='modal-open']/*[@class='page-content'] /*[@class='content-wrapper'] /*[@class='content'] /*[@id='modal_order-remove']//*[@class='modal-content']")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@onclick='removeTemplate()']")));
        driver.findElement(By.xpath("//*[@onclick='removeTemplate()']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='modal_order-remove-success'] //*[@data-dismiss='modal']")));
        driver.findElement(By.xpath("//*[@id='modal_order-remove-success'] //*[@data-dismiss='modal']")).click();
        refreshingThisPage();
    }


}
