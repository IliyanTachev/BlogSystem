$(document).ready(function (){
    $("#jumbotron-profile-follow-button").click(function(){
        if ($(this).text() == "+ Follow"){
            $(this).animate({ width: '-=10px' }, 100, 'easeInQuad', function () {});
            $(this).animate({ width: '+=45px', left: '-=15px' }, 600, 'easeInQuad', function () {
                $(this).css("color", "#fff");
                $(this).text("Following");

                $(this).animate({
                    backgroundColor: "#2EB82E",
                    borderColor: "#2EB82E"
                }, 1000 );
            });
        }else if($(this).text() != "Me"){

            // *** State Change: Unfollow ***
            // Change the button back to it's original state
            $(this).animate({ width: '-=25px', left: '+=15px' }, 600, 'easeInQuad', function () {
                $(this).text("+ Follow");
                $(this).css("color", "#3399FF");
                $(this).css("background-color", "#ffffff");
                $(this).css("border-color", "#3399FF");
            });
        }
    });

    $(".btn-create-post").click(function (){
        $("#page-mask").show();
        $(".modal-create-post").show();
        $("body").css("overflow", "hidden");
    });

    $("#create-post-form").submit(function (event){
        event.preventDefault();
        $.ajax({
            type: 'POST',
            url: '/posts/create',
            data: $(this).serialize(),
            success: function(data){
                if(data === "success") {
                    // Cleaning Post-creation View
                    $(".modal-create-post").hide();
                    $("body").css("overflow", "auto");
                    $("#page-mask").hide();
                    $(".post-alert.alert-success").show();
                }
                else alert("ERROR");
            }
        })
    });

    $('.close').click(function() {
        $(this).parents(".closable").hide();
        if($("#page-mask").css('display') !== 'none'){
            $("#page-mask").hide();
            $("body").css("overflow", "auto");
        }
    });
});