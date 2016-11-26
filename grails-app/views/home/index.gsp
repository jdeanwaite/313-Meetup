<!doctype html>
<html lang="en">
<g:include controller="layout" action="head" params="[title: 'Meetup']"/>

<body>

<nav>

</nav>

<div class="container">
    <div class="page-header">
        <h1>Meetup <small>A simple group scheduling app</small></h1>
    </div>

    <div class="content push-down-15 max-50">
        <div class="row">
            <div class="col-xs-12">
                <h4>Create a Meetup</h4>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-12 text-center">
                <g:form class="create-form" controller="meetup" action="create" method="post">
                    <button type="submit" class="btn btn-default">Create</button>

                    <div class="fill-width">
                        <input type="text" class="form-control inline-form-control" name="meetupName"
                               placeholder="New Meetup Name">
                    </div>
                </g:form>
            </div>
        </div>
    </div>
</div>

<g:include controller="layout" action="footer"/>
<g:include controller="layout" action="scripts"/>

</body>
</html>
