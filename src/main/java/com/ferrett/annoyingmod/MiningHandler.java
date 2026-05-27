package com.ferrett.annoyingmod;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AnnoyingMod.MOD_ID)
public class MiningHandler {

    @SubscribeEvent
    public static void onMining(PlayerEvent.BreakSpeed event) {
        Player player = event.getEntity();
        BlockPos pos = event.getPosition().orElse(null);
        if (pos == null) return;

        BlockState state = player.level().getBlockState(pos);
        Block block = state.getBlock();

        if (player.getMainHandItem().isEmpty()) {
            RandomSource random = player.level().getRandom();

            int randInt = random.nextInt(7);
            if (randInt == 0) {
                player.hurt(player.damageSources().generic(), 0.5f);
            }
        }
    }
}