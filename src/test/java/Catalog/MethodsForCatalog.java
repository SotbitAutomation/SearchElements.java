package Catalog;

import BaseActions.BaseActions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Iterator;
import java.util.Set;

public class MethodsForCatalog extends BaseActions {

    int numberOfProductsPerPage = 0;
    public int randomNumberOfProductsPerPage = 0;
    public int randomNumberPage = 0;
    public double quantityOfProductsInStock = 0;
    public double quantityOfSecondProductsInStock = 0;
    public double randomNumberUpToMAxQuantityThisProducts = 0;
    public double basePriceRandomProduct = 0;
    public double sumOfPricesOfTheAddedProducts = 0;
    public double sumOfPricesOfTheAddedProductsWithTheCostOfDelivery = 0;
    public double priceForNewlyAddedProducts = 0;
    public double pricesForAllProductsInTheFooter = 0;
    public double pricesForAllProductsInTheCartPAge = 0;
    public int numberOfProductsInTheFooter = 0;
    public int productCounterInTheCart = 0;
    double unitsOfMeasurement = 0;
    double coefficientForQuantityOfProducts = 0;
    public String tempString;
    String tempString2 = "0.1 шт";
    public int tempInt = 0;
    public int tempInt2 = 0;
    public int tempInt3 = 0;
    public double tempDouble = 0;
    public double tempDouble2 = 0;
    public double numberOfAvailableGefestGasStove;
    public double numberOfAvailableKaiserGasStove;
    public double tempDouble3 = 0;
    public double tempDouble4 = 0;
    public double tempDouble5 = 0;
    String maxPriceForFiltering = "10000";
    String minPriceForFiltering = "3000";
    double scale;
    public By quantityFieldOfGefestLocator = By.xpath("//*[contains(text(), 'GEFEST')] /following::* //*[@data-entity ='basket-item-quantity-field']");
    public By iconPlusOfGefestLocator = By.xpath("//*[contains(text(), 'GEFEST')] /following::* //*[@data-entity ='basket-item-quantity-plus']");
    public By iconMinusOfGefestLocator = By.xpath("//*[contains(text(), 'GEFEST')] /following::* //*[@data-entity ='basket-item-quantity-minus']");

    public By quantityFieldOfKaiserLocator = By.xpath("//*[contains(text(), 'Kaiser')] /following::* //*[@data-entity ='basket-item-quantity-field']");
    public By iconPlusOfKaiserLocator = By.xpath("//*[contains(text(), 'Kaiser')] /following::* //*[@data-entity ='basket-item-quantity-plus']");
    public By iconMinusOfKaiserLocator = By.xpath("//*[contains(text(), 'Kaiser')] /following::* //*[@data-entity ='basket-item-quantity-minus']");

    public By quantityFieldOfRandomTPLocator = By.xpath("//*[@data-entity ='basket-item-quantity-field']");
    public By iconPlusOfRandomTPLocator = By.xpath("//*[@data-entity ='basket-item-quantity-plus']");
    public By iconMinusOfRandomTPLocator = By.xpath("//*[@data-entity ='basket-item-quantity-minus']");

    public By quantityFieldOfTPWithAFractionalCoefficientLocator = By.xpath("(//*[@data-entity ='basket-item-quantity-field'])[1]");
    public By iconPlusOfTPWithWithAFractionalCoefficientLocator = By.xpath("(//*[@data-entity ='basket-item-quantity-plus'])[1]");
    public By iconMinusOfTPWithWithAFractionalCoefficientLocator = By.xpath("(//*[@data-entity ='basket-item-quantity-minus'])[1]");

    public By quantityFieldOfTPWithQuantitativeAccountingDisabledLocator = By.xpath("(//*[@data-entity ='basket-item-quantity-field'])[2]");
    public By iconPlusOfTPWithQuantitativeAccountingDisabledLocator = By.xpath("(//*[@data-entity ='basket-item-quantity-plus'])[2]");
    public By iconMinusOfTPWithQuantitativeAccountingDisabledLocator = By.xpath("(//*[@data-entity ='basket-item-quantity-minus'])[2]");

    public By priceForFirstProductInCart = By.xpath("((//*[@class='basket__column-price-wrap'])[1] /span)[1]");
    public By priceForSecondProductInCart = By.xpath("(//*[@class='basket__column-price-wrap'] /span)[last()-1]");



    public String del = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE;

    //By priceForUser = By.xpath("//*[@href='/orders/blank_zakaza/']");
    public By checkboxThatHighlightsAllProductsInTheCartLocator =  By.xpath("//*[@data-entity='basket-gruope-item-checkbox']");
    public By buttonForDeletingProductsInCartLocator =  By.xpath("//*[@data-entity='basket-groupe-item-delete']");


    public void changeTheQuantityOfRandomProduct() {
        //openingAllOffers(); в 11 тесте закрывает уже открытые офферы
        determiningNumberOfProductsOnThePage();
        determiningRandomNumberOfProducts();
        calculationOfTheCoefficientForNonPieceProducts();
        determiningRandomNumberUpToMAxQuantityThisProducts();
        numberOfProductsInTheFooter = Integer.parseInt(driver.findElement(By.id("catalog__basket-quantity-value")).getText());
        driver.findElement(By.xpath("(//*[@class='quantity-selector__value'])[" + randomNumberOfProductsPerPage + "]"))
                .sendKeys(String.valueOf(randomNumberUpToMAxQuantityThisProducts));
        productCounterInTheCart++;
    }

