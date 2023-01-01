package dev.quarris.incstu;

import dev.quarris.incstu.soul.ISoul;
import dev.quarris.incstu.soul.SoulProvider;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ModRef.ID)
public class EventHandler {

    @SubscribeEvent
    public static void attachSoulCapability(AttachCapabilitiesEvent<Entity> event) {
        event.addCapability(ISoul.KEY, new SoulProvider(event.getObject()));
    }
}
