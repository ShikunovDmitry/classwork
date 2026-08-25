package it.academy;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Child test class that inherits from BaseTest.
 * • baseSetUp() from parent runs BEFORE childSetUp()
 * • baseTearDown() from parent runs AFTER each test
 *
 * Execution order:
 *   1. BaseTest.baseSetUp()
 *   2. ChildTest.childSetUp()
 *   3. @Test method
 *   4. BaseTest.baseTearDown()
 */
public class T12_ChildTest extends BaseTest {

    private String childData;

    @BeforeMethod
    public void childSetUp() {
        childData = "child-ready";
        System.out.println("  [ChildTest] @BeforeMethod: childData initialized");
    }

    @Test(groups = "lesson11")
    public void testInheritedSetup() {
        // sharedResource comes from BaseTest.baseSetUp()
        Assert.assertEquals(sharedResource, "initialized",
                "Should have inherited sharedResource from BaseTest");
        Assert.assertEquals(childData, "child-ready");
        System.out.println("  [ChildTest] Test verified inherited setup works");
    }

    @Test(groups = "lesson11")
    public void testChildSpecificLogic() {
        Assert.assertNotNull(sharedResource, "BaseTest should have set up the resource");
        System.out.println("  [ChildTest] Inherited resource is available");
    }
}

