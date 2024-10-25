package com.servent.testmod.provider;

import com.servent.testmod.TestMod;
import com.servent.testmod.TestModBlocks;
import com.servent.testmod.TestModItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.ImpossibleTrigger;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.PlacedBlockTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class TestAdvancementProvider extends ForgeAdvancementProvider {
    public TestAdvancementProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper existingFileHelper) {
        super(output, registries, existingFileHelper, List.of(new TestAdvancementGenerator()));
    }

    public static class TestAdvancementGenerator implements AdvancementGenerator {

        @Override
        public void generate(HolderLookup.@NotNull Provider registries, @NotNull Consumer<Advancement> saver, @NotNull ExistingFileHelper helper) {
            Advancement root = Advancement.Builder.advancement()
                    .display(new ItemStack(Blocks.TNT), Component.literal("TestMod"), Component.translatable("block.minecraft.tnt"), new ResourceLocation(TestMod.MOD_ID, "textures/block/test_block.png"), FrameType.TASK, true, false, true)
                    .addCriterion("has_tnt", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.TNT))
                    .save(saver, new ResourceLocation(TestMod.MOD_ID, "root"), helper);

            Advancement test_block = Advancement.Builder.advancement()
                    .display(new ItemStack(TestModBlocks.TEST_BLOCK.get()), Component.translatable("block.testmod.test_block"), Component.translatable("block.testmod.test_block"), null, FrameType.CHALLENGE, true, true, false)
                    .addCriterion("place_test_block", PlacedBlockTrigger.TriggerInstance.placedBlock(TestModBlocks.TEST_BLOCK.get()))
                    .addCriterion("has_test_block", InventoryChangeTrigger.TriggerInstance.hasItems(TestModBlocks.TEST_BLOCK.get()))
                    .parent(root)
                    .save(saver, new ResourceLocation(TestMod.MOD_ID, "test_block"), helper);

            Advancement test_button = Advancement.Builder.advancement()
                    .display(new ItemStack(TestModItems.TEST_BUTTON.get()), Component.translatable("item.testmod.test_button"), Component.translatable("item.testmod.test_button"), null, FrameType.GOAL, true, true, true)
                    .addCriterion("toss_test_button", new ImpossibleTrigger.TriggerInstance())
                    .rewards(AdvancementRewards.Builder.loot(new ResourceLocation("chests/spawn_bonus_chest")))
                    .parent(root)
                    .save(saver, new ResourceLocation(TestMod.MOD_ID, "test_button"), helper);
        }
    }
}
