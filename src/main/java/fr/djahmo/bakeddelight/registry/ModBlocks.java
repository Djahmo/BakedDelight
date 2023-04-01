package fr.djahmo.bakeddelight.registry;

import fr.djahmo.bakeddelight.BakedDelight;
import fr.djahmo.bakeddelight.custom.block.BakingCookedDishBlock;
import fr.djahmo.bakeddelight.custom.block.BakingDishBlock;
import fr.djahmo.bakeddelight.custom.block.BakingUncookedDishBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS;
    public static final RegistryObject<Block> BACKING_DISH, UNCOOKED_LASAGNA_DISH, COOKED_LASAGNA_DISH;
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

    public static RegistryObject<Block> setBlock(String name, Supplier<Block> block) {
        RegistryObject<Block> temp = BLOCKS.register(name, block);
        setItemBlock(name, temp);
        return temp;
    }

    public static void setItemBlock(String name, RegistryObject<Block> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(BakedDelight.CREATIVE_TAB)));
    }

    static {
        BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, BakedDelight.MODID);
        BACKING_DISH = setBlock("baking_dish", () -> new BakingDishBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(0.4F)));
        UNCOOKED_LASAGNA_DISH = setBlock("uncooked_lasagna_dish", () -> new BakingUncookedDishBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(0.4F)));
        COOKED_LASAGNA_DISH = setBlock("cooked_lasagna_dish", () -> new BakingCookedDishBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(0.4F), ModItems.LASAGNA_SLICE));
    }
}
