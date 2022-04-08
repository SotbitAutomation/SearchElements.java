package BaseActions;
//111333555
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

    public class Retry implements IRetryAnalyzer {
        private int actualRetry = 0;
        private static final int MAX_RETRY = 0;
        @Override
        public boolean retry(ITestResult result) {
            if (actualRetry < MAX_RETRY){
                actualRetry++;
                return true;
            }else {
                return false;
            }
        }
    }

