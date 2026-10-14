package dev.instantzoom;

import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.event.ViewportEvent;
import net.neoforged.neoforge.common.NeoForge;

@Mod(value = "instantzoom", dist = Dist.CLIENT)
public final class InstantZoomNeoForge {
    // NeoForge 26.3 requires explicit registration of custom key mapping categories.
    private static final KeyMapping.Category CATEGORY = new KeyMapping.Category(
        Identifier.fromNamespaceAndPath("instantzoom", "main")
    );

    private static KeyMapping zoom;

    public InstantZoomNeoForge(IEventBus modBus) {
        modBus.addListener(InstantZoomNeoForge::registerKeys);
        NeoForge.EVENT_BUS.addListener(InstantZoomNeoForge::onFov);
    }

    private static void registerKeys(RegisterKeyMappingsEvent event) {
        event.registerCategory(CATEGORY);
        zoom = new KeyMapping("key.instantzoom.zoom", com.mojang.blaze3d.platform.InputConstants.KEY_C, CATEGORY);
        event.register(zoom);
    }

    // Unchanged working zoom implementation from v1.1.0.
    private static void onFov(ViewportEvent.ComputeFov event) {
        if (zoom != null && zoom.isDown()) event.setFOV(event.getFOV() * 0.25F);
    }
}
