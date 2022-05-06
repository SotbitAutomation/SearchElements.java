package OrderTemplates;

import BaseActions.Retry;
import org.testng.annotations.Test;

public class OrderTemplates extends MethodsForOrderTemplates{

    @Test(retryAnalyzer = Retry.class) //1.  asd
    public void asd() {
        //arrange
        deletingExcelAndJpgFilesFromDownloads();
        navigationToAuthorizationTab();
        fillingFieldsOnTheLogInTabLikeUser();
        logInToB2B();
        deletingProductsFromTheCart();
        //act
        navigationToOrderTemplates();





        downloadingCatalogFromExcel("blank.xlsx");


        downloadingCatalogToYourComputer();
    }
}
