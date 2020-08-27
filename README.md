# Kreator ofert
Rozbudowana aplikacja pomagająca pracownikom różnych firm w tworzeniu ofert dla klientów z wybranych produktów.
Program dostosowany jest do języka polskiego.

![screenA](https://raw.githubusercontent.com/r0jsik/kreator-ofert/master/screenshots/A.png)
![screenB](https://raw.githubusercontent.com/r0jsik/kreator-ofert/master/screenshots/B.png)

Więcej zrzutów ekranu znajduje się [tutaj](https://github.com/r0jsik/kreator-ofert/tree/master/screenshots).

### Swoboda działania
##### Wybór produktów jest bardzo elastyczny:
* Możliwość dostosowania bazy danych do własnych potrzeb
* Możliwość wykorzystywania wielu cenników
* Możliwość wybierania ceny z dowolnej listy rabatowej
* Produkty nie muszą mieć list rabatowych

##### Dodatkowo
* Wszystkie raporty mogą zostać wyeksportowane do dokumentu Excela, którego układ, wygląd i zawartość mogą zostać z łatwością dopasowane do potrzeb firmy
* Program posiada wbudowaną bazę z danymi klientów oraz opcje łatwego zarządzania nimi
* Konfigurowanie działania odbywa się za pośrednictwem plików .ini
* Skróty klawiszowe przyspieszają proces tworzenia ofert

### Bezpieczeństwo
Wszystkie bazy danych są szyfrowane na podstawie wybranego hasła.

### Instalacja i działanie
* Aplikacja może zostać zainstalowana na dowolnym komputerze
* Do działania wymaga środowiska Java 8+
* W celu zainstalowania programu można wykorzystać instalator znajdujący się w zakładce Releases

### Działanie aplikacji
W repozytorium znajduje się kod przedstawiający przykładowe wykorzystanie aplikacji na podstawie losowych cenników i klientów.
Paczka dostępna w zakładce Releases wykonuje czystą instalację (bez przykładowej zawartości).

### Strona techniczna
* Domyślnie wykorzystywanymi technologiami są JavaFX, Spring, Hibernate, Maven, Apache POI, ini4j
* Kod jest elastyczny i dostosowany do swobodnej zmiany (za pośrednictwem systemu DI) wykorzystywanych bibliotek
* W repozytorium dostępne są pakiety z narzędziami umożliwiającymi zarządzanie bazami danych
