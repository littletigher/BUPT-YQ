$('form').on('submit',function(e){
    e.preventDefault()
    const data = $('form').serialize()
    console.log(data)
    //username=123&password=123&rpassword=123传递给后端注册
    //若注册失败，显示提示信息


    $.post('http://localhost:10003/regist?'+data,res=>{
        console.log(res)
        //注册失败
        if(res.result == -1){
            $('form>span').css('display','block')
            return
        }
        window.alert('恭喜你，注册成功')
        window.location.href = './login.html'
    })
})
