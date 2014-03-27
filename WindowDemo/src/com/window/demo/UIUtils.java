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
				.getWidth(); // ��Ļ�����أ��磺480px��
		// int screenHeight =
		// getWindowManager().getDefaultDisplay().getHeight(); // ��Ļ�ߣ����أ��磺800p��

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
	 * ��ȡ�������ĸ߶�
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
	 * ��ȡ״̬���߶�
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
	 * ��ȡ��Ļ���
	 * 
	 * @param activity
	 * @return int[0] ��int[1]��
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
	 * ��ȡ�������߶�
	 * 
	 * @param
	 * @return
	 * @date 2014��3��21��
	 * @author trs
	 */
	/**
	 * ��ȡ��Ļ���
	 * 
	 * @param activity
	 * @return int[0] ��int[1]��
	 */
	public static int getNavigationBarHeight(Activity activity) {

		int res = getWindowsHeight(activity)
				- getScreenWidthAndSizeInPx(activity)[1]
				- getStateHeight(activity) - getTitleHeight(activity);

		return res;
	}

	/**
	 * ��ȡ��Ļ�����С
	 * 
	 * @param
	 * @return
	 * @date 2014��3��21��
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
