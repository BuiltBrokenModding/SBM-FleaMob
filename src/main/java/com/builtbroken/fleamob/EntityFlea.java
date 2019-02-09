package com.builtbroken.fleamob;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityJumpHelper;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityFlea extends EntityMob {
	
    private int jumpTicks;
    private int jumpDuration;
    private boolean wasOnGround;
    private int currentMoveTypeDuration;
	
	public EntityFlea(World worldIn) {
		super(worldIn);
        this.setSize(0.4F, 0.5F);
        this.jumpHelper = new EntityFlea.FleaJumpHelper(this);
        this.moveHelper = new EntityFlea.FleaMoveHelper(this);
        this.setMovementSpeed(0.0D);
	}

	protected void initEntityAI()
	{
		this.tasks.addTask(1, new EntityFlea.AIEvilAttack(this));
		this.tasks.addTask(2, new EntityAIWanderAvoidWater(this, 0.6D));
		this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, true, true));
	}

	protected float getJumpUpwardsMotion()
	{
		if (!this.collidedHorizontally && (!this.moveHelper.isUpdating() || this.moveHelper.getY() <= this.posY + 0.5D))
		{
			Path path = this.navigator.getPath();

			if (path != null && path.getCurrentPathIndex() < path.getCurrentPathLength())
			{
				Vec3d vec3d = path.getPosition(this);

				if (vec3d.y > this.posY + 0.5D)
				{
					return 0.5F;
				}
			}

			return this.moveHelper.getSpeed() <= 0.6D ? 0.2F : 0.3F;
		}
		else
		{
			return 0.5F;
		}
	}

	/**
	 * Causes this entity to do an upwards motion (jumping).
	 */
	protected void jump()
	{
		super.jump();
		double d0 = this.moveHelper.getSpeed();

		if (d0 > 0.0D)
		{
			double d1 = this.motionX * this.motionX + this.motionZ * this.motionZ;

			if (d1 < 0.010000000000000002D)
			{
				this.moveRelative(0.0F, 0.0F, 1.0F, 0.1F);
			}
		}

		if (!this.world.isRemote)
		{
			this.world.setEntityState(this, (byte)1);
		}
	}

	@SideOnly(Side.CLIENT)
	public float getJumpCompletion(float p_175521_1_)
	{
		return this.jumpDuration == 0 ? 0.0F : ((float)this.jumpTicks + p_175521_1_) / (float)this.jumpDuration;
	}

	public void setMovementSpeed(double newSpeed)
	{
		this.getNavigator().setSpeed(newSpeed);
		this.moveHelper.setMoveTo(this.moveHelper.getX(), this.moveHelper.getY(), this.moveHelper.getZ(), newSpeed);
	}

	public void setJumping(boolean jumping)
	{
		super.setJumping(jumping);

		if (jumping)
		{
			this.playSound(this.getJumpSound(), this.getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) * 0.8F);
		}
	}

	public void startJumping()
	{
		this.setJumping(true);
		this.jumpDuration = 10;
		this.jumpTicks = 0;
	}

	public void updateAITasks()
	{
		if (this.currentMoveTypeDuration > 0)
		{
			--this.currentMoveTypeDuration;
		}

		if (this.onGround)
		{
			if (!this.wasOnGround)
			{
				this.setJumping(false);
				this.checkLandingDelay();
			}

			if (this.currentMoveTypeDuration == 0)
			{
				EntityLivingBase entitylivingbase = this.getAttackTarget();

				if (entitylivingbase != null && this.getDistanceSq(entitylivingbase) < 16.0D)
				{
					this.moveHelper.setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, this.moveHelper.getSpeed());
					this.startJumping();
					this.wasOnGround = true;
				}
			}

			EntityFlea.FleaJumpHelper jumphelper = (EntityFlea.FleaJumpHelper)this.jumpHelper;

			if (!jumphelper.getIsJumping())
			{
				if (this.moveHelper.isUpdating() && this.currentMoveTypeDuration == 0)
				{
					Path path = this.navigator.getPath();
					Vec3d vec3d = new Vec3d(this.moveHelper.getX(), this.moveHelper.getY(), this.moveHelper.getZ());

					if (path != null && path.getCurrentPathIndex() < path.getCurrentPathLength())
					{
						vec3d = path.getPosition(this);
					}

					this.startJumping();
				}
			}
			else if (!jumphelper.canJump())
			{
				this.enableJumpControl();
			}
		}

		this.wasOnGround = this.onGround;
	}

	private void enableJumpControl()
	{
		((EntityFlea.FleaJumpHelper)this.jumpHelper).setCanJump(true);
	}

	private void disableJumpControl()
	{
		((EntityFlea.FleaJumpHelper)this.jumpHelper).setCanJump(false);
	}

	private void updateMoveTypeDuration()
	{
		if (this.moveHelper.getSpeed() < 2.2D)
		{
			this.currentMoveTypeDuration = 10;
		}
		else
		{
			this.currentMoveTypeDuration = 1;
		}
	}

	private void checkLandingDelay()
	{
		this.updateMoveTypeDuration();
		this.disableJumpControl();
	}

	/**
	 * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
	 * use this to react to sunlight and start to burn.
	 */
	public void onLivingUpdate()
	{
		super.onLivingUpdate();

		if (this.jumpTicks != this.jumpDuration)
		{
			++this.jumpTicks;
		}
		else if (this.jumpDuration != 0)
		{
			this.jumpTicks = 0;
			this.jumpDuration = 0;
			this.setJumping(false);
		}
		
		if(this.getAttackTarget() != null) {
			double d1 = this.getAttackTarget().posX - this.posX;
			double d2 = this.getAttackTarget().posZ - this.posZ;
			this.rotationYaw = -((float)MathHelper.atan2(d1, d2)) * (180F / (float)Math.PI);
			this.renderYawOffset = this.rotationYaw;
		}
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(2.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
	}

	protected SoundEvent getJumpSound()
	{
		return SoundEvents.ENTITY_RABBIT_JUMP;
	}

	protected SoundEvent getAmbientSound()
	{
		return SoundEvents.ENTITY_RABBIT_AMBIENT;
	}

	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return SoundEvents.ENTITY_RABBIT_HURT;
	}

	protected SoundEvent getDeathSound()
	{
		return SoundEvents.ENTITY_RABBIT_DEATH;
	}

	public boolean attackEntityAsMob(Entity entityIn) {
		return entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 3.0F);
	}

	/**
	 * Handler for {@link World#setEntityState}
	 */
	@SideOnly(Side.CLIENT)
	public void handleStatusUpdate(byte id)
	{
		if (id == 1)
		{
			this.createRunningParticles();
			this.jumpDuration = 10;
			this.jumpTicks = 0;
		}
		else
		{
			super.handleStatusUpdate(id);
		}
	}

	static class AIEvilAttack extends EntityAIAttackMelee
	{
		public AIEvilAttack(EntityFlea flea)
		{
			super(flea, 1.4D, true);
		}

		protected double getAttackReachSqr(EntityLivingBase attackTarget)
		{
			return (double)(4.0F + attackTarget.width);
		}
	}

	public class FleaJumpHelper extends EntityJumpHelper
	{
		private final EntityFlea flea;
		private boolean canJump;

		public FleaJumpHelper(EntityFlea flea)
		{
			super(flea);
			this.flea = flea;
		}

		public boolean getIsJumping()
		{
			return this.isJumping;
		}

		public boolean canJump()
		{
			return this.canJump;
		}

		public void setCanJump(boolean canJumpIn)
		{
			this.canJump = canJumpIn;
		}

		/**
		 * Called to actually make the entity jump if isJumping is true.
		 */
		public void doJump()
		{
			if (this.isJumping)
			{
				this.flea.startJumping();
				this.isJumping = false;
			}
		}
	}

	static class FleaMoveHelper extends EntityMoveHelper
	{
		private final EntityFlea flea;
		private double nextJumpSpeed;

		public FleaMoveHelper(EntityFlea flea)
		{
			super(flea);
			this.flea = flea;
		}

		public void onUpdateMoveHelper()
		{
			if (this.flea.onGround && !this.flea.isJumping && !((EntityFlea.FleaJumpHelper)this.flea.jumpHelper).getIsJumping())
			{
				this.flea.setMovementSpeed(0.0D);
			}
			else if (this.isUpdating())
			{
				this.flea.setMovementSpeed(this.nextJumpSpeed);
			}

			super.onUpdateMoveHelper();
		}

		/**
		 * Sets the speed and location to move to
		 */
		public void setMoveTo(double x, double y, double z, double speedIn)
		{
			if (this.flea.isInWater())
			{
				speedIn = 1.5D;
			}

			super.setMoveTo(x, y, z, speedIn);

			if (speedIn > 0.0D)
			{
				this.nextJumpSpeed = speedIn;
			}
		}
	}

}
