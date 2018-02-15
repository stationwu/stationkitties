package com.cat.domain;

public enum EyeType implements KittyProperty {
	WINGTIPS(1, "wingtips"), FABULOUS(2, "fabulous"), RAISEDBROW(4, "raisedbrow"), SIMPLE(5,
			"simple"), CRAZY(6, "crazy"), THICCCBROWZ(3, "thicccbrowz"), GOOGLY(0, "googly");

	private int index;

	private String fileName;

	final private String folder = "\\eye\\";

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
