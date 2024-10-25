package com.servent.testmod;

import com.servent.testmod.provider.TestItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.EntityMobGriefingEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.event.level.ExplosionEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

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
    public static void itemTossEvent(ItemTossEvent event) {
        if (event.getEntity().getItem().is(TestModItems.TEST_BUTTON.get()) && event.getPlayer() instanceof ServerPlayer serverPlayer) {
            serverPlayer.getAdvancements().award(Objects.requireNonNull(Objects.requireNonNull(serverPlayer.getServer()).getAdvancements().getAdvancement(new ResourceLocation(TestMod.MOD_ID, "test_button"))), "toss_test_button");
        }
        if (event.getEntity().getItem().is(TestItemTagsProvider.TOSS_EXPLOSIVE) && !event.getEntity().getLevel().isClientSide()) {
            event.getEntity().getLevel().explode(event.getPlayer(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), 1f, true, Level.ExplosionInteraction.TNT);
        }
    }

    @SubscribeEvent
    public static void mobSpawnEvent(MobSpawnEvent.FinalizeSpawn event) {
        if (event.getEntity() instanceof Phantom) {
            event.setSpawnCancelled(true);
        }
    }
}
