package com.hapifyme.tests;


import com.hapifyme.pages.LoginPage; // Linia 3
import com.hapifyme.pages.RegisterPage; // Linia 4
import org.openqa.selenium.WebDriver; // Linia 5
import org.testng.Assert; // Linia 6
import org.testng.annotations.AfterMethod; // Linia 7
import org.testng.annotations.BeforeMethod; // Linia 8
import org.testng.annotations.Test; // Linia 9

// ATENȚIE: Numele clasei se potrivește acum cu cel din imagine și fișier.
public class RegisterPageTest {

    private WebDriver driver;
    private RegisterPage registerPage;

    // --- Date de test ---
    private final String URL_LOGIN = "https://test.hapifyme.com/login_register.php"; // Schimbați URL-ul

    private final String VALID_FIRST_NAME = "Tester";
    private final String VALID_LAST_NAME = "Automation";
    private final String VALID_EMAIL = "testuser" + System.currentTimeMillis() + "@email.com";
    private final String VALID_PASSWORD = "Password123!";
    // --------------------

    @BeforeMethod
    public void setup() {
        driver = DriverFactory.getDriver();

        driver.get(URL_LOGIN);

        LoginPage loginPage = new LoginPage(driver);
        registerPage = loginPage.clickRegisterLink();
    }

    // Aceasta este metoda vizibilă în imagine (testSuccessfulRegistration)
    @Test
    public void testSuccessfulRegistration() {
        System.out.println("Rulează testul: Înregistrare Reușită");

        // 1. Acțiune: Apelarea metodei registerUser cu date valide
        registerPage.registerUser(
                VALID_FIRST_NAME,
                VALID_LAST_NAME,
                VALID_EMAIL,
                VALID_PASSWORD
        );

        // 2. Verificare (Assertion):
        String expectedUrlPart = "/profilul-meu"; // Schimbați calea așteptată
        String actualUrl = registerPage.getCurrentUrl();

        Assert.assertTrue(actualUrl.contains(expectedUrlPart),
                "Eroare: După înregistrare, URL-ul nu conține: " + expectedUrlPart + ". URL-ul curent este: " + actualUrl);
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

// --- Clasa DriverFactory (Poate fi în DriverFactory.java sau aici) ---
class DriverFactory {

    public static WebDriver getDriver() {
        // Asigurați-vă că aveți importurile și dependențele necesare pentru WebDriverManager/ChromeDriver
        // Exemplu: WebDriverManager.chromedriver().setup();
        // return new ChromeDriver();
        return null; // Înlocuiți cu logica reală
    }
}