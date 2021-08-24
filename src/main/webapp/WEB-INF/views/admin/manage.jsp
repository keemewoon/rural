<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>슬기로운 농촌생활</title>

<%@ include file="/inc/asset.jsp" %>
<script src="/rural/assets/js/highcharts.js"></script>
<style>
#manageMem {
	margin-left: 88%;
	position: relative;
	top: 55px;
}
#chartList {width: 100%; margin: 0px auto;}
.col {height: 500px; border: .5px solid #D9D9D9;}

</style>
</head>
<body>

	<!-- 홈화면에서 카테고리 들어갔을 처음 나오는 화면 -->
	<%@ include file="/inc/header.jsp" %>
	
       <!-- 이미지 카테고리명 -->
        <div class="slider-area ">
            <div class="single-slider slider-height2 d-flex align-items-center" data-background="/rural/assets/img/logo/admin.jpg">
                <div class="container">
                    <div class="row">
                        <div class="col-xl-12">
                            <div class="hero-cap">
                                <h2>홈페이지 관리</h2>
                                	<button type="button" class="btn btn-dark" id="manageMem"
									onclick="location.href='/rural/admin/membermanage.do';">회원관리 하기</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
		<!-- 구현부 -->
        <div class="section-padding2">
            <div class="container">
				  <div class="row" id="chartList">

		                <div class="col">
		                	<div class="chart" id="chart1" style="margin-top: 30px;"></div>
		                </div>
		                <div class="col">
		                	<div class="chart" id="chart2" style="margin-top: 30px;"></div>
		                </div>
		                <div class="w-100"></div>
		                
		                <div class="col">
		                	<div class="chart" id="chart3" style="margin-top: 30px;"></div>
		                </div>
		                <div class="col">
		                	<div class="chart" id="chart4" style="margin-top: 30px;"></div>
		                </div>
		                <div class="w-100"></div>

       				</div>
            </div>
            
            

        </div>
	
 	<%@ include file="/inc/footer.jsp" %>
 	<%@ include file="/inc/init.jsp" %>
	
	<script>
	
		Highcharts.chart('chart1', {
		    chart: {
		        plotBackgroundColor: null,
		        plotBorderWidth: null,
		        plotShadow: false,
		        type: 'pie'
		    },
		    title: {
		        text: '유저별 게시물 TOP 10'
		    },
		    tooltip: {
		        pointFormat: '{series.name}: <b>{point.y}개</b>'
		    },
		    accessibility: {
		        point: {
		            valueSuffix: '개'
		        }
		    },
		    plotOptions: {
		        pie: {
		            allowPointSelect: true,
		            cursor: 'pointer',
		            dataLabels: {
		                enabled: true,
		                format: '<b>{point.name}</b>: {point.y}개'
		            }
		        }
		    },
		    series: [
		    	{
		        name: '게시물수',
		        colorByPoint: true,
		        data: [
			        
		        	<c:forEach items="${ list1 }" var="dto">
		        	{
			            name: '${ dto.name }',
			            y: ${ dto.cnt }
			        },
			        </c:forEach>
			        
		        ]
		    }]
		});
		
		Highcharts.chart('chart2', {
		    chart: {
		        plotBackgroundColor: null,
		        plotBorderWidth: null,
		        plotShadow: false,
		        type: 'pie'
		    },
		    title: {
		        text: '카테고리별 게시물 TOP 10'
		    },
		    tooltip: {
		        pointFormat: '{series.name}: <b>{point.y}개</b>'
		    },
		    accessibility: {
		        point: {
		            valueSuffix: '개'
		        }
		    },
		    plotOptions: {
		        pie: {
		            allowPointSelect: true,
		            cursor: 'pointer',
		            dataLabels: {
		                enabled: true,
		                format: '<b>{point.name}</b>: {point.y}개'
		            }
		        }
		    },
		    series: [
		    	{
		        name: '게시물수',
		        colorByPoint: true,
		        data: [ 
		        		{
		        			name: '주말농장',
				            y: ${ list2.farm }
		        		},
		        		{
			        		name: '일손돕기',
				            y: ${ list2.worker }
		        		},
		        		{
			        		name: '농촌체험',
				            y: ${ list2.exp }
		        		},
		        		{
			        		name: '농작물 직거래',
				            y: ${ list2.market }
		        		},
		        		{
			        		name: '커뮤니티',
				            y: ${ list2.community }
		        		},
		        		{
			        		name: '알림마당',
				            y: ${ list2.notice }
		        			
		        		}
		        ]
		    }]
		});
		
		Highcharts.chart('chart3', {
		    chart: {
		        plotBackgroundColor: null,
		        plotBorderWidth: null,
		        plotShadow: false,
		        type: 'column'
		    },
		    title: {
		        text: '총 회원 정상/정지/탈퇴자 수'
		    },
		    tooltip: {
		        pointFormat: '{series.name}: <b>{point.y}명</b>'
		    },
		    accessibility: {
		        point: {
		            valueSuffix: '명'
		        }
		    },
		    xAxis: {
    	      categories: ['정지회원', '탈퇴회원', '정상회원'],
    	      crosshair: true
		    },
		    yAxis: {
    	      min: 0,
    	      title: {
    	         		text: '인원수'         
    	      		}      
		   	},
		    plotOptions: {
		        pie: {
		            allowPointSelect: true,
		            cursor: 'pointer',
		            dataLabels: {
		                enabled: true,
		                format: '<b>{point.name}</b>: {point.y}명'
		            }
		        }
		    },
		    series: [
		    	{
		        name: '회원 상태',
		        colorByPoint: true,
		        data: [
		        	
		        		{
			        		name: '정지회원',
				            y: ${ list3.stop }
		        		},
		        		{
			        		name: '탈퇴회원',
				            y: ${ list3.out }
		        			
		        		},
		        		{
		        			name: '정상회원',
				            y: ${ list3.normal }
		        		}
		        ]
		    }]
		});
		
		Highcharts.chart('chart4', {
		    chart: {
		        plotBackgroundColor: null,
		        plotBorderWidth: null,
		        plotShadow: false,
		        type: 'column'
		    },
		    title: {
		        text: '총 회원 일반/농업인 수'
		    },
		    tooltip: {
		        pointFormat: '{series.name}: <b>{point.y}명</b>'
		    },
		    accessibility: {
		        point: {
		            valueSuffix: '명'
		        }
		    },
		    xAxis: {
    	      categories: ['일반회원', '농업인 회원'],
    	      crosshair: true
		    },
		    yAxis: {
    	      min: 0,
    	      title: {
    	         		text: '인원수'         
    	      		}      
		   	},
		    plotOptions: {
		        pie: {
		            allowPointSelect: true,
		            cursor: 'pointer',
		            dataLabels: {
		                enabled: true,
		                format: '<b>{point.name}</b>: {point.y}명'
		            }
		        }
		    },
		    series: [
		    	{
		        name: '회원 유형',
		        colorByPoint: true,
		        data: [
		        	
		        		{
			        		name: '일반 회원',
				            y: ${ list4.ordinary }
		        		},
		        		{
			        		name: '농업인 회원',
				            y: ${ list4.farmer }
		        			
		        		}
		        ]
		    }]
		});

		
		

	
	</script>
</body>
</html>