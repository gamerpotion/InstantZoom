package dev.instantzoom;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;

/** Client-only instant zoom. No camera motion interpolation. */
public final class InstantZoomFabricClient implements ClientModInitializer {
    private static KeyMapping zoomKey;

    @Override
    public void onInitializeClient() {
        KeyMapping.Category category = KeyMapping.Category.register(
            Identifier.fromNamespaceAndPath("instantzoom", "main"));
        zoomKey = KeyMappingHelper.registerKeyMapping(new KeyMapping(
            "key.instantzoom.zoom", InputConstants.Type.KEYBOARD, InputConstants.KEY_C, category));
    }

    public static boolean isZoomDown() {
        KeyMapping key = zoomKey;
        if (key == null) return false;
        Minecraft client = Minecraft.getInstance();
        if (client == null || client.player == null || client.gui.screen() != null) return false;
        if (key.isDown()) return true;
        // In 26.3, SDL scancodes are exposed by InputConstants (not GLFW).
        try {
            InputConstants.Key assigned = InputConstants.getKey(key.saveString());
            return assigned.getType() == InputConstants.Type.KEYBOARD
                && assigned.getValue() > 0
                && InputConstants.isKeyDown(assigned.getValue());
        } catch (IllegalArgumentException error) {
            return false;
        }
    }

    public static float adjustFov(float baseFov) {
        return isZoomDown() ? baseFov * 0.25F : baseFov;
    }
}
