package dev.instantzoom;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.event.ViewportEvent;

/**
 * Instant, hold-to-zoom, client-only. Forge 26.3 introduced event-specific buses;
 * do not annotate these listener methods with old-style @SubscribeEvent.
 */
public final class ForgeZoomClient {
    private static KeyMapping zoomKey;
    private static boolean registered;

    private ForgeZoomClient() {}

    public static void registerListeners() {
        if (registered) return;
        registered = true;
        RegisterKeyMappingsEvent.BUS.addListener(ForgeZoomClient::registerKey);
        ViewportEvent.ComputeFov.BUS.addListener(ForgeZoomClient::modifyFov);
    }

    private static void registerKey(RegisterKeyMappingsEvent event) {
        KeyMapping.Category category = KeyMapping.Category.register(
            Identifier.fromNamespaceAndPath("instantzoom", "main"));
        zoomKey = new KeyMapping("key.instantzoom.zoom", InputConstants.Type.KEYBOARD,
                                 InputConstants.KEY_C, category);
        event.register(zoomKey);
    }

    private static void modifyFov(ViewportEvent.ComputeFov event) {
        if (isZoomDown()) event.setFOV(event.getFOV() * 0.25F);
    }

    public static boolean isZoomDown() {
        KeyMapping key = zoomKey;
        if (key == null) return false;
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft == null || minecraft.player == null || minecraft.gui.screen() != null) return false;
        if (key.isDown()) return true;

        // SDL physical-key fallback (uses the player's configured binding).
        try {
            InputConstants.Key assigned = InputConstants.getKey(key.saveString());
            return assigned.getType() == InputConstants.Type.KEYBOARD
                   && assigned.getValue() > 0
                   && InputConstants.isKeyDown(assigned.getValue());
        } catch (IllegalArgumentException exception) {
            return false;
        }
    }
}
