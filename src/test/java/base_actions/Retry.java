package base_actions;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
    /*
       В случае падения теста запускает этот тест еще MAX_RETRY кол-во раз
     */
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

