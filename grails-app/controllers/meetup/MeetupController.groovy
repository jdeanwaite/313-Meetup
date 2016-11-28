package meetup

import grails.converters.JSON

class MeetupController {
    // this is how to include and essentially create an instance of the MeetupService in Groovy.
    def meetupService;

    /*
    Route for creating a new meetup. If a name is provided, we call the meetupService to create it,
    otherwise we return an error message to the user.
    */
    def create() {
        println("Creating new meetup");
        String meetupName = params.get("meetupName");

        def results = [success:false, message: ""];
        if (meetupName) {
//            results = meetupService.createMeetup(meetupName);
            //TODO: redirect to the scheduling page
        } else {
            results = [success: false, message: "Provide a name for the meetup"];
            forward controller: 'home', view: 'index', params: results;
            return;
        }

        render results as JSON;
    }

    def results() {
        def times = params.get('daterange[]');
        render times as JSON;
    }

    def meetup() {

    }
}
