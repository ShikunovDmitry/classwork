package it.academy;

import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LifecycleTest {
  private static DatabaseConnection db;
  private UserService userService;

  @BeforeClass
  public static void setUpClass() {
    System.out.println(">>> @BeforeClass: opening database connection");
    db = new DatabaseConnection();
    db.open();
  }

  @Before
  public void setUp() {
    System.out.println("  >>> @Before: creating UserService");
    userService = new UserService();
  }

  @Test
  public void testAddUser() {
    System.out.println("    [TEST] testAddUser");
    userService.addUser("Alice");
    assertEquals(1, userService.getUserCount());
    assertTrue(db.isConnected());
  }

  @Test
  public void testRemoveUser() {
    System.out.println("    [TEST] testRemoveUser");
    userService.addUser("Bob");
    userService.removeUser("Bob");
    assertEquals(0, userService.getUserCount());
  }

  @Test
  public void testUserServiceIsResetBetweenTests() {
    System.out.println("    [TEST] testUserServiceIsResetBetweenTests");
    assertEquals("Each test gets a fresh UserService", 0, userService.getUserCount());
  }

  @After
  public void tearDown() {
    System.out.println("  >>> @After: clearing UserService");
    userService.clear();
  }

  @AfterClass
  public static void tearDownClass() {
    System.out.println(">>> @AfterClass: closing database connection");
    db.close();
  }
}
