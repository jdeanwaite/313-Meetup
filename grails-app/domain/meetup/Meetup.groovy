package meetup

class Meetup {

    static constraints = {
    }

    int id;
    String name;
    String publicId;
    Date dateCreated;

    static hasMany = [availableTimes: AvailableTime];

}
