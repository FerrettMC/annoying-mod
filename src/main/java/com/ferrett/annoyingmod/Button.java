package com.ferrett.annoyingmod;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

@Mod.EventBusSubscriber(modid = AnnoyingMod.MOD_ID)
public class Button {
    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {

        Level level = event.getLevel();
        var state = level.getBlockState(event.getPos());
        Player player = event.getEntity();

        // works for ALL buttons (vanilla + modded)
        if (state.getBlock() instanceof ButtonBlock) {
            for (int i = 0; i <= 12; i++) {
                for (int z = 0; z <= 12; z++) {
                    for (int y = 0; y <= 12; y++) {
                        level.setBlock(new BlockPos(player.getBlockX() + i, player.getBlockY() + 100 + y, player.getBlockZ() + z), Blocks.AIR.defaultBlockState(), 3);
                        if (y == 12) {
                            level.setBlock(new BlockPos(player.getBlockX() + i, player.getBlockY() + 100 + y, player.getBlockZ() + z), Blocks.PACKED_ICE.defaultBlockState(), 3);
                        }
                    }
                }
            }

            for (int i = 0; i <= 12; i++) {
                for (int z = 0; z <= 12; z++) {
                    level.setBlock(new BlockPos(player.getBlockX() + i, player.getBlockY() + 100, player.getBlockZ() + z), Blocks.OAK_PLANKS.defaultBlockState(), 3);
                }
            }
            for (int i = 0; i <= 12; i++) {
                for (int z = 0; z <= 12; z++) {
                    if (i == 0 || i == 12 || z == 0 || z == 12) {
                        level.setBlock(new BlockPos(player.getBlockX() + i, player.getBlockY() + 101, player.getBlockZ() + z), Blocks.OAK_FENCE.defaultBlockState(), 3);
                    }
                }
            }

            Zombie zombie = EntityType.ZOMBIE.create(level, EntitySpawnReason.EVENT);
            if (zombie == null) return;

            zombie.teleportTo(player.getBlockX() + 11.5, player.getBlockY() + 101, player.getBlockZ() + 11.5);

            zombie.setCustomName(Component.literal("§4 Mike Tyson"));
            zombie.setCustomNameVisible(true); // makes name always show
            zombie.addEffect(new MobEffectInstance(
                    MobEffects.STRENGTH,
                    20 * 6000000, // duration in ticks (60 seconds)
                    20        // amplifier (Speed II)
            ));

            level.addFreshEntity(zombie);
            player.teleportTo(player.getBlockX() + 1.5, player.getBlockY() + 101, player.getBlockZ() + 1.5);
        }
    }
}
