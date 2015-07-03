/**
 * Created by Huxley on 7/1/15.
 */

var util = (function() {
    return {
        getUrlParam: function(name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
            var val = window.location.search.substr(1).match(reg);
            if (null != val) return val[2]; else return null;
        },
        showAndScrollTo: function(id) {
            $('#' + id).show();
            $('html, body').animate({scrollTop: $('#' + id).offset().top}, 100);
        }
    }
})();