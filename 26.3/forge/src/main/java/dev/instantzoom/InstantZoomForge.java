package dev.instantzoom;

import net.minecraftforge.fml.common.Mod;

/** Forge 26.3 mod entry point. All interactions are client-side. */
@Mod("instantzoom")
public final class InstantZoomForge {
    public InstantZoomForge() {
        ForgeZoomClient.registerListeners();
    }
}
