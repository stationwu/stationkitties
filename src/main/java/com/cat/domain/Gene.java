package com.cat.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
	
	private List<Byte> genes;
	
	@OneToOne
	private Kitty kitty;
	
	private Image kittyImage;
	
	public long getId() {
		return id;
	}

	public void setGenes(List<Byte> genes) {
		this.genes = genes;
	}
	
	public List<Byte> getGenes() {
		return genes;
	}
	
	public Kitty getKitty() {
		return kitty;
	}
	
	public Gene(){
		List<Byte> geneList = new ArrayList<>();
		Random num = new Random();
		for(int i=0;i<12;i++){
			for(int j=0;i<4;j++){
				if(i<5){
					geneList.add((byte) num.nextInt(Byte.MAX_VALUE));
				}else{
					switch (i) {
					case 4:
						geneList.add((byte)num.nextInt(BodyType.values().length));
						break;
					case 5:
						geneList.add((byte)num.nextInt(PatternType.values().length));
						break;
					case 6:
						geneList.add((byte)num.nextInt(MouthType.values().length));
						break;
					case 7:
						geneList.add((byte)num.nextInt(EyeType.values().length));
						break;
					case 8:
						geneList.add((byte)num.nextInt(PrimaryColor.values().length));
						break;
					case 9:
						geneList.add((byte)num.nextInt(SecondaryColor.values().length));
						break;
					case 10:
						geneList.add((byte)num.nextInt(TertiaryColor.values().length));
						break;
					case 11:
						geneList.add((byte)num.nextInt(EyeColor.values().length));
						break;
					}
				}
			}
		}
		this.genes = geneList;
	}
	
	public Image generateKittyImage(List<Byte> geneList){
		BodyType bodyType = BodyType.byOrdinal(geneList.get(19));
		PatternType patternType = PatternType.byOrdinal(geneList.get(23));
		MouthType mouthType = MouthType.byOrdinal(geneList.get(27));
		EyeType eyeType = EyeType.byOrdinal(geneList.get(31));
		PrimaryColor primaryColor = PrimaryColor.byOrdinal(geneList.get(35));
		SecondaryColor secondaryColor = SecondaryColor.byOrdinal(geneList.get(39));
		TertiaryColor tertiaryColor = TertiaryColor.byOrdinal(geneList.get(43));
		EyeColor eyeColor = EyeColor.byOrdinal(geneList.get(47));
		String kittyImagePath =  bodyType.getPath() + '-' + patternType.getPatternName();
		String kittyMouthPath = mouthType.getPath();
		String kittyEyePath = eyeType.getPath();
		
	}
}
