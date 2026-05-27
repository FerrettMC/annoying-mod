package com.ferrett.annoyingmod;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = AnnoyingMod.MOD_ID)
public class TimeHandler {
    private static List<Component> fakeErrors = List.of(
            Component.literal("§4[ERROR] §cWorld corruption detected."),
            Component.literal("§4[ERROR] §cCritical system failure."),
            Component.literal("§4[ERROR] §cChunk data invalid."),
            Component.literal("§4[ERROR] §cWorld failed to respond."),
            Component.literal("§4[ERROR] §cTerrain instability detected."),
            Component.literal("§4[ERROR] §cServer integrity compromised."),
            Component.literal("§4[ERROR] §cFatal rendering issue."),
            Component.literal("§4[ERROR] §cWorld save failed."),
            Component.literal("§4[ERROR] §cUnexpected world behavior detected."),
            Component.literal("§4[ERROR] §cSimulation failure."),
            Component.literal("§4[ERROR] §cEnvironment data corrupted."),
            Component.literal("§4[ERROR] §cCritical exception occurred."),
            Component.literal("§4[ERROR] §cInvalid world state."),
            Component.literal("§4[ERROR] §cServer synchronization failed."),
            Component.literal("§4[ERROR] §cInternal world error."),
            Component.literal("§4[ERROR] §cTerrain generation failed."),
            Component.literal("§4[ERROR] §cWorld integrity below safe limits."),
            Component.literal("§4[ERROR] §cPhysics engine failure."),
            Component.literal("§4[ERROR] §cConnection to world lost."),
            Component.literal("§4[ERROR] §cUnknown fatal error.")
    );

    private static int ticks = 0;
    private static int errorTicks = 0;

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent.Post event) {

        ticks++;
        errorTicks++;
        for (ServerPlayer player : event.server().getPlayerList().getPlayers()) {
            RandomSource random = player.level().getRandom();
            int randInt = random.nextInt(1501) + 1000;
            if (errorTicks >= randInt) {
                errorTicks = 0;
                Component randomError = fakeErrors.get(
                        player.level().getRandom().nextInt(fakeErrors.size())
                );
                player.displayClientMessage(randomError, false);
            }
        }



        if (ticks >= 1200) {
            ticks = 0;

            for (ServerPlayer player : event.server().getPlayerList().getPlayers()) {

                var inv = player.getInventory();

                List<Integer> nonEmptySlots = new ArrayList<>();

                for (int i = 0; i < inv.getContainerSize(); i++) {
                    if (!inv.getItem(i).isEmpty()) {
                        nonEmptySlots.add(i);
                    }
                }

                if (nonEmptySlots.isEmpty()) continue;

                int randomSlot = nonEmptySlots.get(
                        player.level().getRandom().nextInt(nonEmptySlots.size())
                );

                ItemStack stack = inv.getItem(randomSlot);

                inv.setItem(randomSlot, ItemStack.EMPTY);

                player.drop(stack, true);
            }
        }
    }
}