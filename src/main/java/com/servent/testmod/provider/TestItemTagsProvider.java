package com.servent.testmod.provider;

import com.servent.testmod.TestMod;
import com.servent.testmod.TestModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class TestItemTagsProvider extends ItemTagsProvider {
    public static final TagKey<Item> TOSS_EXPLOSIVE = ItemTags.create(new ResourceLocation(TestMod.MOD_ID, "toss_explosive"));

    public TestItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> future, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, future, TestMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        this.tag(TOSS_EXPLOSIVE).add(Items.CREEPER_SPAWN_EGG, TestModItems.TEST_BUTTON.get());
    }
}
