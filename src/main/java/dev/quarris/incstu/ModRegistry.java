package dev.quarris.incstu;

import dev.quarris.incstu.particles.ModParticles;
import net.minecraft.core.particles.ParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModRegistry {

    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, ModRef.ID);

    public static void init(IEventBus bus) {
        PARTICLES.register(bus);

        ModParticles.init();
    }

}
