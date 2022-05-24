package TechnicalSupport;

import BaseActions.BaseActions;
import MyOrdersHistory.MethodsForMyOrders;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.io.File;

public class MethodsForTechnicalSupport extends BaseActions {
    MethodsForMyOrders orderHistory = new MethodsForMyOrders();
    int randomNumberOfCategory;
    int randomNumberOfCriticalityOfTreatment;
    int randomNumberOfEvaluationOfResponse;
    String randomMessage = "Описание что не работает: \n" + randomString(500);
    String heading = "Заголовок= " + randomString(20);
    String headingForCheck;

    public void openingPageOfCreateRequest(){
        driver.findElement(By.xpath("//*[contains(@href, 'support')][contains(@class, 'btn_b2b')]")).click();
        determineThemeColor();
        if (themeColorBlack){
            Assert.assertTrue(driver.findElement(By.xpath("//*[contains(@class, 'page-title')][contains(text(),'Новое обращение')]")).isDisplayed());
        }else {
            Assert.assertTrue(driver.findElement(By.xpath("//h4[contains(text(),'Новое обращение')]")).isDisplayed());
        }
    }
    public void fillingTheHeadForTechnicalSupport(){
        driver.findElement(By.cssSelector("#TITLE")).sendKeys(heading);
        headingForCheck = heading;
        System.out.println("Введенный заголовок - " +headingForCheck);
    }
    public void fillingFieldsRandomValuesForTechnicalSupport(){
        driver.findElement(By.xpath("//*[contains(@id,'CATEGORY')][contains(@id,'select')]")).click();
        randomNumberOfCategory = 1 + (int) (Math.random()
                * driver.findElements(By.xpath("//*[contains(@id, 'CATEGORY_ID-results')] /*")).size());
        try {
            driver.findElement(By.xpath("(//*[contains(@id, 'CATEGORY_ID-results')] /*)[" + randomNumberOfCategory + "]")).click();
        }catch (Exception e){
            System.out.println("Категория вопроса уже выбрана");
        }
        driver.findElement(By.cssSelector("#MESSAGE")).sendKeys(randomMessage);
        clickElement("//*[contains(@id, 'CRITICALITY')][contains(@id, 'select')]");
        randomNumberOfCriticalityOfTreatment = 1 + (int) (Math.random()
                * driver.findElements(By.xpath("//*[contains(@id, 'CRITICALITY_ID-results')] /*")).size());
        driver.findElement(By.xpath("(//*[contains(@id, 'CRITICALITY_ID-results')] /*)[" + randomNumberOfCriticalityOfTreatment + "]")).click();
        scrollToTheElement("//*[contains(text(), 'Оценка ответов')] /following::*[1]");
        clickElement("//*[contains(text(), 'Оценка ответов')] /following::*[1]");
        randomNumberOfEvaluationOfResponse = 1 + (int) (Math.random()
                * driver.findElements(By.xpath("//*[contains(@class, 'results__option')][contains(@id,'results')] /*")).size());
        clickElement("(//*[contains(@class, 'results__option')][contains(@id,'results')] /*)[" + randomNumberOfEvaluationOfResponse + "]");
    }
    public void sendingRequest(){
        clickElement("//*[@name='save']");
    }
    public void checkingThatRequestIsDisplayedForTheUser(){
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='" + heading + "']")).isDisplayed());
        determineThemeColor();
        if (themeColorBlack){
            Assert.assertTrue(driver.findElement(By.xpath("//*[contains(@class, 'page-title')][contains(text(), 'Список обращений')]")).isDisplayed());
        }else {
            Assert.assertTrue(driver.findElement(By.xpath("//h4[contains(text(), 'Список обращений')]")).isDisplayed());
        }
    }
    public void navigationToAdminPart(){
        driver.findElement(By.cssSelector("#bx-panel-admin-tab")).click();
    }
    public void navigationToSupportRequests(){
        driver.findElement(By.xpath("//*[@class='adm-main-menu-item-text'][text()='Сервисы']")).click();
        try {
            driver.findElement(By.xpath("//*[@class='adm-submenu-item-name-link-text'][text()='Обращения']")).click();
        }catch (Exception e){
            driver.findElement(By.xpath("//*[@class='adm-submenu-item-name-link-text'][text()='Техподдержка']")).click();
            driver.findElement(By.xpath("//*[@class='adm-submenu-item-name-link-text'][text()='Обращения']")).click();
        }
    }
    public void checkingThatRequestIsDisplayed(){
        //driver.findElement(By.xpath("//*[@class='adm-select']/*[@selected='selected']")).click();
        driver.findElement(By.xpath("//*[@class='adm-select']/*[@value='500']")).click();
        implicitWaiting();
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='" + headingForCheck + "']")).isDisplayed());
    }

    public void sortingRequestsById(){
        scrollToTheElement("//*[contains(@title,  'ID')]");
        try {
            driver.findElement(By.xpath("//*[@class='adm-list-table-cell-inner'][text()='ID']")).click();
        }catch (Exception e){}
        implicitWaiting();implicitWaiting();
        try {
            driver.findElement(By.xpath("//*[@title='Сортировка: ID  (упорядочено по возрастанию)']")).click();
        }catch (Exception e){
            System.out.println("уже отсортипровано по убыванию");
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@title='Сортировка: ID  (упорядочено по убыванию)']")));

    }
    public void openingLastRequestInAdminPart(){
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("(//*[@class='adm-list-table-cell'])[1]"), headingForCheck));
        driver.findElement(By.xpath("(//*[@class='adm-list-table-popup'][@title='Действия'])[1]")).click();
        driver.findElement(By.xpath("//*[@class='bx-core-popup-menu-item-text'][text()='Изменить']")).click();
    }
    public void downloadFile(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@title='Скачать файл \"palms.jpg\"']")));
        clickElement("//*[@title='Скачать файл \"palms.jpg\"']");
    }
    public void checkingThatFileIsDownload(){
        implicitWaiting();
        File[] files = dir.listFiles();
        Assert.assertTrue(files[1].getName().equals("palms.jpg"));
    }
    public void answeringToRequest(){
        scrollToTheElement("//*[@value='ответ']");
        scrollUp();
        driver.findElement(By.xpath("//*[@value='ответ']")).click();
        driver.findElement(By.cssSelector("#MESSAGE")).sendKeys("ОТВЕТ НА " + randomMessage);
    }
    public void sendingAnsweringToRequest(){
        driver.findElement(By.xpath("//*[@value='Сохранить']")).click();
    }
    public void sortingUserRequests(){
        driver.findElement(By.xpath("//*[@class='main-grid-head-title'][text()='Индикатор']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='main-grid-control-sort main-grid-control-sort-asc']")));
        driver.findElement(By.xpath("//*[@class='main-grid-head-title'][text()='Индикатор']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='main-grid-control-sort main-grid-control-sort-desc']")));
    }
    public void checkingThatTheResponseToTheLastRequestIsDisplayed(){
        flag = false;
        while (flag == false){
            try{
                Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), '" + headingForCheck + "')]")).isDisplayed());
                flag = true;
            }catch (Exception e){
                driver.findElement(By.xpath("//*[contains(@class, 'pagination-next')]")).click();
            }
        }
        sortingUserRequests();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("((//*[@class='main-grid-row main-grid-row-body'])[1])/*[4]"), headingForCheck));
        driver.findElement(By.xpath("(//*[@class='main-grid-row-action-button'])[1]")).click();
        driver.findElement(By.xpath("//*[@class='menu-popup-item-text']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(),'ОТВЕТ НА Описание что не работает:')]")).isDisplayed());
    }
    public void checkingThatTheLastRequestIsDisplayedOnTheTechnicalSupportPage(){
        Assert.assertTrue(driver.findElement(By.xpath("//*[@class='main-grid-row main-grid-row-body'] //*[contains(text(), '" + tempValue + "')]")).isDisplayed());
    }
    public void checkingThatLastRequestIsDisplayedOnTheDetailOrderPageInSupportTab(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@class='main-grid-row main-grid-row-body'])[1] /*[1]")));
        orderHistory.openingLastOrder();
        orderHistory.openSupportServiceTabInOrderPage();
        checkingDisplayingRequestByMessage();
    }
    public void checkingDisplayingRequestByMessage(){
        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), '" + randomMessage.substring(randomMessage.length() - 10) + "')]")).isDisplayed());
    }
    public void checkingThatURLContainsIDOfRequest(){
        Assert.assertTrue(driver.getCurrentUrl().contains(tempValue));
    }
    public void openingLastRequestOnTheTechnicalSupportPage(){
        driver.findElement(By.xpath("//*[contains(@class, 'action-button')]")).click();
        driver.findElement(By.xpath("//*[@class='menu-popup-item-text']")).click();
    }
    public void searchForNumberOfTheColumnResponsibleForTheID(){
        for (int i = 1; i < 5 ; i++) {
            if (driver.findElement(By.xpath("(//*[@class='main-grid-head-title'])[" + i +"]")).getText().equals("ID")){
                count = i;
                break;
            }
        }
        tempValue = driver.findElement(By.xpath("((//*[@class='main-grid-row main-grid-row-body'])[1] //*[@class='main-grid-cell main-grid-cell-left'])[" + count + "]")).getText();

    }
    public void memorizingNumberOfLastRequest(){
        System.out.println();
        searchForNumberOfTheColumnResponsibleForTheID();
        System.out.println("Номер последнего обращения в ТП первого пользователя - " + tempValue);
        System.out.println();
    }
    public void checkThatTheSecondUserDoesNotHaveTheFirstRequest (){
        System.out.println("Все номера обращений в ТП второго пользователя :");
        for (int i = 1; i <= driver.findElements(By.xpath("//*[@class='main-grid-row main-grid-row-body']")).size() ; i++) {
            System.out.println(driver.findElement(By.xpath("((//*[@class='main-grid-row main-grid-row-body'])[" + i + "] //*[@class='main-grid-cell main-grid-cell-left'])[" + count + "]"))
                    .getText());
            Assert.assertFalse(driver.findElement(By.xpath("((//*[@class='main-grid-row main-grid-row-body'])[" + i + "] //*[@class='main-grid-cell main-grid-cell-left'])[" + count + "]"))
                    .getText().equals(tempValue));
        }
    }
    public void checkingThatTheEnteringMessagesFieldIsRequired (){
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id='MESSAGE'][@required]")).isDisplayed());
    }

    public void checkingThatAnErrorMessageIsDisplayedIfTheHeaderIsEmpty(){
        driver.findElement(By.xpath("//*[@id='MESSAGE'][@required]")).sendKeys(randomMessage);
        sendingRequest();
        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(@class, 'errortext')][contains(text(), 'Заголовок')]")).isDisplayed());
    }
    public void checkingThatAnErrorMessageIsDisplayedIfTheTheDownloadedFileIsTooLarge(){
        driver.findElement(By.xpath("//*[@id='MESSAGE'][@required]")).sendKeys(randomMessage);
        fillingTheHeadForTechnicalSupport();
        downloadingALargeFile();
        sendingRequest();
        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(@class, 'errortext')][contains(text(), 'Возможно, превышен максимально допустимый размер файла')]")).isDisplayed());
    }
    public void fillingInOnlyRequiredFields (){
        driver.findElement(By.xpath("//*[@id='MESSAGE'][@required]")).sendKeys(randomMessage);
        fillingTheHeadForTechnicalSupport();
    }
    public void rememberingLastOrder() {
        try {
            tempValue = driver.findElement(By.xpath("//*[@class='main-grid-row main-grid-row-body'] //*[contains(@class, 'main-grid-cell-left')]")).getText();
        }catch (Exception e){
            navigationToCatalogTab();
            orderHistory.changeTheQuantityOfRandomProduct();
            navigationToCart();
            orderHistory.navigationToMakingOrderFromCart();
            orderHistory.trySelectCompany();
            orderHistory.makingOrder();
            navigationToMyOrdersPage();
            tempValue = driver.findElement(By.xpath("//*[@class='main-grid-row main-grid-row-body'] //*[contains(@class, 'main-grid-cell-left')]")).getText();
        }
    }


}
