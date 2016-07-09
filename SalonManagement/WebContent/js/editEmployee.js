$(function (){
var $orders = $('#orders');
$.ajax({
type: 'GET',
url: '/api/orders.json',
dataType: 'json',
success: function(orders) {
$.each(orders, function(i, order){
$orders.append('<li>name:'+order.name+'drink:'+order.drink+'</li>');
});
}
}); //END ajax options object }); //END document read
});