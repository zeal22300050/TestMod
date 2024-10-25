package com.servent.testmod;

// .*とすることで、provider直下のファイルを全てインポートできる
import com.servent.testmod.provider.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(TestMod.MOD_ID)
public class TestMod {
    public static final String MOD_ID = "testmod"; // MODの名前

    public TestMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::registerProviders);
        TestModBlocks.register(modEventBus);
        TestModItems.register(modEventBus);
    }

    private void registerProviders(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        PackOutput packOutput = gen.getPackOutput();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();
        gen.addProvider(event.includeClient(), new TestItemModelProvider(packOutput, fileHelper));
        gen.addProvider(event.includeClient(), new TestBlockStateProvider(packOutput, fileHelper));
        gen.addProvider(event.includeClient(), new TestItemLangProvider.TestModLangJP(gen.getPackOutput()));
        gen.addProvider(event.includeClient(), new TestItemLangProvider.TestModLangUS(gen.getPackOutput()));
        gen.addProvider(event.includeServer(), new TestRecipeProvider(gen.getPackOutput()));
        gen.addProvider(event.includeServer(), new TestAdvancementProvider(packOutput, event.getLookupProvider(), fileHelper));

        TestBlockTagsProvider blockTagsProvider = new TestBlockTagsProvider(packOutput, event.getLookupProvider(), fileHelper);
        gen.addProvider(event.includeServer(), blockTagsProvider);
        gen.addProvider(event.includeServer(), new TestItemTagsProvider(packOutput, event.getLookupProvider(), blockTagsProvider.contentsGetter(), fileHelper));
    }
}