    public void changeTheQuantityOfRandomProductUsingIconPlus() {
        determiningNumberOfProductsOnThePage();
        determiningRandomNumberOfProducts();
        calculationOfTheCoefficientForNonPieceProducts();
        determiningRandomNumberUpToMAxQuantityThisProducts();
        numberOfProductsInTheFooter = Integer.parseInt(driver.findElement(By.id("catalog__basket-quantity-value")).getText());
        for (int i = 0; i < randomNumberUpToMAxQuantityThisProducts; i++) {
            if(quantityOfProductsInStock * coefficientForQuantityOfProducts < 30){
                driver.findElement(By.xpath("(//*[contains(@id, 'quantity-increment')])[" + randomNumberOfProductsPerPage + "]")).click();
                waitingMilliSecond();
            }else {
                driver.findElement(By.xpath("(//*[@class='quantity-selector__value'])[" + randomNumberOfProductsPerPage + "]"))
                        .sendKeys(String.valueOf(randomNumberUpToMAxQuantityThisProducts));
                i = (int) (randomNumberUpToMAxQuantityThisProducts * coefficientForQuantityOfProducts) +1 ;
                break;
            }
        }
        productCounterInTheCart++;
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".b2b-notification__content")));
    }

    public void addingTheMaxNumberOfProductsToTheCart (){
        determiningNumberOfProductsOnThePage();
        determiningRandomNumberOfProducts();
        calculationOfTheCoefficientForNonPieceProducts();
        determiningRandomNumberUpToMAxQuantityThisProducts();
        numberOfProductsInTheFooter = Integer.parseInt(driver.findElement(By.id("catalog__basket-quantity-value")).getText());
        driver.findElement(By.xpath("(//*[@class='quantity-selector__value'])[" + randomNumberOfProductsPerPage + "]"))
                .sendKeys(String.valueOf(quantityOfProductsInStock));
        priceForNewlyAddedProducts = basePriceRandomProduct * quantityOfProductsInStock;
        randomNumberUpToMAxQuantityThisProducts = quantityOfProductsInStock;
        productCounterInTheCart++;
    }
    public void addingTheMaxNumberOfProductsToTheCartUsingIconPlus (){
        determiningNumberOfProductsOnThePage();
        determiningRandomNumberOfProducts();
        calculationOfTheCoefficientForNonPieceProducts();
        determiningRandomNumberUpToMAxQuantityThisProducts();

        numberOfProductsInTheFooter = Integer.parseInt(driver.findElement(By.id("catalog__basket-quantity-value")).getText());
        for (int i = 0; i < quantityOfProductsInStock * coefficientForQuantityOfProducts; i++) {
            if(quantityOfProductsInStock * coefficientForQuantityOfProducts < 500){
                driver.findElement(By.xpath("(//*[contains(@id, 'quantity-increment')])[" + randomNumberOfProductsPerPage + "]")).click();
                waitingMilliSecond();
            }else {
                driver.findElement(By.xpath("(//*[@class='quantity-selector__value'])[" + randomNumberOfProductsPerPage + "]"))
                        .sendKeys(String.valueOf(quantityOfProductsInStock));
                i = (int) (quantityOfProductsInStock * coefficientForQuantityOfProducts) +1 ;
                break;
            }
        }
        priceForNewlyAddedProducts = basePriceRandomProduct * quantityOfProductsInStock;
        randomNumberUpToMAxQuantityThisProducts = quantityOfProductsInStock;
        productCounterInTheCart++;
    }

    public void determiningRandomNumberOfProducts() {
        count =0;
        flag = false;
        while (flag == false){
            tempInt = 1 + (int) (Math.random() * numberOfProductsPerPage);
            count++;
            if(count>50){
                break;
            }
            tempValue2 = driver.findElement(By.xpath("(//*[@class='item-quantity__general'])[" + tempInt + "]"))
                    .getText();
            tempValue2 = replacingSomeSymbols(tempValue2);
            tempValue2 = tempValue2.replaceAll("[^0-9.]", "");

            if (tempValue2 != null && !tempValue2.trim().isEmpty()){
                    if (Double.valueOf(tempValue2) > 0) {
                        try {
                            driver.findElement(By.xpath("(//*[@class='quantity-selector__increment'])[" + tempInt + "]")).click();
                            flag = true;
                            tempDouble = Double.valueOf(tempValue2);
                            driver.findElement(By.xpath("(//*[@class='quantity-selector__decrement'])[" + tempInt + "]")).click();
                        }catch (Exception e){}

                    }
            }
        }

        if (randomNumberOfProductsPerPage == tempInt){
            randomNumberOfProductsPerPage = tempInt + 1;
        }else{
            randomNumberOfProductsPerPage = tempInt;
        }
        if (randomNumberOfProductsPerPage == numberOfProductsPerPage+1){
            randomNumberOfProductsPerPage = randomNumberOfProductsPerPage-2;
        }            //Рандомной номер от 1 до (кол-во продуктов на странице), но не равный предыдущему рандомному продукту

        System.out.println("Выбран товар №_" + randomNumberOfProductsPerPage);
    }

    public void determiningRandomNumberUpToMAxQuantityThisProducts(){
        System.out.println("В наличии= " + tempDouble);
        quantityOfProductsInStock = tempDouble;
        randomNumberUpToMAxQuantityThisProducts = 1 + (int) (Math.random() * quantityOfProductsInStock * coefficientForQuantityOfProducts);
        randomNumberUpToMAxQuantityThisProducts = (double) (randomNumberUpToMAxQuantityThisProducts / coefficientForQuantityOfProducts);
    }

    public void calculationOfTheCoefficientForNonPieceProducts(){
        numberOfProductsInTheFooter = Integer.parseInt(driver.findElement(By.id("catalog__basket-quantity-value")).getText());
        driver.findElement(By.xpath("(//*[@class='quantity-selector__increment'])[" + randomNumberOfProductsPerPage + "]")).click();
        try {
            wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("catalog__basket-quantity-value"), String.valueOf(numberOfProductsInTheFooter+1)));
        }catch (Exception e){
            driver.findElement(By.xpath("(//*[@class='quantity-selector__increment'])[" + randomNumberOfProductsPerPage + "]")).click();
            wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("catalog__basket-quantity-value"), String.valueOf(numberOfProductsInTheFooter+1)));
        }
        explicitWaiting();

        if (Double.valueOf(driver.findElement(By.xpath("(//*[@class='quantity-selector__value'])[" + randomNumberOfProductsPerPage + "]")).getAttribute("value"))
            == 0.1){
            unitsOfMeasurement = 0.1;
        }else {
            unitsOfMeasurement = 1;
        }

        driver.findElement(By.xpath("(//*[@class='quantity-selector__decrement'])[" + randomNumberOfProductsPerPage + "]")).click();
        coefficientForQuantityOfProducts = 1/unitsOfMeasurement;
        driver.findElement(By.xpath("(//*[@class='quantity-selector__decrement'])[" + randomNumberOfProductsPerPage + "]")).click();
        try {
            wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("catalog__basket-quantity-value"), String.valueOf(numberOfProductsInTheFooter)));
        }catch (Exception e){
            driver.findElement(By.xpath("(//*[@class='quantity-selector__value'])[" + randomNumberOfProductsPerPage + "]")).clear();
            driver.findElement(By.xpath("(//*[@class='quantity-selector__value'])[" + randomNumberOfProductsPerPage + "]")).sendKeys("0");
            wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("catalog__basket-quantity-value"), String.valueOf(numberOfProductsInTheFooter)));
        }
    }

    public void determiningNumberOfProductsOnThePage() {
        numberOfProductsPerPage = driver.findElements(By.cssSelector(".product__link")).size() - driver.findElements(By.cssSelector(".offers-info__label")).size();
        System.out.println("Количесвто товаров на странице = " + numberOfProductsPerPage);
    }

    public void determiningPriceOfThisRandomProduct() {
        tempString = driver.findElement(By.xpath("(//*[contains(@id, 'price_BASE')])[" + randomNumberOfProductsPerPage + "]")).getText();
        basePriceRandomProduct = Double.valueOf(replacingSomeSymbols(tempString));
        try{
            if(basePriceRandomProduct > Double.valueOf(replacingSomeSymbols(driver.findElement(By.xpath("(//*[contains(@id, 'SMALL_OPT')])[" + randomNumberOfProductsPerPage + "]"))
                    .getText()))){
                basePriceRandomProduct =  Double.valueOf(replacingSomeSymbols(driver.findElement(By.xpath("(//*[contains(@id, 'SMALL_OPT')])[" + randomNumberOfProductsPerPage + "]"))
                        .getText()));
            }
        }catch (Exception e){}   //Если у товара есть меньшая цена (для твоей группы пользователя), то выберется меньшая цена

        try{
            if(basePriceRandomProduct > Double.valueOf(replacingSomeSymbols(driver.findElement(By.xpath("(//*[contains(@id, 'price_OPT')])[" + randomNumberOfProductsPerPage + "]"))
                    .getText()))){
                basePriceRandomProduct =  Double.valueOf(replacingSomeSymbols(driver.findElement(By.xpath("(//*[contains(@id, 'price_OPT')])[" + randomNumberOfProductsPerPage + "]"))
                        .getText()));
            }
        }catch (Exception e){}   //Если у товара есть меньшая цена (для твоей группы пользователя), то выберется меньшая цена

        try{
            if(basePriceRandomProduct > Double.valueOf(replacingSomeSymbols(driver.findElement(By.xpath("(//*[contains(@id, 'price_TEST')])[" + randomNumberOfProductsPerPage + "]"))
                    .getText()))){
                basePriceRandomProduct =  Double.valueOf(replacingSomeSymbols(driver.findElement(By.xpath("(//*[contains(@id, 'price_TEST')])[" + randomNumberOfProductsPerPage + "]"))
                        .getText()));
            }
        }catch (Exception e){}   //Если у товара есть меньшая цена (для твоей группы пользователя), то выберется меньшая цена

        try{
            if(basePriceRandomProduct > Double.valueOf(replacingSomeSymbols(driver.findElement(By.xpath("(//*[contains(@id, 'PRIVATE_PRICE')])[" + randomNumberOfProductsPerPage + "]"))
                    .getText()))){
                basePriceRandomProduct =  Double.valueOf(replacingSomeSymbols(driver.findElement(By.xpath("(//*[contains(@id, 'PRIVATE_PRICE')])[" + randomNumberOfProductsPerPage + "]"))
                        .getText()));
            }
        }catch (Exception e){}   //Если у товара есть меньшая цена (для твоей группы пользователя), то выберется меньшая цена

        System.out.println("Доступная цена товара за шт. = " + basePriceRandomProduct);
        System.out.println("Рандомное число товаров = " + randomNumberUpToMAxQuantityThisProducts);
        priceForNewlyAddedProducts = basePriceRandomProduct * randomNumberUpToMAxQuantityThisProducts ; //* coefficientForQuantityOfProducts
    }
    public void calculatingOfThePriceForAllProducts(){
        sumOfPricesOfTheAddedProducts = Math.round((sumOfPricesOfTheAddedProducts + priceForNewlyAddedProducts) * 100.0) / 100.0;
        System.out.println("Сумма добавленных товаров подсчитанная мной = " + sumOfPricesOfTheAddedProducts);
    }

    public void checkingThatThePriceOfTheAddedProductHasBeenCalculated(){
        determiningPriceOfThisRandomProduct();
        calculatingOfThePriceForAllProducts();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("catalog__basket-quantity-value"), String.valueOf(productCounterInTheCart)));
        explicitWaiting();
        tempString = replacingSomeSymbols(driver.findElement(By.id("catalog__basket-price-value")).getText());
        System.out.println("Сумма добавленных товаров отображаемая в футере каталога = " + Double.valueOf(tempString));
        pricesForAllProductsInTheFooter =  Double.valueOf(tempString);
        explicitWaiting();

        System.out.println("1 " + sumOfPricesOfTheAddedProducts);
        System.out.println("2 " + pricesForAllProductsInTheFooter);
        Assert.assertTrue(sumOfPricesOfTheAddedProducts == pricesForAllProductsInTheFooter);
        navigationToCart();
        explicitWaiting();
        tempString = driver.findElement(By.id("page-basket-total-block")).getText();
        pricesForAllProductsInTheCartPAge =  Double.valueOf(replacingSomeSymbols(tempString));
        waitingMilliSecond();
        System.out.println("Сумма добавленных товаров отображаемая в футере корзины = " + pricesForAllProductsInTheCartPAge);
        Assert.assertTrue(pricesForAllProductsInTheCartPAge ==  sumOfPricesOfTheAddedProducts);
        navigationToCatalogTab();
    }

    public void deletingProductsFromTheCart(){
        navigationToCart();
            if (driver.findElements(By.xpath("//*[@class='basket__product-name ']")).size() > 0){
                driver.findElement(checkboxThatHighlightsAllProductsInTheCartLocator).click();
                driver.findElement(buttonForDeletingProductsInCartLocator).click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-entity='basket-item-restore-button']")));
                driver.navigate().refresh();
                explicitWaiting();

                if (!driver.findElement(By.cssSelector(".index_cart-empty")).isDisplayed()){
                    flag = false;
                    while (flag == false && count < 5){
                        count++;
                        driver.findElement(checkboxThatHighlightsAllProductsInTheCartLocator).click();
                        driver.findElement(buttonForDeletingProductsInCartLocator).click();
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-entity='basket-item-restore-button']")));
                        driver.navigate().refresh();
                        if (driver.findElements(By.xpath("//*[@class='basket__product-name ']")).size() == 0 ){
                            flag = true;
                        }
                    }
                }
            }
        Assert.assertTrue(driver.findElement(By.xpath("//*[@class='card-title']//*[contains(@href, 'blank')]")).isDisplayed());
    }
    public void deletingLastAddedProductFromTheCatalog (){
        driver.findElement(By.xpath("(//*[@class='quantity-selector__value'])[" + randomNumberOfProductsPerPage + "]"))
                .clear();
        driver.findElement(By.xpath("(//*[@class='quantity-selector__value'])[" + randomNumberOfProductsPerPage + "]"))
                .sendKeys(String.valueOf("0"));
        productCounterInTheCart--;
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("catalog__basket-quantity-value"), String.valueOf(productCounterInTheCart)));
        sumOfPricesOfTheAddedProducts = sumOfPricesOfTheAddedProducts - priceForNewlyAddedProducts;
        sumOfPricesOfTheAddedProducts = Math.round(sumOfPricesOfTheAddedProducts *1000.0) / 1000.0; //Ингода добавляет цифру в конце, поэтому округля до 3-х знаков
        tempString = driver.findElement(By.id("catalog__basket-price-value")).getText();
        pricesForAllProductsInTheFooter =  Double.valueOf(replacingSomeSymbols(tempString));
        System.out.println("1 подсчитано= " + sumOfPricesOfTheAddedProducts);
        System.out.println("2 в футере= " + pricesForAllProductsInTheFooter);
        Assert.assertTrue(pricesForAllProductsInTheFooter == sumOfPricesOfTheAddedProducts);
        navigationToCart();
        explicitWaiting();
        tempString = driver.findElement(By.id("page-basket-total-block")).getText();
        pricesForAllProductsInTheCartPAge =  Double.valueOf(replacingSomeSymbols(tempString));
        Assert.assertTrue(pricesForAllProductsInTheCartPAge == sumOfPricesOfTheAddedProducts);
        navigationToCatalogTab();
    }
    public void deletingLastAddedProductFromTheCatalogUsingIconMinus(){
        for (double i = 0; i < quantityOfProductsInStock * coefficientForQuantityOfProducts; i++) {
            if(quantityOfProductsInStock * coefficientForQuantityOfProducts < 50){
                driver.findElement(By.xpath("(//*[contains(@id, 'quantity-decrement')])[" + randomNumberOfProductsPerPage + "]")).click();
            }else {
                driver.findElement(By.xpath("(//*[@class='quantity-selector__value'])[" + randomNumberOfProductsPerPage + "]"))
                        .clear();
                driver.findElement(By.xpath("(//*[@class='quantity-selector__value'])[" + randomNumberOfProductsPerPage + "]"))
                        .sendKeys(String.valueOf("0"));
                i = quantityOfProductsInStock * coefficientForQuantityOfProducts +1;
                break;
            }
        }
        productCounterInTheCart--;
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("catalog__basket-quantity-value"), String.valueOf(productCounterInTheCart)));
        sumOfPricesOfTheAddedProducts = sumOfPricesOfTheAddedProducts - priceForNewlyAddedProducts;  // /coeff
        tempString = driver.findElement(By.id("catalog__basket-price-value")).getText();
        pricesForAllProductsInTheFooter =  Double.valueOf(replacingSomeSymbols(tempString));
        Assert.assertTrue(pricesForAllProductsInTheFooter == sumOfPricesOfTheAddedProducts);
        navigationToCart();
        explicitWaiting();
        tempString = driver.findElement(By.id("page-basket-total-block")).getText();
        pricesForAllProductsInTheCartPAge =  Double.valueOf(replacingSomeSymbols(tempString));
        Assert.assertTrue(pricesForAllProductsInTheCartPAge == sumOfPricesOfTheAddedProducts);
        navigationToCatalogTab();
    }



    public void addingPlusOneToThisProduct(){
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("catalog__basket-quantity-value"), String.valueOf(productCounterInTheCart)));
        driver.findElement(By.xpath("(//*[@class='quantity-selector__value'])[" + randomNumberOfProductsPerPage + "]"))
                .clear();
        driver.findElement(By.xpath("(//*[@class='quantity-selector__value'])[" + randomNumberOfProductsPerPage + "]"))
                .sendKeys(String.valueOf(quantityOfProductsInStock + 1));
    }
    public void addingPlusOneToThisProductUsingIconPlus(){
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("catalog__basket-quantity-value"), String.valueOf(productCounterInTheCart)));
//        if(quantityOfProductsInStock * coefficientForQuantityOfProducts < 120){
//            driver.findElement(By.xpath("(//*[@class='quantity-selector__value'])[" + randomNumberOfProductsPerPage + "]"))
//                    .clear();
//            driver.findElement(By.xpath("(//*[@class='quantity-selector__value'])[" + randomNumberOfProductsPerPage + "]"))
//                    .sendKeys( "0");
//            wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("catalog__basket-quantity-value"), String.valueOf(productCounterInTheCart-1)));
//            for (int i = 0; i < quantityOfProductsInStock * coefficientForQuantityOfProducts + 1; i++) {
//                driver.findElement(By.xpath("(//*[contains(@id, 'quantity-increment')])[" + randomNumberOfProductsPerPage + "]")).click();
//            }
//        }else {
//            driver.findElement(By.xpath("(//*[contains(@id, 'quantity-increment')])[" + randomNumberOfProductsPerPage + "]")).click();
//        }
        driver.findElement(By.xpath("(//*[contains(@id, 'quantity-increment')])[" + randomNumberOfProductsPerPage + "]")).click();
        explicitWaiting();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("catalog__basket-quantity-value"), String.valueOf(productCounterInTheCart)));
        randomNumberUpToMAxQuantityThisProducts = quantityOfProductsInStock;
    }

    public void changingPageInCatalog (){
        randomNumberPage = 1 + (int) (Math.random() * 4); //Если Каталог нормальных (все товары есть в наличии, то раскоментить)
        driver.findElement(By.xpath("(//*[@class='page-item'])[" + randomNumberPage + "]")).click();
        openingAllOffers();
        //driver.findElement(By.xpath("(//*[@class='page-item'])[1]")).click(); //Если каталог галимый (доступные товары тольок на первых двух страницах, то раскоментить)
    }

    public void navigationToThePageWithTheLastAddedProduct (){
        driver.findElement(By.xpath("(//*[@class='page-item'])[" + randomNumberPage + "]")).click();  //Если Каталог нормальных (все товары есть в наличии, то раскоментить)
        //driver.findElement(By.xpath("(//*[@class='page-item'])[1]")).click(); //Если каталог галимый (доступные товары тольок на первых двух страницах, то раскоментить)
    }
    public void decreaseQuantitiesLastAddedProduct(){
        if (randomNumberUpToMAxQuantityThisProducts > 1){
            System.out.println(productCounterInTheCart);
            driver.findElement(By.xpath("(//*[@class='quantity-selector__value'])[" + randomNumberOfProductsPerPage + "]"))
                    .clear();
            driver.findElement(By.xpath("(//*[@class='quantity-selector__value'])[" + randomNumberOfProductsPerPage + "]"))
                    .sendKeys("0");
            wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("catalog__basket-quantity-value"), String.valueOf(productCounterInTheCart-1)));
            driver.findElement(By.xpath("(//*[@class='quantity-selector__value'])[" + randomNumberOfProductsPerPage + "]"))
                    .sendKeys(String.valueOf(randomNumberUpToMAxQuantityThisProducts - 1));
            wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("catalog__basket-quantity-value"), String.valueOf(productCounterInTheCart)));
            sumOfPricesOfTheAddedProducts = sumOfPricesOfTheAddedProducts - priceForNewlyAddedProducts;
            sumOfPricesOfTheAddedProducts = sumOfPricesOfTheAddedProducts + basePriceRandomProduct * (randomNumberUpToMAxQuantityThisProducts - 1);
            if (Double.valueOf(replacingSomeSymbols(driver.findElement(By.cssSelector(".catalog__basket-price-value")).getText()))
                    != sumOfPricesOfTheAddedProducts){
                explicitWaiting();
            }
            explicitWaiting();
            tempString = driver.findElement(By.id("catalog__basket-price-value")).getText();
            pricesForAllProductsInTheFooter =  Double.valueOf(replacingSomeSymbols(tempString));
            Assert.assertTrue(pricesForAllProductsInTheFooter == sumOfPricesOfTheAddedProducts);
            navigationToCart();
            explicitWaiting();
            tempString = driver.findElement(By.id("page-basket-total-block")).getText();
            pricesForAllProductsInTheCartPAge =  Double.valueOf(replacingSomeSymbols(tempString));
            Assert.assertTrue(pricesForAllProductsInTheCartPAge == sumOfPricesOfTheAddedProducts);
        }else{
            System.out.println("кол-во товара = 1");
        }
        navigationToCatalogTab();
    }
    public void decreaseQuantitiesLastAddedProductUsingIconMinus(){
        System.out.println(randomNumberUpToMAxQuantityThisProducts);
        if (randomNumberUpToMAxQuantityThisProducts > 1){
            driver.findElement(By.xpath("(//*[contains(@id, 'quantity-decrement')])[" + randomNumberOfProductsPerPage + "]")).click();
            sumOfPricesOfTheAddedProducts = sumOfPricesOfTheAddedProducts - priceForNewlyAddedProducts;
            sumOfPricesOfTheAddedProducts = sumOfPricesOfTheAddedProducts + basePriceRandomProduct * (randomNumberUpToMAxQuantityThisProducts - 1);
            explicitWaiting();explicitWaiting();
            tempString = driver.findElement(By.id("catalog__basket-price-value")).getText();
            pricesForAllProductsInTheFooter =  Double.valueOf(replacingSomeSymbols(tempString));
            explicitWaiting();explicitWaiting();
            Assert.assertTrue(sumOfPricesOfTheAddedProducts == pricesForAllProductsInTheFooter);
            navigationToCart();
            explicitWaiting();
            tempString = driver.findElement(By.id("page-basket-total-block")).getText();
            pricesForAllProductsInTheCartPAge =  Double.valueOf(replacingSomeSymbols(tempString));
            explicitWaiting();explicitWaiting();
            Assert.assertTrue(pricesForAllProductsInTheCartPAge == sumOfPricesOfTheAddedProducts);
        }else{
            System.out.println("кол-во товара = 1");
        }
        navigationToCatalogTab();
    }
    public void addingProductsThatAreOutOfStock(){
        driver.findElement(By.cssSelector(".page-item.last")).click();
        driver.findElement(By.cssSelector(".quantity-selector__value")).sendKeys("1");
        explicitWaiting();
        Assert.assertEquals(driver.findElement(By.cssSelector(".catalog__basket-quantity-value")).getText(), "0");
    }

    public void addingProductsThatAreOutOfStockUsingIconPlus(){
        driver.findElement(By.cssSelector(".page-item.last")).click();
        driver.findElement(By.xpath("(//*[@class='quantity-selector__increment'])[1]")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector(".catalog__basket-quantity-value")).getText(), "0");
    }

    public void downloadExcelCatalog(){
        driver.findElement(By.cssSelector(".catalog__actions-toggler")).click();
        driver.findElement(By.cssSelector(".icon-upload")).click();
        if (driver.findElements(By.xpath("//*[contains(@id, 'All_link')]")).size() != 0){
            driver.findElement(By.xpath("//*[contains(@id, 'All_link')]")).click();
            driver.findElement(By.xpath("//*[@name='send_cond_tree']")).click();
        }
        checkingThatCatalogIsDownloaded();
    }

    public void uploadingExcelCatalog (String nameCatalog){
        try {
            driver.findElement(By.cssSelector(".catalog__actions-toggler")).click();
        }catch (Exception e){
            driver.findElement(By.cssSelector(".btn-actions")).click();
        }
        driver.findElement(By.xpath("//*[contains(@class, 'icon-download')]")).click();
        By fileInput = By.cssSelector("input[type=file]");
        String filePath = System.getProperty("user.dir")  + "\\resources\\" + nameCatalog;
        driver.findElement(fileInput).sendKeys(filePath);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class, 'file-extended')] //*[contains(@class, 'files-size')]")));
        driver.findElement(By.xpath("//*[contains(@class, 'excel')]//*[contains(text(), 'Применить')]")).click();
        try {
            driver.findElement(By.xpath("//button[contains(text(), 'Закрыть')][contains(@class, 'btn')]")).click();
        }catch (Exception e){}
    }
    public void checkThatProductsAreDisplayedInCart(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".basket__product-discrioption")));
        explicitWaiting();
        Assert.assertTrue(driver.findElement(By.cssSelector(".basket__product-discrioption")).isDisplayed());
    }
    public void checkingThatThereAreTwoGefestGasStoveInTheCart (){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".basket__product-discrioption")));
        explicitWaiting();
        Assert.assertTrue(driver.findElement(By.cssSelector(".basket__product-discrioption")).isDisplayed());
        for (int i = 1; i <= driver.findElements(By.cssSelector(".basket__item")).size(); i++) {
            if (driver.findElements(By.xpath("(//*[contains(@class, 'basket__product-name')])[" + i +"][contains(text(), 'Плита GEFEST')]")).size() > 0){
                count=i;
                break;
            }
        }
        Assert.assertTrue(driver.findElement(By.xpath("(//*[contains(@id, 'basket-item-quantity')])[" + count + "]")).getAttribute("value").equals("2"));
    }
    public void checkThatMessageAboutEmptyExcelIsDisplayed(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert-danger")));
        Assert.assertTrue(driver.findElement(By.cssSelector(".alert-danger")).isDisplayed());
        driver.findElement(By.xpath("//*[@id='modal_import_excel'] //button[@class='close']")).click();
    }
    public void addingProductToCartFromTabAddAnAdditionalProduct(){
        driver.findElement(By.xpath("(//*[@class='input-group-append'])[" + randomNumberUpToFife + "]")).click();
        explicitWaiting();explicitWaiting();explicitWaiting();explicitWaiting();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".basket__item")));
    }
    public void checkThatProductAddedFromTabAddAnAdditional (){
        Assert.assertEquals(driver.findElement(By.xpath("(//*[contains(@class, 'catalog-list__font-white-space-nowrap')])[" + randomNumberUpToFife + "]")).getText()
                ,driver.findElement(By.xpath("//*[contains(@class, 'busket__column__font-bold')]")).getText());

        Assert.assertEquals(driver.findElement(By.xpath("(//*[contains(@class, 'catalog-list__font-white-space-nowrap')])[" + randomNumberUpToFife + "]")).getText()
                ,driver.findElement(By.xpath("//*[contains(@class, 'basket-page__total-price-value')]")).getText());
    }

    public void sortsProductsByIncreaseAvailability(){
        driver.findElement(By.xpath("//*[@data-property-code = 'AVALIABLE']")).click();
        openingAllOffers();
    }
    public void sortsProductsByDecreaseAvailability(){
        driver.findElement(By.xpath("//*[@data-property-code = 'AVALIABLE']")).click();
        driver.findElement(By.xpath("//*[@data-property-code = 'AVALIABLE']")).click();
        openingAllOffers();
    }
    public void sortsProductsByIncreasePrice(){
        try {
            driver.findElement(By.xpath("//*[@data-property-code = 'BASE']")).click();
        }catch (Exception e){
            System.out.println("Нет столбца с 'BASE'");
            driver.findElement(By.xpath("//*[contains(@class, 'blank-zakaza__header-property')][contains(text(), 'Розничная цена')]")).click();
        }
        Assert.assertTrue("У 'Розничная цена' не появился флажок сортировки во убыванию", driver.findElement(By.xpath("//*[contains(@class, 'active sort-ASC')]")).isDisplayed());
        openingAllOffers();
    }
    public void sortsProductsByDecreasePrice(){
        try {
            driver.findElement(By.xpath("//*[@data-property-code = 'BASE']")).click();
            driver.findElement(By.xpath("//*[@data-property-code = 'BASE']")).click();
        }catch (Exception e){
            System.out.println("Нет столбца с 'BASE'");
            driver.findElement(By.xpath("//*[contains(@class, 'blank-zakaza__header-property')][contains(text(), 'Розничная цена')]")).click();
            driver.findElement(By.xpath("//*[contains(@class, 'blank-zakaza__header-property')][contains(text(), 'Розничная цена')]")).click();
        }
        Assert.assertTrue("У 'Розничная цена' не появился флажок сортировки во убыванию", driver.findElement(By.xpath("//*[contains(@class, 'active sort-DESC')]")).isDisplayed());
        openingAllOffers();
    }

    public void determineRandomProductOnPge(){
        determiningNumberOfProductsOnThePage();
        determiningRandomNumberOfProducts();
        if (randomNumberOfProductsPerPage==1){
            randomNumberOfProductsPerPage++;
        }
        if (randomNumberOfProductsPerPage==numberOfProductsPerPage){
            randomNumberOfProductsPerPage--;
        }
    }
    
