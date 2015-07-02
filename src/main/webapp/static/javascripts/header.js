/**
 * Created by Huxley on 7/1/15.
 */

var header = (function() {
    return {
        setTitle: function(title) {
            var head = document.querySelector('.banner > h1');
            head.innerHTML = title;
        }
    }
})();