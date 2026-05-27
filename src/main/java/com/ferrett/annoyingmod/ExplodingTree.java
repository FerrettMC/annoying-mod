package com.ferrett.annoyingmod;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = "annoyingmod", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ExplodingTree {
    private static List<Block> blocks = List.of(
                Blocks.BIRCH_LOG,
                Blocks.OAK_LOG,
                Blocks.ACACIA_LOG,
                Blocks.CHERRY_LOG,
                Blocks.DARK_OAK_LOG,
                Blocks.JUNGLE_LOG,
                Blocks.MANGROVE_LOG,
                Blocks.PALE_OAK_LOG,
                Blocks.SPRUCE_LOG
            );
    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {

        Player player = event.getPlayer();
        ServerLevel serverLevel = null;
        if (!player.level().isClientSide()) {
            serverLevel = (ServerLevel) player.level();
        } else {
            return;
        }
        Block block = event.getState().getBlock();
        BlockPos pos = event.getPos();

        if (blocks.contains(block)) {
            serverLevel.setBlock(pos, Blocks.TNT.defaultBlockState(), 3);
            // Remove the mined block first
            serverLevel.removeBlock(pos, false);

// Create a custom explosion
            serverLevel.explode(
                    null,                          // entity causing it (null = world)
                    pos.getX(), pos.getY(), pos.getZ(), // position
                    15f,                           // radius (vanilla TNT is 4f)
                    true,                         // fire
                    Level.ExplosionInteraction.BLOCK // interaction type
            );

        }
    }

}
