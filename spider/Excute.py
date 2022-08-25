from time import sleep

import requests
import json
import re
from pyquery import PyQuery as pq
from dao.OpiDao import OpiDao


def question(url1, id, url2):
    headers = {
        'Accept': "application/json, text/plain, */*",
        'User-Agent': "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) "
                      "Chrome/65.0.3325.181 Safari/537.36 "
    }
    url = url1 + id + url2
    response = requests.get(url=url, headers=headers, timeout=300)
    text = response.text
    jtext = json.loads(text)
    datalist = []
    sleep(0.5)
    if len(jtext['data']) == 0:
        return 0
    for data in jtext['data']:
        id = str(data.get('id'))
        answer_count = data.get('answer_count')
        data = {'id': id, 'answer_count': answer_count}
        datalist.append(data)
        pass

    return datalist


def answer(url1, id, url2):
    headers = {
        'Accept': "application/json, text/plain, */*",
        'User-Agent': "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36"
    }
    url = url1 + id + url2
     response = requests.get(url=url, headers=headers, timeout=300)
    sleep(0.5)
    jtext = json.loads(s=response.text)
    contentList = []
    if len(jtext['data']) == 0:
        return 0
    for data in jtext['data']:
        rawcontext = data['content']
        voteup_count = data.get('voteup_count')
        comment_count = data.get('comment_count')
        context = pq(rawcontext).text()
        data = {'context': context, 'voteup_count': voteup_count, 'comment_count': comment_count}
        contentList.append(data)

    return contentList


item = 0
questionurl1 = "https://www.zhihu.com/api/v4/questions/"
questionurl2 = "/similar-questions?include=data%5B*%5D.answer_count%2Cauthor%2Cfollower_count&limit=5"
answerurl1 = 'https://www.zhihu.com/api/v4/questions//'
answerurl2 = '/answers?include=data%5B%2A%5D.is_normal%2Cadmin_closed_comment%2Creward_info%2Cis_collapsed' \
             '%2Cannotation_action%2Cannotation_detail%2Ccollapse_reason%2Cis_sticky%2Ccollapsed_by%2Csuggest_edit' \
             '%2Ccomment_count%2Ccan_comment%2Ccontent%2Ceditable_content%2Cattachment%2Cvoteup_count' \
             '%2Creshipment_settings%2Ccomment_permission%2Ccreated_time%2Cupdated_time%2Creview_info%2Crelevant_info' \
             '%2Cquestion%2Cexcerpt%2Cis_labeled%2Cpaid_info%2Cpaid_info_content%2Crelationship.is_authorized' \
             '%2Cis_author%2Cvoting%2Cis_thanked%2Cis_nothelp%2Cis_recognized%3Bdata%5B%2A%5D.settings' \
             '.table_of_content.enabled&limit=5&offset=' \
             + str(item) + '&platform=desktop&sort_by=default'
questionList = [{'id': "527445211", 'answer_count': "605"}, {'id': "457368252", 'answer_count': "423"}]
answerList = []
for i in range(100):
    if i == len(questionList):
        break
    qlist = question(questionurl1, questionList[i].get('id'), questionurl2)
    if qlist != 0:
        for qitem in qlist:
            if qitem not in questionList:
                questionList.append(qitem)
    print(i)
for questionitem in questionList:
    end = int(questionitem.get('answer_count')) - int(questionitem.get('answer_count')) % 5
    for item in range(0, end, 5):
        alist = answer(answerurl1, questionitem.get('id'), answerurl2)
        if alist != 0:
            for aitem in alist:
                answerList.append(aitem)

for content in answerList:
    try:
        opidao = OpiDao()
        result = opidao.createcontext(content)
        if result > 0:
            print("写入成功")
    finally:
        opidao.close()
    # print(content,end="")
