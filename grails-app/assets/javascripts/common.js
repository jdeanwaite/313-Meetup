var TIME_SLOTS = 1;

function initDateRangePicker(id) {
    $('#daterange' + id).daterangepicker({
        timePicker: true,
        timePickerIncrement: 60,
        locale: {
            format: 'MM/DD/YYYY H:mm A'
        }
    });
}

$('#addTimeButton').click(function() {
    var timeSlotId = ++TIME_SLOTS;
    var timeSlotsElem = $('#timeSlots');
    var formGroupHTML = '<div class="form-group">' +
                            '<div class="input-group date">' +
                                '<input name="daterange[]" type="text" id="daterange' + timeSlotId + '" class="form-control" />' +
                                '<span class="input-group-addon">' +
                                    '<span class="glyphicon glyphicon-calendar"></span>' +
                                '</span>' +
                            '</div>' +
                        '</div>';
    timeSlotsElem.append(formGroupHTML);
    initDateRangePicker(timeSlotId);
});

initDateRangePicker(TIME_SLOTS);
