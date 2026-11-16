# Portals

A simple portals plugin for **PowerNukkitX**. Create teleport zones without portal blocks: just mark two corners and a destination.

## NOTE

The plugin has not yet been tested so it may have bugs, I will be correcting the bugs when I test it.

## Features

- Create named portals.
- Define the portal area with two corners.
- Set a destination, including a different world.
- Teleport players when they walk into the portal area.
- Confirmation and error messages for every step.

## Commands

| Command | Description |
|---------|-------------|
| `/portal help` | Show the help |
| `/portal list` | List existing portals |
| `/portal make <name>` | Create a portal |
| `/portal delete <name>` | Delete a portal |

Aliases: `/portals`, `/prt`.

## Creating a portal

1. Run `/portal make <name>`.
2. Use the **golden axe** to mark the first corner.
3. Use the **diamond axe** to mark the opposite corner.
4. Use the **iron axe** to mark the destination.
5. Use the **green dye** to save the portal.
6. Use the **red dye** to cancel and exit.

Once saved, walk into the portal area to teleport.

## Installation

1. Build with `./gradlew build`.
2. Copy the JAR from `build/libs/` into the server's `plugins/` folder.
3. Restart PowerNukkitX.
