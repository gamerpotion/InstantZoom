package dev.instantzoom;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.class_304;

/** Client initializer in intermediary names for Minecraft 1.20.1. */
public final class InstantZoomFabricClient implements ClientModInitializer {
    private static class_304 zoomKey;

    @Override
    public void onInitializeClient() {
        // constructor: (translation key, GLFW keysym, category); 67 = C
        zoomKey = KeyBindingHelper.registerKeyBinding(new class_304(
            "key.instantzoom.zoom", 67, "key.categories.instantzoom"
        ));
    }

    public static boolean isZoomDown() {
        return zoomKey != null && zoomKey.method_1434(); // KeyBinding.isPressed()
    }
}
