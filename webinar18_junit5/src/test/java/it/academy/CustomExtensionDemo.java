package it.academy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * ═══════════════════════════════════════════════════════════════════════════════
 * DEMO 14: EXTENSIONS MODEL
 * ═══════════════════════════════════════════════════════════════════════════════
 *
 * Run this demo separately:
 *   mvn test -Dtest="CustomExtensionDemo"
 *
 * ★ JUNIT 5 ONLY — Extensions API (@ExtendWith)
 *
 * JUnit 5 replaces JUnit 4's @Rule, @ClassRule, and @RunWith with a SINGLE,
 * unified Extension model.
 *
 * Extension points include:
 *   - BeforeAllCallback / AfterAllCallback
 *   - BeforeEachCallback / AfterEachCallback
 *   - BeforeTestExecutionCallback / AfterTestExecutionCallback
 *   - ParameterResolver
 *   - TestExecutionExceptionHandler
 *   - TestInstanceFactory
 *   - TestInstancePostProcessor
 *   - ExecutionCondition
 *   - TestWatcher
 *
 * JUnit 4: Used @Rule + TestRule/MethodRule and @RunWith — fragmented approach.
 * TestNG:  Uses Listeners (ITestListener, etc.) — similar but separate mechanism.
 *
 * JUnit 5 extensions can be:
 *   1. Registered declaratively with @ExtendWith
 *   2. Registered programmatically with @RegisterExtension ★
 *   3. Registered globally via ServiceLoader
 */
@ExtendWith(CustomExtensionDemo.TimingExtension.class)  // Declarative registration
@DisplayName("Demo 14 — ★ Extensions Model (JUnit 5 ONLY)")
class CustomExtensionDemo {

    private final Calculator calculator = new Calculator();

    // ─── ★ @RegisterExtension — programmatic registration ──────────────────────

    /**
     * ★ @RegisterExtension — allows programmatic extension registration
     * with constructor parameters. NOT available in JUnit 4 or TestNG.
     */
    @RegisterExtension
    static final LoggingExtension loggingExtension = new LoggingExtension("Calculator Tests");

    @Test
    @DisplayName("Test with timing and logging extensions")
    void testWithExtensions() {
        assertEquals(10, calculator.add(4, 6));
    }

    @Test
    @DisplayName("Another test with extensions")
    void anotherTestWithExtensions() {
        assertEquals(20, calculator.multiply(4, 5));
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // Custom Extension: Timing — measures test execution time
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * ★ Custom Extension implementing multiple extension points.
     * This replaces what would require a @Rule in JUnit 4.
     */
    static class TimingExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

        private static final String START_TIME = "start_time";

        @Override
        public void beforeTestExecution(ExtensionContext context) {
            getStore(context).put(START_TIME, System.currentTimeMillis());
        }

        @Override
        public void afterTestExecution(ExtensionContext context) {
            long startTime = getStore(context).remove(START_TIME, long.class);
            long duration = System.currentTimeMillis() - startTime;
            System.out.printf("  ⏱ %s took %d ms%n",
                    context.getDisplayName(), duration);
        }

        private ExtensionContext.Store getStore(ExtensionContext context) {
            return context.getStore(ExtensionContext.Namespace.create(
                    getClass(), context.getRequiredTestMethod()));
        }
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // Custom Extension: Logging — logs test lifecycle events
    // ═══════════════════════════════════════════════════════════════════════════

    static class LoggingExtension implements BeforeEachCallback, AfterEachCallback {

        private final String suiteName;

        LoggingExtension(String suiteName) {
            this.suiteName = suiteName;
        }

        @Override
        public void beforeEach(ExtensionContext context) {
            System.out.printf("  📝 [%s] Starting: %s%n",
                    suiteName, context.getDisplayName());
        }

        @Override
        public void afterEach(ExtensionContext context) {
            System.out.printf("  📝 [%s] Finished: %s%n",
                    suiteName, context.getDisplayName());
        }
    }
}

