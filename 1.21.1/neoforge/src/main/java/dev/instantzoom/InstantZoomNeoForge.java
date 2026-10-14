package dev.instantzoom;
          import net.minecraft.client.KeyMapping;
          import net.neoforged.bus.api.IEventBus;
          import net.neoforged.fml.common.Mod;
          import net.neoforged.api.distmarker.Dist;
          import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
          import net.neoforged.neoforge.client.event.ViewportEvent;
          import net.neoforged.neoforge.common.NeoForge;
          @Mod(value="instantzoom", dist=Dist.CLIENT)
          public final class InstantZoomNeoForge {
              private static KeyMapping zoom;
              public InstantZoomNeoForge(IEventBus modBus) {
                  modBus.addListener(InstantZoomNeoForge::registerKeys);
                  NeoForge.EVENT_BUS.addListener(InstantZoomNeoForge::onFov);
              }
              private static void registerKeys(RegisterKeyMappingsEvent e) {
                  zoom = new KeyMapping("key.instantzoom.zoom", 67, "key.categories.instantzoom");
                  e.register(zoom);
              }
              private static void onFov(ViewportEvent.ComputeFov e) {
                  if (zoom != null && zoom.isDown()) e.setFOV(e.getFOV() * 0.25D);
              }
          }
