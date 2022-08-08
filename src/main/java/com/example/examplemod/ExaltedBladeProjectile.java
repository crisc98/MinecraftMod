package com.example.examplemod;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class ExaltedBladeProjectile extends AbstractHurtingProjectile {
	float baseDamage = 50.0f;
	int life = 0, lifeSpan = 100;

	public ExaltedBladeProjectile(EntityType<? extends AbstractHurtingProjectile> entityType, Level level) {
		super(entityType, level);
	}

	@Override
	protected float getInertia() {
		return 1.0F;
	}

	@Override
	protected boolean canHitEntity(Entity entity) {
		return true;
	}

	@Override
	protected void onHitEntity(EntityHitResult entityHitRes) {
		super.onHitEntity(entityHitRes);
		Entity target = entityHitRes.getEntity();
		Entity owner = this.getOwner();
		DamageSource dmgSource;
		if (owner == null) {
			dmgSource = DamageSource.indirectMagic(this, this);
		} else {
			dmgSource = DamageSource.playerAttack((Player) owner);
			if (owner instanceof LivingEntity) {
				((LivingEntity) owner).setLastHurtMob(target);
			}
		}
		target.hurt(dmgSource, this.baseDamage);
	}

	@Override
	public void tick() {
		tickDespawn();
		super.tick();
	}

	protected void tickDespawn() {
		++this.life;
		if (this.life >= this.lifeSpan) {
			this.discard();
		}

	}
}
