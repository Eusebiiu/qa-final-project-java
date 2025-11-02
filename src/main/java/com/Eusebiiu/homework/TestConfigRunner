
package com.Eusebiiu.homework;

public class TestConfigRunner {

    public static void main(String[] args) {
        System.out.println("--- Testarea Constructorilor Supraîncărcați ---");


        BrowserConfig cfg1 = new BrowserConfig(BrowserType.FIREFOX, "120.0", true);
        System.out.print("Config 1 (3 param): ");
        cfg1.afiseazaConfig();


        BrowserConfig cfg2 = new BrowserConfig(BrowserType.OPERA, "latest-beta");
        System.out.print("Config 2 (2 param): ");
        cfg2.afiseazaConfig();


        BrowserConfig cfg3 = new BrowserConfig(BrowserType.CHROME);
        System.out.print("Config 3 (1 param): ");
        cfg3.afiseazaConfig();


        System.out.println("\n--- Testarea Metodei Statice Factory ---");


        BrowserConfig cfg4 = BrowserConfig.createDefaultChromeConfig();
        System.out.print("Config 4 (Factory): ");
        cfg4.afiseazaConfig();

        System.out.println("\nVerificare încheiată. Configurările de mai sus demonstrează funcționalitatea.");
    }
}