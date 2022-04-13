package AccessToB2BWithoutAuthorization;

import BaseActions.Retry;
import org.testng.annotations.Test;

public class AccessToB2BWithoutAuthorization extends MethodsForAccessToB2BWithoutAuthorization{
    @Test(retryAnalyzer = Retry.class) //1. asd
    public void asd() {
        //arrange
        firsNavigationToB2B();
        navigationToCatalogTab();

    }
}
