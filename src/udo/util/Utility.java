package udo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import udo.storage.Task;

//@author A0093587M
public class Utility {
	private static final SimpleDateFormat fmt =
	        new SimpleDateFormat("dd/MM/yyyy HH:mm");
	private static final String timeFormat = "%sh%sm";

	public static final long MILISECONDS_IN_MINUTE = 60 * 1000;

	public static HashMap<Integer, Integer> indexMap = new HashMap<>();

	/**
	 * Convert a name of a command in string form to corresponding enum form
	 * @param cmdName
	 * @return corresponding CommandName enum for cmdName
	 */
    public static Config.CommandName convertToCommandName(String cmdName) {
        cmdName = cmdName.toLowerCase();

        if (cmdName.equals(Config.CMD_STR_ADD)) {
            return Config.CommandName.ADD;
        } else if (cmdName.equals(Config.CMD_STR_DELETE) ||
                   cmdName.equals(Config.CMD_STR_DELETE_SHORT)) {
            return Config.CommandName.DELETE;
        } else if (cmdName.equals(Config.CMD_STR_MODIFY)) {
            return Config.CommandName.MODIFY;
        } else if (cmdName.equals(Config.CMD_STR_DISPLAY)) {
            return Config.CommandName.DISPLAY;
        } else if (cmdName.equals(Config.CMD_STR_DONE)){
            return Config.CommandName.DONE;
        } else if (cmdName.equals(Config.CMD_STR_CHDIR)) {
            return Config.CommandName.CHDIR;
        } else if (cmdName.equals(Config.CMD_STR_SEARCH)) {
            return Config.CommandName.SEARCH;
        } else if (cmdName.equals(Config.CMD_STR_UNDO)) {
            return Config.CommandName.UNDO;
        } else if (cmdName.equals(Config.CMD_STR_CONFIRM)) {
            return Config.CommandName.CONFIRM;
        } else {
            return null;
        }
    }

    /**
     * Get the lower-level task's index in storage from the task's index
     * as displayed by gui
     * @param argIndex the displayed index
     * @return the storage index or null if it's not found
     */
    public static Integer getStorageIndex(Integer displayIndex) {
        assert(displayIndex != null);
        return indexMap.get(displayIndex);
    }

    public static void setToStartOfDay(Calendar start) {
        start.set(GregorianCalendar.HOUR_OF_DAY, 0);
        start.set(GregorianCalendar.MINUTE, 0);
    }

    /**
     * Set a Gregorian calendar to end of the day i.e 23:59pm
     * @param end
     */
    public static void setToEndOfDay(Calendar end) {
        end.set(GregorianCalendar.HOUR_OF_DAY, 23);
        end.set(GregorianCalendar.MINUTE, 59);
    }

    public static String minutesToString(Integer minutes) {
        assert(minutes != null);

        int hours = minutes / 60;
        minutes = minutes % 60;

        return String.format(timeFormat, hours, minutes);
    }

    public static long findDiffMinutes(Calendar cal1, Calendar cal2) {
        Date d1 = cal1.getTime();
        Date d2 = cal2.getTime();

        long diff = Math.abs(d1.getTime() - d2.getTime());

        return diff / MILISECONDS_IN_MINUTE;
    }

    /**
     * Convert a Gregorian calendar object to string representation
     * @param calendar
     * @return the string representation or empty string if calendar is null
     */
  //@author A0112115A
	public static String calendarToString(Calendar calendar){
	    if (calendar == null) {
	        return "";
	    }
		return fmt.format(calendar.getTime());
	}

    /**
     * Convert a Gregorian calendar object to string representation
     * with a given format
     * @param calendar
     * @return the string representation or empty string if calendar is null
     */
	//@author A0112115A
	public static String calendarToString(Calendar calendar,
	                                      SimpleDateFormat format){
	    if (calendar == null) {
	        return "";
	    }
		return format.format(calendar.getTime());
	}

	/**
	 * Convert a formatted date string into a calendar object
	 * @param dateStr the formatted string representation of a date
	 * @return a new calendar or null if dateStr is null or
	 *         the dateStr's format is invalid
	 */
	//@author A0112115A
	public static GregorianCalendar stringToCalendar(String dateStr) {
	    try {
	        if (dateStr == null) {
	            return null;
	        }
	        GregorianCalendar calendar = new GregorianCalendar();
	        calendar.setTime(fmt.parse(dateStr));
            return calendar;
        } catch (ParseException e) {
            System.err.println("Invalid date format");
            return null;
        }
	}

    //@author A0093587M
	public static ArrayList<Task> deepCopy(List<Task> firstCopy) {
	    ArrayList<Task> copy = new ArrayList<>();
	    for(Task element : firstCopy) {
	        copy.add(element.copy());
	    }
	    return copy;
	}

	//@author A0114906J
	public static boolean isSameDate(GregorianCalendar cal1,
	                                 GregorianCalendar cal2) {
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
               cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }
}
