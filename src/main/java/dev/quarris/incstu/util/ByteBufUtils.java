package dev.quarris.incstu.util;

import com.mojang.math.Vector3f;
import net.minecraft.network.FriendlyByteBuf;

public class ByteBufUtils {

    public static Vector3f readVector3f(FriendlyByteBuf buf) {
        return new Vector3f(buf.readFloat(), buf.readFloat(), buf.readFloat());
    }

    public static void writeVector3f(FriendlyByteBuf buf, Vector3f vector) {
        buf.writeFloat(vector.x());
        buf.writeFloat(vector.y());
        buf.writeFloat(vector.z());
    }

}
