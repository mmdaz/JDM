import java.io.Serializable;

public class SettingInformation implements Serializable {
    public  int sameTimeDownloads ;
    public  String savePath ;
    public  String[] filterSites ;
    public int lookAndFeelOption ;

    public SettingInformation (String savePath , int sameTimeDownloads ) {
        this.sameTimeDownloads = sameTimeDownloads ;
        this.savePath = savePath ;
    }

    public SettingInformation () {

    }


    public  int getSameTimeDownloads() {
        return sameTimeDownloads;
    }

    public  String getSavePath() {
        return savePath;
    }

    public  void setSameTimeDownloads(int sameTimeDownloads) {
        this.sameTimeDownloads = sameTimeDownloads;
    }

    public  void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public  String[] getFilterSites() {
        return filterSites;
    }

    public  void setFilterSites(String[] filterSites) {
        this.filterSites = filterSites;
    }

    public int getLookAndFeelOption() {
        return lookAndFeelOption;
    }

    public void setLookAndFeelOption(int lookAndFeelOption) {
        this.lookAndFeelOption = lookAndFeelOption;
    }
}
