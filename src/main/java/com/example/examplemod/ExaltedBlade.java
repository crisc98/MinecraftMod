package com.example.examplemod;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ExaltedBlade extends SwordItem {
	public ExaltedBlade(Tier tier, int damage, float atkSpeed, Item.Properties props) {
	  super(tier, damage, atkSpeed, props);
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
	  fireProjectile(player);
      return super.use(level, player, hand);
	}
	
	private void fireProjectile(Player player) {
	  Level level = player.level;
	  ExaltedBladeProjectile proj = new ExaltedBladeProjectile(EntityType.FIREBALL, level);
	  Vec3 srcPos = player.position();
	  proj.setPos(srcPos);
	  proj.noPhysics = false;
	  level.addFreshEntity(proj);
	  proj.shootFromRotation(player, player.getXRot(), player.getYRot(), 10.0F, 1.1F, 1.0F);
	}
	
}
