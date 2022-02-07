package MeanPage;

import BaseActions.BaseActions;
import MakingOrders.MethodsForMakingOrders;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MethodsForMeanPage extends BaseActions {

    MethodsForMakingOrders makeOrder = new MethodsForMakingOrders();
    // локаторы для главной страницы
    By randomColumnWhereToMoveLocator;
    By calendarIconLocator = By.cssSelector(".icon-calendar2.mr-2");
    By settingIconLocator = By.cssSelector(".icon-cog.mr-2");
    By buttonOfHomeLocator = By.xpath("//*[@title='Главная']");
    By buttonForResetTheCurrentSettings = By.xpath("//*[text()='Сбросить текущие настройки']");
    By leftColumnLocator = By.xpath("//*[@id='s0']");
    By middleColumnLocator = By.xpath("//*[@id='s1']");
    By rightColumnLocator = By.xpath("//*[@id='s2']");
    By firsWidgetInLeftColumnLocator = By.xpath("(//*[@id='s0'] //*[@class='card-title'])[1]");
    By secondWidgetInMiddleColumnLocator = By.xpath("(//*[@id='s1'] //*[@class='card-title'])[2]");

    By firsWidgetInRightColumnLocator = By.xpath("(//*[@id='s2'] //*[@class='card-title'])[1]");
    By desktopIconLocator = By.cssSelector(".icon-menu7.mr-2");
    String tempValueOfTitle;
    String tempValueOfId;
    String tempLocationOfWidgetsOnTheDesktop;
    String dataFromTheWidget;
    String dataFromTheSetting;
    int tempNumber;
    int sumOfProductsInCart = 0;
    int i = 0;
    int columnNumberWhereToMove;

    WebElement tempLocator;
    By tempLocatorForSearchElementByTextAndColumn;
    By addWidgetButtonLocator = By.xpath("//*[contains(text(), 'Добавить виджет')]");
    By saveSettingLikeDefaultLocator = By.xpath("//*[contains(text(), 'Сохранить как настройки по умолчанию')]");
    By allWidgetsOnTheDesktop = By.cssSelector(".sotbit-cabinet-gadget");
    By buttonForSavingTheMainSettings =  By.xpath("(//*[contains(@name, 'save')])[1]");
    By buttonForSavingThePersonalData =  By.xpath("(//*[contains(@name, 'save')])[2]");
    By buttonPrivacyPolicyForMainSettings = By.xpath("(//*[contains(text(), 'политикой конфиденциальности.')])[1]");


    public void movingARandomWidget(int columnNumber ){
        tempRandomNumber = 1 + (int) (Math.random() * driver.findElements(By.xpath("(//*[contains(@class, 'gd-page-column')])[" + columnNumber + "] //*[contains(@class, 'sotbit-cabinet-gadget')]")).size());
        System.out.println("Рандомный номер виджета в колонке № " + columnNumber + "  =  " + tempRandomNumber);
        By randomWidgetLocator = By.xpath("((//*[contains(@class, 'gd-page-column')])[" + columnNumber + "] //*[contains(@class, 'sotbit-cabinet-gadget')])[" + tempRandomNumber + "] //*[@class='card-title']");
        tempValueOfTitle = driver.findElement(randomWidgetLocator).getText();
        System.out.println("Название виджета который перемещаю - " + tempValueOfTitle);
        actions.moveToElement(driver.findElement(randomWidgetLocator));
        actions.clickAndHold();

        if (columnNumber != 3){
            columnNumberWhereToMove = columnNumber+1;
        }else  {
            columnNumberWhereToMove = 1;
        }
        randomColumnWhereToMoveLocator = By.xpath("(//*[contains(@class, 'gd-page-column')])[" + columnNumberWhereToMove +  "]");

        actions.moveToElement(driver.findElement(randomColumnWhereToMoveLocator));
        actions.release().perform();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("(//*[contains(@class, 'gd-page-column')])[" + columnNumber + "] //*[@class='card-title'][text()='" + tempValueOfTitle + "']")));
        driver.navigate().refresh();
        Assert.assertTrue("Виджет отображается в колонке из которой я его перемещал"
                ,driver.findElements(By.xpath("(//*[contains(@class, 'gd-page-column')])[" + columnNumber + "] //*[@class='card-title'][text()='" + tempValueOfTitle + "']")).size()==0);

        Assert.assertTrue("Перемещенный виджет не отображается в колонке в которую я его переместил"
                , driver.findElement(By.xpath("(//*[contains(@class, 'gd-page-column')])[" + columnNumberWhereToMove + "] //*[@class='card-title'][text()='" + tempValueOfTitle + "']")).isDisplayed());
    }

    public void addingRandomWidgetToTheDesktop(){
        driver.findElement(addWidgetButtonLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".widgets_cabinet")));
        tempRandomNumber = 1 + (int) (Math.random() * driver.findElements(By.xpath("(//div[@class='widgets_cabinet_title'])")).size());
        tempValueOfTitle = driver.findElement(By.xpath("(//div[@class='widgets_cabinet_title'])" + "[" + tempRandomNumber + "]")).getText();
        System.out.println("Название добавленного виджета  - " + tempValueOfTitle);
        driver.findElement(By.xpath("(//div[@class='widgets_cabinet_title'])" + "[" + tempRandomNumber + "]")).click();
        explicitWaiting();
        tempLocatorForSearchElementByTextAndColumn = By.xpath("//*[text()='" + tempValueOfTitle + "']");
        Assert.assertTrue("Добавленный виджет не отображается", driver.findElement(tempLocatorForSearchElementByTextAndColumn).isDisplayed());
        explicitWaiting();
        driver.navigate().refresh();
        explicitWaiting();
        Assert.assertTrue("Добавленный виджет не отображается", driver.findElement(tempLocatorForSearchElementByTextAndColumn).isDisplayed());
    }

    public void deletionJustAddedWidget(){
        tempValueOfId = driver.findElement(By.xpath("(//*[contains(@class, 'data-table-gadget')])[1]")).getAttribute("id");
        driver.findElement(By.xpath("(//*[@data-action='remove'])[1]")).click();
        explicitWaiting();
        Assert.assertTrue(driver.findElements(By.xpath("//*[@id='" + tempValueOfId + "']")).size() == 0);
        explicitWaiting();
        driver.navigate().refresh();
        explicitWaiting();
        Assert.assertTrue(driver.findElements(By.xpath("//*[@id='" + tempValueOfId + "']")).size() == 0);
    }

    public void deletionRandomWidgetFromDesktop(){
        tempRandomNumber = 1 + (int) (Math.random() * driver.findElements(By.xpath("//*[contains(@class, 'sotbit-cabinet-gadget')]//*[@class='card-title']")).size());
        By randomWidgetInDesktopLocator = By.xpath("(//*[contains(@class, 'sotbit-cabinet-gadget')])" + "[" + tempRandomNumber + "] //*[@data-action = 'remove']");
        tempValueOfId = driver.findElement(randomWidgetInDesktopLocator).getAttribute("id");
        driver.findElement(randomWidgetInDesktopLocator).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='" + tempValueOfId + "']")));
        Assert.assertTrue(driver.findElements(By.xpath("//*[@id='" + tempValueOfId + "']")).size() == 0);
        driver.navigate().refresh();
        Assert.assertTrue(driver.findElements(By.xpath("//*[@id='" + tempValueOfId + "']")).size() == 0);
    }

    public void resettingTheCurrentWidgetSettings (){
        driver.findElement(buttonForResetTheCurrentSettings).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        explicitWaiting();
    }

    public void storingTheLocationOfWidgetsOnTheDesktop(){
        tempLocationOfWidgetsOnTheDesktop="";
        for (int i = 1; i <= driver.findElements(By.xpath("//*[contains(@class, 'sotbit-cabinet-gadget')]//*[@class='card-title']")).size(); i++) {
            tempLocationOfWidgetsOnTheDesktop = tempLocationOfWidgetsOnTheDesktop + driver.findElement(By.xpath("(//*[@class='card-title'])[" + i + "]")).getText();
        }
    }

    public void saveTheLocationOfWidgetsOnTheDesktopLikeDefault(){
            driver.findElement(saveSettingLikeDefaultLocator).click();
            new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());  //сохраннеие настроек по умолчанию метод
            driver.switchTo().alert().accept();
    }

    public void checkingTgeReturnOfSettingsToDefault(){
        tempValue="";
        for (int i = 1; i <= driver.findElements(By.xpath("//*[contains(@class, 'sotbit-cabinet-gadget')]//*[@class='card-title']")).size(); i++) {
            System.out.println(driver.findElement(By.xpath("(//*[@class='card-title'])[" + i + "]")).getText());
            tempValue = tempValue + driver.findElement(By.xpath("(//*[@class='card-title'])[" + i + "]")).getText();
        }
            Assert.assertEquals(tempValue, tempLocationOfWidgetsOnTheDesktop);
    }


    public void navigationToTheCalendar(){
            driver.findElement(buttonOfHomeLocator).click();
            driver.findElement(calendarIconLocator).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class, 'b2bcabinet-mainpage__calendar')]")));
    }

    public void navigationToTheSetting(){
        driver.findElement(buttonOfHomeLocator).click();
        driver.findElement(settingIconLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#settings")));
    }

    public void navigationToTheCart(){
        driver.findElement(By.xpath("//*[contains(@class, 'icon-cart')]")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector(".basket-page")).isDisplayed());
    }

    public void storingDataFromTheWidgetOfPersonalData(){
        dataFromTheWidget = driver.findElement(By.cssSelector(".personal_information")).getText();
    }

    public void storingDataFromTheSettingTabOfPersonalData(){
        dataFromTheSetting = driver.findElement(By.cssSelector("[placeholder='Введите имя']")).getAttribute("value");
        dataFromTheSetting = dataFromTheWidget + driver.findElement(By.cssSelector("[placeholder='Введите фамилию']")).getAttribute("value");
        dataFromTheSetting = dataFromTheWidget + "\n" + driver.findElement(By.cssSelector("[placeholder='admin@sotbit.com']")).getText();
        Assert.assertEquals("данные пользователя не равны", dataFromTheSetting.trim(), dataFromTheWidget.trim());
    }

    public void storingDataFromTheWidgetOfMyCart(){
        tempNumber = Integer.parseInt(driver.findElement(By.xpath("(//*[contains(@class, 'widget-pending')] /*)[1]")).getText());
    }

    public void storingTheQuantityOfProductsInTheCart(){
        while (i < driver.findElements(By.xpath("//*[contains(@id, 'basket-item-quantity')]")).size()){
            i++;
            sumOfProductsInCart = sumOfProductsInCart + Integer.parseInt( driver.findElement(By.xpath("(//*[contains(@id, 'basket-item-quantity')])[" + i + "]")).getAttribute("value"));
        }
        Assert.assertEquals(sumOfProductsInCart, tempNumber);
    }

    public void CheckingThatTheWidgetOfOrganizationsHaveContent(){
        Assert.assertTrue(driver.findElement(By.cssSelector(".widget-organizations-content")).isDisplayed());
    }

