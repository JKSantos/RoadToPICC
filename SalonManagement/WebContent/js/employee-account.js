/**
 * Created by Castillo on 9/11/2016.
 */

function passEmpID(id) {
    $('#pwdontMatch' + id).hide();
    $('#strUpEmpConfPassword' + id).on('input', function() {
       if($(this).val() != $('#strUpEmpNewPassWord' + id).val()) {
           $('#pwdontMatch' + id).show();
           $('.updateSubmitForm').prop('disabled', true).css('opacity', '0.3').css('cursor', 'not-allowed');
       } else if ($(this).val() == $('#strUpEmpNewPassWord' + id).val()) {
           $('#pwdontMatch' + id).hide();
           $('.updateSubmitForm').prop('disabled', false).css('opacity', '1').css('cursor', 'default');
       }
    });
}