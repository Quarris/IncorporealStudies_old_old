package dev.quarris.incstu;

import net.minecraft.resources.ResourceLocation;

public class ModRef {

    public static final String ID = "incstu";

    public static ResourceLocation res(String name) {
        return new ResourceLocation(ID, name);
    }
}
