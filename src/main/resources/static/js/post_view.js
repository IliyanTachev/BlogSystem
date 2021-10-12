$(document).ready(function (){
    console.log("It is working mf");
    $("#like-unlike-buttons > .like-icon").click(function () {
        if($(this).hasClass("far")){
            $("#like-unlike-buttons > .unlike-icon").removeClass("fas").addClass("far");
            $(this).removeClass("far").addClass("fas");
            $("#rating-choice-text").css({
                'background-color': 'rgb(40,167,69)',
                'color': 'white'
            })
            .text("You liked this post.").fadeIn().delay(200).fadeOut();
            $.ajax({
                url: "/posts/update/rating/likes/" + $('#post-id').val(),
                type: "post",
                success: function(data){
                    $("#likes-counter").text(data[0]);
                    $("#dislikes-counter").text(data[1]);
                }
            });
        } else {
            $(this).removeClass("fas").addClass("far");
        }
    });

    $("#like-unlike-buttons > .unlike-icon").click(function() {
        if($(this).hasClass("far")) {
            $("#like-unlike-buttons > .like-icon").removeClass("fas").addClass("far");
            $(this).removeClass("far").addClass("fas");
            $("#rating-choice-text").css({
                'background-color': 'rgb(220,53,69)',
                'color': 'white'
            }).text("You unliked this post.").fadeIn().delay(200).fadeOut();
            $.ajax({
                url: "/posts/update/rating/dislikes/" + $('#post-id').val(),
                type: "post",
                success: function(data){
                    $("#dislikes-counter").text(data[0]);
                    $("#likes-counter").text(data[1]);
                }
            });
        } else {
            $(this).removeClass("fas").addClass("far");
        }
    });
});