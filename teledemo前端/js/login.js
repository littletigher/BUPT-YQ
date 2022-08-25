$('form').on('submit', function (e) {
    e.preventDefault()
    const data = $('form').serialize()
    console.log(data)
    //userName=mmmm&password=123传递给后端登录
    //若登录失败，显示提示信息active


     $.post('http://localhost:10003/login?'+data,res=>{
         console.log(res)
         //登录失败
         if(res.code == -1){
             $('form>span').css('display','block')
             return
         }
         //window.localStorage.setItem('id',res.data[0].userId)
         window.location.href = './index.html'
     })

    //测试环境只能用get，具体用上面的
/*     $.get('./json/login.json', res => {
        console.log(res)
        //登录失败
        if (res.code == 0) {
            $('form>span').css('display', 'block')
            return
        }
        console.log(res.data[0].userId)
        window.localStorage.setItem('id',res.data[0].userId)
        window.location.href = './index.html'
    }) */
})