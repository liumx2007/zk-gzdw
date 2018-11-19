<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>楚天云展厅讲解词</title>
<style>
	body {background-image:url(page/ipad/images/jjc.jpg);background-repeat:no-repeat;}
</style>
<script src="resources/js/SpryTabbedPanels.js" type="text/javascript"></script>
<link href="resources/css/SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	var movies = new Array();
	//离任审计
	movies[0] = "cmd/send.do?play=PJP,27";
	movies[1] = "cmd/send.do?play=PJP,28";
	movies[2] = "cmd/send.do?play=PJP,29";
	movies[3] = "cmd/send.do?play=PJP,30";
	movies[4] = "cmd/send.do?play=PJP,31";
	movies[5] = "cmd/send.do?play=PJP,32";
	movies[6] = "cmd/send.do?play=PJP,33";
	//舆情
	movies[7] = "cmd/send.do?play=PJP,34";
	movies[8] = "cmd/send.do?play=PJP,35";
	movies[9] = "cmd/send.do?play=PJP,36";
	movies[10] = "cmd/send.do?play=PJP,37";
	movies[11] = "cmd/send.do?play=PJP,38";
	//数据湖北
	movies[12] = "cmd/send.do?play=PJP,74";
	movies[13] = "cmd/send.do?play=PJP,75";
	movies[14] = "cmd/send.do?play=PJP,76";
	movies[15] = "cmd/send.do?play=PJP,77";
	movies[16] = "cmd/send.do?play=PJP,78";
	movies[17] = "cmd/send.do?play=PJP,79";
	movies[18] = "cmd/send.do?play=PJP,80";
	movies[19] = "cmd/send.do?play=PJP,81";
	movies[20] = "cmd/send.do?play=PJP,82";
	movies[21] = "cmd/send.do?play=PJP,83";
	movies[22] = "cmd/send.do?play=PJP,84";
	//教育云
	movies[23] = "cmd/send.do?play=PJP,45";
	movies[24] = "cmd/send.do?play=PJP,46";
	movies[25] = "cmd/send.do?play=PJP,47";
	movies[26] = "cmd/send.do?play=PJP,48";
	movies[27] = "cmd/send.do?play=PJP,49";
	movies[28] = "cmd/send.do?play=PJP,50";
	movies[29] = "cmd/send.do?play=PJP,51";
	//工业云
	movies[30] = "cmd/send.do?play=PJP,85";
	movies[31] = "cmd/send.do?play=PJP,86";
	movies[32] = "cmd/send.do?play=PJP,87";
	movies[33] = "cmd/send.do?play=PJP,88";
	movies[34] = "cmd/send.do?play=PJP,89";
	movies[35] = "cmd/send.do?play=PJP,90";
	movies[36] = "cmd/send.do?play=PJP,91";
	movies[37] = "cmd/send.do?play=PJP,92";
	movies[38] = "cmd/send.do?play=PJP,93";
	movies[39] = "cmd/send.do?play=PJP,94";
	//平台特性
	movies[40] = "cmd/send.do?play=PJP,52";
	movies[41] = "cmd/send.do?play=PJP,53";
	movies[42] = "cmd/send.do?play=PJP,54";
	movies[43] = "cmd/send.do?play=PJP,55";
	movies[44] = "cmd/send.do?play=PJP,56";
	movies[45] = "cmd/send.do?play=PJP,57";
	movies[46] = "cmd/send.do?play=PJP,58";
	movies[47] = "cmd/send.do?play=PJP,59";
	movies[48] = "cmd/send.do?play=PJP,60";
	movies[49] = "cmd/send.do?play=PJP,61";
	movies[50] = "cmd/send.do?play=PJP,62";
	//平台价值
	movies[51] = "cmd/send.do?play=PJP,63";
	movies[52] = "cmd/send.do?play=PJP,64";
	movies[53] = "cmd/send.do?play=PJP,65";
	movies[54] = "cmd/send.do?play=PJP,66";
	movies[55] = "cmd/send.do?play=PJP,67";
	movies[56] = "cmd/send.do?play=PJP,68";
	movies[57] = "cmd/send.do?play=PJP,69";
	movies[58] = "cmd/send.do?play=PJP,70";
	movies[59] = "cmd/send.do?play=PJP,71";
	movies[60] = "cmd/send.do?play=PJP,72";
	movies[61] = "cmd/send.do?play=PJP,73";
	//大数据产业规划
	movies[62] = "cmd/send.do?play=PJP,39";
	movies[63] = "cmd/send.do?play=PJP,40";
	movies[64] = "cmd/send.do?play=PJP,41";
	movies[65] = "cmd/send.do?play=PJP,42";
	movies[66] = "cmd/send.do?play=PJP,43";
	movies[67] = "cmd/send.do?play=PJP,44";
	function sendCmd(url,index){
		var str = "";
		if(index == "8") {
			str = "确定关闭矩阵区的灯？";
		} else if(index == "9") {
			if(window.parent.index < 1) return;
			else {
				url = movies[--window.parent.index];
			}
		} else if(index == "10") {
			url = movies[window.parent.index=window.parent.index==movies.length-1?0:window.parent.index+1];
		} else if(index == "5") {
			window.parent.index = 0;
		}
		if(str != "") {
			if(confirm(str)) {
				document.getElementById("iframe").src=url;
			}
		} else {
			document.getElementById("iframe").src=url;
		}
	}
