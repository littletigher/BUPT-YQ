const id = window.localStorage.getItem('id')

if (!id) {
    //表示未登录
    $('.login').removeClass('active')
    $('.logout').addClass('active')
} else {
    //表示登陆过,根据id获取信息
    //测试环境只能用get
    $.get('./json/login.json', res => {
        console.log(res)
        //获取失败
        if (res.code == 0) {
            $('.login').removeClass('active')
            $('.logout').addClass('active')
            return
        }
        console.log(res.data[0].name)
        //获取成功
        console.log('获取成功')
        $('.login').addClass('active').find('span').text(res.data[0].name)
        $('.logout').removeClass('active')

    })

}


//个人中心没写
$('.login button').on('click',function(){
    window.location.href = './login.html'
})