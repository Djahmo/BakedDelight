package net.djahmo.bakeddelight.registry;

import net.djahmo.bakeddelight.BakedDelight;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS;

    public static final RegistryObject<SoundEvent> CUT_SLICE;
    public static void register(IEventBus eventBus) {
        SOUNDS.register(eventBus);
    }

    public static RegistryObject<SoundEvent> setSound(String name) {
        return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(BakedDelight.MODID, name)));
    }
    static {
        SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, BakedDelight.MODID);

        CUT_SLICE = setSound("cut_slice");
    }
}
