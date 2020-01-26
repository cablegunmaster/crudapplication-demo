var httpRequest = (function () {
    function httpRequest() {
    }
    httpRequest.postrequest = function (url, data, redirect_url) {
        $.ajax({
            type: "POST",
            url: url,
            data: JSON.stringify(data),
            contentType: "application/json",
            headers: {
                'Accept': 'application/json, text/plain, */*',
            },
            error: function (data) {
                alert("stomething went wrong");
            },
            success: function (data) {
                alert("successful send");
                if (redirect_url.length != -1) {
                    window.location.replace("/user/list");
                }
            }
        });
    };
    httpRequest.deleterequest = function (url) {
        $.ajax({
            type: "DELETE",
            url: url,
            success: function (data) {
                alert("successful deleted user");
                location.reload();
            }
        });
    };
    return httpRequest;
}());
//# sourceMappingURL=httprequest.js.map