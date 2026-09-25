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
```
_____________________________________________________________

# ProgettoLPO_Unige
# Custom Language Interpreter

A complete interpreter developed in **Java** and **F#** for a custom programming language with support for set operations, static typing, and flow control.

This group project applies advanced concepts of language engineering and object-oriented programming (OOP), starting from the formal definition of the grammar to the dynamic execution of the code.

## System Architecture

The interpreter's architecture is divided into sequential pipelines:
1. **Lexical Analysis (`Tokenizer`):** Based on Java regex for the tokenization of the source code.
2. **Syntax Analysis (`Parser`):** An LL(1) *Recursive Descent Parser* that implements an unambiguous EBNF grammar to build the Abstract Syntax Tree (AST).
3. **Static Semantics (`Typecheck`):** A static type-checker implemented via the **Visitor Pattern** to prevent type errors at compile time.
4. **Dynamic Semantics (`Execute`):** The runtime execution engine, also based on the Visitor pattern, which manages state transitions and the variable environment (scoping and shadowing).

## Implemented Language Features
The language supports standard control structures and introduces advanced primitives for set theory:
* **Primitive Types:** Integers (`int`), Booleans (`bool`), Pairs (`pair`).
* **Flow Constructs:** `if-else`, `while`, nested blocks, and variable declarations (`var`).
* **Set Theory:** 
  * Literal set construction (e.g., `{1}++{2}`)[cite: 10].
  * Set operations: Union (`++`), Difference (`\`), Membership (`in`), Cardinality (`#`).
  * *Set Comprehension / Iterators:* Iteration over sets using dedicated constructs (e.g., `{for x in s | x + 1}`).

## Technologies and Design Patterns
* **Java 24 / Maven:** Main language for implementing the interpreter and AST[cite: 10].
* **F# / .NET:** Used for the formal and testable definition of static and dynamic semantic rules (`Semantics.fs`).
* **Design Patterns:** Use of the **Visitor Pattern** to separate AST node classes from type-checking and evaluation logic.
* **Scope Management:** Implementation of a stack-based Symbol Table (using `LinkedList` and `HashMap`) to manage nested environments and variable lifecycles.

## Compilation and Execution
The interpreter provides a command-line interface to parse and execute text scripts:

```bash
# Standard execution with static type-checking
java finalProject.Main -i script.txt

# Execution ignoring static semantics (No Type-Checking)
java finalProject.Main -i script.txt -ntc

# Redirect output to a file
java finalProject.Main -i script.txt -o result.txt
