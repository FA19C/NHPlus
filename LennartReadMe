Meine Tasks waren, das Sperren der Benutzer, der PDF-Export, das Entfernen des Vermögensstandes, dass man Patienten erst nach 10 Jahren nach der letzten Behandlung löschen kann, das verschlüsseln der Datenbank und das kaskadierende Löschen der Behandlungen, wenn ein Patient gelöscht wird.
Das Sperren der Benutzer, der PDF-Export und die Entfernung des Vermögensstandes gingen Problemlos. Beim Löschen erst 10 Jahre nach der letzten Behandlung wurde das Sperren der Zelle statt wie zuerst in den Tasks 1-3 angegeben in den Eventhandlern stattdessen über die Cellfactory umgesetzt.
Ein Sperrentestfall konnte nicht erfüllt werden, da er nicht beachtet hat, dass Patienten mit "jungen Behandlungen" nicht bearbeitet werden dürfen.
Die Datenbankverschlüsselung war so nicht möglich, da erwartet wurde, dass man jeden Wert über gängige Verschlüsselungsalgorithmen einfach abspeichern kann, was natürlich z.B. in einem INT-Feld nicht geht, weswegen darauf ausgewichen wurde, die komplette .script-Datei bei Nichtbenutzung des Programmes verschlüsselt abzuspeichern.
Für das erstellen der PDF für den PDF export habe ich ein Drittbibliothek "iText" hinzugezogen, weil Java von sich aus keine Funktionalität bereitstellt.

Tests:

User-Story
Als Datenschutzbeauftragter möchte ich nicht mehr die Daten des Vermögensstandes erfassen, um der DSGVO zu entsprechen.

Testfälle

TF1_: Entfernung aus der GUI
- Vorbedingung: Der Nutzer hat im Hauptfenster der Anwendung die Option Patienten/innen ausgewählt
- auszuführende Testschritte: -
- erwartetes Ergebnis: Es werden alle Patienten, die in der Datenbank gespeichert sind, mit ihrer ID, Nachnamen, Vornamen, Geburtsdatum, Pflegestufe und Raumnummer in einer Tableview angezeigt
ohne die Spalte Vermögensstand und ohne das Textfeld für das Hinzufügen eines Patienten mit Vermögensstand abzuzeigen.

ERFÜLLT

TF2_: Entfernung aus der Datenbank
- Vorbedingung: Der Administrator ist auf der Datenbank eingeloggt.
- auszuführende Testschritte: Select Befehl auf Patient-Tabelle für Spalte ASSETS ausführen
- erwartetes Ergebnis: Fehler beim Ausführen des Select-Befehls, dass die Spalte nicht gefunden wurde

ERFÜLLT

TF3_: Entfernung aus Modell
- Vorbedingung: Der Nutzer hat im Hauptfenster der Anwendung die Option Patienten/innen ausgewählt.
TF2_ wurde erfolgreich ausgeführt.
- auszuführende Testschritte: Einen neuen Patienten hinzufügen.
Auf den Tab Behandlungen wechseln.
Wieder in den Tab Patienten/innen wechseln
- erwartetes Ergebnis: Der soeben angelegte Patient ist mit den zuvor bestimmten Daten in der Tabelle vorhanden

ERFÜLLT

User-Story
Als Haupt-Arzt möchte ich die Löschung der persönlichen Daten der Patienten  erst 10 Jahre nach der letzten Behandlung erlauben um die DSGVO einzuhalten.

Testfälle

TF1_:
- Vorbedingung:
 Programm starten und zur Patienten Ansicht navigieren.
 Patient anlegen danach zur  Behandlungs-Ansicht navigieren und eine Behandlung für den Patienten anlegen mit einem Aktuellen Datum.
- auszuführende Testschritte:
in der Patienten Ansicht denn vorher angelegten Patienten versuchen zu bearbeiten
und die Änderungen zu speichern.
- erwartetes Ergebnis:
Da die Daten vom Patienten nicht gesperrt sind sollte die Bearbeitung erfolgreich sein.

