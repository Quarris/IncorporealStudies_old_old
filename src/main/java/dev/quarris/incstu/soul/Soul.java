package dev.quarris.incstu.soul;

import com.mojang.math.Vector3f;
import dev.quarris.incstu.particles.SoulParticleOptions;
import dev.quarris.incstu.util.RandomUtils;
import dev.quarris.incstu.util.TagUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;

public class Soul implements ISoul {

    // Tag Keys
    private static final String COLOR_TAG = "Color";

    // Init
    private final Entity entity;

    // Data
    private Vector3f color;
    private float strength;
    public Soul(Entity entity) {
        this.entity = entity;
        this.color = new Vector3f(RandomUtils.get().nextFloat(), RandomUtils.get().nextFloat(), RandomUtils.get().nextFloat());
        this.strength = 1 + RandomUtils.get().nextFloat() * 9;
    }

    @Override
    public Vector3f getColor() {
        return this.color;
    }

    @Override
    public float getStrength() {
        return this.strength;
    }

    @Override
    public void tick() {
        if (this.entity.level.isClientSide()) return;
        ServerLevel level = (ServerLevel) this.entity.level;

        if (this.entity.level.getGameTime() % 5 == 0) {
            SoulParticleOptions particleData = new SoulParticleOptions(this.color, this.strength);
            level.sendParticles(particleData, this.entity.getX(), this.entity.getY() + this.entity.getBbHeight() / 2, this.entity.getZ(), 0, 0, 0, 0, 1);
        }
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        TagUtils.putVector3f(tag, COLOR_TAG, this.color);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        this.color = TagUtils.getVector3f(tag, COLOR_TAG);
    }
}
