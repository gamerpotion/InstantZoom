package dev.instantzoom;

import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/** Registered on the MOD event bus only. Never register the FOV handler there. */
public final class ForgeZoomKeyRegistration {
    private ForgeZoomKeyRegistration() {}

    @SubscribeEvent
    public static void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(ForgeZoomClient.createZoomKeyMapping());
    }
}
