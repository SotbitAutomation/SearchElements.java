package TechnicalSupport;

import BaseActions.BaseActions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;

public class MethodsForTechnicalSupport extends BaseActions {
    int randomNumberOfCategory;
    int randomNumberOfCriticalityOfTreatment;
    int randomNumberOfEvaluationOfResponse;
    String randomMessage = "Описание что не работает: \n" + randomString(500);
    String heading = "Заголовок= " + randomString(20);
    String headingForCheck;

    public void openingPageOfCreateAppeal(){
        driver.findElement(By.cssSelector(".btn_create-appeal")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(@class,'page-title')]/*[text()='Новое обращение']")).isDisplayed());
    }
    public void fillingFieldsRandomValues(){
        driver.findElement(By.cssSelector("#TITLE")).sendKeys(heading);
        headingForCheck = heading;
        System.out.println(headingForCheck);
        driver.findElement(By.xpath("//*[contains(@id,'CATEGORY')][contains(@id,'select')]")).click();
        randomNumberOfCategory = 1 + (int) (Math.random()
                * driver.findElements(By.xpath("//*[contains(@id, 'CATEGORY_ID-results')] /*")).size());
        driver.findElement(By.xpath("(//*[contains(@id, 'CATEGORY_ID-results')] /*)[" + randomNumberOfCategory + "]")).click();
        driver.findElement(By.cssSelector("#MESSAGE")).sendKeys(randomMessage);

        driver.findElement(By.xpath("//*[contains(@id, 'CRITICALITY')][contains(@id, 'select')]")).click();
        randomNumberOfCriticalityOfTreatment = 1 + (int) (Math.random()
                * driver.findElements(By.xpath("//*[contains(@id, 'CRITICALITY_ID-results')] /*")).size());
        driver.findElement(By.xpath("(//*[contains(@id, 'CRITICALITY_ID-results')] /*)[" + randomNumberOfCriticalityOfTreatment + "]")).click();
        driver.findElement(By.xpath("//*[contains(text(), 'Оценка ответов')] /following::*[1] ")).click();
        randomNumberOfEvaluationOfResponse = 1 + (int) (Math.random()
                * driver.findElements(By.xpath("//*[contains(@class, 'results__option')][contains(@id,'results')] /*")).size());
        driver.findElement(By.xpath("(//*[contains(@class, 'results__option')][contains(@id,'results')] /*)[" + randomNumberOfEvaluationOfResponse + "]")).click();
    }
    public void sendingAppeal(){
        driver.findElement(By.xpath("//*[@name='save']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='" + heading + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(@class,'page-title')]/*[text()='Список обращений']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector(".support-list__title")).isDisplayed());
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
    public void checkingThatAppealIsDisplayed(){
        //driver.findElement(By.xpath("//*[@class='adm-select']/*[@selected='selected']")).click();
        driver.findElement(By.xpath("//*[@class='adm-select']/*[@value='500']")).click();
        explicitWaiting();
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='" + headingForCheck + "']")).isDisplayed());
    }

    public void sortingAppealsById(){
        scrollUp();
        try {
            driver.findElement(By.xpath("//*[@class='adm-list-table-cell-inner'][text()='ID']")).click();
        }catch (Exception e){}
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@title='Сортировка: ID  (упорядочено по возрастанию)']")));
        //explicitWaiting();
        if (driver.findElement(By.xpath("//*[@class='adm-list-table-cell adm-list-table-cell-sort-up']"))
                .getAttribute("title").equals("Сортировка: ID  (упорядочено по возрастанию)")){
            driver.findElement(By.xpath("//*[@class='adm-list-table-cell-inner'][text()='ID']")).click();
        }
    }
    public void openingLastAppealInAdminPart(){
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("(//*[@class='adm-list-table-cell'])[1]"), headingForCheck));
        driver.findElement(By.xpath("(//*[@class='adm-list-table-popup'][@title='Действия'])[1]")).click();
        driver.findElement(By.xpath("//*[@class='bx-core-popup-menu-item-text'][text()='Изменить']")).click();
    }
    public void downloadFile(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@title='Скачать файл \"palms.jpg\"']")));
        driver.findElement(By.xpath("//*[@title='Скачать файл \"palms.jpg\"']")).click();
    }
    public void checkingThatFileIsDownload(){
        explicitWaiting();
        File[] files = dir.listFiles();
        Assert.assertTrue(files[1].getName().equals("palms.jpg"));
    }
    public void answeringToAppeal(){
        driver.findElement(By.xpath("//*[@value='ответ']")).click();
        driver.findElement(By.cssSelector("#MESSAGE")).sendKeys("ОТВЕТ НА " + randomMessage);
    }
    public void sendingAnsweringToAppeal(){
        driver.findElement(By.xpath("//*[@value='Сохранить']")).click();
    }
    public void sortingUserAppeals(){
        driver.findElement(By.xpath("//*[@class='main-grid-head-title'][text()='Индикатор']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='main-grid-control-sort main-grid-control-sort-asc']")));
        driver.findElement(By.xpath("//*[@class='main-grid-head-title'][text()='Индикатор']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='main-grid-control-sort main-grid-control-sort-desc']")));
    }
    public void checkingThatTheResponseToTheLastAppealIsDisplayed(){
        flag = false;
        while (flag == false){
            try{
                Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), '" + headingForCheck + "')]")).isDisplayed());
                flag = true;
            }catch (Exception e){
                driver.findElement(By.xpath("//*[contains(@class, 'pagination-next')]")).click();
            }
        }
        sortingUserAppeals();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("((//*[@class='main-grid-row main-grid-row-body'])[1])/*[4]"), headingForCheck));
        driver.findElement(By.xpath("(//*[@class='main-grid-row-action-button'])[1]")).click();
        driver.findElement(By.xpath("//*[@class='menu-popup-item-text']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(),'ОТВЕТ НА Описание что не работает:')]")).isDisplayed());
    }


}
