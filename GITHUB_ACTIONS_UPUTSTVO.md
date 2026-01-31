# GitHub Actions - Kompletno Uputstvo za APK Build

## 📦 Šta dobijate

✅ **Automatski build** svaki put kad push-ujete kod
✅ **Besplatan** - unlimited build-ovi za public repozitorijume
✅ **Brzo** - build traje 8-12 minuta
✅ **APK download** direktno sa GitHub-a

---

## 🚀 BRZI START - 3 Koraka

### Korak 1️⃣: Upload projekta na GitHub

**1.1 Kreirajte repozitorijum:**
- Idite na https://github.com/new
- Ime: `ICTVestiApp`
- Tip: **Public** (obavezno za besplatne build-ove!)
- Štiklirajte "Add a README file"
- Kliknite "Create repository"

**1.2 Upload fajlova:**
- Raspakujte `ICTVestiApp-Updated.zip` koji vam šaljem
- Na GitHub stranici: **"Add file" → "Upload files"**
- Prevucite SVE foldere/fajlove
- Commit message: `Initial commit`
- Kliknite "Commit changes"

### Korak 2️⃣: Dodajte GitHub Actions Workflow

**2.1 Kreirajte folder strukturu:**
Na vašem GitHub repozitorijumu:
- Kliknite **"Add file" → "Create new file"**
- U File name unesite: `.github/workflows/android-build.yml`
- GitHub će automatski kreirati foldere

**2.2 Kopirajte workflow kod:**
Kopirajte sadržaj `android-build.yml` fajla koji sam vam napravio i nalepite ga ovde.

**2.3 Commit:**
- Commit message: `Add GitHub Actions workflow`
- Kliknite "Commit changes"

### Korak 3️⃣: Pokrenite Build

**Automatski način:**
- Build se automatski pokreće pri svakom push-u

**Ručni način:**
1. Idite na tab **"Actions"** u vašem repo-u
2. Kliknite na **"Android CI - Build APK"** workflow
3. Kliknite **"Run workflow"** dugme (desno)
4. Kliknite zeleno **"Run workflow"**

---

## 📥 PREUZIMANJE APK-a

### Metod 1: Artifacts (odmah dostupno)

1. Idite na **"Actions"** tab
2. Kliknite na poslednji build (zeleni ✓)
3. Skrolujte dole do **"Artifacts"** sekcije
4. Kliknite na **"app-debug"** da preuzmete APK
5. Raspakujte ZIP arhivu
6. Unutra ćete naći `app-debug.apk`

### Metod 2: Releases (trajno dostupno)

1. Idite na **"Releases"** (desna strana GitHub stranice)
2. Kliknite na poslednji release (npr. `v1.0.1`)
3. Pod "Assets" kliknite na `app-debug.apk`
4. Direktan download bez raspakovavanja!

---

## 📱 INSTALACIJA APK-a NA TELEFON

### Android instalacija:

**Način 1 - Download na telefonu:**
1. Otvorite GitHub release na telefonu
2. Preuzmite APK
3. Tapnite na preuzeti fajl
4. Dozvolite instalaciju iz nepoznatih izvora ako pita
5. Tapnite "Install"

**Način 2 - Prebacivanje sa računara:**
1. Preuzmite APK na računar
2. Povežite telefon USB kablom
3. Kopirajte APK u Download folder telefona
4. Na telefonu otvorite File Manager
5. Pronađite i tapnite APK fajl
6. Dozvolite instalaciju
7. Tapnite "Install"

---

## ⚙️ KAKO RADI WORKFLOW

```yaml
Trigger: Push na main/master branch
↓
1. Checkout koda iz repo-a
↓
2. Setup Java 17
↓
3. Cache Gradle zavisnosti (ubrzava build)
↓
4. Kompajliranje Debug APK (./gradlew assembleDebug)
↓
5. Upload APK kao Artifact
↓
6. Kreiranje GitHub Release sa APK-om
```

### Build vreme:
- Prvi build: **12-15 minuta** (preuzima sve zavisnosti)
- Sledeći build-ovi: **6-8 minuta** (koristi keš)

---

## 🔧 TROUBLESHOOTING

### Problem 1: Build fails - "Permission denied: gradlew"

**Uzrok:** Gradlew nema execute permission

**Rešenje:** Dodajte ovaj korak u workflow POSLE checkout:
```yaml
- name: Make gradlew executable
  run: chmod +x gradlew
```

### Problem 2: Build fails - "SDK not found"

**Uzrok:** Gradle ne može pronaći Android SDK

**Rešenje:** GitHub Actions runner već ima SDK. Proverite da imate:
```yaml
- name: Set up JDK 17
  uses: actions/setup-java@v4
```

### Problem 3: "No artifacts found"

**Uzrok:** APK nije generisan

**Rešenje:** Proverite build log. Path do APK-a je:
```
app/build/outputs/apk/debug/app-debug.apk
```

### Problem 4: Build uspešan ali nema Release

**Uzrok:** Release se kreira samo za main/master branch

**Rešenje:** Push-ujte na main branch ili uklonite uslov:
```yaml
if: github.ref == 'refs/heads/main'
```

---

## 💡 NAPREDNE OPCIJE

### Automatski build za Pull Requests

Workflow već podržava PR build-ove:
```yaml
on:
  pull_request:
    branches: [ main ]
```

### Build za specifične tagove

Dodajte:
```yaml
on:
  push:
    tags:
      - 'v*'
```

### Release APK sa potpisom

Za production release, dodajte signing:
1. Generišite keystore
2. Dodajte ga kao GitHub Secret
3. Modifikujte workflow da koristi signing config

---

## 📊 GITHUB ACTIONS LIMITI

### Besplatni plan (Public repo):
✅ **Unlimited minuta** za javne repozitorijume
✅ **Unlimited build-ova**
✅ **20 konkurentnih job-ova**
✅ **500MB artifact storage (90 dana)**

### Privatni repozitorijumi:
- 2000 minuta/mesečno (besplatno)
- Posle toga: $0.008 po minuti

**Za ovaj projekat: Public repo = potpuno besplatno zauvek!** 🎉

---

## 🎯 VAŽNE NAPOMENE

1. **Public vs Private:**
   - Public repo = besplatno unlimited
   - Private repo = 2000 min/mesec besplatno

2. **Artifacts retencija:**
   - Čuvaju se 90 dana
   - Releases ostaju zauvek

3. **Build vreme:**
   - Android build-ovi su sporiji (~10 min)
   - Normalno za GitHub Actions

4. **APK tip:**
   - Debug APK = za testiranje
   - Release APK = za produkciju (treba signing)

---

## 🔄 ŠORTKAT - Brzo Testiranje Lokalno

Pre push-a na GitHub, možete testirati build lokalno:

```bash
# Ako imate Android SDK instaliran:
cd ICTVestiApp
./gradlew assembleDebug

# APK će biti u:
# app/build/outputs/apk/debug/app-debug.apk
```

---

## ✨ SLEDEĆI KORACI

Posle uspešnog build-a:

1. ✅ Instalirajte APK na telefon
2. 🧪 Testirajte aplikaciju
3. 🐛 Ako ima bagova, popravite kod i push-ujte
4. 🔄 GitHub Actions automatski builduje novu verziju
5. 📦 Preuzmite novu APK i testirajte ponovo

**Želite li da dodam nešto još u workflow ili imate pitanja?**
