<#import "../bpmn-template-util.ftl" as util/>

<#macro listGlobals bpmn>

## Globals

### Messages
    <#if bpmn.messages?has_content>
        <#list bpmn.messages>
            <#items as message>
* <@util.emptyOrNull message.name "message" /> <sup id="${message.id}">(${message.id})</sup>
            </#items>
        </#list>
    <#else>
<@util.emptySection skip=skipEmptySections section="global messages" quote=false />
    </#if>

### Errors
    <#if bpmn.errors?has_content>
        <#list bpmn.errors>
            <#items as error>
* <@util.emptyOrNull error.name "error" /> <sup id="${error.id}">(${error.id})</sup> - <@util.emptyOrNull error.errorCode "error code" />
            </#items>
        </#list>
    <#else>
<@util.emptySection skip=skipEmptySections section="global errors" quote=false />
    </#if>

### Signals
    <#if bpmn.signals?has_content>
        <#list bpmn.signals>
            <#items as signal>
* <@util.emptyOrNull signal.name "signal" /> <sup id="${signal.id}">(${signal.id})</sup>
            </#items>
        </#list>
    <#else>
<@util.emptySection skip=skipEmptySections section="global signals" quote=false />
    </#if>

### Escalations
    <#if bpmn.escalations?has_content>
        <#list bpmn.escalations>
            <#items as escalation>
* <@util.emptyOrNull escalation.name "escalation" /> <sup id="${escalation.id}">(${escalation.id})</sup> - <@util.emptyOrNull escalation.escalationCode "escalation code" />
            </#items>
        </#list>
    <#else>
<@util.emptySection skip=skipEmptySections section="global escalations" quote=false />
    </#if>

</#macro>