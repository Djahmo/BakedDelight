package net.djahmo.bakeddelight;

import com.mojang.logging.LogUtils;
import net.djahmo.bakeddelight.registry.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(BakedDelight.MODID)
public class BakedDelight {
    public static final String MODID = "bakeddelight";
    private static final Logger LOGGER = LogUtils.getLogger();

    public BakedDelight() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntity.register(modEventBus);
        ModSounds.register(modEventBus);
        ModRecipe.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);

    }
}
