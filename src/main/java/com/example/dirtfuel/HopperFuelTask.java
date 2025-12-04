package com.example.dirtfuel;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.Furnace;
import org.bukkit.block.Hopper;
import org.bukkit.inventory.FurnaceInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class HopperFuelTask implements Runnable {

    @Override
    public void run() {
        for (World world : Bukkit.getWorlds()) {
            for (Chunk chunk : world.getLoadedChunks()) {
                for (BlockState state : chunk.getTileEntities()) {
                    if (state instanceof Furnace) {
                        processFurnace(state.getBlock());
                    }
                }
            }
        }
    }

    private void processFurnace(Block furnaceBlock) {
        if (!(furnaceBlock.getState() instanceof Furnace)) {
            return;
        }

        Furnace furnace = (Furnace) furnaceBlock.getState();
        FurnaceInventory furnaceInv = furnace.getInventory();
        ItemStack currentFuel = furnaceInv.getFuel();

        if (currentFuel != null && currentFuel.getType() != Material.AIR &&
            currentFuel.getType() != Material.DIRT) {
            return;
        }

        if (currentFuel != null && currentFuel.getType() == Material.DIRT &&
            currentFuel.getAmount() >= 64) {
            return;
        }

        BlockFace[] sides = {BlockFace.NORTH, BlockFace.SOUTH, BlockFace.EAST, BlockFace.WEST};

        for (BlockFace face : sides) {
            Block hopperBlock = furnaceBlock.getRelative(face);
            if (hopperBlock.getType() != Material.HOPPER) {
                continue;
            }

            if (!(hopperBlock.getState() instanceof Hopper)) {
                continue;
            }

            Hopper hopper = (Hopper) hopperBlock.getState();
            Inventory hopperInv = hopper.getInventory();

            for (int i = 0; i < hopperInv.getSize(); i++) {
                ItemStack item = hopperInv.getItem(i);
                if (item != null && item.getType() == Material.DIRT && item.getAmount() > 0) {
                    if (currentFuel == null || currentFuel.getType() == Material.AIR) {
                        ItemStack toMove = new ItemStack(Material.DIRT, 1);
                        furnaceInv.setFuel(toMove);
                        item.setAmount(item.getAmount() - 1);
                        hopperInv.setItem(i, item.getAmount() > 0 ? item : null);
                        return;
                    } else if (currentFuel.getType() == Material.DIRT && currentFuel.getAmount() < 64) {
                        currentFuel.setAmount(currentFuel.getAmount() + 1);
                        furnaceInv.setFuel(currentFuel);
                        item.setAmount(item.getAmount() - 1);
                        hopperInv.setItem(i, item.getAmount() > 0 ? item : null);
                        return;
                    }
                }
            }
        }
    }
}
