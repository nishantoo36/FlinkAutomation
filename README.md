# FlinkAutomation

---

---
A sample framework based on Page Object Model, Selenium, TestNG using Java.

This framework is based in **Page Object Model (POM).**

The framework uses:

1. Java
2. Selenium
3. TestNG
4. ExtentReport
5. PageFactory
6. Maven


Steps to create test cases:
----
Let's say we want to automate Google search test.

1.Create GoogleSearchPage in **pages** package.  
A page class typically should contain all the elements that are present on the page and corresponding action methods.

  ```
  public class GooglePage extends SeleniumUtility {
	
	@FindBy(name = "q")
	private WebElement searchinput;
	public GooglePage() {
		super();
	}
	public void searchText(String key) {
		searchinput.sendKeys(key + Keys.ENTER);
	}
}
```

2.Create the test class which class the methods of GoogleSearchPage

```
@Test(testName = "Google search test", description = "Test description")
public class GoogleSearchTest extends BaseTest {
	@Test
	public void googleSearchTest() {
		GooglePage googlePage = new GooglePage();
		googlePage.searchText("abc");
		Assert.assertTrue(driver.getTitle().contains("abc"), "Title doesn't contain abc : Test Failed");
	}
}

3.Add the test class in testng.xml file under the folder `src/test/resources/suites/`

```
<suite name="Suite">
	<listeners></listeners>
	<test thread-count="5" name="Test" parallel="classes">
		<classes>
			<class name="example.example.tests.GoogleSearchTest" /> 

4.Execute the test cases by maven command `mvn clean test`

---

Reproting
---
The framework gives report in Html ways,

A html report - Which is generated using extent reports, under the folder `ExtentReports`.


Key Points:
---

1. The class `SeleniumUtility` is responsible for maintaining the same WebDriver instance throughout the test. So whenever you require a webdriver instance which has been using for current test (In current thread) always call `getDriver()`.
2. Always when you page change the last method of that should return object of next page class.
3. This test is running right now on Chrome and Safari(Note: To run of safari need to enable Develop > Allow Remote Automation)
4. Also, you can add support for other browser too.
5. As of now test data is static and taken from constant java file. This also we can change and take it from command line or from property file.
---
---
TestCase: E2E test case is written in <b>"src/test/e2eCase.txt"</b>.


>For any query or suggestions please do comment or mail @ pnishant90@gmail.com 