package utils;


import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public abstract class APIListener  implements ITestListener {
    private TestLogger log;
    private String testName;
    private String className;
    private String  packageName;

    private ByteArrayOutputStream request = new ByteArrayOutputStream();
    private ByteArrayOutputStream response = new ByteArrayOutputStream();

    private PrintStream requestVar = new PrintStream(request, true);
    private PrintStream responseVar = new PrintStream(response, true);

    public void onTestStart(ITestResult result) {
        log = TestLogger.getLogger(testName, className, packageName);
        log.info("Test: " +result.getClass() +" "+ result.getName() + " is started");
        RestAssured.filters(new ResponseLoggingFilter(LogDetail.ALL, responseVar),
                new RequestLoggingFilter(LogDetail.ALL, requestVar));
    }

//    public void onTestSuccess(ITestResult result) {
//        log.info("Test: "  +result.getClass() +" "+ result.getName() + " passed");
//        logRequest(request);
//        logResponse(response);
//    }


//    public void onTestFailure(ITestResult result) {
//        log.error("Test: " +result.getClass() +" "+ result.getName() + " FAILED");
//        onTestSuccess(result)
//    }
//    @Atta(value = "request")
//    public byte[] logRequest(ByteArrayOutputStream stream) {
//        return attach(stream);
//    }
//
//    @Attachment(value = "response")
//    public byte[] logResponse(ByteArrayOutputStream stream) {
//        return attach(stream);
//    }
//
//    public byte[] attach(ByteArrayOutputStream log) {
//        byte[] array = log.toByteArray();
//        log.reset();
//        return array;
//    }
    public void onTestSkipped(ITestResult result) {
        log.error("Test: " +result.getClass() +" "+ result.getName()+ " skipped");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    public void onStart(ITestContext context) {

    }

    public void onFinish(ITestContext context) {
        log.error("Test: " +context.getClass() +" "+ context.getName() + "is finished");;
    }
}
