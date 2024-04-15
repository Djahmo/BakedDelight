package net.djahmo.bakeddelight.custom.entity;

import net.djahmo.bakeddelight.registry.ModBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;

import java.util.ArrayList;

public class BakingDishEntity extends BlockEntity {
    public BakingDishEntity( BlockPos pos, BlockState state) {
        super(ModBlockEntity.BACKING_DISH.get(), pos, state);
    }

    private final ItemStackHandler itemHandler = new ItemStackHandler(5) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt.put("slices", itemHandler.serializeNBT());
        super.saveAdditional(nbt);
    }
    
    public void addItem(ItemStack stack, int slot) {
        if (itemHandler.getStackInSlot(slot).isEmpty()) 
            itemHandler.insertItem(slot, stack, false);
    }

    public ArrayList<Item> getItemList() {
        ArrayList<Item> items = new ArrayList<>();
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            if (itemHandler.getStackInSlot(i).isEmpty())
                break;
            items.add(itemHandler.getStackInSlot(i).getItem());
        }
        return items;
    }

    public ItemStack dropItem(int slot) {
        return itemHandler.extractItem(slot, 1, false);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("slices"));
    }

    public void drops() {
        int slots = itemHandler.getSlots();
        SimpleContainer slices = new SimpleContainer(slots);
        for (int i = 0; i < slots; i++)
            slices.setItem(i, itemHandler.getStackInSlot(i));
        assert this.level != null;
        Containers.dropContents(this.level, this.worldPosition, slices);
    }
}
