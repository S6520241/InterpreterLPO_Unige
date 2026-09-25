# ProgettoLPO_Unige
# Custom Language Interpreter

Un interprete completo sviluppato in **Java** e **F#** per un linguaggio di programmazione custom con supporto per operazioni insiemistiche, tipizzazione statica e controllo di flusso.

Questo progetto di gruppo applica concetti avanzati di ingegneria dei linguaggi e programmazione orientata agli oggetti (OOP), partendo dalla definizione formale della grammatica fino all'esecuzione dinamica del codice.

## Architettura del Sistema

L'architettura dell'interprete è suddivisa in pipeline sequenziali:
1. **Analisi Lessicale (`Tokenizer`):** Basata su regex in Java per la tokenizzazione del codice sorgente.
2. **Analisi Sintattica (`Parser`):** Un *Recursive Descent Parser* LL(1) che implementa una grammatica EBNF non ambigua per costruire l'Abstract Syntax Tree (AST).
3. **Semantica Statica (`Typecheck`):** Un type-checker statico implementato tramite il **Visitor Pattern** per prevenire errori di tipo a tempo di compilazione.
4. **Semantica Dinamica (`Execute`):** Il motore di esecuzione a runtime, anch'esso basato sul pattern Visitor, che gestisce le transizioni di stato e l'environment delle variabili (scoping e shadowing).

## Funzionalità del Linguaggio Implementate
Il linguaggio supporta le strutture di controllo standard e introduce primitive avanzate per la teoria degli insiemi:
* **Tipi Primitivi:** Interi (`int`), Booleani (`bool`), Coppie (`pair`).
* **Costrutti di Flusso:** `if-else`, `while`, blocchi nidificati e dichiarazioni di variabili (`var`).
* **Teoria degli Insiemi:** 
  * Costruzione di insiemi literal (es. `{1}++{2}`).
  * Operazioni insiemistiche: Unione (`++`), Differenza (`\`), Appartenenza (`in`), Cardinalità (`#`).
  * *Set Comprehension / Iteratori:* Iterazione su insiemi tramite costrutti dedicati (es. `{for x in s | x + 1}`).

## Tecnologie e Design Pattern
* **Java 24 / Maven:** Linguaggio principale per l'implementazione dell'interprete e dell'AST.
* **F# / .NET:** Utilizzato per la definizione formale e testabile delle regole di semantica statica e dinamica (`Semantics.fs`).
* **Design Patterns:** Impiego del **Visitor Pattern** per separare le classi dei nodi AST dalle logiche di typechecking e valutazione.
* **Gestione degli Scope:** Implementazione di una Symbol Table basata su stack (tramite `LinkedList` e `HashMap`) per gestire ambienti nidificati e cicli di vita delle variabili.

## Compilazione ed Esecuzione
L'interprete fornisce un'interfaccia a riga di comando per analizzare ed eseguire script di testo:

```bash
# Esecuzione standard con type-checking statico
java finalProject.Main -i script.txt

# Esecuzione ignorando la semantica statica (No Type-Checking)
java finalProject.Main -i script.txt -ntc

# Redirezione dell'output su file
java finalProject.Main -i script.txt -o result.txt
