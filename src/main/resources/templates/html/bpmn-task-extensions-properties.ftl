<#macro listProperties properties>

<#if properties?has_content>
    <details>
        <summary><h4>Properties</h4></summary>
        <ul>
            <#list properties as key, value>
            <li><strong>${key}:</strong> ${value}</li>
            </#list>
        </ul>
    </details>
<#else>
    <p>No properties defined.</p>
</#if>

</#macro>