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
        let form = $(this);
        event.preventDefault();
        $.ajax({
            type: 'POST',
            url: '/posts/create',
            data: form.serialize(),
            success: function(data){
                if(data === "success") {
                    // Cleaning Post-creation View
                    $(".modal-create-post").hide();
                    $("body").css("overflow", "auto");
                    $("#page-mask").hide();
                    $(".post-alert.alert-success").show();

                    // Append new post
                    $(".profile-posts-wrapper").append(
                        '<div class="page">' +
                        '<div class="inner-wrapper">'+
                        '    <h3>' + form.find('input[name="title"]').val() + '</h3>'+
                        '    <div class="details">'+
                        '       <ul>'+
                        '          <li>' + form.find('input[name="author"]').val() + '</li>'+
                        '         <li>' + new Date() + '</li>'+
                        '        <li>' + form.find('input[name="tags"]').val() + '</li>'+
                        '   </ul>'+
                        '       </div>'+
                        '        <div class="content">'+
                        '           <p>' + form.find('textarea[name="description"]').val() + '</p>'+
                        '    </div>'+
                        '   <h4><a href="read-more">Read More</a></h4>'+
                        '    </div>' +
                        '</div>'
                    );
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

    $(".page").hover(function (){
        $(this).find(".post-options-overlay").first().fadeIn();
        $(this).find(".inner-wrapper").css({
            "border-top-left-radius": "unset",
            "border-top-right-radius": "unset"
        });
    }, function(){
        $(this).find(".post-options-overlay").first().hide();
        $(this).find(".inner-wrapper").css("border-radius", "10px");
    });
});