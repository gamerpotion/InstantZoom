package dev.instantzoom;
          import net.minecraft.client.KeyMapping;
          import net.minecraftforge.api.distmarker.Dist;
          import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
          import net.minecraftforge.client.event.ViewportEvent;
          import net.minecraftforge.eventbus.api.SubscribeEvent;
          import net.minecraftforge.fml.common.Mod;
          import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
          public final class ForgeZoomClient {
              private static KeyMapping zoomKey;
              private ForgeZoomClient() {}
              @Mod.EventBusSubscriber(modid="instantzoom", bus=Bus.MOD, value=Dist.CLIENT)
              public static final class Keys {
                 @SubscribeEvent public static void register(RegisterKeyMappingsEvent e) {
                    zoomKey = new KeyMapping("key.instantzoom.zoom", 67, "key.categories.instantzoom");
                    e.register(zoomKey);
                 }
              }
              @Mod.EventBusSubscriber(modid="instantzoom", bus=Bus.FORGE, value=Dist.CLIENT)
              public static final class Fov {
                 @SubscribeEvent public static void change(ViewportEvent.ComputeFov e) {
                    if (zoomKey != null && isZoomDown()) e.setFOV(e.getFOV() * 0.25D);
                 }
              }
              private static boolean isZoomDown() { try {
               java.lang.reflect.Method method;
               try { method = KeyMapping.class.getMethod("m_90857_"); }
               catch (NoSuchMethodException e) { method = KeyMapping.class.getMethod("isDown"); }
               return Boolean.TRUE.equals(method.invoke(zoomKey));
             } catch (ReflectiveOperationException ex) { return false; } }
          }
