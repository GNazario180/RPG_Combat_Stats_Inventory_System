# ⚔️ RPG Combat, Stats & Inventory System

A modular, text-based backend system for managing player stats, inventory, equipment, and status effects in a Java RPG project. Built with clean OOP principles, this system can be integrated into a larger RPG engine or used as a standalone simulation for learning and experimentation.

---

## 🔧 Features

- **Character Attributes**: Track base and modified values for health, mana, strength, defense, speed, and luck.
- **Status Effects**: Supports buffs and debuffs with turn-based or instant effects, including stat reset logic.
- **Equipment System**: Equip and unequip items that modify player stats.
- **Inventory System**:
  - Item stacking with max limits
  - Inventory sorting (alphabetically, by rarity, quantity)
- **Item Types**:
  - **Weapons**: Provide stat bonuses to attributes
  - **Consumables**: Heal or restore mana
- **Player Stats UI**: Displays level, current stats, status effects, and equipped items in a clean text-based format.

---

## 📁 Project Structure

The system is split into organized packages:
- `game.player` – Core player logic
- `game.stats` – Handles all stat calculations
- `game.inventory` – Inventory/item-related systems
- `game.enums` – Stat types, item rarities, etc.
- `game.interfaces` – Common interfaces
- `main` – Contains the `Main.java` file (entry point)

Each class is separated into its own `.java` file to demonstrate modular design and real-world code organization.

---

## ▶️ How to Run This Project

> This is not an interactive command-line or GUI app — it is a code demonstration that runs from a structured `main()` method.

### To run:

1. Clone or download the full repository.
2. Open it in an IDE like IntelliJ IDEA.
3. Ensure that **all packages and files maintain the original structure**.
4. Locate and open `Main.java` inside the `main` package.
5. Run the `main()` method to simulate character creation, item interactions, and stat display.

The console will output a full demonstration of how the systems work together in a simulated environment.

---

## 📦 Modular Design Philosophy

This system was designed to:
- Demonstrate OOP principles in a practical way
- Keep logic reusable and expandable
- Reflect real-world game development structure (interfaces, enums, class composition)

Perfect for:
- Learning modular Java
- Practicing design patterns
- Building a game backend

---

## 🔮 Planned Features / TODO

- [ ] Equipment swapping with stat preview before confirmation
- [ ] Stat change indicators (green = increase, red = decrease)
- [ ] Tier/Class system (e.g., *Novice*, *Warrior*, *Champion*)
- [ ] Advanced conditional effects (e.g., “When HP < 50%, increase defense”)
- [ ] UI for inventory item usage and interactions (e.g., Use, Drop, Inspect)

---

## 👤 Author

Developed by **Nazario** — aspiring Java developer & game systems designer.  
[GitHub Profile](https://github.com/GNazario180)

