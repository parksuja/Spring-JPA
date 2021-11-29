let index= {
    init:function(){
       $("$btn-save").on("click",()=>{
           this.save();
       })
    //    첫번째 on 파라미터 click 이벤트 두번째는 무엇을 할것인지
    }

    save:function (){
        alert("user의 save함수 호출됨");
    }

}

index.init();