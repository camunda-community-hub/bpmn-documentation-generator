<#import "bpmn-template-util.ftl" as util/>
<#import "bpmn-lanes.ftl" as lanes/>
<#import "bpmn-tasks.ftl" as tasks/>
<#import "bpmn-events.ftl" as events/>
<#import "bpmn-gateways.ftl" as gateways/>

<#assign aDateTime = .now/>
<#assign aDate = aDateTime?date/>
<#assign aTime = aDateTime?time/>

# BPMN ${bpmn.fileName}

Generated on ${aDate} at ${aTime}
[](${bpmn.fileName?replace('.bpmn', '.svg', 'i')})

| Property | Value | Version |
|-|-|-|
| Definitions Id | ${bpmn.id} | n/a |
| Exporter | ${bpmn.exporter} | ${bpmn.exporterVersion} |
| Execution Platform | ${bpmn.executionPlatform} | ${bpmn.executionPlatformVersion} |

<#if bpmn.collaboration?has_content>
## Collaboration
<sup>(${bpmn.collaboration.id})</sup>

        <#if bpmn.collaboration.documentation?has_content>
> ${bpmn.collaboration.documentation}
        <#else>
> Not documented.
        </#if>

        <#list bpmn.collaboration.extensions>
            <#items as extensionKey, extensionValue>
                <#if extensionKey == "properties">
#### ***Properties***

| Name | Value |
|-|-|
                    <#list extensionValue>
                        <#items as itemKey, itemValue>
| ${itemKey} | ${itemValue} |
                        </#items>
                    </#list>
                </#if>
            </#items>
        </#list>

        <#list bpmn.collaboration.extensions>
            <#items as extensionKey, extensionValue>
                <#if extensionKey == "exampleData">
#### ***Example Data***
                    <#list extensionValue>
                        <#items as itemKey, itemValue>
```json
${itemValue}
```
                        </#items>
                    </#list>
                </#if>
            </#items>
        </#list>

    <#if bpmn.collaboration.participants?has_content>

## Participants

        <#list bpmn.collaboration.participants>
            <#items as participant>
* [<@util.emptyOrNull participant.name "participant" />](${participant.id})
            </#items>
        </#list>

        <#list bpmn.collaboration.participants>
            <#items as participant>
<a id="${participant.id}"></a>
### <@util.emptyOrNull participant.name "participant" />
<sup>(id: ${participant.id})</sup><br/>
                <#if participant.documentation?has_content>
> ${participant.documentation}
                <#else>
> Not documented.
                </#if>

process [**${participant.processRef}**](${participant.processRef})

                <#list bpmn.collaboration.extensions>
                    <#items as extensionKey, extensionValue>
                        <#if extensionKey == "properties">
#### ***Properties***
| Name | Value |
|-|-|
                            <#list extensionValue>
                                <#items as itemKey, itemValue>
| ${itemKey} | ${itemValue} |
                                </#items>
                            </#list>
                        </#if>
                    </#items>
                </#list>

                <#list participant.extensions>
                    <#items as extensionKey, extensionValue>
                        <#if extensionKey == "exampleData">
#### ***Example Data***
                            <#list extensionValue>
                                <#items as itemKey, itemValue>
```json
${itemValue}
```
                                </#items>
                            </#list>
                        </#if>
                    </#items>
                </#list>
            </#items>
        <#else>
None.
        </#list>
    </#if>
</#if>

## Processes

    <#if bpmn.processes?has_content>
        <#list bpmn.processes as process>
### <@util.emptyOrNull process.name "process" />
<sup>(id: ${process.id})</sup><br/><br/>
            <#if process.documentation?has_content>
> ${process.documentation}
            <#else>
> Not documented.
            </#if>
            <#if process.laneSets?has_content>
                <@lanes.listLanes processLaneSets=process.laneSets type="markdown"/>
            </#if>
            <#if process.elements?has_content>
                <@tasks.listTasks processElements=process.elements type="markdown"/>
                <@events.listEvents processElements=process.elements type="markdown"/>
                <@gateways.listGateways processElements=process.elements type="markdown"/>
            </#if>
        </#list>
    <#else>
No processes present.
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
