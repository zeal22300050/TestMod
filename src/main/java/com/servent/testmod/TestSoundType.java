package com.servent.testmod;

import com.servent.testmod.TestModSoundEvent;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;

public class TestSoundType {
    public static final SoundType TEST_BLOCK_SOUND;

    static {
        TEST_BLOCK_SOUND = new SoundType(1.0f, 1.0f,
                SoundEvents.STONE_BREAK,
                TestModSoundEvent.TEST_BLOCK_PLACE_SOUND.get(),
                SoundEvents.STONE_STEP,
                SoundEvents.STONE_HIT,
                SoundEvents.STONE_FALL);
    }
}
