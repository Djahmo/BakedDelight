package net.djahmo.bakeddelight.registry;

import net.djahmo.bakeddelight.BakedDelight;
import net.djahmo.bakeddelight.custom.block.BakingCookedDishBlock;
import net.djahmo.bakeddelight.custom.block.BakingDishBlock;
import net.djahmo.bakeddelight.custom.block.BakingUncookedDishBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS;
    public static final RegistryObject<Block> BAKING_DISH, UNCOOKED_LASAGNA_DISH, COOKED_LASAGNA_DISH, UNCOOKED_GRATIN_DISH, COOKED_GRATIN_DISH;
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

    public static RegistryObject<Block> setBlock(String name, Supplier<Block> block) {
        RegistryObject<Block> temp = BLOCKS.register(name, block);
        setItemBlock(name, temp);
        return temp;
    }

    public static void setItemBlock(String name, RegistryObject<Block> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().stacksTo(1)));
    }

    static {
        BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, BakedDelight.MODID);

        BAKING_DISH = setBlock("baking_dish", () -> new BakingDishBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(0.4F)));

        UNCOOKED_LASAGNA_DISH = setBlock("uncooked_lasagna_dish", () -> new BakingUncookedDishBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(0.4F)));
        COOKED_LASAGNA_DISH = setBlock("cooked_lasagna_dish", () -> new BakingCookedDishBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(0.4F), ModItems.LASAGNA_SLICE));

        UNCOOKED_GRATIN_DISH = setBlock("uncooked_gratin_dish", () -> new BakingUncookedDishBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(0.4F)));
        COOKED_GRATIN_DISH = setBlock("cooked_gratin_dish", () -> new BakingCookedDishBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(0.4F), ModItems.GRATIN_SLICE));
    }
}
