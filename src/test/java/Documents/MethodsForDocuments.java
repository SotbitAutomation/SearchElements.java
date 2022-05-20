package Documents;

import BaseActions.BaseActions;
import Catalog.MethodsForCatalog;
import MakingOrders.MethodsForMakingOrders;
import MyOrdersHistory.MethodsForMyOrders;
import RegistrationAndAuthorization.RegistrationB2B;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MethodsForDocuments extends BaseActions {
    RegistrationB2B registr = new RegistrationB2B();
    MethodsForCatalog ordering = new MethodsForCatalog();
    MethodsForMakingOrders  makingOrder = new MethodsForMakingOrders();
    MethodsForMyOrders historyOrder = new MethodsForMyOrders();
    By buttonForSaveDocumentForUserLocator = By.cssSelector(".adm-btn-save");

    //JavascriptExecutor jse = (JavascriptExecutor)driver;

    public void openDocumentsTabInAdminPanel(){
        navigationToAdminPartFromMeanPage();
        driver.findElement(By.xpath("//*[@class='adm-main-menu'] //*[contains(text(), 'Контент')]")).click();
        driver.findElement(By.xpath(
                    "//*[contains(@class,'adm-submenu-title-content')][contains(text(),'Контент')] /following::*[1] //*[contains(text(),'Документы')] /.. //*[@class='adm-submenu-item-link-icon iblock_menu_icon_types']"))
                    .click();
    }
    public void addingExistingDocumentWithOrganizationToJustCreatedUser(){
        navigationToDocumentWhichWillBeAdded();
        fillingOrganizationField(registr.theSameInnManual);
        goToPageForChoiceUser();
        choiceUserFromJustOpenedPage(registr.email);
        driver.findElement(buttonForSaveDocumentForUserLocator).click();
    }
    public void addingExistingDocumentToJustCreatedUser(){
        navigationToDocumentWhichWillBeAdded();
        goToPageForChoiceUser();
        choiceUserFromJustOpenedPage(registr.theSameEmail);
        driver.findElement(buttonForSaveDocumentForUserLocator).click();
    }
    public void addingDocumentWithOrganizationToJustCreatedUser(String INNOrganization, String emailUserForDoc){
        navigationToDocumentWhichWillBeAdded();
        uploadingExcelCatalog("Акт по заказу №2");
        fillingOrganizationField(INNOrganization);
        goToPageForChoiceUser();
        choiceUserFromJustOpenedPage(emailUserForDoc);
    }

    public void addingDocumentForUserWithNumberOfOrder(String emailUserForDoc, String numberOfOrder){
        navigationToDocumentWhichWillBeAdded();
        driver.findElement(By.xpath("//*[contains(text(), 'Заказ')] /following::*[1] //input")).clear();
        driver.findElement(By.xpath("//*[contains(text(), 'Заказ')] /following::*[1] //input")).sendKeys(numberOfOrder);
        implicitWaiting();
        uploadingExcelCatalog("Акт по заказу №2");
        goToPageForChoiceUser();
        choiceUserFromJustOpenedPage(emailUserForDoc);
        driver.findElement(buttonForSaveDocumentForUserLocator).click();
    }
    public void addingDocumentToJustCreatedUser(){
        navigationToDocumentWhichWillBeAdded();
        goToPageForChoiceUser();
        choiceUserFromJustOpenedPage(registr.email);
        uploadingExcelCatalog("Акт по заказу №2");
        driver.findElement(buttonForSaveDocumentForUserLocator).click();
    }
    public void uploadingExcelCatalog (String nameDocument){
        //jse.executeScript("window.scrollBy(0,250)", "");
        try {
            driver.findElement(By.cssSelector(".adm-btn-del")).click();
        }catch (Exception e){}
        By fileInput = By.cssSelector("input[type=file]");
        String filePath = System.getProperty("user.dir")  + "\\resources\\blank.xlsx";
        driver.findElement(fileInput).sendKeys(filePath);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@id,'file')][text()='blank.xlsx']")));
        implicitWaiting();
        driver.findElement(buttonForSaveDocumentForUserLocator).click();
        driver.findElement(By.xpath("//*[text()='"+ nameDocument + "']")).click();

        try{
            driver.findElement(By.cssSelector(".container-doc-title")).getText();
        }catch (Exception e ){
            System.out.println("Нету названия, док не загрузился с первого раза");
            driver.findElement(fileInput).sendKeys(filePath);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@id,'file')][text()='blank.xlsx']")));
            implicitWaiting();
        }
    }
    public void navigationToDocumentWhichWillBeAdded(){
        openDocumentsTabInAdminPanel();
        driver.findElement(By.xpath("//*[@id='menucontainer']//*[text()='Акты']")).click();
        driver.findElement(By.xpath("//*[text()='Акт по заказу №2']")).click();
        implicitWaiting();
    }
    public void fillingOrganizationField(String INNOrganization){
        driver.findElement(By.xpath("//*[contains(text(), 'Организация')] /following::*[1] //input")).clear();
        driver.findElement(By.xpath("//*[contains(text(), 'Организация')] /following::*[1] //input")).sendKeys(INNOrganization);
    }
    public void goToPageForChoiceUser(){
        //driver.findElement(By.xpath("//*[contains(@id, 'SELECTPROP')]")).click();
        //driver.findElement(By.xpath("//*[contains(@id, 'SELECTPROP')] //*[text()='Выбрать']")).click();
        clickElementByItsCssSelector(".tablebodybutton");
    }
    public void choiceUserFromJustOpenedPage(String emailUserForDoc) {
        Set<String> handles = driver.getWindowHandles();
        Iterator<String> itr = handles.iterator();
        String parentWindow = itr.next();
        String newWindow = itr.next();
        driver.switchTo().window(newWindow);

        driver.findElement(By.xpath("//*[contains(@title, 'Введите')]")).clear();
        driver.findElement(By.xpath("//*[contains(@title, 'Введите')]")).sendKeys(emailUserForDoc);
        driver.findElement(By.xpath("//*[@type='submit']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[text()='" + emailUserForDoc +  "'])[1]")));
        implicitWaiting();

        if (driver.findElements(By.xpath("(//*[text()='" + emailUserForDoc +  "'])[1]")).size() == 0){
            driver.findElement(By.xpath("//*[text()='На странице:'] /following::*[1]")).click();
            driver.findElement(By.xpath("//*[text()='все']")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[text()='" + emailUserForDoc +  "'])[1]")));
            implicitWaiting();
        }

        //scrollToTheElement("(//*[text()='" + emailUserForDoc +  "'])[1]");
        System.out.println("Емаил пользователя для которого настраиваю документ  " + emailUserForDoc);
        try {
            driver.findElement(By.xpath("(//*[text()='" + emailUserForDoc +  "'])[1]")).click();
            implicitWaiting();
        }catch (Exception e){
            System.out.println("Тупая ошибка, не смог выбрать пользователя (хз почему)");
            driver.switchTo().window(parentWindow);
            implicitWaiting();
            try {
                exitFromB2B();
            }catch (Exception e1) {
                System.out.println("Я не смог сделать скриншот");
            }
        }
        implicitWaiting();
        driver.switchTo().window(parentWindow);
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//*[@class='tablebodybutton']/following::*[1]"), 1));
        implicitWaiting();implicitWaiting();
        System.out.println(driver.findElement(By.xpath("//*[@class='tablebodybutton']/following::*[1]")).getText());
        Assert.assertTrue(driver.findElement(By.xpath("//*[@class='tablebodybutton']/following::*[1]")).getText().contains(emailUserForDoc));
    }

    public void navigationToActsOfDocuments(){
        determineThemeColor();
        if (!themeColorBlack){
            expandTheNeededNavigationMenu("окументы");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Акты')]")));
        }
        clickElement("//*[contains(text(), 'Акты')]");
        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(@class,'page-header')] //*[text()='Акты']")).isDisplayed());
    }
    public void checkingThatDocumentIsDisplayed(){
        implicitWaiting();
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Акт по заказу №2']")).isDisplayed());
    }
    public void downloadDocument(){
        driver.findElement(By.xpath("//*[text()='Акт по заказу №2']")).click();
        implicitWaiting();
        checkingThatDocumentHasBeenDownloaded();
    }
    public void checkingThatDocumentHasBeenDownloaded(){
        implicitWaiting();
        File dir = new File("C:\\Users\\SotBit\\Downloads");
        File[] files = dir.listFiles();
        Assert.assertTrue(files[1].getName().contains("Акт по заказу №2.xlsx"), "Файл не скачался, или в папке 'Downloads' есть лишнии файлы ");
    }
    public void navigationToDetailInformationOfOrganizationFromDocument(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@class='main-grid-cell main-grid-cell-left'])[last()] //a[@href]")));
        clickElement("(//*[@class='main-grid-cell main-grid-cell-left'])[last()] //a[@href]");
        Assert.assertTrue(driver.findElement(By.cssSelector(".companies-wrapper")).isDisplayed());