</script>
</head>
<body>
<div class="backImg">
	<img src="page/ipad/images/zjm.png" onclick="javascript:window.parent.gotoPage('page/ipad/zjm.jsp')" width="100px"/>
	<img src="page/ipad/images/dg.png" onclick="javascript:window.parent.gotoPage('page/ipad/dg.jsp')" width="100px"/>
	<img src="page/ipad/images/xt.png" onclick="javascript:window.parent.gotoPage('page/ipad/xt.jsp')" width="100px"/>
	<img src="page/ipad/images/jzp.png" onclick="javascript:window.parent.gotoPage('page/ipad/jzp.jsp')" width="100px"/>
	<img src="page/ipad/images/hdq.png" onclick="javascript:window.parent.gotoPage('page/ipad/hdq.jsp')" width="100px"/>
</div>
<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab">1、应用演示-政务应用-离任审计</li>
    <li class="TabbedPanelsTab">2、应用演示-政务应用-舆情监控系统</li>
    <li class="TabbedPanelsTab">3、应用演示-政务应用-数据湖北</li>
    <li class="TabbedPanelsTab">4、应用演示-民生领域-教育云</li>
    <li class="TabbedPanelsTab">5、应用演示-产业领域-工业云</li>
    <li class="TabbedPanelsTab">6、枢纽平台-平台特性</li>
    <li class="TabbedPanelsTab">7、枢纽平台-平台价值</li>
    <li class="TabbedPanelsTab">8、大数据产业规划</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
    <div class="TabbedPanelsContent" alt="离任审计">
    	<p><span class="title">01.</span>自党的十八届三中全会提出“自然资源资产负债表”这一理念后，省委、省政府对此高度重视，将领导干部自然资源资产离任审计试点确定为我省重大改革项目之一，省委主要领导亲自领衔推进。</p>
        <p><span class="title">02.</span>以鄂州市试点为例，如何算好地方“自然资源账”？传统的数据统计方式下，数据分散、准确性低、客观性不强、系统不全面。</p>
        <p><span class="title">03.</span>通过从遥感影像数据库、楚天云四大基础数据库、统计填报数据库提取相关数据，做到对领导干部所辖区域内的森林、土地、水等自然资源的可监控、可管理、可预警，提升精准性、增强客观性、统一并完善系统。</p>
    	<p><span class="title">04.</span>例如设置水污染模型、动态显示水污染的区域、程度变化，自主进行监测、预警；通过任职前后的监控图表比对，查看自然资源资产的整体变化。</p>
    	<p><span class="title">05.</span>同时，运用云计算、大数据技术，对数据进行整合和分析，摸清自然资源资产的“家底”，给生态资源打上“价格标签”，预测未来发展趋势，为领导干部制定针对性环境保护措施提供决策依据。</p>
    	<p><span class="title">06.</span>借助新一代信息技术落实领导干部自然资源离任审计，加强过程管控，将帮助领导干部及时掌握自然资源资产变动情况。</p>
    	<p><span class="title">07.</span>提升“绿色政绩”，切实落实创新、协调、绿色、开放和共享的发展理念。</p>
    </div>
    <div class="TabbedPanelsContent" alt="舆情">
    	<p><span class="title">01.</span>中国已有7亿网民，人在上网，民意就上网，舆论场也随之转移到网上。能否及时监控网络舆情，直接影响到民意走势，政府决策和措施落地。</p>
        <p><span class="title">02.</span>传统用人工统计或舆情软件进行舆情监控与分析，深度和广度不足，时效性差；而依托云计算和大数据技术所构建的舆情监控系统，可全天候监控互联网舆情，全面覆盖境内外13万家站点、210万个内容版块，每日数据采集量超过3亿条。</p>
        <p><span class="title">03.</span>以今年5月“高考减招”为例，舆情监控系统对湖北地区进行了监控，可以对舆情走势、境内外媒体报道、正负面比例进行实时的大数据统计分析和自动化预警。</p>
    	<p><span class="title">04.</span>监测网民观点，快速追溯各类传播渠道上首个发表的信息源。系统自主预警，5月13日至14日，舆情热度直线上升，达到预警值。此外，通过舆情分析报告，帮助相关部门综合评判网络舆情发展态势，科学决策并及时采取引导措施。</p>
    	<p><span class="title">05.</span>“高考减招”事件发生后，江苏省教育厅通过舆情监控系统，快速掌握了舆情发展态势，第一时间通过官方渠道进行回应，及时有效的地控制并引导舆论发展走向。</p>
    </div>
	<div class="TabbedPanelsContent" alt="数据湖北">
    	<p><span class="title">01.</span>大数据的运用有助于推动政府用数据管理、用数据决策。</p>
        <p><span class="title">02.</span>进而加快治理体系和治理能力现代化。</p>
        <p><span class="title">03.</span>统计大数据是党政领导管理社会、监测政策实施效果、进行宏观决策的重要依据。</p>
    	<p><span class="title">04.</span>传统信息化建设模式下，应用系统各自为政，不能相互进行数据交换和共享，成为信息孤岛。</p>
    	<p><span class="title">05.</span>针对数据存储分散的情况，省统计局大力推动“数据湖北”工程建设，建立了全省统一的统计信息资源管理中心。</p>
    	<p><span class="title">06.</span>采集历次经济普查、人口普查等统计数据以及年鉴、统计月报、快报等各类经济、社会统计数据，实现数据的集中存储和共享。</p>
    	<p><span class="title">07.</span>形成汇集了企业基层数据、综合数据的湖北省数据资源中心，使得庞大信息资源“一盘棋”变为现实。</p>
		<p><span class="title">08.</span>工业云将加速全产业链交互和集成协作，发挥“1+1>“数据湖北”工程包括国民经济信息、工业、农业、商业、物资、投资、建筑业、运输业等20余类数据，涵盖我省国民经济和社会发展各个方面的信息资源。</p>
        <p><span class="title">09.</span>系统采用多维统计指标技术与成熟的指标体系架构技术，建立多种分析手段，为领导决策提供强有力的数据支撑。</p>
        <p><span class="title">10.</span>在系统中，我们可以清晰地看到省级、地区、部门多元化的数据统计结果，并通过数据可视化，对产业发展、人均收入、社会就业等方面的情况进行科学的研判。</p>
    	<p><span class="title">11.</span>同时，通过本系统，提供面向各级领导、社会公众等不同群体的服务应用平台，提供经济普查、人口普查以及经济统计等信息的分权发布和查询应用，加快政府职能转型，打造“透明统计”，助推“数字湖北”，增强统计部门的公共服务能力。</p>
	</div>
    <div class="TabbedPanelsContent"  alt="教育云">
    	<p><span class="title">01.</span>世界经济强国，无一不是教育强国；国内经济强省，无一不是教育强省。十八届三中全会明确提出“构建利用信息化手段扩大优质教育资源覆盖面的有效机制，逐步缩小区域、城乡、校际差距”的重要任务。</p>
        <p><span class="title">02.</span>省教育厅联合烽火通信共同打造的湖北省教育资源公共服务平台，目前已正式上线运行为全省师生提供多样丰富的教、学、管服务，助力实现教育公平，促进教学模式转变。</p>
        <p><span class="title">03.</span>教育云构建了教育资源生态体系，已实现7000多部教学视频、8000多套教学资料、100多万道测验试题资源的共享。全省各地师生通过互联网即可访问教育云，根据需求使用各类教育资源，完全实现城乡教育资源无差异共享。</p>
    	<p><span class="title">04.</span>平台功能丰富，简单智能，帮助老师从备课、上课、批改作业等重复繁琐的劳动中解放出来，回归育人本质；</p>
    	<p><span class="title">05.</span>让教学突破传统形式，更多互动和乐趣；为学生、家长、老师提供实时交流互动的社区，实现家校互动。</p>
    	<p><span class="title">06.</span>同时，在云平台下所有的信息都被系统自动的采集、汇聚生成统计报表并且实时更新形成大数据。通过对各类教育数据的分析，辅助各级领导科学管理和决策。</p>
    	<p><span class="title">07.</span>教育云的正式上线，为教育信息化改革探索出了一条“政府引导、企业建设、学校应用”的发展路径。</p>
    </div>
    <div class="TabbedPanelsContent"  alt="工业云">
    	<p><span class="title">01.</span>《中国制造2025》中提出：“实施工业云及工业大数据创新应用试点，建设一批高质量的工业云服务和工业大数据平台，推动软件与服务、设计与制造资源、关键技术与标准的开放共享。”</p>
        <p><span class="title">02.</span>中小微企业在发展过程中普遍面临信息化成本高、研发能力弱、市场拓展能力不足、产业链资源和人才短缺等问题。</p>
        <p><span class="title">03.</span>省经信委联合楚天云公司，牵头开展与国内顶尖云服务平台、云应用开发商、云计算咨询机构、省内企业代表的广泛调研和座谈，共同完成湖北工业云平台的顶层设计和总体规划。</p>
    	<p><span class="title">04.</span>2016年，将逐步建设工业云基础资源和开发平台，加强与省内外工业云应用企业合作，逐步在工业云平台部署应用。</p>
    	<p><span class="title">05.</span>2017年，为工业企业提供覆盖研发、设计、生产、营销、售后等制造业各环节的云服务和云应用。</p>
    	<p><span class="title">06.</span>到2018年，帮助企业智能制造转型，低成本满足客户定制化需求，实现制造过程数据化，全产业链信息整合、协同生产。</p>
    	<p><span class="title">07.</span>依托工业云，广大中小微企业可以快速、灵活、低成本地获取云主机、云存储等基础云服务，ERP、CRM、FOL等通用云服务，以及满足行业差异化需求的专用云服务，极大降低企业信息化门槛。</p>
		<p><span class="title">08.</span>工业云将加速全产业链交互和集成协作，发挥“1+1>2”的协同效应，并为大众创业、万众创新提供平台与环境。</p>
        <p><span class="title">09.</span>同时，通过楚天云平台，可汇聚经信委、统计、工商、质监等部门数据，整合系统功能，挖掘工业大数据价值，为政府科学决策提供服务。</p>
        <p><span class="title">10.</span>支撑我省工业企业发展降成本、去库存、补短板、增效益。</p>
    	<p><span class="title">11.</span>建设工业云平台，推动万家企业上云，开发千种工业应用，打造湖北工业云生态圈和工业大数据产业，将探索出我省工业发展的共享经济新模式。</p>
	</div>
    <div class="TabbedPanelsContent" alt="平台特性">
    	<p><span class="title">01.</span>自成立以来，楚天云遵循一流标准、博采众长的工作要求。</p>
        <p><span class="title">02.</span>建成具有安全、可靠、高效和自主可控等特点的全省统一信息共享交换枢纽平台。</p>
        <p><span class="title">03.</span>安全：提供目前国内电子政务云当中最高的安全性和最完备的防护体系。</p>
    	<p><span class="title">04.</span>数据中心采用楼宇红外检测、金属探测安检等先进技术，确保机房物理安全；</p>
    	<p><span class="title">05.</span>同时，部署国内主流厂商防火墙、IPS、IDS等安全防护设备，采用系统分区部署、设置独立访问权限等方式，严格保障政府数据安全。</p>
    	<p><span class="title">06.</span>以网络安全监测预警为例，楚天云可针对重点网站和重点单位的安全威胁进行监测，汇集网络安全事件监测基础数据，并针对重点事件、嫌疑对象、攻击溯源等各种攻击行为，提供分析模型。</p>
    	<p><span class="title">07.</span>平台通过查询检索、抓包取证功能对线索核查确认，对攻击事件取证并进行分析和回放，全方位保障系统安全。</p>
		<p><span class="title">08.</span>目前，楚天云枢纽平台正在进行整体系统等保三级加固，将在七月份成为国内首个达到这一安全水平的政务云平台。</p>
        <p><span class="title">09.</span>可靠：楚天云平台采用多路网络接入、设备冗余设计、完善备份机制等方式，确保平台可靠性达到99.99%。</p>
        <p><span class="title">10.</span>高效：楚天云平台可实现分钟级的资源动态调配、秒级的网络策略下发和连通。</p>
    	<p><span class="title">11.</span>自主可控：云平台由国内ICT行业领军企业烽火通信，基于主流的开源OpenStack架构自主研发；数据中心服务器、安全、网络等全部IT设备及软件实现国产化。</p>
	</div>
    <div class="TabbedPanelsContent" alt="平台价值">
    	<p><span class="title">01.</span>枢纽平台以“先集中、后整合”为基础路线，按照统一存储规范进行清洗、和转换。</p>
        <p><span class="title">02.</span>建立湖北省基础数据库共享服务门户，实现了人口、法人、自然资源与空间地理以及宏观经济四大基础数据库的汇聚、共享和交换。</p>
        <p><span class="title">03.</span>2016年，楚天云将完成28个省直部门所有非涉密应用系统的迁移，满足省直各部门信息共享和业务协同需求。2017年，全面完成省直各部门非涉密应用系统迁移至楚天云平台，打造全省统一的云数据服务平台。通过数据集中，为数据共享和交换奠定必要基础，同时集聚数据资源，为大数据开发、培育产业生态创造有利条件。</p>
    	<p><span class="title">04.</span>平台汇集人口、法人、自然资源与空间地理以及宏观经济基础信息资源，并为各部门提供共享服务。针对四大基础库中的共享类数据，平台可以实现数据资源共享流程的全覆盖。</p>
    	<p><span class="title">05.</span>从横向分为“申请、审核、开放”三大的环节，纵向上综合考虑“线上、线下”不同流程的融合。</p>
    	<p><span class="title">06.</span>最终打破信息的地区封锁和部门分割，真正建立跨地区、跨部门的政务信息资源共建共享机制。</p>
    	<p><span class="title">07.</span>数据汇聚共享后，可加快实现“互联网+政府服务”。</p>
		<p><span class="title">08.</span>老百姓办证不必进行基本信息的重复登记、开一些无谓的奇葩证明，让老百姓在同一个界面，完成不同的办事手续，真正实现让数据多跑路，让百姓少跑腿。</p>
        <p><span class="title">09.</span>同时，帮助政府提升治理水平。以法人单位数据库为例，帮助工商、地税、国税、质监等业务部门实现“信息共享、业务联动、交叉稽核、统一监管”的经济管理和企业服务体系，为行政审批系统等项目提供法人单位数据支撑。
			<p>通过大数据建模分析，在企业族谱、行业成长趋势、个人就业与产业发展关联、重点产业分布等领域，对基础库数据进行综合分析，实现动态可视化，为政府的科学决策提供强有力的数据支撑。</p></p>
        <p><span class="title">10.</span>2016年，省级层面将实现信息枢纽交换平台的全覆盖，省直部门间数据的共享和交换原则上必须通过楚天云平台来实现，原有“点对点、一对一”的交换通道逐步关闭，真正实现数据的“一次采集、多方共享”。
			<p>通过数据交换服务平台，为各部门应用系统提供多种方式的数据交换功能，使四库数据资源动态扩展、持续构建，为用户提供一致的访问方式和服务接口。</p></p>
    	<p><span class="title">11.</span>作为智慧湖北的主战场，楚天云将按照“快、实、有力度”的工作要求，继续加快数据的汇聚、共享和交换工作。</p>
	</div>
    <div class="TabbedPanelsContent" alt="大数据产业规划">
    	<p><span class="title">01.</span>总体布局：1+1+1+N
			<p>1个园区：大数据产业园</p>
			<p>1个平台：大数据共享交换枢纽平台</p>
			<p>1个实验室：国家大数据重点实验室</p>
			<p>N个中心：湖北大数据分析处理中心、大数据运营中心、大数据创新中心、大数据培训中心、楚天大数据交易所</p>
			<p>发展思路：纵向产业聚集、横向产业拉动、构建产业生态</p>
		</p>
        <p><span class="title">02.</span>纵向产业聚集——围绕大数据产业链全流程（采集、传输、处理、存储、管理、统计、分析、挖掘、展现、应用、安全），全面发展大数据有关的技术和产品，形成一批关键技术和核心产品的突破，在全省范围内实现以楚天云为中心的大数据产业聚集。</p>
        <p><span class="title">03.</span>横向产业带动——依托“10+N朵云”， 实现省内各行业的大数据应用，并结合湖北省优势产业，实现“大数据+智能制造”，通过横向产业的融合创新，实现各产业的升级发展。</p>
    	<p><span class="title">04.</span>构建产业生态——以武汉为核心，宜昌、襄阳为支点，构建“一核、两点、多园”的大数据产业发展格局；建立“产、学、研、用”相结合的大数据创新体系；引进领军人物，培育大数据人才。</p>
    	<p><span class="title">05.</span>2018年，在排名前100的大数据企业中，引起至少50家入驻产业园，形成覆盖全省的产业链，实现8000亿规模的产业带动，大数据产业发展水平位于中部第一。</p>
    	<p><span class="title">06.</span>到2020年，1+2+1+N完全落地，达到国内国际领先水平，以大数据为主要内容的现代信息服务业产值超过10000亿元，成为国民经济新兴支柱产业。全），全面发展大数据有关的技术和产品，形成一批关键技术和核心产品的突破，在全省范围内实现以楚天云为中心的大数据产业聚集。</p>
    </div>
  </div>
