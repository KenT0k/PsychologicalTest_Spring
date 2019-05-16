$(document).ready(function () {
    $(".linkForTitleOfTest").hide();
    $(".titleOfTest").change(function () {
        var elem = this.value;
        $(".linkForTitleOfTest").attr('href', function () {
            $(".linkForTitleOfTest").show();
            return this.href + elem;
        });
    });
});