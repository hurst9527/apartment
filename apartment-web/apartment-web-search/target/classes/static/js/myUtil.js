function pormptBox(times, showText) {
    $("#PromptBox").html(showText);
    $("#PromptBox").show(380);
    $("#PromptBox").delay(times).hide(380);
}