package DQMIIaddAMT.Handler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.EventPriority;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import DQMII.DqmMobExp;
import DQMII.Function.ExtendedPlayerProperties;
import DQMIIaddAMT.DqmAMTAddon;

public class LivingEventHandler {
	Random rand = new Random();

	@ForgeSubscribe
    public void onLivingUpdate(LivingUpdateEvent event) {
    	if (event.entityLiving instanceof EntityPlayer) {

    		EntityPlayer ep = (EntityPlayer)event.entityLiving;

        	Collection pe = ep.getActivePotionEffects();

        	boolean protectExplode = false;
        	int lvImmunization = 0;
        	int lvSuffocation = 0;

    	    for (Iterator i = pe.iterator(); i.hasNext();) {
    	        PotionEffect element = (PotionEffect) i.next();

    	        if(element.getPotionID() == DqmAMTAddon.amtCfg.ptProtecExplode)
    	        {
    	        	protectExplode = true;
    	        }
    	        if(element.getPotionID() == DqmAMTAddon.amtCfg.ptImmunization)
    	        {
    	        	lvImmunization = element.getAmplifier() + 1;
    	        }
    	        if(element.getPotionID() == DqmAMTAddon.amtCfg.ptSuffocation)
    	        {
    	        	lvSuffocation = element.getAmplifier() + 1;
    	        }
    	        //System.out.println(element.getPotionID());
    	      }

    	    if(protectExplode)
    	    {
    	    	ExtendedPlayerProperties.get(ep).setResistExplosion(0, 500);
    	    }else
    	    {
    	    	ExtendedPlayerProperties.get(ep).setResistExplosion(0, 0);
    	    }

    	    if(lvImmunization >= 2)
    	    {
    	    	ep.removePotionEffect(Potion.Rukani.getId());
    	    	ep.removePotionEffect(Potion.Henatosu.getId());
    	    }

    	    /*
    	    if(lvSuffocation > 0)
    	    {
    	    	ep.HP = ep.HP - (lvSuffocation * 2);
    			if(!ep.worldObj.isRemote)
    			{
    				ep.processCommandLV(ep, null);
    			}
    	    }
    	    */
        	 /*
        	 for(int cnt = 0; cnt < pe.size(); cnt++)
        	 {

        	 }
        	 */
        	 /*
        	if(pe2 != null)
        	{
        		ep.removePotionEffect(Potion.fireResistance.getId());
        		PotionEffect rePe = new PotionEffect(Potion.Honoo.getId(), pe2.getDuration(), 0);
        		ep.addPotionEffect(rePe);
        		ep.Honoo = 1;
        	}
    	*/
    	}
    }

