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
|-|-|
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
* [<@util.emptyOrNull participant.name "participant" />](${participant.id})
        </#items>
    </#list>

    <#list collaboration.participants>
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

            <#list collaboration.extensions>
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

</#macro>