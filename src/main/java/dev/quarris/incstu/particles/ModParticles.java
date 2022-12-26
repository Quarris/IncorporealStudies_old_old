package dev.quarris.incstu.particles;

import com.mojang.serialization.Codec;
import dev.quarris.incstu.ModRegistry;
import net.minecraft.core.particles.ParticleType;
import net.minecraftforge.registries.RegistryObject;

public class ModParticles {

    public static final RegistryObject<ParticleType<SoulParticleOptions>> SOUL = ModRegistry.PARTICLES.register("soul",
        () -> new ParticleType<>(true, null) {
            @Override
            public Codec<SoulParticleOptions> codec() {
                return null;
            }
        });

    // Static Initializer
    public static void init() {
    }
}
