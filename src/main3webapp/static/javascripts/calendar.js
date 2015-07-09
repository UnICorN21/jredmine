/**
 * Created by Huxley on 7/9/15.
 */

var calendar = (function() {
    $('#calendar').fullCalendar({
        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month,agendaWeek'
        },
        events: function(start, end, timezone, callback) {
            $.ajax({
                url: '/ajax_issues.do',
                dataType: 'json',
                data: {
                    projectId: util.getUrlParam('id'),
                    start: start.unix(),
                    end: end.unix()
                },
                success: function(data) {
                    var events = [];
                    data.forEach(function(event) {
                       events.push({
                           id: event.id,
                           title: event.subject,
                           start: event.startDate,
                           end: event.dueDate,
                           color: (function(status) {
                               if ("New" === status) return "rgb(237, 153, 2)";
                               else if ("In progress" === status) return "#3899B8";
                               else return "rgb(76, 174, 76)";
                           })(event.status),
                           url: '/single_issue.do?id=' + event.id,
                           update: event.updateTime,
                           assignee: event.assignee,
                           priority: event.priority,
                           status: event.status,
                           tracker: event.tracker
                       });
                    });
                    console.log(events);
                    callback(events);
                }
            });
        },
        editable: false,
        eventLimit: true, // allow "more" link when too many events
        eventMouseover: function(event, jsEvent, view) {

            var labels = [['id', '#'], ['title', 'subject'], 'assignee', 'priority', 'status', 'tracker', 'update'];

            var content = (function(event) {
                var html = '', i;
                for (i = 0; i < labels.length; ++i) {
                    if ('object' === typeof(labels[i]))
                        html += '<strong>' + util.upperFirst(labels[i][1]) + '</strong>:&nbsp;' + event[labels[i][0]] + '<br>';
                    else html += '<strong>' + util.upperFirst(labels[i]) + '</strong>:&nbsp;' + event[labels[i]] + '<br>';
                }
                return html;
            })(event);

            var block = document.querySelector('#calendar .cal-float') || (function() {
                    var rst = document.createElement('div');
                    rst.className = "cal-float";
                    document.querySelector('#calendar').appendChild(rst);
                    return rst;
                })();
            $(block).css({
                left: jsEvent.pageX,
                top: jsEvent.pageY,
                background: (function(status) {
                    if ('New' === status) return "rgb(247, 240, 227)";
                    else if ('In progress' === status) return "#D7E7EC";
                    else return "rgb(212, 232, 212)";
                })(event.status)
            });
            block.innerHTML = content;
            block.style.display = 'block';
        },
        eventMouseout: function(event, jsEvent, view) {
            var block = document.querySelector('#calendar .cal-float');
            if (undefined !== block) {
                block.style.display = 'none';
            }
        }
    });
})();
