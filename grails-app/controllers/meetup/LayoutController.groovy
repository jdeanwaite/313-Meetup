package meetup

class LayoutController {

    def head() {
        // Our default page title if none other is provided.
        String title = "Meetup";

        // Check if there was a "title" parameter passed into the action. Set the title variable if so.
        if (params.containsKey("title")) {
            title = params.get("title");
        }

        // The last declared object is the "model" that gets passed into the view (GSP) to be rendered.
        [
                title: title
        ]
    }

    def footer() {}

    def scripts() {}
}
