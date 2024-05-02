# INF112 Prosjekt – _Mega Marius_
- Team: _Ctrl+Alt+Defeat_ (Gruppe 5): _Christoffer A. Slettebø, Marius Soma, Henrik Johansen, Olav Høysæther Opheim_
- Lenke til <a href="https://trello.com/b/naQJzzQZ/ctrlaltdefeat" target="_blank">Trello</a>.
- Lenke til <a href="https://git.app.uib.no/Christoffer.Slettebo/ctrl-alt-defeat" target="_blank">GitLab</a>

## Om spillet
- Lore:
_«Marius er i trøbbel nok engong! Han er helt tom for Pessi (Brus). Marius trenger Pessi for å ha det bra. Derfor må han komme kjapt til butikken, men veien til butikken byr på mange utfordringer. Blir det for tøft for Marius»_

- Hvordan spillet fungere:
Spillet har en karakter som heter marius, denne karakteren ka bevege seg til "venstre", "høgre" og "hoppe" (opp).
Karakteren kan drepe fiender ved å hoppe og lande oppå hode til fienden.
Hvis Marius blir truffet av fienden fra siden vil Marius dø og man må starte spillet på nytt.
Man kommer i mål hvis man når frem til en flagg stang i enden av mappet.
De er totalt tre maps med varierende grad av vansklighet.
Man kan sjekke scoren sin ved å trykke på scoreboard knappen i map select menyen.

## Kjøring
- Kompileres med `mvn package`.
- Kjøres med `java -jar skeleton/app/main.java`
- Krever Java 15 eller senere

## Kjente feil
- Kan hoppe i blokker og da hoppe for alltid, på en måte slik du svever. 
- Hit box på karakter er litt rar.
- Noen ganger kan du ta skade selv om du treffer hodet til en edderkopp, mistanke er at du går gjennom hitboxen.
- Spillet krasjer noen ganger hvis man hopper opp i coin/pessi block, feil er utbedret slik at de skjer skjeldnere. 

## Credits
- Musikk fra <a href="https://pixabay.com/users/white_records-32584949/?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=164702" target="_blank">Maksym Dudchyk</a> from <a href="https://pixabay.com//?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=164702" target="_blank">Pixabay</a>.
- Sprites, vi har redigert de fleste : <a href= https://www.spriters-resource.com/nes/supermariobros/sheet/52571 target="_blank">Spriters-resource</a>.
- Powerup 10.wav av MATRIXXX_ <a href="https://freesound.org/s/523654/" target="_blank">Freesound</a> License: Creative Commons 0.
- Retro, Coin 02.wav av MATRIXXX_ <a href="https://freesound.org/s/402288/" target="_blank">Freesound</a> License: Creative Commons 0.
- Trúng Gạch Đá av SieuAmThanh <a href="https://freesound.org/s/530812/" target="_blank">Freesound</a> License: Creative Commons 0.
