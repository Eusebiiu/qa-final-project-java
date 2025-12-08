Feature: 2. Crearea si vizualizarea postarilor

  Scenario: 2.1 Crearea unei postari noi pe Feed (index.php)
    Given sunt logat ca utilizator valid
    And sunt pe pagina principala (index.php)
    When scriu un mesaj in campul de postare: "Test Automation cu Selenide si Cucumber!"
    And apas butonul de Publicare
    Then postarea mea ar trebui sa apara in partea de sus a Feed-ului