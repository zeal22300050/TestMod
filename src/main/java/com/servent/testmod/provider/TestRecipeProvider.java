package com.servent.testmod.provider;

import com.servent.testmod.TestMod;
import com.servent.testmod.TestModBlocks;
import com.servent.testmod.TestModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;

public class TestRecipeProvider extends RecipeProvider{
    public TestRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, TestModItems.TEST_BUTTON.get(), 3)
                .requires(TestModBlocks.TEST_BLOCK.get())
                .group("testitem")
                .unlockedBy("has_test_block", has(TestModBlocks.TEST_BLOCK.get()))
                .save(consumer, new ResourceLocation(TestMod.MOD_ID, "test_button"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, TestModBlocks.TEST_BLOCK.get(), 2)
                .define('#', Blocks.TNT)
                .define('G', Items.GUNPOWDER)
                .pattern(" # ").pattern("GGG").pattern(" # ")
                .group("testitem")
                .unlockedBy("has_tnt", has(Blocks.TNT))
                .save(consumer, new ResourceLocation(TestMod.MOD_ID, "test_block"));

    }
}
