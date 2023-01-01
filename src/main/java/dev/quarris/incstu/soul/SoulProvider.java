package dev.quarris.incstu.soul;

import dev.quarris.incstu.ModRef;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SoulProvider implements ICapabilitySerializable<CompoundTag> {

    private final ISoul soul;
    private final LazyOptional<ISoul> lazySoul;

    public SoulProvider(Entity entity) {
        this.soul = new Soul(entity);
        this.lazySoul = LazyOptional.of(() -> this.soul);
    }

    public void invalidateCaps() {
        this.lazySoul.invalidate();
    }

    @Override
    public CompoundTag serializeNBT() {
        return this.soul.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        this.soul.deserializeNBT(tag);
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ModRef.Capabilities.SOUL) {
            return this.lazySoul.cast();
        }

        return LazyOptional.empty();
    }
}
