# IP STRATEGO 
[![pipeline status](https://git.sogyo.nl/lmanenschijn/ip-stratego/badges/main/pipeline.svg)](https://git.sogyo.nl/lmanenschijn/ip-stratego/-/commits/main) [![coverage report](https://git.sogyo.nl/lmanenschijn/ip-stratego/badges/main/coverage.svg)](https://git.sogyo.nl/lmanenschijn/ip-stratego/-/commits/main) [![Latest Release](https://git.sogyo.nl/lmanenschijn/ip-stratego/-/badges/release.svg)](https://git.sogyo.nl/lmanenschijn/ip-stratego/-/releases)

## Doelstelling van het project volgens MoSCoW

Het doel van mijn project is om het spel Stratego te implementeren.

**Must**
- Een spel moet gestart kunnen worden voor twee spelers achter één console:
    - Je ziet alleen wat je eigen stukken zijn. Van de tegenstander zie je alleen op welke vak een stuk staat.
    - Het perspectief draait na elke beurt.  
    - Tegenstander mag niet kijken als ander aan zet is → pop up einde van je beurt: “geef beurt door aan speler 2” + “speler 2 ben je klaar om een zet te doen?” - zoals bij 30 seconds app.
- Bord staat klaar met 100 vakjes (geen meren). Twee teams: rood en blauw. Per team: 37x verkenner (rang 2), 1x maarschalk (rang 1), 1x vlag, 1x spion (rang 3). 
- Beide spelers krijgen een willekeurige opstelling waar ze mee moeten spelen.
- Je doet een zet door eerst op een vakje van jezelf te klikken waar een stuk op staat. Daarna klik je op een ander vakje (een leeg vakje of een vakje waar een stuk van een tegenstander staat).

- Regels:
    - Stukken die zich kunnen verplaatsen: verkenners, maarschalk, spion. Stuk dat zich niet kan verplaatsen: vlag.
    - Van de beweegbare stukken, kan elk stuk 1 stap naar rechts, links, onder of boven. Uitzondering: de verkenner mag over alle lege  
velden heen springen. Wanneer het veld waar je naar toe gaat een stuk van de tegenstander bevat, val je dit stuk aan.
    - Wanneer je aanvalt noem je de rang van jouw stuk en daarna noemt de tegenstander de rang van zijn stuk (of het blijkt de vlag(/   
bom) te zijn).
    - Als je tegenstanders stuk de laagste rang heeft dan sneuvelt dit stuk en neemt jouw stuk zijn plaats in. Als jouw stuk de laagste
rang heeft dan sneuvelt jouw stuk en blijft je tegenstanders stuk staan. 
    - De maarschalk heeft de hoogste rank en wint van elk beweegbaar stuk. Behalve de spion, die wint van de maarschalk.
    - De vlag kan veroverd worden door elk beweegbaar stuk.
    - Als je de vlag hebt veroverd of de tegenstander kan geen zet meer doen dan heb je gewonnen.
    - Je mag niet vaker dan 5x ononderbroken heen en weer schuiven met één stuk.

**Should**
- Meren toevoegen in bord. Stukken mogen niet in of over de meren springen, maar moeten er langsheen.
- Een samengesteld team zoals bij het originele stratego, inclusief alle tot nu toe ontbrekende stukken: 1x vlag, 6x bom, 1x maarschalk (rang 1), 1x generaal (rang 2), 2x kolonel (rang 3), 3x majoor (rang 4), 4x kapitein (rang 5), 4x luitenant (rang 6), 4x sergeant (rang 7), 5x mineur (rang 8), 8x verkenner (rang 9), 1x spion (rang 10).
- Uitzondering: de bom is een onbeweegbaar stuk. De bom wint van iedereen, behalve de mineur.
 

**Could**
- Verschillende thema's. Bijvoorbeeld een Harry Potter variant van Stratego
- Als speler zelf je opstelling kunnen bepalen

**Won't**
- Een engine voor Stratego
- Online spelen tegen elkaar op verschillende consoles

## Details software stack
Backend: Jetty (Java)
Frontend: Vite + React (tsx), NodeJS as runtime environment
Database: MySQL
Build tool: Gradle

## Persoonlijke leerdoelen
- Snel genoeg om hulp vragen als ik ergens niet uitkom
- Gestructureerd werken met branches en het issue bord op gitlab
- Niet overweldigd raken door dit uitdagende project waarbij ik alles mag toepassen dat ik tijdens het traineeship heb geleerd


## Technische leerdoelen
- De software stack die we gebruikt hebben tijdens de MVC module beter leren beheersen
- Een beter begrip van het concept MVC verkrijgen 
- Zoveel mogelijk Object Georienteerd
- Goed leesbare code schrijven (kies voor Engels of Nederlands)
- Werken met verticale sneden


