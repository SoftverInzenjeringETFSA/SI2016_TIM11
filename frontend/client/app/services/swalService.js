export default class SwalService {
    success(text) {
        swal({
            title: 'Bravo!',
            text: text,
            type: 'success'
        });
    }

    areYouSure(text, callback) {
        swal({
            title: 'Da li ste sigurni?',
            text: text,
            type: 'warning',
            showCancelButton: true,
            cancelButtonText: 'Ne, zaustavi',
            confirmButtonText: 'Da, nastavi',
            closeOnConfirm: true
        }, callback);
    }
}