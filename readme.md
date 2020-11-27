# NHPlus

##Informationen zur Lernsituation
Du bist Mitarbeiter der HiTec GmbH, die seit über 15 Jahren IT-Dienstleister und seit einigen Jahren ISO/IEC 27001 zertifiziert ist. Die HiTec GmbH ist ein mittelgroßes IT-Systemhaus und ist auf dem IT-Markt mit folgenden Dienstleistungen und Produkten vetreten: 

Entwicklung: Erstellung eigener Softwareprodukte

Consulting: Anwenderberatung und Schulungen zu neuen IT- und Kommunikationstechnologien , Applikationen und IT-Sicherheit

IT-Systembereich: Lieferung und Verkauf einzelner IT-Komponenten bis zur Planung und Installation komplexer Netzwerke und Dienste

Support und Wartung: Betreuung von einfachen und vernetzten IT-Systemen (Hard- und Software)

Für jede Dienstleistung gibt es Abteilungen mit spezialisierten Mitarbeitern. Jede Abteilung hat einen Abteilungs- bzw. Projektleiter, der wiederum eng mit den anderen Abteilungsleitern zusammenarbeitet.

 

##Projektumfeld und Projektdefinition

Du arbeitest als Softwareentwickler in der Entwicklungsabteilung. Aktuell bist du dem Team zugeordnet, das das Projekt "NHPlus" betreut. Dessen Auftraggeber - das Betreuungs- und Pflegeheim "Curanum Schwachhausen" - ist ein Pflegeheim im Bremer Stadteil Schwachhausen - bietet seinen in eigenen Zimmern untergebrachten Bewohnern umfangreiche Therapie- und Serviceleistungen an, damit diese so lange wie möglich selbstbestimmt und unabhängig im Pflegeheim wohnen können. Curanum Schwachhausen hat bei der HiTec GmbH eine Individualsoftware zur Verwaltung der Patienten und den an ihnen durchgeführten Behandlungen in Auftrag gegeben. Aktuell werden die Behandlungen direkt nach ihrer Durchführung durch die entsprechende Pflegekraft handschriftlich auf einem Vordruck erfasst und in einem Monatsordner abgelegt. Diese Vorgehensweise führt dazu, dass Auswertungen wie z.B. welche Behandlungen ein Patient erhalten oder welche Pflegkraft eine bestimmte Behandlung durchgeführt hat, einen hohen Arbeitsaufwand nach sich ziehen. Durch NHPlus soll die Verwaltung der Patienten und ihrer Behandlungen elektronisch abgebildet und auf diese Weise vereinfacht werden.

Bei den bisher stattgefundenen Meetings mit dem Kunden konnten folgende Anforderungen an NHPlus identifiziert werden:

- alle Patienten sollen mit ihrem vollen Namen, Geburtstag, Pflegegrad, dem Raum, in dem sie im Heim untergebracht sind, sowie ihrem Vermögensstand erfasst werden.

- Die Pflegekräfte werden mit ihrem vollen Namen und ihrer Telefonnumer erfasst, um sie auf Station schnell erreichen zu können.

- jede Pflegekraft erfasst eine Behandlung elektronisch, indem sie den Patienten, das Datum, den Beginn, das Ende, die Behandlungsart sowie einen längeren Text zur Behandlung erfasst.

- Die Software muss den Anforderungen des Datenschutzes entsprechen. 

- Die Software ist zunächst als Desktopanwendung zu entwickeln, da die Pflegekräfte ihre Behandlungen an einem stationären Rechner in ihrem Aufenthaltsraum erfassen sollen.

 

Da in der Entwicklungsabteilung der HiTech GmbH agile Vorgehensweisen vorgeschrieben sind, wurde für NHPlus Scum als Vorgehensweise gewählt.

 

##Stand des Projektes

In den bisherigen Sprints wurden die Module zur Erfassung der Patienten- und Behandlungsdaten fertiggestellt. Es fehlt das Modul zur Erfassung der Pflegekräfte. Deswegen kann bisher ebenfalls nicht erfasst werden, welche Pflegekraft eine bestimmte Behandlung durchgeführt hat. In der letzten Sprint Review sind von der Curanum Schwachhausen Zweifel angebracht worden, dass die bisher entwickelte Software den Anforderungen des Datenschutzes genügt.

## Technische Hinweise

Wird das Open JDK verwendet, werden JavaFX-Abhängigkeiten nicht importiert. Die Lösung besteht in der Installation der neuesten JDK-Version der Firma Oracle.

## Technische Hinweise zur Datenbank

- Benutzername: SA
- Passwort: SA
- Bitte nicht in die Datenbank schauen, während die Applikation läuft. Das sorgt leider für einen Lock, der erst wieder verschwindet, wenn IntelliJ neugestartet wird!


# Neue Änderungen

## Login
- Beim Start kann der Benutzer sich mit einem Benutzernamen und Passwort einloggen.
- User's: name, name2, name3
- Passwörter für die Users: 123

## Speeren
Über sperren kann der Benutzer die Anwendung speeren. Hierbei muss man sich wieder neu einloggen.

## Benutzer
In der Benutzerübersicht dann der aktuell eingeloggt Benutzer seine Daten ändern. 
Hierbei müssen die neuen Daten angegeben werden und dann mit Speichen übernommen werden. Hier kann der Benutzer auch ein neues Passwort angeben, das diesem aber nicht angezeigt wird. Dies wird auch nur gemacht, wen ein Passwort angegeben wurde.

## Alle Benutzer
Hier kann man sich alle Benutzer anzeigen lassen. 
Die Anzeige kann über die Combobox gefiltert erden. Dadurch ist es möglich, sich z.B. nur alle Pfleger anzeigen zu lassen. Hierfür wählt man einen Typen aus und lädt dann die Tabelle über den Button "Neu Laden" neu.

## Patienten/innen
Hier kann man sich alle Patenten angucken und neue Patenten anlegen. 
Dazu kann man ausgewählt Patenten aus der Tabelle über den Button "Als PDF Expor..." die Daten des Patenten als PDF Exportieren. Hier wurde auch der Vermögensstand aus der Tabelle entfernt.

## Verschlüsselung
Die Verschlüsselung der Daten konnte nicht wie geplant umgesetzt werden. 
Da wir beim Verschlüsseln aller Spalten die Datentypen von allen Spalten auf String ändern müssten. Daher haben wir uns entschieden, nur das Passwort zu verschlüsseln, damit man sich mit den Daten aus der Datenbanktabelle nicht einloggen kann. Wir haben, um uns das Entwickeln einfach zu machen, die Daten mit Base64 "Verschlüsselt".

## Locken/Sperren bzw. Löschen von Patieten Daten
Wenn man jetzt einen Patient löschen oder es explezit macht werden die Daten erst gesperrt und nicht direkt gelöscht.
