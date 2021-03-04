## Retrospekt Sprint 2

I løpet av sprint 2 har vi erfart at det er veldig viktig å definere små oppgaver. Det er viktig å få på plass rammeverk før man begynner å jobbe med kode; og vi har lært at om vi gjør for mye utenfor det som er planlagt, så kan dette mer til hinder enn nytte. Noe av det vikigste vi har funnet ut er at vi trenger et oversiktskart til en hver tid.
Det har blitt viktigere for oss å merke oss hva som er kritisk for prosjektet. Vi har i denne sprinten kjent påå at vi er foire personer som jobber på vidt forskjellig måte, og at dette fører til en del frustrasjon.
Sprinten har tvunget frem klarere rollefordeling.
Gruppen hadde fra sprint 1 en god dialog, der alle var enig om at man skulle behandle hverandre med respekt. Dette har vært både bra, og dårlig, da det har kommet situasjoner der gruppemedlem ikke har turt å gi hverandre nødvendig tilbakemelding ut fra et ønske om å være høflige.
Gruppen ser nødvendigheten av mer parprogrammering.

Til nå kan spillet vise ett kart, og man kan flytte brikke i spillet.
Teknisk sett kan spillet nå vise kort, men dette blir dekket over av brettet. Dette betyr at kortene er i spill, men mangler noe brukervennlighet.

Gruppen har tatt lærdom av Sprint1 og lagt til flere tester. Det har også blitt diskutert hvorfor det er en ugjevn fordeling i commit. Deler av gruppen har vært opptatt med tildels tunge obligatoriske oppgaver i andre fag. Samt hva som er forklart tidligere i dette dokument.


** Multiplayer **
Host - client build. Allt i spillet skal kalkuleres fra hver maskin, men Host skal tildele tilganger. Denne løsningen kommer etter samtale med gruppeleder.
En server løsning virkler mer fristende.

** Multitreading **
Gui-tread - oppdaterer grafikken
Backend-tread - henter informasjon fra logikken, og kan vente på beskjeder fra mp o.l.
Multiplayer-tread - sender pakker mellom maskinene

** Plan videre**
- slanke så mye som mulig
- jobbe mot multiplayer
- få to robotter til å kunne bevege seg på brettet
- få to maskiner til å kunne kommunisere
- sikre at en maskin bare kan kontrollere en robot, og at en maskin ikke kan kontrollere en robot som blir kontrollert av en annen maskin
- sørge for at en spiller får fem kort, og at disse fem kortene sørger for bevegelse
- legge testkart under Test - mappen, oppdatere POM i henhold til dette
- trenger grafikk for robot2
- bygge game-tread loop
- sørge for at kart ikke dekker for kort
- gi hver player en unik ID, som kan gjøre det lett å koble seg på om man faller ut.

** Refleksjon rundt roller i teamet **
Gjennom denne sprinten har det blitt klart for laget at vi trenger en mer definert leder for selve programmeringen. 
Det har blitt komentert fra gruppen at man føler seg hjelpeløs io forhold til angrepspunkt på prosjektet.
