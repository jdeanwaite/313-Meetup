package meetup

import grails.converters.JSON

class MeetupController {
    // this is how to include and essentially create an instance of the MeetupService in Groovy.
    def meetupService;

    /*
    Route for creating a new meetup. If a name is provided, we call the meetupService to create it,
    otherwise we return an error message to the user.
    */

    def create = {
        String meetupName = params.get("meetupName");

        def results = [success: false, message: "Failed to create meetup."];
        if (meetupName) {
            println("Creating new meetup");
            results = meetupService.createMeetup(meetupName);
            if (results && results.success) {
                redirect(action: 'meetup', params: [id: results.meetupId]);
            } else {
                println("Not successful:");
                println(results)
                redirect(controller: 'home', action: 'index', params: results);
            }
            //TODO: redirect to the scheduling page
        } else {
            results = [success: false, message: "Provide a name for the meetup"];
            forward controller: 'home', view: 'index', params: results;
        }

//        render results as JSON;
    }

    def results() {

        [
                times: times
        ]
    }

    def submitHours() {
        def times = params.get('daterange[]');
        //TODO: We need to have the schedule form submit a date (2016-12-10 or something) and an hour of the day in 24 hours format.
        println(times);
    }

    def meetup() {
        [
                id: params.get("id")
        ]
    }
}
