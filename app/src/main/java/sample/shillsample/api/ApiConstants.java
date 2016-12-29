package sample.shillsample.api;

/**
 * Created by we on 2016/12/9.
 */

public class ApiConstants {


    /**
     * 新浪图片新闻
     * http://gank.io/api/data/福利/{size}/{page}
     */
    public static final String SINA_PHOTO_HOST = "http://gank.io/api/";

    /**
     * 获取对应的host
     *
     * @param hostType host类型
     * @return host
     */
    public static String getHost(int hostType) {
        String host;
        switch (hostType) {

            case HostType.GANK_GIRL_PHOTO:
                host = SINA_PHOTO_HOST;
                break;

            default:
                host = "";
                break;
        }
        return host;
    }
}
