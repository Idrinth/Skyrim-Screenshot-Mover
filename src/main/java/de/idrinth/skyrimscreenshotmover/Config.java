package de.idrinth.skyrimscreenshotmover;

public class Config {
    private boolean convert = true;
    private String legacy = System.getProperty("user.home") + "/Pictures/Skyrim Legacy Edition";
    private String special = System.getProperty("user.home") + "/Pictures/Skyrim Special Edition";
    private String vr = System.getProperty("user.home") + "/Pictures/Skyrim VR Edition";
    private boolean steam = true;
    public final long legacyId = 72850;
    public final long specialId = 489830;
    public final long vrId = 611670;
    public final String steam32 = "HKLM\\SOFTWARE\\Valve\\Steam";
    public final String steam64 = "HKLM\\SOFTWARE\\WOW6432Node\\Valve\\Steam";
    public final String legacyGame = "HKLM\\SOFTWARE\\WOW6432Node\\Bethesda Softworks\\skyrim";
    public final String specialGame = "HKLM\\SOFTWARE\\WOW6432Node\\Bethesda Softworks\\Skyrim Special Edition";
    public final String vrGame = "HKLM\\SOFTWARE\\WOW6432Node\\Bethesda Softworks\\Skyrim VR";

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

    public void setSteam(boolean steam) {
        this.steam = steam;
    }

    public boolean maySteam() {
        return steam;
    }

    public String getVr() {
        return vr;
    }

    public void setVr(String vr) {
        this.vr = vr;
    }

    public String toString() {
        return "convert: " + convert + "\n"
            + "steam: " + steam + "\n"
            + "legacy: " + legacy + "\n"
            + "special: " + special + "\n"
            + "vr: " + vr + "\n";
    }
}
