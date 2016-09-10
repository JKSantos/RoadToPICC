/**
 * Created by Castillo on 9/10/2016.
 */
$.ajax({
    type: 'get',
    url: 'getJob',
    dataType: 'json',
    async: true,
    success: function (data) {
        console.log(data);
    }
});