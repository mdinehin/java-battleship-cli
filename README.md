# Java Battleship CLI

![Java Version](https://img.shields.io/badge/Java-25.x-ED8B00?style=flat-square&logo=openjdk&logoColor=white)
![Test Coverage](https://img.shields.io/badge/Test_Coverage-100%25-brightgreen?style=flat-square)
![GitHub Release](https://img.shields.io/github/v/release/mdinehin/java-battleship-cli?label=Release&style=flat-square&logo=github&logoColor=white)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg?style=flat-square)](LICENSE)

A lightweight and robust Command-Line Interface (CLI) implementation of the classic Battleship naval strategy game built with Java and driven by Domain-Driven Design (DDD) and Test-Driven Development (TDD) principles.

## Overview

Java Battleship CLI brings the tactical naval warfare experience directly to your terminal. Designed with clean architecture, strict coordinate validation, and precise turn management, the project demonstrates scalable OOP patterns alongside interactive command-line gameplay.

> Note on Game Rules: This implementation defaults to the classic Sea Battle fleet layout, the original Eastern European paper-and-pencil configuration, predating Milton Bradley's 1967 plastic board game that popularized the Western standard.

### Features

- 🚢 Standard Board Setup: Classic 10x10 grid layout (Rows A–J, Columns 0–9).
- 🎲 Randomized Fleet Generation: Automated and valid placement for full ship fleets without overlaps.
- 🎯 Smart Parsing: Robust handling of target coordinates (e.g., `A0`, `J9`).
- 📊 Detailed Domain Messaging: Informative exception tracking and clean validation feedback.
- 🛠️ TDD Built: High test coverage keeping domain logic decoupled from user interface code.

### Fleet Configuration Overview

In this version, played on a standard 10x10 grid, each player has a fleet of 10 ships occupying a total of 20 squares:

| Ship Type / Name | Deck Count | Quantity | Total Grid Spaces |
| --- | --- | --- | --- |
| 4-Deck Ship (Battleship / Four-decker) | 4 | 1 | 4 |
| 3-Deck Ships (Cruisers / Three-deckers) | 3 | 2 | 6 |
| 2-Deck Ships (Destroyers / Two-deckers) | 2 | 3 | 6 |
| 1-Deck Ships (Submarines / Single-deckers) | 1 | 4 | 4 |
| Total | | 10 ships | 20 spaces |

Note (Adjacent Buffer Rule): Ships cannot touch each other, even diagonally, and must be completely surrounded by at least one cell of open water.

## Prerequisites

The following software dependencies are required:

- [Java Standard Edition](https://www.oracle.com/java/technologies/downloads/) version 25 or higher

## Installation & Running

If you just want to play the game without compiling the source code:

1. Head over to the [Releases Page](../../releases/latest)
2. Download the latest `battleship-cli.jar` file
3. Run the application from your terminal with `java -jar battleship-cli.jar`
