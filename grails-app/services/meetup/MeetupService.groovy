package meetup

import grails.transaction.Transactional
import org.apache.commons.lang.RandomStringUtils

import javax.sql.DataSource
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement
import java.text.SimpleDateFormat
import java.util.regex.Matcher
import java.util.regex.Pattern

@Transactional
class MeetupService {

    def serviceMethod() {

    }

    def createMeetup(String meetupName) {
        Meetup meetup = new Meetup(
                name: meetupName,
                publicId: RandomStringUtils.randomAlphanumeric(5),
                dateCreated: new Date()
        );
        meetup.save();
        return [success: true, message: "Meetup created successfully.", meetupId: meetup.publicId];
    }

    def saveAvailableTimes(def publicId, def times, def name) {
        println("inside " + publicId + " " + times + " " + name);
        def results = [success: false, message: "Meetup not found."];
        Meetup meetup = Meetup.findByPublicId(publicId);
        println("meetup: " + meetup);
        if (meetup != null) {
            println("parsing times");
            def dayHourMap = parseHoursFromTimes(times);
            println("dayHourMap " + dayHourMap);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
            dayHourMap.each {
                key, value ->
                    print key;
                    println("dateString " + key);
                    Date date = simpleDateFormat.parse(key);
                    for (int hour : value) {
                        AvailableTime availableTime = new AvailableTime();
                        availableTime.meetup = meetup;
                        availableTime.date = date;
                        availableTime.name = name;
                        availableTime.hour = hour;
                        availableTime.save();
                    }
            }
            results.success = true;
            results.message = "";
        }
        return results;
    }

    private def parseHoursFromTimes(def times) {
        def dayHourMap = [:];
        for (def timeRange : times) {
            Pattern pattern = Pattern.compile("(\\d+.\\d+.\\d+)\\s+(\\d+):\\d{2}");

            println("timeRange " + timeRange);

            Matcher matcherStart = pattern.matcher(timeRange[0] as String);
            Matcher matcherEnd = pattern.matcher(timeRange[1] as String);

            println("checking matches on " + timeRange[0] + " and " + timeRange[1]);

            if (matcherStart.matches() && matcherEnd.matches()) {
                println("matches found");
                SimpleDateFormat fromFormat = new SimpleDateFormat("MM/dd/yyyy");
                SimpleDateFormat toFormat = new SimpleDateFormat("yyyy/MM/dd");

                Date startDate = fromFormat.parse(matcherStart.group(1));
                Date endDate = fromFormat.parse(matcherEnd.group(1));

                String startDateString = toFormat.format(startDate);
                String endDateString = toFormat.format(endDate);

                int startHour = Integer.parseInt(matcherStart.group(2));
                int endHour = Integer.parseInt(matcherStart.group(2));

                if (startDate != endDate) {
                    dayHourMap."${startDateString}" = [];
                    for (int i = startHour; i < 24; i++) {
                        dayHourMap."${startDateString}".add(i);
                    }

                    if (endHour > 0) {
                        dayHourMap."${endDateString}" = [];
                        for (int i = 0; i <= endHour; i++) {
                            dayHourMap."${endDateString}".add(i);
                        }
                    }
                } else {
                    dayHourMap."${startDateString}" = [];
                    for (int i = startHour; i <= endHour; i++) {
                        dayHourMap."${startDateString}".add(i);
                    }
                }
            }
        }
        return dayHourMap;
    }

    def getCommonMeetupTimes(String publicId) {
        println('\npublicId: ' + publicId + '\n');
        def id = Meetup.findByPublicId(publicId).id;
        println('\nid: ' + id + '\n');
        def availableTimes = [:];
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/meetup", "root", "root");

        Statement st = con.createStatement();
        String sql = ("SELECT a.date, a.hour " +
                "FROM available_time a " +
                "INNER JOIN ( " +
                "SELECT b.date, b.hour " +
                "FROM available_time b " +
                "WHERE b.meetup_id = ${id} " +
                "GROUP BY b.date, b.hour " +
                "HAVING COUNT(b.name) = (select count(distinct(name)) from available_time c where c.meetup_id = ${id}) " +
                ") temp ON a.hour = temp.hour and a.date = temp.date " +
                "WHERE a.meetup_id = ${id} " +
                "GROUP BY a.date, a.hour");
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
            if (!availableTimes[simpleDateFormat.format(rs.getDate("date"))]) {
                availableTimes[simpleDateFormat.format(rs.getDate("date"))] = [];
            }
            availableTimes[simpleDateFormat.format(rs.getDate("date"))].add(rs.getInt("hour"))
        }

        println("availableTimes: " + availableTimes);

        con.close();

        return availableTimes;
    }
}
