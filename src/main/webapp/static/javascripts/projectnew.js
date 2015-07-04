/**
 * Created by Huxley on 7/4/15.
 */

var projectNew = (function() {

    $('#issue-form input[type=range]').bind('change', function() {
        var value = this.value;
        $('#issue_show_done_radio').html(value);
    });

    util.checkForm('issue-form');
    util.dismiss('div.flash');

})();
