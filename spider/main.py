import json

import requests
import re
from pyquery import PyQuery as pq

headers = {
    'Accept': "application/json, text/plain, */*",
    'User-Agent': "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 "
                  "Safari/537.36 "
}
url = 'https://www.zhihu.com/api/v4/questions/527445211/similar-questions?include=data%5B*%5D.answer_count%2Cauthor%2Cfollower_count&limit=5'
#url = "https://www.zhihu.com/api/v4/questions//527445211/answers?include=content"
# url = 'https://www.zhihu.com/api/v4/questions//457368252/answers?include=data%5B%2A%5D.is_normal' \
#       '%2Cadmin_closed_comment%2Creward_info%2Cis_collapsed%2Cannotation_action%2Cannotation_detail%2Ccollapse_reason' \
#       '%2Cis_sticky%2Ccollapsed_by%2Csuggest_edit%2Ccomment_count%2Ccan_comment%2Ccontent%2Ceditable_content' \
#       '%2Cattachment%2Cvoteup_count%2Creshipment_settings%2Ccomment_permission%2Ccreated_time%2Cupdated_time' \
#       '%2Creview_info%2Crelevant_info%2Cquestion%2Cexcerpt%2Cis_labeled%2Cpaid_info%2Cpaid_info_content' \
#       '%2Crelationship.is_authorized%2Cis_author%2Cvoting%2Cis_thanked%2Cis_nothelp%2Cis_recognized%3Bdata%5B%2A%5D' \
#       '.mark_infos%5B%2A%5D.url%3Bdata%5B%2A%5D.author.follower_count%2Cbadge%5B%2A%5D.topics%3Bdata%5B%2A%5D' \
#       '.settings.table_of_content.enabled&limit=5&offset=5' \
#       '&platform=desktop&sort_by=default'
# #+ str(item) +
response = requests.get(url=url, headers=headers, timeout=300)
text = response.text
jtext = json.loads(s=response.text)
if len(jtext['data']) == 0:
    print(1)

for data in jtext['data']:
    print(data)
#print(jtext['data'][0])
# for data in jtext['data']:
#     rawcontext = data.get('content')
#     vote_up = data.get('voteup_count')
#     comment_count = data.get('comment_count')
#     context = pq(rawcontext).text()
#     print(context)
#     print(vote_up)
#     print(comment_count)

# exp = r'content":"(.*?)","created_time":'
# mc = re.compile(exp, re.S)
# divList = mc.findall(text)
# divList2 =re.sub('exp','',text)
# # for item in divList:
# #     gsonStr = pq(item).text()
# #     print(gsonStr)
# #     pass
# for item in divList2:
#     print(item)
#     pass
