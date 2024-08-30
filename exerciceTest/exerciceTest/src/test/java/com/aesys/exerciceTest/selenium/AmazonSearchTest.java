package com.aesys.exerciceTest.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class AmazonSearchTest {

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
    public void testSearchShoesOnAmazon() {
        // Apri il sito di Amazon
        driver.get("https://www.amazon.it");

        //Rifiuta i coockie
//      WebElement rejectCookiesTag = driver.findElement(By.xpath("//*[text()='Rifiuta tutto']"));
        WebElement rifiutaCookie = driver.findElement(By.id("a-autoid-0"));
        rifiutaCookie.click();

        // Trova il campo di ricerca e inserisci "scarpe"
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("scarpe");

        // Invia la ricerca
        searchBox.submit();

        // Attendi che i risultati siano caricati
        WebElement searchResults = driver.findElement(By.cssSelector("div.s-main-slot"));

        // Trova tutti i titoli dei risultati della ricerca
        List<WebElement> results = searchResults.findElements(By.cssSelector("span.a-size-base-plus.a-color-base.a-text-normal"));

        // Assicurati che ci siano risultati
        Assert.assertTrue(results.size() > 0, "Non sono stati trovati risultati per 'scarpe'.");

        // Verifica che i primi 5 risultati contengano la parola 'scarpe'
        for (int i = 0; i < Math.min(5, results.size()); i++) {
            String resultText = results.get(i).getText().toLowerCase();
            Assert.assertTrue(resultText.contains("scarpe"),
                    "Il risultato non contiene la parola 'scarpe': " + resultText);
        }
    }

    @AfterMethod
    public void tearDown() {
        // Esegui uno screenshot
        takeScreenshot("screenshot_amazon_search.png");

        // Chiude il browser
        if (driver != null) {
            driver.quit();
        }
    }

    // Metodo per eseguire uno screenshot
    public void takeScreenshot(String fileName) {
        // Converte il WebDriver in TakesScreenshot
        TakesScreenshot screenshot = ((TakesScreenshot) driver);

        // Esegui lo screenshot e salva l'immagine
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        try {
            // Specifica il percorso dove salvare lo screenshot
            String filePath = System.getProperty("user.home") + "\\Desktop\\" + fileName;
            Files.copy(srcFile.toPath(), Paths.get(filePath));
            System.out.println("Screenshot salvato: " + filePath);
        } catch (IOException e) {
            System.out.println("Errore nel salvare lo screenshot: " + e.getMessage());
        }
    }
}

