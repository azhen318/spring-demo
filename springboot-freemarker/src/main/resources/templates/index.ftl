<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>freemarker Demo</title>
</head>

<body>
    <#if host??>
        <h1>${host}</h1>
    </#if>

    <#if date??>
        <div>日期</div><h1>${date?string('yyyy-MM-dd')}</h1>
    </#if>

    <table>
       <tr>
           <td>ID</td>
           <td>姓名</td>
           <td>年龄</td>
       </tr>
        <#list stuent as value>
            <tr>
                <td>${value.id!''}</td>
                <td>${value.name!''}</td>
                <td>${value.age!''}</td>
            </tr>
        </#list>
    </table>
</body>
</html>
