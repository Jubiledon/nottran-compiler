# Nottran Compiler

A non-optimising compiler for the **Nottran** programming language, implemented in **Java 21** and powered by **ANTLR 4**.  
The compiler generates executable bytecode for the **Triangle Abstract Machine (TAM)**, a simple stack-based virtual machine with a deterministic execution model.

## Features

### Parsing & Syntax Analysis
- ANTLR 4 grammar for full Nottran syntax
- Automatically generated lexer and parser
- Abstract Syntax Tree (AST) construction and traversal

### Semantic Analysis
- Static type checking (integer, logical, character)
- Scope and symbol table management
- Detection of invalid assignments, undeclared variables, and type mismatches
- Validation for control-flow structures (`if`, `if-else`, `do`, `do-while`)

### Code Generation
- Output to TAM bytecode
- Support for:
  - Integer and logical expressions
  - Relational and arithmetic operators
  - Variable declarations and assignments
  - Character handling
  - Arrays and indexed access
  - Looping and branching constructs
- Deterministic translation from AST → TAM instructions

### Execution
- Compatible with the standard TAM emulator  
- Generates binary output or (optionally) TAM assembly

## Technologies
- **Java 21**
- **ANTLR 4** (scanner + parser generation)
- **Gradle** (build automation)
- **Triangle Abstract Machine** (execution target)

## Project Structure
```bash

/src
/main
/java
- Compiler entrypoint
- AST classes
- Semantic checker
- Code generator
/antlr
- NottranLexer.g4
- NottranParser.g4
/output

* Generated TAM bytecode files
```

## How to Build

```bash
./gradlew build
````

## How to Run

Compile a Nottran source file to TAM bytecode:

```bash
./gradlew run --args="source.nottran"
```

Run the output on the TAM emulator:

```bash
java -jar tam-emulator.jar output.tam
```

## Testing

A suite of sample Nottran programs is included for verifying:

* Correct parsing
* Proper semantic checks
* Accurate TAM bytecode generation
* Behaviour under edge cases and nested control flow

## Future Improvements

* Optimising compiler passes (constant folding, dead code elimination)
* Better error reporting and debugging messages
* Support for additional Nottran features

