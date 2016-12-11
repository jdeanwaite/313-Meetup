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
                <h4>Members</h4>
                The following members have submitted their available times: <br>
                <strong>${members.join(', ')}</strong>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="col-xs-12">
                <h4>See Common Times</h4>
                <g:each var="availableTime" in="${times}">
                    <p><strong>${availableTime.key}</strong></p>
                    <table class="table">
                        <g:each var="time" in="${availableTime.value}">
                            <tr>
                                <g:if test="${time <= 12}">
                                    <td>${time}:00 AM</td>
                                </g:if>
                                <g:else>
                                    <td>${time - 12}:00 PM</td>
                                </g:else>
                                %{--<td class="add-time-button">--}%
                                    %{--<button class="btn btn-primary">Select</button>--}%
                                %{--</td>--}%
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
