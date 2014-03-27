package com.window.demo;

import java.lang.reflect.Method;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;

public class UIUtils {

	public static void fitWidth(Activity activity, Bitmap bitmap,
			ImageView imageView) {
		if (bitmap == null || imageView == null || activity == null) {
			return;
		}
		int orgWid = bitmap.getWidth();
		int orgHeight = bitmap.getHeight();

		int screenWidth = activity.getWindowManager().getDefaultDisplay()
				.getWidth(); // 屏幕宽（像素，如：480px）
		// int screenHeight =
		// getWindowManager().getDefaultDisplay().getHeight(); // 屏幕高（像素，如：800p）

		int newWid = screenWidth;
		int newHeight = (int) ((double) newWid / (double) orgWid
				* (double) orgHeight + 0.5f);

		LayoutParams params = (LayoutParams) imageView.getLayoutParams();
		params.width = newWid;
		params.height = newHeight;
		imageView.setLayoutParams(params);
		imageView.setImageBitmap(bitmap);
	}

	/**
	 * 获取标题栏的高度
	 * 
	 * @param activity
	 * @return
	 */
	public static int getTitleHeight(Activity activity) {
		Rect rect = new Rect();
		Window window = activity.getWindow();
		window.getDecorView().getWindowVisibleDisplayFrame(rect);
		int statusBarHeight = rect.top;
		int contentViewTop = window.findViewById(Window.ID_ANDROID_CONTENT)
				.getTop();
		int titleBarHeight = contentViewTop - statusBarHeight;

		return titleBarHeight;
	}

	/**
	 * 
	 * 获取状态栏高度
	 * 
	 * @param activity
	 * @return
	 */
	public static int getStateHeight(Activity activity) {
		Rect rect = new Rect();
		activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
		return rect.top;
	}

	/**
	 * 获取屏幕宽高
	 * 
	 * @param activity
	 * @return int[0] 宽，int[1]高
	 */
	public static int[] getScreenWidthAndSizeInPx(Activity activity) {
		DisplayMetrics displayMetrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay()
				.getMetrics(displayMetrics);
		int[] size = new int[2];
		size[0] = displayMetrics.widthPixels;
		size[1] = displayMetrics.heightPixels;
		return size;
	}

	/**
	 * 获取导航栏高度
	 * 
	 * @param
	 * @return
	 * @date 2014年3月21日
	 * @author trs
	 */
	/**
	 * 获取屏幕宽高
	 * 
	 * @param activity
	 * @return int[0] 宽，int[1]高
	 */
	public static int getNavigationBarHeight(Activity activity) {

		int res = getWindowsHeight(activity)
				- getScreenWidthAndSizeInPx(activity)[1]
				- getStateHeight(activity) - getTitleHeight(activity);

		return res;
	}

	/**
	 * 获取屏幕区域大小
	 * 
	 * @param
	 * @return
	 * @date 2014年3月21日
	 * @author trs
	 */
	public static int getWindowsHeight(Activity activity) {

		String dpi = null;
		Display display = activity.getWindowManager().getDefaultDisplay();
		DisplayMetrics dm = new DisplayMetrics();
		@SuppressWarnings("rawtypes")
		Class c;
		try {
			c = Class.forName("android.view.Display");
			@SuppressWarnings("unchecked")
			Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
			method.invoke(display, dm);
			dpi = dm.widthPixels + "*" + dm.heightPixels;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return dm.heightPixels;
	}
}
