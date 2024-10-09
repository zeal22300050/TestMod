package com.servent.testmod.provider;

import com.servent.testmod.TestMod;
import com.servent.testmod.TestModBlocks;
import com.servent.testmod.TestModItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TestBlockStateProvider extends BlockStateProvider{
    public TestBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, TestMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        this.simpleBlockWithItem(TestModBlocks.TEST_BLOCK.get(), this.cubeAll(TestModBlocks.TEST_BLOCK.get()));
    }
}
