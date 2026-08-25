package it.academy;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

/**
 * ╔══════════════════════════════════════════════════════════════════════════════╗
 * ║  LESSON 7: TestNG Dependency Injection & ITestContext                      ║
 * ╠══════════════════════════════════════════════════════════════════════════════╣
 * ║                                                                            ║
 * ║  TestNG can inject special objects into @Test / @Before / @After methods:  ║
 * ║  • ITestContext  — info about the current test run                         ║
 * ║  • ITestResult   — result of the current test method                       ║
 * ║  • Method        — java.lang.reflect.Method of the current test            ║
 * ║  • XmlTest       — the <test> tag from testng.xml                          ║
 * ║                                                                            ║
 * ║  ┌──────────────────────────────────────────────────────────────────┐      ║
 * ║  │  COMPARISON                                                     │      ║
 * ║  ├──────────────┬──────────────────┬───────────────────────────────┤      ║
 * ║  │ TestNG       │ JUnit 4          │ JUnit 5                       │      ║
 * ║  ├──────────────┼──────────────────┼───────────────────────────────┤      ║
 * ║  │ ITestContext │ @Rule TestName   │ TestInfo (injected param)     │      ║
 * ║  │ ITestResult  │ — (none)         │ TestReporter                  │      ║
 * ║  │ Method       │ — (none)         │ TestInfo.getTestMethod()      │      ║
 * ║  │              │                  │                               │      ║
 * ║  │ ⚠ TestNG     │                  │ JUnit 5 also supports DI via │      ║
 * ║  │ natively     │                  │ ParameterResolver extension   │      ║
 * ║  │ supports DI  │                  │                               │      ║
 * ║  └──────────────┴──────────────────┴───────────────────────────────┘      ║
 * ╚══════════════════════════════════════════════════════════════════════════════╝
 */
public class T08_DependencyInjectionTest {

    private UserService userService;

    @BeforeMethod
    public void setUp(Method method) {
        // TestNG injects java.lang.reflect.Method — the current test method
        System.out.println("  ▸ Setting up for test: " + method.getName());
        userService = new UserService();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        // TestNG injects ITestResult — contains the outcome of the test
        String status = switch (result.getStatus()) {
            case ITestResult.SUCCESS -> "PASSED ✓";
            case ITestResult.FAILURE -> "FAILED ✗";
            case ITestResult.SKIP -> "SKIPPED ⊘";
            default -> "UNKNOWN";
        };
        System.out.println("  ▸ Test " + result.getName() + " finished: " + status);
        System.out.println("  ▸ Duration: " + (result.getEndMillis() - result.getStartMillis()) + "ms");
    }

    @Test(groups = "lesson07")
    public void testAddUser() {
        userService.addUser("Alice");
        Assert.assertEquals(userService.getUserCount(), 1);
    }

    @Test(groups = "lesson07")
    public void testFindUser() {
        userService.addUser("Bob");
        Assert.assertTrue(userService.findUser("bob").isPresent(),
                "Should find user by case-insensitive search");
    }

    // ─────────── Using ITestContext ───────────
    // ITestContext gives you information about the entire test run.

    @Test(groups = "lesson07")
    public void testWithContext(ITestContext context) {
        System.out.println("  ▸ Suite name: " + context.getSuite().getName());
        System.out.println("  ▸ Test name: " + context.getName());
        System.out.println("  ▸ Output directory: " + context.getOutputDirectory());

        // You can store/retrieve attributes across tests via ITestContext
        context.setAttribute("sharedData", "Hello from testWithContext");

        Assert.assertNotNull(context.getSuite().getName());
    }

    // ─────────── Sharing data via ITestContext ───────────

    @Test(groups = "lesson07", dependsOnMethods = "testWithContext")
    public void testReadSharedData(ITestContext context) {
        String data = (String) context.getAttribute("sharedData");
        System.out.println("  ▸ Retrieved shared data: " + data);
        Assert.assertEquals(data, "Hello from testWithContext");
    }
}

