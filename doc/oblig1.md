# Rapport – innlevering 1
**Team:** CRTL+ALT+DEFEAT

# Oppgave A1

# Medlemer:
* Teamlead: Christoffer A. Slettebø
* Programmerer/Nestleder: Olav Høysæther Opheim
* Programmerer/Kundekontakt: Marius Soma
* Programmerer/Testansvarlig: Henrik Johansen

* Valgt Teamlead siden noen trenger å ta ledelsen og ta beslutninger, for dette valgte vi Christoffer Slettebø
* Vi valgte nestleder siden Teamlead er noen ganger bare tilgjengelig digitalt eller hvis teamlead er sjuk er det greit at noen andre kan ta ledelsen, for dette valgte vi Olav Opheim
* Vi har en kundekontakt som kommuniserer med kunden underveis slik porduktet blir som kunden vil ha det, for dette valgte vi Marius Soma
* Vi har en testansvarlig slik vi har en som prøver å finne ut om spiller funker slik som det skal og helst ingen exploits, for dette valgte vi Henrik Johansen. 

# Oppgave A2 - Konspet

# Tanker om spillet 

* Spillfigur som kan styres – gå til høyre/venstre, hoppe oppover, bevege seg nedover i en pipe elns
* Todimensjonal verden:
    * Plattform – horisontal flate spilleren kan stå eller gå på (inkludert «bakken»)
        * Vegg – vertikal flate som spilleren ikke kan gå gjennom
        * Spilleren beveger seg oppover ved å hoppe, og nedover ved å crouche
* Fiender som beveger seg og er skadelige ved berøring
* Spilleren kan samle poeng ved å plukke opp ting
* Utfordringen i spillet er å: å bevege seg gjennom terrenget uten å falle utenfor mappet, å samle mest mulig poeng, å unngå/bekjempe fiendene, å nå frem til og bekjempe en boss som er julenissen
* Verden er bygget opp av blokker med fast størrelse (felter i et 2D-rutenett)
* Verden har plattformer som man kan hoppe opp gjennom og ned gjennom
* Verden er større enn skjermen og scroller horisontalt eller vertikalt
* Plattformer som beveger seg
* Spilleren kan drepe fiendene ved å hoppe på dem eller skyte dem
* «Power-ups» som gir spilleren spesielle krefter
* Easter eggs som gir ekstra poeng

# Prosess for teamet:
* Vi tenker å bruke: parprogrammering, trello (ganske likt på kaban) og testing.
* Møter: Hver tirsdag (gruppe time), og evt discord ved behov.
* Kommunikasjon: Discord
* Arbeidsfordelding: Vi bruker trello til å føre opp oppgaver, og fordeler de på gruppe timene.
* Oppfølging av arbeid: Gjøres på gruppetimer/discord i ukentlig møte.
* Delig og oppbevaring av felles dokumenter: Discord, gitlab og trello.


# Prosjekt organisering:
* Jobbe med obliger på gruppetimer og spørre gruppeledere om spørsmål vi lurer på. Fordele og gå over oppgaver vi har gjort på gruppe/discord. Komme i gang med programming, for å få til å bevege en karakter på skjermen.


# Oppgave A3 - Oversikt over forventet produkt

# MVP:
* Vise et spillebrett
* Vise spiller på spillebrett
* Flytte spiller ved å bruke tastene
* Spiller interagerer med terreng
* Vise fiender/monstre; de skal interagere med terreng og spiller
* Spiller kan dø (ved kontakt med fiender, eller ved å falle utfor skjermen)
* Mål for spillbrett, å komme i mål uten å dø
* Start-skjerm ved oppstart / game over
* Brukerhistorier:
* Her er eksempel på to bruker historier som passer for spiller vi skal utvikle,

# Spiller sitt perspektiv:
* «Som spiller trenger jeg å kunne skille plattformer/vegger fra bakgrunnselementer slik at jeg avgjøre hvordan jeg skal styre spillfiguren.» Ved design av grafiske elementer må vi da tenke på fargevalg, texture osv, med tanke på farge blinde personer.

# Spiller sitt perspektiv:
* «Som spiller trenger jeg lyd for å få en bedre bruker opplevelese.» Ved implementasjon av lyd må vi tenke på folk som ikke kan lese, da kan vi legge inn lyd filer so leser opp instruksjoner i spillet (for eksmepel).

# Utvikler sitt perspektiv:
* «Som utvikler trenger jeg å kunne skille plattformer og entites fra hverandre, slik at jeg kan avgjøre om spillfiguren og fiendene kan bevege seg i en gitt retning»



# Oppgave A4 - Koding:
* Vi har kodet en liten del (sjekk fil i gitlab)


# Oppgave A5 - Oppsumering:

* Alt fungerte stort sett fint ved gjennomføring av Del A. Vi har:
* Komt i gang med programmering og fått klargjort mye av konseptet og hvordan vi skal gå frem videre for å utvilke spillet.
* Hadde litt problemer med Trello (lasta treigt inn), men det ordner seg nokk.
* Laget en brukerhistorie
* Valg hvilke prosjektmetodikk vi vil bruke ved utvikling (Parprogrammering, Trello (litt som kaban) og testing.





