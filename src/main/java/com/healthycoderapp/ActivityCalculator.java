package main.java.com.healthycoderapp;

/*
    Rate user's activity level (bad-average-good). Input:
    (1) the weekly time [min] spent doing cardio
    (2) the number of workout sessions (1 workout = 45 minutes).
    Throw exception when any input below 0

    Calculate the weekly total, divide by 7 to find the daily average.
    If < 20, return "bad".
    If >= 20 and < 40, return "average".
    If >= 40, return "good".
*/
public class ActivityCalculator {

    public static int WORKOUT_DURATION_MIN = 45;

    public static String rateActivityLevel(int weeklyCardioMin, int weeklyCardioSessions) {
        if (weeklyCardioMin < 0 || weeklyCardioSessions < 0) throw new RuntimeException("Input below 0");
        int totalMinutes = weeklyCardioMin + weeklyCardioSessions * WORKOUT_DURATION_MIN;
        double avgDailyActivityMins = totalMinutes / 7.0;
        if (avgDailyActivityMins < 20)
            return "bad";
        else if (avgDailyActivityMins < 40)
            return "average";
        else
            return "good";
    }
}
