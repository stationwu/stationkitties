package com.cat.domain;

public enum MouthType implements KittyProperty {
	WHIXTENSIONS(1, "whixtensions"), DALI(2, "dali"), SAYCHEESE(3, "saycheese"), BEARD(4, "beard"), TONGUE(5,
			"tongue"), HAPPYGOKITTY(6,
					"happygokitty"), POUTY(7, "pouty"), SOSERIOUS(8, "soserious"), GERBIL(0, "gerbil");

	private int index;

	private String fileName;
	
	final private String folder = "\\mouth\\";

	private MouthType(int index, String fileName) {
		this.index = index;
		this.fileName = fileName;
	}

	public int getIndex() {
		return index;
	}

	public String getFileName() {
		return fileName;
	}

	@Override
	public String getPath() {
		return this.folder + this.fileName;
	}
	
	public static MouthType byOrdinal(int ord) {
        for (MouthType m : MouthType.values()) {
            if (m.index == ord) {
                return m;
            }
        }
        return null;
    }
	
	public String toString(){
		return this.fileName;
	}
}
