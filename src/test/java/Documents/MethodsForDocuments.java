package Documents;

import BaseActions.BaseActions;
import RegistrationAndAuthorization.RegistrationB2B;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MethodsForDocuments extends BaseActions {
    RegistrationB2B registr = new RegistrationB2B();
    //JavascriptExecutor jse = (JavascriptExecutor)driver;

    public void openDocumentsTabInAdminPanel(){
            driver.findElement(By.xpath(
                    "//*[contains(@class,'adm-submenu-title-content')][contains(text(),'Контент')] /following::*[1] //*[contains(text(),'Документы')] /.. //*[@class='adm-submenu-item-link-icon iblock_menu_icon_types']"))
                    .click();
    }
    public void addingExistingDocumentWithOrganizationToJustCreatedUser(){
        navigationToDocumentWhichWillBeAdded();
        fillingOrganizationField();
        goToPageForChoiceUser();
        choiceUserFromJustOpenedPage();
        driver.findElement(By.cssSelector(".adm-btn-save")).click();
    }
    public void addingExistingDocumentToJustCreatedUser(){
        navigationToDocumentWhichWillBeAdded();
        goToPageForChoiceUser();
        choiceUserFromJustOpenedPage();
        driver.findElement(By.cssSelector(".adm-btn-save")).click();
    }
    public void addingDocumentWithOrganizationToJustCreatedUser(){
        navigationToDocumentWhichWillBeAdded();
        fillingOrganizationField();
        goToPageForChoiceUser();
        choiceUserFromJustOpenedPage();
        uploadingExcelCatalog();
        driver.findElement(By.cssSelector(".adm-btn-save")).click();
    }
    public void addingDocumentToJustCreatedUser(){
        navigationToDocumentWhichWillBeAdded();
        goToPageForChoiceUser();
        choiceUserFromJustOpenedPage();
        uploadingExcelCatalog();
        driver.findElement(By.cssSelector(".adm-btn-save")).click();
    }
    public void uploadingExcelCatalog (){
        //jse.executeScript("window.scrollBy(0,250)", "");
        try {
            driver.findElement(By.cssSelector(".adm-btn-del")).click();
        }catch (Exception e){}
        By fileInput = By.cssSelector("input[type=file]");
        String filePath = System.getProperty("user.dir")  + "\\resources\\blank.xlsx";
        driver.findElement(fileInput).sendKeys(filePath);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@id,'file')][text()='blank.xlsx']")));
        driver.findElement(By.cssSelector("#save")).click();
        driver.findElement(By.xpath("//*[text()='Акт по заказу №2']")).click();

        try{
            driver.findElement(By.cssSelector(".container-doc-title")).getText();
        }catch (Exception e ){
            System.out.println("Нету названия, док не загрузился с первого раза");
            driver.findElement(fileInput).sendKeys(filePath);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@id,'file')][text()='blank.xlsx']")));
        }
    }
    public void navigationToDocumentWhichWillBeAdded(){
        navigationToAdminPartFromMeanPage();
        driver.findElement(By.xpath("//*[@class='adm-main-menu'] //*[contains(text(), 'Контент')]")).click();
        openDocumentsTabInAdminPanel();
        driver.findElement(By.xpath("//*[@id='menucontainer']//*[text()='Акты']")).click();
        driver.findElement(By.xpath("//*[text()='Акт по заказу №2']")).click();
    }
    public void fillingOrganizationField(){
        driver.findElement(By.xpath("//*[contains(text(), 'Организация')] /following::*[1] //input")).clear();
        driver.findElement(By.xpath("//*[contains(text(), 'Организация')] /following::*[1] //input")).sendKeys(registr.theSameInnManual);
    }
    public void goToPageForChoiceUser(){
        //driver.findElement(By.xpath("//*[contains(@id, 'SELECTPROP')]")).click();
        //driver.findElement(By.xpath("//*[contains(@id, 'SELECTPROP')] //*[text()='Выбрать']")).click();
        jse.executeScript("window.scrollBy(0,250)", "");
        driver.findElement(By.cssSelector(".tablebodybutton")).click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".tablebodybutton")));
//        try{
//            driver.findElement(By.cssSelector(".tablebodybutton")).click();
//        }catch (Exception e){
//            driver.findElement(By.cssSelector(".tablebodybutton")).click();
//        }
    }
    public void choiceUserFromJustOpenedPage(){
        Set<String> handles = driver.getWindowHandles();
        Iterator<String> itr = handles.iterator();
        String parentWindow = itr.next();
        String newWindow = itr.next();
        driver.switchTo().window(newWindow);
        driver.findElement(By.xpath("//*[text()='На странице:'] /following::*[1]")).click();
        driver.findElement(By.xpath("//*[text()='все']")).click();
        System.out.println(theSameEmail);
        driver.findElement(By.xpath("(//*[text()='" + registr.theSameEmail +  "'])[1]")).click();
        driver.switchTo().window(parentWindow);
    }


    public void navigationToActsOfDocuments(){
        driver.findElement(By.xpath("//*[text()='Акты']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(@class,'page-header')] //*[text()='Акты']")).isDisplayed());

    }
    public void checkingThatDocumentIsDisplayed(){
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Акт по заказу №2']")).isDisplayed());
    }
    public void downloadDocument(){
        driver.findElement(By.xpath("//*[text()='Акт по заказу №2']")).click();
        explicitWaiting();
        File dir = new File("C:\\Users\\SotBit\\Downloads");
        File[] files = dir.listFiles();
        Assert.assertTrue(files[1].getName().contains("Акт по заказу №2.xlsx"));
    }
    public void navigationToDetailInformationOfOrganizationFromDocument(){
        driver.findElement(By.xpath("(//*[@class='main-grid-cell main-grid-cell-left'])[last()]")).click();
        try {
            Assert.assertTrue(driver.findElement(By.cssSelector(".companies-wrapper")).isDisplayed());
        }catch (Exception e){
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!! На Деталку организации не перешло!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
    }

    public void confirmRegistrationOfOrganizationJustCreatedUser() {
        exitFromB2B();
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
        exitFromB2B();
        navigationToAuthorizationTab();
        registr.fillingFieldsOnTheLogInTab();
        logInToB2B();
        navigationToOrganizationTab();
        Assert.assertEquals("Одобрена", driver.findElement(By.xpath("(//*[@class='main-grid-row main-grid-row-body'])[1] /*[7]")).getText());
    }

    public  void deletingExcelFilesFromDownloads(){
        File dir = new File("C:\\Users\\SotBit\\Downloads");
        String [] str = {"xlsx"};
        Collection<File> files = FileUtils.listFiles(dir,str,true);
        for(File f:files){
            f.delete();
        }
    }


}
