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
    <p>No definition defined.</p>
</#if>

</#macro>