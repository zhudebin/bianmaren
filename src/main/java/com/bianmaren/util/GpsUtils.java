package com.bianmaren.util;
/**
 * 用于计算gps坐标及排序等
 * @author hzy
 *
 */
public final class GpsUtils {
	
	/**
	 * 获取到以经纬度为中心，以r为半径的正方形的左上角和右下角点的经纬度范围坐标
	 * @param r 半径
	 * @param lat 纬度
	 * @param lng 经度
	 * @return
	 */
	public static GpsUtils getGpsRect(double r,double lat,double lng){
		return null;
	}
	
	/**
	 * 获取XY两点之间的直线距离，精确到米
	 * @param lat_a 纬度
	 * @param lng_a 经度
	 * @param lat_b
	 * @param lng_b
	 * @return
	 */
	public static double getDistanceFromXtoY(double lat_a, double lng_a, double lat_b, double lng_b)
	 {
		  double pk = (double) (180 / 3.14169);

		  double a1 = lat_a / pk;
		  double a2 = lng_a / pk;
		  double b1 = lat_b / pk;
		  double b2 = lng_b / pk;

		  double t1 = Math.cos(a1) * Math.cos(a2) * Math.cos(b1) * Math.cos(b2);
		  double t2 = Math.cos(a1) * Math.sin(a2) * Math.cos(b1) * Math.sin(b2);
		  double t3 = Math.sin(a1) * Math.sin(b1);
		  double tt = Math.acos(t1 + t2 + t3);

		  return 6366000 * tt;
	 }
}
