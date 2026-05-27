package com.ferrett.annoyingmod;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = AnnoyingMod.MOD_ID)
public class WalkBreak {
    // Track last position per player UUID
    private static final Map<UUID, BlockPos> lastPositions = new HashMap<>();

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent.Post event) {
        Player player = event.player();
        if (player.level().isClientSide()) return;

        BlockPos currentPos = player.blockPosition();
        BlockPos lastPos = lastPositions.get(player.getUUID());
        if (lastPos == null) {
            lastPositions.put(player.getUUID(), currentPos);
            return;
        }

        if (currentPos.getX() != lastPos.getX() || currentPos.getZ() != lastPos.getZ()) {
            lastPositions.put(player.getUUID(), currentPos);
            BlockPos below = player.blockPosition().below();
            BlockState blockState = player.level().getBlockState(below);
            if (blockState.isAir()) {
                return;
            }
            if (blockState.is(BlockTags.STAIRS)) {
                player.teleportTo(player.getBlockX(), player.getBlockY() + 150, player.getBlockZ());
            }


            onBlockPosChanged(player, lastPos, currentPos);
        }
    }
    @SubscribeEvent
    public static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        lastPositions.remove(event.getEntity().getUUID());
    }

    private static void onBlockPosChanged(Player player, BlockPos oldPos, BlockPos newPos) {
        RandomSource random = player.level().getRandom();

        int randInt = random.nextInt(18); // 25 default
        if (randInt == 0) {
            for (int i = -1; i <= 1; i++) {
                for (int z = -1; z <= 1; z++) {
                    player.level().setBlock(new BlockPos(player.getBlockX() + i, player.getBlockY() - 1, player.getBlockZ() + z), Blocks.AIR.defaultBlockState(), 3);
                }
            }


        }
    }
}
