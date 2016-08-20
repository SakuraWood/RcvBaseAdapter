package com.sakurawood.activity;

/**
 * Created by leesure on 16-6-1.
 */
public class Bean {
    private String name;
    private String value;
    private int pic;

    public Bean(String name, String value, int pic) {
        this.name = name;
        this.value = value;
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }
}
