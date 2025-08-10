<#import "../bpmn-template-util.ftl" as util/>

<#macro listDefinition definition>

<#if definition?has_content>
    <details>
        <summary><h4>Task Definition</h4></summary>
        <ul>
            <#list definition as key, value>
            <li><strong>${key}:</strong> ${value}</li>
            </#list>
        </ul>
    </details>
<#else>
    <@util.emptySection skip=skipEmptySections section="task definition" quote=false markdown=false/>
</#if>

</#macro>