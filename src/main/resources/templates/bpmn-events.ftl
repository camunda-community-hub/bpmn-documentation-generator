<#import "bpmn-template-util.ftl" as util/>

<#macro listEvents processElements type>

<#assign eventTypes = [
{ "startEvent": "Start Events" },
{ "endEvent": "End Events" },
{ "intermediateThrowEvent": "Intermediate Throw Events" },
{ "intermediateCatchEvent": "Intermediate Catch Events" },
{ "boundaryEvent": "Boundary Events"}
]/>

<#assign noEvents = processElements?filter(element -> element.flowType?ends_with("Event"))/>

<#if type == "markdown">
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

<#else>
    <details>
        <summary><h4>Events</h4></summary>
        <#if (noEvents?size > 0)>
            <#list eventTypes as type>
                <#list type as eventType, title>
                    <#assign events = processElements?filter(element -> element.flowType == eventType)>
                    <#if (events?size > 0)>
                        <h5>${title}</h5>
                        <ul>
                            <#list events as event>
                                <li><@util.emptyOrNull event.name "event" /> (${event.id})</li>
                            </#list>
                        </ul>
                    </#if>
                </#list>
            </#list>
        <#else>
            <p>No events.</p>
        </#if>
    </details>
</#if>

</#macro>