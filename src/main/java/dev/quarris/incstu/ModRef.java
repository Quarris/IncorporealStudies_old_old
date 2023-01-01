package dev.quarris.incstu;

import dev.quarris.incstu.soul.ISoul;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class ModRef {

    public static final String ID = "incstu";

    public static ResourceLocation res(String name) {
        return new ResourceLocation(ID, name);
    }

    public static class Capabilities {
        public static final Capability<ISoul> SOUL = CapabilityManager.get(new CapabilityToken<>() {
        });
    }
}