NICHT ERFÜLLT, DA IN DEM TESTFALL NICHT BEACHTET WURDE, DASS EIN TASK ES WAR PATIENTENDATEN NICHT BEARARBEITBAR SIND FALLS BEHANDLUNG NOCH AKTUELL IST.

TF2_:
- Vorbedingung:
 Programm starten und zur Patienten Ansicht navigieren.
 Patient anlegen danach zur  Behandlungs-Ansicht navigieren und eine Behandlung für den Patienten anlegen mit einem Aktuellen Datum und dann auf den Button „Daten sperren“ klicken.
- auszuführende Testschritte:
in der Patienten Ansicht denn vorher angelegten Patienten versuchen zu bearbeiten
und die Änderungen zu speichern.
- erwartetes Ergebnis:
Da die Daten vom Patienten gesperrt sind sollte die Bearbeitung nicht erfolgreich sein und es sollte ein Popup kommen mit einer Fehlernachricht.

ERFÜLLT

TF3_:
- Vorbedingung:
 Programm starten und zur Patienten Ansicht navigieren.
 Patient anlegen danach zur  Behandlungs-Ansicht navigieren und eine Behandlung für den Patienten anlegen mit dem Datum „28.02.2010“ und dann auf den Button „Daten sperren“ klicken.
- auszuführende Testschritte:
in der Patienten Ansicht denn vorher angelegten Patienten versuchen den Patienten zu Löschen.
- erwartetes Ergebnis:
Da die Letzte Behandlung vom Patienten außerhalb der 10 Jahre ist sollte die Löschung erfolgreich durchgehen.

ERFOLG

TF4_:
- Vorbedingung:
 Programm starten und zur Patienten Ansicht navigieren.
 Patient anlegen danach zur  Behandlungs-Ansicht navigieren und eine Behandlung für den Patienten anlegen mit einem Aktuellen Datum und dann auf den Button „Daten sperren“ klicken.
- auszuführende Testschritte:
in der Patienten Ansicht denn vorher angelegten Patienten versuchen den Patienten zu Löschen.
- erwartetes Ergebnis: Da die Letzte Behandlung vom Patienten innerhalb der 10 Jahre ist sollte die Löschung fehlschlagen und es sollte ein Popup mit Fehlernachricht gezeigt werden.

ERFOLG

User-Story
Der Administrator der Klinik will die Funktion der Datenbank testen. Hierbei gibt er sich die Patientendaten per Sql Befehl aus. Hierbei sind die Daten, die er durch sein Programm erhält, verschlüsselt und er hat damit keine Einsicht.

Testfälle
TF1_: Sql Abfrage von der Datenbank
- Vorbedingung: Öffnen vom Programm (nicht unseres), um Sql Abfragen zu machen.
- auszuführende Testschritte: Ausführen von Sql abfrage von den Patientendaten.
- erwartetes Ergebnis: Die Daten sollten jetzt verschlüsselt zurückgegeben werden.

MISSERFOLG, DA VERSCHLÜSSELUNG NICHT SO UMSETZBAR WAR WIE GEPLANT.
NUN IST DIE GESAMMTE DATENBANK BEI NICHTBENUTZUNG VERSCHLÜSSELT UND ABFRAGEN FUNKTIONIEREN NICHT MEHR OHNE LAUFENDES PROGRAMM

User-Story
Der behandelnde Arzt des Patienten möchte die Patientendaten als PDF (weil sein Patient die haben will, weil er diese nicht per Hand für seinen Patienten rausarbeiten will.

Testfälle

TF1_: Patientenakte erstellen
- Vorbedingung: Patient wurde mit einigen Behandlungen erstellt.
Tab Patient/-innen ist geöffnet
- auszuführende Testschritte: Auf den Knopf Patientenakte klicken und einen Speicherort über den FileDialog auswählen.
Die gespeicherte Datei öffnen.
- erwartetes Ergebnis: In der Patientenakte stehen die in der Vorbereitung erstellten Daten des ausgewählten Patienten

ERFOLG


