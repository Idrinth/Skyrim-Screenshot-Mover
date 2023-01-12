package de.idrinth.skyrimscreenshotmover;

public class Config {
    private boolean convert = true;
    private String legacy = System.getProperty("user.home") + "/Pictures/Skyrim Legacy Edition";
    private String special = System.getProperty("user.home") + "/Pictures/Skyrim Special Edition";
    private String vr = System.getProperty("user.home") + "/Pictures/Skyrim VR Edition";

    public void setConvert(boolean convert) {
        this.convert = convert;
    }

    public String getLegacy() {
        return legacy;
    }

    public void setLegacy(String legacy) {
        this.legacy = legacy;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public boolean mayConvert() {
        return convert;
    }

    public String toString() {
        return "convert: " + convert + "\n"
            + "legacy: " + legacy + "\n"
            + "special: " + special + "\n"
            + "vr: " + vr + "\n";
    }

    public String getVr() {
        return vr;
    }

    public void setVr(String vr) {
        this.vr = vr;
    }
}
