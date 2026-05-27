package com.ferrett.annoyingmod;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AnnoyingMod.MOD_ID)
public class KnockbackHandler {
    @SubscribeEvent
    public static void onKnockback(LivingKnockBackEvent event) {
        if (event.getEntity() instanceof Player) return;

        float strength = event.getStrength();
        double dx = event.getRatioX();
        double dz = event.getRatioZ();

        event.setStrength(0);
        event.setRatioX(0);
        event.setRatioZ(0);

        // get the player who last hit this mob
        Player player = event.getEntity().getLastHurtByMob() instanceof Player p ? p : null;
        if (player == null) return;

        player.knockback(strength, -dx, -dz);
        player.hurtMarked = true;

    }
}