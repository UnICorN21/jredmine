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
        },
        checkForm: function(id) {
            $('#' + id).bind('change', function() {
                $('#' + id + ' input[type=submit]').prop('disabled', false);
            });
        },
        dismiss: function(selector, animateTime, delay) {
            animateTime = animateTime | 500;
            delay = delay | 4500;

            var element = document.querySelector(selector);
            if (undefined != element) {
                setTimeout(function() {
                    $(element).animate({height: 0}, animateTime, 'linear', function() {
                        element.style.display = "none";
                    })
                }, delay);
            } else {
                console.error('util: Couldn\'t select the given element!')
            }
        },
        upperFirst: function(str) {
            return str.charAt(0).toUpperCase() + str.slice(1);
        }
    }
})();