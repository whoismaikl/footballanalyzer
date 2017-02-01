package Services;

public class TimeUtils {

    public static final int MAIN_GAME_TIME = 3600;

    private int convertTimeToSeconds(String time) {
        int minutes = Integer.parseInt(time.substring(0, 2));
        int seconds = Integer.parseInt(time.substring(time.length() - 2));
        int timeInSeconds = minutes * 60 + seconds;
        return timeInSeconds;
    }

    public boolean isAdditionalTime(String time) {

        if (convertTimeToSeconds(time) > MAIN_GAME_TIME)
            return true;
        return false;
    }

    public int returnTimeInSeconds(String time){
     return convertTimeToSeconds(time);
    }
}
