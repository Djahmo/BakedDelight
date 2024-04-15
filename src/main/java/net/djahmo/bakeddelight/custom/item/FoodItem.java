package net.djahmo.bakeddelight.custom.item;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class FoodItem extends Item {

    public final FoodProperties actual;
    public final Item remaining;
    public FoodItem(FoodProperties food) {
        super(new Properties().food(food));
        this.actual = food;
        this.remaining = null;
    }
    public FoodItem(FoodProperties food, Item remaining) {
        super(new Properties().food(food).craftRemainder(remaining));
        this.actual = food;
        this.remaining = remaining;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pEntity) {
        if(remaining != null){
            if (pEntity instanceof ServerPlayer && !((ServerPlayer) pEntity).getAbilities().instabuild) {
                ItemStack bowlStack = new ItemStack(remaining);
                if (!((ServerPlayer) pEntity).getInventory().add(bowlStack)) {
                    pEntity.spawnAtLocation(bowlStack, 0.5f);
                }
            }
        }
        return super.finishUsingItem(pStack, pLevel, pEntity);
    }
}
