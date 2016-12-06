<!doctype html>
<html lang="en">
<g:include controller="layout" action="head" params="[title: 'Add Available Times']"/>

<body>

<nav>

</nav>

<div class="container">
    <div class="page-header">
        <h1>Meetup ${id.toUpperCase()}</h1>
    </div>

    <div class="content push-down-15 max-50">
        <div class="row">
            <div class="col-xs-12">
                <h4>Add Available Times</h4>
                <g:form controller="meetup" action="submitHours" method="post">
                    <input name="meetupId" type="hidden" value="${id}">

                    <div class="form-group">
                        <input type="text" class="form-control" name="name" placeholder="Your Name" required>
                    </div>

                    <div id="timeSlots">
                        <div class="form-group">
                            <div class='input-group date'>
                                <input type='text' name="daterange[]" id="daterange1" class="form-control"/>
                                <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-calendar"></span>
                                </span>
                            </div>
                        </div>
                    </div>
                    <button id="addTimeButton" class="btn btn-default" type="button" name="button">Add Time</button>
                    <button class="btn btn-success" type="submit">Submit</button>
                </g:form>
            </div>
        </div>
    </div>
</div>

<g:include controller="layout" action="footer"/>
<g:include controller="layout" action="scripts"/>

</body>
</html>
