package model;

/**
 * @author Francois and Julien
 *
 */
public final class Chrono {
	private long begin, end;
	 
    /**
     * Method start : start the clock 
     * 
     */
    public void start(){
        begin = System.currentTimeMillis();
    }
 
    /**
     * Method stop : stop the clock
     */
    public void stop(){
        end = System.currentTimeMillis();
    }
 
    /**
     * Method getTime
     * @return the timeClock
     */
    public long getTime() {
        return end-begin;
    }
 
    /**
     * Method getMilliseconds
     * @return milliseconds
     */
    public long getMilliseconds() {
        return end-begin;
    }
 
    /**
     * Method getSeconds
     * @return seconds
     */
    public double getSeconds() {
        return (end - begin) / 1000.0;
    }
 
    /**
     * Method getMinutes
     * @return minutes
     */
    public double getMinutes() {
        return (end - begin) / 60000.0;
    }
 
    /**
     * Method getHours
     * @return hours
     */
    public double getHours() {
        return (end - begin) / 3600000.0;
    }
}
