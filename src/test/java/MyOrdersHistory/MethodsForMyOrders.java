package MyOrdersHistory;

import MakingOrders.MethodsForMakingOrders;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.ArrayList;

public class MethodsForMyOrders extends MethodsForMakingOrders {
    int randomNumberUpToNumberOfOrders;
    String tempPriceOfOrder;
    String tempNamePayment;
    int randomNumberOfPaymentWay;
    public void openingLastOrder(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@class='main-grid-row main-grid-row-body'])[1] /*[1]")));
        clickElement("(//*[@class='main-grid-row main-grid-row-body'])[1] /*[1]");
        driver.findElement(By.cssSelector(".menu-popup-item-text")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(),'Детальная информация о заказе')]")).isDisplayed());
    }
    public void checkingThatLastOrderIsDisplayed(){
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id='ORDER_LIST']//span[contains(text(),'" + numberOfOrder.replaceAll("№", "") + "')]")).isDisplayed());
    }
    public void checkingThatDeliveryWayIsEqualTheOneSpecifiedWhenMadeOrder(){
        Assert.assertTrue(nameDeliveryWay.equals(driver.findElement(By.xpath("((//*[contains(@class, 'payment-title')] /following::*[1]) /*[2])[2]")).getText()));
    }
    public void checkingThatPaymentMethodIsEqualTheOneSpecifiedWhenMadeOrder(){
        Assert.assertTrue(paymentWay.equals(driver.findElement(By.xpath("((//*[contains(@class, 'payment-title')] /following::*[1]) /*[2])[1]")).getText()));
    }
    public void checkingThatINNIsEqualTheOneSpecifiedWhenMadeOrder(){
        Assert.assertTrue(iNNNumberCompanyWhichMadeOrder.equals(driver.findElement(By.xpath("//*[contains(text(), 'ИНН')] /following::*[1]")).getText()));
    }
    public void addingDocumentToTheLastOrder(){
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        driver.findElement(By.cssSelector("#bx-panel-admin-tab")).click();
        driver.findElement(By.xpath("//*[@class='adm-main-menu'] //*[contains(text(), 'Контент')]")).click();
        driver.findElement(By.xpath("//*[@id='menucontainer']//*[text()='Акты']")).click();
        driver.findElement(By.xpath("//*[text()='Акт по заказу №2']")).click();
        driver.findElement(By.xpath("(//*[text()='Заказ:']/following::*[1] //td)[1] /*")).clear();
        driver.findElement(By.xpath("(//*[text()='Заказ:']/following::*[1] //td)[1] /*")).sendKeys(numberOfOrder);
        driver.findElement(By.cssSelector(".adm-btn-save")).click();
        driver.findElement(By.xpath("//*[@title='Перейти в режим просмотра сайта']")).click();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
    }
    public void navigationToProductTabInOrderPage(){
        driver.findElement(By.xpath("//*[text()='Товары']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#PRODUCT_LIST_2_table")));
        Assert.assertTrue(driver.findElement(By.cssSelector("#PRODUCT_LIST_2_table")).isDisplayed());
    }
    public void navigationToDocumentsTabInOrderPage(){
        driver.findElement(By.xpath("//*[@class='blank_detail-menu'] //*[text()='Документы']")).click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Акт по заказу №2']")));
//        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Акт по заказу №2']")).isDisplayed());
    }
    public void openPaymentTabInOrderPage(){
        driver.findElement(By.xpath("//*[@class='blank_detail-menu'] //*[text()='Оплаты']")).click();
    }
    public void navigationToPaymentsTabInOrderPage() {
        openPaymentTabInOrderPage();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='" + paymentWay + "']")));
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='" + paymentWay + "']")).isDisplayed());
    }
    public void openDeliveryTabInOrderPage(){
        driver.findElement(By.xpath("//*[@class='blank_detail-menu'] //*[text()='Отгрузки']")).click();
    }
    public void navigationToDeliveryTabInOrderPage() {
        openDeliveryTabInOrderPage();
        System.out.println(nameDeliveryWay);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(), '" + nameDeliveryWay + "')]")));
        Assert.assertTrue(driver.findElement(By.xpath("//td[contains(text(), '" + nameDeliveryWay + "')]")).isDisplayed());
    }
    public void openSupportServiceTabInOrderPage(){
        driver.findElement(By.xpath("//*[@class='blank_detail-menu'] //*[text()='Служба поддержки']")).click();
    }
    public void navigationToSupportServiceTabInOrderPage() {
        openSupportServiceTabInOrderPage();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='supportForm']")));
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id='supportForm']")).isDisplayed());
    }
    public void cancelingLastOrder(){
        driver.findElement(By.xpath("//*[@type='button'][contains(@class, 'b2b_detail_order__second__tab__btn')]")).click();
        driver.findElement(By.xpath("//*[@class='dropdown-item'][text()='Отменить']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Вы уверены, что хотите отменить']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Вы уверены, что хотите отменить'] /following::*[1]")).getText().contains("заказ №"));
        driver.findElement(By.xpath("//*[@name='REASON_CANCELED']")).sendKeys("Причина отказа = TEST!!!!! \n +9haE%uSKgqEde?%zRD<X*.y<xUzj@QHD^a>QR3.S6Ez!B9, ");
        driver.findElement(By.xpath("//*[@value='Отменить заказ']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("(//*[@class='main-grid-row main-grid-row-body'])[1] //*[text()='Отменён']")).isDisplayed());
    }

    public void openingRandomOrder (){
        determineRandomNumber();
        rememberingPriceOfRandomOrder();
        openRandomOrder();
        determinePriceOfRandomOrderWithoutDelivery();
    }
    public void determineRandomNumber(){
        randomNumberUpToNumberOfOrders = 1 + (int) (Math.random()
                * driver.findElements(By.xpath("//*[@class='main-grid-row main-grid-row-body']")).size());
        System.out.println("Рандомбый заказ= " + randomNumberUpToNumberOfOrders);
    }
    public void rememberingPriceOfRandomOrder(){
        tempPriceOfOrder = replacingSomeSymbols(driver.findElement(By.xpath("(//*[@class='main-grid-row main-grid-row-body'])[" + randomNumberUpToNumberOfOrders
                + "]  //*[contains(text(), '₽')]")).getText());
        System.out.println("цена выбранного заказа =" + tempPriceOfOrder);
    }
    public void openRandomOrder (){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@class='main-grid-row-action-button'])[" + randomNumberUpToNumberOfOrders + "]")));
        clickElement("(//*[@class='main-grid-row-action-button'])[" + randomNumberUpToNumberOfOrders + "]");
        driver.findElement(By.xpath("(//*[@class='menu-popup-item-text'])")).click();
    }
    public void determinePriceOfRandomOrderWithoutDelivery(){
        driver.findElement(By.xpath("//*[text()='Общее']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//dt[contains(text(),'Доставка')] /following::*[1]")));
        sumOfPricesOfTheAddedProducts = Double.valueOf(tempPriceOfOrder)
                - Double.valueOf(replacingSomeSymbols(driver.findElement(By.xpath("//dt[contains(text(),'Доставка')] /following::*[1]"))
                .getText()));
    }
    public void ReOrderingOrder(){
        driver.findElement(By.xpath("//*[@type='button'][contains(@class, 'b2b_detail_order__second__tab__btn')]")).click();
        driver.findElement(By.xpath("//*[@class='dropdown-item'][text()='Повторить заказ']")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector(".basket-page")).isDisplayed());
        Assert.assertTrue(sumOfPricesOfTheAddedProducts == Double.valueOf(replacingSomeSymbols(driver.findElement(By.cssSelector(".basket-page__total-price-value"))
                .getText())), "сумма для сравнения - " + sumOfPricesOfTheAddedProducts);
    }
    public void checkingPriceOfLastedOrder(){
        System.out.println("Сумма=" + sumOfPricesOfTheAddedProducts);
        Assert.assertTrue((sumOfPricesOfTheAddedProducts +tempShippingCost)
                ==Double.valueOf(replacingSomeSymbols(driver.findElement(By.xpath("(//*[@class='main-grid-row main-grid-row-body'])[1] //*[contains(text(), '₽')]"))
                .getText())));
    }
    public void checkingThatPaymentMethodIsChanged(){
        System.out.println(paymentWay);
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='" + paymentWay + "']")));
        try{
            Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Оплата через '] /*")).getText().equals(paymentWay));
        }catch (Exception e){
            Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'Платёжная система:')]/following::*[1]")).getText().equals(paymentWay));
            System.out.println(driver.findElement(By.xpath("//*[contains(text(), 'Платёжная система:')]/following::*[1]")).getText());
        }
    }
    public void changingWayOfPaymentInOrder(){
        driver.findElement(By.xpath("//*[text()='Общее']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".sale-order-detail-payment-options-methods-info-change-link")));
        driver.findElement(By.cssSelector(".sale-order-detail-payment-options-methods-info-change-link")).click();
        randomNumberOfPaymentWay = 1 + (int) (Math.random()
                * driver.findElements(By.cssSelector(".sale-order-payment-change-pp-company")).size());
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@class='sale-order-payment-change-pp-company-smalltitle'])["
                + randomNumberOfPaymentWay + "]")));
        paymentWay = driver.findElement(By.xpath("(//*[@class='sale-order-payment-change-pp-company-smalltitle'])["
                + randomNumberOfPaymentWay + "]")).getText();
        if ((driver.findElement(By.xpath("(//*[@class='sale-order-payment-change-pp-company-smalltitle'])["
                + randomNumberOfPaymentWay + "]")).getText().equals("Счёт на оплату"))
                || driver.findElement(By.xpath("(//*[@class='sale-order-payment-change-pp-company-smalltitle'])["
                + randomNumberOfPaymentWay + "]")).getText().equals("Банковский перевод")){
            driver.findElement(By.xpath("(//*[@class='sale-order-payment-change-pp-company'])[" + randomNumberOfPaymentWay + "]")).click();
            implicitWaiting();
            ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
            driver.switchTo().window(tabs2.get(1));
            Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'СЧЕТ')]")).isDisplayed());
            driver.close();
            driver.switchTo().window(tabs2.get(0));
        }else {
            driver.findElement(By.xpath("(//*[@class='sale-order-payment-change-pp-company'])[" + randomNumberOfPaymentWay + "]")).click();
        }
    }
    public void checkingThatPaymentMethodIsChangedInTabOfPayment(){
        Assert.assertTrue(driver.findElement(By.xpath("(//*[@class='tab-pane fade active show']//*[@class='main-grid-row main-grid-row-body'] " +
                "//*[@class='main-grid-cell main-grid-cell-left'])[2]")).getText().equals(paymentWay));
    }
    public void creatingOrdersUntilTheSecondPageOfOrdersIsVisible (){
        while (driver.findElements(By.xpath("//*[@class='main-ui-pagination']")).size() < 1 || count > 10){
            navigationToCatalogTab();
            changeTheQuantityOfRandomProduct();
            wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("catalog__basket-quantity-value"), "1"));
            navigationToCart();
            navigationToMakingOrderFromCart();
            trySelectCompany();
            makingOrder();
            navigationToMyOrdersPage();
        }
        Assert.assertTrue(driver.findElements(By.xpath("//*[@class='main-grid-row main-grid-row-body']")).size() == randomNumberPage);
    }
    public void checkingThatPagesAreSwitching(){
        tempValue = driver.findElement(By.xpath("//*[@class='main-grid-row main-grid-row-body'] /*[@class='main-grid-cell main-grid-cell-left']//*[@class='main-grid-cell-content']")).getText();
        driver.findElement(By.xpath("//*[@class='main-ui-pagination-page']")).click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//*[@class='main-ui-pagination-page main-ui-pagination-active']"), "2"));
        implicitWaiting();
        tempValue2 = driver.findElement(By.xpath("//*[@class='main-grid-row main-grid-row-body'] /*[@class='main-grid-cell main-grid-cell-left']//*[@class='main-grid-cell-content']")).getText();
        Assert.assertFalse(tempValue.equals(tempValue2));
    }
    public void rememberingNamesAndQuantityAddedProducts(){
        tempValue1 = driver.findElement(By.xpath("(//*[@class='basket__product-name '])[1]")).getText();
        tempValue2 = driver.findElement(By.xpath("(//*[@class='basket__product-name '])[2]")).getText();
        tempDouble = Double.valueOf(driver.findElement(By.xpath("(//*[contains(@id, 'basket-item-quantity')])[1]")).getAttribute("value"));
        tempDouble2 = Double.valueOf(driver.findElement(By.xpath("(//*[contains(@id, 'basket-item-quantity')])[2]")).getAttribute("value"));
        tempDouble3 = Double.valueOf(replacingSomeSymbols(driver.findElement(By.xpath("((//*[@data-entity= 'basket-item'])[1] //*[@class='basket__column-price-wrap'])[last()]")).getText()));
        tempDouble4 = Double.valueOf(replacingSomeSymbols(driver.findElement(By.xpath("((//*[@data-entity= 'basket-item'])[2] //*[@class='basket__column-price-wrap'])[last()]")).getText()));
    }
    public void rememberingTotalPrice (){
        scrollToTheElement("//*[@class='index_checkout-total_title'] //*[@class='index_checkout-promocode-total_amount']");
        tempDouble5 = Double.valueOf(replacingSomeSymbols(driver.findElement(By.xpath
                ("//*[@class='index_checkout-total_title'] //*[@class='index_checkout-promocode-total_amount']")).getText()));
    }
    public void findTheColumnWithTheQuantityOfTheProduct (){
        for (int i = 1; i < 10 ; i++) {
            if (driver.findElement(By.xpath("(//*[@id='PRODUCT_LIST_table']//*[@class='main-grid-header'] //*[contains(@class,'main-grid-draggable')])[" + i + "]"))
                    .getAttribute("data-name").equals("QUANTITY")){
                count = i;
                break;
            }
        }
    }
    public void checkingThatAddedProductsAreDisplayed(){
        driver.findElement(By.xpath("//*[text()='Общее']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='PRODUCT_LIST_table']//*[text()='" + tempValue1 + "']")));
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id='PRODUCT_LIST_table']//*[text()='" + tempValue1 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id='PRODUCT_LIST_table']//*[text()='" + tempValue2 + "']")).isDisplayed());
        findTheColumnWithTheQuantityOfTheProduct();
        driver.findElement(By.xpath("//*[text()='Общее']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("((//*[@id='PRODUCT_LIST_table']//*[@class='main-grid-row main-grid-row-body'])[1]//*[@class='main-grid-cell main-grid-cell-left'])[" + count + "]")));
        Assert.assertTrue( Double.valueOf(driver.findElement(By.xpath
                ("((//*[@id='PRODUCT_LIST_table']//*[@class='main-grid-row main-grid-row-body'])[1]//*[@class='main-grid-cell main-grid-cell-left'])[" + count + "]")).getText())
                == tempDouble, "Количество первого товара не равно кол-ву вводимому мной");
        Assert.assertTrue(Double.valueOf(driver.findElement(By.xpath
                ("((//*[@id='PRODUCT_LIST_table']//*[@class='main-grid-row main-grid-row-body'])[2]//*[@class='main-grid-cell main-grid-cell-left'])[" + count + "]")).getText())
                == tempDouble2, "Количество второго товара не равно кол-ву вводимому мной");
        Assert.assertTrue( driver.findElement(By.xpath("//*[@name='form_PRODUCT_LIST'] //*[@class='main-grid-panel-content-text']")).getText().equals("2")
                , "ВСЕГО не равен '2'");

        Assert.assertTrue(tempDouble3 == Double.valueOf(replacingSomeSymbols(driver.findElement(By.xpath
                ("((//*[@id='PRODUCT_LIST_table']//*[@class='main-grid-row main-grid-row-body'])[1]//*[@class='main-grid-cell main-grid-cell-left'])[last()]")).getText())));
        Assert.assertTrue(tempDouble4 == Double.valueOf(replacingSomeSymbols(driver.findElement(By.xpath
                ("((//*[@id='PRODUCT_LIST_table']//*[@class='main-grid-row main-grid-row-body'])[2]//*[@class='main-grid-cell main-grid-cell-left'])[last()]")).getText())));

        Assert.assertTrue(tempDouble5 == Double.valueOf(replacingSomeSymbols(driver.findElement(By.xpath
                ("(//*[@class='table table-bordered'] //*[@class='text-center'])[last()]")).getText())));
    }
    public void enteringWordForSearch(){
        driver.findElement(By.cssSelector("#PRODUCT_LIST_search")).clear();
        driver.findElement(By.cssSelector("#PRODUCT_LIST_search")).sendKeys(tempValue1);
        try {
            driver.findElement(By.xpath("//*[@id='popup-window-content-PRODUCT_LIST_search_container']//*[contains(@class, 'main-ui-filter-find')]")).click();
        }catch (Exception e){
            System.out.println("!!!!!!!");
        }
    }
    public void checkingThatOnlyOneProductIsDisplayed(){
        Assert.assertTrue(driver.findElements(By.xpath("//*[@id='PRODUCT_LIST_table'] //*[@class='main-grid-row main-grid-row-body']")).size() == 1
                , "Кол-во товаров после поиска по имени не навно '1'");
        Assert.assertTrue( driver.findElement(By.xpath("//*[@name='form_PRODUCT_LIST'] //*[@class='main-grid-panel-content-text']")).getText().equals("1")
                , "ВСЕГО не равен '1'");
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id='PRODUCT_LIST_table']//*[text()='" + tempValue1 + "']")).isDisplayed());
        findTheColumnWithTheQuantityOfTheProduct();
        Assert.assertTrue(Double.valueOf(driver.findElement(By.xpath
                ("((//*[@id='PRODUCT_LIST_table']//*[@class='main-grid-row main-grid-row-body'])[1]//*[@class='main-grid-cell main-grid-cell-left'])[" + count + "]")).getText())
                == tempDouble, "Количество первого товара не равно кол-ву вводимому мной");
    }
    public void openLastOrder(){
        driver.findElement(By.xpath("//*[@title='Посмотреть подробную информацию о заказе']")).click();
    }
    public void addingSecondPaymentForOrder (){
        driver.findElement(By.xpath("//*[@value='Добавить оплату']")).click();
        driver.findElement(By.xpath("//select[contains(@name, 'PAYMENT')]")).click();
        driver.findElement(By.xpath("(//select[contains(@name, 'PAYMENT')] /option)[2]")).click();
        driver.findElement(By.xpath("//*[contains(@id, 'PAYMENT_SUM')]")).sendKeys("666");
        driver.findElement(buttonSaveLocator).click();
    }
    public void addingSecondDeliveryForOrder(){
        driver.findElement(By.xpath("//*[@value='Добавить отгрузку']")).click();
        driver.findElement(By.xpath("//*[contains(@id, 'PRICE_DELIVERY')][contains(@class, 'input-price')]")).clear();
        driver.findElement(By.xpath("//*[contains(@id, 'PRICE_DELIVERY')][contains(@class, 'input-price')]")).sendKeys("666");
        driver.findElement(buttonSaveLocator).click();
    }
    public void checkingThatThereAreTwoPaymentsThere(){
        openPaymentTabInOrderPage();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='PAY_SYSTEMS_LIST_table'] //span[contains(text(), '666')]")));
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id='PAY_SYSTEMS_LIST_table'] //span[contains(text(), '666')]")).isDisplayed());
        Assert.assertTrue(driver.findElements(By.xpath("//*[@id='PAY_SYSTEMS_LIST_table'] //*[@class='main-grid-row main-grid-row-body']")).size() == 2);
    }
    public void checkingThatThereAreTwoDelivery(){
        openDeliveryTabInOrderPage();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(), '666 ₽')]")));
        Assert.assertTrue(driver.findElement(By.xpath("//td[contains(text(), '666 ₽')]")).isDisplayed());
        Assert.assertTrue(driver.findElements(By.cssSelector(".card.card-shipment__wrap")).size() == 2);
    }
    int numberOfOrders = 0;
    public void searchForAnOrderByItSNumber(){
        numberOfOrders = driver.findElements(By.xpath("//*[@id='ORDER_LIST_table'] //*[@class='main-grid-row main-grid-row-body']")).size();
        driver.findElement(By.cssSelector("#ORDER_LIST_search")).sendKeys(numberOfOrder);
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//*[@id='ORDER_LIST_table'] //*[@class='main-grid-row main-grid-row-body']"), 1));
        Assert.assertEquals(driver.findElements(By.xpath("//*[@id='ORDER_LIST_table'] //*[@class='main-grid-row main-grid-row-body']")).size(), 1);
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id='ORDER_LIST_table'] //*[@class='main-grid-row main-grid-row-body'] //*[@class= 'main-grid-cell-inner']"))
                .getText(), numberOfOrder);
    }
    public void deletingNumberForSearchUsingCloseIcon(){
        driver.findElement(By.xpath("//*[@id='ORDER_LIST_search_container']//*[contains(@class, 'delete')]")).click();
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//*[@id='ORDER_LIST_table'] //*[@class='main-grid-row main-grid-row-body']"), 1));
    }
    public void deletingNumberForSearchUsingFieldForSearch(){
        driver.findElement(By.cssSelector("#ORDER_LIST_search")).clear();
        driver.findElement(By.cssSelector("#ORDER_LIST_search")).sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//*[@id='ORDER_LIST_table'] //*[@class='main-grid-row main-grid-row-body']"), 1));
    }
    public void checkingThatAllOrdersAreDisplayedAgain (){
        Assert.assertEquals(driver.findElements(By.xpath("//*[@id='ORDER_LIST_table'] //*[@class='main-grid-row main-grid-row-body']")).size(), numberOfOrders);
    }

}

