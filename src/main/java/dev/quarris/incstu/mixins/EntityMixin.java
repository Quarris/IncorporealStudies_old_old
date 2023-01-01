package dev.quarris.incstu.mixins;

import dev.quarris.incstu.ModRef;
import dev.quarris.incstu.soul.ISoul;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.extensions.IForgeEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin implements IForgeEntity {

    @Shadow public Level level;

    @Inject(method = "baseTick", at = @At("HEAD"))
    private void tickSouls(CallbackInfo ci) {
        this.level.getProfiler().push("tickSouls");
        this.getCapability(ModRef.Capabilities.SOUL).ifPresent(ISoul::tick);
        this.level.getProfiler().pop();
    }

}
