package com.ferrett.annoyingmod;

import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, AnnoyingMod.MOD_ID);

    public static final RegistryObject<SoundEvent> FAMILY =
            SOUND_EVENTS.register("family",
                    () -> SoundEvent.createVariableRangeEvent(
                            Identifier.fromNamespaceAndPath(AnnoyingMod.MOD_ID, "family")
                    ));

    public static void init() {}
}