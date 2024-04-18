package net.djahmo.bakeddelight.custom.datagen.provider;

import net.djahmo.bakeddelight.BakedDelight;
import net.djahmo.bakeddelight.custom.block.BakingDishBlock;
import net.djahmo.bakeddelight.registry.ModBlocks;
import net.djahmo.bakeddelight.utils.Function;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, BakedDelight.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        registerBakingDish(ModBlocks.BAKING_DISH.get());

        registerCrop(ModBlocks.EGGPLANT_CROP.get());
        registerCrop(ModBlocks.ZUCCHINI_CROP.get());

        registerCookedDishBlock(ModBlocks.COOKED_LASAGNA_DISH.get());
        registerCookedDishBlock(ModBlocks.COOKED_GRATIN_DISH.get());
        registerCookedDishBlock(ModBlocks.COOKED_MOUSSAKA_DISH.get());
        registerCookedDishBlock(ModBlocks.COOKED_TIAN_DISH.get());

        registerUncookedDishBlock(ModBlocks.UNCOOKED_LASAGNA_DISH.get());
        registerUncookedDishBlock(ModBlocks.UNCOOKED_GRATIN_DISH.get());
        registerUncookedDishBlock(ModBlocks.UNCOOKED_MOUSSAKA_DISH.get());
        registerUncookedDishBlock(ModBlocks.UNCOOKED_TIAN_DISH.get());

    }

    private void registerCookedDishBlock(Block block) {
        String dishName = Function.getItemName(block);
        for (Direction dir : Direction.Plane.HORIZONTAL) {
            for (int slice = 0; slice <= 5; slice++) {
                int yRotation = getYRotation(dir);
                getVariantBuilder(block)
                    .partialState()
                    .with(BlockStateProperties.HORIZONTAL_FACING, dir)
                    .with(BakingDishBlock.SLICE, slice)
                    .modelForState()
                    .modelFile(models().getExistingFile(modLoc("block/" + dishName + (slice != 0 ? "_" + slice + "slice" : ""))))
                    .rotationY(yRotation)
                    .addModel();
            }
        }
    }

    private void registerUncookedDishBlock(Block block) {
        String dishName = Function.getItemName(block);
        for (Direction dir : Direction.Plane.HORIZONTAL) {
            int yRotation = getYRotation(dir);
            getVariantBuilder(block)
                    .partialState()
                    .with(BlockStateProperties.HORIZONTAL_FACING, dir)
                    .modelForState()
                    .modelFile(models().getExistingFile(modLoc("block/" + dishName)))
                    .rotationY(yRotation)
                    .addModel();
        }
    }

    private void registerCrop(Block block) {
        String plantName = Function.getItemName(block).split("_")[0];
        for (int i = 0; i <= 5; i++) {
            getVariantBuilder(block)
                .partialState()
                .with(BlockStateProperties.AGE_5, i) // Assuming the crop uses the common age property with range 0 to 7
                .modelForState()
                .modelFile(models().getExistingFile(modLoc("block/" + plantName + "_stage" + i)))
                .addModel();
        }
    }

    private void registerBakingDish(Block block) {
        VariantBlockStateBuilder builder = getVariantBuilder(block);

        for (Direction dir : Direction.Plane.HORIZONTAL) {
            int yRotation = getYRotation(dir);
            builder
                .partialState()
                .with(BlockStateProperties.HORIZONTAL_FACING, dir)
                .with(BakingDishBlock.SLICE, 0)
                .modelForState()
                .modelFile(models().getExistingFile(modLoc("block/baking_dish")))
                .rotationY(yRotation)
                .addModel();
        }

        for (Direction dir : Direction.Plane.HORIZONTAL) {
            for (int slice = 0; slice <= 5; slice++) {
                for (int dish = 1; dish <= 4; dish++) {
                    if (slice == 0) continue;
                    int yRotation = getYRotation(dir);
                    String modelName = String.format("block/uncooked_%s_dish_%sslice", getDishName(dish), slice);
                    builder
                        .partialState()
                        .with(BlockStateProperties.HORIZONTAL_FACING, dir)
                        .with(BakingDishBlock.SLICE, slice)
                        .with(BakingDishBlock.DISH, dish)
                        .modelForState()
                        .modelFile(models().getExistingFile(modLoc(modelName)))
                        .rotationY(yRotation)
                        .addModel();
                }
            }
        }
    }

    private int getYRotation(Direction direction) {
        return switch (direction) {
            case SOUTH -> 180;
            case WEST -> 270;
            case EAST -> 90;
            default -> 0;
        };
    }

    private String getDishName(int dish) {
        return switch (dish) {
            case 1 -> "lasagna";
            case 2 -> "gratin";
            case 3 -> "moussaka";
            case 4 -> "tian";
            default -> "unknown";
        };
    }
}
