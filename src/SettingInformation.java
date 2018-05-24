import java.io.Serializable;

public class SettingInformation implements Serializable {
    private int sameTimeDownloads ;
    private String savePath ;

    public SettingInformation (String savePath , int sameTimeDownloads ) {
        this.sameTimeDownloads = sameTimeDownloads ;
        this.savePath = savePath ;
    }

    public SettingInformation () {

    }


    public int getSameTimeDownloads() {
        return sameTimeDownloads;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSameTimeDownloads(int sameTimeDownloads) {
        this.sameTimeDownloads = sameTimeDownloads;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }
}
