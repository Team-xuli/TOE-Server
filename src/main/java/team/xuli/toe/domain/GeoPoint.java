package team.xuli.toe.domain;

import team.xuli.toe.util.AppConst;

/**
 * @author: 徐清锋
 * 创建时间：2016/6/4
 * 创建原因：
 */
public class GeoPoint {
    private double longitude = 0.0;
    private double latitude = 0.0;

    public boolean isNearby(GeoPoint anotherPoint){
        return distance(this.longitude,
                        this.latitude,
                        anotherPoint.longitude,
                        anotherPoint.latitude) <= AppConst.GEO_POINT_NEARBY_LIMIT;
    }

    public boolean isNearby(double longitude,double latitude){
        GeoPoint point = new GeoPoint();
        point.setLatitude(latitude);
        point.setLongitude(longitude);
        return isNearby(point);
    }

    public static double distance(double long1, double lat1, double long2,
                                  double lat2) {
        double a, b, R;
        R = 6378137; // 地球半径
        lat1 = lat1 * Math.PI / 180.0;
        lat2 = lat2 * Math.PI / 180.0;
        a = lat1 - lat2;
        b = (long1 - long2) * Math.PI / 180.0;
        double d;
        double sa2, sb2;
        sa2 = Math.sin(a / 2.0);
        sb2 = Math.sin(b / 2.0);
        d = 2
                * R
                * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1)
                * Math.cos(lat2) * sb2 * sb2));
        return d;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
