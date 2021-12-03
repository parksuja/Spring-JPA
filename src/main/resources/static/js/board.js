let index= {
    init:function(){
       $("#btn-board-save").on("click",()=>{
           this.save();
       })

    },

    save:function (){
        // alert("user의 save함수 호출됨");
        let data ={
           title:$("#title").val(),
            content:$("#content").val()

        };

        // console.log(data);
        //ajax통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청

        //ajax 호출시 default가 비동기 호출
        $.ajax({
            //회원가입 수행 요청
            type:"POST",
            url:"/api/board",
            data:JSON.stringify(data),  //JSON문자열로 보내준다. http body데이터
            contentType:"application/json;charset=utf-8", //body데이터가 어떤 타입인지
            //ajax가 통신을 성공하고 json을 리턴해주면 자동으로 자바 오브젝트로 변환해준다.
            // dataType:"json" //요청을 서버로해서 응답이 왔을 때 (기본적으로 응답 String문자)
        //   생긴게 json이라면 => javaScript Object로 변환해서 보여줌
        }).done(function(resp){
            alert("글쓰기 완료");
            location.href="/";
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },
}

index.init();


// $("#btn-login").on("click",()=>{
//     this.login();
// })
//    첫번째 on 파라미터 click 이벤트 두번째는 무엇을 할것인지
// login: function() {
//     //alert('user의 save함수 호출됨);
//     let data = {
//         username: $("#username").val(),
//         password: $("#password").val()
//     };
//
//     $.ajax({
//         type: 'POST',
//         url: "/api/user/login",
//         data: JSON.stringify(data),
//         contentType: "application/json; charset=utf-8",
//         dataType: "json"
//     }).done(function (resp) {
//         alert("로그인이 완료");
//         location.href = "/";
//     }).fail(function (error) {
//         alert(JSON.stringify(error));
//     });