package com.cat.domain;

public enum BodyType implements KittyProperty {
	CHARTREUX(0, "chartreux"),
	MAINECOON(1, "mainecoon"), 
	//CYMRIC(2, "cymric"), 
	LAPERM(2, "laperm"), 
	MUNCHKIN(3, "munchkin"), 
	SPHYNX(4, "sphynx"), 
	RAGAMUFFIN(5, "ragamuffin"), 
	HIMALAYAN(6, "himalayan");

	private int index;

	private String fileName;

	final private String folder = "\\body\\";

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
