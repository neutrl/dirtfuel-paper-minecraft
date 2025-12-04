# DirtFuel

A Minecraft Paper plugin that allows dirt blocks to be used as furnace fuel.

## Features

- **Use dirt as fuel**: Dirt blocks can now power furnaces for smelting
- **Multiple loading methods**:
  - Shift-click dirt from your inventory into furnaces
  - Automated hopper loading from the sides
- **Proper fuel consumption**: Each dirt block burns for 200 ticks (10 seconds)
- **Vanilla-like behavior**: Works just like coal, wood, or other standard fuels
- **Automated systems**: Build fully automatic smelting setups with hoppers

## Compatibility

- **Minecraft Version**: 1.21.4 (compatible with 1.21.x)
- **Server Software**: Paper, Spigot
- **Java Version**: 21

## Installation

1. Download the latest `dirt-fuel-1.0.0.jar` from the releases page
2. Place the JAR file in your server's `plugins/` folder
3. Restart your server or run `/reload confirm`
4. Dirt can now be used as fuel!

## Usage

### Manual Method
1. Open a furnace
2. **Shift-click** dirt blocks from your inventory
3. Dirt will be placed in the fuel slot
4. Add items to smelt and watch it work!

### Hopper Method (Automated)
1. Place a furnace
2. Place a hopper **next to** (touching the side of) the furnace
3. Fill the hopper with dirt
4. The plugin automatically transfers dirt into the fuel slot

### Minecraft Hopper Setup
```
[Hopper with items]
        ↓
   [Furnace] ← [Hopper with dirt fuel]
        ↓
[Output Hopper]
```

## Fuel Properties

- **Burn Time**: 200 ticks (10 seconds per dirt block)
- **Equivalent to**: Wooden planks
- **Stack Size**: Up to 64 dirt blocks in the fuel slot

## Building from Source

### Requirements
- Java 21 or higher
- Maven 3.6+

### Build Steps
```bash
# Clone the repository
git clone https://github.com/yourusername/dirt-fuel.git
cd dirt-fuel

# Build with Maven
mvn clean package

# The compiled JAR will be in target/dirt-fuel-1.0.0.jar
```

## Development

### Project Structure
```
src/
├── main/
│   ├── java/com/example/dirtfuel/
│   │   ├── DirtFuelPlugin.java      # Main plugin class
│   │   ├── DirtFuelListener.java    # Event listeners
│   │   └── HopperFuelTask.java      # Hopper automation
│   └── resources/
│       └── plugin.yml                # Plugin configuration
└── pom.xml                           # Maven configuration
```

### How It Works

1. **FurnaceBurnEvent**: Detects when dirt is used as fuel and sets burn time
2. **InventoryClickEvent**: Allows shift-clicking dirt into furnace fuel slots
3. **HopperFuelTask**: Scheduled task that transfers dirt from hoppers to furnaces

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contributing

Contributions are welcome! Feel free to:
- Report bugs
- Suggest new features
- Submit pull requests

## Support

If you encounter any issues or have questions:
- Open an issue on GitHub
- Check existing issues for solutions

## Changelog

### Version 1.0.0
- Initial release
- Dirt can be used as furnace fuel
- Shift-click support
- Hopper automation support
- Burns for 200 ticks per block

## Acknowledgments

Generated with [Claude Code](https://claude.com/claude-code)
