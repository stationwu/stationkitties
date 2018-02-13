package com.cat.domain;

public enum SecondaryColor {
	PEACH(1,"#f9cfad"),
	BLOODRED(2,"#ff7a7a"),
	EMERALDGREEN(3,"#8be179"),
	GRANITEGREY(4,"#b1aeb9"),
	KITTENCREAM(0,"#f7ebda");

	private int index;

	private String color;

	private SecondaryColor(int index, String color) {
		this.index = index;
		this.color = color;
	}

	public int getIndex() {
		return index;
	}

	public String getColor() {
		return color;
	}
	
	public static SecondaryColor byOrdinal(int ord) {
        for (SecondaryColor m : SecondaryColor.values()) {
            if (m.index == ord) {
                return m;
            }
        }
        return null;
    }
	
	public String toString(){
		return this.color;
	}
	
}