    @ForgeSubscribe(priority = EventPriority.HIGH)
    public void onLivingHurt(LivingHurtEvent event)
    {

    	if(event.entityLiving instanceof EntityPlayer)
    	{
    		EntityPlayer ep = (EntityPlayer)event.entityLiving;

    		//System.out.println("DEBUGLINE:1" + event.source.getDamageType());
    		if(event.source == DamageSource.inWall)
    		{
    			PotionEffect pe2 = ep.getActivePotionEffect(Potion.potionTypes[DqmAMTAddon.amtCfg.ptProtecSuffocation]);
    			if(pe2 != null)
    			{
	    	    	PotionEffect pe = ep.getActivePotionEffect(Potion.potionTypes[DqmAMTAddon.amtCfg.ptSuffocation]);

	    	    	int damageEp = 0;// = ep.HPMAX() / 20;
	    	    	if(pe != null)
	    	    	{
	    	    		//System.out.println("DEBUGLINE:" + pe.getAmplifier());
	    	    		damageEp = damageEp + (pe.getAmplifier() * ep.HPMAX() / 20);
	    	    	}
	    	    	//System.out.println("DEBUGLINE:" + damageEp);
	    	    	ep.HP = ep.HP - damageEp;
	    			if(!ep.worldObj.isRemote)
	    			{
	    				ep.processCommandLV(ep, null);
	    			}
    			}
    		}

    		boolean flg = false;
    		boolean flg2 = false;

    		PotionEffect pe2 = ep.getActivePotionEffect(Potion.potionTypes[DqmAMTAddon.amtCfg.ptDamageAbsorption]);
    		if(pe2 != null)
    		{

    			if(pe2.getAmplifier() > 2)
    			{
    				flg = true;
    			}else if(pe2.getAmplifier() > 1 && (event.source.isExplosion() || event.source.isFireDamage()))
    			{
    				flg = true;
    			}else
    			{
    				if(event.source instanceof EntityDamageSource)
    				{
    					flg = true;
    				}
    			}

    			if(flg)
    			{
    				if(ep.HP + event.ammount > ep.HPMAX())
    				{
    					ep.HP = ep.HPMAX();
    				}else
    				{
    					ep.HP = ep.HP + event.ammount;
    				}
    			}
    		}

    		pe2 = ep.getActivePotionEffect(Potion.potionTypes[DqmAMTAddon.amtCfg.ptEXPAbsorption]);
    		if(pe2 != null)
    		{

    			if(pe2.getAmplifier() > 2)
    			{
    				flg2 = true;
    			}else if(pe2.getAmplifier() > 1 && (event.source.isExplosion() || event.source.isFireDamage()))
    			{
    				flg2 = true;
    			}else
    			{
    				if(event.source instanceof EntityDamageSource)
    				{
    					flg2 = true;
    				}
    			}

    			if(flg2)
    			{
    				DqmMobExp.Job(ep, event.ammount * 3);
    			}
    		}

    		if(flg || flg2)
    		{
    			event.ammount = 0;
    			if(!ep.worldObj.isRemote)
    			{
    				ep.processCommandLV(ep, null);
    			}
    		}

    	}

    }

    @ForgeSubscribe
    public void onLivingDeathEvent(LivingDeathEvent event)
    {
    	//System.out.println("DEBUGLINE:" + event.entity.getEntityName());
    	//System.out.println("DEBUGLINE:" + event.entityLiving.getLastAttackingEntity().getEntityName());

    	//EntityCreature test = (EntityCreature)event.entity;
    	//event.source.getSourceOfDamage()


    	if(event.source.getSourceOfDamage() instanceof EntityPlayer)
    	{
    		EntityPlayer killerEP = (EntityPlayer)event.source.getSourceOfDamage();


    		if(killerEP.inventory.hasItemStack(new ItemStack(DqmAMTAddon.amtCfg.itmPrincessClam, 1, 2)))
    		{
    			int plusExp = event.entityLiving.experienceValue * rand.nextInt(5) / 10;
    			DqmMobExp.Job(killerEP, plusExp);
    		}
    	}

    }

    @ForgeSubscribe
    public void onLivingAttackEvent(LivingAttackEvent event)
    {
    	/*
    	if(event.entityLiving instanceof EntityPlayer)
    	{
    		System.out.println("DEBUGLINE:A");
    	}
    	*/

    }
    @ForgeSubscribe
    public void onLivingDropsEvent(LivingDropsEvent event)
    {
    	EntityLiving entity = event.entityLiving;
		World world = entity.worldObj;
		double posX = entity.posX;
		double posY = entity.posY;
		double posZ = entity.posZ;

    	if(event.source.getSourceOfDamage() instanceof EntityPlayer)
    	{
    		EntityPlayer killerEP = (EntityPlayer)event.source.getSourceOfDamage();

    		if(killerEP.inventory.hasItemStack(new ItemStack(DqmAMTAddon.amtCfg.itmPrincessClam, 1, 1)) && rand.nextInt(3) == 0)
    		{
    			ArrayList<EntityItem> arDrops = event.drops;
    			for(int cnt = 0; cnt < arDrops.size(); cnt++)
    			{
					//ItemStack isReDrop = new ItemStack(arDrops.get(cnt).)
					if (arDrops.get(cnt) != null)
					{
						world.spawnEntityInWorld(arDrops.get(cnt));
					}
    			}
    		}
    	}
    }
}
