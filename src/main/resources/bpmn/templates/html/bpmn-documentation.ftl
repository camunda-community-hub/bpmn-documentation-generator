<#import "../bpmn-template-util.ftl" as util/>
<#import "bpmn-collaboration.ftl" as collaboration/>
<#import "bpmn-processes.ftl" as processes/>
<#import "bpmn-globals.ftl" as globals/>

<#assign aDateTime=.now />
<#assign aDate=aDateTime?date />
<#assign aTime=aDateTime?time />

<#global skipEmptySections=bpmn.suppressEmptySections />
<#global openSections="" />
<#if bpmn.openSections>
    <#global openSections="open" />
</#if>


<html lang="en">
<head>
    <title>BPMN ${bpmn.fileName}</title>
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
<h1>BPMN ${bpmn.fileName}</h1>
<p>Generated on ${aDate} at ${aTime}</p>
    <#if skipEmptySections>
        <p><strong>Note:</strong> This documentation skips empty sections.</p>
    </#if>
<p>
    <img alt="Visual representation of the BPMN ${bpmn.fileName}" src="${bpmn.fileName?replace('.bpmn', '.svg', 'i')}"/>
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
        <td>${bpmn.id}</td>
        <td>n/a</td>
    </tr>
    <tr>
        <td>Exporter</td>
        <td>${bpmn.exporter}</td>
        <td>${bpmn.exporterVersion}</td>
    </tr>
    <tr>
        <td>Execution Platform</td>
        <td>${bpmn.executionPlatform}</td>
        <td>${bpmn.executionPlatformVersion}</td>
    </tr>
    </tbody>
</table>

<#if bpmn.collaboration?has_content>
    <@collaboration.listCollaboration bpmn.collaboration />
</#if>

<#if bpmn.processes?has_content>
    <@processes.listProcesses bpmn.processes />
<#else>
    <@util.emptySection skip=skipEmptySections section="processes" quote=false markdown=false />
</#if>

<@globals.listGlobals bpmn />

</article>
</body>
</html>
