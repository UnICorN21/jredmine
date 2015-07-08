/**
 * Created by Huxley on 7/1/15.
 */

var issues = (function() {
    var self = this;

    var ajaxErrorHandler = function(module, url) {
        return function() {
            console.error(module + ': Get ' + url + ' error!');
        }
    };

    $('.issues .query-form-content > fieldset > legend').click(function() {
        var item = $(this).parent();
        var div = $(this).next('div');
        if ($(item).hasClass('collapsible')) {
            $(item).removeClass('collapsible');
            $(item).addClass('collapsed');
            $(div).css({display: "none"});
        } else if ($(item).hasClass('collapsed')) {
            $(item).removeClass('collapsed');
            $(item).addClass('collapsible');
            $(div).css({display: "block"});
        }
    });

    $('#issues-table > tbody').on('click', 'tr', function(event) {
        var _trCheckbox = $(this).find('.checkbox > input');
        if (event.target.nodeName !== "INPUT") {
            _trCheckbox.prop('checked', true);
        }
        if (_trCheckbox.prop('checked')) $(this).addClass('context-menu-selection');
        else $(this).removeClass('context-menu-selection');
    });

    var contextMenu = (function() {

        $.ajax({
            url: '/ajax_userinfo.do',
            dataType: 'json',
            success: function(data) {
                console.log(data);
                // TODO
            },
            error: ajaxErrorHandler('issues -> contextMenu', 'userinfo')
        });

        var menu = document.createElement('div'),
            menuItems =
            '<ul>' +
            '   <li><a class="icon icon-edit disabled" href="#">Edit</a></li>' +
            '   <li><a class="submenu" href="#">Tracker</a></li>' +
            '   <li><a class="icon icon-copy disabled" href="#">Copy</a></li>' +
            '   <li><a class="icon icon-delete disabled" href="#">Delete</a></li>' +
            '</ul>';
        menu.className = 'context-menu';
        menu.innerHTML = menuItems;
        menu.style.display = "none";

        var tbody = document.querySelector('#issues-table > tbody');
        tbody.appendChild(menu);

        tbody.oncontextmenu = function(event) {
            menu.style.left = event.pageX;
            menu.style.top = event.pageY;
            menu.style.display = "block";
            return false;
        };

        $('section.issues').click(function() {
            menu.style.display = "none";
        })

    })();

    var refresh = function(data) {
        var tbody = document.querySelector('#issues-table > tbody');

        for(var i = 0; i < data.length; ++i) {
            var tr = document.createElement('tr');
            var trContent =
                '<tr class="odd">' +
                '<td class="checkbox">' +
                '   <input type="checkbox" name="ids[]">' +
                '</td>' +
                '<td class="id">' +
                '   <a href="/single_issue.do?id=' + data[i].id + '">' + data[i].id + '</a>' +
                '</td>' +
                '<td class="tracker">' + data[i].tracker + '</td>' +
                '<td class="status">' + data[i].status + '</td>' +
                '<td class="priority">' + data[i].priority + '</td>' +
                '<td class="subject">' +
                '   <a href="/single_issue.do?id=' + data[i].id + '">' + data[i].subject + '</a>' +
                '</td>' +
                '<td class="assigned_to">' +
                '   <a class="user" href="/ajax_user?id=">' + data[i].assignee + '</a>' +
                '</td>' +
                '<td class="updated_on">' + data[i].updateTime + '</td>' +
                '</tr>';
            tr.innerHTML = trContent;
            tbody.appendChild(tr);
        }
    };

    return {
        getData: function(force) {
            var cache;
            force = force | false;
            if (!force && !cache) {
                var param = { projectId: util.getUrlParam('id') };

                $.ajax({
                    url: "/ajax_issues.do",
                    method: "POST",
                    dataType: "json",
                    async: false,
                    data: param,
                    success: function(data) {
                        console.log(data);
                        cache = data;
                        refresh(data);
                    },
                    error: ajaxErrorHandler('issues', 'issues')
                });
            }
            return cache;
        }
    }
})();