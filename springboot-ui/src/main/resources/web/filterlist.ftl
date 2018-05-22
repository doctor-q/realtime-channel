<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>集群列表</title>
    <#include 'include.ftl'>
</head>
<body>
<#include 'navbarheader.ftl'>
<#include 'sidebar.ftl'>

<div class="main-content">
    <div class="page-header">
        <h1>
            集群列表
            <small>
                <i class="icon-double-angle-right"></i>
                查看
            </small>
        </h1>
    </div>
    <div class="page-content">
        <div class="row">
            <div class="col-xs-12">
                <div class="col-xs-12">
                    <div class="table-header">
                        过滤器列表
                    </div>
                    <div class="table-responsive">
                        <div id="sample-table-2_wrapper" class="dataTables_wrapper" role="grid">

                            <table id="sample-table-2" class="table table-striped table-bordered table-hover dataTable"
                                   aria-describedby="sample-table-2_info">
                                <thead>
                                <tr role="row">
                                    <th class="center sorting_disabled" role="columnheader" rowspan="1" colspan="1"
                                        aria-label=""
                                        style="width: 88px;">
                                        <label>
                                            <input type="checkbox" class="ace">
                                            <span class="lbl"></span>
                                        </label>
                                    </th>
                                    <th class="sorting" role="columnheader" tabindex="0" aria-controls="sample-table-2"
                                        rowspan="1"
                                        colspan="1" aria-label="Domain: activate to sort column ascending"
                                        style="width: 236px;">
                                        类名
                                    </th>

                                    <th class="sorting_disabled" role="columnheader" rowspan="1" colspan="1"
                                        aria-label=""
                                        style="width: 265px;"></th>
                                </tr>
                                </thead>


                                <tbody role="alert" aria-live="polite" aria-relevant="all">
                                <#list data as filter>
                                <tr>
                                    <td class="center sorting_1">
                                        <label>
                                            <input type="checkbox" class="ace">
                                            <span class="lbl"></span>
                                        </label>
                                    </td>

                                    <td class=" ">${filter.className}</td>

                                    <td class=" ">
                                        <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">

                                        </div>
                                    </td>
                                </tr>
                                </#list>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>