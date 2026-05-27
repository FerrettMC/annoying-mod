package com.ferrett.annoyingmod;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AnnoyingMod.MOD_ID)
public class OnMobDeath {
    @SubscribeEvent
    public static void onMobDeath(LivingDeathEvent event) {
        if (!(event.getEntity() instanceof Mob mob)) return;

        // get the killer
        if (!(event.getSource().getEntity() instanceof Player player)) return;

        player.level().playSound(null, player.blockPosition(),
                ModSounds.FAMILY.get(), SoundSource.PLAYERS, 100.0f, 1.0f);
    }
}
