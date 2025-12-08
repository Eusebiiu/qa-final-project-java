Feature: 1. Autentificarea utilizatorilor pe hapifyMe

  Scenario: 1.1 Login cu succes
    Given sunt pe pagina de login a aplicatiei
    When ma autentific ca "eusebiiumihalache@gmail.com" cu parola "Lastweek12@"
    Then ar trebui sa fiu logat cu succes si sa vad pagina de Feed

  Scenario Outline: 1.2 Login esuat cu date invalide
    Given sunt pe pagina de login a aplicatiei
    When incerc sa ma autentific ca "<email>" cu parola "<parola>"
    Then ar trebui sa vad un mesaj de eroare

    Examples:
      | email               | parola        |
      | gresit@mail.com     | ParolaCorecta |
      | corect@mail.com     | Parola123 |
      | blank               | blank |