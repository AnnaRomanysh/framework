package apitest;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import utils.TestLogger;

import java.lang.reflect.Method;

@Listeners(utils.TestListener.class)
public class BaseTest {
    public TestLogger log;


    @BeforeMethod
    public void beforeMethod(Method method) {
        log = TestLogger.getLogger(method.getName(), method.getDeclaringClass().getSimpleName(), method.getDeclaringClass().getPackage().getName());

    }

}
