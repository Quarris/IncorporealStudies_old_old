package dev.quarris.incstu.client.particles;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Vector3f;
import dev.quarris.incstu.particles.SoulParticleOptions;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class SoulParticle extends TextureSheetParticle {
    private final SpriteSet sprites;

    public SoulParticle(ClientLevel pLevel, double pX, double pY, double pZ, Vector3f color, float scale, SpriteSet sprites) {
        super(pLevel, pX, pY, pZ);
        this.sprites = sprites;
        this.lifetime = 10;
        this.setAlpha(0.9f);
        this.scale(scale);
        this.setColor(color.x(), color.y(), color.z());
        this.setSpriteFromAge(sprites);
    }

    @Override
    public void tick() {
        super.tick();
        this.setAlpha(1 - (this.age / (float)this.lifetime));
        this.setSpriteFromAge(this.sprites, 4);
    }

    @Override
    public void render(VertexConsumer pBuffer, Camera pRenderInfo, float pPartialTicks) {
        double dist = pRenderInfo.getPosition().distanceTo(new Vec3(this.x, this.y, this.z));
        this.setAlpha(1 - (float) Mth.clamp(dist / 8, 0, 1));
        super.render(pBuffer, pRenderInfo, pPartialTicks);
    }

    public void setSpriteFromAge(SpriteSet sprites, int interval) {
        if (!this.removed) {
            this.setSprite(sprites.get(this.age % interval, interval));
        }
    }

    final ParticleRenderType CUSTOM_TYPE = new ParticleRenderType() {
        public void begin(BufferBuilder buf, TextureManager textures) {
            Minecraft.getInstance().getProfiler().push("soulParticle");
            RenderSystem.enableDepthTest();
            RenderSystem.depthFunc(519);
            RenderSystem.setShaderTexture(0, TextureAtlas.LOCATION_PARTICLES);
            RenderSystem.enableBlend();
            RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            buf.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.PARTICLE);
        }

        public void end(Tesselator tessy) {
            tessy.end();
            RenderSystem.disableDepthTest();
            RenderSystem.depthFunc(515);
            Minecraft.getInstance().getProfiler().pop();
        }

        public String toString() {
            return "INCSTU SOUL";
        }
    };

    @Override
    public ParticleRenderType getRenderType() {
        return CUSTOM_TYPE;
    }
    public static class Provider implements ParticleProvider<SoulParticleOptions> {
        private final SpriteSet sprites;

        public Provider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Nullable
        @Override
        public Particle createParticle(SoulParticleOptions type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new SoulParticle(level, x, y, z, type.color, type.scale, this.sprites);
        }
    }
}
