<#import "../bpmn-template-util.ftl" as util/>

<#macro listGlobals bpmn>

## Globals

### Messages
    <#if bpmn.messages?has_content>
        <#list bpmn.messages>
            <#items as message>
<#assign messageName><#compress><@util.emptyOrNull message.name "message" /></#compress></#assign>
* ${messageName?trim}
            </#items>
        </#list>
    <#else>
<@util.emptySection skip=skipEmptySections section="global messages" quote=false />
    </#if>

### Errors
    <#if bpmn.errors?has_content>
        <#list bpmn.errors>
            <#items as error>
<#assign errorName><#compress><@util.emptyOrNull error.name "error" /></#compress></#assign>
<#assign errorCode><#compress><@util.emptyOrNull error.errorCode "error code" /></#compress></#assign>
* ${errorName?trim} - ${errorCode?trim}
            </#items>
        </#list>
    <#else>
<@util.emptySection skip=skipEmptySections section="global errors" quote=false />
    </#if>

### Signals
    <#if bpmn.signals?has_content>
        <#list bpmn.signals>
            <#items as signal>
<#assign signalName><#compress><@util.emptyOrNull signal.name "signal" /></#compress></#assign>
* ${signalName?trim}
            </#items>
        </#list>
    <#else>
<@util.emptySection skip=skipEmptySections section="global signals" quote=false />
    </#if>

### Escalations
    <#if bpmn.escalations?has_content>
        <#list bpmn.escalations>
            <#items as escalation>
<#assign escalationName><#compress><@util.emptyOrNull escalation.name "escalation" /></#compress></#assign>
<#assign escalationCode><#compress><@util.emptyOrNull escalation.escalationCode "escalation code" /></#compress></#assign>
* ${escalationName?trim} - ${escalationCode?trim}
            </#items>
        </#list>
    <#else>
<@util.emptySection skip=skipEmptySections section="global escalations" quote=false />
    </#if>

</#macro>