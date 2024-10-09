package com.servent.testmod.provider;

import com.servent.testmod.TestMod;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;


public class TestItemModelProvider extends ItemModelProvider {
    public TestItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super (output, TestMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        this.singleTexture("test_button", mcLoc(folder + "/generated"), "layer0",
                new ResourceLocation("testmod", folder + "/" + "test_button"));
    }
}
