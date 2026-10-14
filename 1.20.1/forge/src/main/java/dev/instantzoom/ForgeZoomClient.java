package dev.instantzoom;

import java.lang.reflect.Method;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/**
 * Client only. Key mapping is saved/rebindable via vanilla Controls > Key Binds.
 * 4x FOV reduction is applied immediately on each render, never using smooth camera.
 *
 * Forge's distribution normally uses SRG method names on vanilla classes. To
 * allow building the small mod without a ForgeGradle reobf stage, the ONE
 * Minecraft method we need is accessed by its mapped names using reflection.
 */
public final class ForgeZoomClient {
    private static final String CATEGORY = "key.categories.instantzoom";
    private static final String KEY = "key.instantzoom.zoom";
    private static final int DEFAULT_C_KEY = 67; // GLFW_KEY_C
    private static final double FOV_MULTIPLIER = 0.25D;

    private static KeyMapping zoomKey;
    private static Method isDownMethod;

    private ForgeZoomClient() {}

    static KeyMapping createZoomKeyMapping() {
        zoomKey = new KeyMapping(KEY, DEFAULT_C_KEY, CATEGORY);
        return zoomKey;
    }

    @SubscribeEvent
    public static void onComputeFov(ViewportEvent.ComputeFov event) {
        if (event.usedConfiguredFov() && isZoomPressed()) {
            event.setFOV(event.getFOV() * FOV_MULTIPLIER);
        }
    }

    private static boolean isZoomPressed() {
        final KeyMapping key = zoomKey;
        if (key == null) return false;
        try {
            Method method = isDownMethod;
            if (method == null) {
                // SRG production / Mojmap development names for MC 1.20.1.
                try {
                    method = KeyMapping.class.getMethod("m_90857_");
                } catch (NoSuchMethodException ignored) {
                    method = KeyMapping.class.getMethod("isDown");
                }
                isDownMethod = method;
            }
            return Boolean.TRUE.equals(method.invoke(key));
        } catch (ReflectiveOperationException | RuntimeException e) {
            return false;
        }
    }
}
