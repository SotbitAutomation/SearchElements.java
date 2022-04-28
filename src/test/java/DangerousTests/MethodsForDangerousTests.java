package DangerousTests;

import Catalog.MethodsForCatalog;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class MethodsForDangerousTests extends MethodsForCatalog {

    public void enterPartOfNameRandomCategoryInTheSearchFieldOnTheCatalogTabAndDeleteOne(By searchField, String wordForSearch){
        clickElement(searchField);
        enterPartOfNameInTheSearchFieldOnTheCatalogTab(searchField, wordForSearch);
        driver.findElement(searchField).sendKeys("\b");
    }

    public void choiceCategoryFromPopApWindow(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@class= 'media-body'] /* [@href] /*[contains(@class, 'media-title')])[last()]")));
        waitingMilliSecond();
        driver.findElement(By.xpath("(//*[@class= 'media-body'] /* [@href] /*[contains(@class, 'media-title')])[last()]")).click();
    }

    public void checkingThatURLContainsChosenCategory() {
        System.out.println(driver.getCurrentUrl());
        System.out.println(tempValue4);
        if (driver.findElements(By.xpath("//*[contains(@class, 'categorie')]")).size() > 0) {
            Assert.assertTrue(driver.getCurrentUrl().contains("SECTION_ID"));
        }else {
            Assert.assertTrue(driver.getCurrentUrl().contains("SECTION_ID=" + tempValue4));
        }
    }
    public void checkingThatBreadCrumbsContainTheNameOfTheSection() {
        if (driver.findElements(By.xpath("//*[contains(@class, 'categorie')]")).size() > 0) {
            System.out.println("Если меню в фильтре то крошки не предусмортрены");
        } else {
            Assert.assertTrue(driver.findElement(By.cssSelector(".breadcrumb-item.active")).getText().equals(tempString));
        }
    }

    public void checkingThatAllProductsHaveSimilarIdToTheSectionId (){
        if (tempValue4!=null){
            checkingThatAllProductsHaveASimilarIdToTheSectionId();
        }else {
            System.out.println("Не проверял  ID всех товаров, так как когда меню в фильтре нельзя узнать ID раздела");
        }
    }
    public void enableTheIndexSectionsForTheSearchModuleSetting (){
        driver.navigate().to(b2bUrl.replaceAll("b2bcabinet/", "") + "/bitrix/admin/iblock_edit.php?type=sotbit_b2bcabinet_type_catalog&lang=ru&ID=1&admin=Y&return_url=iblock_list_admin.php%3FIBLOCK_ID%3D1%26type%3Dsotbit_b2bcabinet_type_catalog%26lang%3Dru%26find_section_section%3D0");
        if (driver.findElements(By.xpath("//*[@id='INDEX_SECTION'][@checked]")).size()==0){
            clickElement("//*[@for='INDEX_SECTION'][contains(@class, 'label')]");
            driver.findElement(By.xpath("//*[@id='tabControl_buttons_div']//*[@name='save']")).click();
        }
    }
    public void checkingTheSmallOptPriceInPopUpSearchWindow(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".search-title-result-item-current-price")));
        implicitWaiting();
        String priceInPupOpSearchWindow = driver.findElement(By.cssSelector(".search-title-result-item-current-price")).getText();
        priceInPupOpSearchWindow = replacingSomeSymbols(priceInPupOpSearchWindow);
        try {
            Assert.assertTrue(tempDouble == Double.valueOf(priceInPupOpSearchWindow));
        }catch (Exception e){
            System.out.println(priceInPupOpSearchWindow);
            System.out.println(tempDouble);
            System.out.println(Double.valueOf(priceInPupOpSearchWindow));
            Assert.assertTrue(tempDouble == Double.valueOf(priceInPupOpSearchWindow));
        }
    }
    public void returningSettingsBackIfCatalogBroken() {
        driver.navigate().to(b2bUrl.replaceAll("b2bcabinet/", "") + "orders/blank_zakaza/");
        if (driver.findElements(By.xpath("//*[@title='Ремни']")).size() > 0) {
            returningSettingsBack();
        }else{
            clickElement("//*[@title='Компьютеры']");
            determineQuantityItemsEqualsZero();
            if (quantityItemsWithQuantityEqualsZero > 3) {
                returningSettingsBack();
            }
            }


//        if (driver.findElements(By.xpath("//*[contains(@class, 'icon-question')]")).size() > 0
//                || driver.findElement(By.xpath("//*[@class='product__link']")).getText().contains("Тапочки")
//                || driver.findElement(By.xpath("(//*[@class='item-quantity__general'])[3]")).getText().equals("0")) {
//            returningSettingsBack();
//        }
    }
    public void returningSettingsBack(){
        navigationToSystemSettings();
        navigationToSettingOfQuantitativeAccountingForTheProduct();
        enableQuantitativeAccountingForTheProductsInCatalog();
        navigationToGasStoveSetting();
        returningQuantitativeAccountingAtTheGefestGasStoveByDefault();
        returningTheKaiserGasStoveSettingByDefault();
        navigationToMeanPageByUrl();
        navigationToCatalogTab();
        turnOffShowTheQuantityOfProductsInStorageIfItIsShowed();
        choiceStandardCatalog();
        choiceMinPriceForOutputInCatalog();
        driver.navigate().refresh();
    }
    public void returningQuantitativeAccountingAtTheGefestGasStoveByDefault() {
        driver.findElement(By.xpath("//a[contains(text(), 'Плита GEFEST')]")).click();
        driver.findElement(By.xpath("//*[contains(text(), 'Торговый каталог')][contains(@class, 'adm-detail-tab')]")).click();
        clearAllOptPrices();
        driver.findElement(By.xpath("//*[@title='Дополнительные параметры']")).click();
        driver.findElement(By.xpath("//*[contains(@id, 'MEASURE_RATIO')]")).clear();
        driver.findElement(By.xpath("//*[contains(@id, 'MEASURE_RATIO')]")).sendKeys("1");
        try {
            driver.findElement(By.xpath("//*[@id='CAT_BASE_QUANTITY']")).clear();
        } catch (Exception e) {
            navigationToSystemSettings();
            navigationToSettingOfQuantitativeAccountingForTheProduct();
            scrollToTheElement("//*[@for='use_store_control_y'][@class]");
            try {
                driver.findElement(By.xpath("//*[@for='use_store_control_y'][@class]")).click();
            }catch (Exception e2){
                scrollUp();
                driver.findElement(By.xpath("//*[@for='use_store_control_y'][@class]")).click();
            }
            driver.findElement(By.xpath("//*[@value='Сохранить']")).click();
            navigationToGasStoveSetting();
            driver.findElement(By.xpath("//a[contains(text(), 'Плита GEFEST')]")).click();
            driver.findElement(By.xpath("//*[contains(text(), 'Торговый каталог')][contains(@class, 'adm-detail-tab')]")).click();
            driver.findElement(By.xpath("//*[@title='Дополнительные параметры']")).click();
            driver.findElement(By.xpath("//*[@id='CAT_BASE_QUANTITY']")).clear();
        }
        driver.findElement(By.xpath("//*[@id='CAT_BASE_QUANTITY']")).clear();
        driver.findElement(By.xpath("//*[@id='CAT_BASE_QUANTITY']")).sendKeys("5");
        driver.findElement(By.xpath("//*[contains(@id, 'BASE_QUANTITY_TRACE')]")).click();
        driver.findElement(By.xpath("//*[@id='CAT_BASE_QUANTITY_TRACE'] /option[@value='D']")).click();
        driver.findElement(By.xpath("//*[@title='Управление товаром на складах']")).click();
        driver.findElement(By.xpath("(//input[contains(@id, 'AMOUNT')])[1]")).clear();
        driver.findElement(By.xpath("(//input[contains(@id, 'AMOUNT')])[2]")).clear();
        driver.findElement(By.cssSelector(".adm-btn-save")).click();
    }
    public void clearAllOptPrices(){
        int quantityTypesOfPrices = driver.findElements(By.xpath("//*[contains(@onchange, 'ChangePrice')]")).size();
        for (int i = 1; i <= quantityTypesOfPrices; i++) {
            driver.findElement(By.xpath("(//*[contains(@onchange, 'ChangePrice')])["+ i + "]")).clear();
        }
    }

    public void returningTheKaiserGasStoveSettingByDefault() {
        driver.findElement(By.xpath("//a[contains(text(), 'Плита Kaiser')]")).click();
        driver.findElement(By.xpath("//*[contains(text(), 'Торговый каталог')][contains(@class, 'adm-detail-tab')]")).click();
        driver.findElement(By.xpath("//*[@title='Дополнительные параметры']")).click();
        driver.findElement(By.xpath("//*[contains(@id, 'MEASURE_RATIO')]")).clear();
        driver.findElement(By.xpath("//*[contains(@id, 'MEASURE_RATIO')]")).sendKeys("1");
        driver.findElement(By.cssSelector(".adm-btn-save")).click();
    }

    public void turnOffQuantitativeAccountingAtTheGefestGasStove() {
        driver.findElement(By.xpath("//a[contains(text(), 'Плита GEFEST')]")).click();
        driver.findElement(By.xpath("//*[contains(text(), 'Торговый каталог')][contains(@class, 'adm-detail-tab')]")).click();
        driver.findElement(By.xpath("//*[@title='Дополнительные параметры']")).click();
        driver.findElement(By.xpath("//*[contains(@id, 'BASE_QUANTITY_TRACE')]")).click();
        driver.findElement(By.xpath("//*[@id='CAT_BASE_QUANTITY_TRACE'] /option[@value='N']")).click();
        driver.findElement(By.cssSelector(".adm-btn-save")).click();
    }
    public void setTheNumberOfGasStoveGefestEqualToZero(){
        driver.findElement(By.xpath("//a[contains(text(), 'Плита GEFEST')]")).click();
        driver.findElement(By.xpath("//*[contains(text(), 'Торговый каталог')][contains(@class, 'adm-detail-tab')]")).click();
        driver.findElement(By.xpath("//*[@title='Дополнительные параметры']")).click();
        driver.findElement(By.cssSelector("#CAT_BASE_QUANTITY")).clear();
        driver.findElement(By.cssSelector("#CAT_BASE_QUANTITY")).sendKeys("0");
        driver.findElement(By.cssSelector(".adm-btn-save")).click();
    }
    public void turnOffStoreAccounting(){
        if (driver.findElements(By.xpath("//*[@id='use_store_control_y'][@checked]")).size()>0){
            driver.findElement(By.xpath("//*[@id='use_store_control_y']/following::*[1]")).click();
        }
        clickElement("//*[@value='Сохранить']");
    }
    public void turnOnStoreAccounting(){
        if (driver.findElements(By.xpath("//*[@id='use_store_control_y'][@checked]")).size()==0){
            clickElement("//*[@id='use_store_control_y']/following::*[1]");
        }
        clickElement("//*[@value='Сохранить']");
    }
    public void checkingThatThereIsAWordFrom(){
        Assert.assertTrue(driver.findElement(By.cssSelector(".search-title-result-item-current-price")).getText().contains("от"));
    }
    public void checkingThatThereArePricesWithAndWithoutDiscount (){
        // названия цен (обычн и опт)
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Розничная цена']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='SMALL_OPT']")).isDisplayed());
        // базовые цены (обычн и опт)
        Assert.assertTrue(driver.findElement(By.xpath("//*[@class='bzd-prices__item-value'][contains(@id, 'price_BASE')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//*[@class='bzd-prices__item-value'][contains(@id, 'SMALL_OPT')]")).isDisplayed());
        // цены с скидкой (обычн и опт)
        Assert.assertTrue(driver.findElement(By.xpath("//*[@class='product__property--discount-price'][contains(@id, 'price_BASE')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//*[@class='product__property--discount-price'][contains(@id, 'SMALL_OPT')]")).isDisplayed());
        navigationToMeanPageByUrl();
    }
    public void checkingThatThereAreThisQuantityItemsInTheBasket(int expectedQuantity){
        Assert.assertEquals(driver.findElements(By.cssSelector(".basket__item")).size(), expectedQuantity);
    }

    int quantityItemsWithQuantityEqualsZero;
    public void showTheQuantityOfProductsInStorageIfItIsNotShowed() {
        determineQuantityItemsEqualsZero();
        if (quantityItemsWithQuantityEqualsZero < 2) {
            navigationToComponentOfCatalogSetting();
            showingTheQuantityOfProductsInStorage();
        }
    }
    public void determineQuantityItemsEqualsZero(){
        ternOffEditMode();
        quantityItemsWithQuantityEqualsZero = 0;
        numberOfProductsPerPage = driver.findElements(By.cssSelector(".item-quantity__general")).size();
        for (int i = 1; i <= numberOfProductsPerPage; i++) {
            if (driver.findElement(By.xpath("(//*[@class='item-quantity__general'])[" + i + "]")).getText().equals("0")){
                quantityItemsWithQuantityEqualsZero++;
            }
        }
    }
    public void turnOffShowTheQuantityOfProductsInStorageIfItIsShowed(){
        determineQuantityItemsEqualsZero();
        if (quantityItemsWithQuantityEqualsZero > 3) {
            ternOnEditMode();
            navigationToComponentOfCatalogSetting();
            showingTheQuantityOfProductsInStorage();
        }
    }
    int quantityItemsInTheTESTStorage;
    public void addingProductsToTheStorages() {
        driver.findElement(By.xpath("//a[contains(text(), 'Плита GEFEST')]")).click();
        driver.findElement(By.xpath("//*[contains(text(), 'Торговый каталог')][contains(@class, 'adm-detail-tab')]")).click();
        driver.findElement(By.xpath("//*[@title='Управление товаром на складах']")).click();
        try {
            driver.findElement(By.xpath("//input[contains(@id, 'AMOUNT')]")).clear();
        } catch (Exception e) {
            driver.findElement(By.xpath("//*[@id='bx-search-box'] /input")).sendKeys("склады");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".adm-search-result")));
            driver.findElement(By.cssSelector(".adm-search-result")).click();
            driver.findElement(By.xpath("//*[contains(@for, 'store_control')][contains(@class, 'checkbox')]")).click();
            driver.findElement(By.xpath("//*[@value='Сохранить']")).click();
            navigationToGasStoveSetting();
            driver.findElement(By.xpath("//a[contains(text(), 'Плита GEFEST')]")).click();
            driver.findElement(By.xpath("//*[contains(text(), 'Торговый каталог')][contains(@class, 'adm-detail-tab')]")).click();
            driver.findElement(By.xpath("//*[@title='Управление товаром на складах']")).click();
            driver.findElement(By.xpath("//input[contains(@id, 'AMOUNT')]")).clear();
        }
        tempValue = randomNumberWithoutZero(4);
        driver.findElement(By.xpath("(//input[contains(@id, 'AMOUNT')])[1]")).clear();
        driver.findElement(By.xpath("(//input[contains(@id, 'AMOUNT')])[1]")).sendKeys(tempValue);
        tempValue1 = randomNumberWithoutZero(4);
        driver.findElement(By.xpath("(//input[contains(@id, 'AMOUNT')])[2]")).clear();
        driver.findElement(By.xpath("(//input[contains(@id, 'AMOUNT')])[2]")).sendKeys(tempValue1);
        driver.findElement(By.xpath("//*[@title='Дополнительные параметры']")).click();
        driver.findElement(By.xpath("//*[@id='CAT_BASE_QUANTITY']")).clear();
        tempValue2 = String.valueOf(Integer.parseInt(tempValue1) + Integer.parseInt(tempValue));
        driver.findElement(By.xpath("//*[@id='CAT_BASE_QUANTITY']")).sendKeys(tempValue2);
        quantityItemsInTheTESTStorage = Integer.valueOf(driver.findElement(By.xpath("//*[text()='TEST'] /following::*[contains(@id, 'AMOUNT')]")).getAttribute("value"));
        driver.findElement(By.xpath("//*[@name='save']")).click();
        System.out.println("Кол-ва gefest на складе 'TEST' -  " + quantityItemsInTheTESTStorage);
    }
    public void checkingThatTheTotalNumberOfOutputProductsAndQuantityStoragesIsEqualToThePreviouslyEnteredData() {
        Actions action = new Actions(driver);
        driver.findElement(By.xpath("(//*[@class='quantity-selector__value'])[" + count + "]")).clear();
        driver.findElement(By.xpath("(//*[@class='quantity-selector__value'])[" + count + "]")).sendKeys("0");
        tempValue3 = driver.findElement(By.xpath("(//*[@class='item-quantity__general'])[" + count + "]")).getText();
        Assert.assertTrue((Integer.parseInt(tempValue) + Integer.parseInt(tempValue1)) == Integer.parseInt(tempValue3));
        System.out.println(count);
        action.moveToElement(driver.findElement(By.xpath("(//*[@class='item-quantity__general'])[" + count + "]")));
        action.perform();
        Assert.assertTrue(driver.findElements(By.xpath("//*[@class='item-quantity__store-name'][contains(text(), 'TEST')]")).size() > 0, "Не отображается склад с названием 'TEST'");
        System.out.println("Отображаемое кол-во товаров на складе №1   " + driver.findElement(By.xpath("(//*[@class='item-quantity__store-quantity'])[1]")).getText());
        System.out.println("Отображаемое кол-во товаров на складе №2   " + driver.findElement(By.xpath("(//*[@class='item-quantity__store-quantity'])[2]")).getText());
        System.out.println("Кол-во товара на складе №1 которое я вводил   " + tempValue1);
        System.out.println("Кол-во товара на складе №2 которое я вводил   " + tempValue);
        flag = false;
        for (int i = 1; i <= 2; i++) {
            if (driver.findElement(By.xpath("(//*[@class='item-quantity__store-quantity'])[" + i + "]")).getText().trim().equals(tempValue1.trim())) {
                flag = true;
            }
        }
        Assert.assertTrue(flag == true);
        flag = false;
        for (int i = 1; i <= 2; i++) {
            if (driver.findElement(By.xpath("(//*[@class='item-quantity__store-quantity'])[" + i + "]")).getText().trim().equals(tempValue.trim())) {
                flag = true;
            }
        }
        Assert.assertTrue(flag == true);
        implicitWaiting();
        implicitWaiting();
    }
    public void checkingThatTheTotalNumberOfOutputProductsIsEqualToThePreviouslyEnteredDataInTestStorage() {
        System.out.println("УДАЛИТЬ  " + count);
        Actions action = new Actions(driver);
        driver.findElement(By.xpath("(//*[@class='quantity-selector__value'])[" + count + "]")).clear();
        waitingMilliSecond();
        driver.findElement(By.xpath("(//*[@class='quantity-selector__value'])[" + count + "]")).sendKeys("0");
        tempValue3 = driver.findElement(By.xpath("(//*[@class='item-quantity__general'])[" + count + "]")).getText();
        Assert.assertTrue(quantityItemsInTheTESTStorage == Integer.parseInt(tempValue3));
        System.out.println(count);
        action.moveToElement(driver.findElement(By.xpath("(//*[@class='item-quantity__general'])[" + count + "]")));
        action.perform();
        Assert.assertTrue(driver.findElements(By.xpath("//*[@class='item-quantity__store-name'][contains(text(), 'TEST')]")).size() > 0, "Не отображается склад с названием 'TEST'");
        System.out.println("Отображаемое кол-во товаров на складе №1   " + driver.findElement(By.xpath("(//*[@class='item-quantity__store-quantity'])[1]")).getText());
        System.out.println("Кол-во товара на складе №1 которое я вводил   " + quantityItemsInTheTESTStorage);
        Assert.assertTrue(driver.findElements(By.cssSelector(".item-quantity__store-item")).size() == 1);
        Assert.assertTrue(Integer.parseInt( driver.findElement(By.cssSelector(".item-quantity__store-item")).getText().replaceAll("[^0-9]", "")) == quantityItemsInTheTESTStorage);
    }
    public void attemptToAddMoreItemsThanIsAtTheStorage(){
        driver.findElement(By.xpath("(//*[@class='quantity-selector__value'])[" + count + "]")).sendKeys(String.valueOf(quantityItemsInTheTESTStorage));
        driver.findElement(By.xpath("(//*[@class='quantity-selector__increment'])[" + count + "]")).click();
        implicitWaiting();
        Assert.assertTrue(Integer.parseInt(driver.findElement(By.xpath("(//*[@class='quantity-selector__value'])[" + count + "]")).getAttribute("value")) == quantityItemsInTheTESTStorage);

    }
}
