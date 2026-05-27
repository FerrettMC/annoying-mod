package com.ferrett.annoyingmod;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
@Mod.EventBusSubscriber(modid = AnnoyingMod.MOD_ID)
public class HitSoundHandler {

    @SubscribeEvent
    public static void onHit(LivingHurtEvent event) {
        if (event.getEntity() instanceof Player) return;
        if (!(event.getSource().getEntity() instanceof Player player)) return;

        // pick a random mob sound
        SoundEvent[] sounds = {
                SoundEvents.CREEPER_HURT,
                SoundEvents.ZOMBIE_HURT,
                SoundEvents.ZOMBIE_VILLAGER_HURT,
                SoundEvents.SKELETON_HURT,
                SoundEvents.SPIDER_HURT,
                SoundEvents.ENDERMAN_HURT,
                SoundEvents.BLAZE_HURT,
                SoundEvents.WITCH_HURT,
                SoundEvents.SLIME_HURT,
                SoundEvents.GHAST_HURT,
                SoundEvents.PHANTOM_HURT,
                SoundEvents.HOGLIN_HURT,
                SoundEvents.ZOGLIN_HURT,
                SoundEvents.PIGLIN_HURT,
                SoundEvents.PIGLIN_BRUTE_HURT,
                SoundEvents.ZOMBIFIED_PIGLIN_HURT,
                SoundEvents.DROWNED_HURT,
                SoundEvents.ELDER_GUARDIAN_HURT,
                SoundEvents.GUARDIAN_HURT,
                SoundEvents.EVOKER_HURT,
                SoundEvents.VINDICATOR_HURT,
                SoundEvents.PILLAGER_HURT,
                SoundEvents.RAVAGER_HURT,
                SoundEvents.VEX_HURT,
                SoundEvents.WITHER_HURT,
                SoundEvents.WITHER_SKELETON_HURT,
                SoundEvents.SILVERFISH_HURT,
                SoundEvents.ENDERMITE_HURT,
                SoundEvents.SHULKER_HURT,
                SoundEvents.MAGMA_CUBE_HURT,
                SoundEvents.IRON_GOLEM_HURT,
                SoundEvents.SNOW_GOLEM_HURT,
                SoundEvents.BAT_HURT,
                SoundEvents.BEE_HURT,
                SoundEvents.DOLPHIN_HURT,
                SoundEvents.FOX_HURT,
                SoundEvents.GOAT_HURT,
                SoundEvents.LLAMA_HURT,
                SoundEvents.PANDA_HURT,
                SoundEvents.POLAR_BEAR_HURT,
                SoundEvents.RABBIT_HURT,
                SoundEvents.TURTLE_HURT,
                SoundEvents.CAT_HURT,
                SoundEvents.STRIDER_HURT,
                SoundEvents.AXOLOTL_HURT,
                SoundEvents.GLOW_SQUID_HURT,
                SoundEvents.SQUID_HURT,
                SoundEvents.TROPICAL_FISH_HURT,
                SoundEvents.PUFFER_FISH_HURT,
                SoundEvents.COD_HURT,
                SoundEvents.SALMON_HURT,
                SoundEvents.TADPOLE_HURT,
                SoundEvents.FROG_HURT,
                SoundEvents.ALLAY_HURT,
                SoundEvents.WARDEN_HURT,
                SoundEvents.CAMEL_HURT,
                SoundEvents.SNIFFER_HURT,
                SoundEvents.BREEZE_HURT,
                SoundEvents.BOGGED_HURT,
                SoundEvents.ARMADILLO_HURT,
        };

        RandomSource random = player.level().getRandom();
        SoundEvent sound = sounds[random.nextInt(sounds.length)];

        player.level().playSound(null, event.getEntity().blockPosition(),
                sound, SoundSource.HOSTILE, 100.0f, 1.0f);
    }
}