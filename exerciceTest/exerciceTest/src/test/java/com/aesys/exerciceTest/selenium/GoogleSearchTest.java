package com.aesys.exerciceTest.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class GoogleSearchTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Configura automaticamente il driver di Chrome
        WebDriverManager.chromedriver().setup();
        var options = new ChromeOptions();
        options.addArguments("--disable-search-engine-choice-screen");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Test
    public void testGoogleSearch() {
        // Apri Google
        driver.get("https://www.google.com");

        //Rifiuta i coockie
//      WebElement rejectCookiesTag = driver.findElement(By.xpath("//*[text()='Rifiuta tutto']"));
        WebElement rifiutaCookie = driver.findElement(By.id("W0wltc"));
        rifiutaCookie.click();

        // Trova il campo di ricerca e inserisci "Selenium"
        WebElement searchBox = driver.findElement(By.className("gLFyf"));
        searchBox.sendKeys("Test automation");

        // Invia la ricerca
        searchBox.submit();


        // Trova tutti i link dei risultati della ricerca
        WebElement bodyParent = driver.findElement(By.className("dURPMd"));

       List<WebElement> results =  bodyParent.findElements(By.className("MjjYud"));



        // Filtra i risultati non vuoti
        List<String> nonEmptyResults = new ArrayList<>();
        for (WebElement result : results) {
            String resultText = result.getText().trim();
            if (!resultText.isEmpty()) {
                nonEmptyResults.add(resultText);
            }
        }


        // Verifica che i primi 5 risultati siano pertinenti
        for (int i = 0; i < 5; i++) {
            String resultText = nonEmptyResults.get(i).toLowerCase();
            Assert.assertTrue(resultText.contains("test") || resultText.contains("automation"),
                    "Il risultato non è pertinente alla ricerca: " + resultText);
        }

        // Attendi che la pagina si carichi e verifica che il titolo contenga "Selenium"
        String pageTitle = driver.getTitle();
         Assert.assertTrue(pageTitle.contains("Test automation"), "Il titolo della pagina non contiene 'Selenium'");
        // Assicurati che ci siano più di 5 risultati non vuoti
        Assert.assertTrue(nonEmptyResults.size() > 5, "Ci sono meno di 5 risultati non vuoti nella ricerca");
    }

    @AfterMethod
    public void tearDown() {
        // Chiude il browser
        if (driver != null) {
            driver.quit();
        }
    }
}

