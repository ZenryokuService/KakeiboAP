package zenryokuservice.gui.lwjgl.kakeibo.util;

import java.util.Calendar;

/**
 * 家計簿の各数値を表示するための領域(日)を作成する
 * @author takunoji
 *
 * 2018/11/10
 */
public class CalendarPosit {
	/** 日本語の曜日 */
	private static final String[] ja_week = new String[] {"日", "月", "火", "水", "木", "金", "土"};
	/** 英語の曜日 */
	private static final String[] en_week = new String[] {"Sun", "Mon", "Tue", "Wed", "thu", "Fri", "Sat"};

	private Calendar cal = null;
	
	public CalendarPosit() {
		cal = Calendar.getInstance();
	}
	
	/**
	 * 今月の最大日数を返す
	 * @return 最大日数
	 */
	public int getMaxDayOfMonth() {
		return cal.getMaximum(Calendar.DATE);
	}

	/**
	 * 今月の最大日数を返す
	 * @move 今月の月からずらす月数
	 * move=1  -> 1ヶ月たす
	 * mobe=-1 -> 1ヶ月引く
	 * 
	 * <Ex>
	 * 現在：10月
	 * move=  1  -> 11月の最大日数
	 * move= -1 -> 9月の最大日数
	 * @return 最大日数
	 */
	public int getMaxDayOfMonth(int move) {
		Calendar tmpCal = cal;
		tmpCal.add(Calendar.MONTH, move);
		return tmpCal.getMaximum(Calendar.DATE);
	}

	/**
	 * 対象月の月初の曜日を取得する
	 * @return Calendar.MONDAY〜SUNDAY
	 */
	public int getStartPoint(boolean isStartMon) {
		int day_week = 0;
		cal.set(Calendar.DATE, 1);
		day_week = cal.get(Calendar.DAY_OF_WEEK);
		if (isStartMon) {
			day_week = day_week == 1 ? 7 : day_week - 1;
		}
		return day_week;
	}	

	/**
	 * 現在の年の指定した月初の曜日を返却する
	 * @param month 1-12を指定する
	 * @return 対象月の、月初曜日
	 */
	public int getStartPoint(int month, boolean isStartMon) {
		int day_week = 0;
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DATE, 1);
		
		day_week = cal.get(Calendar.DAY_OF_WEEK);
		return day_week;
	}

	/**
	 * カレンダークラスを返却する。
	 * @return
	 */
	public Calendar getCalendar() {
		return this.cal;
	}
	/**
	 * 開始日付に対応する曜日の値を返す。
	 * @return 曜日の文字列
	 */
	public String getDayOfWeek(boolean isStartMon) {
		return ja_week[getStartPoint(isStartMon)];
	}
	/**
	 * 引数の値が何曜日か判定して返却する
	 * @param value Calendarクラスより取得した、DAY_OF_WEEK
	 * @return 対象の曜日の値
	 */
	public int chkWeek(int value, boolean isStartMon) {
		int day_of_week = 0;
		
		switch(value) {
		case Calendar.MONDAY:
			day_of_week = Calendar.MONDAY;
			break;
		case Calendar.TUESDAY:
			day_of_week = Calendar.TUESDAY;
			break;
		case Calendar.WEDNESDAY:
			day_of_week = Calendar.TUESDAY;
			break;
		case Calendar.THURSDAY:
			day_of_week = Calendar.TUESDAY;
			break;
		case Calendar.FRIDAY:
			day_of_week = Calendar.TUESDAY;
			break;
		case Calendar.SATURDAY:
			day_of_week = Calendar.TUESDAY;
			break;
		case Calendar.SUNDAY:
			day_of_week = Calendar.TUESDAY;
			break;
		default:
			// TODO-[例外処理の実装]
			System.out.println("想定外の曜日です[day_of_week]: " + day_of_week);
			System.exit(-1);
		}

		// 日曜始まり=true, 月曜始まり=false
		if (isStartMon == false) {
			day_of_week = day_of_week < 7 ? day_of_week + 1 : 1;
		}
		return day_of_week;
	}
}
