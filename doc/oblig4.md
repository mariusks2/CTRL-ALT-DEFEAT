# Innlevering 4
Teamnavn: Ctrl+Alt+Defeat
Spillnavn: Mega Marius

## Prosjektrapport
# Medlemer og Roller:
* Hoved: Teamlead, Sekunder: Tester - Christoffer A. Slettebø
* Hoved: Nestleder, Sekunder: Utvikler - Olav Høysæther Opheim
* Hoved: Design Ansvarlig, Sekunder: Utvikler, Kundekontakt - Marius Soma
* Hoved: Test Ansvarlig, Sekunder: Utvikler - Henrik Johansen

# Enderinger ved Roller
- Vi la til Rollen: "Design Ansvarlig", Marius tok ansvar for mye av design prosessen av karakter, map osv. så derfor passer rollen bra.

# Rolle beskrivelse
Teamlead: Ansvar for å planlegge tidspunkt for møter og sørge for at alle i teamet får samme melding og er informert.
Nestleder: Ansvar for å skrive møte refferat for hver uke, og sørge for at trello er/blir oppdatert.
Design Ansvarlig: Ansvar for design av maps, karakter og sprites. Samt fordele arbeidsoppagaver til utviklere ved behov om hjelp,
Test Ansvarlig: Ansvar for at vi får laget tester, og tester vikitge funksjoner i spillet. Samt fordele arbeidsoppgaver til tester og evt utviklere ved behov.
Utvikler: Jobber med programmering av spillet. Selvom vi har roller så flyter vi litt mellom oppgaver,så en utvikler vil jobbe med mye forskjellig.
Tester: Jobber med testing av spillet, og kordinerer med test ansvarlig.
Kundekontakt: Holde kontakt med kunder eller henvendelser.

# Hvordan fungerer rollene?
Olav "jobber" forsatt som nestleder, dette fungerer fint siden struktur på referat og obliger blir lik.
Christoffer "jobber" som teamlead, dette har fungert fint ved at han har planlagt møte tid, satt mål for videre jobbing. Han jobbet en del med test biten av prosjktet sammen med Henrik.
Vi la til rollen "Design ansvarlig" og tilga den til Marius som har jobbet med å lage maps, design av karakter og sprites. Ellers lite kundekontakt.

Vi syntes at rollene har fungert fint og at dette har vært en god erfaring å ta med seg. Vi har holdt oss noe lunde til rollene, selvom vi alle har jobbet litt på tvers av "rollene" vi har hatt.

# Erfaringer, gruppedynamikk og kommunikasjon
Vi syntes at valget vi har tatt har fungert fint, det er nokk alltid ting som kunne blitt gjort anderledes. Det er ingen spesielle valg vi ser på som dårlige og vi tar stort sett alle de større valgene sammen som et team. Dynamikken i gruppen er vedlig god. Vi kommuniserer bra via discord ukentlig og ofte daglig når frister nærmer seg. Vi sitte ofte alle sammen i discord ilag og jobber som par, eller som en hel gruppe å ser på problemet sammen. Vi syntes at dette har fungert veldig bra. Vi har ingen uenigheter som må løses.

# Retroperspektiv for hele prosjektet
* Hva har dere gjort bra?

* Hva hadde dere gjort annerledes hvis dere begynte på nytt? 

# Commits til kodebasen
Vi hadde en liten periode vi commitet med både norsk og engelsk commit melding, men vi bestemete oss for engelsk og har holdt oss til det en stund nå.
Vi så ikke tilbakelemdilgene fra tidligere obliger før dagen før fristen så vi fikk ikke med oss at de ville at vi skulle skrive hvem som har jobbet sammen i par programmering i git commitsene. Olav og Marius har jobbet en del sammen i prosjektet når det kommer til alt det grafiske, samt . Henrik og Christoffer har jobbet en del sammen med testing. Dette er de som har jobbet med sammen totalt, men alle har jobbet med alle gjennom prosjeket på ulike deler. Vi sitter som regel alle fire i discord sammen og jobber. Commits skal være ganskje likt på de fleset ingen uregelmessiger som vi kan se, alle har bidratt like mye på prosjektet hvertfall.

# Rapporter for denne "Sprinten"
- uke16.md
- uke17.md
- uke18.md

## Krav og spesifikasjon
# MVP (Ferdig):
* Vise et spillebrett
* Vise spiller på spillebrett
* Flytte spiller ved å bruke tastene
* Spiller interagerer med terreng
* Vise fiender/monstre; de skal interagere med terreng og spiller
* Spiller kan dø (ved kontakt med fiender, eller ved å falle utfor skjermen)
* Mål for spillbrett, å komme i mål uten å dø
* Start-skjerm ved oppstart / game over

# Early access-version (Dette er de vi har lagt til):
* Power up (Marius kan drikke pepsi og vokse seg stor)
* To fiender (en edderkopp og et pepsi turtle som kan dø og drepe marius)
* Musikk
* Et nytt map
* oppdatert pixel art
* Restruktur
* Tester
* Main menu og Help menu

# Bugs som vi har funnet:
* Kan "sveve" ved å spamme hoppe knapp når man treffer en blokk i lufta
* Kan "sveve" ved å spamme venstre eller høgre knapp intil en vegg/blokk
* Hit box rar på enemies, spesielt når man er stor
* Faller man fra en stor høyde ned på enemies kan også skade selv om man treffer hodet. 
* Mens man går fra liten marius til stor marius ser det ut som man kan dobbel jumpe mid animation
* Spillet krasjer når en tar en coin, en dreper en spider, tar en pepsi og så før pepsien er i bakken tar en coin. 
  Får da Java Runtime Enviroment feil. (Muligens CoinAnimation feil)

# Commit
* Vi har alle commitet en del
* Vi har jobbet litt i brancher, men og pushet en del til main da vi har jobbet mer sammen for å løse problemer
* Noen er litt mer glad i flere små commits, mens noen i få store

## Kode
- Vi har laget en "structure.txt" fil som viser strukturern til prosjektet, og vi har ogås laget et "klassediagram.?" som ligger vedlagt i root mappen.
- Vi har fått laget automatiserte tester som viser coverage, den viser rett over 75%. Tester kan kjøres manuelt i IDE-en eller man kan bruke "mvn test" commandoen i terminalen etter man har "bygget" prosjektet med "mvn compile".

# Dette har vi fikset siden sist:
Vi fant ikke tilbakemeldingene til de tidligere obligen før dagen før innlevering, men heldivis var de ikke alt for mye vi måtte fikse opp i så de gikk fint. Så her er en liste de vi har ordnet oppi fra alle tilbakemeldinger:
- Vi holdt oss til engelsk på commit meldinger.
- Vi har endre mappe struktur fra å ha to mapper som heter screens og scenes til å bruke M-V-C, så vi har nå Model, View og Controller.
- Vi har laget et klassediagram og en structure fil.
- Vi har lagt inn all javadocs.
- Vi har oppdatert pom.xml iflen med korrekt prosjektnavn.
- Vi har fjernet ubrokt kode.
- Vi har fått 66% test coverage på automatiske tester, og resterende 9% (antar vi) er oppnåd ved manuell og visuell testing.
- Vi har også oppdatert prosjektmetodikk og beskrevet bedre hvordan de har fungert osv.

# Oppsumering
ToDo: