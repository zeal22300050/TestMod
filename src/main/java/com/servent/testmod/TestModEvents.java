package com.servent.testmod;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;



@Mod.EventBusSubscriber(modid = TestMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TestModEvents {

    public static CreativeModeTab TAB_TEST;

    @SubscribeEvent
    public static void creativeTabsBuildEvent(CreativeModeTabEvent.BuildContents event){
        if(event.getTab() == CreativeModeTabs.TOOLS_AND_UTILITIES) { // TOOLS_AND_UTILITIESの部分を変更するとアイテムが表示されるタブが変わる
            event.accept(TestModItems.TEST_BUTTON.get());
        } else if (event.getTab() == CreativeModeTabs.REDSTONE_BLOCKS) {
            event.accept(TestModBlocks.TEST_BLOCK.get());
        }
    }

    @SubscribeEvent
    public static void creativeTabRegisterEvent(CreativeModeTabEvent.Register event) {
        TAB_TEST = event.registerCreativeModeTab(new ResourceLocation(TestMod.MOD_ID, "tab_test"),
                builder -> builder
                        .icon(() -> TestModItems.TEST_BUTTON.get().getDefaultInstance())
                        .displayItems((parameters, output) -> {
                            output.accept(TestModItems.TEST_BUTTON.get());
                            output.accept(TestModBlocks.TEST_BLOCK.get());
                            output.accept(Blocks.TNT);
                        })
                        .title(Component.translatable("block.testmod.test_block"))); // 文字列を変更するとタブのタイトルを変更出来る
    }
}
