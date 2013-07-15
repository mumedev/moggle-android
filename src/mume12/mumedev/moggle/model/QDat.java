package mume12.mumedev.moggle.model;

import java.util.Calendar;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * <p>The <code>QDat</code> class contains all data
 * for one Moggle entry by the user.</p>
 * 
 * @author 	Joris Schelfaut
 * @since	2012-12-08
 */
public class QDat implements Parcelable {
	
	private long 	_id 	=		-1;
	private int		year;
	private int		week;
	private int		day_of_week;
	private int		work_quantity;
	private int		work_quality;
	private int		mood_quantified;
	
	public static final int WORK_QUALITY_GOOD	= 2;
	public static final int WORK_QUALITY_AVG	= 1;
	public static final int WORK_QUALITY_POOR	= 0;
	
	/**
	 * <p>Instantiates a new <code>QDat</code> object.</p>
	 */
	public QDat() {
		super();
	}
	
	/**
	 * <p>Instantiates a new <code>QDat</code> object
	 * using the data from a <code>Parcel</code> object.</p>
	 * @param	parcel the parcel containing QDat data
	 */
	public QDat(Parcel parcel) {
		this();
		this.setID(parcel.readLong());
		this.setYear(parcel.readInt());
		this.setWeek(parcel.readInt());
		this.setDayOfWeek(parcel.readInt());
		this.setWorkQuality(parcel.readInt());
		this.setWorkQuantity(parcel.readInt());
		this.setMoodQuantified(parcel.readInt());
	}
	
	/**
	 * <code>CREATOR</code> for the Parcelable implementation.
	 */
	public static final Parcelable.Creator<QDat> CREATOR = new Parcelable.Creator<QDat>() {

		@Override
		public QDat createFromParcel(Parcel source) {
			return new QDat(source);
		}

		@Override
		public QDat[] newArray(int size) {
			return new QDat[size];
		}
		
	};
	
	/**
	 * @return	the id
	 */
	public long getID() {
		return _id;
	}
	
	/**
	 * @param _id	the id
	 */
	public void setID(long _id) {
		this._id = _id;
	}
	
	/**
	 * @return	the year
	 */
	public int getYear() {
		return year;
	}
	
	/**
	 * @return	the week
	 */
	public int getWeek() {
		return week;
	}
	
	/**
	 * @return	the dayo of the week
	 */
	public int getDayOfWeek() {
		return day_of_week;
	}	
	
	/**
	 * @return	the quantity of the work, a number between 0 and 24
	 */
	public int getWorkQuantity() {
		return work_quantity;
	}
	
	/**
	 * @return	the work quality, a number between 0 and 2
	 */
	public int getWorkQuality() {
		return work_quality;
	}
	
	/**
	 * @return	the quantified mood, a number between 1 and 10
	 */
	public int getMoodQuantified() {
		return mood_quantified;
	}
	
	/**
	 * @param year	the year
	 */
	public void setYear(int year) {
		this.year = year;
	}
	
	/**
	 * @param week	the week
	 */
	public void setWeek(int week) {
		assert (week >= Calendar.getInstance().getMinimum(Calendar.WEEK_OF_YEAR)
				&& week <= Calendar.getInstance().getMaximum(Calendar.WEEK_OF_YEAR));
		this.week = week;
	}
	
	/**
	 * @param day_of_week	the day of the week
	 */
	public void setDayOfWeek(int day_of_week) {
		assert (week >= Calendar.getInstance().getMinimum(Calendar.DAY_OF_WEEK)
				&& week <= Calendar.getInstance().getMaximum(Calendar.DAY_OF_WEEK));
		this.day_of_week = day_of_week;
	}
	
	/**
	 * @param calendar a calendar object, sets the year, week and day of week fields
	 */
	public void setDate(Calendar calendar) {
		assert (calendar != null);
		this.setYear(calendar.get(Calendar.YEAR));
		this.setWeek(calendar.get(Calendar.WEEK_OF_YEAR));
		this.setDayOfWeek(calendar.get(Calendar.DAY_OF_WEEK));
	}
	
	/**
	 * @param work_quality	the work quality, a number between 0 and 2
	 */
	public void setWorkQuality(int work_quality) {
		assert (this.work_quality >= 0 && work_quality < 3);
		this.work_quality = work_quality;
	}
	
	/**
	 * @param work_quantity	the quantity of the work, a number between 0 and 24
	 */
	public void setWorkQuantity(int work_quantity) {
		assert (work_quantity >= 0 && work_quantity <= 24);
		this.work_quantity = work_quantity;
	}
	
	/**
	 * @param mood_quantified the quantified mood, a number between 1 and 10
	 */
	public void setMoodQuantified(int mood_quantified) {
		assert (mood_quantified > 0 && mood_quantified <= 10);
		this.mood_quantified = mood_quantified;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(this.getID());
		dest.writeInt(this.getYear());
		dest.writeInt(this.getWeek());
		dest.writeInt(this.getDayOfWeek());
		dest.writeInt(this.getWorkQuality());
		dest.writeInt(this.getWorkQuantity());
		dest.writeInt(this.getMoodQuantified());
	}
}
