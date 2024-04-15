package net.djahmo.bakeddelight.registry;

import net.djahmo.bakeddelight.BakedDelight;
import net.djahmo.bakeddelight.custom.entity.BakingDishEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntity {
    public static final DeferredRegister<BlockEntityType<?>> ENTITIES;

    public static final RegistryObject<BlockEntityType<BakingDishEntity>> BACKING_DISH;


    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }

    static {
        ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, BakedDelight.MODID);
        BACKING_DISH = ENTITIES.register("baking_dish", () -> BlockEntityType.Builder.of(BakingDishEntity::new, ModBlocks.BAKING_DISH.get()).build(null));
    }
}
