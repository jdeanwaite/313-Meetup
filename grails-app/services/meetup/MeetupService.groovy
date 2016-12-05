package meetup

import grails.transaction.Transactional
import org.apache.commons.lang.RandomStringUtils

@Transactional
class MeetupService {

    def serviceMethod() {

    }

    def createMeetup(String meetupName) {
        println("Yo");
        Meetup meetup = new Meetup(
                name: meetupName,
                publicId: RandomStringUtils.randomAlphanumeric(5),
                dateCreated: new Date()
        );
        println("Y1");
        meetup.save();
        println("Yo2");
        return [success: true, message: "Meetup created successfully.", meetupId: meetup.publicId];
    }
}
