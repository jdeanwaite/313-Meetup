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
    }

    def results() {
        def times = [];
        def availableTimes = meetupService.getCommonMeetupTimes(params.get("id"));
        [
                times: times
        ]
    }

    def submitHours() {
        def times = params.list('daterange[]');
        def dateTimes = [];
        def meetupId = params.get('meetupId') as String;
        def name = params.get('name') as String;

        println("times " + times);

        times.each {
            def dateString = "${it}";
            def pieces = dateString.split(' ');

            def start = [];
            def end = [];

            print("dateString ");
            println(dateString);

            print("pieces ");
            println(pieces);

            start.push(pieces[0]);
            start.push(pieces[1]);
            end.push(pieces[4]);
            end.push(pieces[5]);

            def dateTime = [];

            dateTime.push(start.join(" "));
            dateTime.push(end.join(" "));
            dateTimes.push(dateTime);
        }

        println(dateTimes);

        meetupService.saveAvailableTimes(meetupId, dateTimes, name);

        redirect(controller: 'meetup', action: 'results', params: times);
    }

    def meetup() {
        [
                id: params.get("id")
        ]
    }
}