//    public void addingTheWidgetOfOrdersStatusToDesktop(){
//            driver.findElement(addWidgetButtonLocator).click();
//            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".widgets_cabinet")));
//            driver.findElement(By.xpath("//*[@class='widget_button'] //*[contains(text(), 'Состояние заказов')]")).click();
//            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".sotbit-cabinet-gadget-orders")));
//    }

    public void addingTheWidgetOfPersonalDataToDesktop(){
            driver.findElement(addWidgetButtonLocator).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".widgets_cabinet")));
            driver.findElement(By.xpath("//*[@class='widget_button'] //*[contains(text(), 'Персональные данные')]")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".sotbit-cabinet-gadget-profile")));
    }

    public void addingTheWidgetOfMyCartToDesktop(){
            driver.findElement(addWidgetButtonLocator).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".widgets_cabinet")));
            driver.findElement(By.xpath("//*[@class='widget_button'] //*[contains(text(), 'Моя корзина')]")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".sotbit-cabinet-gadget-basket")));
    }

    public void addingTheWidgetOfOrganizationsToDesktop(){
        driver.findElement(addWidgetButtonLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".widgets_cabinet")));
        driver.findElement(By.xpath("//*[@class='widget_button'] //*[contains(text(), 'Организации')]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".sotbit-cabinet-gadget-buyers")));
    }

    public void checkThatTheOrdersAreDisplayInTheCalendarTab(){
        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'Создан заказ')]")).isDisplayed());
        System.out.println(tempValue);
        Assert.assertTrue(driver.findElement(By.xpath("//*[@class='fc-event-title'][text()='Создан заказ №" + tempValue + "']")).isDisplayed());
    }
    public void fillingBasicData(){
            driver.findElement(By.cssSelector("[placeholder='Введите имя']")).clear();
            driver.findElement(By.cssSelector("[placeholder='Введите имя']")).sendKeys(name);

            driver.findElement(By.cssSelector("[placeholder='Введите фамилию']")).clear();
            driver.findElement(By.cssSelector("[placeholder='Введите фамилию']")).sendKeys(lastName);

            driver.findElement(By.cssSelector("[placeholder='Как к Вам обращаться']")).clear();
            driver.findElement(By.cssSelector("[placeholder='Как к Вам обращаться']")).sendKeys(appeal);
    }

    public void fillingPersonalData(){
        driver.findElement(By.xpath("(//*[@name='PERSONAL_GENDER'])[" + randomNumberUpToTwo + "]")).click();
        driver.findElement(By.cssSelector("[placeholder='Введите телефон']")).clear();
        driver.findElement(By.cssSelector("[placeholder='Введите телефон']")).sendKeys(phone);

        driver.findElement(By.cssSelector("[placeholder='Введите факс']")).clear();
        driver.findElement(By.cssSelector("[placeholder='Введите факс']")).sendKeys(phone);

        driver.findElement(By.cssSelector("[placeholder='Введите мобильный']")).clear();
        driver.findElement(By.cssSelector("[placeholder='Введите мобильный']")).sendKeys(mobilePhone);
    }

    public void saveBasicData(){
        driver.findElement(buttonForSavingTheMainSettings).click();
    }
    public void savePersonalData(){
            driver.findElement(buttonForSavingThePersonalData).click();
    }

    public void checkThatBasicDataHasBeenSaved(){
        Assert.assertEquals("Основные данные не сохранились", name
                , driver.findElement(By.cssSelector("[placeholder='Введите имя']")).getAttribute("value") );
        Assert.assertEquals("Основные данные не сохранились", lastName
                , driver.findElement(By.cssSelector("[placeholder='Введите фамилию']")).getAttribute("value") );
        Assert.assertEquals("Основные данные не сохранились", appeal
                , driver.findElement(By.cssSelector("[placeholder='Как к Вам обращаться']")).getAttribute("value") );
    }
    public void checkThatBasicDataDoesNotSaved(){
        Assert.assertNotEquals("Основные данные сохранились", name
                , driver.findElement(By.cssSelector("[placeholder='Введите имя']")).getAttribute("value") );
        Assert.assertNotEquals("Основные данные сохранились", lastName
                , driver.findElement(By.cssSelector("[placeholder='Введите фамилию']")).getAttribute("value") );
        Assert.assertNotEquals("Основные данные сохранились", appeal
                , driver.findElement(By.cssSelector("[placeholder='Как к Вам обращаться']")).getAttribute("value") );
    }
        public void checkThatPersonalDataHasBeenSaved(){
            Assert.assertEquals("Телефон не сохранился", phone
                    , driver.findElement(By.cssSelector("[placeholder='Введите телефон']")).getAttribute("value") );

            Assert.assertEquals("Факс не сохранился", phone
                    , driver.findElement(By.cssSelector("[placeholder='Введите факс']")).getAttribute("value") );
            Assert.assertEquals("личные данные не сохранились", mobilePhone
                    , driver.findElement(By.cssSelector("[placeholder='Введите мобильный']")).getAttribute("value") );
        }

    public void fillingNewPassword(){
        driver.findElement(By.xpath("//*[@name='NEW_PASSWORD']")).clear();
        driver.findElement(By.xpath("//*[@name='NEW_PASSWORD']")).sendKeys(password);
        driver.findElement(By.xpath("//*[@name='NEW_PASSWORD_CONFIRM']")).clear();
        driver.findElement(By.xpath("//*[@name='NEW_PASSWORD_CONFIRM']")).sendKeys(password);

        System.out.println(password);
    }
    public void returnOldPassword(){
        driver.findElement(By.xpath("//*[@name='NEW_PASSWORD']")).clear();
        driver.findElement(By.xpath("//*[@name='NEW_PASSWORD']")).sendKeys(passwordUser);
        driver.findElement(By.xpath("//*[@name='NEW_PASSWORD_CONFIRM']")).clear();
        driver.findElement(By.xpath("//*[@name='NEW_PASSWORD_CONFIRM']")).sendKeys(passwordUser);
    }
    public void fillingFieldsOnTheLogInTabLikeUserWithNewPassword(){
        fillingFieldsOnTheLogInTabLikeUser();
        driver.findElement(passwordInputInAuthorizationTanLocator).clear();
        driver.findElement(passwordInputInAuthorizationTanLocator).sendKeys(password);
        Assert.assertEquals("Пароль не отображается", password,
                driver.findElement(passwordInputInAuthorizationTanLocator).getAttribute("value"));
    }
    public void openPrivacyPolicy(){
        driver.findElement(buttonPrivacyPolicyForMainSettings).click();
        // Store the current window handle
        String winHandleBefore = driver.getWindowHandle();
        // Perform the click operation that opens new window
        // Switch to new window opened
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }
        Assert.assertTrue(driver.findElement(By.xpath("//h4[contains(text(), 'Конфиденциальность')]")).isDisplayed());
        // Perform the actions on new window
        // Close the new window, if that window no more required
        driver.close();
        // Switch back to original browser (first window)
        driver.switchTo().window(winHandleBefore);
        // Continue with original browser (first window)
    }
    public void checkingThatPhotoIsDisplayed(){
        Assert.assertTrue(driver.findElement(By.xpath("//*[@class='image_photo']/img")).isDisplayed());
    }
    public void navigationToTheDesktop() {
        driver.findElement(buttonOfHomeLocator).click();
        driver.findElement(desktopIconLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#s0")));
    }



}
