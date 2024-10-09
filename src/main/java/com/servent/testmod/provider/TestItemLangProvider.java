package com.servent.testmod.provider;

import com.servent.testmod.TestMod;
import com.servent.testmod.TestModItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;


public abstract class TestItemLangProvider extends LanguageProvider {
    public TestItemLangProvider(PackOutput output, String locale) {
        super(output, TestMod.MOD_ID,locale);
    }

    public static class TestModLangJP extends TestItemLangProvider {
        public TestModLangJP(PackOutput output) {
            super(output, "ja_jp");
        }

        @Override
        protected void addTranslations() {
            this.add(TestModItems.TEST_BUTTON.get(), "テスト用ボタン");
        }
    }

    public static class TestModLangUS extends TestItemLangProvider {
        public TestModLangUS(PackOutput output) {
            super(output, "en_us");
        }

        @Override
        protected void addTranslations() {
            this.add(TestModItems.TEST_BUTTON.get(), "this test.");
        }
    }
}
