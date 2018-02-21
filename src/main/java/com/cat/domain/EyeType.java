package com.cat.domain;

public enum EyeType implements KittyProperty {
	GOOGLY(0, "googly"),
	WINGTIPS(1, "wingtips"), 
	FABULOUS(2, "fabulous"), 
	//OTAKU(3,"otaku"),
	RAISEDBROW(3, "raisedbrow"), 
	SIMPLE(4, "simple"), 
	CRAZY(5, "crazy"), 
	THICCCBROWZ(6, "thicccbrowz");

	private int index;

	private String fileName;

	final private String folder = "/eye/";

	private EyeType(int index, String fileName) {
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
	
	public static EyeType byOrdinal(int ord) {
        for (EyeType m : EyeType.values()) {
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
