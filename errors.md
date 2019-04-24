# 异常信息对照表

关于错误编号（形如20102）的说明：

* 2： 1表示系统错误，如磁盘写满、没有权限等；2表示业务错误，如参数传递错误或系统参数配置不恰当。
* 01：表示模块组，此处01表示客户模块，只要相互不冲突，无严格要求，总体遵循越基础的模块数字越小的规律
* 02：表示模块内参数编号，此处表示客户模块第二种错误，只需要不重复，可查阅即可，无严格要求


错误编号|错误信息|备注|附加信息|对应的类
---|---|---|---|---
泰坦云数据同步模块|
20101|signature failure|签名失败| |SignatureFailureException
20102|signature not found|签名不存在| |SignatureNotFoundException
20103|titan server call on failed|泰坦云服务器访问失败| |TitanServerCallOnFailedException
20104|country not found|国家名不存在| |CountryNotFoundException
20105|country exist|该已经存在| |CountryExistException
20106|titan response failed|泰坦云接口返回失败结果| |ResponseFailedException
20107|parameter parse error|请求参数解析异常| |ParameterParseException