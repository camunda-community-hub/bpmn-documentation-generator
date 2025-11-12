<#import "../bpmn-template-util.ftl" as util/>

<#macro listCollaboration collaboration>

## Collaboration
<sup>(${collaboration.id})</sup>

<#if collaboration.documentation?has_content>
> ${collaboration.documentation}
<#else>
> Not documented.
</#if>

<#list collaboration.extensions>
    <#items as extensionKey, extensionValue>
        <#if extensionKey == "properties">
#### ***Properties***

| Name | Value |
| --- | --- |
            <#list extensionValue>
                <#items as itemKey, itemValue>
| ${itemKey} | ${itemValue} |
                </#items>
            </#list>
        </#if>
    </#items>
</#list>

<#list collaboration.extensions>
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

<#if collaboration.participants?has_content>

## Participants

    <#list collaboration.participants>
        <#items as participant>
<#assign participantName>
<#compress>
<@util.emptyOrNull participant.name?trim "participant" />
</#compress>
</#assign>
* [${participantName?trim}](#${participant.id})
        </#items>
    </#list>

    <#list collaboration.participants>
        <#items as participant>
<#assign participantName>
<#compress>
<@util.emptyOrNull participant.name?trim "participant" />
</#compress>
</#assign>
<a id="${participant.id}"></a>
### ${participantName?trim}
            <#if participant.documentation?has_content>
> ${participant.documentation}
            <#else>
> Not documented.
            </#if>

Process [**${participant.processRef}**](#${participant.processRef})

            <#list collaboration.extensions>
                <#items as extensionKey, extensionValue>
                    <#if extensionKey == "properties">
#### ***Properties***

| Name | Value |
| --- |--- |
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

</#macro>