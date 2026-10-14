package dev.instantzoom.mixin;

import dev.instantzoom.InstantZoomFabricClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/** Fabric 1.20.1 intermediary: GameRenderer#getFov(Camera,float,boolean). */
@Mixin(targets = "net.minecraft.class_757", remap = false)
public abstract class GameRendererMixin {
    @Inject(
        method = "method_3196(Lnet/minecraft/class_4184;FZ)D",
        at = @At("RETURN"),
        cancellable = true,
        remap = false
    )
    private void instantzoom$changeFov(CallbackInfoReturnable<Double> callback) {
        if (InstantZoomFabricClient.isZoomDown()) {
            callback.setReturnValue(callback.getReturnValue() * 0.25D);
        }
    }
}
