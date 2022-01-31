package SettingUpCabinetForTesting;

import org.junit.Test;

public class SettingUpCabinetForTesting extends MethodsForSettingUpCabinetForTesting {

    @Test //1. Создание дополнительных типов плательщиков и их свойств
    public void creatingAdditionalBuyerTypesAndTheirProperties() {
        //arrange
        navigationToAuthorizationTab();
        //act
        fillingFieldsOnTheLogInTabLikeAdmin();
        logInToB2B();
        navigationToBuyerTypes();
        creatingBuyerTypes();
        navigationToListOfProperties();
        creatingPropertiesForTheseBuyerTypes();
    }
}
