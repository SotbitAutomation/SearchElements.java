package DangerousTests;

import Catalog.MethodsForCatalog;
import org.openqa.selenium.By;
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
        navigationToCatalogTab();
        if (driver.findElements(By.xpath("//*[contains(@class, 'icon-question')]")).size() > 0
                || driver.findElement(By.xpath("//*[@class='product__link']")).getText().contains("Тапочки")
                || driver.findElement(By.xpath("(//*[@class='item-quantity__general'])[3]")).getText().equals("0")) {
            returningSettingsBack();
        }
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
        tryTurnOffShowTheQuantityOfProductsInStorage();
        navigationToComponentOfCatalogSetting();
        choiceStandardCatalog();
        choiceMinPriceForOutputInCatalog();
        driver.navigate().refresh();
    }
    public void returningQuantitativeAccountingAtTheGefestGasStoveByDefault() {
        driver.findElement(By.xpath("//a[contains(text(), 'Плита GEFEST')]")).click();
        driver.findElement(By.xpath("//*[contains(text(), 'Торговый каталог')][contains(@class, 'adm-detail-tab')]")).click();
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
        driver.findElement(By.cssSelector(".adm-btn-save")).click();
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
}
