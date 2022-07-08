package order_templates;

import catalog.MethodsForCatalog;
import making_orders.MethodsForMakingOrders;
import organizations_with_extended_version.MethodsForAddingOrganizationsWithExtendedVersion;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.ArrayList;

public class MethodsForOrderTemplates extends MethodsForCatalog {
    MethodsForMakingOrders makeOrder = new MethodsForMakingOrders();
    MethodsForAddingOrganizationsWithExtendedVersion org = new MethodsForAddingOrganizationsWithExtendedVersion();
    By orderTemplatesLocator = By.xpath("//a[contains(@href, 'templates')]");

    public void navigationToOrderTemplates() {
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

    public void uploadingExcelCatalogForOrderTemplatesWithAcceptIt(String nameCatalog) {
        uploadingExcelCatalogForOrderTemplates(nameCatalog);
        clickAcceptTheUploadedFile();
    }
    public void uploadingExcelCatalogForOrderTemplates(String nameCatalog) {
        driver.findElement(actionsButtonLocator).click();
        driver.findElement(By.cssSelector("#add-ordertemplate")).click();
        choiceExcelFromResources(nameCatalog);
    }

    String randomNameTemplate;

    public void setNameForOrderTemplate() {
        driver.findElement(By.xpath("//*[@name='TEMPLATE_NAME']")).clear();
        randomNameTemplate = "Немного кириллицы " + randomString(20);
        driver.findElement(By.xpath("//*[@name='TEMPLATE_NAME']")).sendKeys(randomNameTemplate);
        System.out.println("Имя создаваемого шаблона -  " + randomNameTemplate);
    }

    public void setOrganizationForOrderTemplate() {
        driver.findElement(By.xpath("//*[contains(@class, 'selection__rendered')] //input")).sendKeys(nameCompany);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class, 'results__option--highlighted')]")));
        driver.findElement(By.xpath("//*[contains(@class, 'results__option--highlighted')]")).click();
    }

    public void createOrderTemplate() {
        clickElementByItsCssSelector(".save-btn");
    }
    public void createOrderTemplateWithJustUnloadedFile(){
        driver.findElement(actionsButtonLocator).click();
        driver.findElement(By.cssSelector("#add-ordertemplate")).click();
        By fileInput = By.xpath("//input[@class='file-fileUploader']");
        String filePath = System.getProperty("user.home") + "\\Downloads\\" + randomNameTemplate + ".xlsx";
        System.out.println(filePath);
        driver.findElement(fileInput).sendKeys(filePath);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class, 'file-extended')] //*[contains(@class, 'files-size')]")));
        driver.findElement(By.xpath("//*[contains(@name,'send_file')]")).click();
    }

    public void checkingThatCreatedOrderTemplateIsDisplayed() {
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='" + randomNameTemplate + "']")).isDisplayed());
    }

    public void checkingThatCreatedOrderTemplateIsNotDisplayed() {
        Assert.assertTrue(driver.findElements(By.xpath("//*[text()='" + randomNameTemplate + "']")).size() == 0);
    }

    public void makingOrderWithItemsFromOrderTemplate() {
        makeOrder.navigationToMakingOrderFromCart();
        makeOrder.trySelectCompany();
        makeOrder.makingOrder();
    }

    public void expendHamburgerMenuInFirstOrderTemplate() {
        if (driver.findElements(By.cssSelector(".main-grid-cell.main-grid-cell-action")).size()<2){
            uploadingExcelCatalogForOrderTemplatesWithAcceptIt("blankForOrderTemplates.xlsx");
            setNameForOrderTemplate();
            createOrderTemplate();
            navigationToOrderTemplates();
            checkingThatCreatedOrderTemplateIsDisplayed();
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".main-grid-row-action-button")));
        driver.findElement(By.cssSelector(".main-grid-row-action-button")).click();
    }

    public void addingItemsToTheCartFromOrderTemplate() {
        expendHamburgerMenuInFirstOrderTemplate();
        driver.findElement(By.xpath("//*[@class='menu-popup-item-text'][text()='Создать заказ']")).click();
        confirmTheConfidenceThatTheBasketWillBeReplacedWithProductsFromTheOrderTemplate();
    }
    public void clickingChange(){
        driver.findElement(By.xpath("//*[@class='menu-popup-item-text'][text()='Изменить']")).click();
    }

    public void confirmTheConfidenceThatTheBasketWillBeReplacedWithProductsFromTheOrderTemplate() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@onclick='addToBasket()']")));
        driver.findElement(By.xpath("//*[@onclick='addToBasket()']")).click();
    }

    public void clickingLookOrderTemplate() {
        driver.findElement(By.xpath("//*[@class='menu-popup-item-text'][text()='Просмотреть']")).click();
    }
    public void clickingUploadExcel() {
        driver.findElement(By.xpath("//*[@class='menu-popup-item-text'][text()='Выгрузить Excel']")).click();
        implicitWaiting();implicitWaiting();
    }

    public void clickingActionsInOpenedOrderTemplate() {
        driver.findElement(By.xpath("//*[@data-toggle='dropdown'][contains(@class, 'btn_b2b')]")).click();
    }

    public void clickingButtonForCreatingOrderInTheActions() {
        driver.findElement(By.xpath("//*[contains(@data-target, 'add-basket')]")).click();
    }

    public void checkingThatItemIsDeletedFromOrder() {
        int numberOfItems = driver.findElements(By.xpath("//*[@class= 'card-body index_checkout-table'] //tbody/tr")).size();
        Assert.assertTrue(numberOfItems == 1);
    }

    public void clickingIconForChangingQuantityItemsInTheCart(int itemNumber, String typeOfChange) {
        driver.findElement(By.xpath("(//*[@data-entity='basket-item-quantity-" + typeOfChange + "'])[" + itemNumber + "]")).click();
    }

    double sumPricesInTheCart;

    public void memorizingTheTotalPriceInTheCart() {
        sumPricesInTheCart = Double.valueOf(replacingSomeSymbols(driver.findElement(By.cssSelector(".basket-page__total-price-value")).getText()));
    }

    public void waitingUntilTotalSumIsChanged() {
        double sumPricesInTheCartAfterChangeItems;
        flag = false;
        count = 0;
        while (!flag) {
            count++;
            sumPricesInTheCartAfterChangeItems = Double.valueOf(replacingSomeSymbols(driver.findElement(By.cssSelector(".basket-page__total-price-value")).getText()));
            if (sumPricesInTheCart != sumPricesInTheCartAfterChangeItems) {
                flag = true;
            } else {
                if (count > 1000) {
                    System.out.println(5 / 0);
                }
            }
        }
    }

    public void checkingThatPriceForOrderOnTheMakingOrderPageAndInTheCartIsTheSame() {
        double sumForOrderOnTheMakingOrderPage = Double.valueOf(replacingSomeSymbols(driver.findElement(By.xpath("//h4[text()='Заказ']/ following::td[" + makeOrder.tempInt + "]"))
                .getText()));
        Assert.assertTrue(sumForOrderOnTheMakingOrderPage == sumPricesInTheCart);
    }

    public void determineCurrentOrganizationName() {
        if (themeColorBlack) {
            if (driver.findElements(By.cssSelector(".auth-company-change__current")).size() > 0) {
                nameCompany = driver.findElement(By.cssSelector(".auth-company-change__current")).getText();
            } else {
                org.findingColumnWithOrganizationName("Название");
                nameCompany = driver.findElement(By.xpath("(//tbody /*[@class='main-grid-row main-grid-row-body'] //*[@class='main-grid-cell-content'])[" + (org.count + 1) + "]"))
                        .getText();
                confirmRegistrationOfOrganizationInB2bFromTheUser();
                navigationToAuthorizationTab();
                fillingFieldsOnTheLogInTabLikeUser();
                logInToB2B();
            }
        } else {
            if (driver.findElements(By.xpath("//*[@title='Сменить компанию']")).size() > 0) {
                nameCompany = driver.findElement(By.xpath("//*[@title='Сменить компанию']")).getText();
            } else {
                org.findingColumnWithOrganizationName("Название");
                nameCompany = driver.findElement(By.xpath("(//tbody /*[@class='main-grid-row main-grid-row-body'] //*[@class='main-grid-cell-content'])[" + (org.count + 1) + "]"))
                        .getText();
                confirmRegistrationOfOrganizationInB2bFromTheUser();
                navigationToAuthorizationTab();
                fillingFieldsOnTheLogInTabLikeUser();
                logInToB2B();
            }
        }
        org.nameCompany = nameCompany;
    }

    public void selectingOrganizationForWhichAnotherUserHasCreatedAnOrderTemplate() {
        try {
            org.selectionFromTheHeaderOrganization(nameCompany);
        } catch (Exception e) {
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

    public void deletingFirstOrderTemplate() {
        expendHamburgerMenuInFirstOrderTemplate();
        driver.findElement(By.xpath("//*[@class='menu-popup-item-text'][text()='Удалить']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@onclick='removeTemplate()']")));
        driver.findElement(By.xpath("//*[@onclick='removeTemplate()']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='modal_order-remove-success'] //*[@data-dismiss='modal']")));
        driver.findElement(By.xpath("//*[@id='modal_order-remove-success'] //*[@data-dismiss='modal']")).click();
        refreshingThisPage();
    }

    public void addingAnEmployeeWithBossRoleToOrganizationWithOrderTemplate() {
        readingUserData();
        org.emailEmployee = emailEmployee;
        org.fillingFieldForCreatingEmployeeUsingReferralLink();
        org.choosingBossRole();
        try {
            org.addingAnEmployeeToAnOrganizationUsingByReferralLink();
        } catch (Exception e) {
            navigationToMeanPageByUrl();
            navigationToEmployeesTab();
            expendHamburgerMenuInFirstOrderTemplate();
            driver.findElement(By.xpath("//*[@class='menu-popup-item-text'][text()='Удалить']")).click();
            org.fillingFieldForCreatingEmployeeUsingReferralLink();
            org.choosingBossRole();
            org.addingAnEmployeeToAnOrganizationUsingByReferralLink();
        }
    }

    public void checkingColumnHeadersOrderTemplateTable() {
        String allNamesOfColumnsOrderTemplateTable = driver.findElement(By.xpath("//*[@id='TEMPLATE_LIST_table']/*[@class='main-grid-header']")).getText();
        Assert.assertTrue(allNamesOfColumnsOrderTemplateTable.contains("Название"));
        Assert.assertTrue(allNamesOfColumnsOrderTemplateTable.contains("Дата создания"));
        Assert.assertTrue(allNamesOfColumnsOrderTemplateTable.contains("Автор шаблона"));
    }

    public void checkingColumnHeadersOnTheDetailPageOfOrderTemplate() {
        String allNamesOfColumnsOrderTemplateTableOnTheDetailPage = driver.findElement(By.xpath("//*[@id='ORDER_TEMPLATE_DETAIL_table']/*[@class='main-grid-header']")).getText();
        Assert.assertTrue(allNamesOfColumnsOrderTemplateTableOnTheDetailPage.contains("Наименование"));
        Assert.assertTrue(allNamesOfColumnsOrderTemplateTableOnTheDetailPage.contains("Количество"));
        Assert.assertTrue(allNamesOfColumnsOrderTemplateTableOnTheDetailPage.contains("Стоимость"));
    }

    public void findingNumberOFColumnWithNeededName(String neededName) {
        int numberOfHeaders = driver.findElements(By.xpath("//*[@id='ORDER_TEMPLATE_DETAIL_table'] //th[contains(@class, 'main-grid-cell-head')]")).size();
        for (int i = 1; i <= numberOfHeaders; i++) {
            if (driver.findElement(By.xpath("(//*[@id='ORDER_TEMPLATE_DETAIL_table'] //th[contains(@class, 'main-grid-cell-head')])[" + i + "]"))
                    .getText().contains(neededName)) {
                count = i;
                break;
            }
        }
    }

    public void checkingNumberOfItemsOnTheOrderTemplateDetailPage(String numberOfItem, String expectedQuantity) {
        String quantityOfThisItem = driver.findElement(By.xpath("((//tbody //*[@class= 'main-grid-row main-grid-row-body'])[" + numberOfItem + "] //*[@class='main-grid-cell-content'])[" + count + "]"))
                .getText();
        Assert.assertEquals(quantityOfThisItem, expectedQuantity);
    }

    public void checkingPriceOfItemOnTheOrderTemplateDetailPage(String numberOfItem, String expectedPrice) {
        String priceOfThisItem = replacingSomeSymbols(driver.findElement(
                        By.xpath("((//tbody //*[@class= 'main-grid-row main-grid-row-body'])[" + numberOfItem + "] //*[@class='main-grid-cell-content'])[" + count + "]"))
                .getText());
        Assert.assertEquals(priceOfThisItem, expectedPrice);
    }
    public void checkingNameOfItemOnTheOrderTemplateDetailPage(String numberOfItem, String expectedName) {
        String nameOfThisItem = driver.findElement(
                        By.xpath("((//tbody //*[@class= 'main-grid-row main-grid-row-body'])[" + numberOfItem + "] //*[@class='main-grid-cell-content'])[" + count + "]"))
                .getText();
        Assert.assertEquals(nameOfThisItem, expectedName);
    }
    public void checkingTheTotalQuantityAndPrice(){
        Assert.assertEquals(driver.findElement(By.xpath("//*[@class='orders-templates__summary-description']")).getText(), "3");
        Assert.assertEquals(driver.findElement(By.xpath("(//*[@class='orders-templates__summary-description'])[2]")).getText(), "470 600 ₽");
    }
    public void checkingThatTotalPriceInTheCartIsEqualsOrderTemplate(){
        String expectedSum = "470 600 ₽";
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("#page-basket-total-block"), expectedSum));
        Assert.assertTrue(driver.findElement(By.cssSelector("#page-basket-total-block")).getText().contains(expectedSum));
    }
    public void checkingThatOrderTemplateIsDownloaded(){
            String downloadPath = System.getProperty("user.home") + "/Downloads/";
            if (isFileDownloaded_Ext(downloadPath, randomNameTemplate + ".xlsx")){
                Assert.assertTrue(isFileDownloaded_Ext(downloadPath, randomNameTemplate + ".xlsx"), "Failed to download document which has extension .xlsx");
            }else {
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!УДАЛИТЬ!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                implicitWaiting();implicitWaiting();implicitWaiting();implicitWaiting();implicitWaiting();
                checkingThatCatalogIsDownloaded(randomNameTemplate + ".xlsx");
            }
    }
    public void checkingThatNameByDefaultIsEqualsNameDownloadedFile(){
        String currentNameOrderTemplate = driver.findElement(By.xpath("//*[@name='TEMPLATE_NAME']")).getAttribute("value");
        System.out.println(currentNameOrderTemplate);
        Assert.assertTrue(currentNameOrderTemplate.contains(randomNameTemplate));
    }
    public void checkingDataOnTheDetailPage(){
        driver.findElement(By.cssSelector(".orders-templates__product-link")).click();
        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
        checkThatDetailPageOfProductIsOpened();
        driver.close();
        driver.switchTo().window(tabs2.get(0));
    }
    public void checkingThatOneOfItemIsUnavailable (){
        findingNumberOFColumnWithNeededName("Стоимость");
        checkingPriceOfItemOnTheOrderTemplateDetailPage("2", "Товарнедоступен");
    }
    public void checkingThatAtOneOfItemThereIsNoSuchQuantityAvailable(){
        Assert.assertTrue(driver.findElements(By.cssSelector(".unavailable-product")).size() == 3);
        Assert.assertEquals(driver.findElement(By.cssSelector(".unavailable-product")).getText(), "(1000)");
    }
    public void checkingThatWasAddedOnlyOneItemWithAvailableQuantity(){
        Assert.assertTrue(driver.findElements(By.cssSelector(".basket__product-discrioption")).size()==1);
        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(@id, 'basket-item-quantity')]")).getAttribute("value").equals("10"));
    }
    public void cancelingTemplateCreation(){
        String tempNamePageTitle = driver.findElement(By.cssSelector(".page-title")).getText();
        driver.findElement(By.xpath("//*[@onclick='showFormRemoveSave()']")).click();
        implicitWaiting();
        Assert.assertNotEquals(driver.findElement(By.cssSelector(".page-title")).getText(), tempNamePageTitle);
    }
    public void checkingMessageThatOrderTemplateIsNotSaved(){
        Assert.assertTrue(driver.findElement(By.cssSelector(".template-not-save")).getText().contains("(шаблон не сохранен)"));
    }
    public void checkingThatFirstOrderTemplateHasNotMarkThatItNotSaved(){
        Assert.assertTrue(driver.findElements(By.xpath("//*[@class='main-grid-row main-grid-row-body'][1]//*[@class='template-not-save']")).size()==0);
    }
    public void rememberingNameFirstTemplate(){
        randomNameTemplate = driver.findElement(By.xpath("(//*[@class='main-grid-row main-grid-row-body'] //*[@class='main-grid-cell-content'])[2]")).getText();
    }
    public void closingCatalogDownloadWindowPupOpWindow(){
        driver.findElement(By.xpath("//*[@id='modal-add-ordertemplate']//*[@class='modal-footer'] /*[contains(text(), 'Отмена')]")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#modal-add-ordertemplate")));
    }


}
