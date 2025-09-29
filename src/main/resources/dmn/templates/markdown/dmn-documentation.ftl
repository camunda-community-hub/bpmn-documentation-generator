<#import "../dmn-template-util.ftl" as util/>

<#assign aDateTime = .now/>
<#assign aDate = aDateTime?date/>
<#assign aTime = aDateTime?time/>

# DMN ${dmn.fileName}

Generated on ${aDate} at ${aTime}
[](${dmn.fileName?replace('.dmn', '.svg', 'i')})

| Property | Value | Version |
|-|-|-|
| Definitions Id | ${dmn.id} | n/a |
| Exporter | ${dmn.exporter} | ${dmn.exporterVersion} |
| Execution Platform | ${dmn.executionPlatform} | ${dmn.executionPlatformVersion} |
