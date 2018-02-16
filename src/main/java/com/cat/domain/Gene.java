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
	
	@OneToOne(cascade={CascadeType.REMOVE,CascadeType.PERSIST,CascadeType.MERGE}) 
	private Kitty kitty;
	
	public Gene(){
		List<Byte> geneList = new ArrayList<>();
		Random num = new Random();
		for(int i=0;i<12;i++){
			for(int j=0;j<4;j++){
				if(i<4){
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
		this.genes = geneList.toArray(new Byte[geneList.size()]);
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
