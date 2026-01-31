# ICT Vesti - Android Aplikacija

Android aplikacija za čitanje tehnoloških vesti sa sajta **ictvesti.com**.

## Karakteristike

- 📱 Moderna Material Design 3 UI
- 🔄 Preuzimanje najnovijih vesti sa ictvesti.com
- 🖼️ Prikaz slika uz svaku vest
- 📰 Pregled naslova, datuma, kategorije i sažetka vesti
- ♻️ Dugme za osvežavanje vesti
- 📖 Prošireni prikaz vesti na klik

## Tehnologije

- **Kotlin** - programski jezik
- **Jetpack Compose** - moderan UI toolkit
- **Material Design 3** - dizajn sistem
- **Jsoup** - web scraping biblioteka za parsiranje HTML-a
- **Coil** - učitavanje slika
- **Coroutines** - asinhrono programiranje

## Instalacija

### Zahtevi

- Android Studio Arctic Fox ili noviji
- Android SDK 24+ (Android 7.0 Nougat)
- JDK 8 ili noviji

### Koraci

1. Klonirajte ili preuzmite projekat
2. Otvorite projekat u Android Studio
3. Sačekajte da Gradle završi sinhronizaciju
4. Povežite Android uređaj ili pokrenite emulator
5. Kliknite na Run dugme ili pritisnite Shift + F10

## Struktura projekta

```
ICTVestiApp/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/example/ictvesti/
│   │       │   ├── MainActivity.kt          # Glavna aktivnost
│   │       │   ├── data/
│   │       │   │   ├── Article.kt           # Model za vest
│   │       │   │   └── ICTVestiScraper.kt   # Web scraper
│   │       │   └── ui/theme/                # Tema aplikacije
│   │       ├── res/                         # Resursi
│   │       └── AndroidManifest.xml
│   └── build.gradle.kts
├── build.gradle.kts
└── settings.gradle.kts
```

## Kako radi

Aplikacija koristi **Jsoup** biblioteku za web scraping. Kada se pokrene:

1. Povezuje se na https://www.ictvesti.com
2. Parsira HTML strukturu stranice
3. Izvlači informacije o vestima (naslov, sliku, datum, kategoriju, sažetak)
4. Prikazuje vesti u listi sa mogućnošću proširenja

## Napomene

- Aplikacija zahteva internet konekciju
- Vesti se učitavaju sa početne stranice ictvesti.com
- Za čitanje celih članaka trenutno nije implementirano otvaranje u browseru (može se dodati)

## Moguća poboljšanja

- [ ] Otvaranje celih članaka u WebView-u ili browseru
- [ ] Keš vesti za offline čitanje
- [ ] Filtriranje po kategorijama
- [ ] Pretraga vesti
- [ ] Deljenje vesti na društvenim mrežama
- [ ] Čuvanje omiljenih vesti
- [ ] Push notifikacije za nove vesti

## Licenca

Ovaj projekat je kreiran u obrazovne svrhe.

## Autor

Kreirano pomoću Claude AI
