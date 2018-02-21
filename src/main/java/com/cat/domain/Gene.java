package com.cat.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "gene")
public class Gene {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private Byte[] genes;

	@OneToOne(cascade = { CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.MERGE })
	private Kitty kitty;

	public Gene() {
		List<Byte> geneList = new ArrayList<>();
		Random num = new Random();
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 4; j++) {
				if (i < 4) {
					geneList.add((byte) num.nextInt(Byte.MAX_VALUE));
				} else {
					switch (i) {
					case 4:
						geneList.add((byte) num.nextInt(BodyType.values().length));
						break;
					case 5:
						geneList.add((byte) num.nextInt(PatternType.values().length));
						break;
					case 6:
						geneList.add((byte) num.nextInt(MouthType.values().length));
						break;
					case 7:
						geneList.add((byte) num.nextInt(EyeType.values().length));
						break;
					case 8:
						geneList.add((byte) num.nextInt(PrimaryColor.values().length));
						break;
					case 9:
						geneList.add((byte) num.nextInt(SecondaryColor.values().length));
						break;
					case 10:
						geneList.add((byte) num.nextInt(TertiaryColor.values().length));
						break;
					case 11:
						geneList.add((byte) num.nextInt(EyeColor.values().length));
						break;
					}
				}
			}
		}
		this.genes = geneList.toArray(new Byte[geneList.size()]);
	}

	public Gene(Kitty mKitty, Kitty sKitty) {
		Byte[] mGene = mKitty.getGene().getGenes();
		Byte[] sGene = sKitty.getGene().getGenes();
		Byte[] babyGene = new Byte[48];
		Random num = new Random();
		for (int i = 0; i < 12; i++) {
			int index = 4 * i;
			for (int j = 3; j > 0; j--) {
				if (num.nextInt(100) < 25) {
					byte temp = mGene[index + j];
					mGene[index + j] = mGene[index + j - 1];
					mGene[index + j - 1] = temp;
				}
				if (num.nextInt(100) < 25) {
					byte temp = sGene[index + j];
					sGene[index + j] = sGene[index + j - 1];
					sGene[index + j - 1] = temp;
				}
			}
		}
		// BABY GENES
		for (int i = 0; i < 48; i++) {
			byte mutation = 0;
			// CHECK MUTATION
			if (i % 4 == 0) {
				byte gene1 = mGene[i];
				byte gene2 = sGene[i];
				if (gene1 > gene2) {
					byte temp = gene1;
					gene1 = gene2;
					gene2 = temp;
				}
				if ((gene2 - gene1) == 1 && isEven(gene1)) {
					int probability = 20;
					if (gene1 > 3) {
						probability /= 2;
					}
					if (num.nextInt(100) < probability) {
						switch ((i + 1) / 4) {
						case 4:
							mutation = (byte) num.nextInt(BodyType.values().length);
							break;
						case 5:
							mutation = (byte) num.nextInt(PatternType.values().length);
							break;
						case 6:
							mutation = (byte) num.nextInt(MouthType.values().length);
							break;
						case 7:
							mutation = (byte) num.nextInt(EyeType.values().length);
							break;
						case 8:
							mutation = (byte) num.nextInt(PrimaryColor.values().length);
							break;
						case 9:
							mutation = (byte) num.nextInt(SecondaryColor.values().length);
							break;
						case 10:
							mutation = (byte) num.nextInt(TertiaryColor.values().length);
							break;
						case 11:
							mutation = (byte) num.nextInt(EyeColor.values().length);
							break;
						}
					}

				}
			}
			// GIVE BABY GENES
			if (mutation != 0) {
				babyGene[i] = mutation;
			} else {
				if (num.nextInt(100) < 50)
					babyGene[i] = mGene[i];
				else
					babyGene[i] = sGene[i];
			}
		}
		this.genes = babyGene;
	}

	private boolean isEven(int n) {
		return n % 2 == 0;
	}

	public long getId() {
		return id;
	}

	public Byte[] getGenes() {
		return genes;
	}

	public void setKitty(Kitty kitty) {
		this.kitty = kitty;
	}

	public Kitty getKitty() {
		return kitty;
	}

}
