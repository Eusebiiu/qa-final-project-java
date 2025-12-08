Feature: 3. Cautarea si Vizualizarea Profilului

  Scenario: 3.1 Cautarea unui utilizator folosind Data Table
    Given sunt pe pagina de login a aplicatiei
    When ma autentific ca "eusebiiumihalache@gmail.com" cu parola "Lastweek12@"
    And sunt pe pagina principala (index.php)

    When introduc urmatoarele cuvinte cheie si verific rezultatul "George Datcu":
      | cautare |
      | George |
      | Datcu |
      | George Datcu |

    Then apas pe primul rezultat din cautare si verific profilul "George Datcu"
    And cautarea utilizatorului ar trebui sa se fi finalizat cu succes