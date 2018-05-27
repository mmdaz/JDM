import java.io.Serializable;
import java.util.Set;

public class SettingInformation implements Serializable {
    public static int sameTimeDownloads ;
    public static String savePath ;
    public static String[] filterSites ;

    public SettingInformation (String savePath , int sameTimeDownloads ) {
        this.sameTimeDownloads = sameTimeDownloads ;
        this.savePath = savePath ;
    }

    public SettingInformation () {

    }


    public static int getSameTimeDownloads() {
        return sameTimeDownloads;
    }

    public static String getSavePath() {
        return savePath;
    }

    public static void setSameTimeDownloads(int sameTimeDownloads) {
        SettingInformation.sameTimeDownloads = sameTimeDownloads;
    }

    public static void setSavePath(String savePath) {
        SettingInformation.savePath = savePath;
    }

    public static String[] getFilterSites() {
        return filterSites;
    }

    public static void setFilterSites(String[] filterSites) {
        SettingInformation.filterSites = filterSites;
    }
}
