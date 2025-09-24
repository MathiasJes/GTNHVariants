# GTNH Variants

The mod aims to provide common GTNH variants at **world creation** without change config files and reload the game.

**Note:** This is not an official GTNH project. Treat it as design input and a proof of concept.

## Overview

The mod adds a cycling option button to the **World Creation** screen's **More World Options** (in place of the *Bonus Chest* button). It currently supports **single-player** only.

**Available variants**
- Normal (nothing is changed)
- Garden of Grind
- Hellish Grind
- I’m No Rocket Man

"Hellish Grind" and "I'm No Rocket Man" is Nether only and no rocket With more "Garden of Grind" coolish names.

## Implementation notes

- **Rocket restriction (all variants):**  
  Mixin on `EntityTieredRocket#interactFirst` (GalaxyCraftCore) prevents entering rockets (also applies to GoG and NetherOnly)

- **Garden of Grind (GoG):**  
  Mixin (similar to HodgePodge) intercepts chunk generation to produce empty chunks as required, while maintaining biomes generation. Nether and Twilight Forest portals are disabled (this is also the case in Nether-only).

- **Nether-only:**  
  Creates a world spawn based on the Nether, and assigns the player’s initial dimension as the Nether.

## Usage

1. Create a new world.
2. Select a variant:
   - GoG / Hellish Grind / I’m No Rocket Man / Normal
3. Create the world. Variant rules apply at runtime.

## Limitations

- Single-player only

## Roadmap

- Bugfixing
- Server support  
- Beds in the Nether (Nether-only) for setting respawn without relying on `isSurfaceWorld` returning true for WorldProviderHell. 
- Questbook entries for each variant, plus support code  
- Additional questbook skips for dimensionless progression  
- Recipes for missing items (e.g. Chaos Shard)
- Solving dimension dependencies.