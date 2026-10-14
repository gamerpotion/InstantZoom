package dev.instantzoom.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.instantzoom.InstantZoomFabricClient;
import net.minecraft.client.Camera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/** Apply a no-easing, held-key camera zoom to Minecraft 26.3 world and hand FOV. */
@Mixin(Camera.class)
public abstract class CameraMixin {
    // MixinExtras' actual annotation declares method as String[] and at as At[].
    // The previous stub declared scalar versions, silently preventing proper injection.
    @ModifyReturnValue(method = "calculateFov(F)F", at = @At("RETURN"), require = 1)
    private float instantzoom$world(float original) {
        return InstantZoomFabricClient.adjustFov(original);
    }

    @ModifyReturnValue(method = "calculateHudFov(F)F", at = @At("RETURN"), require = 1)
    private float instantzoom$hands(float original) {
        return InstantZoomFabricClient.adjustFov(original);
    }
}
