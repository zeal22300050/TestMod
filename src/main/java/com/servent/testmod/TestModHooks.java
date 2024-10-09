package com.servent.testmod;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.EntityMobGriefingEvent;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.event.level.ExplosionEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TestMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TestModHooks {
    @SubscribeEvent
    public static void explosionEvent(ExplosionEvent.Detonate event) {
        event.getAffectedEntities().forEach(entity -> {
            if (entity instanceof ItemEntity item && !item.isRemoved() && item.getItem().getItem() == TestModItems.TEST_BUTTON.get()) {
                entity.discard();
                entity.getLevel().explode(null, entity.getX(), entity.getY(), entity.getZ(),6f, Level.ExplosionInteraction.TNT);
            }
        });
    }

    @SubscribeEvent
    public static void entityMobGrieftingEvent(EntityMobGriefingEvent event) {
        if (event.getEntity() instanceof EnderMan) {
            if (!event.getEntity().getLevel().isClientSide()) {
                ((EnderMan) event.getEntity()).addEffect(new MobEffectInstance(MobEffects.GLOWING));
            }
            event.setResult(Event.Result.DENY);
        }
    }

    @SubscribeEvent
    public static void serverChatEvent(ServerChatEvent event) {
        if (event.getRawText().contains("tnt")) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void mobSpawnEvent(MobSpawnEvent.FinalizeSpawn event) {
        if (event.getEntity() instanceof Phantom) {
            event.setSpawnCancelled(true);
        }
    }
}
