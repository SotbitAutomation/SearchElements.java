package MyOrdersHistory;

import MakingOrders.MethodsForMakingOrders;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;

public class MethodsForMyOrders extends MethodsForMakingOrders {
    int randomNumberUpToNumberOfOrders;
    String tempPriceOfOrder;
    String tempNamePayment;
    int randomNumberOfPaymentWay;
    public void openingLastOrder(){
        driver.findElement(By.xpath("(//*[@class='main-grid-row main-grid-row-body'])[1] /*[1]")).click();
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
        exitFromB2B();
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
        exitFromB2B();
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
    public void navigationToPaymentsTabInOrderPage() {
        driver.findElement(By.xpath("//*[@class='blank_detail-menu'] //*[text()='Оплаты']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='" + paymentWay + "']")));
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='" + paymentWay + "']")).isDisplayed());
    }
    public void navigationToDeliveryTabInOrderPage() {
        driver.findElement(By.xpath("//*[@class='blank_detail-menu'] //*[text()='Отгрузки']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='" + nameDeliveryWay + "']")));
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='" + nameDeliveryWay + "']")).isDisplayed());
    }
    public void navigationToSupportServiceTabInOrderPage() {
        driver.findElement(By.xpath("//*[@class='blank_detail-menu'] //*[text()='Служба поддержки']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='supportForm']")));
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id='supportForm']")).isDisplayed());
    }
    public void cancelingLastOrder(){
        driver.findElement(By.xpath("//*[@type='button'][contains(@class, 'b2b_detail_order__second__tab__btn')]")).click();
        driver.findElement(By.xpath("//*[@class='dropdown-item'][text()='Отменить']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Вы уверены, что хотите отменить']")).isDisplayed());
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
        driver.findElement(By.xpath("(//*[@class='main-grid-row-action-button'])[" + randomNumberUpToNumberOfOrders + "]")).click();
        driver.findElement(By.xpath("(//*[@class='menu-popup-item-text'])")).click();
    }
    public void determinePriceOfRandomOrderWithoutDelivery(){
        sumOfPricesOfTheAddedProducts = Double.valueOf(tempPriceOfOrder)
                - Double.valueOf(replacingSomeSymbols(driver.findElement(By.xpath("//dt[contains(text(),'Доставка')] /following::*[1]"))
                .getText()));
    }
    public void ReOrderingOrder(){
        driver.findElement(By.xpath("//*[@type='button'][contains(@class, 'b2b_detail_order__second__tab__btn')]")).click();
        driver.findElement(By.xpath("//*[@class='dropdown-item'][text()='Повторить заказ']")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector(".basket-page")).isDisplayed());
        Assert.assertTrue(sumOfPricesOfTheAddedProducts
                == Double.valueOf(replacingSomeSymbols(driver.findElement(By.cssSelector(".basket-page__total-price-value"))
                .getText())));
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
        driver.findElement(By.cssSelector(".sale-order-detail-payment-options-methods-info-change-link")).click();

        randomNumberOfPaymentWay = 1 + (int) (Math.random()
                * driver.findElements(By.cssSelector(".sale-order-payment-change-pp-company")).size());
        paymentWay = driver.findElement(By.xpath("(//*[@class='sale-order-payment-change-pp-company-smalltitle'])["
                + randomNumberOfPaymentWay + "]")).getText();
        if ((driver.findElement(By.xpath("(//*[@class='sale-order-payment-change-pp-company-smalltitle'])["
                + randomNumberOfPaymentWay + "]")).getText().equals("Счёт на оплату"))
                || driver.findElement(By.xpath("(//*[@class='sale-order-payment-change-pp-company-smalltitle'])["
                + randomNumberOfPaymentWay + "]")).getText().equals("Банковский перевод")){
            driver.findElement(By.xpath("(//*[@class='sale-order-payment-change-pp-company'])[" + randomNumberOfPaymentWay + "]")).click();
            explicitWaiting();
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
        System.out.println(tempValue);
        driver.findElement(By.xpath("//*[@class='main-ui-pagination-page']")).click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//*[@class='main-ui-pagination-page main-ui-pagination-active']"), "2"));
        explicitWaiting();
        System.out.println(tempValue2);
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
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id='PRODUCT_LIST_table']//*[text()='" + tempValue1 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id='PRODUCT_LIST_table']//*[text()='" + tempValue2 + "']")).isDisplayed());
        findTheColumnWithTheQuantityOfTheProduct();
        Assert.assertTrue("Количество первого товара не равно кол-ву вводимому мной", Double.valueOf(driver.findElement(By.xpath
                ("((//*[@id='PRODUCT_LIST_table']//*[@class='main-grid-row main-grid-row-body'])[1]//*[@class='main-grid-cell main-grid-cell-left'])[" + count + "]")).getText())
                == tempDouble);
        Assert.assertTrue("Количество второго товара не равно кол-ву вводимому мной",Double.valueOf(driver.findElement(By.xpath
                ("((//*[@id='PRODUCT_LIST_table']//*[@class='main-grid-row main-grid-row-body'])[2]//*[@class='main-grid-cell main-grid-cell-left'])[" + count + "]")).getText())
                == tempDouble2);
        Assert.assertTrue("ВСЕГО не равен '2'", driver.findElement(By.xpath("//*[@name='form_PRODUCT_LIST'] //*[@class='main-grid-panel-content-text']")).getText().equals("2"));

        Assert.assertTrue(tempDouble3 == Double.valueOf(replacingSomeSymbols(driver.findElement(By.xpath
                ("((//*[@id='PRODUCT_LIST_table']//*[@class='main-grid-row main-grid-row-body'])[1]//*[@class='main-grid-cell main-grid-cell-left'])[last()]")).getText())));
        Assert.assertTrue(tempDouble4 == Double.valueOf(replacingSomeSymbols(driver.findElement(By.xpath
                ("((//*[@id='PRODUCT_LIST_table']//*[@class='main-grid-row main-grid-row-body'])[2]//*[@class='main-grid-cell main-grid-cell-left'])[last()]")).getText())));

        Assert.assertTrue(tempDouble5 == Double.valueOf(replacingSomeSymbols(driver.findElement(By.xpath
                ("(//*[@class='table table-bordered'] //*[@class='text-center'])[last()]")).getText())));
    }
    public void enteringWordForSearch(){
        driver.findElement(By.cssSelector("#PRODUCT_LIST_search")).sendKeys(tempValue1);
        try {
            driver.findElement(By.xpath("//*[@id='popup-window-content-PRODUCT_LIST_search_container']//*[contains(@class, 'main-ui-filter-find')]")).click();
        }catch (Exception e){
            System.out.println("!!!!!!!");
        }
    }
    public void checkingThatOnlyOneProductIsDisplayed(){
        Assert.assertTrue("Кол-во товаров после поиска по имени не навно '1'",driver.findElements(By.xpath("//*[@id='PRODUCT_LIST_table'] //*[@class='main-grid-row main-grid-row-body']")).size() == 1);
        Assert.assertTrue("ВСЕГО не равен '1'", driver.findElement(By.xpath("//*[@name='form_PRODUCT_LIST'] //*[@class='main-grid-panel-content-text']")).getText().equals("1"));
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id='PRODUCT_LIST_table']//*[text()='" + tempValue1 + "']")).isDisplayed());
        findTheColumnWithTheQuantityOfTheProduct();
        Assert.assertTrue("Количество первого товара не равно кол-ву вводимому мной", Double.valueOf(driver.findElement(By.xpath
                ("((//*[@id='PRODUCT_LIST_table']//*[@class='main-grid-row main-grid-row-body'])[1]//*[@class='main-grid-cell main-grid-cell-left'])[" + count + "]")).getText())
                == tempDouble);
    }


}
