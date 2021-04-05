# Informasjon vedrørende obligatorisk oppgave 1

## Deloppgave 1: Organiser teamet
Teamet har fått navnet Unsinkable-II.

De ulike medlemmene har dokumentert sin kompetanse i [The dev Team](/documentation/deliverables/Assignment1/about.md). 

Temet har fordelt seg i følgende roller:

### CCO – Inge Halvorsen
Som CCO skal Halvorsen ha hovedansvar for møter, referat og møteinnkallelser. 
Vi har valgt å bruke denne rolle for å sikre at alle møter har referat, 
at gruppen til en hver tid er oppdatert på de møter som kommer, og sporbarhet.

### CIO – Daniel Liland
Som CIO har Liland ansvar for prosess og planlegging av prosjektet. 
Vi har valgt å ha en ansvarlig for prosessplanlegging fordi det er nødvendig at en person er ansvarlig 
for at vi har en felles oppdatert oversikt over prosjektet, og følger planen vi har laget i plenum.

### CTO – Jonas Valen
Som CTO har Valen ansvar for at programmet blir utviklet etter de planer og prosesser som allerede er avklart i gruppen. 
Vi har valgt å ha en ansvarlig for programutvikling fordi kodestandard og syntaks skal følge den strukturen vi har blitt 
enige om i plenum.

### Gamemaster – Vegard Haugland
Som Gamemaster har Haugland ansvar for at spillet følger de spilleregler som er satt for spillet. 
Vi har valgt å ha en ansvarlig for spillereglene fordi spill logikk er vanskelig å ha oversikt over.

### Redaksjon – Samlet gruppe
Før hver innlevering må gruppen samles for å ferdigstille obligatoriske oppgaver. 
Til dette har gruppen satt ned en redaksjon bestående av samtlige gruppemedlemmer. 
Redaksjonen møtes før hver innlevering. Vi har valgt å sette ned dette ansvarsområdet fordi det er 
viktig at alle i gruppen har eierskap til det produktet som blir levert til karaktersetting.

### Ansvarlig for GitHub – Daniel Liland
Som ansvarlig for GitHub har Liland laget en prosedyre for nedlasting og opplasting av filer mot Git. 
Vi har valgt å bruke denne rollen fordi det kan være vanskelig å sammenfatte opplastinger til Git på 
en god måte om alle i gruppen velger å gjøre det alene.
Projectboard er ferdigstilt på GitHub.

## Deloppgave 2: Velg og tilpass en prosess for laget
### Metodikk
En tilnærming av SCRUM, men med en flytende overgang mot KANBAN. SCRUM virker som en logisk metodikk da man kan legge 
sprinter opp mot obligatoriske innleveringer. Gruppen dokumenterer hvilken deler av SCRUM som blir brukt, og ikke brukt.

#### Hva vi ønsker å ta med fra SCRUM sin modell
**Sprint:** Det å ha sprint inn mot obliger virker som en logisk modell å følge fremover.

**Parprogrammering:** Ingen av oss er kjent med parprogrammering, så vi ønsker å lære mer om dette konseptet i praksis.

**Struktur:** Strukturen som SCRUM tilbyr virker veldig appellerende for gruppen.

#### Hva vi ønsker å se bort fra utifra SCRUM sin modell
**Rollemodell:** Vi følger ikke rollemodellen, og har designet vår egen. Dette er grunnet i at vi har såpass få 
medlemmer i teamet, og at vi liker å ha presist definerte roller.

Unsinkable-II møtes på ukentlig basis, med minst ett (1) møte i uken ved gruppetime mandag. 
Det er satt opp mulighet, og blir anbefalt med to (2) møter i uken, da især møte før en obligatorisk innlevering. 
Det er fullt mulig å ha møter utenfor denne rammen. Alt arbeid som blir gjort for Unsinkable-II skal gjøres mens man er 
tilstedeværende på egnet kanal på Discord. Det er lagt opp til en lav terskel for å spørre om hjelp og støtte. 
Unsinkable-II er et studentprosjekt, og har som formål at alle skal kunne lære, og utvikle seg gjennom prosessen. 
Mellom møtene kommuniserer Unsinkable-II over Discord. 

Gruppen har en flat struktur, der alle gruppemedlemmer er oppfordret til å delta aktivt i hverandres ansvarsområder. 
Der likevel veldefinerte ansvarsområder, noe som fører til at gruppen kan konsentrere seg om enkeltstående oppgaver, 
uten å være redd for dobbeltarbeid.

All kode går gjennom review. Gruppemøter er organiserte og det er åpent for diskusjon rundt løsninger. 
Gruppemøtene har vært preget av en god tone, der konstruktiv kritikk og et ønske om å strømlinjeformet 
arbeidshverdag har vært gjennomgående.

Det er lagt opp til at gruppen samler felles dokumenter i en GoogleDrive, samt laster opp dokumentasjon til GitHub 
av hensyn til innleveringer og retting.

Det er ønsket utforske en tilnærming til TDD. 
Gruppen er lite kjent med denne arbeidsprosessen, men stiller seg positivt til å utforske mulighetene 
denne formen for utvikling kan bringe.

## Deloppgave 3: Få oversikt over forventet produkt
### Hva er det overordnede målet for applikasjonen
-   Applikasjonen skal være et spillbart spill. Med dette menes at spillet skal følge fastsatte regler.
-   Det må være mulig å vinne, og tape spillet. Det skal videre være mulig å kjøre spillet på OSX, Windows, og Linux. 
-   Det er også satt krav om støtte for flere spillere samtidig.

## Deloppgave 4: Kode

###Det vi har implementert så langt i henhold til oppgaven
1.  Vise et spillebrett
2.  Vise brikke på spillebrett
3.  Flytte brikke (vha piltaster eller wasd)
4.  Robot besøker flagg
5.  Robot vinner ved å besøke flagg

**I tillegg har vi lagt til funksjon for:**
-   Robot kan falle ned i hull
-   Robot kan ikke flytte seg utenfor kart

###Testing
Disse testene er gjort manuelt, da frontend er veldig vanskelig å teste. Dette er gjort med innbygd funksjon i IDE
og breakpoints. Vi tenker å bevege oss over i JUnit tester når vi kommer til forretningsregler senere i prosjektet.
-   Det er testet at robot beveger seg ved bruk av piltaster.
-   Det er testet at robot vinner, nytt ikon, når den treffer et flagg.
-   Det er testet at robot dør, nytt ikon, når den treffer et hull.
-   Det er testet at robot ikke kan bevege seg utenfor kart.
-   Spillet kjører fra IDE i Windows, Mac, og Linux.
