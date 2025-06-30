package com.deedeemcx.nofakeriptide;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class NoFakeRiptide extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("NoFakeRiptide has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("NoFakeRiptide has been disabled!");
    }

    @EventHandler
    public void onPlayerUseTrident(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();
        ItemStack item = event.getItem();

        if (item != null && item.getType() == Material.TRIDENT && event.getAction() == Action.RIGHT_CLICK_AIR) {

            // Allow Riptide if player is underwater
            if (player.isInWater()) {
                return;
            }

            // Otherwise, block Riptide if not raining
            if (!world.hasStorm()) {
                event.setCancelled(true);
            }
        }
    }
}