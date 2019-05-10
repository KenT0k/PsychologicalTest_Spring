$(document).ready(function () {
    $(".link").hide();
    $(".form-check-input").change(function () {
        var names = {};
        $('.form-check-input').each(function () {
            names[$(this).attr('name')] = true;
        });
        var count = 0;
        $.each(names, function () {
            count++;
        });
        if ($('.form-check-input:checked').length === count) {
            var score = 0;
            $(".form-check-input:checked").each(function () {
                score += parseInt($(this).val());
            });
            var titleOfTest = $(".titleOfTest").text();
            var oldUrl = '/saveResult/title=' + titleOfTest + '/result=';
            $(".link").attr('href', oldUrl).show();
            var url = document.getElementById("idLink").href;
            $(".link").attr('href', url + score);
        }
    });
});