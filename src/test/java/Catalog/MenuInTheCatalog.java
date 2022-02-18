package Catalog;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MenuInTheCatalog extends MethodsForCatalog {
    @Test //0. Включить вывод каталога в меню
    public void a_enableCatalogOutputInTheMenu() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        if (driver.findElements(By.xpath("//*[contains(@class, 'nav-item-submenu')] /*[contains(@href, 'blank_zakaza')]")).size()==0){
            driver.navigate().to(b2bUrl.replaceAll("b2bcabinet/" , "") + "bitrix/admin/sotbit.b2bcabinet_settings.php?mid=sotbit.b2bcabinet&lang=ru&site=s1");
            driver.findElement(By.xpath("//*[contains(@title, 'Каталог ')]")).click();
            driver.findElement(By.cssSelector("#CATALOG_SHOW_SECTIONS")).click();
            driver.findElement(By.xpath("//*[@value='MENU']")).click();
            driver.findElement(buttonSaveLocator).click();
            navigationToMeanPageByUrl();
        }
        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(@class, 'nav-item-submenu')] /*[contains(@href, 'blank_zakaza')]")).isDisplayed());
    }
    @Test //1. Переключение между разделами различного уровня вложенности
    public void switchingBetweenSectionsOfDifferentNestingLevels() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        expandCatalogCategories();
        choiceRandomCategoryInMenuCatalog();
        checkingThatAllProductsHaveASimilarIdToTheSectionId();
        choiceRandomUnderCategoryOfTheSelectedCategory();
        checkingThatBreadCrumbHaveSelectedCategories();
        checkingThatAllProductsHaveASimilarIdToTheSectionId();
    }
    @Test //2. Вывод меню, когда основное меню свернуто
    public void menuOutputWhenTheMainMenuIsMinimized() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        expandCatalogCategories();
        hideTheMenu();
        driver.findElement(iconCatalogLocator).click();
        hoveringTheCursorOverTheElement(iconCatalogLocator);
        choiceRandomCategoryInPopUpMenuCatalog();
        checkingThatAllProductsHaveASimilarIdToTheSectionId();
        hoveringTheCursorOverTheElement(iconCatalogLocator);
        choiceRandomUnderCategoryOfTheSelectedCategory();
        checkingThatBreadCrumbHaveSelectedCategories();
        checkingThatAllProductsHaveASimilarIdToTheSectionId();
        unHideTheMenu();
    }
    @Test //3. Вывод меню слева/справа
    public void menuOutputLeftRight() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        if (driver.findElements(By.xpath("//*[contains(@class, 'b2bcabinet-sidebar-right')]")).size()==0){
            configureTheMenuOutputToTheRightSideOfTheScreen();
            checkingThatTheMenuIsOnTheRightSideOfTheScreen();
            configureTheMenuOutputToTheLeftSideOfTheScreen();
            checkingThatTheMenuIsOnTheLeftSideOfTheScreen();
        }else {
            configureTheMenuOutputToTheLeftSideOfTheScreen();
            checkingThatTheMenuIsOnTheLeftSideOfTheScreen();
            configureTheMenuOutputToTheRightSideOfTheScreen();
            checkingThatTheMenuIsOnTheRightSideOfTheScreen();
        }
    }
    @Test //4. Фильтрация товаров в выбранном разделе каталога первого уровня по цене (максимальной)
    public void filteringProductsInTheSelectedSectionOfTheFirstLevelCatalogByPriceMaximum() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        expandCatalogCategories();
        choiceRandomCategoryInMenuCatalog();
        determineMaxPriceForFilteringAThousandLessThanThePriceOfTheMostExpensiveProductInThisSection();
        enteringTheMaxPriceIntoTheFilter();
        checkThatTheMaximumPriceIsAppliedOnAllPages();
    }
    @Test //5. Фильтрация товаров в выбранном разделе каталога первого уровня по цене (минимальной)
    public void filteringProductsInTheSelectedSectionOfTheFirstLevelCatalogByPriceMinimum() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        expandCatalogCategories();
        choiceRandomCategoryInMenuCatalog();
        determineMinPriceForFilteringAThousandMoreThanThePriceOfTheMostCheapProductInThisSection();
        enteringTheMinPriceIntoTheFilter();
        checkThatTheMinimumPriceIsAppliedOnAllPages();
    }
    @Test //6. Фильтрация товаров в выбранном разделе каталога второго уровня по цене (максимальной)
    public void filteringProductsInTheSelectedUnderSectionOfTheFirstLevelCatalogByPriceMaximum() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        expandCatalogCategories();
        choiceRandomCategoryInMenuCatalog();
        choiceRandomUnderCategoryOfTheSelectedCategory();
        determineMaxPriceForFilteringAThousandLessThanThePriceOfTheMostExpensiveProductInThisSection();
        enteringTheMaxPriceIntoTheFilter();
        checkThatTheMaximumPriceIsAppliedOnAllPages();
    }
    @Test //7. Фильтрация товаров в выбранном разделе каталога второго уровня по цене (минимальной)
    public void filteringProductsInTheSelectedUnderSectionOfTheFirstLevelCatalogByPriceMinimum() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        expandCatalogCategories();
        choiceRandomCategoryInMenuCatalog();
        choiceRandomUnderCategoryOfTheSelectedCategory();
        determineMinPriceForFilteringAThousandMoreThanThePriceOfTheMostCheapProductInThisSection();
        enteringTheMinPriceIntoTheFilter();
        checkThatTheMinimumPriceIsAppliedOnAllPages();
    }
    @Test //8. Проверка корректности вывода релевантных свойств фильтра
    public void checkingCorrectnessOfOutputOfRelevantFilterProperties() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToCatalogTab();
        choosingRandomCategory();
        checkingThatQuantityOfPropertiesIsHadDecreased();
    }
    @Test //9. Выбор первого свойства в рандомной  категории каталога
    public void selectingTheFirstPropertyInARandomCatalogCategory() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToCatalogTab();
        choosingRandomCategory();
        rememberQuantityProductsOnThisPage();
        applyTheFirstProperty();
        checkingThatQuantityProductsOnThisPageAreDecreased();
    }
    @Test //10. Выбор первого свойства в рандомной подкатегории каталога
    public void selectingTheFirstPropertyInARandomCatalogUnderCategory() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToCatalogTab();
        choosingRandomCategory();
        choiceRandomUnderCategoryOfTheSelectedCategory();
        rememberQuantityProductsOnThisPage();
        applyTheFirstProperty();
        checkingThatQuantityProductsOnThisPageAreDecreased();
    }
    @Test //11. Переключение между разделами в всплывающем меню
    public void switchBetweenSectionsInThePopUpMenu() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        expandCatalogCategories();
        hideTheMenu();
        driver.findElement(iconCatalogLocator).click();
        hoveringTheCursorOverTheElement(iconCatalogLocator);
        choiceRandomCategoryInPopUpMenuCatalog();
        checkingThatAllProductsHaveASimilarIdToTheSectionId();
        hoveringTheCursorOverTheElement(iconCatalogLocator);
        choiceRandomCategoryInPopUpMenuCatalog();
        checkingThatBreadCrumbHaveSelectedCategories();
        checkingThatAllProductsHaveASimilarIdToTheSectionId();
        unHideTheMenu();
    }



//    @Test
//    public void circle() {
//        for (int i = 0; i < 20; i++) {
//
//            //Сюда тест
//            selectingTheFirstPropertyInARandomCatalogUnderCategory();
//            exitFromB2B();
//            selectingTheFirstPropertyInARandomCatalogCategory();
//            exitFromB2B();
//        }
//    }
}

