![GitHub repo size](https://img.shields.io/github/repo-size/9sefa9/projectFunder)
# Project Funder
  Implementierung einer Crowd-Funding Web Application “Funder”.  Die Crowdfunding-Plattform soll den Nutzern ermöglichen,
  bestimmte Projekte durch spenden zu unterstützen. Dabei soll es möglich sein zu kommentieren, neue Projekte anzulegen oder
  diverse weitere Möglichkeiten anzunehmen. 
  
  Das Projekt wird über Maven importiert und mit Java (Servlet, JSP,..) programmiert.
 
### Gruppennummer in der pom.xml anpassen
 - Zeile 25 in der pom.xml 
 - XXX mit der Gruppennummer ersetzen.
 - Beispiel: dbp001 -> XXX = 001

### Ändern der Config Parameter
- Ersetzt die Werte in settings.properties mit euren Account- und DB Daten

### Starten als Webanwendung(Linux)
1. Linux-Konsole öffnen
2. In das Projektverzeichnis wechseln (z.B. cd /home/dbpXXX/block3/projekte/java/projectFunder/)
2. mvn clean jetty:run in der Konsole ausführen

### Starten als Webanwendung(Windows)
1. Intellij/Eclipse öffnen
2. maven Fenster öffnen/ maven Konsole öffnen
2. mvn clean jetty:run in der IDE ausführen. 

### web.xml und Templates
- befinden sich unter src/main/webapp/WEB-INF

### Um sich von außerhalb der Uni mit der DB zu verbinden
- Die Datei settings.properties überarbeiten.  Die Methode getExternalConnection() in DBUtil.java verwenden.

### Troubleshooting für externe Connections
1. dbstart / dbstop auf dem remote Rechner ausführen
2. Überprüfe, ob der Port richtig gesetzt ist. Der Port müssete 50XXX sein, wobei XXX für eure Gruppennummer steht. 
3. Überprüfe, ob der remote Rechner auf dem Port lauscht: db2 get dbm cfg | grep SVCE
