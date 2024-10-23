package com.servent.testmod;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.eventbus.api.IEventBus;

public class TestModSoundEvent {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, TestMod.MOD_ID);

    public static final RegistryObject<SoundEvent> TEST_BLOCK_PLACE_SOUND = SOUND_EVENTS.register("block.test_block.place", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(TestMod.MOD_ID, "block.test_block.place")));

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
