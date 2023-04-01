package fr.djahmo.bakeddelight.custom.block;

import fr.djahmo.bakeddelight.registry.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import vectorwing.farmersdelight.common.tag.ModTags;

import java.util.function.Supplier;

public class BakingCookedDishBlock extends BakingDishBlock {

    public final Supplier<Item> item;

    public ItemStack getItemSlice() { return this.item.get().getDefaultInstance();}

    public BakingCookedDishBlock(Properties properties, Supplier<Item> item) {
        super(properties);
        this.item = item;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack heldStack = player.getItemInHand(hand);
        if (level.isClientSide) {
            if (heldStack.is(ModTags.KNIVES)) {
                return this.cutSlice(level, pos, state);
            }
            if (this.consumeSlice(level, pos, state, player) == InteractionResult.SUCCESS) {
                return InteractionResult.SUCCESS;
            }
            if (heldStack.isEmpty()) {
                return InteractionResult.CONSUME;
            }
        }
        return heldStack.is(ModTags.KNIVES) ? this.cutSlice(level, pos, state) : this.consumeSlice(level, pos, state, player);
    }

    protected InteractionResult consumeSlice(Level level, BlockPos pos, BlockState state, Player playerIn) {
        if (!playerIn.canEat(false)) {
            return InteractionResult.PASS;
        } else {
            playerIn.eat(level, this.getItemSlice());
            int slices = state.getValue(SLICE);
            if (slices < maxSlice - 1) {
                level.setBlock(pos, state.setValue(SLICE, slices + 1), Block.UPDATE_ALL);
            } else {
                level.removeBlock(pos, false);
                level.setBlock(pos, getBakingDish(state), Block.UPDATE_ALL);
            }
            level.playSound(null, pos, SoundEvents.GENERIC_EAT, SoundSource.PLAYERS, 0.8F, 0.8F);
            return InteractionResult.SUCCESS;
        }
    }

    protected InteractionResult cutSlice(Level level, BlockPos pos, BlockState state) {
        int slices = state.getValue(SLICE);
        if (slices < maxSlice - 1) {
            level.setBlock(pos, state.setValue(SLICE, slices + 1), Block.UPDATE_ALL);
        } else {
            level.removeBlock(pos, false);
            level.setBlock(pos, getBakingDish(state), Block.UPDATE_ALL);
        }
        Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), this.getItemSlice());
        level.playSound(null, pos, ModSounds.CUT_SLICE.get(), SoundSource.PLAYERS, 0.8F, 0.8F);
        return InteractionResult.SUCCESS;
    }
}
