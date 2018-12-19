package ru.zoom4ikdan4ik.core.utils;

public enum TimeFormat {
    SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY, ALL, UNNAMED;

    public static int getTimeFormat(TimeFormat format, int time) {
        switch (format) {
            case SECONDS:
                return time;
            case MINUTES:
                return time / 60;
            case HOURS:
                return time / 3600;
            case DAYS:
                return time / 86400;
            case WEEKS:
                return time / 604800;
            case MONTHS:
                return time / 2419200;
            default:
                return time;
        }
    }

    public static TimeFormat getFormat(int time) {
        if (time > 60)
            if (time > 3600)
                if (time > 86400)
                    if (time > 604800)
                        if (time > 2419200)
                            return MONTHS;
                        else
                            return WEEKS;
                    else
                        return DAYS;
                else
                    return HOURS;
            else
                return MINUTES;
        else
            return SECONDS;
    }

    public static int getTimeOfChar(int time, String format) {
        if (format.startsWith("s"))
            return time;
        if (format.startsWith("m"))
            return time * 60;
        if (format.startsWith("h"))
            return time * 3600;
        if (format.startsWith("d"))
            return time * 86400;
        if (format.startsWith("w"))
            return time * 604800;
        if (format.startsWith("mo"))
            return time * 181444000;

        return time;
    }

    public static TimeFormat getDaysByName(String str) {
        switch (str) {
            case "monday":
                return MONDAY;
            case "tuesday":
                return TUESDAY;
            case "wednesday":
                return WEDNESDAY;
            case "thursday":
                return THURSDAY;
            case "friday":
                return FRIDAY;
            case "saturday":
                return SATURDAY;
            case "sunday":
                return SUNDAY;
            default:
                return ALL;
        }
    }

    public static TimeFormat getTimeByName(String str) {
        switch (str) {
            case "seconds":
                return SECONDS;
            case "minutes":
                return MINUTES;
            case "hours":
                return HOURS;
            case "days":
                return DAYS;
            case "months":
                return MONTHS;
            default:
                return UNNAMED;
        }
    }

    public static TimeFormat getByInt(int calval) {
        switch (calval) {
            case 1:
                return SUNDAY;
            case 2:
                return MONDAY;
            case 3:
                return TUESDAY;
            case 4:
                return WEDNESDAY;
            case 5:
                return THURSDAY;
            case 6:
                return FRIDAY;
            case 7:
                return SATURDAY;
            default:
                return ALL;
        }
    }
}
