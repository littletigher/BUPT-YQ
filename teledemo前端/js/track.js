$('#searchForm').on('submit', function(e){
    e.preventDefault()
    let data = $('#searchForm').serialize()
    // console.log(data)
    // searchId=123
    // 传给后端获取轨迹
    if(data == "searchId="){
        console.log('获取全部确诊轨迹')
        initAllTrack();
    }else{
        console.log('获取个人轨迹')
        console.log(data)
        initTrack(data);
    }
})

$('#total').on('click', function(){
    console.log("向后端请求所有轨迹")
    initAllTrack()
})


// 获取所有轨迹
initAllTrack()

function initTrack(searchId){
    $.get(' http://localhost:10003/gettrack',{searchId},res=>{
        console.log(res)
                if(res.code != 0){
                    console.log(res.msg)
                }else{
                    console.log("获取个人轨迹成功")
                    console.log(res.msg)
                    init(res)
                }
            }
            )
}


function initAllTrack(){
    $.get('http://localhost:10003/getalltrack',res=>{
                console.log(res)
                 if(res.code != 0){
                     console.log(res.msg)
                 }else{
                     console.log("获取全部确诊轨迹成功")
                     init(res)
                }
          

                 })
}










function init(res){

var dom = document.getElementById('container');
var myChart = echarts.init(dom, null, {
    renderer: 'canvas',
    useDirtyRect: false
});
var app = {};
// var ROOT_PATH = 'https://echarts.apache.org/examples';
var option;
// $.get('./js/lines-bus.json', function
//     (res) {
        //data是后端返回的数据
    // data = Array.from(data);
    // apply和concat降维
    // if(res.code != 0){
    //     console.log('获取数据失败')
    // }else{
    //     console.log('获取数据成功')
    // }
    let busLines = res.data
    // let busLines = [].concat.apply(
    //     [],
    //     res.data.map(function (busLine, idx) {
            // map()方法是创建一个新数组，其结果是该数组中的每个元素都调用一个提供的函数后返回的结果
            // let prevPt = [];
            // let points = [];
            // 然后列表奇数位依次相加、偶数位依次相加，两两一组,把数据都除以10000,即为各个公交站点地理坐标，每个列表代表1个线路
            // for (let i = 0; i < busLine.length; i += 2) {
            //     let pt = [busLine[i], busLine[i + 1]];
            //     if (i > 0) {
            //         pt = [prevPt[0] + pt[0], prevPt[1] + pt[1]];
            //     }
            //     prevPt = pt;
            //     //各个公交站点地理坐标
            //     points.push([pt[0] / 1e4, pt[1] / 1e4]);
            // }
            // return {
            //     coords: points
            // };
    //     })
    // );
    myChart.setOption(
        (option = {
            bmap: {
                center: [116.46, 39.92],
                zoom: 10,
                roam: true,
                mapStyle: {
                    styleJson: [
                        {
                            featureType: 'water',
                            elementType: 'all',
                            stylers: {
                                color: '#d1d1d1'
                            }
                        },
                        {
                            featureType: 'land',
                            elementType: 'all',
                            stylers: {
                                color: '#f3f3f3'
                            }
                        },
                        {
                            featureType: 'railway',
                            elementType: 'all',
                            stylers: {
                                visibility: 'off'
                            }
                        },
                        {
                            featureType: 'highway',
                            elementType: 'all',
                            stylers: {
                                color: '#fdfdfd'
                            }
                        },
                        {
                            featureType: 'highway',
                            elementType: 'labels',
                            stylers: {
                                visibility: 'off'
                            }
                        },
                        {
                            featureType: 'arterial',
                            elementType: 'geometry',
                            stylers: {
                                color: '#fefefe'
                            }
                        },
                        {
                            featureType: 'arterial',
                            elementType: 'geometry.fill',
                            stylers: {
                                color: '#fefefe'
                            }
                        },
                        {
                            featureType: 'poi',
                            elementType: 'all',
                            stylers: {
                                visibility: 'off'
                            }
                        },
                        {
                            featureType: 'green',
                            elementType: 'all',
                            stylers: {
                                visibility: 'off'
                            }
                        },
                        {
                            featureType: 'subway',
                            elementType: 'all',
                            stylers: {
                                visibility: 'off'
                            }
                        },
                        {
                            featureType: 'manmade',
                            elementType: 'all',
                            stylers: {
                                color: '#d1d1d1'
                            }
                        },
                        {
                            featureType: 'local',
                            elementType: 'all',
                            stylers: {
                                color: '#d1d1d1'
                            }
                        },
                        {
                            featureType: 'arterial',
                            elementType: 'labels',
                            stylers: {
                                visibility: 'off'
                            }
                        },
                        {
                            featureType: 'boundary',
                            elementType: 'all',
                            stylers: {
                                color: '#fefefe'
                            }
                        },
                        {
                            featureType: 'building',
                            elementType: 'all',
                            stylers: {
                                color: '#d1d1d1'
                            }
                        },
                        {
                            featureType: 'label',
                            elementType: 'labels.text.fill',
                            stylers: {
                                color: '#999999'
                            }
                        }
                    ]
                }
            },
            series: [
                {
                    type: 'lines',
                    coordinateSystem: 'bmap',
                    polyline: true,
                    data: busLines,
                    silent: true,
                    lineStyle: {
                        color: 'rgb(200, 35, 45)',
                        opacity: 0.2,
                        width: 1
                    },
                    progressiveThreshold: 500,
                    progressive: 200
                }
            ]
        })
    );


if (option && typeof option === 'object') {
    myChart.setOption(option);
}

window.addEventListener('resize', myChart.resize);


}

