package com.ferrett.annoyingmod;

import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Mod.EventBusSubscriber(modid = AnnoyingMod.MOD_ID)
public class ShuffleHotbar {
    @SubscribeEvent
    public static void onCraft(PlayerEvent.ItemCraftedEvent event) {
        Player player = event.getEntity();

        shuffleHotbar(player);
    }


    public static void shuffleHotbar(Player player) {
        NonNullList<ItemStack> items = player.getInventory().getNonEquipmentItems();

        List<ItemStack> hotbar = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            hotbar.add(items.get(i).copy());
        }

        Collections.shuffle(hotbar, new Random());

        for (int i = 0; i < 9; i++) {
            items.set(i, hotbar.get(i));
        }

        player.inventoryMenu.broadcastChanges();
    }

}
