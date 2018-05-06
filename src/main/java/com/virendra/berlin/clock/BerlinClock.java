package com.virendra.berlin.clock;

import com.virendra.berlin.clock.exception.BerlinClockException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.virendra.berlin.clock.validation.NumberValidator.numberRangeCheck;
import static com.virendra.berlin.clock.validation.NumberValidator.validate;

/**
 * Created by Virendra Singh on 06/05/18.
 */
public class BerlinClock implements TimeConverter {

    private static final Logger LOG = LoggerFactory.getLogger(BerlinClock.class);

    private static final int FOUR = 4;
    private static final int ELEVEN = 11;

    private static final int FIVE = 5;

    @Override
    public String convertTime(String aTime) {
        LOG.info("About to convert time for input text: {}", aTime);

        if (null == aTime) {
            LOG.error("Can not convert input time as input is null");
            throw new BerlinClockException("Can not convert input time as input is null");
        }

        String[] time = aTime.split(":");
        StringBuilder convertedTime = new StringBuilder(isTopLampOn(time[2]));
        convertedTime.append("\n")
                .append(convertHour(time[0])).append("\n")
                .append(convertMinutes(time[1]));
        return convertedTime.toString();
    }

    private String convertHour(String hours) {
        inputValidator(hours);
        int hour = Integer.parseInt(hours);
        StringBuilder sb = new StringBuilder();

        if (hour == 0) {
            sb.append(constructOnOffLampHour(0, FOUR))
                    .append("\n").append(constructOnOffLampHour(0, FOUR));
        } else if (hour >= FIVE) {
            int hourFirstRow = hour / FIVE;
            sb.append(constructOnOffLampHour(hourFirstRow, FOUR));
            sb.append("\n");

            int reminder = hour % FIVE;
            sb.append(constructOnOffLampHour(reminder, FOUR));
        } else {
            sb.append(constructOnOffLampHour(0, FOUR)).append("\n");
            sb.append(constructOnOffLampHour(hour, FOUR));
        }
        return sb.toString();
    }

    private String convertMinutes(String minutes) {
        inputValidator(minutes);
        int minute = Integer.parseInt(minutes);

        StringBuilder sb = new StringBuilder();
        if (minute == 0) {
            return sb.append(constructOnOffLampForMinute(0, ELEVEN))
                    .append("\n")
                    .append(constructOnOffLampForMinute(0, FOUR))
                    .toString();
        } else if (minute >= FIVE) {
            int minuteInFirstRow = minute / FIVE;
            sb.append(constructOnOffLampForMinute(minuteInFirstRow, ELEVEN));
            sb.append("\n");

            int reminder = minute % FIVE;
            sb.append(constructOnOffLampForMinute(reminder, FOUR));
        } else {
            sb.append(constructOnOffLampForMinute(0, ELEVEN)).append("\n");
            sb.append(constructOnOffLampForMinute(minute, FOUR));
        }
        return sb.toString();
    }

    private String constructOnOffLampHour(int times, int size) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= times; i++) {
            sb.append("R");
        }
        if (times < size) {
            for (int i = times; i < size; i++) {
                sb.append("O");
            }
        }
        return sb.toString();
    }

    private String constructOnOffLampForMinute(int times, int size) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= times; i++) {
            if (size == 11 && (i == 3 || i == 6 || i == 9)) {
                sb.append("R");
            } else
                sb.append("Y");
        }
        if (times < size) {
            for (int i = times; i < size; i++) {
                sb.append("O");
            }
        }
        return sb.toString();
    }

    private String isTopLampOn(String str) {
        inputValidator(str);

        int num = Integer.parseInt(str);
        if (num == 0 || num % 2 == 0) {
            return "Y";
        }
        return "O";
    }

    private void inputValidator(String str) {
        if (!validate(str) || !numberRangeCheck(str)) {
            LOG.error("Input String is not valid, a valid input should contain only number seperated by : and number should be in range between 00 to 59");
            throw new BerlinClockException("Input String should only contain a valid number. Between 00-59");
        }
    }
}
