package com.cat.domain;

public enum BodyType implements KittyProperty {
	MAINECOON(1, "mainecoon"), CYMRIC(2, "cymric"), LAPERM(3, "laperm"), MUNCHKIN(4, "munchkin"), SPHYNX(5,
			"sphynx"), RAGAMUFFIN(6, "ragamuffin"), HIMALAYAN(7, "himalayan"), CHARTREUX(0, "chartreux");

	private int index;

	private String fileName;

	final private String folder = "/body/";

	private BodyType(int index, String fileName) {
		this.index = index;
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public String getPath() {
		return this.folder + this.fileName;
	}

	public int getIndex() {
		return index;
	}

	public String getFloder() {
		return folder;
	}
	
	public static BodyType byOrdinal(int ord) {
        for (BodyType m : BodyType.values()) {
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
