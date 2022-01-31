package MeanPage;

import BaseActions.BaseActions;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MethodsForMeanPage extends BaseActions {


    // локаторы для главной страницы
    By calendarIconLocator = By.cssSelector(".icon-calendar2.mr-2");
    By settingIconLocator = By.cssSelector(".icon-cog.mr-2");
    By buttonOfHomeLocator = By.xpath("//*[contains(@class, 'card-sidebar-mobile')] //*[contains(text(), 'Главная')]");
    By buttonForResetTheCurrentSettings = By.xpath("//*[text()='Сбросить текущие настройки']");
    By leftColumnLocator = By.xpath("//*[@id='s0']");
    By middleColumnLocator = By.xpath("//*[@id='s1']");
    By rightColumnLocator = By.xpath("//*[@id='s2']");
    By firsWidgetInLeftColumnLocator = By.xpath("(//*[@id='s0'] //*[@class='card-title'])[1]");
    By secondWidgetInMiddleColumnLocator = By.xpath("(//*[@id='s1'] //*[@class='card-title'])[2]");
    By randomWidgetFromAddTabLocator = By.xpath("(//div[@class='widgets_cabinet_title'])" + "[" + randomNumberUpToTwelve + "]");
    By firsWidgetInRightColumnLocator = By.xpath("(//*[@id='s2'] //*[@class='card-title'])[1]");
    By randomWidgetInDesktopLocator = By.xpath("(//*[contains(@class, 'sotbit-cabinet-gadget')])" + "[" + randomNumberUpToTwelve + "]");
    String tempValueOfTitle;
    String tempValueOfId;
    String tempLocationOfWidgetsOnTheDesktop;
    String dataFromTheWidget;
    String dataFromTheSetting;
    int tempNumber;
    int sumOfProductsInCart = 0;
    int i = 0;

    WebElement tempLocator;
    By tempLocatorForSearchElementByTextAndColumn;
    By addWidgetButtonLocator = By.xpath("//*[contains(text(), 'Добавить виджет')]");
    By saveSettingLikeDefaultLocator = By.xpath("//*[contains(text(), 'Сохранить как настройки по умолчанию')]");
    By allWidgetsOnTheDesktop = By.cssSelector(".sotbit-cabinet-gadget");
    By buttonForSavingTheMainSettings =  By.xpath("(//*[contains(@name, 'save')])[1]");
    By buttonForSavingThePersonalData =  By.xpath("(//*[contains(@name, 'save')])[2]");
    By buttonPrivacyPolicyForMainSettings = By.xpath("(//*[contains(text(), 'политикой конфиденциальности.')])[1]");


    public void movingTheWidgetFromTheLeftToTheMiddleColumn(){
        tempValueOfTitle = driver.findElement(firsWidgetInLeftColumnLocator).getText();
        actions.moveToElement(driver.findElement(firsWidgetInLeftColumnLocator));
        actions.clickAndHold();
        actions.moveToElement(driver.findElement(middleColumnLocator));
        actions.release().perform();
        explicitWaiting();
        tempLocatorForSearchElementByTextAndColumn = By.xpath("//*[@id='s1'] //*[text()='" + tempValueOfTitle + "']");
        Assert.assertTrue("Перемещенный виджет не отображается в другой колонке",driver.findElement(tempLocatorForSearchElementByTextAndColumn).isDisplayed());
        explicitWaiting();
        driver.navigate().refresh();
        explicitWaiting();
        Assert.assertTrue("Перемещенный виджет не отображается в другой колонке",driver.findElement(tempLocatorForSearchElementByTextAndColumn).isDisplayed());
    }
    public void movingTheWidgetFromTheMiddleToTheRightColumn(){
        tempValueOfTitle = driver.findElement(secondWidgetInMiddleColumnLocator).getText();
        actions.moveToElement(driver.findElement(secondWidgetInMiddleColumnLocator));
        actions.clickAndHold();
        actions.moveToElement(driver.findElement(rightColumnLocator));
        actions.release().perform();
        explicitWaiting();
        tempLocatorForSearchElementByTextAndColumn = By.xpath("//*[@id='s2'] //*[text()='" + tempValueOfTitle + "']");
        Assert.assertTrue("Перемещенный виджет не отображается в другой колонке",driver.findElement(tempLocatorForSearchElementByTextAndColumn).isDisplayed());
        explicitWaiting();
        driver.navigate().refresh();
        explicitWaiting();
        Assert.assertTrue("Перемещенный виджет не отображается в другой колонке",driver.findElement(tempLocatorForSearchElementByTextAndColumn).isDisplayed());
    }
    public void movingTheWidgetFromTheRightToTheLeftColumn(){
        tempValueOfTitle = driver.findElement(firsWidgetInRightColumnLocator).getText();
        actions.moveToElement(driver.findElement(firsWidgetInRightColumnLocator));
        actions.clickAndHold();
        actions.moveToElement(driver.findElement(leftColumnLocator));
        actions.release().perform();
        explicitWaiting();
        tempLocatorForSearchElementByTextAndColumn = By.xpath("//*[@id='s0'] //*[text()='" + tempValueOfTitle + "']");
        Assert.assertTrue("Перемещенный виджет не отображается в другой колонке",driver.findElement(tempLocatorForSearchElementByTextAndColumn).isDisplayed());
        explicitWaiting();
        driver.navigate().refresh();
        explicitWaiting();
        Assert.assertTrue("Перемещенный виджет не отображается в другой колонке",driver.findElement(tempLocatorForSearchElementByTextAndColumn).isDisplayed());
    }
    public void addingRandomWidgetToTheDesktop(){
        driver.findElement(addWidgetButtonLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".widgets_cabinet")));
        tempValueOfTitle = driver.findElement(randomWidgetFromAddTabLocator).getText();
        driver.findElement(randomWidgetFromAddTabLocator).click();
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
        Assert.assertNotEquals(tempValueOfId, driver.findElement(By.xpath("(//*[contains(@class, 'data-table-gadget')])[1]")).getAttribute("id"));
        explicitWaiting();
        driver.navigate().refresh();
        explicitWaiting();
        Assert.assertNotEquals(tempValueOfId, driver.findElement(By.xpath("(//*[contains(@class, 'data-table-gadget')])[1]")).getAttribute("id"));
    }

    public void deletionRandomWidgetFromDesktop(){
        tempValueOfId = driver.findElement(randomWidgetInDesktopLocator).getAttribute("id");
        tempLocator = driver.findElement(randomWidgetInDesktopLocator);
        tempLocator.findElement(By.xpath("//*[@data-action='remove']")).click();
        explicitWaiting();
        Assert.assertNotEquals(tempValueOfId, driver.findElement(randomWidgetInDesktopLocator).getAttribute("id"));
        explicitWaiting();
        driver.navigate().refresh();
        explicitWaiting();
        Assert.assertNotEquals(tempValueOfId, driver.findElement(randomWidgetInDesktopLocator).getAttribute("id"));
    }

    public void resettingTheCurrentWidgetSettings (){
        driver.findElement(buttonForResetTheCurrentSettings).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        explicitWaiting();
    }

    public void storingTheLocationOfWidgetsOnTheDesktop(){
            tempLocationOfWidgetsOnTheDesktop = driver.findElements(allWidgetsOnTheDesktop).toString();
    }

    public void saveTheLocationOfWidgetsOnTheDesktopLikeDefault(){
            driver.findElement(saveSettingLikeDefaultLocator).click();
            new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());  //сохраннеие настроек по умолчанию метод
            driver.switchTo().alert().accept();
    }

    public void checkingTgeReturnOfSettingsToDefault(){
            Assert.assertEquals(driver.findElements(allWidgetsOnTheDesktop).toString(), tempLocationOfWidgetsOnTheDesktop); // проверка что виджеты метод
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
        //Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'Изменился статус заказа')]")).isDisplayed());
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



}
