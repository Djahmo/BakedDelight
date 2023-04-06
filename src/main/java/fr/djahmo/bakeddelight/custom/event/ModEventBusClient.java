package fr.djahmo.bakeddelight.custom.event;

import fr.djahmo.bakeddelight.BakedDelight;
import fr.djahmo.bakeddelight.custom.entity.model.ChefSuitRenderer;
import fr.djahmo.bakeddelight.custom.item.ChefSuitItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

@Mod.EventBusSubscriber(modid = BakedDelight.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClient {
    @SubscribeEvent
    public static void registerArmorRenderer(final EntityRenderersEvent.AddLayers event) {
        GeoArmorRenderer.registerArmorRenderer(ChefSuitItem.class, ChefSuitRenderer::new);
    }
}
