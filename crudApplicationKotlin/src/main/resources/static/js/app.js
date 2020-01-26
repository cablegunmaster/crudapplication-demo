var userModule = (function () {
    function userModule() {
        this.usermodule = this;
        this.init();
    }
    userModule.prototype.init = function () {
        var $that = this;
        var button = document.body.querySelector("#userButton");
        if (button) {
            button.addEventListener("click", function (e) {
                e.preventDefault();
                var form = $("form");
                var data = $that.serializeJSONForm(form);
                $that.createUpdateUser(data);
            });
        }
    };
    userModule.prototype.createUpdateUser = function (data) {
        httpRequest.postrequest("/user/create", data, "/user/list");
    };
    userModule.prototype.serializeJSONForm = function (formElement) {
        return formElement.serializeArray().reduce(function (obj, item) {
            obj[item.name] = item.value;
            return obj;
        }, {});
    };
    return userModule;
}());
new userModule();
//# sourceMappingURL=app.js.map