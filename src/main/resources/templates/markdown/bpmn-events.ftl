<#import "../bpmn-template-util.ftl" as util/>

<#macro listEvents processElements>

<#assign eventTypes = [
{ "startEvent": "Start Events" },
{ "endEvent": "End Events" },
{ "intermediateThrowEvent": "Intermediate Throw Events" },
{ "intermediateCatchEvent": "Intermediate Catch Events" },
{ "boundaryEvent": "Boundary Events"}
]/>

<#assign noEvents = processElements?filter(element -> element.flowType?ends_with("Event"))/>

#### Events

<#if (noEvents?size > 0)>
    <#list eventTypes as type>
        <#list type as eventType, title>
            <#assign events = processElements?filter(element -> element.flowType == eventType)>
            <#if (events?size > 0)>
##### ${title}
                <#list events as event>
* <@util.emptyOrNull event.name "event" /> (${event.id})
                </#list>
            </#if>
        </#list>
    </#list>
<#else>
No events.
</#if>


</#macro>