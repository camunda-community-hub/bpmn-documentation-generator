<#import "../bpmn-template-util.ftl" as util/>
<#import "bpmn-collaboration.ftl" as collaboration/>
<#import "bpmn-lanes.ftl" as lanes/>
<#import "bpmn-tasks.ftl" as tasks/>
<#import "bpmn-events.ftl" as events/>
<#import "bpmn-gateways.ftl" as gateways/>
<#import "bpmn-globals.ftl" as globals />

<#assign aDateTime = .now/>
<#assign aDate = aDateTime?date/>
<#assign aTime = aDateTime?time/>

<#global skipEmptySections=bpmn.suppressEmptySections />
<#global openSections="" />
<#if bpmn.openSections>
    <#global openSections="open" />
</#if>
<#global markdown=true />

# BPMN ${bpmn.fileName}

Generated on ${aDate} at ${aTime}
![Visual representation of the BPMN ${bpmn.fileName}](${bpmn.fileName?replace('.bpmn', '.' + bpmn.imageFormat , 'i')})

| Property | Value | Version |
|---|---|---|
| Definitions Id | ${bpmn.id} | n/a |
<#if bpmn.diagramRelationId?has_content>
| Diagram Relation ID | ${bpmn.diagramRelationId}| n/a |
</#if>
| Exporter | ${bpmn.exporter} | ${bpmn.exporterVersion} |
| Execution Platform | ${bpmn.executionPlatform} | ${bpmn.executionPlatformVersion} |

## Table of Contents
* [Collaboration](#collaboration)
* [Processes](#processes)
* [Tasks](#tasks)
* [Events](#events)
* [Gateways](#gateways)
* [Globals](#globals)

<a id="collaboration"></a>
<#if bpmn.collaboration?has_content>
    <@collaboration.listCollaboration bpmn.collaboration />
</#if>
<a id="processes"></a>
## Processes

    <#if bpmn.processes?has_content>
        <#list bpmn.processes as process>
<a id="${process.id}"></a>
### <@util.emptyOrNull process.name "process" />

            <#if process.documentation?has_content>
> ${process.documentation}
            <#else>
<@util.emptySection skip=skipEmptySections section="documentation" quote=false />
            </#if>

            <#if process.laneSets?has_content>
<@lanes.listLanes processLaneSets=process.laneSets/>
            </#if>

            <#if process.elements?has_content>
<a id="tasks"></a>
<@tasks.listTasks processElements=process.elements/>

<a id="events"></a>
<@events.listEvents processElements=process.elements/>

<a id="gateways"></a>
<@gateways.listGateways processElements=process.elements/>

            </#if>
        </#list>
    <#else>
<@util.emptySection skip=skipEmptySections section="processes" quote=false />
    </#if>

<a id="globals"></a>
<@globals.listGlobals bpmn />
