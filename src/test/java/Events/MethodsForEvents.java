package Events;

import BaseActions.BaseActions;
import OrganizationsWithExtendedVersion.MethodsForAddingOrganizationsWithExtendedVersion;
import RegistrationAndAuthorization.MethodsForRegistrationAndAuthorization;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MethodsForEvents extends BaseActions {

    MethodsForAddingOrganizationsWithExtendedVersion org = new MethodsForAddingOrganizationsWithExtendedVersion();
    MethodsForRegistrationAndAuthorization registr = new MethodsForRegistrationAndAuthorization();
    int countColumnWithEventStatus;
    public void checkingEventAboutTheNeedToConfirmUserRegistration (){
        if (doNeedToConfirmRegistrationUser){
            System.out.println("Проверяю событие о рег. юзера с такие email   "  + registr.email);
            Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'SOTBIT_AUTH_CONFIRM_REGISTRATION')] /following::*[contains(text(), '" + registr.email + "')]"))
                    .isDisplayed());
        }
    }
    public void checkingEventAboutTheRegistrationOrganization(){
        if (doNeedToConfirmRegistrationOrganization){
            System.out.println("Проверяю событие о необходимости подтвердить орг. с таким ИНН   "  + registr.iNNManual);
            Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'SOTBIT_AUTH_COMPANY_MODERATION')] /following::*[contains(text(), '" + registr.iNNManual + "')]"))
                    .isDisplayed());
        }else {
            System.out.println("Проверяю событие что зарегистрировалась орг. с таким ИНН   "  + registr.iNNManual);
            Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'SOTBIT_AUTH_COMPANY_REGISTER')] /following::*[contains(text(), '" + registr.iNNManual + "')]"))
                    .isDisplayed());
        }
    }
    public void searchForAColumnWithEventStatuses(){
        for (int i = 1; i < driver.findElements(By.xpath("//*[@class='adm-list-table-header'] //td[contains(@class, 'adm-list-table-cell')]")).size() ; i++) {
            if (driver.findElement(By.xpath("(//*[@class='adm-list-table-header'] //td[contains(@class, 'adm-list-table-cell')])[" + i + "]"))
                    .getAttribute("title").contains("SUCCESS")){
                countColumnWithEventStatus = i;
                break;
            }
        }
    }
    public void checkingTheStatusOfTheUserRegistrationEvent(){
        if (doNeedToConfirmRegistrationUser){
            System.out.println("Проверяю статус события о рег. юзера (ожидаю '0') с такие email   "  + registr.email);
            for (int i = 1; i < 10; i++) {
                System.out.println(driver.findElement(By.xpath("(//*[@class='adm-list-table-row'])[" + i + "]")).getText());
                if (driver.findElement(By.xpath("(//*[@class='adm-list-table-row'])[" + i + "]")).getText().contains(registr.email)){
                    count = i;
                    System.out.println("№ события о необходимости подтвердить рег. юзера -   " + count);
                    break;
                }
            }
            Assert.assertTrue(driver.findElement(By.xpath("((//*[@class='adm-list-table-row'])[" + count + "] //*[contains(@class, 'adm-list-table-cell')])[" + countColumnWithEventStatus + "]"))
                    .getText().equals("0"));
        }
    }
    public void checkingTheStatusOfAnOrganizationRegistrationEvent(){
        System.out.println("Проверяю статус события о необходимости подтвердить рег. организации (ожидаю 'F') с таким email   "  + registr.iNNManual);
        for (int i = 1; i < 10; i++) {
            if (driver.findElement(By.xpath("(//*[@class='adm-list-table-row'])")).getText().contains(registr.iNNManual)){
                count = i;
                System.out.println("№ события о необходимости подтвердить рег. организации -   " + count);
                break;
            }
        }
        Assert.assertTrue(driver.findElement(By.xpath("((//*[@class='adm-list-table-row'])[" + count + "] //*[contains(@class, 'adm-list-table-cell')])[" + countColumnWithEventStatus + "]"))
                .getText().equals("F"));
    }
    public void searchForNeededColumnByUniqueAttribute (String uniqueAttribute ){
        for (int i = 1; i <= driver.findElements(By.xpath("//*[@class='adm-list-table-header'] //*[contains(@class, 'adm-list-table-cell adm-list-table-cell-sort')]")).size(); i++) {
            if (driver.findElement(By.xpath("(//*[@class='adm-list-table-header'] //*[contains(@class, 'adm-list-table-cell adm-list-table-cell-sort')])[" + i + "]"))
                    .getAttribute("onclick").contains(uniqueAttribute)){
                countColumnWithEventStatus = i;
                break;
            }
        }
    }
    public void searchForNeededColumnByUniqueTitle (String uniqueTitle){
        for (int i = 1; i <= driver.findElements(By.xpath("//*[@class='adm-list-table-header'] //*[contains(@class, 'adm-list-table-cell adm-list-table-cell-sort')]")).size(); i++) {
            if (driver.findElement(By.xpath("(//*[@class='adm-list-table-header'] //*[contains(@class, 'adm-list-table-cell adm-list-table-cell-sort')])[" + i + "]"))
                    .getAttribute("title").contains(uniqueTitle)){
                countColumnWithEventStatus = i;
                break;
            }
        }
    }
    public void checkingThatThisColumnThisCompanyHaveThisData(String dataForCompliance){
        Assert.assertTrue(driver.findElement(By.xpath("((//*[@class='adm-list-table-row'])[1] //*[contains(@class, 'adm-list-table-cell')])[" +countColumnWithEventStatus+"]"))
                .getText().contains(dataForCompliance));
    }
    public void checkingThatThisColumnThisCompanyHaveThisDate(String dateForCompliance){
        if (doNeedToConfirmRegistrationOrganization){
            Assert.assertTrue(driver.findElement(By.xpath("((//*[@class='adm-list-table-row'])[1] //*[contains(@class, 'adm-list-table-cell')])[" +countColumnWithEventStatus+"]"))
                    .getText().replaceAll("[.]", "").contains(dateForCompliance));
        }
    }
    public void confirmRegistrations(){
        registr.navigationToPageForConfirmUserRegistration();
        determineWhetherRegistrationUserNeedsToBeConfirmed();
//        if (doNeedToConfirmRegistrationUser){
//            registr.approveTheRegistrationOfTheLastUser();
//        }
//        determineWhetherRegistrationOrganizationNeedsToBeConfirmed();
//        if (doNeedToConfirmRegistrationOrganization){
//            approveTheRegistrationOfTheLastOrganization(registr.nameCompany);
//        }
    }
    public void navigationToThtListOfCompanies(){
        driver.findElement(By.xpath("//*[text()='Список компаний']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@id, 'company_result')]")));
    }
    public void openTableWithSotbitAuthStaff  (){
        driver.findElement(By.xpath("//*[@data-table-name='sotbit_auth_staff']")).click();
    }
    public void openLastPageSotbitAuthStaffTable(){
        driver.findElement(By.xpath("(//*[@class='adm-nav-page'])[last()]")).click();
    }
    public void checkingThatLastCreatedUserIsManager(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@class='adm-list-table-row'])[last()] /*[@class='adm-list-table-cell']")));
        waitingMilliSecond();
        tempValue = driver.findElement(By.xpath("(//*[@class='adm-list-table-row'])[last()] /*[@class='adm-list-table-cell']"))
                .getText().replaceAll("[^\\d.]", "");
        tempValue = tempValue.substring(tempValue.length() - 1);
        Assert.assertEquals("1", tempValue);
    }



}
