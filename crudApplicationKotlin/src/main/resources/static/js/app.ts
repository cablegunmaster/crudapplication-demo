class userModule {

    usermodule: userModule;

    constructor() {
        this.usermodule = this;
        this.init()
    }

    init() {
        let $that = this;
        let button = <HTMLElement>document.body.querySelector("#userButton");
        if (button) {
            button.addEventListener("click", function (e: Event) {
                e.preventDefault();

                //let form: any = $("#form").serialize(); //string array blabla=apple&id=33
                //JSON.stringify(Object.fromEntries($("#form"))); //ecmascript >.>
                let form: JQuery<HTMLFormElement> = $("form");
                let data: JSON = $that.serializeJSONForm(form);

                //if data ID is empty. create user.
                //if ID is filled user is updating.
                $that.createUpdateUser(data);
            });
        }
    }

    createUpdateUser(data: any) {
        httpRequest.postrequest(
            "/user/create",
            data,
            "/user/list");
    }

    //might think about multi values.
    serializeJSONForm(formElement: JQuery<HTMLFormElement>): any {
        return formElement.serializeArray().reduce(function (obj, item) {
            obj[item.name] = item.value;
            return obj;
        }, {});
    }
}

new userModule();
