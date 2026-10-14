package dev.instantzoom;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;

/** Forge mod entrypoint. Server-safe: client classes are registered only on the client. */
@Mod("instantzoom")
public final class ForgeInstantZoom {
    public ForgeInstantZoom() {
        if (FMLEnvironment.dist == Dist.CLIENT) {
            FMLJavaModLoadingContext.get().getModEventBus().register(ForgeZoomKeyRegistration.class);
            MinecraftForge.EVENT_BUS.register(ForgeZoomClient.class);
        }
    }
}
