package com.example.dirtfuel;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.FurnaceInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class DirtFuelListener implements Listener {

    private final Plugin plugin;

    public DirtFuelListener(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = false)
    public void onFurnaceBurn(FurnaceBurnEvent event) {
        if (event.getFuel() != null && event.getFuel().getType() == Material.DIRT) {
            event.setBurnTime(200);
            event.setCancelled(false);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().getType() != InventoryType.FURNACE) {
            return;
        }

        FurnaceInventory furnace = (FurnaceInventory) event.getInventory();
        ItemStack clicked = event.getCurrentItem();

        if (event.isShiftClick() && clicked != null && clicked.getType() == Material.DIRT) {
            ItemStack fuelSlot = furnace.getFuel();

            if (fuelSlot == null || fuelSlot.getType() == Material.AIR) {
                event.setCancelled(true);

                ItemStack toMove = clicked.clone();
                toMove.setAmount(1);
                furnace.setFuel(toMove);

                clicked.setAmount(clicked.getAmount() - 1);
                if (clicked.getAmount() <= 0) {
                    event.setCurrentItem(null);
                }
            } else if (fuelSlot.getType() == Material.DIRT && fuelSlot.getAmount() < 64) {
                event.setCancelled(true);

                int spaceLeft = 64 - fuelSlot.getAmount();
                int toAdd = Math.min(spaceLeft, clicked.getAmount());

                fuelSlot.setAmount(fuelSlot.getAmount() + toAdd);
                furnace.setFuel(fuelSlot);

                clicked.setAmount(clicked.getAmount() - toAdd);
                if (clicked.getAmount() <= 0) {
                    event.setCurrentItem(null);
                }
            }
        }
    }
}
