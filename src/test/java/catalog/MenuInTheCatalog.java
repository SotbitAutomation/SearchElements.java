package catalog;


import base_actions.Retry;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class MenuInTheCatalog extends MethodsForCatalog {
//    @Test (retryAnalyzer = Retry.class) //0. Включить вывод каталога в меню
//    public void a_enableCatalogOutputInTheMenu() {
//        //arrange
//        navigationToAuthorizationTab();
//        //act
//        fillingFieldsOnTheLogInTabLikeAdmin();
//        logInToB2B();
//        configuringDirectoryOutputInTheNavigationMenu();
//    }
    @Test (retryAnalyzer = Retry.class) //1. Переключение между разделами различного уровня вложенности
    public void switchingBetweenSectionsOfDifferentNestingLevels() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        choiceRandomCategoryInMenuCatalog(false);
        //expandCatalogCategories(false);
        choiceRandomUnderCategoryOfTheSelectedCategory();
        checkingThatBreadCrumbHaveSelectedCategories();
        checkingThatAllProductsHaveASimilarIdToTheSectionId();
    }
    @Test (retryAnalyzer = Retry.class) //2. Вывод меню, когда основное меню свернуто
    public void menuOutputWhenTheMainMenuIsMinimized() {
        determineThemeColor();
        if (themeColorBlack) {
            //arrange
            navigationToAuthorizationTab();
            //act
            fillingFieldsOnTheLogInTabLikeUser();
            logInToB2B();
            expandCatalogCategories(false);
            hideTheMenu();
            driver.findElement(iconCatalogLocator).click();
            hoveringTheCursorOverTheElement(iconCatalogLocator);
            choiceRandomCategoryInMenuCatalog(true);
            hoveringTheCursorOverTheElement(iconCatalogLocator);
            choiceRandomUnderCategoryOfTheSelectedCategory();
            checkingThatBreadCrumbHaveSelectedCategories();
            checkingThatAllProductsHaveASimilarIdToTheSectionId();
            unHideTheMenu();
        }
    }
    @Test (retryAnalyzer = Retry.class) //3. Вывод меню слева/справа
    public void menuOutputLeftRight() {
        determineThemeColor();
        if (themeColorBlack) {
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
    }
    @Test (retryAnalyzer = Retry.class) //4. Фильтрация товаров в выбранном разделе каталога первого уровня по цене (максимальной)
    public void filteringProductsInTheSelectedSectionOfTheFirstLevelCatalogByPriceMaximum() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        choiceRandomCategoryInMenuCatalog(false);
        determineMaxPriceForFilteringAThousandLessThanThePriceOfTheMostExpensiveProductInThisSection();
        enteringTheMaxPriceIntoTheFilter();
        checkThatTheMaximumPriceIsAppliedOnAllPages();
    }
    @Test (retryAnalyzer = Retry.class) //5. Фильтрация товаров в выбранном разделе каталога первого уровня по цене (минимальной)
    public void filteringProductsInTheSelectedSectionOfTheFirstLevelCatalogByPriceMinimum() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        choiceRandomCategoryInMenuCatalog(false);
        determineMinPriceForFilteringAThousandMoreThanThePriceOfTheMostCheapProductInThisSection();
        enteringTheMinPriceIntoTheFilter();
        checkThatTheMinimumPriceIsAppliedOnAllPages();
    }
    @Test (retryAnalyzer = Retry.class) //6. Фильтрация товаров в выбранном разделе каталога второго уровня по цене (максимальной)
    public void filteringProductsInTheSelectedUnderSectionOfTheFirstLevelCatalogByPriceMaximum() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        choiceRandomCategoryInMenuCatalog(false);
        //expandCatalogCategories(false);
        choiceRandomUnderCategoryOfTheSelectedCategory();
        determineMaxPriceForFilteringAThousandLessThanThePriceOfTheMostExpensiveProductInThisSection();  //может упасть (если в разделе нет цен)
        enteringTheMaxPriceIntoTheFilter();
        checkThatTheMaximumPriceIsAppliedOnAllPages();
    }
    @Test (retryAnalyzer = Retry.class) //7. Фильтрация товаров в выбранном разделе каталога второго уровня по цене (минимальной)
    public void filteringProductsInTheSelectedUnderSectionOfTheFirstLevelCatalogByPriceMinimum() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        choiceRandomCategoryInMenuCatalog(false);
        //expandCatalogCategories(false);
        choiceRandomUnderCategoryOfTheSelectedCategory();
        determineMinPriceForFilteringAThousandMoreThanThePriceOfTheMostCheapProductInThisSection(); //возможно падает из-за того что в выбранном разделе нет товаров в наличии
        enteringTheMinPriceIntoTheFilter();
        checkThatTheMinimumPriceIsAppliedOnAllPages();
    }
    @Test (retryAnalyzer = Retry.class) //8. Проверка корректности вывода релевантных свойств фильтра
    public void checkingCorrectnessOfOutputOfRelevantFilterProperties() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToCatalogTab();
        choiceRandomCategoryInMenuCatalog(false);
        //expandCatalogCategories(false);
        choiceRandomUnderCategoryOfTheSelectedCategory();
        checkingThatQuantityOfPropertiesIsHadDecreased();
    }
    @Test (retryAnalyzer = Retry.class) //9. Выбор первого свойства в рандомной  категории каталога
    public void selectingTheFirstPropertyInARandomCatalogCategory() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToCatalogTab();
        choiceRandomCategoryInMenuCatalog(false);
        rememberQuantityProductsOnThisPage();
        applyTheFirstProperty();
        checkingThatQuantityProductsOnThisPageAreDecreased();
    }
    @Test (retryAnalyzer = Retry.class) //10. Выбор первого свойства в рандомной подкатегории каталога
    public void selectingTheFirstPropertyInARandomCatalogUnderCategory() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        navigationToCatalogTab();
        choiceRandomCategoryInMenuCatalog(false);
        //expandCatalogCategories(false);
        choiceRandomUnderCategoryOfTheSelectedCategory();
        rememberQuantityProductsOnThisPage();
        applyTheFirstProperty();
        checkingThatQuantityProductsOnThisPageAreDecreased();
    }
    @Test (retryAnalyzer = Retry.class) //11. Переключение между разделами в всплывающем меню
    public void switchBetweenSectionsInThePopUpMenu() {
        determineThemeColor();
        if (themeColorBlack) {
            //arrange
            navigationToAuthorizationTab();
            //act
            fillingFieldsOnTheLogInTabLikeUser();
            logInToB2B();
            expandCatalogCategories(false);
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

