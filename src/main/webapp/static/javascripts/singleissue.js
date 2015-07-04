/**
 * Created by Huxley on 7/2/15.
 */

var singleIssue = (function() {
    var progress = $('table.attributes td.progress > p').html();
    progress = progress.substr(0, progress.length-1);
    $('table.attributes td.progress span.upper').css({width: progress * 0.8 + 'px'});

    $('#issue-form input[type=range]').bind('change', function() {
        var value = this.value;
        $('#issue_show_done_radio').html(value);
    });

    $('#issue-form').bind('change', function() {
        $('#issue-form input[type=submit]').prop('disabled', false);
    });

    var notice = document.querySelector('div.flash');
    console.log(notice);
    if (undefined != notice) {
        setTimeout(function() {
            $(notice).animate({height: 0}, 500, 'linear', function() {
                notice.style.display = "none";
            })
        }, 4500);
    }

})();