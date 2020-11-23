<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="newsAPI" value="/api/news" />
<c:url var="newsURL" value="/quan-tri/bai-viet/danh-sach" />
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Danh sách bài viết</title>
</head>

<body>
	<div class="main-content">
		<form action="<c:url value='/quan-tri/bai-viet/danh-sach'/>" id="formSubmit" method="get">
			<div class="main-content-inner">
				<div class="breadcrumbs ace-save-state" id="breadcrumbs">
					<ul class="breadcrumb">
						<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Trang chủ</a></li>
					</ul>
					<!-- /.breadcrumb -->
				</div>
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
						
						<c:if test="${not empty message}">
							<div class="alert alert-${alert}">
							${message}
						</div>
						</c:if>
						
							<div class="widget-box table-filter">
								<div class="table-btn-controls">
									<div class="pull-right tableTools-container">
										<div class="dt-buttons btn-overlap btn-group">
											<c:url value="/quan-tri/bai-viet/chinh-sua" var="createNewsURL"/>
											<a flag="info"
												class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
												data-toggle="tooltip" title='Thêm bài viết'
												href='${createNewsURL}'> 
												<span>
													<i class="fa fa-plus-circle bigger-110 purple"></i>
												</span>
											</a>
											<button id="btnDelete" type="button" onclick="warningBeforeDelete()"
												class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"
												data-toggle="tooltip" title='Xóa bài viết'>
												<span> <i class="fa fa-trash-o bigger-110 pink"></i>
												</span>
											</button>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12">
									<div class="table-responsive">
										<table class="table table-bordered">
											<thead>
												<tr>
													<th><input type="checkbox" id="checkAll"></th>
													<th>Tên bài viết</th>
													<th>Mô tả ngắn</th>
													<th>Thao tác</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="item" items="${model.listResult}">
													<tr>
														<td><input type="checkbox" id="checkbox_${item.id}" value="${item.id}"></td>
														<td>${item.title}</td>
														<td>${item.shortDescription}</td>
														<td>
															<c:url var="editURL" value="/quan-tri/bai-viet/chinh-sua">
																<c:param name="id" value="${item.id}"></c:param>
															</c:url> 
															<a class="btn btn-sm btn-primary btn-edit"
																data-toggle="tooltip" title="Cập nhật bài viết"
																href='${editURL}'><i class="fa fa-pencil-square-o"
																	aria-hidden="true"></i> 
															</a>
														</td>
													</tr>
												</c:forEach>

											</tbody>
										</table>
										<ul class="pagination" id="pagination"></ul>
										<input type="hidden" value="" id="page" name="page" /> 
										<input type="hidden" value="" id="limit" name="limit" />
									</div>
								</div>

							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
	<!-- /.main-content -->
	<script type="text/javascript">
		var currentPage = ${model.page};
		var totalPage = ${model.totalPage};
		var limit = 2;
		$(function () {
			window.pagObj = $('#pagination').twbsPagination({
				totalPages: totalPage,
				visiblePages: 5,
				startPage: currentPage,
				onPageClick: function (event, page) {
					//console.info(page + ' (from options)');
					if (currentPage != page) {
						$('#page').val(page);
						$('#limit').val(limit);
						$('#formSubmit').submit();
					}
				}
			});
		});

		function warningBeforeDelete() {
			swal({
			  title: "Xác nhận xóa",
			  text: "Bạn có chắc chắn muốn xóa hay không",
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonClass: "btn-success",
			  cancelButtonClass: "btn-danger",
			  confirmButtonText: "Xác nhận",
			  cancelButtonText: "Hủy bỏ",
			}).then(function(isConfirm) {
			  if (isConfirm) {
					var ids = $('tbody input[type=checkbox]:checked').map(function () {
			            return $(this).val();
			        }).get();
					deleteNews(ids);
			  }
			});
		}

		function deleteNews(data) {
			$.ajax({
				url: '${newsAPI}',
				type: 'DELETE',
				contentType: 'application/json',
				data : JSON.stringify(data),
				success: function (result) {
					window.location.href = "${newsURL}?limit=2&page=1&message=delete_success";
				},
				error: function (error) {
					window.location.href = "${newsURL}?limit=2&page=1&message=error_system";
				}
			});
		}
	</script>
</body>

</html>