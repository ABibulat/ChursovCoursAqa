import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.nio.file.Paths;
import java.sql.Driver;
public class LocatorFindTest {
    WebDriver driver = new ChromeDriver();
    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/web-form.html";
    private static String fishText="Заполнил поле";

    @BeforeEach
            void setup(){
        driver.get(BASE_URL);
        driver.manage().window().fullscreen();
    }

    @AfterEach
    void tearDown(){
        driver.quit();
    }
    @Test
    void textImputLocator(){
        driver.findElement(By.id("my-text-id"))
                .sendKeys(fishText);
    }
    @Test
    void passwordLocator(){
        driver.findElement(By.name("my-password"))
                .sendKeys(fishText);
    }
    @Test
    void textAreaLocator(){
        driver.findElement(By.name("my-textarea"))
                .sendKeys(fishText);
    }
    @Test
    void disabledLocator(){
        String disabledText = driver.findElement(By.name("my-disabled"))
                .getAttribute("placeholder");
        Assertions.assertEquals( "Disabled input", disabledText);
    }
    @Test
    void readonlyLocator(){
        String disabledText = driver.findElement(By.name("my-readonly"))
                .getAttribute("value");
        Assertions.assertEquals( "Readonly input", disabledText);
    }
    @ParameterizedTest
    @CsvSource ( {"1, One","2, Two","3, Three"})
    void dropdownLocator(String number, String textNumber){
        WebElement dropdown = driver.findElement(By.name("my-select"));

        Select select = new Select(dropdown);

        select.selectByValue(number);

        String selectedOptionOne = select.getFirstSelectedOption().getText();
        Assertions.assertEquals(selectedOptionOne, textNumber);
    }
    @ParameterizedTest
    @Disabled("Не смог создать проверку")
    @CsvSource ( {"San Francisco","New York","Seattle"
            ,"Los Angeles","Chicago"})
    void dropdownListLocator(String textNumber){
        WebElement dropdown = driver.findElement(By.name("my-datalist"));

        dropdown.sendKeys(textNumber);

        String enteredText = dropdown.getText();
        Assertions.assertEquals(textNumber, enteredText);
    }
    @Test
    void fileInputLocator(){
        String filePath= "/Users/a1111/IdeaProjects/HomeWork5/src/test/resources/file.jpg";
        driver.findElement(By.name("my-file")).sendKeys(filePath);
    }
    @Test
    void checkedLocator(){
        WebElement checked = driver.findElement(By.id("my-check-1"));
        checked.click();
        String textCheked = driver.findElement(By.cssSelector("label.form-check-label.w-100")).getText();
        Assertions.assertEquals(textCheked, "Checked checkbox");
    }
    @Test
    void defaultLocator(){
        WebElement checked = driver.findElement(By.id("my-check-2"));
        checked.click();
        String textDefault = driver.findElement(By.xpath("//div/div[2]/div[1]/label[2]")).getText();
        Assertions.assertEquals(textDefault, "Default checkbox");
    }
    @Test
    void checkedRadioLocator(){
        String textCheked = driver.findElement(By.xpath("//div[2]/div[2]/label")).getText();
        Assertions.assertEquals(textCheked, "Checked radio");
    }
    @Test
    void defaultRadioLocator(){
        WebElement checked = driver.findElement(By.id("my-radio-2"));
        checked.click();
        String textDefault = driver.findElement(By.xpath("//div[2]/div[3]/label")).getText();
        Assertions.assertEquals(textDefault, "Default radio");
    }
    @Test
    void colorLocator(){
        driver.findElement(By.name("my-colors")).click();
    }
    @Test
    void dateLocator(){
        driver.findElement(By.name("my-date")).click();
        WebElement dateLoc = driver.findElement(By.name("my-date"));
        dateLoc.sendKeys("18/03/2025");
    }
    @Test
    void rangeLocator(){
        WebElement rangLoc = driver.findElement(By.cssSelector("input.form-range"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = arguments[1];", rangLoc, "8");

        String newValue = rangLoc.getAttribute("value");
        Assertions.assertEquals(newValue, "8");
    }
}
