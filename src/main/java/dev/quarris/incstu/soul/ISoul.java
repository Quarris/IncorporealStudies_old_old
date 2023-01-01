package dev.quarris.incstu.soul;

import com.mojang.math.Vector3f;
import dev.quarris.incstu.ModRef;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.util.INBTSerializable;

public interface ISoul extends INBTSerializable<CompoundTag> {

    ResourceLocation KEY = ModRef.res("soul");

    Vector3f getColor();

    float getStrength();

    void tick();
}
