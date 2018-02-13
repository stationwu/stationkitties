package com.cat.domain;

public enum PrimaryColor{
	MAUVEOVER(1,"#ded0ee"),
	CLOUDWHITE(2,"#ffffff"),
	SALMON(3,"#f4a792"),
	SHADOWGREY(4,"#b1b1be"),
	ORANGESODA(5,"#f7bc56"),
	AQUAMARINE(6,"#add5d2"),
	GREYMATTER(7,"#d1dadf"),
	OLDLACE(8,"#ffebe9"),
	COTTONCANDY(0,"#ecd1eb");

	private int index;

	private String color;
	
	private PrimaryColor(int index, String color) {
		this.index = index;
		this.color = color;
	}

	public int getIndex() {
		return index;
	}

	public String getColor() {
		return color;
	}
	
	public static PrimaryColor byOrdinal(int ord) {
        for (PrimaryColor m : PrimaryColor.values()) {
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
