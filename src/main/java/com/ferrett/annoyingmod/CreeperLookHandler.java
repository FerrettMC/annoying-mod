package com.ferrett.annoyingmod;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AnnoyingMod.MOD_ID)
public class CreeperLookHandler {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent.Post event) {
        Player player = event.player();
        if (player.level().isClientSide()) return;

        Vec3 eyePos = player.getEyePosition();
        Vec3 lookVec = player.getLookAngle();
        double range = 40.0;
        Vec3 endPos = eyePos.add(lookVec.scale(range));

        AABB searchBox = player.getBoundingBox().expandTowards(lookVec.scale(range)).inflate(1.0);

        Entity target = player.level().getEntities(player, searchBox, entity -> entity instanceof Creeper)
                .stream()
                .filter(entity -> {
                    AABB entityBox = entity.getBoundingBox().inflate(0.3);
                    return entityBox.clip(eyePos, endPos).isPresent();
                })
                .findFirst()
                .orElse(null);

        if (target != null) {
            Creeper creeper = (Creeper) target;
            onLookingAtCreeper(player, creeper);
        }
    }

    private static void onLookingAtCreeper(Player player, Creeper creeper) {
        // your logic here
        player.level().explode(
                null,                          // entity causing it (null = world)
                player.getX(), player.getY(), player.getZ(), // position
                5f,                           // radius (vanilla TNT is 4f)
                false,                         // fire
                Level.ExplosionInteraction.MOB // interaction type
        );
    }
}