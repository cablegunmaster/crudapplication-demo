
class httpRequest {

    static postrequest(
        url: string,
        data : any,
        redirect_url? : string
    ) {
        $.ajax({
            type: "POST",
            url: url,
            data: JSON.stringify(data),
            contentType: "application/json",
            headers: {
                'Accept': 'application/json, text/plain, */*',
            },
            error: function(data: any){
                alert("stomething went wrong");
            },
            success: function (data : any) {
                alert("successful send");
                if(redirect_url.length != -1) {
                    window.location.replace("/user/list");
                }
            }
        });
    }

    static deleterequest(
        url: string
    ) {
        $.ajax({
            type: "DELETE",
            url: url,
            success: function (data : any) {
                alert("successful deleted user");
                location.reload(); //reload current page
            }
        });
    }

}