//    public void determineRandomProductOnPge(){
//        determiningNumberOfProductsOnThePage();
//        determiningRandomNumberOfProducts();
//        numberOfOffersPerPage = driver.findElements(By.cssSelector(".product.product--offer")).size();
//        if(randomNumberOfProductsPerPage < numberOfOffersPerPage + 2){
//            randomNumberOfProductsPerPage =  numberOfOffersPerPage + 2;
//            System.out.println(randomNumberOfProductsPerPage);
//        }
//        if (randomNumberOfProductsPerPage==1){
//            randomNumberOfProductsPerPage++;
//        }
//        if (randomNumberOfProductsPerPage==numberOfProductsPerPage){
//            randomNumberOfProductsPerPage--;
//        }
//    }


    public void checkThatProductsAreSortedByIncreaseAvailability(){
        for (int i = 1; i < driver.findElements(By.cssSelector(".blank-zakaza__item")).size(); i++) {
            tempValue2 = driver.findElement(By.xpath("((//*[@class='blank-zakaza__item'])[" + i +"] //*[@class='product__property product__property--avaliable'])[1]"))
                    .getText();
            tempValue2 = replacingSomeSymbols(tempValue2);
            tempValue2 = tempValue2.replaceAll("[^0-9.]", "");
            tempValue3 = driver.findElement(By.xpath("((//*[@class='blank-zakaza__item'])[" + (i+1) +"] //*[@class='product__property product__property--avaliable'])[1]"))
                    .getText();
            tempValue3 = replacingSomeSymbols(tempValue3);
            tempValue3 = tempValue3.replaceAll("[^0-9.]", "");
            if (areThereAnyOffers == true){
                if (tempValue2 != null && !tempValue2.trim().isEmpty() && tempValue3 != null && !tempValue3.trim().isEmpty()){
                    Assert.assertTrue(Double.valueOf(tempValue2) <= Double.valueOf(tempValue3));
                }
            }else Assert.assertTrue(Double.valueOf(tempValue2) <= Double.valueOf(tempValue3));
        }
    }

    public void checkThatProductsAreSortedByDecreaseAvailability(){
        for (int i = 1; i < driver.findElements(By.cssSelector(".blank-zakaza__item")).size(); i++) {
            tempValue2 = driver.findElement(By.xpath("((//*[@class='blank-zakaza__item'])[" + i +"] //*[@class='product__property product__property--avaliable'])[1]"))
                    .getText();
            tempValue2 = replacingSomeSymbols(tempValue2);
            tempValue2 = tempValue2.replaceAll("[^0-9.]", "");
            tempValue3 = driver.findElement(By.xpath("((//*[@class='blank-zakaza__item'])[" + (i+1) +"] //*[@class='product__property product__property--avaliable'])[1]"))
                    .getText();
            tempValue3 = replacingSomeSymbols(tempValue3);
            tempValue3 = tempValue3.replaceAll("[^0-9.]", "");
            if (areThereAnyOffers == true){
                if (tempValue2 != null && !tempValue2.trim().isEmpty() && tempValue3 != null && !tempValue3.trim().isEmpty()){
                    Assert.assertTrue(Double.valueOf(tempValue2) >= Double.valueOf(tempValue3));
                }
            }else Assert.assertTrue(Double.valueOf(tempValue2) >= Double.valueOf(tempValue3));
        }
    }
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ДОПИЛИТЬ РАБОТУ С ОФФЕРАМИ
    public void checkThatProductsAreSortedByIncreasePrice(){
        for (int i = 1; i < driver.findElements(By.xpath("//*[@class='blank-zakaza__item'] /*[@class='product'] /following::*[1][not(ancestor-or-self::*[contains(@class, 'product product--offer')])] /preceding::tr[1]"))
                .size(); i++) {
            tempValue2 = driver.findElement(By.xpath("((//*[@class='blank-zakaza__item'] /*[@class='product'] /following::*[1][not(ancestor-or-self::*[contains(@class, 'product product--offer')])] /preceding::tr[1])[" + i + "] //*[@class='product__property product__property--price'])[1]"))
                    .getText();
            tempValue2 = replacingSomeSymbols(tempValue2);
            tempValue2 = tempValue2.replaceAll("[^0-9.]", "");
            tempValue3 = driver.findElement(By.xpath("((//*[@class='blank-zakaza__item'] /*[@class='product'] /following::*[1][not(ancestor-or-self::*[contains(@class, 'product product--offer')])] /preceding::tr[1])[" + (i+1) + "] //*[@class='product__property product__property--price'])[1]"))
                    .getText();
            tempValue3 = replacingSomeSymbols(tempValue3);
            tempValue3 = tempValue3.replaceAll("[^0-9.]", "");
                if (tempValue2 != null && !tempValue2.trim().isEmpty() && tempValue3 != null && !tempValue3.trim().isEmpty()){
                    if (!(Double.valueOf(tempValue2) <= Double.valueOf(tempValue3))){
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" +
                                "ОШИБКА, товары не отсортированы, Но Проверки настроены так, что бы не учитывали скидки, а проверяло только по базовой цене " +
                                "(потому что битрикс не сортирует с учетом скидки)");
                        if (driver.findElements(By.xpath("((//*[@class='blank-zakaza__item'] /*[@class='product'] /following::*[1][not(ancestor-or-self::*[contains(@class, 'product product--offer')])] /preceding::tr[1])[" + i + "] //*[@class='product__property product__property--price'])[1] //*[contains(@class, 'discount-price')]"))
                                .size() > 0){
                            tempValue2 = driver.findElement(By.xpath("((//*[@class='blank-zakaza__item'] /*[@class='product'] /following::*[1][not(ancestor-or-self::*[contains(@class, 'product product--offer')])] /preceding::tr[1])[" + i + "] //*[@class='product__property product__property--price'])[1] //*[contains(@class, 'discount-price')]"))
                                    .getText();
                            tempValue2 = replacingSomeSymbols(tempValue2);
                            tempValue2 = tempValue2.replaceAll("[^0-9.]", "");
                        }
                        if (driver.findElements(By.xpath("((//*[@class='blank-zakaza__item'] /*[@class='product'] /following::*[1][not(ancestor-or-self::*[contains(@class, 'product product--offer')])] /preceding::tr[1])[" + (i+1) + "] //*[@class='product__property product__property--price'])[1] //*[contains(@class, 'discount-price')]"))
                                .size() > 0){
                            tempValue3 = driver.findElement(By.xpath("((//*[@class='blank-zakaza__item'] /*[@class='product'] /following::*[1][not(ancestor-or-self::*[contains(@class, 'product product--offer')])] /preceding::tr[1])[" + (i+1) + "] //*[@class='product__property product__property--price'])[1] //*[contains(@class, 'discount-price')]"))
                                    .getText();
                            tempValue3 = replacingSomeSymbols(tempValue3);
                            tempValue3 = tempValue3.replaceAll("[^0-9.]", "");
                        }
                    }
                    Assert.assertTrue(Double.valueOf(tempValue2) <= Double.valueOf(tempValue3));
                }
            }
        }

    public void checkThatProductsAreSortedByDecreasePrice(){
        for (int i = 1; i < driver.findElements(By.xpath("//*[@class='blank-zakaza__item'] /*[@class='product'] /following::*[1][not(ancestor-or-self::*[contains(@class, 'product product--offer')])] /preceding::tr[1]")).size(); i++) {
            tempValue2 = driver.findElement(By.xpath("((//*[@class='blank-zakaza__item'] /*[@class='product'] /following::*[1][not(ancestor-or-self::*[contains(@class, 'product product--offer')])] /preceding::tr[1])[" + i + "] //*[@class='product__property product__property--price'])[1]"))
                    .getText();
            tempValue2 = replacingSomeSymbols(tempValue2);
            tempValue2 = tempValue2.replaceAll("[^0-9.]", "");
            tempValue3 = driver.findElement(By.xpath("((//*[@class='blank-zakaza__item'] /*[@class='product'] /following::*[1][not(ancestor-or-self::*[contains(@class, 'product product--offer')])] /preceding::tr[1])[" + i +"] //*[@class='product__property product__property--price'])[1]"))
                    .getText();
            tempValue3 = replacingSomeSymbols(tempValue3);
            tempValue3 = tempValue3.replaceAll("[^0-9.]", "");
                if (tempValue2 != null && !tempValue2.trim().isEmpty() && tempValue3 != null && !tempValue3.trim().isEmpty()){
                    if (!(Double.valueOf(tempValue2) >= Double.valueOf(tempValue3))){
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" +
                                "ОШИБКА, товары не отсортированы, Но Проверки настроены так, что если ловится ошибка по сортировке, то он проверяет есть ли у товаров цена со скидкой, " +
                                "и если есть то проверяет базовые цены этих товаров (потому что битрикс не сортирует с учетом скидки)");
                        if (driver.findElements(By.xpath("((//*[@class='blank-zakaza__item'] /*[@class='product'] /following::*[1][not(ancestor-or-self::*[contains(@class, 'product product--offer')])] /preceding::tr[1])[" + i + "] //*[@class='product__property product__property--price'])[1] //*[contains(@class, 'discount-price')]"))
                                .size() > 0){
                            tempValue2 = driver.findElement(By.xpath("((//*[@class='blank-zakaza__item'] /*[@class='product'] /following::*[1][not(ancestor-or-self::*[contains(@class, 'product product--offer')])] /preceding::tr[1])[" + i + "] //*[@class='product__property product__property--price'])[1] //*[contains(@class, 'discount-price')]"))
                                    .getText();
                            tempValue2 = replacingSomeSymbols(tempValue2);
                            tempValue2 = tempValue2.replaceAll("[^0-9.]", "");
                        }
                        if (driver.findElements(By.xpath("((//*[@class='blank-zakaza__item'] /*[@class='product'] /following::*[1][not(ancestor-or-self::*[contains(@class, 'product product--offer')])] /preceding::tr[1])[" + (i+1) + "] //*[@class='product__property product__property--price'])[1] //*[contains(@class, 'discount-price')]"))
                                .size() > 0){
                            tempValue3 = driver.findElement(By.xpath("((//*[@class='blank-zakaza__item'] /*[@class='product'] /following::*[1][not(ancestor-or-self::*[contains(@class, 'product product--offer')])] /preceding::tr[1])[" + (i+1) + "] //*[@class='product__property product__property--price'])[1] //*[contains(@class, 'discount-price')]"))
                                    .getText();
                            tempValue3 = replacingSomeSymbols(tempValue3);
                            tempValue3 = tempValue3.replaceAll("[^0-9.]", "");
                        }
                    }
                    Assert.assertTrue( Double.valueOf(tempValue2) >= Double.valueOf(tempValue3));
                }
            }
        }


    public void choicePartOfWordForSearch(){
        tempString2 = driver.findElement(By.xpath("(//*[@class='product__link'])[" + randomNumberOfProductsPerPage + "]")).getText();
        tempString =tempString2.substring(5);
    }
    public void choiceWordForSearch(){
        tempString = driver.findElement(By.xpath("(//*[@class='product__link'])[" + randomNumberOfProductsPerPage + "]")).getText();
        tempString2=tempString;
    }
    public void searchByWord(){
        driver.findElement(By.xpath("//*[@type='search']")).sendKeys(tempString);
        driver.findElement(By.xpath("//*[@type='search']")).sendKeys(Keys.ENTER);
        explicitWaiting();
        Assert.assertTrue(driver.findElement(By.cssSelector(".blank-zakaza__item")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//*[@title='" + tempString2 + "']")).isDisplayed());
        Assert.assertEquals(driver.findElement(By.xpath("//*[@placeholder='Поиск']")).getAttribute("value"), tempString);
    }
    public void goToTheLastPage(){
        try {
            driver.findElement(By.xpath("//*[@class='page-item last']")).click();
            tempValue2 = driver.findElement(By.xpath("//*[@class='page-item active']")).getText();
        }catch (Exception e){
            Assert.assertTrue(driver.findElements(By.xpath("//*[@class='page-item last']")).size() == 0);
            System.out.println("Всего одна страница!");
            tempValue2 = "1";
        }
    }
    public void rememberTheNumberOfPages(){
        tempValueForNumbers = driver.findElement(By.xpath("//*[@class='page-item active']")).getText();
    }
    public void rememberTheNumberOfProductsOnFirstPage(){
        tempInt = driver.findElements(By.cssSelector(".blank-zakaza__item")).size();
    }
    public void choiceTheCategory(){
        tempIntValue = 1 + (int) (Math.random() * driver.findElements(By.cssSelector(".catalog_section")).size());
        System.out.println("Я выбрал категорию   - " + driver.findElement(By.xpath("(//*[contains(@class, 'catalog_section')])[" + tempIntValue + "]")).getText());
        driver.findElement(By.xpath("(//*[contains(@class, 'catalog_section')])[" + tempIntValue  + "] //*[@class='form-check']")).click();
    }
    public void choiceTheSecondLevelCategory(){
        if (driver.findElements(By.xpath("//*[contains(@class, 'categorie')]")).size() > 0){
            driver.findElement(By.xpath("(//*[contains(@class, 'catalog_section')])[3]")).click();
            driver.findElement(By.xpath("(//*[contains(@class, 'catalog_section')])[3] //*[@class='form-check-label']")).click();
        }else {
            driver.findElement(By.xpath("//*[contains(@href, '/orders/blank_zakaza/')][@title='Каталог']")).click();
            explicitWaiting();
            tempRandomNumber = (1 + (int) (Math.random() * driver.findElements(By.xpath("//*[contains(@class, 'nav-item-open')]/*[contains(@class, 'nav-group-sub')] /li /a /span")).size()));
            driver.findElement(By.xpath("(//*[contains(@class, 'nav-item-open')]/*[contains(@class, 'nav-group-sub')] /li /a /span)[" + tempRandomNumber + "]")).click();
            tempRandomNumber = (1 + (int) (Math.random() * driver.findElements(By.xpath("(//*[contains(@class, 'nav-item-open')])[last()] /ul /li")).size()));
            System.out.println(tempRandomNumber);
            driver.findElement(By.xpath("((//*[contains(@class, 'nav-item-open')])[last()] /ul /li)[" + tempRandomNumber + "]")).click();
        }
    }
    public void checkThatTheQuantityOfPagesIsChanged(){
        Assert.assertNotEquals(tempValueForNumbers, tempValue2);
    }
    public void enteringTheMaxPriceIntoTheFilter() {

        //maxPriceForFiltering = replacingSomeSymbols(driver.findElement(By.xpath("(//*[contains(@id, 'price_BASE')])[7]")).getText());
        driver.findElement(By.cssSelector(".max-price")).sendKeys(maxPriceForFiltering);
        try{
            driver.findElement(By.cssSelector("#set_filter")).click();
        }catch (Exception e){
            driver.findElement(By.cssSelector("#set_filter")).click();
        }
    }

    public void enteringTheMinPriceIntoTheFilter() {
        //minPriceForFiltering = replacingSomeSymbols(driver.findElement(By.xpath("(//*[contains(@id, 'price_BASE')])[3]")).getText()));
        driver.findElement(By.cssSelector(".min-price")).sendKeys(minPriceForFiltering);
        try {
            driver.findElement(By.cssSelector("#set_filter")).click();
        } catch (Exception e) {
            driver.findElement(By.cssSelector("#set_filter")).click();
        }
    }
    public void checkThatMaxPriceHasBeenApplied(){
        for (int i = 1; i < driver.findElements(By.xpath("//*[@class='blank-zakaza__item'] /*[@class='product'] /following::*[1][not(ancestor-or-self::*[contains(@class, 'product product--offer')])] /preceding::tr[1]")).size(); i++) {
            tempValue2 = driver.findElement(By.xpath("((//*[@class='blank-zakaza__item'] /*[@class='product'] /following::*[1][not(ancestor-or-self::*[contains(@class, 'product product--offer')])] /preceding::tr[1])[" + i + "] //*[@class='product__property product__property--price'])[1]"))
                    .getText();
            tempValue2 = replacingSomeSymbols(tempValue2);
            tempValue2 = tempValue2.replaceAll("[^0-9.]", "");
            Assert.assertTrue(Double.valueOf(tempValue2) <= Double.valueOf(maxPriceForFiltering));


//            tempValue3 = driver.findElement(By.xpath("((//*[@class='blank-zakaza__item'])[" + (i+1) + "] //*[@class='product__property product__property--price'])[1]"))
//                    .getText();
//            tempValue3 = replacingSomeSymbols(tempValue3);
//            tempValue3 = tempValue3.replaceAll("[^0-9.]", "");
//            if (areThereAnyOffers == true){
//                if (tempValue2 != null && !tempValue2.trim().isEmpty() && tempValue3 != null && !tempValue3.trim().isEmpty()){
//                    Assert.assertTrue(Double.valueOf(tempValue2) >= Double.valueOf(tempValue3));
//                    Assert.assertTrue(Double.valueOf(tempValue2) <= Double.valueOf(maxPriceForFiltering));
//                    Assert.assertTrue(Double.valueOf(tempValue3) <= Double.valueOf(maxPriceForFiltering));
//                }
//            }else {
//                Assert.assertTrue(Double.valueOf(tempValue2) >= Double.valueOf(tempValue3));
//                Assert.assertTrue(Double.valueOf(tempValue2) <= Double.valueOf(maxPriceForFiltering));
//                Assert.assertTrue(Double.valueOf(tempValue3) <= Double.valueOf(maxPriceForFiltering));
//            }
        }

//        Assert.assertTrue(Double.valueOf(replacingSomeSymbols(driver.findElement(By.xpath("(//*[contains(@id, 'price_BASE')])[1]")).getText()))
//                <= Double.valueOf(maxPriceForFiltering));
//
//        Assert.assertTrue(Double.valueOf(replacingSomeSymbols(replacingSomeSymbols(driver.findElement(By.xpath("(//*[contains(@id, 'price_BASE')])[1]")).getText())))
//                >= Double.valueOf(replacingSomeSymbols(replacingSomeSymbols(driver.findElement(By.xpath("(//*[contains(@id, 'price_BASE')])[1]")).getText()))));
    }
    public void checkThatMinPriceHasBeenApplied(){
        for (int i = 1; i < driver.findElements(By.xpath("//*[@class='blank-zakaza__item'] /*[@class='product'] /following::*[1][not(ancestor-or-self::*[contains(@class, 'product product--offer')])] /preceding::tr[1]")).size(); i++) {
            tempValue2 = driver.findElement(By.xpath("((//*[@class='blank-zakaza__item'] /*[@class='product'] /following::*[1][not(ancestor-or-self::*[contains(@class, 'product product--offer')])] /preceding::tr[1])[" + i + "] //*[@class='product__property product__property--price'])[1]"))
                    .getText();
            tempValue2 = replacingSomeSymbols(tempValue2);
            tempValue2 = tempValue2.replaceAll("[^0-9.]", "");
            Assert.assertTrue(Double.valueOf(tempValue2) >= Double.valueOf(minPriceForFiltering));



//            tempValue3 = driver.findElement(By.xpath("((//*[@class='blank-zakaza__item'])[" + (i+1) + "] //*[@class='product__property product__property--price'])[1]"))
//                    .getText();
//            tempValue3 = replacingSomeSymbols(tempValue3);
//            tempValue3 = tempValue3.replaceAll("[^0-9.]", "");
//            if (areThereAnyOffers == true){
//                if (tempValue2 != null && !tempValue2.trim().isEmpty() && tempValue3 != null && !tempValue3.trim().isEmpty()){
//                    Assert.assertTrue(Double.valueOf(tempValue2) <= Double.valueOf(tempValue3));
//
//                    Assert.assertTrue(Double.valueOf(tempValue3) >= Double.valueOf(minPriceForFiltering));
//                }
//            }else {
//                Assert.assertTrue(Double.valueOf(tempValue2) <= Double.valueOf(tempValue3));
//                Assert.assertTrue(Double.valueOf(tempValue2) >= Double.valueOf(minPriceForFiltering));
//                Assert.assertTrue(Double.valueOf(tempValue3) >= Double.valueOf(minPriceForFiltering));
//            }
        }
    }
    public void changePageOnTheSecond(){
        try{
            driver.findElement(By.xpath("(//*[@class='page-item'])[1]")).click();
        }catch (Exception e){
            System.out.println("Я не нашел второй страницы");
        }
    }
    public void changePageOnTheFifth(){
        driver.findElement(By.xpath("//*[@class='page-link'][text()='5']")).click();
    }

    //    public void determineFlag(){
//        try {
//            driver.findElement(By.cssSelector(".quantity-selector__increment")).click();
//            flag=false;
//        } catch (Exception e) {flag = true;}
//    }
    public void checkThatDetailPageOfProductIsOpened(){
        Assert.assertTrue("!!!! Блок с детальной картинкой не отображается", driver.findElement( By.cssSelector(".blank-zakaza-detail__image")).isDisplayed());
        Assert.assertTrue("!!!! Блок добавления (удаления) в корзину не отображается", driver.findElement(By.cssSelector(".quantity-selector")).isDisplayed());
        Assert.assertTrue("!!!! Описание не отображается", driver.findElement(By.xpath("//h2[@class='card-title'][contains(text(), 'Описание')]")).isDisplayed());
        Assert.assertTrue("!!!! Характеристики не отображаются", driver.findElement(By.xpath("//h2[@class='card-title'][contains(text(), 'Характеристики')]")).isDisplayed());
    }
    public void openDetailPageOfRandomProduct(){
        explicitWaiting();
        driver.findElement(By.xpath("(//*[@class='product__link'])[" + randomNumberOfProductsPerPage + "]")).click();
        By locIframe = By.cssSelector(".side-panel-iframe");
        driver.switchTo().frame(driver.findElement(locIframe));
    }
    public void addingProductFromPopUpDetailPage(){
        driver.findElement(By.cssSelector(".quantity-selector__increment")).click();
        tempString = driver.findElement(By.cssSelector(".blank-zakaza-detail__title")).getText();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".b2b-notification__content")));
    }
    public void checkingThatThereIsProductInTheCartThatWasAddedFromTheDetailedProductPage(){
        Assert.assertTrue("В коризне нет товара который добавлялся из поп-ап деталки товара", driver.findElement(By.xpath("//*[contains(text(), '" + tempString + "')]")).isDisplayed());
    }
    public void navigationToComponentOfUnloadingTheCatalog(){
        driver.findElement(By.xpath("//*[contains(@id, 'components')] //*[@class='bx-panel-small-single-button-arrow']")).click();
        driver.findElement(By.xpath("//*[text()='Выгрузка каталога в файл excel']")).click();
        driver.findElement(By.xpath("//*[@title='Развернуть']")).click();
    }
    public void navigationToComponentOfUserOrders(){
        driver.findElement(By.xpath("//*[contains(@id, 'components')] //*[@class='bx-panel-small-single-button-arrow']")).click();
        driver.findElement(By.xpath("//*[contains(@title, 'company.order')]")).click();
        driver.findElement(By.xpath("//*[@title='Развернуть']")).click();
    }
    public void navigationToComponentOfCatalogSetting(){
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@id, 'components')] //*[@class='bx-panel-small-single-button-arrow']")));
        }catch (Exception e){
            driver.findElement(By.cssSelector("#bx-panel-expander-arrow")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@id, 'components')] //*[@class='bx-panel-small-single-button-arrow']")));
        }
        driver.findElement(By.xpath("//*[contains(@id, 'components')] //*[@class='bx-panel-small-single-button-arrow']")).click();
        explicitWaiting();
        try {
            driver.findElement(By.xpath("//*[contains(@class, 'popup-menu-item-text')][text()='Каталог']")).click();
        }catch (Exception e){
            enableEditMode();
            driver.findElement(By.xpath("//*[contains(@id, 'components')] //*[@class='bx-panel-small-single-button-arrow']")).click();
            driver.findElement(By.xpath("//*[contains(@class, 'popup-menu-item-text')][text()='Каталог']")).click();
        }
        explicitWaiting();
        driver.findElement(By.xpath("//*[@title='Развернуть']")).click();
    }
    public void selectTheWorkModelAsUserConfigurable(){
        driver.findElement(By.xpath("//*[contains(@for, 'MODEL_OF_WORK')] / following::*[1]")).click();
        driver.findElement(By.xpath("//*[@value= 'user_config']")).click();
        driver.findElement(By.xpath("//*[contains(@id, 'save-button')]")).click();
    }
    public void selectTheNumberOfDisplayedOrdersOnThePage(){
        randomNumberPage = randomNumberUpToNine;
        System.out.println("Выбранное кол-во заказов на странице - " + randomNumberPage);
        driver.findElement(By.xpath("//*[@name='ORDERS_PER_PAGE']")).clear();
        driver.findElement(By.xpath("//*[@name='ORDERS_PER_PAGE']")).sendKeys(String.valueOf(randomNumberPage));
        driver.findElement(buttonSaveLocator).click();
        explicitWaiting();
    }
    public void selectTheNumberOfDisplayedOrdersOnThePageIsEquallyThirty(){
        driver.findElement(By.xpath("//*[@name='ORDERS_PER_PAGE']")).clear();
        driver.findElement(By.xpath("//*[@name='ORDERS_PER_PAGE']")).sendKeys("30");
        driver.findElement(buttonSaveLocator).click();
    }

    public void selectRandomSectionToDownloadToTheCatalog(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".catalog__actions-toggler")));
        explicitWaiting();
        driver.findElement(By.cssSelector(".catalog__actions-toggler")).click();
        driver.findElement(By.cssSelector(".icon-upload")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@id,'add_link')]")));
        driver.findElement(By.xpath("//*[contains(@id,'add_link')]")).click();
        driver.findElement(By.xpath("//*[@value='CondIBSection']")).click();
        driver.findElement(By.xpath("//*[contains(@id,'value_link')]")).click();
        selectingRandomCatalogFromPupOpWindow();
    }
    public void selectingRandomCatalogFromPupOpWindow (){
        Set<String> handles = driver.getWindowHandles();
        Iterator<String> itr = handles.iterator();
        String parentWindow = itr.next();
        String newWindow = itr.next();
        driver.switchTo().window(newWindow);
        driver.findElement(By.xpath("//*[@id='find_type']")).click();
        driver.findElement(By.xpath("//*[@value='sotbit_b2bcabinet_type_catalog']")).click();
        driver.findElement(By.xpath("//*[@id='find_iblock_id']")).click();
        driver.findElement(By.xpath("//*[@id='find_iblock_id'] //*[contains(text(), 'Каталог товаров')]")).click();
        tempInt = 1 + (int) (Math.random() * driver.findElements(By.xpath("//*[@class='adm-list-table-popup']")).size());
        driver.findElement(By.xpath("(//*[@class='adm-list-table-popup'])["+ tempInt + "]")).click();
        driver.findElement(By.xpath("//span[contains(text(), 'Выбрать')]")).click();
        driver.switchTo().window(parentWindow); //вернуть фокус на старое окно
    }
    public void clickTheUploadButton(){
        driver.findElement(By.xpath("//*[@name='send_cond_tree']")).click();
    }
    public void checkingThatCatalogIsDownloaded(){
        String downloadPath = System.getProperty("user.home") + "/Downloads/";
        explicitWaiting();explicitWaiting();
        Assert.assertTrue("Failed to download document which has extension .xlsx", isFileDownloaded_Ext(downloadPath, ".xlsx"));
    }
    public void sortingOfProductsInAlphabeticalIncreasingOrder (){
        driver.findElement(By.xpath("//*[contains(@class, 'blank-zakaza__header-property--name')]")).click();
        if (driver.findElements(By.xpath("//*[contains(@class, 'blank-zakaza__header-property--name')][contains(@class, 'active sort-ASC')]")).size() == 0){
            driver.findElement(By.xpath("//*[contains(@class, 'blank-zakaza__header-property--name')]")).click();
        }
        if (driver.findElements(By.xpath("//*[contains(@class, 'blank-zakaza__header-property--name')][contains(@class, 'active sort-ASC')]")).size() == 0){
            driver.findElement(By.xpath("//*[contains(@class, 'blank-zakaza__header-property--name')]")).click();
        }
        Assert.assertTrue("Значок что товары отсортированы по Алфавиту (возрастанию) не отображается"
                , driver.findElement(By.xpath("//*[contains(@class, 'blank-zakaza__header-property--name')][contains(@class, 'active sort-ASC')]")).isDisplayed());
    }
    public void sortingOfProductsInAlphabeticalDescendingOrder (){
        driver.findElement(By.xpath("//*[contains(@class, 'blank-zakaza__header-property--name')]")).click();
        if (driver.findElements(By.xpath("//*[contains(@class, 'blank-zakaza__header-property--name')][contains(@class, 'active sort-DESC')]")).size() == 0){
            driver.findElement(By.xpath("//*[contains(@class, 'blank-zakaza__header-property--name')]")).click();
        }
        if (driver.findElements(By.xpath("//*[contains(@class, 'blank-zakaza__header-property--name')][contains(@class, 'active sort-DESC')]")).size() == 0){
            driver.findElement(By.xpath("//*[contains(@class, 'blank-zakaza__header-property--name')]")).click();
        }
        Assert.assertTrue("Значок что товары отсортированы по Алфавиту (возрастанию) не отображается"
                , driver.findElement(By.xpath("//*[contains(@class, 'blank-zakaza__header-property--name')][contains(@class, 'active sort-DESC')]")).isDisplayed());
    }

    public void checkingThatCatalogIsSortedByProductNamesIncrease (){
        for (int i = 1; i < driver.findElements(By.cssSelector(".blank-zakaza__item")).size(); i++) {
            tempInt = driver.findElement(By.xpath("(//*[@class='blank-zakaza__item'] //*[@class='product'] //*[@class='product__link'])[" + i + "]")).getText().substring(0, 5)
                    .compareTo(
                            driver.findElement(By.xpath("(//*[@class='blank-zakaza__item'] //*[@class='product'] //*[@class='product__link'])[" + (i+1) + "]")).getText().substring(0, 5)
                    );
            Assert.assertTrue("Товары не отсортированы по алфавиту (возрастанию)", tempInt<=0);
        }
    }
    public void checkingThatURLContainsSORTAndASC(){
        Assert.assertTrue(driver.getCurrentUrl().contains("SORT"));
        Assert.assertTrue(driver.getCurrentUrl().contains("asc"));
    }
    public void checkingThatURLContainsSORTAndDESC(){
        Assert.assertTrue(driver.getCurrentUrl().contains("SORT"));
        Assert.assertTrue(driver.getCurrentUrl().contains("desc"));
    }

    public void checkingThatCatalogIsSortedByProductNamesDecrease (){
        for (int i = 1; i < driver.findElements(By.cssSelector(".blank-zakaza__item")).size(); i++) {
            tempInt = driver.findElement(By.xpath("(//*[@class='blank-zakaza__item'] //*[@class='product'] //*[@class='product__link'])[" + i + "]")).getText().substring(0, 5)
                    .compareTo(
                            driver.findElement(By.xpath("(//*[@class='blank-zakaza__item'] //*[@class='product'] //*[@class='product__link'])[" + (i+1) + "]")).getText().substring(0, 5)
                    );
            Assert.assertTrue("Товары не отсортированы по алфавиту (возрастанию)", tempInt>=0);
        }
    }
    public void checkingThatSelectedSecondPage(){
        Assert.assertTrue("Я выбирал страницу №2, а выбралась другая(", driver.findElement(By.cssSelector(".page-item.active")).getText().equals("2"));
        Assert.assertTrue(driver.getCurrentUrl().contains("=2"));
        tempString =  driver.findElement(By.xpath("(//*[@class='blank-zakaza__item'] //*[@class='product__link'])[1]")).getAttribute("id");
    }
    public void checkingThatSelectedFifthPage(){
        Assert.assertTrue("Я выбирал страницу №5, а выбралась другая(", driver.findElement(By.cssSelector(".page-item.active")).getText().equals("5"));
        Assert.assertTrue(driver.getCurrentUrl().contains("=5"));
        Assert.assertFalse(tempString.equals(driver.findElement(By.xpath("(//*[@class='blank-zakaza__item'] //*[@class='product__link'])[1]")).getAttribute("id")));
    }
    public void choosingRandomCategory(){
        tempInt = driver.findElements(By.cssSelector(".item_name")).size();
        if (driver.findElements(By.xpath("//*[contains(@class, 'categorie')]")).size() > 0){
            tempRandomNumber = (1 + (int) (Math.random() * driver.findElements(By.xpath("//*[contains(@class, 'catalog_section')]")).size()));
            driver.findElement(By.xpath("(//*[contains(@class, 'catalog_section')])[" + tempRandomNumber +"]//*[@class='form-check']")).click();
        }else {
            driver.findElement(By.xpath("//*[contains(@href, '/orders/blank_zakaza/')][@title='Каталог']")).click();
            explicitWaiting();
            tempRandomNumber = (1 + (int) (Math.random() * driver.findElements(By.xpath("//*[contains(@class, 'nav-item-open')]/*[contains(@class, 'nav-group-sub')] /li /a /span")).size()));
            driver.findElement(By.xpath("(//*[contains(@class, 'nav-item-open')]/*[contains(@class, 'nav-group-sub')] /li /a /span)[" + tempRandomNumber + "]")).click();
        }
    }

    public void checkingThatQuantityOfPropertiesIsHadDecreased(){
        explicitWaiting();explicitWaiting();
        Assert.assertTrue("Количество свойств в рандомной разделе больше или равно чем во всех разделах"
                ,tempInt > driver.findElements(By.cssSelector(".item_name")).size());
    }
    public void navigationToSystemSettings(){
        driver.navigate().to(b2bUrl.replaceAll("b2bcabinet/" , "") + "bitrix/admin/settings.php?lang=ru&mid=iblock&mid_menu=1");
    }
    public void enableOutputOfPropertiesInTheDirectory(){
        if (driver.findElements(By.xpath("//*[@id='property_features_enabled'][@checked]")).size() == 0){
            driver.findElement(By.xpath("//*[@for='property_features_enabled'][@class]")).click();
        }
        driver.findElement(By.xpath("//*[@type='submit'][contains(@class,'save')]")).click();
    }
    public void disableOutputOfPropertiesInTheDirectory (){
        if (driver.findElements(By.xpath("//*[@id='property_features_enabled'][@checked]")).size() > 0){
            driver.findElement(By.xpath("//*[@for='property_features_enabled'][@class]")).click();
        }
        driver.findElement(By.xpath("//*[@type='submit'][contains(@class,'save')]")).click();
    }
    public void navigationToGasStoveSetting(){
        driver.findElement(By.xpath("//*[@class='adm-main-menu-item-text'][text()='Контент']")).click();
        try {
            driver.findElement(By.xpath("//*[@class='adm-submenu-item-name-link-text'][text()='Каталог (sotbit.b2bcabinet)']")).click();
            driver.findElement(By.xpath("//*[@class='adm-submenu-item-name-link-text'][text()='Каталог товаров']")).click();
            driver.findElement(By.xpath("//*[@class='adm-submenu-item-name-link-text'][text()='Бытовая техника']")).click();
            driver.findElement(By.xpath("//*[@class='adm-submenu-item-name-link-text'][text()='Кухонные плиты']")).click();
        }catch (Exception e) {

            try {
                driver.findElement(By.xpath("//*[@class='adm-submenu-item-name-link-text'][text()='Кухонные плиты']")).click();
            } catch (Exception e4) {
                try {
                    driver.findElement(By.xpath("//*[@class='adm-submenu-item-name-link-text'][text()='Бытовая техника']")).click();
                    driver.findElement(By.xpath("//*[@class='adm-submenu-item-name-link-text'][text()='Кухонные плиты']")).click();
                } catch (Exception e1) {
                    try {
                        driver.findElement(By.xpath("//*[@class='adm-submenu-item-name-link-text'][text()='Каталог товаров']")).click();
                        driver.findElement(By.xpath("//*[@class='adm-submenu-item-name-link-text'][text()='Бытовая техника']")).click();
                        driver.findElement(By.xpath("//*[@class='adm-submenu-item-name-link-text'][text()='Кухонные плиты']")).click();
                    } catch (Exception e2) {
                        try {
                            driver.findElement(By.xpath("//*[@class='adm-submenu-item-name-link-text'][text()='Каталог товаров']")).click();
                            driver.findElement(By.xpath("//*[@class='adm-submenu-item-name-link-text'][text()='Бытовая техника']")).click();
                            driver.findElement(By.xpath("//*[@class='adm-submenu-item-name-link-text'][text()='Кухонные плиты']")).click();
                        } catch (Exception e3) {
                            driver.findElement(By.xpath("//*[@class='adm-submenu-item-name-link-text'][text()='Каталог (sotbit.b2bcabinet)']")).click();
                            driver.findElement(By.xpath("//*[@class='adm-submenu-item-name-link-text'][text()='Каталог товаров']")).click();
                            driver.findElement(By.xpath("//*[@class='adm-submenu-item-name-link-text'][text()='Бытовая техника']")).click();
                            driver.findElement(By.xpath("//*[@class='adm-submenu-item-name-link-text'][text()='Кухонные плиты']")).click();
                        }
                    }
                }
            }
        }
    }
    public void addRegionPropertyToThisProduct(){
        driver.findElement(By.xpath("//a[contains(text(), 'Плита GEFEST')]")).click();
        driver.findElement(By.xpath("//*[text()='Регионы:']/following::*[1] //input")).clear();
        tempString = randomString(10);
        driver.findElement(By.xpath("//*[text()='Регионы:']/following::*[1] //input")).sendKeys(tempString);
        driver.findElement(By.cssSelector("#save")).click();
    }
    public void configureOutputOfThisPropertyToTheCatalog (){
        driver.findElement(By.xpath("//*[contains(text(), 'Настройках информационного блока')]")).click();
        driver.findElement(By.xpath("//*[@class='adm-detail-tab'][contains(text(), 'Свойства')]")).click();
        driver.findElement(By.xpath("//*[@value='Регионы']/following::td[7]")).click();
        driver.findElement(By.xpath("//*[@title='Развернуть']")).click();
        if (driver.findElements(By.xpath("//*[contains(text(), 'Показывать на странице списка элементов')]/following-sibling::* //*[@checked]")).size() == 0){
            driver.findElement(By.xpath("//*[contains(text(), 'Показывать на странице списка элементов')]/following::label[1]")).click();
        }
        driver.findElement(By.cssSelector("#savebtn")).click();
        driver.findElement(By.xpath("//*[@value='Регионы']/following::td[7]")).click();
        driver.findElement(By.xpath("//*[@title='Развернуть']")).click();
        Assert.assertTrue(driver.findElements(By.xpath("//*[contains(text(), 'Показывать на странице списка элементов')]/following-sibling::* //*[@checked]")).size() > 0);
        driver.findElement(By.cssSelector("#savebtn")).click();
        scrollToTheElement("//*[@class='adm-btn-save']");
        driver.findElement(By.cssSelector(".adm-btn-save")).click();
    }
    public void selectTheSectionWithGasStoves(){
        if (driver.findElements(By.xpath("//*[contains(@class, 'categorie')]")).size() > 0){
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@class='nav-item nav-item-submenu catalog_section '])[1]")));
            driver.findElement(By.xpath("(//*[@class='nav-item nav-item-submenu catalog_section '])[1]")).click();
            explicitWaiting();
            driver.findElement(By.xpath("(//*[@class='nav-item nav-item-submenu'])[1]")).click();
            explicitWaiting();
        }else {
            try {
                driver.findElement(By.xpath("//*[contains(@href, '/orders/blank_zakaza/')][@title='Каталог']")).click();
                explicitWaiting();
                driver.findElement(By.xpath("(//*[contains(@class, 'nav-item-open')] //span)[2]")).click();
                explicitWaiting();
                driver.findElement(By.xpath("((//*[contains(@class, 'nav-item-open')])[last()] //span)[2]")).click();
            }catch (Exception e){
                navigationToCatalogTab();
                driver.findElement(By.xpath("//*[contains(@href, '/orders/blank_zakaza/')][@title='Каталог']")).click();
                explicitWaiting();
                driver.findElement(By.xpath("(//*[contains(@class, 'nav-item-open')] //span)[2]")).click();
                explicitWaiting();
                driver.findElement(By.xpath("((//*[contains(@class, 'nav-item-open')])[last()] //span)[2]")).click();
            }
        }
    }
    public void selectSectionWithChainsaws(){
        if (driver.findElements(By.xpath("//*[contains(@class, 'categorie')]")).size() > 0){
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@class='nav-item nav-item-submenu catalog_section '])[2]")));
            driver.findElement(By.xpath("(//*[@class='nav-item nav-item-submenu catalog_section '])[2]")).click();
            explicitWaiting();
            driver.findElement(By.xpath("(//*[contains(@class, 'card-body border')]//*[@type='checkbox'])[6]")).click();
        }else {
            driver.findElement(By.xpath("//*[contains(@href, '/orders/blank_zakaza/')][@title='Каталог']")).click();
            explicitWaiting();
            driver.findElement(By.xpath("(//*[contains(@class, 'nav-item-open')]/*[contains(@class, 'nav-group-sub')] /li /a /span)[2]")).click();
            explicitWaiting();
            driver.findElement(By.xpath("((//*[contains(@class, 'nav-item-open')])[last()] //span)[2]")).click();
        }
    }
    public void checkingThatTheColumnWithThisPropertyHasAppearedInTheCatalog (){
        Assert.assertTrue("",driver.findElement(By.xpath("//th[contains(text(), 'егион')]")).isDisplayed());
    }
    public void checkingThatTheProductHasAPropertyWithThePreviouslyEnteredValue (){
        tempInt = 1;
        flag = false;
        while (flag == false){
            if (driver.findElement(By.xpath("(//*[contains(@class, 'blank-zakaza__header-property')])[" + tempInt +"]")).getText().replaceAll(" ", "")
                    .equals("Регионы")){
                flag = true;
            }else {
                tempInt++;
                if (tempInt >20) break;
            }
        }
        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(@title, 'Плита GEFEST')] /following::td[" + (tempInt-2) +"]")).getText().replaceAll(" ", "").equals(tempString));
    }
    public void removeAllColumnsWithPropertiesFromTheCatalog (){
        for (int i = 2; i <= driver.findElements(By.xpath("(//*[@data-bx-property-id='LIST_PROPERTY_CODE'] //option)")).size(); i++) {
            if (driver.findElement(By.xpath("(//*[@data-bx-property-id='LIST_PROPERTY_CODE'] //option)[" + i + "]")).isSelected()){
                driver.findElement(By.xpath("(//*[@data-bx-property-id='LIST_PROPERTY_CODE'] //option)[" + i + "]")).click();
                explicitWaiting();explicitWaiting();
            }
        }
        driver.findElement(buttonToSaveTheComponentSettingsForTheCatalog).click();
    }
    public void selectFewPropertiesToOutputToTheCatalog(){
        for (int i = 2; i <= driver.findElements(By.xpath("(//*[@data-bx-property-id='LIST_PROPERTY_CODE'] //option)")).size(); i++) {
            if (driver.findElement(By.xpath("(//*[@data-bx-property-id='LIST_PROPERTY_CODE'] //option)[" + i + "]")).getText().contains("Производитель")
                    || driver.findElement(By.xpath("(//*[@data-bx-property-id='LIST_PROPERTY_CODE'] //option)[" + i + "]")).getText().contains("Артикул")
                    || driver.findElement(By.xpath("(//*[@data-bx-property-id='LIST_PROPERTY_CODE'] //option)[" + i + "]")).getText().contains("Реквизиты")){
                driver.findElement(By.xpath("(//*[@data-bx-property-id='LIST_PROPERTY_CODE'] //option)[" + i + "]")).click();
                explicitWaiting();
            }
        }
        driver.findElement(buttonToSaveTheComponentSettingsForTheCatalog).click();
    }
    public void checkingThatSelectedPropertiesAreDisplayedInTheCatalog(){
        Assert.assertTrue("",driver.findElement(By.xpath("//th[contains(text(), 'роизводитель')]")).isDisplayed());
        Assert.assertTrue("",driver.findElement(By.xpath("//th[contains(text(), 'ртикул')]")).isDisplayed());
        Assert.assertTrue("",driver.findElement(By.xpath("//th[contains(text(), 'еквизиты')]")).isDisplayed());
    }
    public void chooseToDisplaySmallOptPricesForAllUsers (){
        driver.navigate().to(b2bUrl.replaceAll("b2bcabinet/" , "") + "/bitrix/admin/cat_group_admin.php?lang=ru");
        driver.findElement(By.xpath("//*[text()='SMALL_OPT']/preceding::label[1] /following::*[1]")).click();
        driver.findElement(By.xpath("//*[text()='Изменить тип цен']")).click();
        for (int i = 1; i <= driver.findElements(By.xpath("//*[contains(text(),'Все пользователи')]")).size(); i++) {
            if (!driver.findElement(By.xpath("(//*[contains(text(),'Все пользователи')])["+ i + "]")).isSelected()){
                driver.findElement(By.xpath("(//*[contains(text(),'Все пользователи')])["+ i + "]")).click();
            }
        }
        driver.findElement(By.cssSelector(".adm-btn-save")).click();
    }
    public void setPriceForSmallOptForGasStoveGefest(){
        driver.findElement(By.xpath("//a[contains(text(), 'Плита GEFEST')]")).click();
        driver.findElement(By.xpath("//*[contains(text(), 'Торговый каталог')][contains(@class, 'adm-detail-tab')]")).click();
        driver.findElement(By.xpath("//td[contains(text(), 'SMALL_OPT')] /following::input[1][not(ancestor-or-self::*[@style = 'display: none;'])]")).clear();
        tempDouble = Double.parseDouble(driver.findElement(By.xpath("//input[contains(@id, 'BASE_PRICE')][not(ancestor-or-self::*[@style = 'display: none;'])]")).getAttribute("value")) - 1;
        driver.findElement(By.xpath("//td[contains(text(), 'SMALL_OPT')] /following::input[1][not(ancestor-or-self::*[@style = 'display: none;'])]")).sendKeys(String.valueOf(tempDouble));
        driver.findElement(By.xpath("//*[@title='Управление скидками']")).click();
        if (driver.findElements(By.xpath("//td[text()='ID']")).size() > 0){
            tempDouble2 = tempDouble * Double.valueOf(driver.findElement(By.xpath("(//td[contains(@style, 'text-align:')])[5]")).getText().replaceAll("%", "")) / 100;
            tempDouble = tempDouble - tempDouble2;
            tempDouble = Math.round(tempDouble *100.0) / 100.0;
            System.out.println("Цена со скидкой для SMALLOPT =  " + tempDouble);
        }
        driver.findElement(By.cssSelector(".adm-btn-save")).click();
    }
    public void settingTheMultiplicityForTheGefestGasStove (){
        driver.findElement(By.xpath("//a[contains(text(), 'Плита GEFEST')]")).click();
        driver.findElement(By.xpath("//*[contains(text(), 'Торговый каталог')][contains(@class, 'adm-detail-tab')]")).click();
        driver.findElement(By.xpath("//*[@title='Дополнительные параметры']")).click();
        driver.findElement(By.xpath("//*[contains(@id, 'MEASURE_RATIO')]")).clear();
        driver.findElement(By.xpath("//*[contains(@id, 'MEASURE_RATIO')]")).sendKeys("0.1");
        driver.findElement(By.cssSelector(".adm-btn-save")).click();
    }
    public void settingTheMultiplicityForTheKaiserGasStove(){
        driver.findElement(By.xpath("//a[contains(text(), 'Плита Kaiser')]")).click();
        driver.findElement(By.xpath("//*[contains(text(), 'Торговый каталог')][contains(@class, 'adm-detail-tab')]")).click();
        driver.findElement(By.xpath("//*[@title='Дополнительные параметры']")).click();
        driver.findElement(By.xpath("//*[contains(@id, 'MEASURE_RATIO')]")).clear();
        driver.findElement(By.xpath("//*[contains(@id, 'MEASURE_RATIO')]")).sendKeys("0.1");
        driver.findElement(By.cssSelector(".adm-btn-save")).click();
    }
    public void removingImagesFromTheGefestGasStove(){
        driver.findElement(By.xpath("//a[contains(text(), 'Плита GEFEST')]")).click();
        try {
            driver.findElement(By.xpath("//*[contains(@class, 'adm-btn-del')]")).click();
        }catch (Exception e){
            scrollDown();
        }try {
            driver.findElement(By.xpath("//*[contains(@class, 'adm-btn-del')]")).click();
        }catch (Exception e){}
        explicitWaiting();
        try {
            driver.findElement(By.xpath("//*[contains(@class, 'adm-btn-del')]")).click();
        }catch (Exception e){
            scrollDown();
        }
        try {
            driver.findElement(By.xpath("//*[contains(@class, 'adm-btn-del')]")).click();
        }catch (Exception e){}
        driver.findElement(By.cssSelector(".adm-btn-save")).click();
    }
    public void returningImagesToTheGefestGasStoveBack() {
        driver.findElement(By.xpath("//a[contains(text(), 'Плита GEFEST')]")).click();
        String filePath = System.getProperty("user.dir")  + "\\resources\\Gefest.jpg";
        driver.findElement(By.xpath("(//input[@type='file'])[1]")).sendKeys(filePath);
        driver.findElement(By.xpath("(//input[@type='file'])[2]")).sendKeys(filePath);
        explicitWaiting();
        driver.findElement(By.cssSelector(".adm-btn-save")).click();
    }
    public void settingBackTheMultiplicityForTheGefestGasStove (){
        driver.findElement(By.xpath("//a[contains(text(), 'Плита GEFEST')]")).click();
        driver.findElement(By.xpath("//*[contains(text(), 'Торговый каталог')][contains(@class, 'adm-detail-tab')]")).click();
        driver.findElement(By.xpath("//*[@title='Дополнительные параметры']")).click();
        driver.findElement(By.xpath("//*[contains(@id, 'MEASURE_RATIO')]")).clear();
        driver.findElement(By.xpath("//*[contains(@id, 'MEASURE_RATIO')]")).sendKeys("1");
        driver.findElement(By.cssSelector(".adm-btn-save")).click();
    }
    public void findNumberOfGefestInCatalogInCatalog (){
        for (int i = 1; i < driver.findElements(By.cssSelector(".product")).size(); i++) {
            if (driver.findElements(By.xpath("(//*[@class='product'])[" + i + "]//*[contains(@title, 'Плита GEFEST')]")).size() > 0){
                count=i;
                break;
            }
        }
    }
    public void findNumberOfKaiserInCatalogInCatalog (){
        for (int i = 1; i < driver.findElements(By.cssSelector(".product")).size(); i++) {
            if (driver.findElements(By.xpath("(//*[@class='product'])[" + i + "]//*[contains(@title, 'Плита Kaiser')]")).size() > 0){
                count=i;
                break;
            }
        }
    }
    public void checkingThatTheGefestGasStoveHasTheSmallOptPrice (){
        findNumberOfGefestInCatalogInCatalog();
        Assert.assertTrue("Отображаемая ОПТ цена для пользваотеля не равна вводимой у товара в админке"
                , Double.valueOf(replacingSomeSymbols(driver.findElement(By.xpath("(//*[contains(@id, 'SMALL_OPT')])[" + count + "]")).getText())) == tempDouble);
    }
    public void addingGefestGasStoveToTheCart(){
        findNumberOfGefestInCatalogInCatalog();
        driver.findElement(By.xpath("(//*[@class='quantity-selector__value'])[" + count + "]")).sendKeys("1");
    }
    public void checkingThatThePriceInTheCatalogFooterIsDisplayedAsForSmallOptGroup (){
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("#catalog__basket-quantity-value"),"1"));
        tempString = replacingSomeSymbols(driver.findElement(By.id("catalog__basket-price-value")).getText());
        System.out.println("Сумма добавленных товаров отображаемая в футере каталога = " + Double.valueOf(tempString));
        System.out.println("Цена товара которую я выбирал для SmallOpt  = " + tempDouble);
        pricesForAllProductsInTheFooter =  Double.valueOf(tempString);
        Assert.assertTrue(tempDouble == pricesForAllProductsInTheFooter);
    }
    public void navigationToSettingOfQuantitativeAccountingForTheProduct(){
        driver.navigate().to(b2bUrl.replaceAll("b2bcabinet/" , "") + "bitrix/admin/settings.php?lang=ru&mid=catalog");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#default_quantity_trace")));
    }

    public void enableQuantitativeAccountingForTheProductsInCatalog(){
        if (driver.findElement(By.cssSelector("#default_quantity_trace")).getText().equals("Нет")){
            driver.findElement(By.cssSelector("#product_settings")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@for='quantity_trace']")));
            driver.findElement(By.xpath("//*[@for='quantity_trace']")).click();
            driver.findElement(By.cssSelector("#product_settings_start_button")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@value='Сохранить']")));
            try {
                driver.findElement(By.xpath("//*[@value='Сохранить']")).click();
            }catch (Exception e){
                driver.findElement(By.xpath("//*[@value='Сохранить']")).click();
            }
        }
    }
    public void turnOffQuantitativeAccountingForTheProductsInCatalog(){
        if (driver.findElement(By.cssSelector("#default_quantity_trace")).getText().equals("Да")){
            driver.findElement(By.cssSelector("#product_settings")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@for='quantity_trace']")));
            driver.findElement(By.xpath("//*[@for='quantity_trace']")).click();
            driver.findElement(By.cssSelector("#product_settings_start_button")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@value='Сохранить']")));
            try {
                driver.findElement(By.xpath("//*[@value='Сохранить']")).click();
            }catch (Exception e){
                driver.findElement(By.xpath("//*[@value='Сохранить']")).click();
            }
        }
    }
    public void addingThisProductOneMoreTime(){
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("catalog__basket-quantity-value"), String.valueOf(numberOfProductsInTheFooter+1)));
        explicitWaiting();
        driver.findElement(By.xpath("(//*[contains(@id, 'quantity-increment')])[" + randomNumberOfProductsPerPage + "]")).click();
        explicitWaiting();
    }

    public void checkingThatPriceAndQuantityHaveIncreased (){
        Assert.assertTrue(Double.valueOf(replacingSomeSymbols(driver.findElement(By.cssSelector("#catalog__basket-price-value")).getText())) > priceForNewlyAddedProducts  );
        explicitWaiting();
        System.out.println(randomNumberOfProductsPerPage);
        Assert.assertTrue(Double.valueOf(replacingSomeSymbols(driver.findElement(By.xpath("(//*[@class='quantity-selector__value'])[" + randomNumberOfProductsPerPage +
                "]")).getAttribute("value"))) > quantityOfProductsInStock  );
    }
    public void checkingThatPriceAndQuantityHaveIncreasedForGefestGasStove (){
        Assert.assertTrue(Double.valueOf(replacingSomeSymbols(driver.findElement(By.cssSelector("#catalog__basket-price-value")).getText())) > priceForNewlyAddedProducts  );
        Assert.assertTrue(Double.valueOf(replacingSomeSymbols(driver.findElement(By.xpath("(//*[@class='quantity-selector__value'])[" + count +
                "]")).getAttribute("value"))) > quantityOfProductsInStock  );
    }

    public void enableQuantitativeAccountingAtTheGefestGasStove (){
        driver.findElement(By.xpath("//a[contains(text(), 'Плита GEFEST')]")).click();
        driver.findElement(By.xpath("//*[contains(text(), 'Торговый каталог')][contains(@class, 'adm-detail-tab')]")).click();
        driver.findElement(By.xpath("//*[@title='Дополнительные параметры']")).click();
        driver.findElement(By.xpath("//*[contains(@id, 'BASE_QUANTITY_TRACE')]")).click();
        driver.findElement(By.xpath("//*[@id='CAT_BASE_QUANTITY_TRACE'] /option[@value='Y']")).click();
        driver.findElement(By.cssSelector(".adm-btn-save")).click();
    }
    public void returningQuantitativeAccountingAtTheGefestGasStoveByDefault(){
        driver.findElement(By.xpath("//a[contains(text(), 'Плита GEFEST')]")).click();
        driver.findElement(By.xpath("//*[contains(text(), 'Торговый каталог')][contains(@class, 'adm-detail-tab')]")).click();
        driver.findElement(By.xpath("//*[@title='Дополнительные параметры']")).click();
        driver.findElement(By.xpath("//*[contains(@id, 'MEASURE_RATIO')]")).clear();
        driver.findElement(By.xpath("//*[contains(@id, 'MEASURE_RATIO')]")).sendKeys("1");
        try {
            driver.findElement(By.xpath("//*[@id='CAT_BASE_QUANTITY']")).clear();
        }catch (Exception e){
            navigationToSystemSettings();
            navigationToSettingOfQuantitativeAccountingForTheProduct();
            driver.findElement(By.xpath("//*[@for='use_store_control_y'][@class]")).click();
            driver.findElement(By.xpath("//*[@value='Сохранить']")).click();
            navigationToGasStoveSetting();
            driver.findElement(By.xpath("//a[contains(text(), 'Плита GEFEST')]")).click();
            driver.findElement(By.xpath("//*[contains(text(), 'Торговый каталог')][contains(@class, 'adm-detail-tab')]")).click();
            driver.findElement(By.xpath("//*[@title='Дополнительные параметры']")).click();
            driver.findElement(By.xpath("//*[@id='CAT_BASE_QUANTITY']")).clear();
        }
        driver.findElement(By.xpath("//*[@id='CAT_BASE_QUANTITY']")).clear();
        driver.findElement(By.xpath("//*[@id='CAT_BASE_QUANTITY']")).sendKeys("5");
        driver.findElement(By.xpath("//*[contains(@id, 'BASE_QUANTITY_TRACE')]")).click();
        driver.findElement(By.xpath("//*[@id='CAT_BASE_QUANTITY_TRACE'] /option[@value='D']")).click();
        driver.findElement(By.cssSelector(".adm-btn-save")).click();
    }
    public void returningTheKaiserGasStoveSettingByDefault(){
        driver.findElement(By.xpath("//a[contains(text(), 'Плита Kaiser')]")).click();
        driver.findElement(By.xpath("//*[contains(text(), 'Торговый каталог')][contains(@class, 'adm-detail-tab')]")).click();
        driver.findElement(By.xpath("//*[@title='Дополнительные параметры']")).click();
        driver.findElement(By.xpath("//*[contains(@id, 'MEASURE_RATIO')]")).clear();
        driver.findElement(By.xpath("//*[contains(@id, 'MEASURE_RATIO')]")).sendKeys("1");
        driver.findElement(By.cssSelector(".adm-btn-save")).click();
    }

    public void turnOffQuantitativeAccountingAtTheGefestGasStove(){
        driver.findElement(By.xpath("//a[contains(text(), 'Плита GEFEST')]")).click();
        driver.findElement(By.xpath("//*[contains(text(), 'Торговый каталог')][contains(@class, 'adm-detail-tab')]")).click();
        driver.findElement(By.xpath("//*[@title='Дополнительные параметры']")).click();
        driver.findElement(By.xpath("//*[contains(@id, 'BASE_QUANTITY_TRACE')]")).click();
        driver.findElement(By.xpath("//*[@id='CAT_BASE_QUANTITY_TRACE'] /option[@value='N']")).click();
        driver.findElement(By.cssSelector(".adm-btn-save")).click();
    }
    public void enterTheMaximumAvailableQuantityOfThisProduct(){
        findNumberOfGefestInCatalogInCatalog();
        tempValue2 = driver.findElement(By.xpath("(//*[@class='item-quantity__general'])[" + count + "]"))
                .getText();
        tempValue2 = replacingSomeSymbols(tempValue2);
        tempValue2 = tempValue2.replaceAll("[^0-9.]", "");
        driver.findElement(By.xpath("(//*[@class='quantity-selector__value'])[" + count + "]")).clear();
        driver.findElement(By.xpath("(//*[@class='quantity-selector__value'])[" + count + "]")).sendKeys("0");
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("#catalog__basket-quantity-value"), "0"));
        driver.findElement(By.xpath("(//*[@class='quantity-selector__value'])[" + count + "]")).sendKeys(tempValue2);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("#catalog__basket-quantity-value"), "1"));
    }
    public void addThisProductOneMoreTimeUsingPlusIcon (){
        quantityOfProductsInStock = Double.valueOf(replacingSomeSymbols(driver.findElement(By.xpath("(//*[@class='quantity-selector__value'])[" + count +
                "]")).getAttribute("value")));
        priceForNewlyAddedProducts = Double.valueOf(replacingSomeSymbols(driver.findElement(By.cssSelector("#catalog__basket-price-value")).getText()));
        driver.findElement(By.xpath("(//*[@class='quantity-selector__increment'])[" + count + "]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".b2b-notification__content")));
        explicitWaiting();explicitWaiting();
    }
    public void addThisProductOneMoreTimeManually (){
        quantityOfProductsInStock = Double.valueOf(replacingSomeSymbols(driver.findElement(By.xpath("(//*[@class='quantity-selector__value'])[" + count +
                "]")).getAttribute("value")));
        priceForNewlyAddedProducts = Double.valueOf(replacingSomeSymbols(driver.findElement(By.cssSelector("#catalog__basket-price-value")).getText()));
        driver.findElement(By.xpath("(//*[@class='quantity-selector__value'])[" + count + "]")).clear();
        driver.findElement(By.xpath("(//*[@class='quantity-selector__value'])[" + count + "]")).sendKeys(String.valueOf(quantityOfProductsInStock+1));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".b2b-notification__content")));
        explicitWaiting();explicitWaiting();
    }
    public void checkingThatPriceAndQuantityAreTheSame(){
        Assert.assertTrue(Double.valueOf(replacingSomeSymbols(driver.findElement(By.cssSelector("#catalog__basket-price-value")).getText())) == priceForNewlyAddedProducts  );
        Assert.assertTrue(Double.valueOf(replacingSomeSymbols(driver.findElement(By.xpath("(//*[@class='quantity-selector__value'])[" + count +
                "]")).getAttribute("value"))) == quantityOfProductsInStock  );
    }
    public void checkingTheMultiplicityOfAddingProductsAndCalculatingThePrice(){
        enterTheMaximumAvailableQuantityOfThisProduct();
        driver.findElement(By.xpath("(//*[@class='quantity-selector__value'])[" + count + "]")).clear();
        driver.findElement(By.xpath("(//*[@class='quantity-selector__value'])[" + count + "]")).sendKeys("0");
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("#catalog__basket-quantity-value"), "0"));
        driver.findElement(By.xpath("(//*[@class='quantity-selector__value'])[" + count + "]")).sendKeys("1");
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("#catalog__basket-quantity-value"), "1"));
        tempString = replacingSomeSymbols(driver.findElement(By.xpath("//*[@class='catalog__basket-price-value']")).getText());
        tempDouble = Double.parseDouble(tempString);
        driver.findElement(By.xpath("(//*[@class='quantity-selector__value'])[" + count + "]")).clear();
        driver.findElement(By.xpath("(//*[@class='quantity-selector__value'])[" + count + "]")).sendKeys("0");
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("#catalog__basket-quantity-value"), "0"));
        driver.findElement(By.xpath("(//*[@class='quantity-selector__increment'])[" + count + "]")).click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("#catalog__basket-quantity-value"), "1"));
        tempDouble2 = Math.round((tempDouble/10) *100.0) / 100.0;
        Assert.assertTrue(Double.valueOf(replacingSomeSymbols(driver.findElement(By.cssSelector("#catalog__basket-price-value")).getText())) == tempDouble2);
    }
    public void checkingThatThisProductHasStubInsteadOfPicture(){
        findNumberOfGefestInCatalogInCatalog();
        driver.findElement(By.xpath("(//*[@class='quantity-selector__value'])[" + count + "]")).clear();
        driver.findElement(By.xpath("(//*[@class='quantity-selector__value'])[" + count + "]")).sendKeys("1");
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("#catalog__basket-quantity-value"), "1"));
        Assert.assertTrue(driver.findElement(By.xpath("(//img[@src][@class='product__image'])[" + count + "]")).getAttribute("src").contains("no_photo.svg"));
    }
    public void checkingThatThisProductHasStubInsteadOfPictureInTheCart() {
        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(@id, 'basket-item')]//img[@src]")).getAttribute("src").contains("no_photo.svg"));
    }
    public void removeTheSelectionFromTheCatalogCategory (){
        if (driver.findElements(By.xpath("//*[contains(@class, 'categorie')]")).size() > 0){
            System.out.println(tempRandomNumber);
            driver.findElement(By.xpath("(//*[contains(@class, 'catalog_section')])[" + tempRandomNumber  + "] //*[@class='form-check']")).click();
        }else {
            navigationToCatalogTab();
        }
    }
    public void checkingThatAllProductsAreDisplayedAgain(){
        goToTheLastPage();
        Assert.assertTrue(tempValueForNumbers.equals(driver.findElement(By.xpath("//*[@class='page-item active']")).getText()));
    }
    public void removeOutputOfAllSubcategories(){
        if (driver.findElements(By.xpath("//*[contains(@class, 'categorie')]")).size() > 0){
            tempInt2 = driver.findElements(By.xpath("//*[@class='nav nav-group-sub'][@style='display:block'] /*[@class='nav-item nav-item-submenu'] //*[@class='checked']")).size();
            for (int i = 1; i <= tempInt2; i++) {
                driver.findElement(By.xpath("//*[@class='nav nav-group-sub'][@style='display:block'] /*[@class='nav-item nav-item-submenu'] //*[@class='checked']")).click();
                explicitWaiting();
            }
        }else {
            navigationToCatalogTab();
        }
    }
    public void checkingThatThereIsSomeProductsInTheBasket(){
        Assert.assertTrue(driver.findElements(By.cssSelector(".basket__item")).size() > 0);
    }
    public void addingThisProductToTheBasket(){
        driver.findElement(By.cssSelector(".quantity-selector__increment")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".b2b-notification__content")));
    }
    public void settingUpRandomDiscountForGefestGasStove(){
        driver.findElement(By.xpath("//*[@id='bx-search-box'] /input")).sendKeys("Предустановленный список маркетинговых акций");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".adm-search-result")));
        driver.findElement(By.cssSelector(".adm-search-result")).click();
        driver.findElement(By.xpath("//*[contains(text(), 'Скидка на товары, группы товаров')] /following::div[2] //*[contains(text(), 'Создать')]")).click();
        driver.findElement(By.xpath("//*[@name = 'discount_name']")).sendKeys("Название скидки " + randomString(10));
        driver.findElement(By.xpath("//*[contains(text(), 'Следующий шаг')]")).click();
        tempString = randomNumber(2);
        driver.findElement(By.xpath("//*[@name = 'discount_value']")).sendKeys(tempString);
        driver.findElement(By.cssSelector("#sale_discount_preset_product_add")).click();
        driver.findElement(By.cssSelector(".bx-core-adm-icon-expand")).click();
        driver.findElement(By.xpath("//*[contains(@class, 'adm-s-search-input-cel')] /input")).clear();
        driver.findElement(By.xpath("//*[contains(@class, 'adm-s-search-input-cel')] /input")).sendKeys("gefest");
        explicitWaiting();explicitWaiting();
        driver.findElement(By.cssSelector(".adm-list-table-popup")).click();
        driver.findElement(By.cssSelector(".bx-core-popup-menu-item-text")).click();
        driver.findElement(By.xpath("//*[contains(@class, 'adm-icon-close')]")).click();
        driver.findElement(By.xpath("//*[contains(text(), 'Следующий шаг')]")).click();
        try {
            driver.findElement(By.xpath("//*[contains(@id, 'discount_groups')] /*[contains(text(), 'Все пользователи')]")).click();
        }catch (Exception e){
            scrollDown();
            driver.findElement(By.xpath("//*[contains(text(), 'Следующий шаг')]")).click();
            driver.findElement(By.xpath("//*[contains(@id, 'discount_groups')] /*[contains(text(), 'Все пользователи')]")).click();
        }
        driver.findElement(By.xpath("//*[contains(text(), 'Следующий шаг')]")).click();
    }
    public void memorizingDiscountedAndNonDiscountedPricesForGefest(){
        driver.findElement(By.xpath("(//*[@class='quantity-selector__value'])[" + count + "]")).clear();
        driver.findElement(By.xpath("(//*[@class='quantity-selector__value'])[" + count + "]")).sendKeys("0");
        System.out.println(Double.valueOf(replacingSomeSymbols(driver.findElement(By.xpath("(//*[contains(@id, 'price_BASE')])[" + count + "]")).getText())));
        System.out.println(Double.valueOf(replacingSomeSymbols(driver.findElement(By.xpath("(//*[contains(@id, 'price_BASE')])[" + count + "] /following::*[1]")).getText())));
        tempDouble2 = Double.valueOf(replacingSomeSymbols(driver.findElement(By.xpath("(//*[contains(@id, 'price_BASE')])[" + count + "] /following::*[1]")).getText()));
        tempDouble = Double.valueOf(replacingSomeSymbols(driver.findElement(By.xpath("(//*[contains(@id, 'price_BASE')])[" + count + "]")).getText()))
                / Double.valueOf(replacingSomeSymbols(driver.findElement(By.xpath("(//*[contains(@id, 'price_BASE')])[" + count + "] /following::*[1]")).getText()));
        tempDouble = (1- (Math.round(tempDouble *100.0) / 100.0)) *100;
        tempDouble = Math.round(tempDouble *100.0) / 100.0;
        System.out.println("Высчитанный процент скидки= " + tempDouble);
        System.out.println("Введенный процент скидки= " + tempString);
    }
    public void deletingALLDiscounts (){
        driver.findElement(By.xpath("//*[@id='bx-search-box'] /input")).sendKeys("Предустановленный список маркетинговых акций");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".adm-search-result")));
        driver.findElement(By.cssSelector(".adm-search-result")).click();
        if (driver.findElements(By.xpath("//*[contains(text(), 'Скидка на товары, группы товаров')] /following::div[2] //*[contains(text(), 'Список')]")).size() > 0){
            driver.findElement(By.xpath("//*[contains(text(), 'Скидка на товары, группы товаров')] /following::div[2] //*[contains(text(), 'Список')]")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class = 'main-grid-row main-grid-row-body']//*[@class='main-grid-cell main-grid-cell-action']")));
        }


        if (driver.findElements(By.xpath("//*[@class='main-grid-control-sort main-grid-control-sort-asc']")).size() > 0) {
            driver.findElement(By.cssSelector("#tbl_sale_discount_check_all")).click();
            driver.findElement(By.cssSelector("#grid_remove_button_control")).click();
            driver.findElement(By.cssSelector("#tbl_sale_discount-confirm-dialog-apply-button")).click();
            //standardConfirmationOfTheActionOnThePage();
        }
    }
    public void checkingThatTheDiscountEnteredAndDisplayedInTheCatalogAreTheSame (){
        Assert.assertTrue(Double.valueOf(tempString) == tempDouble);
    }
    public void addingAnItemToTheCartWithPriceChecking(){
        randomNumberOfProductsPerPage = count;
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("catalog__basket-quantity-value"), "0"));
        calculationOfTheCoefficientForNonPieceProducts();
        numberOfProductsInTheFooter = Integer.parseInt(driver.findElement(By.id("catalog__basket-quantity-value")).getText());
        driver.findElement(By.xpath("(//*[@class='quantity-selector__value'])[" + randomNumberOfProductsPerPage + "]"))
                .sendKeys("1");
        productCounterInTheCart++;
        determiningPriceOfThisRandomProduct();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("catalog__basket-quantity-value"), String.valueOf(productCounterInTheCart)));
        explicitWaiting();
        tempString = replacingSomeSymbols(driver.findElement(By.id("catalog__basket-price-value")).getText());
        System.out.println("Сумма добавленных товаров отображаемая в футере каталога = " + Double.valueOf(tempString));
        pricesForAllProductsInTheFooter =  Double.valueOf(tempString);
        explicitWaiting();
        Assert.assertTrue(pricesForAllProductsInTheFooter == basePriceRandomProduct);
        navigationToCart();
        explicitWaiting();
        tempString = driver.findElement(By.id("page-basket-total-block")).getText();
        pricesForAllProductsInTheCartPAge =  Double.valueOf(replacingSomeSymbols(tempString));
        waitingMilliSecond();
        System.out.println("Сумма добавленных товаров отображаемая в футере корзины = " + pricesForAllProductsInTheCartPAge);
        Assert.assertTrue(pricesForAllProductsInTheFooter == basePriceRandomProduct);
    }

    public void choiceCatalogWithOnlyOffers(){
        driver.findElement(By.xpath("//*[@data-bx-property-id = 'IBLOCK_TYPE']")).click();
        driver.findElement(By.xpath("//*[@data-bx-property-id = 'IBLOCK_TYPE'] //*[@value='catalog']")).click();
        driver.findElement(By.xpath("//select[@data-bx-property-id = 'IBLOCK_ID']")).click();
        driver.findElement(By.xpath("//select[@data-bx-property-id = 'IBLOCK_ID'] //*[contains(text(), 'Одежда')]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(buttonToSaveTheComponentSettingsForTheCatalog));
        explicitWaiting();
        driver.findElement(buttonToSaveTheComponentSettingsForTheCatalog).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(catalogTabLocator));
        explicitWaiting();
    }
    public void choiceStandardCatalog (){
        driver.findElement(By.xpath("//*[@data-bx-property-id = 'IBLOCK_TYPE']")).click();
        driver.findElement(By.xpath("//*[@data-bx-property-id = 'IBLOCK_TYPE'] //*[@value='sotbit_b2bcabinet_type_catalog']")).click();

        driver.findElement(By.xpath("//select[@data-bx-property-id = 'IBLOCK_ID']")).click();
        driver.findElement(By.xpath("//select[@data-bx-property-id = 'IBLOCK_ID'] //*[contains(text(), 'Каталог товаров')]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(buttonToSaveTheComponentSettingsForTheCatalog));
        explicitWaiting();
        driver.findElement(buttonToSaveTheComponentSettingsForTheCatalog).click();
    }
    public void checkingTheNamesOfTheOutputGasStoves (){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(), 'Плита ENDEVER IP-26')]")));
        tempString = "Плита ENDEVER IP-26 Плита GEFEST 6560-03 0001 Плита Gorenje EC 62 CLB Плита Kaiser HC 62010 S Moire Плита Oursson IP1220T/GA";
        for (int i = 1; i <= 5; i++) {
            Assert.assertTrue("Этой плиты  " + driver.findElement(By.xpath("(//*[@class='product__link'])[" + i + "]")).getText() + "нету в моем списке плит, которые должны выводится  "
                    ,tempString.contains(driver.findElement(By.xpath("(//*[@class='product__link'])[" + i + "]")).getText()));
        }
    }
    public void checkingTheNamesOfTheOutputGasChainsaw(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(), 'пила CHAMPION')]")));
        tempString = "Цепная бензиновая пила CHAMPION 125T-10 Цепная бензиновая пила FORWARD FGS-4504 Цепная бензиновая пила Husqvarna 365H Цепная бензиновая пила Husqvarna 450e " +
                "Цепная бензиновая пила Huter BS-62 Цепная бензиновая пила PARTNER P340S Цепная бензиновая пила PIRAN CS4990";
        for (int i = 1; i <= 7; i++) {
            Assert.assertTrue("Этой бензопилы  " + driver.findElement(By.xpath("(//*[@class='product__link'])[" + i + "]")).getText() + "нету в моем списке бензопил, которые должны выводится  "
                    ,tempString.contains(driver.findElement(By.xpath("(//*[@class='product__link'])[" + i + "]")).getText()));
        }
    }
    public void checkingTheQuantityOfOutputGoodsWhenSelectingSubcategoriesChainsawsAndGasStoves(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(), 'Плита')]")));
        tempInt = driver.findElements(By.cssSelector(".product__link")).size();
        if (driver.findElements(By.xpath("//*[@class='page-item']")).size() > 0){
            changePageOnTheSecond();
            tempInt = tempInt + driver.findElements(By.cssSelector(".product__link")).size();
        }
        if (driver.findElements(By.xpath("//*[contains(@class, 'categorie')]")).size() > 0){
            Assert.assertTrue("Количество товаров в двух подкатегриях (Кух. плиты и Бензопилы) не равно 12 ", tempInt == 12);
        }
    }

    public void showTheQuantityOfProductsInStorage(){
        if (driver.findElements(By.xpath("//*[contains(@class,'icon-question')]")).size() == 0){
            navigationToComponentOfCatalogSetting();
            driver.findElement(By.xpath("//*[contains(@for, 'USE_STORE')][contains(@class, 'checkbox')]")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(buttonToSaveTheComponentSettingsForTheCatalog));
            explicitWaiting();
            driver.findElement(buttonToSaveTheComponentSettingsForTheCatalog).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(catalogTabLocator));
            explicitWaiting();
        }
    }
    public void tryTurnOffShowTheQuantityOfProductsInStorage (){
        if (driver.findElements(By.xpath("//*[contains(@class,'icon-question')]")).size() > 0){
            navigationToComponentOfCatalogSetting();
            driver.findElement(By.xpath("//*[contains(@for, 'USE_STORE')][contains(@class, 'checkbox')]")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(buttonToSaveTheComponentSettingsForTheCatalog));
            explicitWaiting();
            driver.findElement(buttonToSaveTheComponentSettingsForTheCatalog).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(catalogTabLocator));
            explicitWaiting();
        }
    }
    public void turnOffShowingTheQuantityOfProductsInStorage(){
        driver.findElement(By.xpath("//*[contains(@for, 'USE_STORE')][contains(@class, 'checkbox')]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(buttonToSaveTheComponentSettingsForTheCatalog));
        explicitWaiting();
        driver.findElement(buttonToSaveTheComponentSettingsForTheCatalog).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(catalogTabLocator));
        explicitWaiting();
    }
    public void addingSecondStorageIfItNeed(){
        driver.findElement(By.cssSelector("#global_menu_store")).click();
        driver.findElement(By.xpath("//*[@class = 'adm-submenu-item-name-link-text'][contains(text(), 'Складской учет')]")).click();
        if (driver.findElements(By.xpath("//*[@class='main-grid-row main-grid-row-body']")).size() < 2){
            driver.findElement(By.cssSelector(".ui-btn-primary")).click();
            driver.findElement(By.xpath("//*[@name='TITLE']")).sendKeys("TEST");
            driver.findElement(By.xpath("//*[@name='ADDRESS']")).sendKeys("address" + randomString(3));
            driver.findElement(By.xpath("//*[@name='save']")).click();
        }
    }
    public void addingProductsToTheStorages(){
        driver.findElement(By.xpath("//a[contains(text(), 'Плита GEFEST')]")).click();
        driver.findElement(By.xpath("//*[contains(text(), 'Торговый каталог')][contains(@class, 'adm-detail-tab')]")).click();
        driver.findElement(By.xpath("//*[@title='Управление товаром на складах']")).click();
        try {
            driver.findElement(By.xpath("//input[contains(@id, 'AMOUNT')]")).clear();
        }catch (Exception e){
            driver.findElement(By.xpath("//*[@id='bx-search-box'] /input")).sendKeys("склады");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".adm-search-result")));
            driver.findElement(By.cssSelector(".adm-search-result")).click();
            driver.findElement(By.xpath("//*[contains(@for, 'store_control')][contains(@class, 'checkbox')]")).click();
            driver.findElement(By.xpath("//*[@value='Сохранить']")).click();
            navigationToGasStoveSetting();
            driver.findElement(By.xpath("//a[contains(text(), 'Плита GEFEST')]")).click();
            driver.findElement(By.xpath("//*[contains(text(), 'Торговый каталог')][contains(@class, 'adm-detail-tab')]")).click();
            driver.findElement(By.xpath("//*[@title='Управление товаром на складах']")).click();
            driver.findElement(By.xpath("//input[contains(@id, 'AMOUNT')]")).clear();
        }
        tempValue = randomNumberWithoutZero(4);
        driver.findElement(By.xpath("(//input[contains(@id, 'AMOUNT')])[1]")).clear();
        driver.findElement(By.xpath("(//input[contains(@id, 'AMOUNT')])[1]")).sendKeys(tempValue);
        tempValue1 = randomNumberWithoutZero(4);
        driver.findElement(By.xpath("(//input[contains(@id, 'AMOUNT')])[2]")).clear();
        driver.findElement(By.xpath("(//input[contains(@id, 'AMOUNT')])[2]")).sendKeys(tempValue1);
        driver.findElement(By.xpath("//*[@title='Дополнительные параметры']")).click();
        driver.findElement(By.xpath("//*[@id='CAT_BASE_QUANTITY']")).clear();
        tempValue2 = String.valueOf(Integer.parseInt(tempValue1)+ Integer.parseInt(tempValue));
        driver.findElement(By.xpath("//*[@id='CAT_BASE_QUANTITY']")).sendKeys(tempValue2);
        driver.findElement(By.xpath("//*[@name='save']")).click();
    }
    public void configureTheOutputOfAllStoragesToTheCatalog (){
        for (int i = 2; i <= driver.findElements(By.xpath("//select[@data-bx-property-id = 'STORES'] /option")).size(); i++) {
            if (!(driver.findElement(By.xpath("(//select[@data-bx-property-id = 'STORES'] /option)[" + i + "]")).isSelected())){
                driver.findElement(By.xpath("(//select[@data-bx-property-id = 'STORES'] /option)[" + i + "]")).click();
            }
        }
        driver.findElement(buttonToSaveTheComponentSettingsForTheCatalog).click();
    }
    public void checkingThatTheTotalNumberOfOutputProductsAndQuantityByIsEqualToThePreviouslyEnteredData(){
        driver.findElement(By.xpath("(//*[@class='quantity-selector__value'])[" + count + "]")).clear();
        driver.findElement(By.xpath("(//*[@class='quantity-selector__value'])[" + count + "]")).sendKeys("0");
        tempValue3 = driver.findElement(By.xpath("(//*[@class='item-quantity__general'])[" + count + "]")).getText();
        Assert.assertTrue((Integer.parseInt(tempValue) + Integer.parseInt(tempValue1))  == Integer.parseInt(tempValue3));
        System.out.println(count);
        actions.moveToElement(driver.findElement(By.xpath("(//*[@class='item-quantity__general'])[" + count + "]")));
        actions.perform();
        Assert.assertTrue("Не отображается склад с названием 'TEST'", driver.findElements(By.xpath("//*[@class='item-quantity__store-name'][text()='TEST']")).size() > 0);
        System.out.println("Отображаемое кол-во товаров на складе №1   " + driver.findElement(By.xpath("((//*[@class='item-quantity__store-list'])["+ count + "] //*[@class='item-quantity__store-quantity'])[1]")).getText());
        System.out.println("Отображаемое кол-во товаров на складе №2   " + driver.findElement(By.xpath("((//*[@class='item-quantity__store-list'])["+ count + "] //*[@class='item-quantity__store-quantity'])[2]")).getText());
        System.out.println("Кол-во товара на складе №1 которое я вводил   " + tempValue1);
        System.out.println("Кол-во товара на складе №2 которое я вводил   " + tempValue);
        flag = false;
        for (int i = 1; i <= 2; i++) {
            if (driver.findElement(By.xpath("((//*[@class='item-quantity__store-list'])["+ count + "] //*[@class='item-quantity__store-quantity'])[" + i + "]")).getText().trim().equals(tempValue1.trim())){
                flag = true;
            }
        }
        Assert.assertTrue(flag == true);
        flag = false;
        for (int i = 1; i <= 2; i++) {
            if (driver.findElement(By.xpath("((//*[@class='item-quantity__store-list'])["+ count + "] //*[@class='item-quantity__store-quantity'])[" + i + "]")).getText().trim().equals(tempValue.trim())){
                flag = true;
            }
        }
        Assert.assertTrue(flag == true);
    }
    public void addingGefestGasStoveToCartUsingPlusIcon(){
        driver.findElement(By.xpath("(//*[contains(@title, 'Плита GEFEST')] /following::* //*[@class='quantity-selector__increment'])[1]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".b2b-notification__content")));
        numberOfAvailableGefestGasStove = Double.valueOf(driver.findElement(By.xpath("(//*[contains(@title, 'Плита GEFEST')] /following::* //*[@class='item-quantity__general'])[1]")).getText());
    }
    public void addingKaiserGasStoveToCartUsingPlusIcon(){
        driver.findElement(By.xpath("(//*[contains(@title, 'Плита Kaiser')] /following::* //*[@class='quantity-selector__increment'])[1]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".b2b-notification__content")));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("#catalog__basket-quantity-value"), "2"));
        numberOfAvailableKaiserGasStove = Double.valueOf(driver.findElement(By.xpath("(//*[contains(@title, 'Плита Kaiser')] /following::* //*[@class='item-quantity__general'])[1]")).getText());
    }
//    public void addingGefestGasStoveToCartUsingPlusIconOneMoreThanAvailable(){
//        driver.findElement(By.xpath("//*[contains(text(), 'GEFEST')] /following::* //*[@data-entity ='basket-item-quantity-field']")).clear();
//        driver.findElement(By.xpath("//*[contains(text(), 'GEFEST')] /following::* //*[@data-entity ='basket-item-quantity-field']")).sendKeys(String.valueOf(tempDouble + 1));
//        driver.findElement(checkboxThatHighlightsAllProductsInTheCartLocator).click();
//        Assert.assertTrue(Double.valueOf(driver.findElement(By.xpath("//*[contains(text(), 'GEFEST')] /following::* //*[@data-entity ='basket-item-quantity-field']"))
//                .getAttribute("value")) > tempDouble);
//        driver.findElement(By.xpath("//*[contains(text(), 'GEFEST')] /following::* //*[@data-entity ='basket-item-quantity-field']")).clear();
//        explicitWaiting();explicitWaiting();
//        driver.findElement(By.xpath("//*[contains(text(), 'GEFEST')] /following::* //*[@data-entity ='basket-item-quantity-field']")).clear();
//        explicitWaiting();explicitWaiting();
//        tempDouble3 = Double.valueOf(driver.findElement(By.xpath("//*[contains(text(), 'GEFEST')] /following::* //*[@data-entity ='basket-item-quantity-field']")).getAttribute("value"));
//        while (!(tempDouble == tempDouble3)){
//            driver.findElement(By.xpath("//*[contains(text(), 'GEFEST')] /following::* //*[@data-entity ='basket-item-quantity-plus']")).click();
//            tempDouble3 = Double.valueOf(driver.findElement(By.xpath("//*[contains(text(), 'GEFEST')] /following::* //*[@data-entity ='basket-item-quantity-field']")).getAttribute("value"));
//            waitingMilliSecond();
//        }
//        driver.findElement(By.xpath("//*[contains(text(), 'GEFEST')] /following::* //*[@data-entity ='basket-item-quantity-plus']")).click();
//        driver.findElement(checkboxThatHighlightsAllProductsInTheCartLocator).click();
//        Assert.assertTrue(Double.valueOf(driver.findElement(By.xpath("//*[contains(text(), 'GEFEST')] /following::* //*[@data-entity ='basket-item-quantity-field']"))
//                .getAttribute("value")) > tempDouble);
//    }
//    public void addingKaiserGasStoveToCartUsingPlusIconOneMoreThanAvailable (){
//        tempDouble3 = Double.valueOf(driver.findElement(By.xpath("//*[contains(text(), 'Kaiser')] /following::* //*[@data-entity ='basket-item-quantity-field']")).getAttribute("value"));
//        while (!(tempDouble2 == tempDouble3)){
//            driver.findElement(By.xpath("//*[contains(text(), 'Kaiser')] /following::* //*[@data-entity ='basket-item-quantity-plus']")).click();
//            waitingMilliSecond();
//            tempDouble3 = Double.valueOf(driver.findElement(By.xpath("//*[contains(text(), 'Kaiser')] /following::* //*[@data-entity ='basket-item-quantity-field']")).getAttribute("value"));
//        }
//        driver.findElement(By.xpath("//*[contains(text(), 'Kaiser')] /following::* //*[@data-entity ='basket-item-quantity-plus']")).click();
//        driver.findElement(checkboxThatHighlightsAllProductsInTheCartLocator).click();
//        Assert.assertTrue(Double.valueOf(driver.findElement(By.xpath("//*[contains(text(), 'Kaiser')] /following::* //*[@data-entity ='basket-item-quantity-field']"))
//                .getAttribute("value")) == tempDouble2);
//        driver.findElement(By.xpath("//*[contains(text(), 'Kaiser')] /following::* //*[@data-entity ='basket-item-quantity-field']")).clear();
//        driver.findElement(By.xpath("//*[contains(text(), 'Kaiser')] /following::* //*[@data-entity ='basket-item-quantity-field']")).sendKeys(String.valueOf(tempDouble2 + 1));
//        driver.findElement(checkboxThatHighlightsAllProductsInTheCartLocator).click();
//        explicitWaiting();explicitWaiting();
//        Assert.assertTrue(Double.valueOf(driver.findElement(By.xpath("//*[contains(text(), 'Kaiser')] /following::* //*[@data-entity ='basket-item-quantity-field']"))
//                .getAttribute("value")) == tempDouble2);
//    }


    public void addingMaxQuantityOfProductInTheCartUsingPlusIconOneMoreThanAvailable (By quantityFieldSelector, By quantityPlusSelector, double numberOfAvailableProduct){
        tempDouble = 1;
        driver.findElement(quantityFieldSelector).clear();
        explicitWaiting();explicitWaiting();
        System.out.println("Доступное кол-во товара - " + numberOfAvailableProduct);
        if (numberOfAvailableProduct > 95){
            driver.findElement(quantityFieldSelector).sendKeys(del + (numberOfAvailableProduct - 10));
            driver.findElement(checkboxThatHighlightsAllProductsInTheCartLocator).click();
            explicitWaiting();
        }
        while (!(numberOfAvailableProduct == tempDouble)){
            try {
                driver.findElement(quantityPlusSelector).click();
            }catch (Exception e){
                driver.findElement(quantityPlusSelector).click();
            }
            waitingMilliSecond();
            try {
                tempDouble = Double.valueOf(driver.findElement(quantityFieldSelector).getAttribute("value"));
            }catch (Exception e){
                tempDouble = Double.valueOf(driver.findElement(quantityFieldSelector).getAttribute("value"));
            }
        }
        try {
            driver.findElement(quantityPlusSelector).click();
        }catch (Exception e){
            driver.findElement(quantityPlusSelector).click();
        }
        driver.findElement(checkboxThatHighlightsAllProductsInTheCartLocator).click();
        explicitWaiting(); explicitWaiting();
    }
    public void attemptToSelectNegativeQuantityOfProductsInTheCartUsingMinusIcon(By quantityFieldSelector, By quantityMinusSelector){
        tempDouble = Double.valueOf(driver.findElement(quantityFieldSelector).getAttribute("value"));
        if (tempDouble > 95){
            driver.findElement(quantityFieldSelector).sendKeys(del + "10");
            driver.findElement(checkboxThatHighlightsAllProductsInTheCartLocator).click();
            explicitWaiting();
        }
        while (!(1 == tempDouble)){
            try {
                driver.findElement(quantityMinusSelector).click();
            }catch (Exception e){
                driver.findElement(quantityMinusSelector).click();
            }
            waitingMilliSecond();
            try {
                tempDouble = Double.valueOf(driver.findElement(quantityFieldSelector).getAttribute("value"));
            }catch (Exception e){
                tempDouble = Double.valueOf(driver.findElement(quantityFieldSelector).getAttribute("value"));
            }
        }
        for (int i = 0; i < 11; i++) {
            try {
                driver.findElement(quantityMinusSelector).click();
            }catch (Exception e){
                driver.findElement(quantityMinusSelector).click();
            }
            waitingMilliSecond();
        }
        driver.findElement(checkboxThatHighlightsAllProductsInTheCartLocator).click();
    }
    public void attemptToEnterNegativeQuantityOfProductsInTheCart(By quantityFieldSelector){
        driver.findElement(quantityFieldSelector).sendKeys(del + "-1");
        explicitWaiting();explicitWaiting();
        driver.findElement(checkboxThatHighlightsAllProductsInTheCartLocator).click();
    }
    public void addingMaxQuantityOfProductInTheCartUsingInputField(By quantityFieldSelector, double numberOfAvailableProduct){
        explicitWaiting();explicitWaiting();
        driver.findElement(quantityFieldSelector).click();
        driver.findElement(quantityFieldSelector).sendKeys(del + String.valueOf(numberOfAvailableProduct + 1));
        explicitWaiting();explicitWaiting();
        driver.findElement(checkboxThatHighlightsAllProductsInTheCartLocator).click();
        explicitWaiting();explicitWaiting();
    }

    public void checkingThatTotalPriceAreCalculatedRight(){
        System.out.println(Double.valueOf(replacingSomeSymbols(driver.findElement(By.cssSelector(".basket-page__total-price-value")).getText())));
        System.out.println(Double.valueOf((((numberOfAvailableGefestGasStove+1) * Double.valueOf(replacingSomeSymbols(driver.findElement(By.xpath("(//*[contains(text(), 'GEFEST')] /following::* //*[@class ='basket__column-price-wrap']/span) [1]")).getText())))
                + (numberOfAvailableKaiserGasStove * Double.valueOf(replacingSomeSymbols(driver.findElement(By.xpath("(//*[contains(text(), 'Kaiser')] /following::* //*[@class ='basket__column-price-wrap']/span) [1]")).getText())) *10))));

        Assert.assertTrue(Double.valueOf(replacingSomeSymbols(driver.findElement(By.cssSelector(".basket-page__total-price-value")).getText()))
                == (((numberOfAvailableGefestGasStove+1) * Double.valueOf(replacingSomeSymbols(driver.findElement(By.xpath("(//*[contains(text(), 'GEFEST')] /following::* //*[@class ='basket__column-price-wrap']/span) [1]")).getText())))
                + (numberOfAvailableKaiserGasStove * Double.valueOf(replacingSomeSymbols(driver.findElement(By.xpath("(//*[contains(text(), 'Kaiser')] /following::* //*[@class ='basket__column-price-wrap']/span) [1]")).getText())) *10)
        ));
    }
    public void checkingThatTotalPriceOfTheseProductsAreCalculatedRight(By priceForFirstProduct, By priceForSecondProduct){
        Assert.assertTrue(Double.valueOf(replacingSomeSymbols(driver.findElement(By.cssSelector(".basket-page__total-price-value")).getText()))
                == (((quantityOfSecondProductsInStock+1) * Double.valueOf(replacingSomeSymbols(driver.findElement(priceForSecondProduct).getText())))
                + (quantityOfProductsInStock * 10 * Double.valueOf(replacingSomeSymbols(driver.findElement(priceForFirstProduct).getText())))
        ));
    }
    public void checkingThatQuantityOfGefestGasStoveIsOneMoreThanAvailable(){
        Assert.assertTrue(Double.valueOf(driver.findElement(quantityFieldOfGefestLocator).getAttribute("value")) == numberOfAvailableGefestGasStove+1);
    }
    public void checkingThatQuantityOfThisProductIsOneMoreThanAvailable(By quantityFieldSelector, double numberOfAvailableProduct){
        Assert.assertTrue(Double.valueOf(driver.findElement(quantityFieldSelector).getAttribute("value")) == numberOfAvailableProduct+1);
    }
    public void checkingThatQuantityThisProductIsEqualsAvailable(By quantityFieldSelector, double numberOfAvailableProduct){
        Assert.assertTrue(Double.valueOf(driver.findElement(quantityFieldSelector).getAttribute("value")) == numberOfAvailableProduct);
    }
    public void checkingThatQuantityThisProductIsEqualsOne(By quantityFieldSelector){
        Assert.assertTrue(driver.findElement(quantityFieldSelector).getAttribute("value").equals("1"));
    }
    public void checkingThatQuantityOfKaiserGasStoveEqualsOneTenth(){
        Assert.assertTrue(driver.findElement(quantityFieldOfKaiserLocator).getAttribute("value").equals("0.1"));
    }
    public void checkingThatQuantityThisProductEqualsOneTenth(By quantityFieldSelector){
        Assert.assertTrue(driver.findElement(quantityFieldSelector).getAttribute("value").equals("0.1"));
    }
    public void checkingThatThereAreNoProdutsInTheBasket(){
        Assert.assertTrue(driver.findElements(By.cssSelector(".basket__item")).size() == 0);
    }
    public void configureTheFirstTwoTP(){
        actions.moveToElement(driver.findElement(By.xpath("(//*[@class='quantity-selector__increment'])[1]")));
        actions.perform();
        explicitWaiting();
        driver.findElement(By.xpath("(//*[@class = 'bx-context-toolbar-button-text'][text()='Изменить товар'])[1]")).click();
        driver.findElement(By.xpath("//*[contains(@title, 'еречень торговых предложений')]")).click();
        driver.findElement(By.xpath("(//*[@class='adm-list-table-popup'])[1]")).click();
        driver.findElement(By.xpath("//*[contains(@class, 'menu-item-text')][text()='Изменить']")).click();
        driver.findElement(By.xpath("//*[contains(text(), 'Торговый каталог')][contains(@class, 'adm-detail-tab')]")).click();
        driver.findElement(By.xpath("//*[@title='Дополнительные параметры']")).click();
        driver.findElement(By.xpath("//*[contains(@id, 'MEASURE_RATIO')]")).clear();
        driver.findElement(By.xpath("//*[contains(@id, 'MEASURE_RATIO')]")).sendKeys("0.1");
        try {
            driver.findElement(By.xpath("//*[@class='adm-btn-save']")).click();
        }catch (Exception e){
            driver.findElement(By.xpath("(//*[@class='adm-btn-save'])[2]")).click();
        }
        driver.findElement(By.xpath("(//*[@class='adm-list-table-popup'])[2]")).click();
        try {
            driver.findElement(By.xpath("(//*[contains(@class, 'menu-item-text')][text()='Изменить'])[1]")).click();
        }catch (Exception e){
            driver.findElement(By.xpath("(//*[contains(@class, 'menu-item-text')][text()='Изменить'])[2]")).click();
        }
        driver.findElement(By.xpath("//*[contains(text(), 'Торговый каталог')][contains(@class, 'adm-detail-tab')]")).click();
        driver.findElement(By.xpath("//*[@title='Дополнительные параметры']")).click();
        driver.findElement(By.xpath("//*[contains(@id, 'BASE_QUANTITY_TRACE')]")).click();
        driver.findElement(By.xpath("//*[contains(@id, 'BASE_QUANTITY_TRACE')] /option[@value='N']")).click();
        try {
            driver.findElement(By.xpath("//*[@class='adm-btn-save']")).click();
        }catch (Exception e){
            driver.findElement(By.xpath("(//*[@class='adm-btn-save'])[2]")).click();
        }
        driver.findElement(By.cssSelector(".adm-btn-save")).click();
        explicitWaiting();
    }
    public void addingFirstTwoTPToTheCart(){
        driver.findElement(By.cssSelector(".offers-info")).click();
        quantityOfProductsInStock = Double.valueOf(replacingSomeSymbols(driver.findElement(By.xpath("(//*[@class='item-quantity__general'])[1]")).getText()));
        driver.findElement(By.xpath("(//*[@class='quantity-selector__increment'])[1]")).click();
        quantityOfSecondProductsInStock = Double.valueOf(replacingSomeSymbols(driver.findElement(By.xpath("(//*[@class='item-quantity__general'])[2]")).getText()));
        driver.findElement(By.xpath("(//*[@class='quantity-selector__increment'])[2]")).click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//*[@id='catalog__basket-quantity-value']"), "2"));
        navigationToCart();
    }
    public void checkingThatThereAreTwoProductsInTheCart(){
        Assert.assertTrue(driver.findElements(By.xpath("//*[@class='basket__product-name ']")).size() == 2);
        driver.navigate().refresh();
        Assert.assertTrue(driver.findElements(By.xpath("//*[@class='basket__product-name ']")).size() == 2);
    }
    public void checkingThatThereAreOneProductsInTheCart(){
        Assert.assertTrue(driver.findElements(By.xpath("//*[@class='basket__product-name ']")).size() == 1);
    }
    public void deleteTheFirstProductFromTheCart(){
        tempString = driver.findElement(By.xpath("//*[@class='basket__product-name ']")).getText();
        System.out.println(tempString);
        driver.findElement(By.xpath("//*[@data-entity='basket-item-checkbox']")).click();
        driver.findElement(buttonForDeletingProductsInCartLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-entity='basket-item-restore-button']")));
        explicitWaiting();
    }
    public void restoreTheFirstProductInTheCart(){
        driver.findElement(By.xpath("//*[@data-entity='basket-item-restore-button']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()= '" + tempString + "']")));
        explicitWaiting();
    }
    public void checkingThatAllCheckboxesAreSelected(){
        for (int i = 1; i <= driver.findElements(By.xpath("//*[@data-entity='basket-item-checkbox']")).size(); i++) {
            Assert.assertTrue("Не все чекбоксы выбрались"
                    , driver.findElement(By.xpath("(//*[@data-entity='basket-item-checkbox'])["+ i +"] //*[contains(@class, 'state-checked')]")).isDisplayed());
        }
    }
    public void addingAndCheckThatAdditionalProductIsAddedTenTimes(){
        for (int i = 1; i <= driver.findElements(By.xpath("//*[contains(@id, 'basket-item-quantity')]")).size(); i++) {
            tempDouble = tempDouble + Double.valueOf(driver.findElement(By.xpath("(//*[contains(@id, 'basket-item-quantity')])[" + i + "]")).getAttribute("value"));
        }
        for (int i = 1; i <= 10 ; i++) {
            tempRandomNumber = 1 + (int) (Math.random() * driver.findElements(By.xpath("//*[@class='catalog-list__body'] //*[@class='input-group-append']")).size());
            driver.findElement(By.xpath("(//*[@class='catalog-list__body'] //*[@class='input-group-append'])[" + tempRandomNumber + "]")).click();
            explicitWaiting();explicitWaiting();explicitWaiting();explicitWaiting();explicitWaiting();explicitWaiting();
            tempDouble2=0;
            for (int y = 1; y <= driver.findElements(By.xpath("//*[contains(@id, 'basket-item-quantity')]")).size(); y++) {
                tempDouble2 = tempDouble2 + Double.valueOf(driver.findElement(By.xpath("(//*[contains(@id, 'basket-item-quantity')])[" + y + "]")).getAttribute("value"));
            }
            Assert.assertTrue("Я не смог добавить этот товар -  " + tempRandomNumber + " -- это его порядковый номер в таблице доп. товаров"
                    , tempDouble2 > tempDouble);
            tempDouble = tempDouble2;
        }
    }
    public void addingToSearchFieldWordForSearchInTheCart(){
        driver.findElement(By.cssSelector(".search-group__input")).sendKeys("бензин");
        driver.findElement(By.cssSelector(".search-group__input")).sendKeys(Keys.ENTER);
        explicitWaiting();
    }
    public void checkingThatAllProductsInTheCarContainsWordForSearch(){
        for (int i = 1; i <= driver.findElements(By.cssSelector(".basket__product-name ")).size(); i++) {
            Assert.assertTrue(driver.findElement(By.xpath("(//*[@class='basket__product-name '])[" + i + "]")).getText().contains("бензин"));
        }
    }

    public void addingToSearchFieldWordForSearchInAdditionalProducts(){
        driver.findElement(By.xpath("//*[@placeholder='Название']")).sendKeys("бензин");
        driver.findElement(By.xpath("//*[contains(@class, 'filter')]/*[contains(@class, 'btn_b2b')]")).click();
        explicitWaiting();
        explicitWaiting();
    }
    public void addingToSearchFieldWordForSearchInAdditionalProductsGefestGasStove(){
        driver.findElement(By.xpath("//*[@placeholder='Название']")).sendKeys("Плита GEFEST");
        driver.findElement(By.xpath("//*[contains(@class, 'filter')]/*[contains(@class, 'btn_b2b')]")).click();
        explicitWaiting();
    }
    public void checkingThatAllAdditionalProductsContainsWordForSearch(){
        for (int i = 1; i <= driver.findElements(By.xpath("//*[@class='catalog-list__name']")).size(); i++) {
            Assert.assertTrue(driver.findElement(By.xpath("(//*[@class='catalog-list__name'])[" + i + "]")).getText().contains("бензин"));
        }
    }
    public void checkingThatAllAdditionalProductsBelongThisSection(){
        checkingThatAllAdditionalProductsContainsWordForSearch();
    }
    String[] wordsForCheck  = new String[9];
    String[] wordsForSearch  = new String[9];
    public void selectRandomSection (){
        wordsForCheck[0] = "плит";
        wordsForCheck[1] = "тиральн";
        wordsForCheck[2] = "олодильник";
        wordsForCheck[3] = "бензин";
        wordsForCheck[4] = "азонокос";
        wordsForCheck[5] = "ышь";
        wordsForCheck[6] = "кабин";
        wordsForCheck[7] = "анн";
        wordsForCheck[8] = "лавиатур";

        wordsForSearch[0] = "Кухонные плиты";
        wordsForSearch[1] = "Стиральные машины";
        wordsForSearch[2] = "Холодильники";
        wordsForSearch[3] = "Бензопилы";
        wordsForSearch[4] = "Газонокосилки";
        wordsForSearch[5] = "Мыши";
        wordsForSearch[6] = "Душевые кабины и уголки";
        wordsForSearch[7] = "Ванны";
        wordsForSearch[8] = "Клавиатуры";

        tempRandomNumber = 0 + (int) (Math.random() * 8);
        System.out.println("Категория которую буду выбирать - " + wordsForSearch[tempRandomNumber]);
        driver.findElement(By.xpath("//*[@placeholder='Раздел']")).click();
        explicitWaiting();
        driver.findElement(By.xpath("//*[@class='filter__SectionsSelect'] //*[contains(text(), '" + wordsForSearch[tempRandomNumber] + "')]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".filter__selected-item")));
        driver.findElement(By.xpath("//*[contains(@class, 'filter')]/*[contains(@class, 'btn_b2b')]")).click();
        explicitWaiting();explicitWaiting();
    }
    public void checkThatAllProductsHaveThisWord(){
        for (int i = 1; i <= driver.findElements(By.xpath("//*[@class='catalog-list__name']")).size(); i++) {
//            System.out.println(i);
//            System.out.println(wordsForCheck[tempRandomNumber]);
//            System.out.println(driver.findElement(By.xpath("(//*[@class='catalog-list__name'])[" + i + "]")).getText());
            tempString = wordsForCheck[tempRandomNumber];
            Assert.assertTrue(driver.findElement(By.xpath("(//*[@class='catalog-list__name'])[" + i + "]")).getText().contains(tempString));
        }
    }
    public void checkingThatOnlyGefestGasStoveIsDisplayedInAdditionalProducts(){
        Assert.assertTrue(driver.findElements(By.xpath("//*[@class='catalog-list__name']")).size() == 1);
        Assert.assertTrue(driver.findElement(By.xpath("//*[@class='catalog-list__name']")).getText().contains("Плита GEFEST"));
    }
    public void navigationToCatalogUsingTheHyperlinkClickHere(){
        driver.navigate().refresh();
        driver.findElement(By.xpath("//*[@class='card-title']//*[contains(@href, 'blank')]")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector(".catalog")).isDisplayed());
    }
    public void selectingSectionByPartOfItsName(){
        driver.findElement(By.xpath("//*[@placeholder='Раздел']")).sendKeys("бенз");
        explicitWaiting();
        //driver.findElement(By.xpath("//*[contains(text(), 'Бензопилы')]")).click();
        driver.findElement(By.cssSelector(".filter__SectionsSelect")).click();
    }
    public void checkingThatSectionFieldIsEmpty(){
        Assert.assertTrue(driver.findElement(By.xpath("//*[@placeholder='Раздел']")).getAttribute("value").equals(""));
    }
    public void applyTheOutputOfThisSection (){
        driver.findElement(By.xpath("//*[contains(@class, 'filter')]/*[contains(@class, 'btn_b2b')]")).click();
    }
    public void checkingThatThePricesInTheCartForAdditionalProductsIsDisplayedAsForSmallOptGroup (){
        for (int i = 1; i < 10; i++) {
            if (driver.findElement(By.xpath("(//*[@class='catalog-list__header']//*[contains(@class, 'catalog-list__column')])[" + i + "]")).getText().contains("цен")){
                count = i;
                break;
            }
        }
        tempDouble2 = Double.valueOf(replacingSomeSymbols(driver.findElement(By.xpath("((//*[@class='catalog-list__body']//*[contains(@class, 'catalog-list__column')])["+ count + "])/*[1]")).getText()));
        Assert.assertTrue(tempDouble == tempDouble2);
        driver.findElement(By.xpath("(//*[@class='catalog-list__body'] //*[@class='input-group-append'])")).click();
        driver.findElement(By.xpath("(//*[@class='catalog-list__body'] //*[@class='form-control'])")).sendKeys(del + "1");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".basket__product-name")));
        explicitWaiting();
        Assert.assertTrue(tempDouble == Double.valueOf(replacingSomeSymbols(driver.findElement(By.xpath("((//*[@class = 'basket__column-price-wrap'])[2])/*[1]")).getText())));
        Assert.assertTrue(tempDouble == Double.valueOf(replacingSomeSymbols(driver.findElement(By.xpath("//*[@class='basket-page__total-price-value']")).getText())));
    }
    public void checkingTheDiscountAmountAndTheAvailablePriceWithoutDiscount (){
        tempDouble = Double.valueOf(replacingSomeSymbols(driver.findElement(By.xpath("//*[@class='basket__full-price-formated']")).getText()));
        for (int i = 1; i <= driver.findElements(By.xpath("//*[@class='basket__header'] //*[contains(@class, 'basket__column ')]")).size() ; i++) {
            if (driver.findElement(By.xpath("(//*[@class='basket__header'] //*[contains(@class, 'basket__column ')])[" + i + "]")).getText().contains("Скидк")){
                count = i;
                break;
            }
        }
        tempDouble2 = Double.valueOf(replacingSomeSymbols(driver.findElement(By.xpath("(//*[contains(@class, 'basket__item')] //*[contains(@class, 'basket__column ')])[" + count + "]")).getText()));
        System.out.println("Доступная пользователю цена - " + basePriceRandomProduct);
        System.out.println("Доступная пользователю цена БЕЗ учета скидки - " +tempDouble);
        System.out.println("Размер скидки - " + tempDouble2);
        Assert.assertTrue(basePriceRandomProduct + tempDouble2 == tempDouble );
    }
    public void returningSettingsBackIfCatalogBroken(){
        navigationToCatalogTab();
        if (driver.findElements(By.xpath("//*[contains(@class, 'icon-question')]")).size() > 0
                || driver.findElement(By.xpath("//*[@class='product__link']")).getText().contains("Тапочки")
                || driver.findElement(By.xpath("(//*[@class='item-quantity__general'])[3]")).getText().equals("0")){
            navigationToSystemSettings();
            navigationToSettingOfQuantitativeAccountingForTheProduct();
            enableQuantitativeAccountingForTheProductsInCatalog();
            navigationToGasStoveSetting();
            returningQuantitativeAccountingAtTheGefestGasStoveByDefault();
            returningTheKaiserGasStoveSettingByDefault();
            navigationToMeanPageByUrl();
            navigationToCatalogTab();
            tryTurnOffShowTheQuantityOfProductsInStorage();
            navigationToComponentOfCatalogSetting();
            choiceStandardCatalog();
            driver.navigate().refresh();
        }
    }








}
