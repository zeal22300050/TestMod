package com.servent.testmod;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TestModBlocks {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TestMod.MOD_ID);
    public  static final RegistryObject<Block> TEST_BLOCK = BLOCKS.register("test_block", () -> new Block(BlockBehaviour.Properties.of(Material.EXPLOSIVE).lightLevel((value -> 15))));

    public static void register(IEventBus eventBus) {
        BLOCKS.register((eventBus));
    }
}
