<#import "../bpmn-template-util.ftl" as util/>
<#import "bpmn-collaboration.ftl" as collaboration/>
<#import "bpmn-lanes.ftl" as lanes/>
<#import "bpmn-tasks.ftl" as tasks/>
<#import "bpmn-events.ftl" as events/>
<#import "bpmn-gateways.ftl" as gateways/>

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
[](${bpmn.fileName?replace('.bpmn', '.svg', 'i')})

| Property | Value | Version |
|-|-|-|
| Definitions Id | ${bpmn.id} | n/a |
| Exporter | ${bpmn.exporter} | ${bpmn.exporterVersion} |
| Execution Platform | ${bpmn.executionPlatform} | ${bpmn.executionPlatformVersion} |

<#if bpmn.collaboration?has_content>
    <@collaboration.listCollaboration bpmn.collaboration />
</#if>

## Processes

    <#if bpmn.processes?has_content>
        <#list bpmn.processes as process>
### <@util.emptyOrNull process.name "process" />
<sup>(id: ${process.id})</sup><br/><br/>
            <#if process.documentation?has_content>
> ${process.documentation}
            <#else>
                <@util.emptySection skip=skipEmptySections section="documentation" quote=false />
            </#if>
            <#if process.laneSets?has_content>
                <@lanes.listLanes processLaneSets=process.laneSets/>
            </#if>
            <#if process.elements?has_content>
                <@tasks.listTasks processElements=process.elements/>
                <@events.listEvents processElements=process.elements/>
                <@gateways.listGateways processElements=process.elements/>
            </#if>
        </#list>
    <#else>
        <@util.emptySection skip=skipEmptySections section="processes" quote=false />
    </#if>

## Globals

### Messages
    <#if bpmn.messages?has_content>
        <#list bpmn.messages>
                <#items as message>
* <@util.emptyOrNull message.name "message" /> <sup>(${message.id})</sup>
                </#items>
        </#list>
    <#else>
No global messages.
    </#if>

### Errors
    <#if bpmn.errors?has_content>
        <#list bpmn.errors>
            <#items as error>
* <@util.emptyOrNull error.name "error" /> <sup>(${error.id})</sup> - Code ${error.errorCode}
            </#items>
        </#list>
    <#else>
No global errors.
    </#if>

### Signals
    <#if bpmn.signals?has_content>
        <#list bpmn.signals>
            <#items as signal>
* <@util.emptyOrNull signal.name "signal" /> <sup>(${signal.id})</sup>
            </#items>
        </#list>
    <#else>
No global signals.
    </#if>

### Escalations
    <#if bpmn.escalations?has_content>
        <#list bpmn.escalations>
            <#items as escalation>
* <@util.emptyOrNull escalation.name "escalation" /> <sup>(${escalation.id})</sup> - Code ${escalation.escalationCode}
            </#items>
        </#list>
    <#else>
No global escalations.
    </#if>
