package dev.quarris.incstu.particles;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.math.Vector3f;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.quarris.incstu.util.ByteBufUtils;
import net.minecraft.core.particles.DustParticleOptionsBase;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.FriendlyByteBuf;

import java.util.Locale;

public class SoulParticleOptions implements ParticleOptions {

    public static final ParticleOptions.Deserializer<SoulParticleOptions> DESERIALIZER = new ParticleOptions.Deserializer<>() {
        public SoulParticleOptions fromCommand(ParticleType<SoulParticleOptions> type, StringReader reader) throws CommandSyntaxException {
            Vector3f vector3f = DustParticleOptionsBase.readVector3f(reader);
            reader.expect(' ');
            float f = reader.readFloat();
            return new SoulParticleOptions(vector3f, f);
        }

        public SoulParticleOptions fromNetwork(ParticleType<SoulParticleOptions> type, FriendlyByteBuf buf) {
            return new SoulParticleOptions(ByteBufUtils.readVector3f(buf), buf.readFloat());
        }
    };

    public static final Codec<SoulParticleOptions> CODEC = RecordCodecBuilder.create((builder) -> builder
        .group(
            Vector3f.CODEC.fieldOf("color").forGetter((particle) -> particle.color),
            Codec.FLOAT.fieldOf("scale").forGetter((particle) -> particle.scale)
        ).apply(builder, SoulParticleOptions::new)
    );

    public final Vector3f color;
    public final float scale;

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
