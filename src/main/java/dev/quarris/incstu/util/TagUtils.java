package dev.quarris.incstu.util;

import com.mojang.math.Vector3f;
import net.minecraft.nbt.CompoundTag;

public class TagUtils {

    public static void putVector3f(CompoundTag tag, String key, Vector3f vector) {
        tag.putIntArray(key, new int[] {
            Float.floatToRawIntBits(vector.x()),
            Float.floatToRawIntBits(vector.y()),
            Float.floatToRawIntBits(vector.z())
        });
    }

    public static Vector3f getVector3f(CompoundTag tag, String key) {
        int[] intBits = tag.getIntArray(key);
        return new Vector3f(
            Float.intBitsToFloat(intBits[0]),
            Float.intBitsToFloat(intBits[1]),
            Float.intBitsToFloat(intBits[2])
        );
    }

}
