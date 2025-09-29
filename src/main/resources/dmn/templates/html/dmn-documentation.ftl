<#import "../dmn-template-util.ftl" as util/>

<#assign aDateTime=.now />
<#assign aDate=aDateTime?date />
<#assign aTime=aDateTime?time />

<#global skipEmptySections=dmn.suppressEmptySections />
<#global openSections="" />
<#if dmn.openSections>
    <#global openSections="open" />
</#if>


<html lang="en">
<head>
    <title>DMN ${dmn.fileName}</title>
    <link rel="stylesheet" href="../templates/style/markdown.css" />
    <style>
        .markdown-body {
            box-sizing: border-box;
            min-width: 200px;
            max-width: 980px;
            margin: 0 auto;
            padding: 45px;
        }

        @media (max-width: 767px) {
            .markdown-body {
                padding: 15px;
            }
        }
    </style>
</head>
<body>
<article class="markdown-body">
<h1>DMN ${dmn.fileName}</h1>
<p>Generated on ${aDate} at ${aTime}</p>
    <#if skipEmptySections>
        <p><strong>Note:</strong> This documentation skips empty sections.</p>
    </#if>
<p>
    <img alt="Visual representation of the DMN ${dmn.fileName}" src="${dmn.fileName?replace('.dmn', '.svg', 'i')}"/>
</p>
<table>
    <thead>
    <tr>
        <th>Property</th>
        <th>Value</th>
        <th>Version</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>Definitions Id</td>
        <td>${dmn.id}</td>
        <td>n/a</td>
    </tr>
    <tr>
        <td>Exporter</td>
        <td>${dmn.exporter}</td>
        <td>${dmn.exporterVersion}</td>
    </tr>
    <tr>
        <td>Execution Platform</td>
        <td>${dmn.executionPlatform}</td>
        <td>${dmn.executionPlatformVersion}</td>
    </tr>
    </tbody>
</table>
</article>
</body>
</html>
