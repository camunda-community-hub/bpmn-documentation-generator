<#import "../bpmn-template-util.ftl" as util />
<#import "bpmn-events-definition.ftl" as eventsDefinition>" />
<#import "bpmn-extensions.ftl" as extensions />

<#macro listEvents processElements>

<#assign eventTypes = [
{ "startEvent": "Start Events" },
{ "endEvent": "End Events" },
{ "intermediateThrowEvent": "Intermediate Throw Events" },
{ "intermediateCatchEvent": "Intermediate Catch Events" },
{ "boundaryEvent": "Boundary Events"}
]/>

<#assign noEvents = processElements?filter(element -> element.flowType?ends_with("Event"))/>

<#if (noEvents?size = 0) && !skipEmptySections>
<details ${openSections}>
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
        <@util.emptySection skip=skipEmptySections section="events" quote=false />
    </#if>
</details>
</#if>

<#if (noEvents?size > 0)>
    <#list eventTypes as type>
        <#list type as eventType, title>
            <#assign events = processElements?filter(element -> element.flowType == eventType)/>
            <#if (events?size > 0)>
                <details ${openSections}>
                    <summary><h4>${title}</h4></summary>
                    <#list events as event>
                        <p>
                            <h5><@util.emptyOrNull event.name "event" /></h5>
                        <table>
                            <tr>
                                <th>Property</th>
                                <th>Value</th>
                                <th>Version</th>
                            </tr>
                            <tr>
                                <td>ID</td>
                                <td>${event.id}</td>
                                <td>n/a</td>
                            </tr>
                            <tr>
                                <td>Template</td>
                                <td><@util.emptyOrNull event.template.name "template" /></td>
                                <td><@util.emptyOrNull event.template.version "template version" /></td>
                            </tr>
                        </table>
                        <#if event.documentation?has_content>
                            <blockquote>${event.documentation}</blockquote>
                        <#else>
                            <@util.emptySection skip=skipEmptySections section="documentation" quote=true />
                        </#if>
                        </p>
                        <#if event.eventDefinitions?has_content>
                            <p>
                                <@eventsDefinition.listEventDefinition event.eventDefinitions />
                            </p>
                        </#if>
                        <#if event.extensions?has_content>
                            <p>
                                <@extensions.listExtensions event.extensions />
                            </p>
                        </#if>
                    </#list>
                </details>
            </#if>
        </#list>
    </#list>
</#if>

</#macro>