<#import "../bpmn-template-util.ftl" as util/>

<#macro listEventDefinition eventDefinition>

<#if eventDefinition?has_content>
    <h6>Event Definition
    <table>
        <thead>
            <tr>
                <th>Key</th>
                <th>Value</th>
            </tr>
        </thead>
        <tbody>
        <#list eventDefinition as definition>
            <#list definition as definitionKey, definitionValue>
                <tr>
                    <td>${definitionKey}</td>
                    <#if definitionKey == "ref" && definitionValue?has_content>
                        <td><a href="#${definitionValue}">${definitionValue}</a></td>
                    <#else>
                        <td>${definitionValue!"n/a"}</td>
                    </#if>
                </tr>
            </#list>
        </#list>
        </tbody>
    </table>
<#else>
    <@util.emptySection skip=skipEmptySections section="event definitions defined" quote=false />
</#if>

</#macro>
