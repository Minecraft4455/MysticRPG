package net.minecraft4455.mysticrpg.core.dimensionregistry.teleporter;

import net.minecraft.util.ChunkCoordinates;

public class YirawiaPortalPosition extends ChunkCoordinates {
	
	public long field_85087_d;
	final YirawiaTeleporter field_85088_e;

	public YirawiaPortalPosition(YirawiaTeleporter tutorialTeleporter,
			int par2, int par3, int par4, long par5) {
		super(par2, par3, par4);
		this.field_85088_e = tutorialTeleporter;
		this.field_85087_d = par5;
	}
}