//        try {
//            Assert.assertTrue(driver.findElement(By.cssSelector(".companies-wrapper")).isDisplayed());
//        }catch (Exception e){
//            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!! На Деталку организации не перешло!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//        }
    }

    public void confirmRegistrationOfOrganizationJustCreatedUser() {
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToAdminPartFromMeanPage();
        driver.findElement(By.cssSelector("#global_menu_sotbit")).click();
        driver.findElement(By.xpath("//*[text()='Подтверждение организации (расширенный режим)']")).click();
        driver.findElement(By.xpath("//*[@class='adm-designed-checkbox-label adm-checkbox']")).click();
        driver.findElement(By.cssSelector("#digitalwand_admin_helper_sotbit_auth_company_confirm_action")).click();
        driver.findElement(By.xpath("//*[text()='Одобрить']")).click();
        driver.findElement(By.xpath("//*[@value='Применить']")).click();
        driver.findElement(By.xpath("//*[@title='Перейти в режим просмотра сайта']")).click();
        navigationToAuthorizationTab();
        registr.fillingFieldsOnTheLogInTab();
        logInToB2B();
        navigationToOrganizationTab();
        Assert.assertEquals("Одобрена", driver.findElement(By.xpath("(//*[@class='main-grid-row main-grid-row-body'])[1] /*[7]")).getText());
    }

    public  void deletingExcelAndJpgFilesFromDownloads(){
        File dir = new File("C:\\Users\\SotBit\\Downloads");
        String [] str = {"xlsx"};
        Collection<File> files = FileUtils.listFiles(dir,str,true);
        for(File f:files){
            f.delete();
        }
    }
    public void makingAnOrderAndStoringNumberOfOrderForBindingADocument(){
        makingOrder.navigationToMakingOrderFromCart();
        makingOrder.trySelectCompany();
        makingOrder.makingOrder();
        makingOrder.takeNumberOfOrder();
    }

    public void checkingThatLastOrderHaveDocument(){
        historyOrder.openingLastOrder();
        historyOrder.navigationToDocumentsTabInOrderPage();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Акт по заказу №2']")));
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Акт по заказу №2']")).isDisplayed());
    }
    public void navigationToTheLastOrderFromDocumentsPage(){
        driver.findElement(By.xpath("//*[contains(@href, 'order')][contains(@href, 'detail')]")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector(".blank_detail")).isDisplayed());
    }
    public void searchSequenceNumberOfActs (){
        for (int i = 1; i < 5; i++) {
            if (driver.findElement(By.xpath("//*[@title='Forms']/preceding::*[text()='Документы'] /following::a[" + i + "]")).getAttribute("title").equals("Акты")){
                count=i;
                break;
            }
        }
    }
    public void openActSettings(){
        driver.findElement(By.xpath("//*[@class='adm-info-message'] /*[contains(@href, 'document')]")).click();
        driver.findElement(By.xpath("//*[@class='main-grid-row main-grid-row-body']//*[text()='Акты']")).click();
    }
    public void changeTheSequenceNumberForTheActsSectionToTheOpposite (){
        if (count == 1){
            driver.findElement(By.xpath("//*[@name='SORT']")).clear();
            driver.findElement(By.xpath("//*[@name='SORT']")).sendKeys("999");
            driver.findElement(buttonForSaveDocumentForUserLocator).click();
        }else {
            driver.findElement(By.xpath("//*[@name='SORT']")).clear();
            driver.findElement(By.xpath("//*[@name='SORT']")).sendKeys("10");
            driver.findElement(buttonForSaveDocumentForUserLocator).click();
        }
    }
    public void checkingThatTheSequenceNumberOfTheActsHasChanged(){
        for (int i = 1; i < 5; i++) {
            if (driver.findElement(By.xpath("//*[@title='Forms']/preceding::*[text()='Документы'] /following::a[" + i + "]")).getAttribute("title").equals("Акты")){
                Assert.assertFalse(count == i);
                break;
            }
        }
    }

}
