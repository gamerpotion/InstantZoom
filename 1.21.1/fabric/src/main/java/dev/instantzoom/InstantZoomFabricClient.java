package dev.instantzoom;
            import net.fabricmc.api.ClientModInitializer;
            import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
            import net.minecraft.class_304;
            /** Built against Fabric intermediary names for 1.21.1. */
            public final class InstantZoomFabricClient implements ClientModInitializer {
                private static class_304 zoom;
                @Override public void onInitializeClient() {
                    zoom = KeyBindingHelper.registerKeyBinding(new class_304(
                        "key.instantzoom.zoom", 67, "key.categories.instantzoom"));
                }
                public static boolean isZoomDown() { return zoom != null && zoom.method_1434(); }
            }
