package dev.quarris.incstu.particles;

import com.mojang.math.Vector3f;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.FriendlyByteBuf;

import java.util.Locale;

public class SoulParticleOptions implements ParticleOptions {

    private Vector3f color;
    private float scale;

    public SoulParticleOptions(Vector3f color, float scale) {
        this.color = color;
        this.scale = scale;
    }

    @Override
    public void writeToNetwork(FriendlyByteBuf buf) {
        buf.writeFloat(this.color.x());
        buf.writeFloat(this.color.y());
        buf.writeFloat(this.color.z());
        buf.writeFloat(this.scale);
    }

    @Override
    public String writeToString() {
        return String.format(Locale.ROOT, "%s %.2f %.2f %.2f %.2f", this.getType().getRegistryName().toString(), this.color.x(), this.color.y(), this.color.z(), this.scale);
    }

    @Override
    public ParticleType<?> getType() {
        return ModParticles.SOUL.get();
    }
}
