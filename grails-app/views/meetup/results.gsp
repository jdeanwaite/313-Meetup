<!doctype html>
<html lang="en">
<g:include controller="layout" action="head" params="[title: 'See Common Times']"/>

<body>

<nav>

</nav>

<div class="container">
    <div class="page-header">
        <h1>Results</h1>
    </div>

    <div class="content push-down-15 max-50">
        <div class="row">
            <div class="col-xs-12">
                <h4>See Common Times</h4>
                <g:each var="availableTime" in="${times}">
                    <p>${availableTime.key}</p>
                    <table class="table">
                        <g:each var="time" in="${availableTime.value}">
                            <tr>
                                <td>${time}</td>
                                <td class="add-time-button">
                                    <button class="btn btn-primary">Add</button>
                                </td>
                            </tr>
                        </g:each>
                    </table>
                </g:each>
            </div>
        </div>
    </div>
</div>

<g:include controller="layout" action="footer"/>
<g:include controller="layout" action="scripts"/>

</body>
</html>
