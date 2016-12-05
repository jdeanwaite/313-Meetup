package meetup

class AvailableTime {

    int id;
    String name;
    Date date;
    int hour;

    static constraints = {
    }

    static hasOne = ['meetup': Meetup];
    static belongsTo = [Meetup];
}
