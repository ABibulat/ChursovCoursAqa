import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;

public class UiTestHomeWork6 {
    WebDriver driver = new ChromeDriver();
    private static final String WEB_FORM_URL = "https://bonigarcia.dev/selenium-webdriver-java/web-form.html";
    private static final String NAVIGATION_1_URL = "https://bonigarcia.dev/selenium-webdriver-java/navigation1.html";
    private static final String NAVIGATION_2_URL = "https://bonigarcia.dev/selenium-webdriver-java/navigation2.html";
    private static final String NAVIGATION_3_URL = "https://bonigarcia.dev/selenium-webdriver-java/navigation3.html";
    private static final String DRAG_AND_DROP_URL = "https://bonigarcia.dev/selenium-webdriver-java/drag-and-drop.html";
    private static final String DROPDOWN_URL = "https://bonigarcia.dev/selenium-webdriver-java/dropdown-menu.html";
    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/index.html";
    private static final String fishText="Заполнил поле";
    private static final String dropdownText="Action\n" +
            "Another action\n" +
            "Something else here\n" +
            "Separated link";
    @BeforeEach
            void setup(){
        driver.get(WEB_FORM_URL);
        driver.manage().window().fullscreen();
    }

    @AfterEach
    void tearDown(){
        driver.quit();
    }
    @Tag("WebForm_tests")
    @Test
    void textRecordImputTest(){
        WebElement imput = driver.findElement(By.id("my-text-id"));
        imput.sendKeys(fishText);
        Assertions.assertEquals(fishText,imput.getAttribute("value"));
    }
    @Tag("WebForm_tests")
    @Test
    void textRecordPasswordTest(){
        WebElement passwordLocator= driver.findElement(By.name("my-password"));
        passwordLocator.sendKeys(fishText);
        Assertions.assertEquals(fishText,passwordLocator.getAttribute("value"));
    }
    @Tag("WebForm_tests")
    @Test
    void textRecordTextAreaTest(){
        WebElement textAreaLocator = driver.findElement(By.name("my-textarea"));
        textAreaLocator.sendKeys(fishText);
        Assertions.assertEquals(fishText,textAreaLocator.getAttribute("value"));
    }
    @Tag("WebForm_tests")
    @Test //Объеденить тесты этот и следующий?
    void disabledLocatorFindTest(){
        String disabledText = driver.findElement(By.name("my-disabled"))
                .getAttribute("placeholder");
        Assertions.assertEquals( "Disabled input", disabledText);
    }
    @Tag("WebForm_tests")
    @Test
    void textRecordDisabledTest(){
        WebElement disabledLocator = driver.findElement(By.name("my-disabled"));
        Boolean isDisabled = disabledLocator.isDisplayed();
        Assertions.assertTrue(isDisabled);
    }
    @Tag("WebForm_tests")
    @Test
    void textRecordReadonlyTest(){
        WebElement readonlyLocator=driver.findElement(By.name("my-readonly"));
        String getDisabledText = readonlyLocator
                .getAttribute("value");
        Boolean isDisabled=readonlyLocator.isEnabled();
        Assertions.assertEquals( "Readonly input", getDisabledText);
        Assertions.assertTrue(isDisabled);
    }
    @Tag("WebForm_tests")
    @Test
    void returnToIndexClickTest(){
        driver.findElement(By.linkText("Return to index")).click();
        Assertions.assertEquals(driver.getCurrentUrl(),BASE_URL);
    }
    @Tag("WebForm_tests")
    @Test
    void dropdownFirstFindTest(){
        WebElement dropdown = driver.findElement(By.name("my-select"));
        Select select = new Select(dropdown);
        Assertions.assertEquals(select.getFirstSelectedOption().getText(),"Open this select menu");
    }
    @Tag("WebForm_tests")
    @ParameterizedTest
    @CsvSource ( {"1, One","2, Two","3, Three"})
    void dropdownLocatorTest(String number, String textNumber){
        WebElement dropdown = driver.findElement(By.name("my-select"));

        Select select = new Select(dropdown);
        select.selectByValue(number);

        String selectedOptionOne = select.getFirstSelectedOption().getText();
        Assertions.assertEquals(selectedOptionOne, textNumber);
    }
    @Tag("WebForm_tests")
    @ParameterizedTest
    @CsvSource ( {"San Francisco","New York","Seattle","Los Angeles","Chicago", fishText})
    void dropdownListLocator(String textNumber){
        WebElement dropdown = driver.findElement(By.name("my-datalist"));

        dropdown.sendKeys(textNumber);
        String enteredText = dropdown.getAttribute("value");
        Assertions.assertEquals(textNumber, enteredText);
    }
    @Tag("WebForm_tests")
    @Test
    void fileInputTest(){
        String filePath= "/Users/a1111/IdeaProjects/HomeWork5/src/test/resources/file.jpg";
        WebElement fileInput = driver.findElement(By.name("my-file"));
        fileInput.sendKeys(filePath);
        String uploadFileName = fileInput.getAttribute("value");
        Assertions.assertTrue(uploadFileName.contains("file.jpg"));
    }
    @Tag("WebForm_tests")
    @Test
    void checkedLocatorTest(){
        WebElement checked = driver.findElement(By.id("my-check-1"));
        checked.click();
        String textCheked = driver.findElement(By.cssSelector("label.form-check-label.w-100")).getText();
        Assertions.assertEquals(textCheked, "Checked checkbox");
    }
    @Tag("WebForm_tests")
    @Test
    void defaultLocator(){
        WebElement checked = driver.findElement(By.id("my-check-2"));
        checked.click();
        String textDefault = driver.findElement(By.xpath("//div/div[2]/div[1]/label[2]")).getText();
        Assertions.assertEquals(textDefault, "Default checkbox");
    }
    @Tag("WebForm_tests")
    @Test
    void checkedRadioLocator(){
        String textCheked = driver.findElement(By.xpath("//div[2]/div[2]/label")).getText();
        Assertions.assertEquals(textCheked, "Checked radio");
    }
    @Tag("WebForm_tests")
    @Test
    void defaultRadioLocator(){
        WebElement checked = driver.findElement(By.id("my-radio-2"));
        checked.click();
        String textDefault = driver.findElement(By.xpath("//div[2]/div[3]/label")).getText();
        Assertions.assertEquals(textDefault, "Default radio");
    }
    @Tag("WebForm_tests")
    @Test
    void submitClickTest(){
        WebElement checked = driver.findElement(By.className("btn-outline-primary"));
        checked.click();
        WebElement text = driver.findElement(By.className("display-6"));
        Assertions.assertEquals("Form submitted",text.getText() );
    }
    @Tag("WebForm_tests")
    @Test
    void colorLocatorTest() throws InterruptedException {
        WebElement colorLocator = driver.findElement(By.name("my-colors"));
        Actions action = new Actions(driver);
        action.moveToElement(colorLocator).perform();
        Thread.sleep(1000);
        action.click(colorLocator).perform();
        Thread.sleep(1000);
        colorLocator.sendKeys("#e6dcf5");
        Thread.sleep(1000);
        Assertions.assertEquals("#e6dcf5",colorLocator.getAttribute("value"));
    }
    @Tag("WebForm_tests")
    @Test
    void dateLocatorTest(){
        driver.findElement(By.name("my-date")).click();
        WebElement dateLoc = driver.findElement(By.name("my-date"));
        dateLoc.sendKeys("18/03/2025");
        Assertions.assertEquals("18/03/2025",dateLoc.getAttribute("value"));
    }
    @Tag("WebForm_tests")
    @Test
    void rangeLocatorTest() throws InterruptedException {
        WebElement rangLoc = driver.findElement(By.cssSelector("input.form-range"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i=0;i<10;i++){
        js.executeScript("arguments[0].value = arguments[1];", rangLoc, i);
        String newValue = rangLoc.getAttribute("value");
        Thread.sleep(100);
        Assertions.assertEquals(newValue, String.valueOf(i));
        }
    }
    @Tag("Navigation_tests")
    @Test
    void backToIndexTest(){
        driver.get(NAVIGATION_1_URL);
        driver.findElement(By.linkText("Back to index")).click();

        Assertions.assertEquals(BASE_URL,driver.getCurrentUrl());
    }
    @Tag("Navigation_tests")
    @Test
    void previousDisabledTest(){
        driver.get(NAVIGATION_1_URL);
        Boolean previousIsDisabled = driver.findElement(By.className("page-item")).isDisplayed();
        Assertions.assertTrue(previousIsDisabled);
    }
    @Tag("Navigation_tests")
    @Test
    void openNavigation2pageTest(){
        driver.get(NAVIGATION_1_URL);
        WebElement openNavigation2 = driver.findElement(By.linkText("2"));
        openNavigation2.click();
        Assertions.assertEquals(NAVIGATION_2_URL,driver.getCurrentUrl());
    }
    @Tag("Navigation_tests")
    @Test
    void nextPageTest(){
        driver.get(NAVIGATION_1_URL);
        WebElement openNavigation2 = driver.findElement(By.linkText("Next"));
        openNavigation2.click();
        Assertions.assertEquals(NAVIGATION_2_URL,driver.getCurrentUrl());
    }
    @Tag("Navigation_tests")
    @Test
    void previousPageTest(){
        driver.get(NAVIGATION_2_URL);
        WebElement previous = driver.findElement(By.linkText("Previous"));
        previous.click();
        Assertions.assertEquals(NAVIGATION_1_URL,driver.getCurrentUrl());
    }
    @Tag("Navigation_tests")
    @Test
    void openNavigation3pageTest(){
        driver.get(NAVIGATION_2_URL);
        WebElement previous = driver.findElement(By.linkText("3"));
        previous.click();
        Assertions.assertEquals(NAVIGATION_3_URL,driver.getCurrentUrl());
    }
    @Tag("Navigation_tests")
    @Test
    void nextIsDisabledTest(){
        driver.get(NAVIGATION_3_URL);
        Boolean nextIsDisabled = driver.findElement(By.linkText("Next")).isDisplayed();
        Assertions.assertTrue(nextIsDisabled);
    }
    @ParameterizedTest
    @CsvFileSource(resources = "testdata.csv",numLinesToSkip = 0)
    @Tag("Navigation_tests")
    void textPageFindTest(String url, String expectedText) throws InterruptedException {
        driver.get(url);

        WebElement leadElement = driver.findElement(By.className("lead"));
        String actualText = leadElement.getText().trim();

        Assertions.assertEquals(expectedText, actualText, "Текст на странице не совпадает!");
    }
    @Tag("Dropdown_tests")
    @Test
    void findElementLeftClick() throws InterruptedException {
        driver.get(DROPDOWN_URL);
        Actions actions = new Actions(driver);
        WebElement dropdownLeft=driver.findElement(By.id("my-dropdown-1"));
        actions.click(dropdownLeft).perform();
        Thread.sleep(1000);
        String actualDropdownText =driver.findElement(By.className("dropdown-menu")).getText();
        Assertions.assertEquals(dropdownText,actualDropdownText);
    }
    @Tag("Dropdown_tests")
    @Test
    void findElementRightClick() throws InterruptedException {
        driver.get(DROPDOWN_URL);
        Actions actions = new Actions(driver);
        WebElement dropdownLeft=driver.findElement(By.id("my-dropdown-2"));
        actions.contextClick(dropdownLeft).perform();
        Thread.sleep(1000);
        String actualDropdownText =driver.findElement(By.id("context-menu-2")).getText();
        Assertions.assertEquals(dropdownText,actualDropdownText);
    }
    @Tag("Dropdown_tests")
    @Test
    void findElementDoubleClick() throws InterruptedException {
        driver.get(DROPDOWN_URL);
        Actions actions = new Actions(driver);
        WebElement dropdownLeft=driver.findElement(By.id("my-dropdown-3"));
        actions.doubleClick(dropdownLeft).perform();
        Thread.sleep(1000);
        String actualDropdownText =driver.findElement(By.id("context-menu-3")).getText();
        Assertions.assertEquals(dropdownText,actualDropdownText);
    }
    @Tag("DragAndDrop_tests")
    @Test
    void setDragAndDropTest() throws InterruptedException {
        driver.get(DRAG_AND_DROP_URL);
        Actions actions= new Actions(driver);
        WebElement sourceElement = driver.findElement(By.id("draggable"));
        WebElement targetElement = driver.findElement(By.id("target"));
        actions.dragAndDrop(sourceElement,targetElement).perform();
        Thread.sleep(2000);
    }
}
