package MeanPage;

import BaseActions.BaseActions;
import MakingOrders.MethodsForMakingOrders;
import OrganizationsWithExtendedVersion.MethodsForAddingOrganizationsWithExtendedVersion;
import RegistrationAndAuthorization.RegistrationB2B;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MethodsForMeanPage extends BaseActions {

    MethodsForMakingOrders makeOrder = new MethodsForMakingOrders();
    RegistrationB2B registr = new RegistrationB2B();

    MethodsForAddingOrganizationsWithExtendedVersion org = new MethodsForAddingOrganizationsWithExtendedVersion();

    // локаторы для главной страницы
    By randomColumnWhereToMoveLocator;
    By buttonForResetTheCurrentSettings = By.cssSelector(".icon-reset");
    By notesWidgetLocator = By.xpath("//*[@class='widget_button'] //*[contains(text(), 'Заметки')]");
    By organizationWidgetLocator = By.xpath("//*[@class='widget_button'] //*[contains(text(), 'Организации')]");
    By myCartWidgetLocator = By.xpath("//*[@class='widget_button'] //*[contains(text(), 'Моя корзина')]");
    By catalogWidgetLocator = By.xpath("//*[@class='widget_button'] //*[contains(text(), 'Каталог')]");
    By trafficJamsWidgetLocator = By.xpath("//*[@class='widget_button'] //*[contains(text(), 'Пробки')]");
    By favoritesLinks = By.xpath("//*[@class='widget_button'] //*[contains(text(), 'Избранные ссылки')]");
    By personalDataWidgetLocator = By.xpath("//*[@class='widget_button'] //*[contains(text(), 'Персональные данные')]");
    By weatherWidgetLocator = By.xpath("//*[@class='widget_button'] //*[contains(text(), 'погод')]");
    By myOrdersWidgetLocator = By.xpath("//*[@class='widget_button'] //*[contains(text(), 'Мои заказы')]");
    By rssLEntWidgetLocator = By.xpath("//*[@class='widget_button'] //*[contains(text(), 'RSS лента')]");
    By personalAccountWidgetLocator = By.xpath("//*[@class='widget_button'] //*[contains(text(), 'Личный счет')]");


    By tempLocatorForSearchElementByTextAndColumn;
    By addWidgetButtonLocator = By.xpath("//*[contains(@data-fab-label, 'Добавить виджет')]");
    By saveSettingLikeDefaultLocator = By.cssSelector(".icon-floppy-disk");
    By buttonForSavingThePersonalData = By.xpath("(//*[contains(@name, 'save')])[2]");
    By buttonPrivacyPolicyForMainSettings = By.xpath("(//*[contains(text(), 'политикой конфиденциальности.')])[1]");
    String tempValueOfTitle;
    String tempValueOfId;
    String tempLocationOfWidgetsOnTheDesktop;
    String dataFromTheWidget;
    String dataFromTheSetting;
    int tempNumber;
    int sumOfProductsInCart = 0;
    int i = 0;
    int columnNumberWhereToMove;


    public void movingARandomWidget(int columnNumber) {
        Actions action = new Actions(driver);
        tempRandomNumber = 1 + (int) (Math.random() * driver.findElements(By.xpath("(//*[contains(@class, 'gd-page-column')])[" + columnNumber + "] //*[contains(@class, 'sotbit-cabinet-gadget')]")).size());
        System.out.println("Рандомный номер виджета в колонке № " + columnNumber + "  =  " + tempRandomNumber);
        By randomWidgetLocator = By.xpath("((//*[contains(@class, 'gd-page-column')])[" + columnNumber + "] //*[contains(@class, 'sotbit-cabinet-gadget')])[" + tempRandomNumber + "] //*[@class='card-title']");
        tempValueOfTitle = driver.findElement(randomWidgetLocator).getText();
        System.out.println("Название виджета который перемещаю - " + tempValueOfTitle);
        scrollToTheElement(randomWidgetLocator);
        waitingMilliSecond();
        action.moveToElement(driver.findElement(randomWidgetLocator));
        waitingMilliSecond();
        action.clickAndHold();
        implicitWaiting();

        if (columnNumber != 3) {
            columnNumberWhereToMove = columnNumber + 1;
        } else {
            columnNumberWhereToMove = 1;
        }
        randomColumnWhereToMoveLocator = By.xpath("(//*[contains(@class, 'gd-page-column')])[" + columnNumberWhereToMove + "]");
        action.moveToElement(driver.findElement(randomColumnWhereToMoveLocator));
        implicitWaiting();
        action.release().perform();
        implicitWaiting();
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.xpath("(//*[contains(@class, 'gd-page-column')])[" + columnNumber + "] //*[@class='card-title'][text()='" + tempValueOfTitle + "']")));
        }catch (Exception e){// обрабатываю тупую ошибку
            System.out.println("тупая ошибка !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            refreshingThisPage();
            navigationToTheDesktop();
            action.moveToElement(driver.findElement(randomWidgetLocator));
            action.clickAndHold();
            implicitWaiting();

            if (columnNumber != 3) {
                columnNumberWhereToMove = columnNumber + 1;
            } else {
                columnNumberWhereToMove = 1;
            }
            randomColumnWhereToMoveLocator = By.xpath("(//*[contains(@class, 'gd-page-column')])[" + columnNumberWhereToMove + "]");
            action.moveToElement(driver.findElement(randomColumnWhereToMoveLocator));
            implicitWaiting();
            action.release().perform();
            implicitWaiting();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.xpath("(//*[contains(@class, 'gd-page-column')])[" + columnNumber + "] //*[@class='card-title'][text()='" + tempValueOfTitle + "']")));
        }
        refreshingThisPage();
        Assert.assertTrue(driver.findElements(By.xpath("(//*[contains(@class, 'gd-page-column')])[" + columnNumber + "] //*[@class='card-title'][text()='" + tempValueOfTitle + "']")).size() == 0
                , "Виджет отображается в колонке из которой я его перемещал");

        Assert.assertTrue(driver.findElement(By.xpath("(//*[contains(@class, 'gd-page-column')])[" + columnNumberWhereToMove + "] //*[@class='card-title'][text()='" + tempValueOfTitle + "']")).isDisplayed()
                , "Перемещенный виджет не отображается в колонке в которую я его переместил");
    }

    public void showButtonForAddingWidgets() {
        try {
            driver.findElement(By.cssSelector(".fab-menu-absolute")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".icon-reset")));
            implicitWaiting();
        } catch (Exception e) {
            System.out.println("меню с кнопкой доб. виджета уже раскрыта");
        }
        waitingMilliSecond();
    }

    public void addingRandomWidgetToTheDesktop() {
        showButtonForAddingWidgets();
        driver.findElement(addWidgetButtonLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".widgets_cabinet")));
        tempRandomNumber = 1 + (int) (Math.random() * driver.findElements(By.xpath("(//div[@class='widgets_cabinet_title'])")).size());
        tempValueOfTitle = driver.findElement(By.xpath("(//div[@class='widgets_cabinet_title'])" + "[" + tempRandomNumber + "]")).getText();
        System.out.println("Название добавленного виджета  - " + tempValueOfTitle);
        //scrollDownToTheElement("(//div[@class='widgets_cabinet_title'])" + "[" + tempRandomNumber + "]");
        driver.findElement(By.xpath("(//div[@class='widgets_cabinet_title'])" + "[" + tempRandomNumber + "]")).click();
        implicitWaiting();
        tempLocatorForSearchElementByTextAndColumn = By.xpath("//*[text()='" + tempValueOfTitle + "']");
        System.out.println("Проверяю что виджет с таким заголовком есть " + tempValueOfTitle);
        Assert.assertTrue(driver.findElement(tempLocatorForSearchElementByTextAndColumn).isDisplayed(), "Добавленный виджет не отображается");
        implicitWaiting();
        refreshingThisPage();
        implicitWaiting();
        Assert.assertTrue(driver.findElement(tempLocatorForSearchElementByTextAndColumn).isDisplayed(), "Добавленный виджет не отображается");
    }

    public void deletionJustAddedWidget() {
        tempValueOfId = driver.findElement(By.xpath("(//*[contains(@class, 'data-table-gadget')])[1]")).getAttribute("id");
        driver.findElement(By.xpath("(//*[@data-action='remove'])[1]")).click();
        implicitWaiting();
        Assert.assertNotEquals(driver.findElement(By.xpath("(//*[contains(@class, 'data-table-gadget')])[1]")).getAttribute("id"), tempValueOfId);
        waitingMilliSecond();
        refreshingThisPage();
        waitingMilliSecond();
        Assert.assertNotEquals(driver.findElement(By.xpath("(//*[contains(@class, 'data-table-gadget')])[1]")).getAttribute("id"), tempValueOfId);
    }

    public void deletionRandomWidgetFromDesktop() {
        tempRandomNumber = 1 + (int) (Math.random() * driver.findElements(By.xpath("//*[contains(@class, 'sotbit-cabinet-gadget')]//*[@class='card-title']")).size());
        tempValueOfId = driver.findElement(By.xpath("(//*[contains(@class, 'sotbit-cabinet-gadget')])[" + tempRandomNumber + "]")).getAttribute("id");
        try {
            driver.findElement(By.xpath("(//*[contains(@class, 'sotbit-cabinet-gadget')])" + "[" + tempRandomNumber + "] //*[@data-action = 'remove']")).click();
        }catch (Exception e){
            System.out.println("Удалить, не смог кликнуть - "  + tempRandomNumber);
            refreshingThisPage();
            scrollToTheElement("(//*[contains(@class, 'sotbit-cabinet-gadget')])" + "[" + tempRandomNumber + "] //*[@data-action = 'remove']");
            driver.findElement(By.xpath("(//*[contains(@class, 'sotbit-cabinet-gadget')])" + "[" + tempRandomNumber + "] //*[@data-action = 'remove']")).click();
        }
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='" + tempValueOfId + "']")));
        Assert.assertTrue(driver.findElements(By.xpath("//*[@id='" + tempValueOfId + "']")).size() == 0);
        refreshingThisPage();
        Assert.assertTrue(driver.findElements(By.xpath("//*[@id='" + tempValueOfId + "']")).size() == 0);
    }

    public void resettingTheCurrentWidgetSettings() {
        showButtonForAddingWidgets();
        try {
            driver.findElement(buttonForResetTheCurrentSettings).click();
        }catch (Exception e){
            System.out.println("тупая ошибка");
            navigationToTheDesktop();
            showButtonForAddingWidgets();
            driver.findElement(buttonForResetTheCurrentSettings).click();

        }
        new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        implicitWaiting();
        refreshingThisPage();
    }

    public void storingTheLocationOfWidgetsOnTheDesktop() {
        tempLocationOfWidgetsOnTheDesktop="";
        for (int i = 1; i <= driver.findElements(By.xpath("//*[contains(@class, 'sotbit-cabinet-gadget')]//*[@class='card-title']")).size(); i++) {
            tempLocationOfWidgetsOnTheDesktop = tempLocationOfWidgetsOnTheDesktop + driver.findElement(By.xpath("(//*[@class='card-title'])[" + i + "]")).getText();
        }
    }

    public void saveTheSettingForWidgetsOnTheDesktopLikeDefault() {
        showButtonForAddingWidgets();
        tryToClickElement(saveSettingLikeDefaultLocator);
        //driver.findElement(saveSettingLikeDefaultLocator).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());  //сохраннеие настроек по умолчанию метод
        driver.switchTo().alert().accept();
    }

    public void checkingTgeReturnOfSettingsToDefault() {
        tempValue="";
        for (int i = 1; i <= driver.findElements(By.xpath("//*[contains(@class, 'sotbit-cabinet-gadget')]//*[@class='card-title']")).size(); i++) {
            tempValue = tempValue + driver.findElement(By.xpath("(//*[@class='card-title'])[" + i + "]")).getText();
        }
        System.out.println("Фактические виджеты - " + tempValue);
        System.out.println("Ожидаемые виджеты - " + tempLocationOfWidgetsOnTheDesktop);
        Assert.assertEquals(tempLocationOfWidgetsOnTheDesktop,tempValue);


    }

    public void navigationToTheCart() {
        driver.findElement(By.xpath("//*[contains(@class, 'icon-cart')]")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector(".basket-page")).isDisplayed());
    }

    public void storingDataFromTheWidgetOfPersonalData() {
        dataFromTheWidget = driver.findElement(By.cssSelector(".personal_information")).getText();
    }

    public void storingDataFromTheSettingTabOfPersonalData() {
        dataFromTheSetting = driver.findElement(By.cssSelector("[placeholder='Введите имя']")).getAttribute("value");
        dataFromTheSetting = dataFromTheWidget + driver.findElement(By.cssSelector("[placeholder='Введите фамилию']")).getAttribute("value");
        dataFromTheSetting = dataFromTheWidget + "\n" + driver.findElement(By.cssSelector("[placeholder='admin@sotbit.com']")).getText();
    }

    public void checkingThatDataInWidgetOfPersonalIsEqualsSettingInMeanPage() {
        Assert.assertEquals(dataFromTheSetting.trim(), dataFromTheWidget.trim(), "данные пользователя не равны");
        System.out.println("Данные в виджете:");
        System.out.println(dataFromTheWidget);
        System.out.println();
        System.out.println("Данные в настрйоках на главной старнице:");
        System.out.println(dataFromTheSetting);
    }

    public void storingDataFromTheWidgetOfMyCart() {
        if (driver.findElement(By.xpath("(//*[contains(@class, 'widget-pending')] /*)[1]")).getText().equals("0")) {
            navigationToCatalogTab();
            makeOrder.changeTheQuantityOfRandomProduct();
            navigationToTheDesktop();
        }
        tempNumber = Integer.parseInt(driver.findElement(By.xpath("(//*[contains(@class, 'widget-pending')] /*)[1]")).getText());
    }

    public void storingTheQuantityOfProductsInTheCart() {
//        for (int i = 1; i <= driver.findElements(By.cssSelector(".basket__item")).size(); i++) {
//            sumOfProductsInCart = sumOfProductsInCart + Integer.parseInt( driver.findElement(By.xpath("(//*[contains(@id, 'basket-item-quantity')])[" + i + "]")).getAttribute("value"));
//        }
        sumOfProductsInCart = driver.findElements(By.cssSelector(".basket__product-name")).size();
    }

    public void checkingThatTheNumberOfProductsInTheWidgetAndInTheCartAreEqual() {
        Assert.assertEquals(sumOfProductsInCart, tempNumber);
        System.out.println("Количество товаров в корзине - " + sumOfProductsInCart);
        System.out.println("Количество товаров в виджете - " + tempNumber);
    }

    public void checkingThatTheWidgetOfOrganizationsHaveContent() {
        try {
            if (driver.findElements(By.cssSelector(".company-auth-vidget-item")).size() < 3) ;
        } catch (Exception e) {
            navigationToOrganizationTab();
            org.creatingThreeOrganizations();
            navigationToTheDesktop();
            Assert.assertTrue(driver.findElements(By.cssSelector(".company-auth-vidget-item")).size() < 3);
        }
        tempValue = driver.findElement(By.xpath("(//*[@class='company-auth-vidget-item'])[1]")).getText();
        navigationToOrganizationTab();
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='" + tempValue + "']")).isDisplayed());
    }

    public void checkingThatTheWidgetOfTrafficJamsHaveContent() {
        Assert.assertTrue(driver.findElement(By.cssSelector(".congestion_content")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector(".congestion_content-rate")).isDisplayed());
    }

    public void addingTheWidgetToDesktop(By xpathOfWidget) {
        showButtonForAddingWidgets();
        driver.findElement(addWidgetButtonLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".widgets_cabinet")));
        try {
            driver.findElement(xpathOfWidget).click();
        }catch (Exception e){
            System.out.println("Не смог кликнуть так как нужно сначала проскролить до элемента");
            scrollToTheElement(xpathOfWidget);
            driver.findElement(xpathOfWidget).click();
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".sotbit_cabinet")));
    }

    public void checkThatTheOrdersAreDisplayInTheCalendarTab() {
        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'Создан заказ')]")).isDisplayed());
        System.out.println(tempValue);
        Assert.assertTrue(driver.findElement(By.xpath("//*[@class='fc-event-title'][text()='Создан заказ №" + tempValue + "']")).isDisplayed());
    }

    public void fillingBasicData() {
        name = "Name Имя" + randomString(8);
        lastName = "LastName Фамилия" + randomString(10);
        appeal = "sir" + randomString(10);
        driver.findElement(By.cssSelector("[placeholder='Введите имя']")).clear();
        driver.findElement(By.cssSelector("[placeholder='Введите имя']")).sendKeys(name);

        driver.findElement(By.cssSelector("[placeholder='Введите фамилию']")).clear();
        driver.findElement(By.cssSelector("[placeholder='Введите фамилию']")).sendKeys(lastName);

        driver.findElement(By.cssSelector("[placeholder='Как к Вам обращаться']")).clear();
        driver.findElement(By.cssSelector("[placeholder='Как к Вам обращаться']")).sendKeys(appeal);
    }

    public void fillingPersonalData() {
        scrollToTheElement("(//*[@name='PERSONAL_GENDER'])[" + randomNumberUpToTwo + "]");
        driver.findElement(By.xpath("(//*[@name='PERSONAL_GENDER'])[" + randomNumberUpToTwo + "]")).click();
        driver.findElement(By.cssSelector("[placeholder='Введите телефон']")).clear();
        driver.findElement(By.cssSelector("[placeholder='Введите телефон']")).sendKeys(phone);
        driver.findElement(By.cssSelector("[placeholder='Введите факс']")).clear();
        driver.findElement(By.cssSelector("[placeholder='Введите факс']")).sendKeys(phone);
        driver.findElement(By.cssSelector("[placeholder='Введите мобильный']")).clear();
        driver.findElement(By.cssSelector("[placeholder='Введите мобильный']")).sendKeys(mobilePhone);
    }

    public void savePersonalData() {
        scrollToTheElement(buttonForSavingThePersonalData);
        driver.findElement(buttonForSavingThePersonalData).click();
    }

    public void checkThatBasicDataHasBeenSaved() {
        Assert.assertEquals(name, driver.findElement(By.cssSelector("[placeholder='Введите имя']")).getAttribute("value")
                , "Основные данные не сохранились");
        Assert.assertEquals(lastName, driver.findElement(By.cssSelector("[placeholder='Введите фамилию']")).getAttribute("value")
                , "Основные данные не сохранились");
        Assert.assertEquals(appeal, driver.findElement(By.cssSelector("[placeholder='Как к Вам обращаться']")).getAttribute("value")
                , "Основные данные не сохранились");
    }

    public void checkThatBasicDataDoesNotSaved() {
        Assert.assertNotEquals(name.trim(), driver.findElement(By.cssSelector("[placeholder='Введите имя']")).getAttribute("value").trim()
                , "Основные данные сохранились");
        Assert.assertNotEquals(lastName.trim(), driver.findElement(By.cssSelector("[placeholder='Введите фамилию']")).getAttribute("value").trim()
                , "Основные данные сохранились");
        Assert.assertNotEquals(appeal.trim(), driver.findElement(By.cssSelector("[placeholder='Как к Вам обращаться']")).getAttribute("value").trim()
                , "Основные данные сохранились");
    }

    public void checkThatPersonalDataHasBeenSaved() {
        Assert.assertEquals(phone, driver.findElement(By.cssSelector("[placeholder='Введите телефон']")).getAttribute("value")
                , "Телефон не сохранился");

        Assert.assertEquals(phone, driver.findElement(By.cssSelector("[placeholder='Введите факс']")).getAttribute("value")
                , "Факс не сохранился");
        Assert.assertEquals(mobilePhone, driver.findElement(By.cssSelector("[placeholder='Введите мобильный']")).getAttribute("value")
                , "личные данные не сохранились");
    }

    public void fillingNewPassword() {
        driver.findElement(By.xpath("//*[@name='NEW_PASSWORD']")).clear();
        driver.findElement(By.xpath("//*[@name='NEW_PASSWORD']")).sendKeys(password);
        driver.findElement(By.xpath("//*[@name='NEW_PASSWORD_CONFIRM']")).clear();
        driver.findElement(By.xpath("//*[@name='NEW_PASSWORD_CONFIRM']")).sendKeys(password);

        System.out.println(password);
    }

    public void returnOldPassword() {
        driver.findElement(By.xpath("//*[@name='NEW_PASSWORD']")).clear();
        driver.findElement(By.xpath("//*[@name='NEW_PASSWORD']")).sendKeys(passwordUser);
        driver.findElement(By.xpath("//*[@name='NEW_PASSWORD_CONFIRM']")).clear();
        driver.findElement(By.xpath("//*[@name='NEW_PASSWORD_CONFIRM']")).sendKeys(passwordUser);
    }

    public void fillingFieldsOnTheLogInTabLikeUserWithNewPassword() {
        fillingFieldsOnTheLogInTabLikeUser();
        driver.findElement(passwordInputInAuthorizationTanLocator).clear();
        driver.findElement(passwordInputInAuthorizationTanLocator).sendKeys(password);
        Assert.assertEquals( password, driver.findElement(passwordInputInAuthorizationTanLocator).getAttribute("value"), "Пароль не отображается");
    }

    public void openPrivacyPolicy() {
        driver.findElement(buttonPrivacyPolicyForMainSettings).click();
        String winHandleBefore = driver.getWindowHandle();
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        if (driver.findElements((By.xpath("//*[contains(text(), 'Конфиденциальность')]"))).size() == 1){
            Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'Конфиденциальность')]")).isDisplayed());
        }else{
            Assert.assertTrue(driver.findElement(By.xpath("(//*[contains(text(), 'Конфиденциальность')])[2]")).isDisplayed());
        }


        driver.close();
        driver.switchTo().window(winHandleBefore);
    }

    public void checkingThatPhotoIsDisplayed() {
        determineThemeColor();
        if (themeColorBlack){
            Assert.assertTrue(driver.findElement(By.xpath("//*[@class='sidebar-user']//*[contains(@src, 'upload')]")).isDisplayed());
        }else{
            Assert.assertTrue(driver.findElements(By.xpath("//img[contains(@src, 'upload/resize_cach')]")).size() >= 2);
        }
    }

    public void checkingThatThereIsNoButtonToSaveTheDefaultSettings() {
        showButtonForAddingWidgets();
        Assert.assertTrue(driver.findElements(By.xpath("//*[contains(@onclick, 'SetForAll')]")).size() == 0);
    }

    public void fillingTheDataForTheNotesWidget() {
        driver.findElement(By.xpath("//*[@class='gadgetholder']//*[contains(@href, 'edit')]")).click();
        driver.findElement(By.xpath("//*[@class='gadgetholder']//*[contains(@class, 'editor-cell')]")).click();
        By locIframe = By.xpath("//*[contains(@id, 'LHE_iframe_bxlhe')]");
        driver.switchTo().frame(driver.findElement(locIframe));
        driver.findElement(By.xpath("/html/body")).sendKeys(randomData);
        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("(//*[contains(@class, 'sotbit-cabinet-gadget')])[1] //*[contains(@onclick, 'save()')]")).click();
    }

    public void fillingTheDataForTheFavoriteLinksWidget() {
        driver.findElement(By.xpath("(//*[contains(@id, 'favoriteslink')][not(ancestor-or-self::*[@style = 'display: none;'])] /*)[1]")).click();
        driver.findElement(By.xpath("//*[@name='url']")).sendKeys("www.bbc.com");
        driver.findElement(By.xpath("//*[@name='name']")).sendKeys("Новости на - BBC" + randomData);
        System.out.println(randomData);
        driver.findElement(By.xpath("//*[@type='submit']")).click();
        refreshingThisPage();
    }

    public void checkingThatEnteredDataIsDisplayedInTheNotesWidget() {
        refreshingThisPage();
        Assert.assertTrue(driver.findElement(By.xpath("(//*[contains(@class, 'sotbit-cabinet-gadget')])[1] //*[contains(@class, 'gdcontent')]")).getText().contains(randomData));
    }

    public void downloadingCatalogToYourComputerFromMeanPage() {
        makeOrder.deletingExcelAndJpgFilesFromDownloads();
        driver.findElement(By.cssSelector(".icon-upload")).click();
    }

    public void checkingThatTheLinkInTheFavoriteLinksWidgetWorks() {
        driver.findElement(By.xpath("//*[contains(text(), '" + randomData + "')]")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("www.bbc.com"));
    }

    public void checkingThatWeatherWidgetIsDisplayed() {
        Assert.assertEquals(driver.findElement(By.xpath("(//*[contains(@class, 'sotbit-cabinet-gadget')])[1] //*[@class='card-title']")).getText(), "Прогноз погоды");
    }

    public void makeOrderForWidget() {
        makeOrder.deletingProductsFromTheCart();
        navigationToCatalogTab();
        makeOrder.changeTheQuantityOfRandomProduct();
        makeOrder.checkingThatThePriceOfTheAddedProductHasBeenCalculated();
        navigationToCart();
        makeOrder.navigationToMakingOrderFromCart();
        makeOrder.trySelectCompany();
        makeOrder.makingOrder();
        navigationToTheDesktop();
    }

    public void rememberingDataOfMyOrdersInTheWidget() {
        implicitWaiting();
        tempValue = driver.findElement(By.xpath("(//*[contains(@class, 'sotbit-cabinet-gadget')])[1] //*[contains(@href, 'orders/detail')]")).getAttribute("title").replaceAll("[^\\d.]", "");
        tempValue2 = driver.findElement(By.xpath("//*[@class='widget_order_information'] /*[contains(text(), 'умма')]")).getText().replaceAll("[^\\d.]", "");
    }

    public void checkingThatThereIsAOrderWhichDisplayedInWidget() {
        Assert.assertEquals(tempValue, driver.findElement(By.xpath("//*[@class='main-grid-row main-grid-row-body'] /*[@class='main-grid-cell main-grid-cell-left']")).getText());
        Assert.assertEquals(tempValue2, driver.findElement(By.xpath("//*[@class='main-grid-row main-grid-row-body'] /*[@class='main-grid-cell main-grid-cell-left'] //*[contains(text(), '₽')]"))
                .getText().replaceAll("[^\\d.]", ""));
    }

    public void goToTheDetailedOrderPageFromTheWidget() {
        driver.findElement(By.xpath("(//*[contains(@class, 'sotbit-cabinet-gadget')])[1] //*[contains(@title, 'аказ №')]/*[contains(text(), '" + tempValue + "')]")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//*[@class='breadcrumb']//*[contains(text(), '" + tempValue + "')]")).isDisplayed());
    }

    public void addingRssLentToTheWidget() {
        try {
            driver.findElement(By.xpath("//*[contains(@class, 'icon-pencil')]")).click();
            driver.findElement(By.xpath("//*[contains(@id, 'RSS_URL')]")).sendKeys("https://news.mail.ru/rss/");
            try {
                driver.findElement(By.cssSelector(".button-ok")).click();
            }catch (Exception e2){
                System.out.println("тупая ошибка, недоскролил до кнопки");
                scrollToTheElement(".button-ok");
                driver.findElement(By.cssSelector(".button-ok")).click();
            }
        }catch (Exception e){
            System.out.println("Похоже включен режим правки, выключаю его");
            refreshingThisPage();
            ternOffEditMode();
            driver.findElement(By.xpath("//*[contains(@class, 'icon-pencil')]")).click();
            driver.findElement(By.xpath("//*[contains(@id, 'RSS_URL')]")).sendKeys("https://news.mail.ru/rss/");
            driver.findElement(By.cssSelector(".button-ok")).click();
        }
    }

    public void checkingThatThisLentIsAdded() {
        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'Новости Mail.ru')]")).isDisplayed());
    }

    public void requestReplenishmentOfYourPersonalAccount() {
        driver.findElement(By.xpath("(//*[@class='sale-acountpay-pp-company-image'])[4]")).click();
        driver.findElement(By.xpath("//*[@class='form-control sale-acountpay-input']")).sendKeys("1000");
        waitingMilliSecond();
        driver.findElement(By.cssSelector(".send_invoices")).click();
        tempValue = driver.findElement(By.xpath("(//*[contains(@class, 'widget-private_invoices_content')]/p)[1] /b")).getText().replaceAll("[^\\d.]", "");
    }

    public void checkingThatThePersonalAccountReplenishmentRequestHasBeenCreated() {
        Assert.assertTrue(driver.findElement(By.xpath("//*[@class='widget_content widget-private_invoices_content'] /p[contains(text(), 'Номер вашей оплаты')]")).isDisplayed());
    }

    public void checkingThatTheUserHasBeenAddedMoneyToHisPersonalAccountInWidget() {
        Assert.assertEquals(driver.findElement(By.xpath("(//*[contains(@class, 'widget-private_invoices_header')] /*)[2]")).getText().replaceAll("[^\\d.]", ""), "111");
    }

    public void makeSureThatTheCheckboxForThePrivacyPolicyIsMandatory() {
        Assert.assertTrue(driver.findElements(By.xpath("(//*[@class='uniform-checker']//*[@type='checkbox'][@required])[1]")).size() > 0);
    }

    public void saveBasicDataWithOutAgreement() {
        scrollToTheElement("(//*[contains(@name, 'save')])[1]");
        driver.findElement(By.xpath("(//*[contains(@name, 'save')])[1]")).click();
        Assert.assertTrue(driver.findElements(By.cssSelector(".alert-success")).size() == 0);
    }

    public void uncheckThePrivacyPolicyCheckbox() {
        driver.findElement(By.xpath("(//*[@class='uniform-checker'])[1]")).click();
    }

    public void goToTheDetailedOrganizationPageFromTheWidget() {
        navigationToTheDesktop();
        tempValue = driver.findElement(By.cssSelector(".company-auth-vidget-item-link")).getText();
        driver.findElement(By.cssSelector(".company-auth-vidget-item-link")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//*[@name='NAME']")).getAttribute("value").contains(tempValue));
    }
    public void deletingAllWidget (){
        tempRandomNumber = driver.findElements(By.xpath("//*[@data-action='remove']")).size();
        for (int i = 1; i <= tempRandomNumber; i++) {
            driver.findElement(By.xpath("//*[@data-action='remove']")).click();
            implicitWaiting();
        }
    }
    public void addingAllWidget(){
        showButtonForAddingWidgets();
        driver.findElement(addWidgetButtonLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".widgets_cabinet")));
        tempRandomNumber = driver.findElements(By.xpath("(//div[@class='widgets_cabinet_title'])")).size();
        navigationToTheDesktop();
        for (int j = 1; j <= tempRandomNumber; j++) {
            showButtonForAddingWidgets();
            driver.findElement(addWidgetButtonLocator).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".widgets_cabinet")));
            tempValueOfTitle = driver.findElement(By.xpath("(//div[@class='widgets_cabinet_title'])" + "[" + j + "]")).getText();
            try {
                driver.findElement(By.xpath("(//*[@class='widgets_cabinet_title'])[" + j + "]")).click();
            }catch (Exception e){
                scrollToTheElement("(//*[@class='widgets_cabinet_title'])[" + j + "]");
                driver.findElement(By.xpath("(//*[@class='widgets_cabinet_title'])[" + j + "]")).click();
            }
            System.out.println("Добавляю виджет  - " + tempValueOfTitle);
            implicitWaiting();
            implicitWaiting();
        }
    }
    public void distributeWidgetsByColumns(){
        for (int i = 0; i < 6; i++) {
            movingARandomWidget(1);
        }
        for (int i = 0; i < 3; i++) {
            movingARandomWidget(2);
        }
    }
    public void rememberingLastOrderForCalendar(){
        tempValue = driver.findElement(By.xpath("//*[@class='main-grid-row main-grid-row-body'] //*[contains(@class, 'main-grid-cell-left')]")).getText();
    }


}
