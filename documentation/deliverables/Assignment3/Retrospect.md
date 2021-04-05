## Retrospect Sprint 3

### Introduksjon
Sprint 3 har krevd mer synkronisert deltagelse fra gruppen. Gjennom denne sprinten har gruppen ferdigstilt sitt MVP, 
og gått gått gjennom og oppdatert gruppestruktur og ansvarsområder etter behov og erfaring. 
Dette var også den sprinten der det ble vist frem demo, samt at gruppen har presenterte metodikk og gruppestruktur
til resten av klassen.

### Presentasjon av gruppen
Deler av denne sprinten ble brukt til å planlegge, gjennomføre og diskutere gruppepresentasjon. 
Gruppen innsåg tidlig i prosessen at den største utfordringen med presentasjonen var tid. 
Med en presentasjonperiode på fem minutter, innsåg gruppen at det ikke var mulig å gi en fullgod presentasjon 
av arbeidet dersom hvert gruppemedlem skulle presentere "sitt" ansvarsområde.
Presentasjonen ble delt i to deler, der gruppens møteleder presenterte gruppen og metodikk; 
og gruppens grafiske leder gikk gjennom prosjektet, og prosjektstrukturen.

### MVP
MVP krav, med kommentarer:
1. **Vise et spillebrett:** 
   - En kan velge, vise og laste vilkårlige spillbrett dynamisk så lenge spillbrettene følger protokoll for design.
2. **Vise brikke på spillebrett:**
   - En kan vise og bevege opp til 8 brikker kontrollert av 8 spillere på maskiner over LAN og WAN.
3. **Flytte brikke (vha taster eller lignende for testing):**
   - En kan flytte brikkene i korrekt rekkefølge i henhold til spillreglene med program kort, eller med WASD i "CheatMode".
4. **Robot besøker flagg:**
   - Roboter kan besøke flagg. Når en robot besøker flagg, blir dette registrert som nytt spawnpoint.
5. **Robot vinner ved å besøke flagg:**
   - Robot vinner når det har besøkt alle flagg, i sekvensiell rekkefølge. Spillet viser SpillFerdig (GameOver) skjerm.
6. **Spille fra flere maskiner (vise brikker for alle spillere, flytte brikker for alle spillere):**
   - Multiplayer virker med til og med 8 spillere over LAN og/eller nettbasert. MERK: port 18888 må være åpen.
7. **Dele ut kort:**
   - Server∕Host deler ut kort til spilleren. Dette fungerer for øyeblikket for en runde.
8. **Velge 5 kort:**
   - En spiller kan velge 5 av 9 kort.
9. **Bevege robot ut fra valgte kort:**
   - Kortene spilleren har valgt blir kjørt i den rekkefølgen spilleren har valgt kortene.

###Forbedringer
Gruppen jobber målrettet mot at alle gruppemedlemmer skal være en aktiv del av arbeidet. 
Etter sprint 2 ble det kritisert at gruppen hadde en ujevn fordeling av commit. 
Dette var en problemstilling gruppen var klar over. Gruppen valgte å ta dette opp i flere møter, og det er en merkbar
forbedring i deling av commits på sprint 3.

Det har også vært en svakhet for prosjektet med en lav testdekning. 
Dette problemet kom delvis av at gruppen ikke prioriterte TTD, 
men heller valgte å lage etter hvert i prosessen, 
samt utstrakt bruk av manuell testing og print-til-terminal. 
Dette har gruppen tatt hensyn til, og en av gruppemedlemmene er nå testansvarlig og har hovedansvaret å påse at tester
blir utviklet og opprettholdt. 

Gruppen vurderer dette som en løsning på tilbakemeldingen om skjevfordeling av commits mot git, 
samt de utfordringene som har vist seg i form av noe manglende testdekning. 

Denne sprinten tydeliggjorde gruppen sine utfordringer med kommunikasjon. Gruppen har vært holdt en svært høflig og forsiktig  
tone i kommunikasjonen seg imellom, og har da ikke klart å formidle budskapet på en tilstrekkelig måte. Dette har ført til 
missforståelser som har økt behovet for møtevirksomhet og dermed har gruppen fått mindre tid til utvikling. 
Dette har gruppen løst med klarere tilbakemeldinger innad i gruppen og mer direkte kommunikasjon. 

###Forandringer i gruppestruktur
- Vegard Haugland har fått ansvar for grafisk design.
  - Kommer fremover til å konsentrere seg om GUI, libgx, assets, og grafikk.
- Daniel Liland har fått ansvar for testing, og test-enviroment.
  - Han kommer fremmover til å jobbe mot ett velfungerende testmiljø.