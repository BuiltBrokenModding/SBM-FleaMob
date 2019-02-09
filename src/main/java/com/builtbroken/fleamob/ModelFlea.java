package com.builtbroken.fleamob;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/** Created by its_meow
 * On 2/9/2019 with Techne 
 **/

@SideOnly(Side.CLIENT)
public class ModelFlea extends ModelBase
{
	//fields
	ModelRenderer rear;
	ModelRenderer neck01;
	ModelRenderer neck02;
	ModelRenderer head01;
	ModelRenderer legFrontRight01;
	ModelRenderer legFrontRight03;
	ModelRenderer legFrontRight02;
	ModelRenderer legFrontLeft01;
	ModelRenderer legFrontLeft02;
	ModelRenderer legFrontLeft03;
	ModelRenderer legBackRight01;
	ModelRenderer legBackRight02;
	ModelRenderer legBackRight03;
	ModelRenderer legBackLeft01;
	ModelRenderer legBackLeft02;
	ModelRenderer legBackLeft03;

	public ModelFlea() {
		textureWidth = 64;
		textureHeight = 64;

		rear = new ModelRenderer(this, 0, 0);
		rear.addBox(0F, 0F, 0F, 10, 8, 6);
		rear.setRotationPoint(1F, 8F, -3F);
		rear.setTextureSize(64, 64);
		setRotation(rear, 0F, 0F, 0.1858931F);
		neck01 = new ModelRenderer(this, 35, 0);
		neck01.addBox(0F, 0F, 0F, 4, 5, 4);
		neck01.setRotationPoint(-3F, 7.5F, -2F);
		neck01.setTextureSize(64, 64);
		setRotation(neck01, 0F, 0F, 0.1115358F);
		neck02 = new ModelRenderer(this, 35, 11);
		neck02.addBox(0F, 0F, 0F, 4, 4, 4);
		neck02.setRotationPoint(-7F, 7.6F, -2F);
		neck02.setTextureSize(64, 64);
		setRotation(neck02, 0F, 0F, 0F);
		head01 = new ModelRenderer(this, 0, 19);
		head01.addBox(0F, 0F, 0F, 4, 3, 4);
		head01.setRotationPoint(-11F, 7.4F, -2F);
		head01.setTextureSize(64, 64);
		setRotation(head01, 0F, 0F, 0F);
		legFrontRight01 = new ModelRenderer(this, 0, 40);
		legFrontRight01.addBox(0F, 0F, 0F, 6, 1, 1);
		legFrontRight01.setRotationPoint(-7F, 10F, 1F);
		legFrontRight01.setTextureSize(64, 64);
		setRotation(legFrontRight01, 0F, 0F, 1.747395F);
		legFrontRight03 = new ModelRenderer(this, 0, 40);
		legFrontRight03.addBox(0F, 0F, 0F, 6, 1, 1);
		legFrontRight03.setRotationPoint(-7F, 15F, 1F);
		legFrontRight03.setTextureSize(64, 64);
		setRotation(legFrontRight03, 0F, 0F, 1.412787F);
		legFrontRight02 = new ModelRenderer(this, 0, 40);
		legFrontRight02.addBox(0F, 0F, 0F, 2, 1, 1);
		legFrontRight02.setRotationPoint(-8.8F, 15F, 1F);
		legFrontRight02.setTextureSize(64, 64);
		setRotation(legFrontRight02, 0F, 0F, 0F);
		legFrontLeft01 = new ModelRenderer(this, 0, 40);
		legFrontLeft01.addBox(0F, 0F, 0F, 6, 1, 1);
		legFrontLeft01.setRotationPoint(-7F, 10F, -2F);
		legFrontLeft01.setTextureSize(64, 64);
		setRotation(legFrontLeft01, 0F, 0F, 1.747395F);
		legFrontLeft02 = new ModelRenderer(this, 0, 40);
		legFrontLeft02.addBox(0F, 0F, 0F, 2, 1, 1);
		legFrontLeft02.setRotationPoint(-8.8F, 15F, -2F);
		legFrontLeft02.setTextureSize(64, 64);
		setRotation(legFrontLeft02, 0F, 0F, 0F);
		legFrontLeft03 = new ModelRenderer(this, 0, 40);
		legFrontLeft03.addBox(0F, 0F, 0F, 6, 1, 1);
		legFrontLeft03.setRotationPoint(-7F, 15F, -2F);
		legFrontLeft03.setTextureSize(64, 64);
		setRotation(legFrontLeft03, 0F, 0F, 1.412787F);
		legBackRight01 = new ModelRenderer(this, 0, 49);
		legBackRight01.addBox(0F, 0F, 0F, 1, 5, 1);
		legBackRight01.setRotationPoint(2F, 16F, -2F);
		legBackRight01.setTextureSize(64, 64);
		setRotation(legBackRight01, 0F, 0F, -0.2974289F);
		legBackRight02 = new ModelRenderer(this, 0, 49);
		legBackRight02.addBox(0F, 0F, 0F, 2, 1, 1);
		legBackRight02.setRotationPoint(3.3F, 20F, 1F);
		legBackRight02.setTextureSize(64, 64);
		setRotation(legBackRight02, 0F, 0F, -0.1487144F);
		legBackRight03 = new ModelRenderer(this, 0, 49);
		legBackRight03.addBox(0F, 0F, 0F, 6, 1, 1);
		legBackRight03.setRotationPoint(4.9F, 19.4F, 1F);
		legBackRight03.setTextureSize(64, 64);
		setRotation(legBackRight03, 0F, 0F, 0.7807556F);
		legBackLeft01 = new ModelRenderer(this, 0, 49);
		legBackLeft01.addBox(0F, 0F, 0F, 1, 5, 1);
		legBackLeft01.setRotationPoint(2F, 16F, 1F);
		legBackLeft01.setTextureSize(64, 64);
		setRotation(legBackLeft01, 0F, 0F, -0.2974289F);
		legBackLeft02 = new ModelRenderer(this, 0, 49);
		legBackLeft02.addBox(0F, 0F, 0F, 2, 1, 1);
		legBackLeft02.setRotationPoint(3.3F, 20F, -2F);
		legBackLeft02.setTextureSize(64, 64);
		setRotation(legBackLeft02, 0F, 0F, -0.1858931F);
		legBackLeft03 = new ModelRenderer(this, 0, 49);
		legBackLeft03.addBox(0F, 0F, 0F, 6, 1, 1);
		legBackLeft03.setRotationPoint(4.9F, 19.4F, -2F);
		legBackLeft03.setTextureSize(64, 64);
		setRotation(legBackLeft03, 0F, 0F, 0.7807508F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		rear.render(f5);
		neck01.render(f5);
		neck02.render(f5);
		head01.render(f5);
		legFrontRight01.render(f5);
		legFrontRight03.render(f5);
		legFrontRight02.render(f5);
		legFrontLeft01.render(f5);
		legFrontLeft02.render(f5);
		legFrontLeft03.render(f5);
		legBackRight01.render(f5);
		legBackRight02.render(f5);
		legBackRight03.render(f5);
		legBackLeft01.render(f5);
		legBackLeft02.render(f5);
		legBackLeft03.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		if(entity instanceof EntityFlea) {
			EntityFlea entityflea = (EntityFlea) entity;
			if(f == 0) {
				setRotation(legFrontRight03, 0F, 0F, 1.412787F);
				setRotation(legFrontLeft03, 0F, 0F, 1.412787F);
				setRotation(legBackRight03, 0F, 0F, 0.7807556F);
				setRotation(legBackLeft03, 0F, 0F, 0.7807508F);
			} else {
				float jumpRotation = MathHelper.sin(entityflea.getJumpCompletion(f) * (float)Math.PI);
				this.legBackLeft03.rotateAngleZ = (jumpRotation / 2) + 0.7807508F;
				this.legBackRight03.rotateAngleZ = (jumpRotation / 2) + 0.7807508F;
				this.legFrontLeft03.rotateAngleZ = (jumpRotation / 2) + 1.412787F;
				this.legFrontRight03.rotateAngleZ = (jumpRotation / 2) + 1.412787F;
			}
		}
	}

}
