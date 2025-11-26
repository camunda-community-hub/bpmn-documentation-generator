<#import "../dmn-template-util.ftl" as util />
<#import "dmn-decisions.ftl" as decisions />
<#import "dmn-input-data.ftl" as inputdata />
<#import "dmn-knowledge-source.ftl" as knowledgesources />
<#import "dmn-business-knowledge-models.ftl" as businessknowledgemodels />

<#assign aDateTime = .now />
<#assign aDate = aDateTime?date />
<#assign aTime = aDateTime?time />

<#global skipEmptySections=dmn.suppressEmptySections />
<#global openSections="" />
<#if dmn.openSections>
    <#global openSections="open" />
</#if>
<#global markdown=true />

# DMN ${dmn.fileName}

Generated on ${aDate} at ${aTime}

![Visual representation of the DMN ${dmn.fileName}](${dmn.fileName?replace('.dmn', '.' + dmn.imageFormat, 'i')})

| Property  | Value  | Version  |
|---|---|---|
| Definitions Id | ${dmn.id} | n/a |
| Exporter | ${dmn.exporter} | ${dmn.exporterVersion} |
| Execution Platform | ${dmn.executionPlatform} | ${dmn.executionPlatformVersion} |

<@util.showDocumentation dmn.description />

<#if dmn.decisions?has_content>
<@decisions.listDecisions dmn.decisions />
<#else>
<@util.emptySection skip=skipEmptySections section="decisions" quote=false />
</#if>

<#if dmn.businessKnowledgeModels?has_content>
<@businessknowledgemodels.listbBusinessKnowledgeModels dmn.businessKnowledgeModels />
<#else>
<@util.emptySection skip=skipEmptySections section="business knowledge models" quote=false />
</#if>

<#if dmn.inputData?has_content>
<@inputdata.listInputData dmn.inputData />
<#else>
<@util.emptySection skip=skipEmptySections section="input data" quote=false />
</#if>

<#if dmn.knowledgeSources?has_content>
<@knowledgesources.listKnowledgeSources dmn.knowledgeSources />
<#else>
<@util.emptySection skip=skipEmptySections section="knowledge sources" quote=false />
</#if>