</div>
<div>
	<img src="page/ipad/images/pjp.png" usemap="#Map" border="0"/>
    <map name="Map">
        <area shape="poly" coords="134,81,251,81,251,112,134,112" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=','1');" alt="欢迎词"/>
        <area shape="poly" coords="266,81,383,81,383,112,266,112" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?play=PJP,5','2');" alt="会议主题"/>
        <area shape="poly" coords="399,81,516,81,516,112,399,112" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=','3');" alt="领导致辞"/>
        <area shape="poly" coords="134,132,251,132,251,163,134,163" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?play=PJP,1','4');" alt="主题片"/>
        <area shape="poly" coords="266,132,383,132,383,163,266,163" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?play=PJP,27','5');" alt="宣讲"/>
        <area shape="poly" coords="134,184,251,184,251,215,134,215" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?play=PJP,4','6');" alt="启动仪式"/>
        <area shape="poly" coords="606,27,723,27,723,58,606,58" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=PJPDG,1','7');" alt="开灯"/>
        <area shape="poly" coords="738,27,855,27,855,58,738,58" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=PJPDG,0','8');" alt="关灯"/>
        <area shape="poly" coords="606,132,723,132,723,163,606,163" href="javascript:void(0);" onclick="sendCmd('','9');" alt="上一页"/>
        <area shape="poly" coords="738,132,855,132,855,163,738,163" href="javascript:void(0);" onclick="sendCmd('','10');" alt="下一页"/>
        <area shape="poly" coords="607,183,724,183,724,214,607,214" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?client=PJP,resume','11');" alt="播放"/>
        <area shape="poly" coords="739,183,856,183,856,214,739,214" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?client=PJP,pause','12');" alt="暂停"/>
    </map>
</div>
<iframe id="iframe" name="iframe" src="" style="display:none;"></iframe>
<script type="text/javascript">
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
</script>
</body>
</html>