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

    var description = document.querySelector('#description-body');
    description.innerHTML = marked(description.innerHTML);

    util.checkForm('issue-form');

    util.dismiss('div.flash');

})();