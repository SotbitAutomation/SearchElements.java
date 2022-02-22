package MakingOrders;

import Catalog.MethodsForCatalog;
import OrganizationsWithExtendedVersion.MethodsForAddingOrganizationsWithExtendedVersion;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MethodsForMakingOrders extends MethodsForCatalog {
     int randomNumberUpToDeliveryWays = 0;
     int randomNumberUpToNumberOfOrganizations = 0;
     public String numberOfOrder;
     public String nameDeliveryWay;
     public String paymentWay;
     public String iNNNumberCompanyWhichMadeOrder;
     public Double tempShippingCost;
     public String numberOfLastOrder = "1";
     public boolean isThisVersionTen = true;


    public void navigationToMakingOrderFromCart(){
        driver.findElement(buttonMakeOrderInTheCartLocator).click();
        try {
            Assert.assertTrue(driver.findElement(By.cssSelector("#ORDER_FORM")).isDisplayed());
        }catch (Exception e){
            Assert.assertTrue(driver.findElement(By.cssSelector(".validation-invalid-label.errortext")).isDisplayed());
            determineWhetherVersionsOfWorkingWithOrganization();
            MethodsForAddingOrganizationsWithExtendedVersion addOrg = new MethodsForAddingOrganizationsWithExtendedVersion();
            if (versionsOfWorkingWithOrganizationsExtended == true){
                navigationToOrganizationTab();
                addOrg.navigationToAddOrganizationTab();
                addOrg.selectionFromDropDownListIndividualBusinessman();
                addOrg.fillingFieldsForCreatingOrganization();
                nameCompany = addOrg.nameCompany;
                addOrg.creatingOrganization();
                tryConfirmRegistrationOfOrganizationInB2bFromTheUser();
                navigationToAuthorizationTab();
                fillingFieldsOnTheLogInTabLikeUser();
                logInToB2B();
                navigationToCart();
                driver.findElement(buttonMakeOrderInTheCartLocator).click();
                Assert.assertTrue(driver.findElement(By.cssSelector("#ORDER_FORM")).isDisplayed());
            }else {
                navigationToOrganizationTab();
                addOrg.navigationToAddOrganizationTab();
                addOrg.selectionFromDropDownListIndividualBusinessman();
                addOrg.fillingFieldsForCreatingOrganization();
                addOrg.creatingOrganization();
                navigationToCart();
                driver.findElement(buttonMakeOrderInTheCartLocator).click();
                Assert.assertTrue(driver.findElement(By.cssSelector("#ORDER_FORM")).isDisplayed());
            }
        }
    }
    public void trySelectCompany(){
        determineWhetherVersionsOfWorkingWithOrganization();
        if (versionsOfWorkingWithOrganizationsExtended == false){
            driver.findElement(By.xpath("(//input[@name='PROFILE_ID'])[1]")).click();
        }
    }
    public void selectCompanyByItSNumber(int companyNumber){
        determineWhetherVersionsOfWorkingWithOrganization();
        if (versionsOfWorkingWithOrganizationsExtended == false){
            driver.findElement(By.xpath("(//input[@name='PROFILE_ID'])[" + companyNumber + "]")).click();
            wait.until(ExpectedConditions.elementToBeSelected(By.xpath("(//input[@name='PROFILE_ID'])["+ companyNumber +"]")));
        }else {
            driver.findElement(By.cssSelector(".auth-company-change__current")).click();
            driver.findElement(By.xpath("(//*[@class='auth-company-change__item'])[" + companyNumber + "]")).click();
        }
        explicitWaiting();
    }

    public void makingOrder(){
        tempShippingCost = Double.valueOf(replacingSomeSymbols(driver.findElement(By.xpath("(//*[@class='text-center'])[11]")).getText()));
        driver.findElement(buttonForMakeOrderLocatorOnTheCheckoutPage).click();
//        try{
//            Assert.assertTrue(driver.findElement(By.cssSelector(".index_order_success")).isDisplayed());
//        }catch (Exception e){
//            driver.findElement(buttonForMakeOrderLocatorOnTheCheckoutPage).click();
//        }
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".index_order_success")));
        }catch (Exception e){
            driver.findElement(buttonForMakeOrderLocatorOnTheCheckoutPage).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".index_order_success")));
        }
        Assert.assertTrue(driver.findElement(By.cssSelector(".index_order_success")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'Ваш заказ')]")).isDisplayed());
    }
    public void checkingPriceOfProductsOnTheMakingOrderPage(){
        tempInt = 1;
        flag = false;
        while (flag == false){
            if (driver.findElement(By.xpath("//h4[text()='Заказ']/ following::*["+ tempInt+ "]")).getText().equals("Сумма")){
                flag = true;
            }else {
                tempInt++;
                if (tempInt >20) break;
            }
        }
        try {
            tempDouble2 = Double.valueOf(replacingSomeSymbols(driver.findElement(By.xpath("//h4[text()='Заказ']/ following::td[" + tempInt +"]"))
                    .getText()));
        }catch (Exception e){
            explicitWaiting();
            System.out.println("БЫЛА ТУПАЯ ОШИБКА");
            tempDouble2 = Double.valueOf(replacingSomeSymbols(driver.findElement(By.xpath("//h4[text()='Заказ']/ following::td[" + tempInt +"]"))
                    .getText()));
        }
        Assert.assertTrue(tempDouble2 == sumOfPricesOfTheAddedProducts);

        tempInt = 1;
        flag = false;
        while (flag == false){
            try {
                tempValue = driver.findElement(By.xpath("//h4[text()='Заказ']/ following::*["+ tempInt+ "]")).getText();
            }catch (Exception e){
                explicitWaiting();
                System.out.println("БЫЛА ТУПАЯ ОШИБКА");
                tempValue = driver.findElement(By.xpath("//h4[text()='Заказ']/ following::*["+ tempInt+ "]")).getText();
            }

            if (tempValue.equals("Доставка")){
                flag = true;
            }else {
                tempInt++;
                if (tempInt >30) break;
            }
        }
        sumOfPricesOfTheAddedProductsWithTheCostOfDelivery = sumOfPricesOfTheAddedProducts
                    + Double.valueOf(replacingSomeSymbols(driver.findElement(By.xpath("//h4[text()='Заказ']/ following::td[" + tempInt +"]")).getText()));
        explicitWaiting();
        Assert.assertTrue(Double.valueOf(replacingSomeSymbols(driver.findElement(By.xpath("//*[@class='index_checkout-total_title'] //*[@class='index_checkout-promocode-total_amount']"))
                .getText()))
                == sumOfPricesOfTheAddedProductsWithTheCostOfDelivery);
        explicitWaiting();
        System.out.println("Сумма добавленных товаров (без доставки) - " + sumOfPricesOfTheAddedProducts);
    }
    public void choicePickupAsADeliveryWay(){
        driver.findElement(By.xpath("//*[contains(text(), 'Самовывоз')]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(), 'Сохранить')]")));
    }
    public void choiceRandomDeliveryWay(){
        randomNumberUpToDeliveryWays = 1 + (int) (Math.random() * driver.findElements(By.xpath("//*[contains(@class, 'delivery')] //*[@class='uniform-choice']")).size());
        if (driver.findElement(By.xpath("(//*[contains(@class, 'delivery')]) /*[" + randomNumberUpToDeliveryWays + "] //*[@class='index_checkout-radios_title']"))
                .getText().equals("Самовывоз")){
            driver.findElement(By.xpath("(//*[contains(@class, 'delivery')] //*[@class='uniform-choice'])[" + randomNumberUpToDeliveryWays + "]")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(), 'Сохранить')]")));
            driver.findElement(By.xpath("//button[contains(text(), 'Сохранить')]")).click();

            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("((//*[contains(@class, 'delivery')] //*[@class='uniform-choice'])["
                    + randomNumberUpToDeliveryWays + "])/*[@class='checked']")));
        }else {
            driver.findElement(By.xpath("(//*[contains(@class, 'delivery')] //*[@class='uniform-choice'])[" + randomNumberUpToDeliveryWays + "]")).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("((//*[contains(@class, 'delivery')] //*[@class='uniform-choice'])["
                    + randomNumberUpToDeliveryWays + "])/*[@class='checked']")));
        }
        explicitWaiting();
        nameDeliveryWay = driver.findElement(By.xpath("(//*[@class='index_checkout-delivery_type'] //*[@class='index_checkout-radios_title'])["
                + randomNumberUpToDeliveryWays + "]")).getText();

        System.out.println("Номер выбранной рандомной доставки - " + randomNumberUpToDeliveryWays);
        tempString = replacingSomeSymbols(driver.findElement(By.xpath("(//*[@class='index_checkout-delivery_cost'])[" + randomNumberUpToDeliveryWays + "]")).getText());
        if (tempString.trim().isEmpty()){
            tempDouble=0;
        }else {
            tempDouble = Double.valueOf(tempString);
        }
        Assert.assertTrue(Double.valueOf(replacingSomeSymbols(driver.findElement(By.xpath("//h4[text()='Заказ']/ following::td[" + tempInt +"]")).getText())) == tempDouble);
    }
    public void choiceRandomPaymentMethod(){
        randomNumberUpToDeliveryWays = 1 + (int) (Math.random() * driver.findElements(By.xpath("(//*[@class='index_checkout-payment_system'] /*)")).size());
        driver.findElement(By.xpath("(//*[@class='index_checkout-payment_system'] //*[@class='form-input-styled'])[" + randomNumberUpToDeliveryWays + "]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("((//*[@class='index_checkout-payment_system'] //*[@class='form-input-styled'])["
                + randomNumberUpToDeliveryWays + "])[@checked='checked']")));
        explicitWaiting();

        paymentWay = driver.findElement(By.xpath("(//*[@class='index_checkout-payment_system'] //*[@class='index_checkout-radios_title'])["
                + randomNumberUpToDeliveryWays + "]")).getText();
    }
    public void checkingThatTheSelectedPaymentMethodIsDisplayed(){
        Assert.assertTrue(paymentWay.equals(driver.findElement(By.cssSelector(".index_checkout-radios_title")).getText()));
        takeNumberOfOrder();
    }
    public void takeNumberOfOrder(){
        numberOfOrder =replacingSomeSymbols(driver.findElement(By.xpath("//*[contains(@class, 'validation-valid-labe')] /b"))
                .getText());
    }
    public void rememberingPriceOfProductsInCart(){
        sumOfPricesOfTheAddedProducts = Double.valueOf(replacingSomeSymbols(driver.findElement(By.cssSelector(".basket-page__total-price-value"))
                .getText()));
    }
    public void changingCompanyInTheCartForExtendedVersion(){
        determineWhetherVersionsOfWorkingWithOrganization();
        iNNNumberCompanyWhichMadeOrder = driver.findElement(By.xpath("//*[contains(text(), 'ИНН')]/following::*[1]/input")).getAttribute("value");
        if (versionsOfWorkingWithOrganizationsExtended == true){
            iNNNumberCompanyWhichMadeOrder = driver.findElement(By.xpath("//*[contains(text(), 'ИНН')]/following::*[1]/input")).getAttribute("value");
            driver.findElement(By.cssSelector(".auth-company-change__current")).click();
            randomNumberUpToNumberOfOrganizations = 1 + (int) (Math.random() * driver.findElements(By.cssSelector(".auth-company-change__item")).size());
            driver.findElement(By.xpath("(//*[@class='auth-company-change__item'])[" + randomNumberUpToNumberOfOrganizations + "]")).click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("(//*[contains(text(), 'ИНН')]/following::*[1]/input)[@value='" + iNNNumberCompanyWhichMadeOrder + "']")));
            Assert.assertFalse(iNNNumberCompanyWhichMadeOrder.equals(driver.findElement(By.xpath("//*[contains(text(), 'ИНН')]/following::*[1]/input")).getAttribute("value")));
            iNNNumberCompanyWhichMadeOrder = driver.findElement(By.xpath("//*[contains(text(), 'ИНН')]/following::*[1]/input")).getAttribute("value");
        }
    }
    public void rememberingINNOfOrganizationOnThePageOfMakingOrder(){
        iNNNumberCompanyWhichMadeOrder = driver.findElement(By.xpath("//*[contains(text(), 'ИНН')]/following::*[1]/input")).getAttribute("value");
    }
    public void ifThereAreLessThanThreeOrganizationsThenCreateThreeOrganizations (){
        navigationToOrganizationTab();
        if (driver.findElements(By.cssSelector(".main-grid-row ")).size() <= 3){
            MethodsForAddingOrganizationsWithExtendedVersion org = new MethodsForAddingOrganizationsWithExtendedVersion();
            determineWhetherVersionsOfWorkingWithOrganization();
            if (versionsOfWorkingWithOrganizationsExtended == true){
                org.creatingThreeOrganizationForExtendedVersion();
            }else {
                org.creatingThreeOrganizationForStandardVersion();
            }
        }
    }
    public void rememberingFieldsThisOrganization(){
        tempValue = driver.findElement(By.xpath("//*[contains(text(), 'Название компании')] /following::*[2]")).getAttribute("value");
        tempValue1 = driver.findElement(By.xpath("//*[contains(text(), 'ИНН')] /following::*[2]")).getAttribute("value");
        tempValue2 = "12345@mail.ru";
        driver.findElement(By.xpath("//*[contains(text(), 'ail')] /following::*[2]")).clear();
        driver.findElement(By.xpath("//*[contains(text(), 'ail')] /following::*[2]")).sendKeys(tempValue2);
        tempValue3 = driver.findElement(By.xpath("//*[contains(text(), 'елефон')] /following::*[2]")).getAttribute("value");
        tempValue4 = driver.findElement(By.xpath("//*[contains(text(), 'ндекс')] /following::*[2]")).getAttribute("value");
        tempValue5 = driver.findElement(By.xpath("//*[contains(text(), 'доставк')] /following::*[2]")).getAttribute("value");
    }
    public void checkingThatTheFieldsOfThisOrganizationAreNotEqualToThePreviousOrganization  (){
        if (tempValue != null && !tempValue.trim().isEmpty()) {
            Assert.assertFalse(driver.findElement(By.xpath("//*[contains(text(), 'Название компании')] /following::*[2]")).getAttribute("value").equals(tempValue));
        }
        if (tempValue1 != null && !tempValue.trim().isEmpty()) {
            Assert.assertFalse(driver.findElement(By.xpath("//*[contains(text(), 'ИНН')] /following::*[2]")).getAttribute("value").equals(tempValue));
        }
        if (tempValue2 != null && !tempValue.trim().isEmpty()) {
            Assert.assertFalse(driver.findElement(By.xpath("//*[contains(text(), 'ail')] /following::*[2]")).getAttribute("value").equals(tempValue));
        }
        if (tempValue3 != null && !tempValue.trim().isEmpty()) {
            Assert.assertFalse(driver.findElement(By.xpath("//*[contains(text(), 'елефон')] /following::*[2]")).getAttribute("value").equals(tempValue));
        }
        if (tempValue4 != null && !tempValue.trim().isEmpty()) {
            Assert.assertFalse(driver.findElement(By.xpath("//*[contains(text(), 'ндекс')] /following::*[2]")).getAttribute("value").equals(tempValue));
        }
        if (tempValue5 != null && !tempValue.trim().isEmpty()) {
            Assert.assertFalse(driver.findElement(By.xpath("//*[contains(text(), 'доставк')] /following::*[2]")).getAttribute("value").equals(tempValue));
        }
    }
    public void tryToMakeAnOrderWithoutTheEmailField(){
        driver.findElement(By.xpath("//*[contains(text(), 'ail')] /following::*[2]")).clear();
        driver.findElement(buttonForMakeOrderLocatorOnTheCheckoutPage).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".errortext")));
        explicitWaiting();
        Assert.assertTrue("Предупреждающее сообщение не выводится", driver.findElement(By.cssSelector(".errortext")).isDisplayed());
        Assert.assertTrue("Предупреждающее сообщение 'какое-то Поле - обязательно для заполнения' не выводится", driver.findElement(By.xpath("//*[contains(text(), 'обязательно для заполнения')]")).isDisplayed());
        System.out.println(email);
        driver.findElement(By.xpath("//*[contains(text(), 'ail')] /following::*[2][@class='form-control']")).sendKeys(email);
    }
    public void tryToMakeAnOrderWithoutTheAddressField(){
        driver.findElement(By.xpath("//*[contains(text(), 'доставки')] /following::*[2]")).clear();
        driver.findElement(buttonForMakeOrderLocatorOnTheCheckoutPage).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".errortext")));
        explicitWaiting();
        Assert.assertTrue("Предупреждающее сообщение не выводится", driver.findElement(By.cssSelector(".errortext")).isDisplayed());
        Assert.assertTrue("Предупреждающее сообщение 'какое-то Поле - обязательно для заполнения' не выводится", driver.findElement(By.xpath("//*[contains(text(), 'обязательно для заполнения')]")).isDisplayed());
        System.out.println(address);
        driver.findElement(By.xpath("//*[contains(text(), 'доставки')] /following::*[2][@class='form-control']")).sendKeys(address);
    }
    int numberOfDeliveryMethodsInTheAdminPart =0;
    public void rememberTheNumberOfActiveDeliveryMethods (){
        for (int i = 1; i <= driver.findElements(By.xpath("//*[@class='main-grid-row main-grid-row-body']")).size(); i++) {

            if (driver.findElements(By.xpath("(//*[@class='main-grid-row main-grid-row-body'])[" + i + "] //*[@class='main-grid-cell-content'][text()='Да']")).size() > 0){
                driver.findElement(By.xpath("(//*[@class='adm-list-table-link'])[" + i + "]")).click();
                try {
                    driver.findElement(By.cssSelector("#tab_cont_edit_profiles")).click();
                    explicitWaiting();
                    tempInt2= driver.findElements(By.xpath("//*[@id='tbl_sale_delivery_subservice']//*[@class='adm-list-table-row']")).size();
                    numberOfDeliveryMethodsInTheAdminPart = numberOfDeliveryMethodsInTheAdminPart + tempInt2;
                }catch (Exception e){
                    numberOfDeliveryMethodsInTheAdminPart++;
                }
                navigationToDeliveryWayByUrl();
            }
        }
    }
    public void checkingThatTheNumberOfDeliveryMethodsIsEqualToTheNumberOfActiveInTheAdminPart(){
        Assert.assertTrue(driver.findElements(By.xpath("//*[contains(@for, 'DELIVERY')]")).size() == numberOfDeliveryMethodsInTheAdminPart);
    }
    public void cancellationOfThePickupDeliveryMethodInThePopUpWindowForSelectingAnAddressForPickup(){
        driver.findElement(By.xpath("//button[contains(text(), 'Отменить')]")).click();
        explicitWaiting();explicitWaiting();explicitWaiting();
        tempInt = 1;
        flag = false;
        while (flag == false){
            try {
                tempValue = driver.findElement(By.xpath("//h4[text()='Заказ']/ following::*["+ tempInt+ "]")).getText();
            }catch (Exception e){
                explicitWaiting();
                System.out.println("БЫЛА ТУПАЯ ОШИБКА");
                tempValue = driver.findElement(By.xpath("//h4[text()='Заказ']/ following::*["+ tempInt+ "]")).getText();
            }

            if (tempValue.equals("Доставка")){
                flag = true;
            }else {
                tempInt++;
                if (tempInt >30) break;
            }
        }
    }
    public void checkingThatPriceForPickupEqualsZero(){
        Assert.assertTrue(Double.valueOf(replacingSomeSymbols(driver.findElement(By.xpath("//h4[text()='Заказ']/ following::td[" + tempInt +"]")).getText())) == 0);
        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'Самовывоз')] /preceding::span[1][@class='checked']")).isDisplayed());
    }
    public void checkThePriceAtTheTopOfThePage(){
        showProductsInTheCartOnTheMakingOrderPage();
        Assert.assertTrue(Double.valueOf(replacingSomeSymbols(driver.findElement(By.xpath("(//*[@class='index_checkout-promocode-total_amount'])[1]")).getText())) ==
                sumOfPricesOfTheAddedProducts);
    }


}

