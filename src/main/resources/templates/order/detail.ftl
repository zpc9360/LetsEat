<html>
  <#include  "../common/header.ftl">
<body>

<div id="wrapper" class="toggled">
<#--边栏-->
    <#include  "../common/nav.ftl">
<#--内容展示-->
    <div id="page-content-wrapper">
        <div class="container"
    </div>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-bordered">
                <thead>
                <tr class="success">
                    <th>订单ID</th>
                    <th>订单总额</th>
                </tr>
                </thead>
                <tbody>
                <tr class="success">
                    <td>${orderDTO.orderId}</td>
                    <td>￥${orderDTO.orderAmount}</td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="col-md-12 column">
            <table class="table table-bordered">
                <thead>
                <tr class="danger">
                    <th>商品编号</th>
                    <th>商品名称</th>
                    <th>单价</th>
                    <th>数量</th>
                    <th>总价</th>
                </tr>
                </thead>
                <tbody>
                    <#list orderDTO.orderDetailList as orderDetail>
                    <tr class="danger">
                        <td>${orderDetail.productId}</td>
                        <td>${orderDetail.productName}</td>
                        <td>${orderDetail.productPrice}</td>
                        <td>${orderDetail.productQuantity}</td>
                        <td>￥${orderDetail.productPrice*orderDetail.productQuantity}</td>
                    </tr>
                    </#list>
                </tbody>
            </table>
        </div>
            <div class="col-md-12 column">
            <#if orderDTO.getOrderStatusEnum().message="新订单">
                <a href="/letseat/seller/order/finish?orderId=${orderDTO.orderId}" type="button" class="btn btn-lg btn-success" >完结订单</a>
                <a href="/letseat/seller/order/cancel?orderId=${orderDTO.orderId}" type="button" class="btn btn-lg btn-danger">取消订单</a>
            </#if>
            </div>
    </div>
</div>
</div>
</body>
</html>